package com.effort.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.effort.entity.CustomEntity;
import com.effort.entity.EmpMasterMappingStatus;
import com.effort.sqls.Sqls;
import com.effort.util.Api;
import com.effort.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

@Repository
public class ExtraSupportAdditionalDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public long insertIntoEmpMasterMappingStatus(
			final EmpMasterMappingStatus empMasterMappingStatus) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_INTO_EMP_MASTER_MAPPING_STATUS,
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setLong(1, empMasterMappingStatus.getCompanyId());
				ps.setInt(2, empMasterMappingStatus.getTriggerEventType());
				ps.setLong(3, empMasterMappingStatus.getMasterId());
				if(empMasterMappingStatus.getEmpMasterMappingConfigurationId() == null){
					ps.setNull(4, Types.NULL);
				}else{
					ps.setLong(4, empMasterMappingStatus.getEmpMasterMappingConfigurationId());
				}
				ps.setLong(5, empMasterMappingStatus.getEventPerformedBy());
				ps.setString(6,Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		return id;
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
	
	

}
