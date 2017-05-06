package com.imfbp.rz.service.billno;

public interface BillnoService {

	/**
	 * 根据业务规则编码获取借据号
	 * @param nodeCode
	 * @return
	 * @throws Exception
	 */
	public String getBillno(String nodeCode) throws Exception;
}
