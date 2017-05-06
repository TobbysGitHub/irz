package com.imfbp.rz.domain.rzprjrevieweqpt;


import java.io.Serializable;

public class RzPrjreviewEqpt  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjreviewEqpt;
	//立项申请主键
	private String pkPrjreview;
	//客户主键
	private String pkCustomer;
	//租赁设备外键
	private String pkEqpt;
	//参照设备分类字典档案数据
	private String eqptType;
	//设备品牌
	private String eqptBrand;
	//设备型号
	private String eqptVer;
	//出厂编码
	private String mfgNo;
	//出厂日期
	private String mfgDate;
	//设备价格
	private Double eqptPrice;
	//制造商
	private String mfg;
	//数量
	private Integer eqptNum;
	//设备总价(元)
	private Double eqptPriceTotal;
	//设备评估价值（元）
	private Double assessPrice;
	//交货日期
	private String deliveryDate;
	//设备净值(元)
	private Double netVal;
	

	public void setPkPrjreviewEqpt(String pkPrjreviewEqpt){
		this.pkPrjreviewEqpt =  pkPrjreviewEqpt;
	}
	
	public String getPkPrjreviewEqpt(){
		return pkPrjreviewEqpt;
	}

	public void setPkPrjreview(String pkPrjreview){
		this.pkPrjreview =  pkPrjreview;
	}
	
	public String getPkPrjreview(){
		return pkPrjreview;
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

	public void setEqptBrand(String eqptBrand){
		this.eqptBrand =  eqptBrand;
	}
	
	public String getEqptBrand(){
		return eqptBrand;
	}

	public void setEqptVer(String eqptVer){
		this.eqptVer =  eqptVer;
	}
	
	public String getEqptVer(){
		return eqptVer;
	}

	public void setMfgNo(String mfgNo){
		this.mfgNo =  mfgNo;
	}
	
	public String getMfgNo(){
		return mfgNo;
	}

	public void setMfgDate(String mfgDate){
		this.mfgDate =  mfgDate;
	}
	
	public String getMfgDate(){
		return mfgDate;
	}

	public void setEqptPrice(Double eqptPrice){
		this.eqptPrice =  eqptPrice;
	}
	
	public Double getEqptPrice(){
		return eqptPrice;
	}

	public void setMfg(String mfg){
		this.mfg =  mfg;
	}
	
	public String getMfg(){
		return mfg;
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

	public void setAssessPrice(Double assessPrice){
		this.assessPrice =  assessPrice;
	}
	
	public Double getAssessPrice(){
		return assessPrice;
	}

	public void setDeliveryDate(String deliveryDate){
		this.deliveryDate =  deliveryDate;
	}
	
	public String getDeliveryDate(){
		return deliveryDate;
	}

	public void setNetVal(Double netVal){
		this.netVal =  netVal;
	}
	
	public Double getNetVal(){
		return netVal;
	}
}