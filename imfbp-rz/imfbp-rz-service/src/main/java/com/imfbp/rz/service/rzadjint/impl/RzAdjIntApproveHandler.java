package com.imfbp.rz.service.rzadjint.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjintleasenew.RzAdjIntLeaseNew;
import com.imfbp.rz.domain.rzadjintleasenew.query.RzAdjIntLeaseNewQuery;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.service.rzadjintleasenew.RzAdjIntLeaseNewService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;

public class RzAdjIntApproveHandler extends ApproveHandler {
	@Autowired
	private RzPmtPlanService rzPmtPlanService;
	
	@Autowired
	private RzPmtPlanLeaseService rzPmtPlanLeaseService;
	@Autowired
	private RzAdjIntLeaseNewService rzAdjIntLeaseNewService;
	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;
	
	@Override
	public void afterApprovePass(SuperHeadBean headBean) throws Exception {
		RzAdjInt bean = (RzAdjInt) headBean;
		String pk = bean.getPkAdjInt();
		RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery = new RzAdjIntLeaseNewQuery();
		rzAdjIntLeaseNewQuery.setPkAdjInt(pk);
		List<RzAdjIntLeaseNew> rzAdjIntLeasenewList = rzAdjIntLeaseNewService.getRzAdjIntLeaseNewAll(rzAdjIntLeaseNewQuery);
		if (ToolUtils.isNotEmptyCollection(rzAdjIntLeasenewList)){
			RzPmtPlanQuery rzPmtPlanQuery = new RzPmtPlanQuery();
			rzPmtPlanQuery.setPkPrjcontr(bean.getPkPrjcontr());
			List<RzPmtPlan> list = rzPmtPlanService.getRzPmtPlanAll(rzPmtPlanQuery);
			if(ToolUtils.isNotEmptyCollection(list)){
				String pkRzPmtPlan = list.get(0).getPkPmtPlan();
				RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery = new RzPmtPlanLeaseQuery();
				rzPmtPlanLeaseQuery.setPkPmtPlan(pkRzPmtPlan);
				List<RzPmtPlanLease> leaseList = rzPmtPlanLeaseService.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(rzPmtPlanLeaseQuery);
				double ver = 0.0;
				if(ToolUtils.isNotEmptyCollection(leaseList)){
					ver = leaseList.get(0).getActAmt();
				}
				List<RzPmtPlanLease> newRzPmtPlanLease = new ArrayList<RzPmtPlanLease>();
				for (RzAdjIntLeaseNew rzAdjIntLeasenew : rzAdjIntLeasenewList){
					RzPmtPlanLease rzPmtPlanLease = new RzPmtPlanLease();
					BeanUtils.copyProperties(rzPmtPlanLease,rzAdjIntLeasenew);
					rzPmtPlanLease.setPkPmtPlan(pkRzPmtPlan);
					rzPmtPlanLease.setVer(ver+0.1);
					rzPmtPlanLease.setPkPmtPlanLease(primaryKeyIdWorker.getPrimaryKey());
					newRzPmtPlanLease.add(rzPmtPlanLease);
				}
				rzPmtPlanLeaseService.insertBatchRzPmtPlanLease(newRzPmtPlanLease);
			}
		}
	}

	@Override
	public void withDrawPassBill(SuperHeadBean headBean) throws Exception {
		// TODO Auto-generated method stub

	}

}
