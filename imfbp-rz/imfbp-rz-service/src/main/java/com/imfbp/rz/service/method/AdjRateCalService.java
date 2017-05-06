package com.imfbp.rz.service.method;

import java.util.List;

import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.exception.BusinessException;

/****
 * 
 * 利率调整计算租金计划
 * 
 * @author Administrator
 *
 */
public interface AdjRateCalService {

	/******
	 * 
	 * 利率调整，重新计算租金计划表
	 * 
	 * @param rzAdjInt
	 * @param adjIntLeaseList
	 * @return
	 */
	public List<RzPricecalLease> calLease(RzAdjInt rzAdjInt, List<RzAdjIntLease> adjIntLeaseList)  throws BusinessException;

}
