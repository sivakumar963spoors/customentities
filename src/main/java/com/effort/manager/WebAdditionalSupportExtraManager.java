package com.effort.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effort.dao.ExtraSupportAdditionalDao;
import com.effort.entity.EmpMasterMappingStatus;

@Service
public class WebAdditionalSupportExtraManager {
	
	@Autowired
	private ExtraSupportAdditionalDao extraSupportAdditionalDao;
	
	
	public void insertIntoEmpMasterMappingStatus(Long companyId,Long masterId,Long empMasterMappingConfigurationId,Integer TriggerEventType,Long eventPerformedBy) {
		EmpMasterMappingStatus empMasterMappingStatus = new EmpMasterMappingStatus(companyId,TriggerEventType,masterId,empMasterMappingConfigurationId,eventPerformedBy);
		extraSupportAdditionalDao.insertIntoEmpMasterMappingStatus(empMasterMappingStatus);
	}
}
