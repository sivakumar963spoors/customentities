package com.effort.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.effort.dao.rollbackhandler.ClientIdCallBackHandler;
import com.effort.entity.CustomEntity;
import com.effort.entity.CustomEntitySpec;
import com.effort.entity.EmployeeCustomEntitySpecSettings;
import com.effort.sqls.Sqls;
import com.effort.util.Api;
import com.effort.util.Log;

@Repository
public class ExtraSupportDao {
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
	
	public List<EmployeeCustomEntitySpecSettings> getDefaultEmployeeCustomEntitySpecSettings() {
			
			List<EmployeeCustomEntitySpecSettings> employeeCustomEntitySpecSettings = new ArrayList<EmployeeCustomEntitySpecSettings>();
			 try{
				  String sql=Sqls.SELECT_DEFAULT_EMPLOYEE_CUSTOM_ENTITY_SPEC_SETTINGS;
				  employeeCustomEntitySpecSettings = jdbcTemplate
							.query(sql,	new BeanPropertyRowMapper<EmployeeCustomEntitySpecSettings>(EmployeeCustomEntitySpecSettings.class));
			  }catch(Exception e){
				  Log.info(this.getClass(), "getDefaultEmployeeCustomEntitySpecSettings : "+e);
			  }
			return employeeCustomEntitySpecSettings;
		}
	public List<EmployeeCustomEntitySpecSettings> getEmployeeCustomEntitySpecSettingsByCompanyIdAndEmpId(
			Integer companyId, long empId) {
		
		List<EmployeeCustomEntitySpecSettings> employeeCustomEntitySpecSettings = new ArrayList<EmployeeCustomEntitySpecSettings>();
		 try{
			  String sql=Sqls.SELECT_EMPLOYEE_CUSTOM_ENTITY_SPEC_SETTINGS_BY_COMPANY_ID_AND_EMP_ID;
			  employeeCustomEntitySpecSettings = jdbcTemplate.query(sql,new Object[] {companyId,empId},
								new BeanPropertyRowMapper<EmployeeCustomEntitySpecSettings>(EmployeeCustomEntitySpecSettings.class));
		  }catch(Exception e){
			  Log.info(this.getClass(), "getEmployeeCustomEntitySpecSettingsByCompanyIdAndEmpId : "+e);
		  }
		return employeeCustomEntitySpecSettings;
		
	}
	
	
	public Map<String, Long> getCustomEntityClientSideIds(List<CustomEntity> customEntities,
			String code) {
		ClientIdCallBackHandler clientIdCallBackHandler = new ClientIdCallBackHandler();
	
		code = "'" + code + "'";
	
		if (!Api.isEmptyString(code)) {
			String clientSideIds = "";
			for (CustomEntity customEntity : customEntities) {
				if (!Api.isEmptyString(customEntity.getClientSideId())) {
					if (!Api.isEmptyString(clientSideIds)) {
						clientSideIds += ",";
					}
	
					clientSideIds += "'" + customEntity.getClientSideId() + "'";
				}
			}
	
			if (!Api.isEmptyString(clientSideIds)) {
				jdbcTemplate.query(
						Sqls.SELECT_CLIENT_IDS_FOR_CUSTOM_ENTITIES.replace(
								":clientSideIds", clientSideIds).replace(
								":clientCode", code), clientIdCallBackHandler);
			}
		}
	
		return clientIdCallBackHandler.getClientIdMap();
	}
	
	
	public long addCustomEntity(final CustomEntity customEntity,final String code) {
	
		KeyHolder keyHolder = new GeneratedKeyHolder();
	
		jdbcTemplate.update(new PreparedStatementCreator() {
	
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
	
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_CUSTOM_ENTITIY, Statement.RETURN_GENERATED_KEYS);
				
				if(customEntity.getCustomEntityNo() == null){
					ps.setNull(1, Types.VARCHAR);
				}
				else
				{
					ps.setString(1, customEntity.getCustomEntityNo());
				}
				
				if(customEntity.getCustomEntityName() == null){
					ps.setNull(2, Types.VARCHAR);
				}else{
					ps.setString(2, customEntity.getCustomEntityName());
				}
				
				if(customEntity.getCustomEntityLocation() == null){
					ps.setNull(3, Types.VARCHAR);
				}else{
					ps.setString(3, customEntity.getCustomEntityLocation());
				}
				
				ps.setLong(4, customEntity.getCustomEntitySpecId());
				ps.setLong(5, customEntity.getCreatedBy());
				ps.setLong(6, customEntity.getModifiedBy());
				if(customEntity.getFormId() == null) {
					ps.setLong(7, 0);
				}else {
					ps.setLong(7, customEntity.getFormId());
				}
				
				ps.setInt(8, customEntity.getCompanyId());
				ps.setString(9, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
				ps.setString(10, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
				ps.setString(11, customEntity.getClientSideId());
				ps.setString(12, code);
				if (customEntity.getParentCustomEntityId() == null) {
					ps.setNull(13, Types.BIGINT);
				} else {
					ps.setLong(13, customEntity.getParentCustomEntityId());
				}
				if (customEntity.getAssignTo() == null) {
					ps.setNull(14, Types.NULL);
				} else {
					ps.setLong(14, customEntity.getAssignTo());
				}
				if(!Api.isEmptyString(customEntity.getLastActivityName())){
					ps.setNull(15, Types.VARCHAR);
				}else{
					ps.setString(15, customEntity.getLastActivityName());
				}
				if(!Api.isEmptyString(customEntity.getLastActivityTime())){
					ps.setNull(16, Types.VARCHAR);
				}else{
					ps.setString(16, customEntity.getLastActivityTime());
				}
				if (customEntity.getCustomEntityFieldsUniqueKey() == null) {
					ps.setNull(17, Types.VARCHAR);
				} else {
					ps.setString(17, customEntity.getCustomEntityFieldsUniqueKey());
				}
				return ps;
			}
		}, keyHolder);
	
		long id = keyHolder.getKey().longValue();
		customEntity.setCustomEntityId(id);
		return id;
	
	}
	
