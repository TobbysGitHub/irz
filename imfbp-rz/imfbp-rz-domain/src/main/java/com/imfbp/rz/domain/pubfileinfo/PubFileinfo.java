package com.imfbp.rz.domain.pubfileinfo;

import java.io.Serializable;

import com.imfbp.rz.domain.pub.SuperHeadBean;

public class PubFileinfo  implements Serializable {

	private static final long serialVersionUID = 1L;

	// 文件主键
	private String pkFile;
	// 节点号
	private String funcode;
	// 单据主键
	private String pkBill;
	// 文件名称
	private String filename;
	// 文件描述
	private String description;
	// 上传人
	private String uploader;
	// 上传时间
	private String uploadtime;
	// 文件类型
	private String filetype;
	// 文件长度
	private Long filelength;
	//
	private String fileFastdfsGroup;
	//
	private String fileFastdfsId;

	// 租户ID
	private String tenantId;
	// 系统编码
	private String systemCode;

	// 文档类型
	private String doctype;
	// 文件来源
	private String filesrc;

	public void setPkFile(String pkFile) {
		this.pkFile = pkFile;
	}

	public String getPkFile() {
		return pkFile;
	}

	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}

	public String getFuncode() {
		return funcode;
	}

	public void setPkBill(String pkBill) {
		this.pkBill = pkBill;
	}

	public String getPkBill() {
		return pkBill;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFilelength(Long filelength) {
		this.filelength = filelength;
	}

	public Long getFilelength() {
		return filelength;
	}

	public void setFileFastdfsGroup(String fileFastdfsGroup) {
		this.fileFastdfsGroup = fileFastdfsGroup;
	}

	public String getFileFastdfsGroup() {
		return fileFastdfsGroup;
	}

	public void setFileFastdfsId(String fileFastdfsId) {
		this.fileFastdfsId = fileFastdfsId;
	}

	public String getFileFastdfsId() {
		return fileFastdfsId;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setFilesrc(String filesrc) {
		this.filesrc = filesrc;
	}

	public String getFilesrc() {
		return filesrc;
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


}