package com.effort.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effort.context.AppContext;
import com.effort.dao.EmployeeDao;
import com.effort.entity.Employee;

@Service
public class WebManager {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private EmployeeDao getEmployeeDao() {
		EmployeeDao employeeDao = AppContext.getApplicationContext().getBean("employeeDao", EmployeeDao.class);
		return employeeDao;
	}
	
	
	public Employee getEmployee(String empId) {
		return employeeDao.getEmployee(empId);
	}
	
	
}
