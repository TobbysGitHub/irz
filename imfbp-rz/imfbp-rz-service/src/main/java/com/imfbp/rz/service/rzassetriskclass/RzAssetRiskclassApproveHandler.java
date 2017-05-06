package com.imfbp.rz.service.rzassetriskclass;

import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzassetriskclass.RzAssetRiskclass;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;

public class RzAssetRiskclassApproveHandler extends ApproveHandler {
	@Autowired
	private RzPrjcontrService rzPrjcontrService;
	@Autowired
	private RzContrTallyService rzContrTallyService;

	@Override
	public void afterApprovePass(SuperHeadBean headBean) throws Exception {
		RzAssetRiskclass rzAssetRiskclass = (RzAssetRiskclass)headBean;
		RzContrTally rzContrTally=new RzContrTally();
		rzContrTally.setPkPrjcontr(rzAssetRiskclass.getPkPrjcontr());
		RzPrjcontrQuery rzPrjcontrQuery=new RzPrjcontrQuery();
		rzPrjcontrQuery.setPkPrjcontr(rzAssetRiskclass.getPkPrjcontr());
		if(rzPrjcontrService.getRzPrjcontrAll(rzPrjcontrQuery).size()>0){
			String contrCode= rzPrjcontrService.getRzPrjcontrAll(rzPrjcontrQuery).get(0).getContrCode();
			rzContrTally.setContrCode(contrCode);
			rzContrTally.setRiskType(Integer.parseInt(rzAssetRiskclass.getCurRiskclass()));
			rzContrTallyService.addRzContrTally(rzContrTally);
		}
		
	}

	@Override
	public void withDrawPassBill(SuperHeadBean headBean) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
