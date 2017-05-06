package com.imfbp.rz.domain.rzrateprd;


import java.io.Serializable;

public class RzRateprd  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkRateprd;
	//期限名称
	private String prdName;
	//期限类型 1
	private Integer prdType;
	//期限范围(开始)天数
	private Integer startTerm;
	//期限终止天数
	private Integer endTerm;
	//期限注释
	private String remark;
	

	public void setPkRateprd(String pkRateprd){
		this.pkRateprd =  pkRateprd;
	}
	
	public String getPkRateprd(){
		return pkRateprd;
	}

	public void setPrdName(String prdName){
		this.prdName =  prdName;
	}
	
	public String getPrdName(){
		return prdName;
	}

	public void setPrdType(Integer prdType){
		this.prdType =  prdType;
	}
	
	public Integer getPrdType(){
		return prdType;
	}

	public void setStartTerm(Integer startTerm){
		this.startTerm =  startTerm;
	}
	
	public Integer getStartTerm(){
		return startTerm;
	}

	public void setEndTerm(Integer endTerm){
		this.endTerm =  endTerm;
	}
	
	public Integer getEndTerm(){
		return endTerm;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}
}