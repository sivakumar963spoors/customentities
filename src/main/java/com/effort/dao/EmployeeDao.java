package com.effort.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.effort.entity.Employee;
import com.effort.settings.Constants;
import com.effort.sqls.Sqls;
@Repository
public class EmployeeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;



	public Employee getEmployee(String id) {
		Employee employee = null;
		try {
			employee = jdbcTemplate.queryForObject(Sqls.SELECT_EMPLOYEE_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<Employee>(Employee.class));
		} catch (Exception e) {
			//StackTraceElement[] stackTrace = e.getStackTrace();
			//getWebExtensionManager().sendExceptionDetailsMail("Exception Occured in getEmployee method",e.toString(),stackTrace,0);
			e.printStackTrace();
		}
		return employee;
	}
	
	
}
