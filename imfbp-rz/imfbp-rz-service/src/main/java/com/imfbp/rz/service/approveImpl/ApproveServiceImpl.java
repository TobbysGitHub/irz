package com.imfbp.rz.service.approveImpl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.service.approve.ApproveService;
public class ApproveServiceImpl implements ApproveService {

	private Map<String,ApproveHandler> approveHandlerMap;
	
	@Override
	public void afterApprovePass(String nodecode,SuperHeadBean headBean) throws Exception {
		ApproveHandler handler = approveHandlerMap.get(nodecode);
		
		if(handler == null){
			return;
		}
		
		handler.afterApprovePass(headBean);
	}

	public void setApproveHandlerMap(Map<String, ApproveHandler> approveHandlerMap) {
		this.approveHandlerMap = approveHandlerMap;
	}

	@Override
	public void withDrawPassBill(String nodecode, SuperHeadBean headBean)
			throws Exception {
		ApproveHandler handler = approveHandlerMap.get(nodecode);
		
		if(handler == null){
			return;
		}
		
		handler.withDrawPassBill(headBean);
		
	}
	
}
