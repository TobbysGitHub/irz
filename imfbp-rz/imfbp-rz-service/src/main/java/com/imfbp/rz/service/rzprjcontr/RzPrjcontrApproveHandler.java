package com.imfbp.rz.service.rzprjcontr;

import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.service.rzprjcontrpub.RzPrjcontrPubService;

/**
 * 合同审批后操作
 * @author zhengjm5
 * @date 2016/12/23 15:41
 */
public class RzPrjcontrApproveHandler extends ApproveHandler{

    @Autowired
    private RzPrjcontrPubService rzPrjcontrPubService;

    @Override
    public void afterApprovePass(SuperHeadBean headBean) throws Exception {
        RzPrjcontr rzPrjcontr = (RzPrjcontr)headBean;
        //推资产管理
        rzPrjcontrPubService.createRzPrjcontrEqptmngr(rzPrjcontr);
        //推收付计划表
        rzPrjcontrPubService.createRzPmtPlan(rzPrjcontr);
        //合同台账
        rzPrjcontrPubService.createRzPrjcontrTally(rzPrjcontr);
    }

    @Override
    public void withDrawPassBill(SuperHeadBean headBean) throws Exception {

    }
}
