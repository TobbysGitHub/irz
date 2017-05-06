package com.imfbp.rz.domain.rzprjcontrguarb;


import java.io.Serializable;

public class RzPrjcontrGuarB  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrGuarB;
	//担保方信息主表主键
	private String pkPrjcontrGuar;
	//项目合同主键
	private String pkPrjcontr;
	//抵质押物数量
	private Integer mortgageNum;
	//抵质押物质量
	private String mortgageQuality;
	//抵质押物状态：0 未抵押、 1 已抵押
	private Integer mortgageState;
	//
	private String situation;
	//当前估值
	private Double currVal;
	//所在地
	private String address;
	//抵质押物名称
	private String mortgageName;
	//出厂日期
	private String prodDate;
	

	public void setPkPrjcontrGuarB(String pkPrjcontrGuarB){
		this.pkPrjcontrGuarB =  pkPrjcontrGuarB;
	}
	
	public String getPkPrjcontrGuarB(){
		return pkPrjcontrGuarB;
	}

	public void setPkPrjcontrGuar(String pkPrjcontrGuar){
		this.pkPrjcontrGuar =  pkPrjcontrGuar;
	}
	
	public String getPkPrjcontrGuar(){
		return pkPrjcontrGuar;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setMortgageNum(Integer mortgageNum){
		this.mortgageNum =  mortgageNum;
	}
	
	public Integer getMortgageNum(){
		return mortgageNum;
	}

	public void setMortgageQuality(String mortgageQuality){
		this.mortgageQuality =  mortgageQuality;
	}
	
	public String getMortgageQuality(){
		return mortgageQuality;
	}

	public void setMortgageState(Integer mortgageState){
		this.mortgageState =  mortgageState;
	}
	
	public Integer getMortgageState(){
		return mortgageState;
	}

	public void setSituation(String situation){
		this.situation =  situation;
	}
	
	public String getSituation(){
		return situation;
	}

	public void setCurrVal(Double currVal){
		this.currVal =  currVal;
	}
	
	public Double getCurrVal(){
		return currVal;
	}

	public void setAddress(String address){
		this.address =  address;
	}
	
	public String getAddress(){
		return address;
	}

	public void setMortgageName(String mortgageName){
		this.mortgageName =  mortgageName;
	}
	
	public String getMortgageName(){
		return mortgageName;
	}

	public void setProdDate(String prodDate){
		this.prodDate =  prodDate;
	}
	
	public String getProdDate(){
		return prodDate;
	}
}