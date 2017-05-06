package com.imfbp.rz.service.service.superbean.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.superbean.SuperBeanDao;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.service.service.superbean.SuperBeanService;
import com.platform.common.web.result.Result;

@Component("superBeanService")
public class SuperBeanServiceImpl implements SuperBeanService {

	private SuperBeanDao superBeanDao;
	
	@Override
	public Result updateSuperHeaderBean(SuperHeadBean headbean) {
		
		Result result = new Result();
		try{
			superBeanDao.updateHeadBean(headbean);
			result.setSuccess(true);
			result.addDefaultModel("datas", headbean);
		} catch (Exception ex){
			Logger.getLogger(getClass()).error(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setErrorMessage("审批过程回写单据失败");
		}
		
		return result;
	}

	public void setSuperBeanDao(SuperBeanDao superBeanDao) {
		this.superBeanDao = superBeanDao;
	}
	
}
