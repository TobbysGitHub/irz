package com.imfbp.rz.service.commons;

import java.util.List;

import com.ifbp.boss.rpc.smallorg.domain.SmallOrg;

public interface OrgUtil {
	
	// 通过租户id 获取当前 租户所有组织机构
	public List<SmallOrg> getAllSmallOrg(String tenantId);
	
	//得到某组织机构对应的所有父级组织
	public List<SmallOrg> getAllParentSmallOrg(String tenantId,String orgId);
	
	//得到所有子级
	public List<SmallOrg> getAllChildSmallOrg(String tenantId,String orgId);
	
	//通过id 得到组织机构
	public List<SmallOrg> getSmallOrgById(String orgId);
	
	//通过id 得到最上级树根组织机构
	public SmallOrg getTopSmallOrgById(String tenantId,String orgId);
	
}
