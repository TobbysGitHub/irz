package com.imfbp.rz.domain.template;

import java.io.Serializable;

/**
 * @Title : 文件公共信息
 * @Description : Controller层的公共信息
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年11月28日 下午6:29:45
 */
public class BaseSystemInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9171913226312520719L;
	// 租户ID
	private String tenantId;
	// 系统编码
	private String systemCode;
	// 文件类型
	private String fileType;
	// 当前用户id
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
