package com.imfbp.rz.service.method.impl;

import java.util.List;

import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.service.method.IRentMethodCal;
import com.imfbp.rz.domain.exception.BusinessException;

public class CalMethodImpl implements IRentMethodCal {

	@Override
	public List<RzPricecalLease> calLease(RzPricecal rzPricecal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RzPricecalCf> calCf(RzPricecal rzPricecal) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void checkCalFactor(RzPricecal rzPricecal) throws BusinessException {

	}

	@Override
	public List<RzPricecalLease> calLeaseChange(RzPricecal rzPricecal) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
