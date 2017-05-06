package com.imfbp.rz.domain.pubfilestore;


import java.io.Serializable;

import com.imfbp.rz.domain.pub.SuperBean;

public class PubFilestore  extends SuperBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//
	private String pkFile;
	//
	private Object filecontent;
	//
	private Long filelength;
	//
	private String filetime;
	//
	private String filename;
	

	public void setPkFile(String pkFile){
		this.pkFile =  pkFile;
	}
	
	public String getPkFile(){
		return pkFile;
	}

	public void setFilecontent(Object filecontent){
		this.filecontent =  filecontent;
	}
	
	public Object getFilecontent(){
		return filecontent;
	}

	public void setFilelength(Long filelength){
		this.filelength =  filelength;
	}
	
	public Long getFilelength(){
		return filelength;
	}

	public void setFiletime(String filetime){
		this.filetime =  filetime;
	}
	
	public String getFiletime(){
		return filetime;
	}

	public void setFilename(String filename){
		this.filename =  filename;
	}
	
	public String getFilename(){
		return filename;
	}

	@Override
	public String getTableName() {
		return "pub_filestore";
	}

	@Override
	public String getPkFieldName() {
		return "pkFile";
	}
	
}