package com.imfbp.rz.domain.templateversion;


import java.io.Serializable;

public class TemplateVersion  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键id
	private String id;
	//模板id
	private String templateCode;
	//模板版本号
	private String templateVersion;
	//模板文件id
	private String templateFileId;
	//时间戳
	private String ts;
	//删除标识
	private Integer dr;
	

	public void setId(String id){
		this.id =  id;
	}
	
	public String getId(){
		return id;
	}

	public void setTemplateCode(String templateCode){
		this.templateCode =  templateCode;
	}
	
	public String getTemplateCode(){
		return templateCode;
	}

	public void setTemplateVersion(String templateVersion){
		this.templateVersion =  templateVersion;
	}
	
	public String getTemplateVersion(){
		return templateVersion;
	}

	public void setTemplateFileId(String templateFileId){
		this.templateFileId =  templateFileId;
	}
	
	public String getTemplateFileId(){
		return templateFileId;
	}

	public void setTs(String ts){
		this.ts =  ts;
	}
	
	public String getTs(){
		return ts;
	}

	public void setDr(Integer dr){
		this.dr =  dr;
	}
	
	public Integer getDr(){
		return dr;
	}
}