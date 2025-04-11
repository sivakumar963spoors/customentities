package com.effort.entity;



import java.io.Serializable;
import java.util.List;

public class StatusBean implements Serializable,Comparable<StatusBean>{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer processingStatus;
	private Integer retryCount;
	private String comment;
	private List<String> comments;
	private String idString;
	private Integer validationStatus;
	private Integer processingResult;
	private boolean retry;
	private String remarks;
	private long totalCount;
	private long successCount;
	private long processingTime;
	private boolean deleted;
	private int firstInstanceRowNumber;
	private long failedCount;
	private long validationSuccessCount;
	private long validationFailedCount;
	private long addedCount;
	private long addedOrModifiedCount;
	private long modifiedCount;
	private long deletedCount;
	private String errorDetails;
	private String errorDisplayMessage;
	private Long workId;
	
	
	public static final int PROCESSING_STATUS_UNPROCESSED=0;
	public static final int PROCESSING_STATUS_ON_PROCESS = 5;
	public static final int PROCESSING_STATUS_FAILED = -1;
	public static final int PROCESSING_STATUS_SUCCESS = 1;
	public static final int PROCESSING_STATUS_ALREADY_CREATED = -2;
	
	private boolean isError = false;
	
	private int rowCount;
	private boolean hasError;
	private int validRows;

	private long historyId;
	private boolean exceptionOccured;
	private String exception;
	private String errorLogId;
	
	public StatusBean() {}
	public StatusBean(String idString,String comment) {
		this.idString=idString;
		this.comment=comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(Integer processingStatus) {
		this.processingStatus = processingStatus;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	
	public Integer getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(Integer validationStatus) {
		this.validationStatus = validationStatus;
	}

	public Integer getProcessingResult() {
		return processingResult;
	}

	public void setProcessingResult(Integer processingResult) {
		this.processingResult = processingResult;
	}

	@Override
	public String toString() {
		return "StatusBean [id=" + id + ", processingStatus="
				+ processingStatus + ", retryCount=" + retryCount
				+ ", comment=" + comment + ", comments=" + comments
				+ ", idString=" + idString + ", isError=" + isError + "]";
	}

	public boolean isRetry() {
		return retry;
	}

	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}

	public long getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getFirstInstanceRowNumber() {
		return firstInstanceRowNumber;
	}

	public void setFirstInstanceRowNumber(int firstInstanceRowNumber) {
		this.firstInstanceRowNumber = firstInstanceRowNumber;
	}
	@Override
	public int compareTo(StatusBean o) {

		long result =this.getId()-o.getId();
	       if(result>0){
		 return 1;
	       }else if(result<0){
		 return -1;
	       }else {
		 return 0;
	       }
	}
	public long getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public int getValidRows() {
		return validRows;
	}
	public void setValidRows(int validRows) {
		this.validRows = validRows;
	}
	public long getHistoryId() {
		return historyId;
	}
	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}
	public boolean isExceptionOccured() {
		return exceptionOccured;
	}
	public void setExceptionOccured(boolean exceptionOccured) {
		this.exceptionOccured = exceptionOccured;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getErrorLogId() {
		return errorLogId;
	}
	public void setErrorLogId(String errorLogId) {
		this.errorLogId = errorLogId;
	}
	public long getValidationSuccessCount() {
		return validationSuccessCount;
	}
	public void setValidationSuccessCount(long validationSuccessCount) {
		this.validationSuccessCount = validationSuccessCount;
	}
	public long getValidationFailedCount() {
		return validationFailedCount;
	}
	public void setValidationFailedCount(long validationFailedCount) {
		this.validationFailedCount = validationFailedCount;
	}
	public long getAddedCount() {
		return addedCount;
	}
	public void setAddedCount(long addedCount) {
		this.addedCount = addedCount;
	}
	public long getModifiedCount() {
		return modifiedCount;
	}
	public void setModifiedCount(long modifiedCount) {
		this.modifiedCount = modifiedCount;
	}
	public long getDeletedCount() {
		return deletedCount;
	}
	public void setDeletedCount(long deletedCount) {
		this.deletedCount = deletedCount;
	}
	public long getAddedOrModifiedCount() {
		return addedOrModifiedCount;
	}
	public void setAddedOrModifiedCount(long addedOrModifiedCount) {
		this.addedOrModifiedCount = addedOrModifiedCount;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public String getErrorDisplayMessage() {
		return errorDisplayMessage;
	}
	public void setErrorDisplayMessage(String errorDisplayMessage) {
		this.errorDisplayMessage = errorDisplayMessage;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	
	

	
}
