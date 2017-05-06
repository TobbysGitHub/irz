package com.imfbp.rz.service.rzdefinterest.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.service.rzdefinterest.RzDefInterestService;
import com.imfbp.rz.service.rzdefinterestplan.RzDefInterestPlanService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplancf.RzPmtPlanCfService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.util.ToolUtils;

public class RzDefInterestApproveHandler extends ApproveHandler{
	@Autowired
	private RzDefInterestService rzDefInterestService;
	@Autowired
    private RzPmtPlanService rzPmtPlanService;
	@Autowired
	private RzPrjcontrService rzPrjcontrService;
	@Autowired
	private RzPmtPlanLeaseService  rzPmtPlanLeaseService;
	@Autowired
	private RzDefInterestPlanService rzDefInterestPlanService;
	@Autowired
	private RzPmtPlanCfService rzPmtPlanCfService;
	@Override
	public void afterApprovePass(SuperHeadBean headBean) throws Exception {
		// TODO Auto-generated method stub
		RzDefInterest rzDefInterest=(RzDefInterest)headBean;
		if(rzDefInterest != null && StringUtils.isNotEmpty(rzDefInterest.getPkPrjcontr())){
			RzDefInterestPlanQuery rzDefInterestPlanQuery=new RzDefInterestPlanQuery();
			rzDefInterestPlanQuery.setPkDefInterest(rzDefInterest.getPkDefInterest());
			List<RzDefInterestPlan>rzDefInterestPlanList=rzDefInterestPlanService.getRzDefInterestPlanAll(rzDefInterestPlanQuery);
			RzPmtPlanQuery rzPmtPlanQuery=new RzPmtPlanQuery();
			rzPmtPlanQuery.setPkPrjcontr(rzDefInterest.getPkPrjcontr());;
			RzPmtPlan plan=rzPmtPlanService.getRzPmtPlanById(rzPmtPlanQuery);
			if(plan != null){
				RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery=new RzPmtPlanLeaseQuery();
				rzPmtPlanLeaseQuery.setPkPmtPlan(plan.getPkPmtPlan());
				List<RzPmtPlanLease>rzPmtPlanLeaseList=rzPmtPlanLeaseService.getRzPmtPlanLeaseAll(rzPmtPlanLeaseQuery);
				RzPmtPlanCfQuery rzPmtPlanCfQuery=new RzPmtPlanCfQuery();
				rzPmtPlanCfQuery.setPkPmtPlan(plan.getPkPmtPlan());
				List<RzPmtPlanCf>rzPmtPlanCfList=rzPmtPlanCfService.getRzPmtPlanCfAll(rzPmtPlanCfQuery);
				if(ToolUtils.isNotEmptyCollection(rzPmtPlanLeaseList)){
					alterLease(rzDefInterestPlanList,rzPmtPlanLeaseList,rzPmtPlanCfList);
				}
			}
		}
		
	}
     //根据逾期罚息子表更新收付计划表子表
	public void alterLease(List<RzDefInterestPlan>rzDefInterestPlanList,List<RzPmtPlanLease>rzPmtPlanLeaseList,List<RzPmtPlanCf>rzPmtPlanCfList){
		if(ToolUtils.isEmptyCollection(rzPmtPlanLeaseList) || ToolUtils.isEmptyCollection(rzDefInterestPlanList) ||ToolUtils.isEmptyCollection(rzPmtPlanCfList))
			return;
		Map<Integer,Double>map=new HashMap<Integer,Double>();
		for(RzDefInterestPlan plan:rzDefInterestPlanList) map.put(plan.getSeqno(), plan.getDefIntAmt());
		for(RzPmtPlanLease lease:rzPmtPlanLeaseList){
			if(lease != null){
			Double defIntAmt =map.get(lease.getSeqNo());
			lease.setRptDefInt(defIntAmt);
			lease.setActDefInt(defIntAmt);
			lease.setIsCheck("Y");
			}
		}
		for(RzPmtPlanCf cf:rzPmtPlanCfList){
			if(cf != null){
			Double defIntAmt =map.get(cf.getSeqNo());
			cf.setActDefInt(defIntAmt);
			cf.setReceivablDefInt(defIntAmt);
			cf.setIsCheck("Y");
			}
		}
		rzPmtPlanLeaseService.updateRzPmtPlanLeaseByBatchId(rzPmtPlanLeaseList);
		rzPmtPlanCfService.updateRzPmtPlanCfByBatchId(rzPmtPlanCfList);
		for(RzPmtPlanLease lease:rzPmtPlanLeaseList){
			if(lease != null){
				rzPmtPlanLeaseService.insertToTally(lease);
			}
		}
		for(RzPmtPlanCf cf:rzPmtPlanCfList){
			if(cf != null){
				rzPmtPlanCfService.insertToTally(cf);
			}
		}
	}
	
	@Override
	public void withDrawPassBill(SuperHeadBean headBean) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
