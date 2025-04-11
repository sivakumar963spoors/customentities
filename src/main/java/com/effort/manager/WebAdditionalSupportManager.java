package com.effort.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.effort.dao.AuditDao;
import com.effort.util.Api;

@Service
public class WebAdditionalSupportManager {

	@Autowired
	private AuditDao auditDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void logCustomEntityAudit(long customEntityId, long by,String ipAddress, String oppUserName ) {
		String time = Api
				.getDateTimeInUTC(new Date(System.currentTimeMillis()));
		long auditParent = auditDao.auditCustomEntity(customEntityId, by, time, ipAddress, oppUserName);
	}

}
