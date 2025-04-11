package com.effort.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.effort.beans.http.response.extra.CustomEntityData;
import com.effort.context.AppContext;
import com.effort.dao.ExtraSupportDao;
import com.effort.entity.CustomEntity;
import com.effort.entity.CustomEntitySpec;
import com.effort.entity.EmpMasterMappingStatus;
import com.effort.entity.Employee;
import com.effort.entity.EmployeeCustomEntitySpecSettings;
import com.effort.entity.JmsMessage;
import com.effort.exception.EffortError;
import com.effort.settings.Constants;
import com.effort.util.Api;
import com.effort.util.Api.DateConversionType;
import com.effort.util.Log;

@Service
public class SyncManager {
	
	@Autowired
	private Constants constants;

	@Autowired
	private ExtraSupportDao extraSupportDao;
	
	private WebAdditionalSupportExtraManager getWebAdditionalSupportExtraManager() {
		WebAdditionalSupportExtraManager webAdditionalSupportExtraManager = AppContext.getApplicationContext()
				.getBean("webAdditionalSupportExtraManager", WebAdditionalSupportExtraManager.class);
		return webAdditionalSupportExtraManager;
	}
	

	private WebAdditionalSupportManager getWebAdditionalSupportManager(){
		WebAdditionalSupportManager webAdditionalSupportManager = AppContext.getApplicationContext().getBean("webAdditionalSupportManager",WebAdditionalSupportManager.class);
		return webAdditionalSupportManager;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean processCustomEntityData(CustomEntityData customEntityData,
			Employee employee, String empId, String code,
			List<Long> addedOrmodifiedCustomEntities,
			Map<String, Long> customEntityNewKeys,
			Map<Long, String> customEntityClientKeys,
			Map<String, Long> customEntityOldClientKeys,String effortToken,String apiLevel, Map<String, Long> formNewKeys,String ipAddress) throws EffortError 
	{

		boolean clientFormIdExistsForCustomEntity = false;
		List<EmployeeCustomEntitySpecSettings> defaultCustomEnitySpecSettings = extraSupportDao.getDefaultEmployeeCustomEntitySpecSettings();
		Map<String,String> customEntitySpecSettingMap = (Map)Api.getMapFromList(defaultCustomEnitySpecSettings, "key", "value");
		
		List<EmployeeCustomEntitySpecSettings> employeeCustomEntitySpecSettings = 
				extraSupportDao.getEmployeeCustomEntitySpecSettingsByCompanyIdAndEmpId(employee.getCompanyId(), employee.getEmpId());
		
		Map<Long,List<EmployeeCustomEntitySpecSettings>> employeeCustomEntitySpecSettingsMap = (Map)
				Api.getResolvedMapFromList(employeeCustomEntitySpecSettings, "customEntitySpecId");
		
		List<CustomEntity> customEntityAdded = customEntityData
				.getCustomEntities().getAdded();

		customEntityOldClientKeys = extraSupportDao.getCustomEntityClientSideIds(
				customEntityAdded, code);

		for (CustomEntity customEntity : customEntityAdded) {
			customEntity.setCompanyId(employee.getCompanyId());
			
			if (customEntity.getClientSideId() != null) {
				Long id = customEntityOldClientKeys.get(customEntity
						.getClientSideId());
			if (id == null) {
				customEntity.setCreatedBy(employee.getEmpId());
				customEntity.setModifiedBy(employee.getEmpId());
				Api.convertDateTimesToGivenType(customEntity, DateConversionType.XSD_TO_STADARD,
						"lastActivityTime");
				if(customEntity.getFormId() == null && !Api.isEmptyString(customEntity.getClientFormId())) {
					clientFormIdExistsForCustomEntity = true;
				}
				
				long customEntityId = extraSupportDao.addCustomEntity(customEntity, code);
				if (customEntity.getCustomEntitySpecId() != null) {
					CustomEntitySpec customEntitySpec = extraSupportDao.getCustomEntitySpec(customEntity.getCustomEntitySpecId(), employee.getCompanyId());
					if (CustomEntitySpec.CONFIGURATION_BASED_MAPPING == customEntitySpec.getCustomEntityEmpMappingType()) {
						getWebAdditionalSupportExtraManager().insertIntoEmpMasterMappingStatus(
								Long.parseLong(employee.getCompanyId() + ""), customEntity.getCustomEntityId(),
								null, EmpMasterMappingStatus.CUSTOM_ENTITY_EVENT_TRIGGER, employee.getEmpId());
					} else {
						if (employeeCustomEntitySpecSettingsMap != null
								&& !employeeCustomEntitySpecSettingsMap.isEmpty()) {
							Map<String, String> settingMap = new HashMap<String, String>();
							if (employeeCustomEntitySpecSettingsMap
									.get(customEntity.getCustomEntitySpecId()) != null) {
								List<EmployeeCustomEntitySpecSettings> customEntitySpecsSettings = employeeCustomEntitySpecSettingsMap
										.get(customEntity.getCustomEntitySpecId());
								settingMap = (Map) Api.getMapFromList(customEntitySpecsSettings, "key", "value");
							} else {
								settingMap = customEntitySpecSettingMap;
							}

							String isMapCustomEntityToEmployee = constants.getMapCustomEntitySpecToEmployeeKey();
							if (settingMap.get(isMapCustomEntityToEmployee) != null
									&& "true".equalsIgnoreCase(settingMap.get(isMapCustomEntityToEmployee))) {
								List<Long> mappedCustomEntityIds = new ArrayList<Long>();
								mappedCustomEntityIds.add(customEntityId);
								extraSupportDao.insertOrUpdateEmployeeCustomEntityMapping(mappedCustomEntityIds,
										customEntity.getCustomEntitySpecId(), Long.parseLong(empId));
							}
						}
					}

				}

				customEntityNewKeys.put(customEntity.getClientSideId(), customEntity.getCustomEntityId());
				customEntityClientKeys.put(customEntity.getCustomEntityId(), customEntity.getClientSideId());
				addedOrmodifiedCustomEntities.add(customEntity.getCustomEntityId());

				try {
					Log.audit(employee.getCompanyId() + "", empId, "customer", "add", customEntity.toString(),
							null);
				} catch (Exception e) {
					Log.info(this.getClass(), e.toString(), e);
				}
				getWebAdditionalSupportManager().logCustomEntityAudit(customEntity.getCustomEntityId(),
						employee.getEmpId(), ipAddress, null);
			} else {
					customEntity.setCustomEntityId(id);
					customEntity.setClientCode(code);
					customEntity.setModifiedBy(employee.getEmpId());
					if(formNewKeys!=null && customEntity.getFormId() == null && !Api.isEmptyString(customEntity.getClientFormId())) {
						String clientFormId = customEntity.getClientFormId();
						Long formId = formNewKeys.get(clientFormId);
						customEntity.setFormId(formId);
					}else if(customEntity.getFormId() == null) {
						CustomEntity oldCustomEntity = extraSupportDao.getCustomEntityByCustomEntityId(customEntity.getCustomEntityId()+"");
						customEntity.setFormId(oldCustomEntity.getFormId());
					}
					
					Api.convertDateTimesToGivenType(customEntity, DateConversionType.XSD_TO_STADARD, "lastActivityTime");
					extraSupportDao.modifyCustomEntity(customEntity);
					customEntityNewKeys.put(customEntity.getClientSideId(),id);
					customEntityClientKeys.put(id,customEntity.getClientSideId());
					addedOrmodifiedCustomEntities.add(customEntity.getCustomEntityId());
					
				}
			} else {
				throw new EffortError(4001,
						HttpStatus.PRECONDITION_FAILED);
			}
		}

		List<CustomEntity> customEntityModified = customEntityData
				.getCustomEntities().getModified();
		for (CustomEntity customEntity : customEntityModified) {
			if (customEntity.getCustomEntityId() > 0) {
				customEntity.setModifiedBy(employee.getEmpId());
				Api.convertDateTimesToGivenType(customEntity, DateConversionType.XSD_TO_STADARD, "lastActivityTime");
				if(formNewKeys!=null && customEntity.getFormId() == null && !Api.isEmptyString(customEntity.getClientFormId())) {
					String clientFormId = customEntity.getClientFormId();
					Long formId = formNewKeys.get(clientFormId);
					customEntity.setFormId(formId);
				}
				extraSupportDao.modifyCustomEntity(customEntity);
				CustomEntitySpec customEntitySpec = extraSupportDao.getCustomEntitySpec(customEntity.getCustomEntitySpecId(), employee.getCompanyId());
				if (CustomEntitySpec.CONFIGURATION_BASED_MAPPING == customEntitySpec.getCustomEntityEmpMappingType()) {
					getWebAdditionalSupportExtraManager().insertIntoEmpMasterMappingStatus(
							Long.parseLong(employee.getCompanyId() + ""), customEntity.getCustomEntityId(),
							null, EmpMasterMappingStatus.CUSTOM_ENTITY_EVENT_TRIGGER, employee.getEmpId());
				}
				addedOrmodifiedCustomEntities.add(customEntity.getCustomEntityId());
				getWebAdditionalSupportManager().logCustomEntityAudit(
						customEntity.getCustomEntityId(),
						employee.getEmpId(),ipAddress,null);
			}
		}
		
		
		return clientFormIdExistsForCustomEntity;
	
	}
}
