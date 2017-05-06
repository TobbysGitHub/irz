package com.imfbp.rz.service.billno.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.rpcruleelement.domain.RpcRuleElement;
import com.ifbp.boss.rpc.rpcruleelement.service.BossRuleRpcService;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.billnumber.BillNumber;
import com.platform.common.utils.billnumber.BillRuleElement;

@Component("billnoService")
public class BillnoServiceimpl implements BillnoService {

	@Autowired
	private BossRuleRpcService bossRuleRpcClient;

	@Autowired
	private BillNumber billNumber;

	/**
	 * 根据业务规则编码获取借据号
	 */
	@Override
	public String getBillno(String nodeCode) throws Exception {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(nodeCode)) {
			nodeCode = IRZConsts.PUBBILLNO;
		}
		String billno = nodeCode;
		List<RpcRuleElement> rpcRuleElementLists = bossRuleRpcClient.getRuleElementsByCode(nodeCode);
		if (rpcRuleElementLists == null || rpcRuleElementLists.size() == 0) {
			rpcRuleElementLists = bossRuleRpcClient.getRuleElementsByCode(IRZConsts.PUBBILLNO);
		}
		if (rpcRuleElementLists != null && rpcRuleElementLists.size() > 0) {
			List<BillRuleElement> ruleElements = new ArrayList<BillRuleElement>(rpcRuleElementLists.size());
			BillRuleElement billRuleElement = null;
			for (int i = 0; i < rpcRuleElementLists.size(); i++) {
				billRuleElement = new BillRuleElement();
				billRuleElement.setElemLen(rpcRuleElementLists.get(i).getElemLen());
				billRuleElement.setElemOrder(rpcRuleElementLists.get(i).getElemOrder());
				billRuleElement.setElemValue(rpcRuleElementLists.get(i).getElemValue());
				billRuleElement.setId(rpcRuleElementLists.get(i).getId());
				billRuleElement.setListElemType(rpcRuleElementLists.get(i).getListElemType());
				billRuleElement.setListReferType(rpcRuleElementLists.get(i).getListReferType());
				billRuleElement.setRuleCode(rpcRuleElementLists.get(i).getRuleCode());
				billRuleElement.setRuleFormat(rpcRuleElementLists.get(i).getRuleFormat());
				ruleElements.add(billRuleElement);
			}
			billno = billNumber.getBillNumber(ruleElements);
			if (StringUtil.isEmpty(billno)) {
				billno = nodeCode;
			}

		}
		return billno;
	}

	public BossRuleRpcService getBossRuleRpcClient() {
		return bossRuleRpcClient;
	}

	public void setBossRuleRpcClient(BossRuleRpcService bossRuleRpcClient) {
		this.bossRuleRpcClient = bossRuleRpcClient;
	}

	public BillNumber getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(BillNumber billNumber) {
		this.billNumber = billNumber;
	}

}
