package com.imfbp.rz.service.metadata.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.smallconditionItem.domain.SmallConditionItem;
import com.ifbp.boss.rpc.smallconditionItem.domain.query.SmallConditionItemQuery;
import com.ifbp.boss.rpc.smallconditionItem.service.BossConditionItemRpcService;
import com.imfbp.rz.service.metadata.MetaDataService;

@Component("metaDataService")
public class MetaDataServiceImpl implements MetaDataService {

	@Autowired
	private BossConditionItemRpcService conditionItemRpcClient;

	public void setConditionItemRpcClient(
			BossConditionItemRpcService conditionItemRpcClient) {
		this.conditionItemRpcClient = conditionItemRpcClient;
	}

	public List<SmallConditionItem> getBossConditionItemByCondition(
			SmallConditionItemQuery arg0) throws Exception {

		return conditionItemRpcClient.getBossConditionItemByCondition(arg0);
	}

	public List<SmallConditionItem> getBossConditionItemByIds(
			SmallConditionItemQuery arg0) throws Exception {

		return conditionItemRpcClient.getBossConditionItemByIds(arg0);
	}

	public SmallConditionItem getBossConditionItemById(
			SmallConditionItemQuery arg0) throws Exception {
		return conditionItemRpcClient.getBossConditionItemById(arg0);
	}
}
