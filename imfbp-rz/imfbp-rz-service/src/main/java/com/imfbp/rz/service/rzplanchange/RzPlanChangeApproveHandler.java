package com.imfbp.rz.service.rzplanchange;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.dao.rzplanchangeleasenew.RzPlanChangeLeaseNewDao;
import com.imfbp.rz.dao.rzpmtplancf.RzPmtPlanCfDao;
import com.imfbp.rz.dao.rzpmtplanlease.RzPmtPlanLeaseDao;
import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzplanchange.RzPlanChange;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.service.rzprjcontrpub.RzPrjcontrPubService;

public class RzPlanChangeApproveHandler extends ApproveHandler {
	
	@Autowired
    private RzPrjcontrPubService rzPrjcontrPubService;
	@Autowired
    private RzPrjcontrService rzPrjcontrService;
	@Autowired
	private RzPlanChangeLeaseNewDao rzPlanChangeLeaseNewDao;
	@Autowired
	private RzPlanChangeService rzPlanChangeService;
	@Autowired
	private RzPmtPlanLeaseDao rzPmtPlanLeaseDao;
	@Autowired
	private RzContrTallyService rzContrTallyService;
	@Autowired
	private RzPmtPlanCfDao rzPmtPlanCfDao;
    @Override
    public void afterApprovePass(SuperHeadBean headBean) throws BusinessException {
        RzPlanChange rzPlanChange = (RzPlanChange)headBean;
        RzPrjcontrQuery rzPrjcontrQuery=new RzPrjcontrQuery();
        rzPrjcontrQuery.setPkPrjcontr(rzPlanChange.getPkPrjcontr());
        RzPrjcontr rzPrjcontrOld= rzPrjcontrService.getRzPrjcontrAll(rzPrjcontrQuery).get(0);
        rzPrjcontrOld.setIsNew("N");
        rzPrjcontrService.updateRzPrjcontrById(rzPrjcontrOld);
        RzPrjcontr rzPrjcontrNew=rzPrjcontrPubService.createRzprjcontrByOld(rzPrjcontrOld);
        //尚未插入首付计划表
        List<RzPmtPlanLease> rzPmtPlanLeaseList= rzPlanChangeService.getPmtPlanLease(rzPlanChange);
        if(rzPmtPlanLeaseList!=null&&rzPmtPlanLeaseList.size()>0){
           rzPmtPlanLeaseDao.insertBatchRzPmtPlanLease(rzPmtPlanLeaseList);//插入租金计划表
           List<RzPmtPlanCf>  rzPmtPlanCfList= rzPlanChangeService.getPmtPlanCf(rzPmtPlanLeaseList, rzPlanChange);
           rzPmtPlanCfDao.insertBatchRzPmtPlanCf(rzPmtPlanCfList);//插入现金流量表
        }else{
        	throw new BusinessException("没有对应的现金流量表");
        }
        //插入合同台账表
        RzContrTally rzContrTally=new RzContrTally();
        try {
			BeanUtils.copyProperties(rzContrTally,rzPlanChange);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        if(rzPlanChange.getChangeType()==2){
        	rzContrTally.setIsReduce("Y");
        	rzContrTally.setIsExtend("N");
        }else if(rzPlanChange.getChangeType()==3){
        	rzContrTally.setIsExtend("Y");
        	rzContrTally.setIsReduce("N");
        }
        rzContrTallyService.addRzContrTally(rzContrTally);
    }

    @Override
    public void withDrawPassBill(SuperHeadBean headBean) throws Exception {

    }
}
