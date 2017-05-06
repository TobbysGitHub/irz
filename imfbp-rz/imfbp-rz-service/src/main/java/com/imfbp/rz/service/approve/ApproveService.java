package com.imfbp.rz.service.approve;

import com.imfbp.rz.domain.pub.SuperHeadBean;

public interface ApproveService {

	
	public void afterApprovePass(String nodecode,SuperHeadBean headBean) throws Exception;
	
	
	/**
	 * 审批通过后 弃审
	 * @param nodecode
	 * @param headBean
	 * @throws Exception
	 */
	public void withDrawPassBill(String nodecode,SuperHeadBean headBean) throws Exception;
}
