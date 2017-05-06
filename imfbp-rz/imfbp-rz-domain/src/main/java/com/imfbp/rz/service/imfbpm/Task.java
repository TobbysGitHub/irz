package com.imfbp.rz.service.imfbpm;

import org.joda.time.DateTime;


public class Task {
	private String id;
	private String url;
	private String owner;
	private String assignee;
	private String name;
	private String delegationState;
	private String category;
	private String executionId;
	private String executionUrl;
	private String procInstId;
	private String procDefId;
	private String createTime;
	private String suspended;
	private String priority;
	private String taskDefinitionKey;
	private String tenantId;
	private String dueDate;
	private String description;
	private String formKey;
	private String parentTaskId;
	private String parentTaskUrl;
	private String processInstanceId;
	private String processInstanceUrl;
	private String processDefinitionId;
	private String processDefinitionUrl;
	private String[] variables;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDelegationState() {
		return delegationState;
	}
	public void setDelegationState(String delegationState) {
		this.delegationState = delegationState;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getExecutionUrl() {
		return executionUrl;
	}
	public void setExecutionUrl(String executionUrl) {
		this.executionUrl = executionUrl;
	}
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	public String getProcDefId() {
		return procDefId;
	}
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		DateTime dateTime = new DateTime(createTime);  
		this.createTime = dateTime.toString("yyyy-MM-dd HH:mm:ss");
	}
	public String getSuspended() {
		return suspended;
	}
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	public String getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public String getParentTaskUrl() {
		return parentTaskUrl;
	}
	public void setParentTaskUrl(String parentTaskUrl) {
		this.parentTaskUrl = parentTaskUrl;
	}
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessInstanceUrl() {
		return processInstanceUrl;
	}
	public void setProcessInstanceUrl(String processInstanceUrl) {
		this.processInstanceUrl = processInstanceUrl;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getProcessDefinitionUrl() {
		return processDefinitionUrl;
	}
	public void setProcessDefinitionUrl(String processDefinitionUrl) {
		this.processDefinitionUrl = processDefinitionUrl;
	}
	public String[] getVariables() {
		return variables;
	}
	public void setVariables(String[] variables) {
		this.variables = variables;
	}
}