	public CustomEntitySpec getCustomEntitySpec(long customEntitySpecId, Integer companyId) {
		CustomEntitySpec customEntitySpec = new CustomEntitySpec();
		try{
			String sql = Sqls.SELECT_CUSTOM_ENTITY_SPEC_BY_CUSTOM_ENTITY_SPEC_ID;
			
			customEntitySpec = jdbcTemplate.queryForObject(sql,new Object[] {customEntitySpecId,companyId},
					new BeanPropertyRowMapper<CustomEntitySpec>(CustomEntitySpec.class));
		}
		catch(Exception e){
			Log.info(getClass(), "getCustomEntitySpec() // Exception occured ",e);
			//StackTraceElement[] stackTrace = e.getStackTrace();
			//getWebExtensionManager().sendExceptionDetailsMail("Exception Occured in getCustomEntitySpec method",e.toString(),stackTrace,companyId);
		}
		return customEntitySpec;
	}

	public CustomEntity getCustomEntityByCustomEntityId(String customEntityId) {
		CustomEntity customEntity = new CustomEntity();
		try{
			String sql = Sqls.SELECT_CUSTOM_ENTITY_BY_CUSTOM_ENTITY_ID;
			
			customEntity= jdbcTemplate.queryForObject(sql,new Object[] {customEntityId},
					new BeanPropertyRowMapper<CustomEntity>(CustomEntity.class));
		}
		catch(Exception e){
			Log.info(getClass(), "getCustomEntityByCustomEntityId() // Exception occured ",e);
		}
		return customEntity;
	}
	public int modifyCustomEntity(CustomEntity customEntity) {

		return jdbcTemplate.update(
				Sqls.UPDATE_CUSTOM_ENTITY,
				new Object[] {
						customEntity.getCustomEntityNo(),
						customEntity.getCustomEntityName(),
						customEntity.getCustomEntityLocation(),
						customEntity.getClientSideId(),
						customEntity.getClientCode(),
						customEntity.getParentCustomEntityId(),
						customEntity.getModifiedBy(),
						Api.getDateTimeInUTC(new Date(System.currentTimeMillis())),
						customEntity.getAssignTo(),
						customEntity.getLastActivityName(),
						customEntity.getLastActivityTime(),
						customEntity.getCustomEntityFieldsUniqueKey(),
						customEntity.getFormId(),
						customEntity.getCustomEntityId() 
					});
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertOrUpdateEmployeeCustomEntityMapping(final List<Long> mappedCustomEntityIds,final long customEntitySpecId,
			final long empId) {
		
		jdbcTemplate.batchUpdate(
				Sqls.INSERT_OR_UPDATE_EMPLOYEE_CUSTOM_ENTITY_MAPPING_FOR_EMP,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException 
					{
						Long mappedCustomEntityId = mappedCustomEntityIds.get(i);
						ps.setLong(1,empId);
						ps.setLong(2, customEntitySpecId);
						ps.setLong(3, mappedCustomEntityId);
						ps.setString(4, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
						ps.setString(5, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					}
					@Override
					public int getBatchSize() {
						return mappedCustomEntityIds.size();
					}
				});
	}
}
