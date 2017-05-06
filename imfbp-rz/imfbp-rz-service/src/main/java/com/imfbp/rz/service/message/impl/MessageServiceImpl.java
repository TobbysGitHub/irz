package com.imfbp.rz.service.message.impl;

import org.springframework.stereotype.Component;

import com.ifbp.message.rpc.smallmessage.domain.SmallMessage;
import com.ifbp.message.rpc.smallmessage.service.SmallMessageRpcService;
import com.imfbp.rz.dao.pubnodevoinfo.PubNodevoinfoDao;
import com.imfbp.rz.domain.pubnodevoinfo.PubNodevoinfo;
import com.imfbp.rz.domain.pubnodevoinfo.query.PubNodevoinfoQuery;
import com.imfbp.rz.service.message.MessageService;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.utils.exception.ResultException;
@Component("messageService")
public class MessageServiceImpl implements MessageService {

	private PubNodevoinfoDao pubNodevoinfoDao;
	
	private SmallMessageRpcService smallMessageRpcClient;
	
	@Override
	public void insertWorkFlowMessage(String sendUserid, String content,
			String checkuserids,String nodeCode,String tenantid, String taskIds,String id) throws Exception {
		PubNodevoinfo voinfo = getNodeVOInfo(nodeCode);
		//处理消息 通知下一位审批
		SmallMessage message = new SmallMessage();
		message.setType(2);
		message.setTitle(voinfo.getNodename());
		message.setContent(content);
		message.setModuleValue(nodeCode);
		message.setBillid(id);
		message.setSystemid("4");
		message.setUserids(checkuserids);
		message.setCreator(sendUserid);
		message.setCreated(DateUtil.getTs());
		message.setTenantid(tenantid);
		message.setTaskids(taskIds);
		message.setApproveflag(0);
		
		smallMessageRpcClient.setMessage(message);
	}
	
	private PubNodevoinfo getNodeVOInfo(String nodeCode) {
		PubNodevoinfoQuery query = new PubNodevoinfoQuery();
		query.setNodecode(nodeCode);
		PubNodevoinfo voinfo = pubNodevoinfoDao.getPubNodevoinfoByFuncode(query);
		
		if(voinfo == null) {
			throw new ResultException("没有单据注册信息！请检查pub_nodevoinfo表");
		}
		
		return voinfo;
	}


	public void setPubNodevoinfoDao(PubNodevoinfoDao pubNodevoinfoDao) {
		this.pubNodevoinfoDao = pubNodevoinfoDao;
	}

	public void setSmallMessageRpcClient(
			SmallMessageRpcService smallMessageRpcClient) {
		this.smallMessageRpcClient = smallMessageRpcClient;
	}

	@Override
	public void deleteWorkFlowMessage(String taskids) throws Exception {
		smallMessageRpcClient.deleteMessageByTaskIds(taskids);
	}

	@Override
	public void updateApproveFlag(String taskid) throws Exception {
		smallMessageRpcClient.setMessageApproveFlag(taskid);
	}
	
	
}
