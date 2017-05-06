package com.imfbp.rz.domain.rzrateb;


import java.io.Serializable;

public class RzRateB  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkRateB;
	//利率主表主键
	private String pkRate;
	//期限主键
	private String pkRateprd;
	//利率(%)
	private Double rate;
	

	public void setPkRateB(String pkRateB){
		this.pkRateB =  pkRateB;
	}
	
	public String getPkRateB(){
		return pkRateB;
	}

	public void setPkRate(String pkRate){
		this.pkRate =  pkRate;
	}
	
	public String getPkRate(){
		return pkRate;
	}

	public void setPkRateprd(String pkRateprd){
		this.pkRateprd =  pkRateprd;
	}
	
	public String getPkRateprd(){
		return pkRateprd;
	}

	public void setRate(Double rate){
		this.rate =  rate;
	}
	
	public Double getRate(){
		return rate;
	}
}