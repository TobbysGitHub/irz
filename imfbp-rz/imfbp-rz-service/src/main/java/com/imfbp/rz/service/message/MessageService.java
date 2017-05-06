package com.imfbp.rz.service.message;

public interface MessageService {

	public void insertWorkFlowMessage(String sendUserid,String content,
			String checkuserids,String nodecode,String tenantid, String taskIds, String id) throws Exception;
	
	
	public void deleteWorkFlowMessage(String taskids) throws Exception;
	
	public void updateApproveFlag(String taskid) throws Exception;
}
