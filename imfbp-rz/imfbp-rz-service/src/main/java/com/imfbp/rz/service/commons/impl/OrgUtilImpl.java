package com.imfbp.rz.service.commons.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.smallorg.domain.SmallOrg;
import com.ifbp.boss.rpc.smallorg.domain.query.SmallOrgQuery;
import com.ifbp.boss.rpc.smallorg.service.BossOrgRpcService;
import com.imfbp.rz.service.commons.OrgUtil;
import com.imfbp.rz.util.ToolUtils;

@Component("orgUtil")
public class OrgUtilImpl implements OrgUtil {

	@Autowired
	private BossOrgRpcService bossOrgRpcService;
	
	@Override
	public List<SmallOrg> getAllSmallOrg(String tenantId) {
		
		
		// 默认查询当前登录人只能查询当前机构及下属机构
		SmallOrgQuery smallOrgQuery = new SmallOrgQuery();
		String orgId = "";
		
//		if (refBaseQuery != null && refBaseQuery.getOrgId() != null) {
//			// 当前登录人只能查询当前机构及下属机构
//			orgId = refBaseQuery.getOrgId();
//		}
		// 000001
		smallOrgQuery.setTenantId(tenantId);
		// 取出所有机构，单独处理
		List<SmallOrg> orgLists = bossOrgRpcService
				.getBossOrgByCondition(smallOrgQuery);
		return orgLists;

	}

	@Override
	public List<SmallOrg> getAllParentSmallOrg(String tenantId, String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmallOrg> getAllChildSmallOrg(String tenantId, String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmallOrg> getSmallOrgById(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmallOrg getTopSmallOrgById(String tenantId,String orgId) {
		SmallOrgQuery smallOrgQuery = new SmallOrgQuery();
		smallOrgQuery.setTenantId(tenantId);
		// 取出所有机构，单独处理
		List<SmallOrg> orgLists = bossOrgRpcService
				.getBossOrgByCondition(smallOrgQuery);
		if (ToolUtils.isNotEmptyCollection(orgLists)){
			Map<String,SmallOrg> map = new HashMap<String,SmallOrg>();
			for(SmallOrg org :orgLists){
				map.put(org.getId(), org);
			}
			if(!map.isEmpty()){
				String parentOrg = orgId;
				while(map.containsKey(parentOrg) && !map.get(parentOrg).getOrgPid().equals(0)){
					orgId = parentOrg;
					parentOrg=map.get(parentOrg).getOrgPid();
				}
				return map.get(orgId);
			}
		}
		return null;
	}
	
	

}
