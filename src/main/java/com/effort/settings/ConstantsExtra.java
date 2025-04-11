package com.effort.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import com.effort.util.Log;

@Component
public class ConstantsExtra {
	


	private boolean asynchronousProcess;
	private boolean ltfsLogin;
	private boolean asynchronousCustomEntityProcess;
	public static final int FORM_FIELD_TYPE_METER_READING = 37;
	public static final int FORM_FIELD_TYPE_REGISTRATION_NUMBER_READER = 38;
	public static final int FORM_FIELD_TYPE_SIGNATURE = 13;
	public static final int FORM_FIELD_TYPE_MULTI_DOCUMENT = 41;
	public static final int FORM_FIELD_TYPE_MULTI_IMAGE = 42;
	public static final int FORM_FIELD_TYPE_DURATION = 44;
	public static final int FORM_FIELD_TYPE_LOCATION_DIFF = 45;
	public static final int WORK_ACTION_UNPROCESSED = 0;
	public static final int FORM_FIELD_TYPE_LOCATION_ADDRESS = 47;
	@Value("$intronLifeScienceCompanyId")
	private String intronLifeScienceCompanyId;
	private String backUpSqliteFile;
	private String imeiBasedProvisioningKey;
	private boolean imeiBasedProvisioning;
	private String enableActivityLocationToResolveAddressKey;
	private long global_company_skeleton_custom_entity_last_activity_name_field_spec_id;
	private long global_company_skeleton_custom_entity_last_activity_time_field_spec_id;
	private Long skeletonWorkStateTypeFieldSpecId;
	private String allowGigUserCreationKey;
	private boolean allowGigUserCreation;
	private int gmetCompanyId;
	private String fireBaseMulticastMessageIOSUrl;
	private String maruthiCompanyId;
	private String pushingBulkUploadsWorksToDWHDailyNight;
	private Integer vstCompanyId;
	private Integer employeeEnrollmentWorkSpecId;
	private String maruthiCallCenterFieldSpecUniqueId;
	private String signOutConditionDurationInHoursKey;
	private int amwCompanyId;
	private String fireBaseMulticastMessageUrl;
	
	public String getSignOutConditionDurationInHoursKey() {
		return signOutConditionDurationInHoursKey;
	}

	
	public boolean isAsynchronousCustomEntityProcess() {
		return asynchronousCustomEntityProcess;
	}
	public void setAsynchronousCustomEntityProcess(boolean asynchronousCustomEntityProcess) {
		this.asynchronousCustomEntityProcess = asynchronousCustomEntityProcess;
	}
	public boolean isAsynchronousProcess() {
		return asynchronousProcess;
	}
	
	
	public String getImeiBasedProvisioningKey() {
		return imeiBasedProvisioningKey;
	}

	public boolean isLtfsLogin() {
		return ltfsLogin;
	}

	public void setAsynchronousProcess(boolean asynchronousProcess) {
		this.asynchronousProcess = asynchronousProcess;
	}
	public String getIntronLifeScienceCompanyId() {
		return intronLifeScienceCompanyId;
	}
	public void setIntronLifeScienceCompanyId(String intronLifeScienceCompanyId) {
		this.intronLifeScienceCompanyId = intronLifeScienceCompanyId;
	}
	
	public String getBackUpSqliteFile() {
		return backUpSqliteFile;
	}
	public String getEnableActivityLocationToResolveAddressKey() {
		return enableActivityLocationToResolveAddressKey;
	}
	public long getGlobal_company_skeleton_custom_entity_last_activity_name_field_spec_id() {
		return global_company_skeleton_custom_entity_last_activity_name_field_spec_id;
	}

	public long getGlobal_company_skeleton_custom_entity_last_activity_time_field_spec_id() {
		return global_company_skeleton_custom_entity_last_activity_time_field_spec_id;
	}
	
	public Long getSkeletonWorkStateTypeFieldSpecId() {
		return skeletonWorkStateTypeFieldSpecId;
	}

	
	public String getAllowGigUserCreationKey() {
		return allowGigUserCreationKey;
	}
	

	public String getMaruthiCompanyId() {
		return maruthiCompanyId;
	}
	public String getPushingBulkUploadsWorksToDWHDailyNight() {
		return pushingBulkUploadsWorksToDWHDailyNight;
	}

	public Integer getVstCompanyId() {
		return vstCompanyId;
	}
	public Integer getEmployeeEnrollmentWorkSpecId() {
		return employeeEnrollmentWorkSpecId;
	}
	public String getMaruthiCallCenterFieldSpecUniqueId() {
		return maruthiCallCenterFieldSpecUniqueId;
	}
	public int getGmetCompanyId() {
		return gmetCompanyId;
	}
	public int getAmwCompanyId() {
		return amwCompanyId;
	}
	public String getFireBaseMulticastMessageUrl() {
		return fireBaseMulticastMessageUrl;
	}
	public String getFireBaseMulticastMessageIOSUrl() {
		return fireBaseMulticastMessageIOSUrl;
	}
}
