package com.effort.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.effort.beans.http.response.extra.CustomEntityData;
import com.effort.beans.http.response.extra.FormSubmissionData;
import com.effort.context.AppContext;
import com.effort.entity.Employee;
import com.effort.entity.Form;
import com.effort.entity.FormHistoryForForm;
import com.effort.entity.JmsMessage;
import com.effort.exception.EffortError;
import com.effort.manager.FormSubmissionManager;
import com.effort.manager.SyncManager;
import com.effort.manager.SyncValidationManager;
import com.effort.manager.WebManager;
import com.effort.util.Api;
import com.effort.util.Log;
import com.effort.util.SecurityUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping(value = "/v17/api")
public class CustomEntityController {
	
	Logger logger = LogManager.getLogger("synclog"); 

	@Autowired
	private SyncManager syncManager;
	
	@Autowired
	private WebManager webManager;
	
	   @Autowired
	    private FormSubmissionManager formSubmissionManager;
	
	private SyncValidationManager getSyncValidationManager() {
		SyncValidationManager syncValidationManager = AppContext.getApplicationContext()
				.getBean("syncValidationManager", SyncValidationManager.class);
		return syncValidationManager;
	}
	
	@RequestMapping(value = "/custom/entities/sync/{empId}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String customEntitiesSyncPost(@PathVariable("empId") long empId,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "apiLevel", required = true) String apiLevel,
			@RequestParam(value = "syncRequestId", required = true, defaultValue = "") String syncRequestId,
			@RequestParam(value = "syncTime", required = true) String syncTime,
			@RequestParam(value = "clientVersion", required = true) String clientVersion,
			@RequestParam(value = "signature", required = true, defaultValue = "") String signature,
			@RequestParam(value = "safeCheck", required = true, defaultValue = "true") boolean safeCheck,
			@RequestBody String jsonString, HttpServletRequest request)
			throws EffortError, JsonGenerationException, JsonMappingException, IOException {

		code = SecurityUtils.stripInvalidCharacters(code, SecurityUtils.INPUT_TYPE_TEXT);
		apiLevel = SecurityUtils.stripInvalidCharacters(apiLevel, SecurityUtils.INPUT_TYPE_NUMBER);
		syncRequestId = SecurityUtils.stripInvalidCharacters(syncRequestId, SecurityUtils.INPUT_TYPE_NUMBER);
		clientVersion = SecurityUtils.stripInvalidCharacters(clientVersion, SecurityUtils.INPUT_TYPE_TEXT);

		List<JmsMessage> jmsMessages = new ArrayList<JmsMessage>();
		getSyncValidationManager().validateSyncSignrature(empId, apiLevel, signature, jsonString, request);

		syncTime = syncTime == null ? null
				: (syncTime.equals("null") || syncTime.equals("Null") || syncTime.equals("NULL")
						|| Api.isEmptyString(syncTime)) ? null : syncTime;

		try {

			Log.info(this.getClass(), " customEntitiesSyncPost Req. EmpId: " + empId + " syncTime = " + syncTime
					+ " syncRequestId = " + syncRequestId + ", json:" + jsonString);
			
			Long syncStartTime = System.currentTimeMillis();
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			ObjectMapper mapper = new ObjectMapper();
			CustomEntityData customEntityData = mapper.readValue(jsonString, CustomEntityData.class);
			try {
				customEntityData = (CustomEntityData) Api.fromJson(jsonString, CustomEntityData.class);
			} catch (JsonParseException e) {
				Log.ignore(this.getClass(), e);
				throw new EffortError(4001, HttpStatus.PRECONDITION_FAILED);
			} catch (JsonMappingException e) {
				Log.ignore(this.getClass(), e);
				throw new EffortError(4001, HttpStatus.PRECONDITION_FAILED);
			} catch (IOException e) {
				Log.ignore(this.getClass(), e);
				throw new EffortError(4001, HttpStatus.PRECONDITION_FAILED);
			}
			
			Employee employee = webManager.getEmployee(empId+"");
			Map<String, Long> formOldClientKeys = new HashMap<String,Long>();
			Map<String, Long> formNewKeys =new HashMap<String,Long>();
			Map<Long, String> formClientKeys = new HashMap<Long, String>();
			Map<String, Long> customerNewKeys= new HashMap<String,Long>();
			Map<Long, String> formWorkflowStatusClientKeys= new HashMap<Long,String>();
			Map<String, Long> customEntityNewKeys = new HashMap<String,Long>();
			Map<String, Boolean> customerMandatoryFormFieldsMissingMap =  new HashMap<String,Boolean>();
			List<Form> formsNotInDb =  new ArrayList<Form>();
			
			FormSubmissionData formSubmissionData = new FormSubmissionData();
			formSubmissionData.setFormDataContainer(customEntityData.getFormDataContainer());
			
			Map<String, Long> customEntityOldClientKeys = null;
			Map<Long, String> customEntityClientKeys = new HashMap<Long, String>();
			List<Long> addedOrModifiedCustomEntities = new ArrayList<Long>();
			
			boolean clientFormIdExistsForCustomEntity = syncManager.processCustomEntityData(customEntityData,employee,empId+"",code,
					addedOrModifiedCustomEntities,customEntityNewKeys,customEntityClientKeys,customEntityOldClientKeys,null,apiLevel,null,ipAddress);
		
			Map<String, Object> formsPayload = new HashMap<>();
			formsPayload.put("formDataContainer", formSubmissionData.getFormDataContainer());


			String jsonResponse = formSubmissionManager.fetchSyncedForms(
			    empId, code, clientVersion, Integer.parseInt(apiLevel), syncRequestId, syncTime, signature, formsPayload
			);

			FormHistoryForForm formHistoryForForm = mapper.readValue(jsonResponse, FormHistoryForForm.class);
			String syncResponseStr = Api.toJson(formHistoryForForm);

			
		
			if(clientFormIdExistsForCustomEntity) {
				syncManager.processCustomEntityData(customEntityData,employee,empId+"",code,
						addedOrModifiedCustomEntities,customEntityNewKeys,customEntityClientKeys,customEntityOldClientKeys,null,apiLevel,formNewKeys,ipAddress);
			}
			
			
			
	        Log.info(this.getClass(), "customEntitiesSyncPost Res. EmpId: " + empId + " syncRequestId = "
	                + syncRequestId + ", response: " + syncResponseStr);
	        
	        return syncResponseStr;
		} catch (EffortError ee) {
			jmsMessages = new ArrayList<JmsMessage>();
			throw ee;
		} finally {
			//syncManager.sendMessages(jmsMessages, empId);
		}
	}
	   @PostMapping("/sync/{userId}")
	    public ResponseEntity<String> syncFormData(
	    		@PathVariable Long userId,
	            @RequestParam String code,
	            @RequestParam String clientVersion,
	            @RequestParam int apiLevel,
	            @RequestParam String syncRequestId,
	            @RequestParam String syncTime,
	            @RequestParam String signature,
	            @RequestBody Map<String, Object> requestBody) {

	        String jsonResponse = formSubmissionManager.fetchSyncedForms(
	                userId, code, clientVersion, apiLevel, syncRequestId, syncTime, signature, requestBody
	        );

	        return ResponseEntity.ok(jsonResponse);
	    }
}
