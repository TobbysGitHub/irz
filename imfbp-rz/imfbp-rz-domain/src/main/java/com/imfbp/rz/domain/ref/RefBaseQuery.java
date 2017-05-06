package com.imfbp.rz.domain.ref;

import java.io.Serializable;
import java.util.List;

import com.platform.common.utils.query.BaseQuery;

public class RefBaseQuery extends BaseQuery implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8716741264308047832L;

	/**
	 * 参照类型key
	 */
	private String typeKey;
	/**
	 * 是否是获取翻译数据
	 */
	private boolean isTranslate;
	/**
	 * 数据记录id集合
	 */
	private List<String> batchIds;

	/**
	 * 当前登录人机构id
	 */
	private String orgId;
	
	/**
	 * 当前登录人部门id
	 */
	private String deptId;
	/**
	 * 当前登录人租户id
	 */
	private String tenantId;

	/**
	 * 档案类型编码
	 */
	private String dicItemCode;

	/**
	 * 模糊查询字段值，该值一般为界面显示字段的值
	 */
	private String likeQueryItemValue;
	
	private String queryCondition;

	public String getLikeQueryItemValue() {
		return likeQueryItemValue;
	}

	public void setLikeQueryItemValue(String likeQueryItemValue) {
		this.likeQueryItemValue = likeQueryItemValue;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDicItemCode() {
		return dicItemCode;
	}

	public void setDicItemCode(String dicItemCode) {
		this.dicItemCode = dicItemCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public boolean getIsTranslate() {
		return isTranslate;
	}

	public void setIsTranslate(boolean isTranslate) {
		this.isTranslate = isTranslate;
	}

	public List<String> getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(List<String> batchIds) {
		this.batchIds = batchIds;
	}

	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}
}
