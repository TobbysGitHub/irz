package com.imfbp.rz.domain.rzpricecalrent;


import java.io.Serializable;

public class RzPricecalRent  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPricecalRent;
	//主键
	private String pkPricecal;
	//期数
	private Integer num;
	//应还款日期
	private String rptDate;
	//偿还期间
	private String paydur;
	//期间天数
	private Integer durdays;
	//租赁利率(%)
	private Double rentRate;
	//应还款本金
	private Double rptAmt;
	//应还款利息
	private Double rptInt;
	//应还款租金
	private Double rptRent;
	//应支付手续费
	private Double payFee;
	//应还款合计
	private Double rptTotal;
	//剩余本金
	private Double residualAmt;
	

	public void setPkPricecalRent(String pkPricecalRent){
		this.pkPricecalRent =  pkPricecalRent;
	}
	
	public String getPkPricecalRent(){
		return pkPricecalRent;
	}

	public void setPkPricecal(String pkPricecal){
		this.pkPricecal =  pkPricecal;
	}
	
	public String getPkPricecal(){
		return pkPricecal;
	}

	public void setNum(Integer num){
		this.num =  num;
	}
	
	public Integer getNum(){
		return num;
	}

	public void setRptDate(String rptDate){
		this.rptDate =  rptDate;
	}
	
	public String getRptDate(){
		return rptDate;
	}

	public void setPaydur(String paydur){
		this.paydur =  paydur;
	}
	
	public String getPaydur(){
		return paydur;
	}

	public void setDurdays(Integer durdays){
		this.durdays =  durdays;
	}
	
	public Integer getDurdays(){
		return durdays;
	}

	public void setRentRate(Double rentRate){
		this.rentRate =  rentRate;
	}
	
	public Double getRentRate(){
		return rentRate;
	}

	public void setRptAmt(Double rptAmt){
		this.rptAmt =  rptAmt;
	}
	
	public Double getRptAmt(){
		return rptAmt;
	}

	public void setRptInt(Double rptInt){
		this.rptInt =  rptInt;
	}
	
	public Double getRptInt(){
		return rptInt;
	}

	public void setRptRent(Double rptRent){
		this.rptRent =  rptRent;
	}
	
	public Double getRptRent(){
		return rptRent;
	}

	public void setPayFee(Double payFee){
		this.payFee =  payFee;
	}
	
	public Double getPayFee(){
		return payFee;
	}

	public void setRptTotal(Double rptTotal){
		this.rptTotal =  rptTotal;
	}
	
	public Double getRptTotal(){
		return rptTotal;
	}

	public void setResidualAmt(Double residualAmt){
		this.residualAmt =  residualAmt;
	}
	
	public Double getResidualAmt(){
		return residualAmt;
	}
}