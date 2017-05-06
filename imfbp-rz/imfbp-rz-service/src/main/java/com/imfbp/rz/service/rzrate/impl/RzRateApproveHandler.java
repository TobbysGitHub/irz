package com.imfbp.rz.service.rzrate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.dao.rzrate.RzRateDao;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.web.result.Result;

public class RzRateApproveHandler extends ApproveHandler {
	@Autowired
	private RzRateDao rzRateDao; 
	@Override
	public void afterApprovePass(SuperHeadBean headBean) throws Exception {
		RzRate rzRate = (RzRate)headBean;
		//获取传入利率的生效日期
		String startdate = rzRate.getStartdate();
		//查询审批通过的全部利率
		RzRateQuery rzRateQuery = new RzRateQuery();
		//审批状态为通过
		rzRateQuery.setApprovestatus(3);
		//币种
		rzRateQuery.setPkCurrency(rzRate.getPkCurrency());
		//倒序
		rzRateQuery.setSort("startdate");
		rzRateQuery.setOrder("desc");
		List<RzRate> rzRateList = rzRateDao.getRzRateAll(rzRateQuery);
		if(ToolUtils.isNotEmptyCollection(rzRateList)){
			//获取生效日期最晚的利率
			
			RzRate lastRzRate = rzRateList.get(0);
			String lastStartdate = lastRzRate.getStartdate();
			//获取生效日期最早的利率
			RzRate firstRzRate = rzRateList.get(rzRateList.size() - 1);
			String firstStartdate = firstRzRate.getStartdate();
			//在最晚的生效日期后生效，则要修改最晚利率的失效日期，失效日期为rzRate生效日期的前一天
			if(lastStartdate.compareTo(startdate) < 0){
				lastRzRate.setEnddate(DateUtil.getPreviousDay(startdate));
				rzRateDao.updateRzRateById(lastRzRate);
			}
			//在最早的生效日期前生效，则要添加利率的失效日期，失效日期为最早生效日期的前一天
			if(firstStartdate.compareTo(startdate) > 0){
				rzRate.setEnddate(DateUtil.getPreviousDay(firstStartdate));
				rzRateDao.updateRzRateById(rzRate);
			}
		}
	}

	@Override
	public void withDrawPassBill(SuperHeadBean headBean) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
