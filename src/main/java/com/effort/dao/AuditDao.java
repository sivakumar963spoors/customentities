package com.effort.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.effort.sqls.Sqls;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
@Repository
public class AuditDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public long auditCustomEntity(final long customEntityId, final long by, final String time, final String ipAddress,final String oppUserName){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(Sqls.AUDIT_CUSTOM_ENTITIES, Statement.RETURN_GENERATED_KEYS);
				ps.setNull(1, Types.BIGINT);
				ps.setLong(2, by);
				ps.setString(3, time);
				if(ipAddress==null)
				{
					ps.setNull(4,Types.VARCHAR);
				}
				else
				{
					ps.setString(4, ipAddress);
				}
				ps.setString(5, oppUserName);
				ps.setLong(6, customEntityId);
				return ps;
			}
		}, keyHolder);
		
		long id = keyHolder.getKey().longValue();
		
		return id;
	}
}
