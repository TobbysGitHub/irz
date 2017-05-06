package com.imfbp.rz.service.rzprjapply.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.dao.rzpricecal.RzPricecalDao;
import com.imfbp.rz.dao.rzpricecalcf.RzPricecalCfDao;
import com.imfbp.rz.dao.rzpricecaleqpt.RzPricecalEqptDao;
import com.imfbp.rz.dao.rzpricecallease.RzPricecalLeaseDao;
import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import com.imfbp.rz.dao.rzprjapplyeqpt.RzPrjapplyEqptDao;
import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;
import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.method.RentMethodCalService;
import com.imfbp.rz.service.rzpricecalcf.RzPricecalCfService;
import com.imfbp.rz.service.rzprjreview.RzPrjreviewService;
import com.imfbp.rz.service.rzprjstate.RzPrjStateService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;

public class RzPrjapplyApproveHandler extends ApproveHandler {
	@Autowired
	private RzPrjreviewService rzPrjreviewService;
	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;
	@Autowired
	private RzPricecalDao rzPricecalDao;
	@Autowired
	private RzPricecalEqptDao rzPricecalEqptDao;
	@Autowired
	private RzPrjapplyEqptDao rzPrjapplyEqptDao;
	@Autowired
	private BillnoService billnoService;
	@Autowired
	private RzPrjapplyDao rzPrjapplyDao;
	@Autowired
	private RzPrjStateService rzPrjStateService;
	@Autowired
	private RentMethodCalService rentMethodCalService;
	@Autowired
	private RzPricecalCfDao rzPricecalCfDao;
	@Autowired
	private RzPricecalLeaseDao rzPricecalLeaseDao;
	@Autowired
	private RzPricecalCfService rzPricecalCfService;
	@Override
	public void afterApprovePass(SuperHeadBean headBean) throws Exception {
		RzPrjapply bean = (RzPrjapply) headBean;
		// 业务类型不对
		RzPrjapplyQuery rzPrjapplyQuery = new RzPrjapplyQuery();
		rzPrjapplyQuery.setPkPrjapply(bean.getPkPrjapply());
		RzPrjapply rzPrjapply = rzPrjapplyDao.getRzPrjapplyById(rzPrjapplyQuery);
		// 情景1：不设置报价单，直接新增立项
		if (StringUtil.isEmpty(rzPrjapply.getPkPricecal())) {
			RzPricecal rzPricecal = new RzPricecal();
			BeanUtils.copyProperties(rzPricecal, rzPrjapply);
			rzPricecal.setFlowinstanceid(null);
			rzPricecal.setPlanleasedate(rzPrjapply.getPlanrentdate());
			rzPricecal.setBilldate(DateUtil.getCurDateStr());
			rzPricecal.setApprovedate(null);
			rzPricecal.setApprovenote(null);
			rzPricecal.setApprovestatus(0);
			rzPricecal.setApproveid(null);
			rzPricecal.setBillmaker(rzPrjapply.getApproveid());
			rzPricecal.setTs(DateUtil.getTs());
			rzPricecal.setDef1(null);
			rzPricecal.setDef2(null);
			rzPricecal.setDef3(null);
			rzPricecal.setDef4(null);
			rzPricecal.setDef5(null);
			rzPricecal.setProjectstate(1);
			rzPricecal.setDr(0);
			String billno;
			try {
				billno = billnoService.getBillno(INodeConsts.RZ_PRICE_NO);
				rzPricecal.setPriceno(billno);
				if (StringUtils.isEmpty(rzPricecal.getPricename()))
					rzPricecal.setPricename(billno);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String pkRzPricecal = primaryKeyUtil.getPrimaryKey();
			rzPricecal.setPkPricecal(pkRzPricecal);
			rzPricecalDao.insertRzPricecal(rzPricecal);
			RzPrjapplyEqptQuery rzPrjapplyEqptQuery = new RzPrjapplyEqptQuery();
			rzPrjapplyEqptQuery.setPkPrjapply(rzPrjapply.getPkPrjapply());
			// 拷贝设备数据
			List<RzPrjapplyEqpt> list = rzPrjapplyEqptDao.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
			List<RzPricecalEqpt> pricecalList = new ArrayList<RzPricecalEqpt>();
			for (RzPrjapplyEqpt eqpt : list) {
				RzPricecalEqpt rzPricecalEqpt = new RzPricecalEqpt();
				BeanUtils.copyProperties(rzPricecalEqpt, eqpt);
				rzPricecalEqpt.setPkPricecal(pkRzPricecal);
				rzPricecalEqpt.setPkPricecalEqpt(primaryKeyIdWorker.getPrimaryKey());
				pricecalList.add(rzPricecalEqpt);
			}
			// 插入子表
			if (ToolUtils.isNotEmptyCollection(pricecalList)) {
				rzPricecalEqptDao.insertBatchRzPricecalEqpt(pricecalList);
			}
			// 修改立项数据
			rzPrjapply.setPkPricecal(pkRzPricecal);
			getPlanByApply(rzPricecal);
			rzPrjapplyDao.updateRzPrjapplyById(rzPrjapply);
			headBean.setAttribute("pkPricecal",pkRzPricecal);
		}
		// 向下推单
		RzPrjreview rzPrjreview = new RzPrjreview();
		BeanUtils.copyProperties(rzPrjreview, rzPrjapply);
		rzPrjreview.setApplydate(DateUtil.getCurDateStr());
		rzPrjreview.setApprovedate(null);
		rzPrjreview.setApprovenote(null);
		rzPrjreview.setApprovestatus(0);
		rzPrjreview.setApproveid(null);
		rzPrjreview.setBillmaker(rzPrjapply.getApproveid());
		rzPrjreview.setTs(DateUtil.getCurDateStr());
		rzPrjreview.setDef1(null);
		rzPrjreview.setDef2(null);
		rzPrjreview.setDef3(null);
		rzPrjreview.setDef4(null);
		rzPrjreview.setDef5(null);
		rzPrjreview.setFlowinstanceid(null);
		RzPricecalCfQuery rzPricecalCfQuery = new RzPricecalCfQuery();
		rzPricecalCfQuery.setPkPricecal(rzPrjapply.getPkPricecal());
		List<RzPricecalCf> rzPricecalCfList = rzPricecalCfService.getRzPricecalCfAll(rzPricecalCfQuery);
		if(rzPricecalCfList!=null&&rzPricecalCfList.size()>0){
			Double receivableAmt=0.0;
			Double receivableInt=0.0;
			Double receivableRent=0.0;
			Double receivableFee=0.0;
			Double payableSc=0.0;
			for(int i=0;i<rzPricecalCfList.size();i++){
				if(rzPricecalCfList.get(i).getReceivableAmt()!=null){
					receivableAmt+=rzPricecalCfList.get(i).getReceivableAmt();
				}
				if(rzPricecalCfList.get(i).getReceivableInt()!=null){
					receivableInt+=rzPricecalCfList.get(i).getReceivableInt();
				}
				if(rzPricecalCfList.get(i).getReceivableRent()!=null){
					receivableRent+=rzPricecalCfList.get(i).getReceivableRent();
				}
				if(rzPricecalCfList.get(i).getReceivableFee()!=null){
					receivableFee+=rzPricecalCfList.get(i).getReceivableFee();
				}
				if(rzPricecalCfList.get(i).getPayableSc()!=null){
					payableSc+=rzPricecalCfList.get(i).getPayableSc();
				}
			}
		
			rzPrjreview.setFeeIntotalAmt(receivableFee);
			rzPrjreview.setScOuttotalAmt(payableSc);
			rzPrjreview.setRentTotalAmt(receivableRent);
			rzPrjreview.setIntTotalAmt(receivableInt);
			rzPrjreview.setPrinTotalAmt(receivableAmt); 
		}
		rzPrjreviewService.insertRzPrjreview(rzPrjreview);

		RzPrjState rzPrjState = new RzPrjState();
		BeanUtils.copyProperties(rzPrjState, bean);
		rzPrjState.setTs(DateUtil.getTs());
		rzPrjState.setPkUserOper(bean.getApproveid());
		rzPrjState.setPrjState(2);
		rzPrjStateService.insertOrUpdatePrjState(rzPrjState);
	}

	@Override
	public void withDrawPassBill(SuperHeadBean headBean) throws Exception {
		// TODO Auto-generated method stub

	}

	private void getPlanByApply(RzPricecal rzPricecal) {
		try {
			List<Double> irrs = new ArrayList<Double>();
			List<RzPricecalLease> rzPricecalLeaseList = rentMethodCalService.calLease(rzPricecal);
			if (ToolUtils.isNotEmptyCollection(rzPricecalLeaseList)) {
				for (RzPricecalLease ls : rzPricecalLeaseList) {
					String rzPricecalLeasePk = primaryKeyUtil.getPrimaryKey();
					ls.setPkPricecalLease(rzPricecalLeasePk);
					irrs.add(ls.getNetCfIn());
					if (StringUtils.isNotEmpty(rzPricecal.getPkPricecal()))
						ls.setPkPricecal(rzPricecal.getPkPricecal());
				}
			}
			rzPricecalLeaseDao.insertBatchRzPricecalLease(rzPricecalLeaseList);
			List<RzPricecalCf> rzPricecalCfList = rentMethodCalService.calCf(rzPricecal);
			if (ToolUtils.isNotEmptyCollection(rzPricecalCfList)) {
				for (RzPricecalCf cf : rzPricecalCfList) {
					String cfpk = primaryKeyUtil.getPrimaryKey();
					cf.setPkPricecalCf(cfpk);
					if (StringUtils.isNotEmpty(rzPricecal.getPkPricecal()))
						cf.setPkPricecal(rzPricecal.getPkPricecal());
				}
				rzPricecalCfDao.insertBatchRzPricecalCf(rzPricecalCfList);
			}
			Double irr = DoubleUtils.getDoubleNullAsZero(rentMethodCalService.calIRR(irrs, rzPricecal));
			rzPricecal.setIrr(irr);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
