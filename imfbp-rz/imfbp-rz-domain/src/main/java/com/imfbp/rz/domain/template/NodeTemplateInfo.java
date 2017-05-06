package com.imfbp.rz.domain.template;

import java.io.Serializable;

/**
 * @Title : 节点分配的模板信息实体
 * @Description : 节点分配的模板信息实体描述
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月12日 下午3:30:00
 */
public class NodeTemplateInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 主键
	private String id;
	// 节点编码
	private String nodeCode;
	// 模板id
	private String templateId;
	// 模板编码
	private String templateCode;
	// 模板名称
	private String templateName;
	// 模板文件id
	private String templateFileId;
	// 模板版本号
	private String templateVersion;
	// 是否启用 0：停用 1：启用
	private Integer useStatus;
	// 租户id
	private String tenantId;
	// 创建日期-分配日期
	private String createdate;
	// 创建人
	private String creator;
	// 创建时间
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

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
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

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateFileId() {
		return templateFileId;
	}

	public void setTemplateFileId(String templateFileId) {
		this.templateFileId = templateFileId;
	}

	public String getTemplateVersion() {
		return templateVersion;
	}

	public void setTemplateVersion(String templateVersion) {
		this.templateVersion = templateVersion;
	}

}
