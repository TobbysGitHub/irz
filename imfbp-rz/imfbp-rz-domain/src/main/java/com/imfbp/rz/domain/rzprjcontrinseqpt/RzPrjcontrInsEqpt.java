package com.imfbp.rz.domain.rzprjcontrinseqpt;


import java.io.Serializable;

public class RzPrjcontrInsEqpt  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrInsEqpt;
	//项目合同保险信息主键
	private String pkPrjcontrIns;
	//项目合同主键
	private String pkPrjcontr;
	//客户主键
	private String pkCustomer;
	//租赁设备外键
	private String pkEqpt;
	//参照设备分类字典档案数据
	private String eqptType;
	//设备价格
	private Double eqptPrice;
	//数量
	private Integer eqptNum;
	//设备总价(元)
	private Double eqptPriceTotal;
	//交货日期
	private String deliveryDate;
	

	public void setPkPrjcontrInsEqpt(String pkPrjcontrInsEqpt){
		this.pkPrjcontrInsEqpt =  pkPrjcontrInsEqpt;
	}
	
	public String getPkPrjcontrInsEqpt(){
		return pkPrjcontrInsEqpt;
	}

	public void setPkPrjcontrIns(String pkPrjcontrIns){
		this.pkPrjcontrIns =  pkPrjcontrIns;
	}
	
	public String getPkPrjcontrIns(){
		return pkPrjcontrIns;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setPkEqpt(String pkEqpt){
		this.pkEqpt =  pkEqpt;
	}
	
	public String getPkEqpt(){
		return pkEqpt;
	}

	public void setEqptType(String eqptType){
		this.eqptType =  eqptType;
	}
	
	public String getEqptType(){
		return eqptType;
	}

	public void setEqptPrice(Double eqptPrice){
		this.eqptPrice =  eqptPrice;
	}
	
	public Double getEqptPrice(){
		return eqptPrice;
	}

	public void setEqptNum(Integer eqptNum){
		this.eqptNum =  eqptNum;
	}
	
	public Integer getEqptNum(){
		return eqptNum;
	}

	public void setEqptPriceTotal(Double eqptPriceTotal){
		this.eqptPriceTotal =  eqptPriceTotal;
	}
	
	public Double getEqptPriceTotal(){
		return eqptPriceTotal;
	}

	public void setDeliveryDate(String deliveryDate){
		this.deliveryDate =  deliveryDate;
	}
	
	public String getDeliveryDate(){
		return deliveryDate;
	}
}