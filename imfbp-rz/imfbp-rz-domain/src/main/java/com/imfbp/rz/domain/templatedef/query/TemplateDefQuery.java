package com.imfbp.rz.domain.templatedef.query;

import java.io.Serializable;
import java.util.List;

import com.platform.common.utils.query.BaseQuery;

public class TemplateDefQuery extends BaseQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	// 主键
	private String id;
	// 模板编码
	private String templateCode;
	// 模板名称
	private String templateName;
	// 模板文件id
	private String templateFileId;
	// 模板类型
	private String templateType;
	// 模板状态 0：已停用 1：已启用 2：已分配
	private Integer templateStatus;
	// 模板版本号
	private String templateVersion;
	// 备注
	private String note;
	// 租户id
	private String tenantId;
	// 制单日期
	private String createdate;
	// 制单人
	private String creator;
	// 制单时间
	private String createdtime;
	// 操作者
	private String operator;
	// 操作时间
	private String operationtime;
	// 修改人
	private String modifier;
	// 修改时间
	private String modifiedtime;
	// 时间戳
	private String ts;
	// 删除标识
	private Integer dr;
	// 批量id集合
	private List<String> batchIds;
	// 节点编码
	private String nodeCode;
	// 是否查询未分配的模板
	private String isDistriTemp;

	public String getIsDistriTemp() {
		return isDistriTemp;
	}

	public void setIsDistriTemp(String isDistriTemp) {
		this.isDistriTemp = isDistriTemp;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public List<String> getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(List<String> batchIds) {
		this.batchIds = batchIds;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateFileId(String templateFileId) {
		this.templateFileId = templateFileId;
	}

	public String getTemplateFileId() {
		return templateFileId;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public Integer getTemplateStatus() {
		return templateStatus;
	}

	public void setTemplateStatus(Integer templateStatus) {
		this.templateStatus = templateStatus;
	}

	public void setTemplateVersion(String templateVersion) {
		this.templateVersion = templateVersion;
	}

	public String getTemplateVersion() {
		return templateVersion;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}

	public String getOperationtime() {
		return operationtime;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public String getModifiedtime() {
		return modifiedtime;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getTs() {
		return ts;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public Integer getDr() {
		return dr;
	}
}
