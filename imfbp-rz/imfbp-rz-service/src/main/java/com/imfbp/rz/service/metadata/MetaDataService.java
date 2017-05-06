package com.imfbp.rz.service.metadata;

import java.util.List;

import com.ifbp.boss.rpc.smallconditionItem.domain.SmallConditionItem;
import com.ifbp.boss.rpc.smallconditionItem.domain.query.SmallConditionItemQuery;

public interface MetaDataService {

	public List<SmallConditionItem> getBossConditionItemByCondition(
			SmallConditionItemQuery arg0) throws Exception;

	public List<SmallConditionItem> getBossConditionItemByIds(
			SmallConditionItemQuery arg0) throws Exception;

	public SmallConditionItem getBossConditionItemById(
			SmallConditionItemQuery arg0) throws Exception;
}
