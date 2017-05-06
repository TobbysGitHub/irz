package com.imfbp.rz.domain.pubnodevoinfo;


import java.io.Serializable;

public class PubNodevoinfo implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private Long id;
	//节点号
	private String nodecode;
	//节点名称
	private String nodename;
	//数据表名
	private String dbtablename;
	//vo类名
	private String voclassname;
	
	private String modelpath;
	

	public void setId(Long id){
		this.id =  id;
	}
	
	public Long getId(){
		return id;
	}

	public void setNodecode(String nodecode){
		this.nodecode =  nodecode;
	}
	
	public String getNodecode(){
		return nodecode;
	}

	public void setNodename(String nodename){
		this.nodename =  nodename;
	}
	
	public String getNodename(){
		return nodename;
	}

	public void setDbtablename(String dbtablename){
		this.dbtablename =  dbtablename;
	}
	
	public String getDbtablename(){
		return dbtablename;
	}

	public void setVoclassname(String voclassname){
		this.voclassname =  voclassname;
	}
	
	public String getVoclassname(){
		return voclassname;
	}

	public String getModelpath() {
		return modelpath;
	}

	public void setModelpath(String modelpath) {
		this.modelpath = modelpath;
	}
	
}