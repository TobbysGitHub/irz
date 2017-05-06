package com.imfbp.rz.service.method;

import java.util.List;

import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;


public interface IRentMethodCal {
	
	/****
	 * 
	 * 租金计划计算
	 * 
	 * @param rzPmtPlan
	 * @return
	 */
	public List<RzPricecalLease> calLease(RzPricecal rzPricecal) throws BusinessException;
	
	
	/****
	 * 
	 * 租金计划计算变更
	 * 
	 * @param rzPmtPlan
	 * @return
	 */
	public List<RzPricecalLease> calLeaseChange(RzPricecal rzPricecal) throws BusinessException;

	/*****
	 * 
	 * 现金流计划计算
	 * 
	 * @param rzPricecal
	 * @return
	 * @throws BusinessException
	 */
	public List<RzPricecalCf> calCf(RzPricecal rzPricecal) throws BusinessException;
	
	
//	public Double calIRR(List<Double> netCfInList) throws BusinessException;
	
	

}
