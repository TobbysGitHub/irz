package com.imfbp.rz.domain.rzplanchangeleasenew;


import java.io.Serializable;

public class RzPlanChangeLeaseNew  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPlanChangeLeaseNew;
	//主键
	private String pkPlanChange;
	//序号
	private Integer seqNo;
	//现金流入
	private Double cfIn;
	//版本号
	private Double ver;
	//净流入
	private Double netCfIn;
	//现金流出
	private Double cfOut;
	//是否核销:Y是N否
	private String isCheck;
	//实际还款合计
	private Double actTotal;
	//实际支付手续费
	private Double actPayFee;
	//实际还款罚息
	private Double actDefInt;
	//实际还款利息
	private Double actInt;
	//实际还款本金
	private Double actAmt;
	//剩余本金
	private Double residualAmt;
	//应还款合计
	private Double rptTotal;
	//应支付手续费
	private Double payFee;
	//应还罚息
	private Double rptDefInt;
	//应还款租金
	private Double rptRent;
	//应还款利息
	private Double rptInt;
	//应还款本金
	private Double rptAmt;
	//租赁利率(%)
	private Double leaseRate;
	//期间天数
	private Integer durdays;
	//偿还期间
	private String paydur;
	//应还款日期
	private String rptDate;
	//期次数
	private String num;
	

	public void setPkPlanChangeLeaseNew(String pkPlanChangeLeaseNew){
		this.pkPlanChangeLeaseNew =  pkPlanChangeLeaseNew;
	}
	
	public String getPkPlanChangeLeaseNew(){
		return pkPlanChangeLeaseNew;
	}

	public void setPkPlanChange(String pkPlanChange){
		this.pkPlanChange =  pkPlanChange;
	}
	
	public String getPkPlanChange(){
		return pkPlanChange;
	}

	public void setSeqNo(Integer seqNo){
		this.seqNo =  seqNo;
	}
	
	public Integer getSeqNo(){
		return seqNo;
	}

	public void setCfIn(Double cfIn){
		this.cfIn =  cfIn;
	}
	
	public Double getCfIn(){
		return cfIn;
	}

	public void setVer(Double ver){
		this.ver =  ver;
	}
	
	public Double getVer(){
		return ver;
	}

	public void setNetCfIn(Double netCfIn){
		this.netCfIn =  netCfIn;
	}
	
	public Double getNetCfIn(){
		return netCfIn;
	}

	public void setCfOut(Double cfOut){
		this.cfOut =  cfOut;
	}
	
	public Double getCfOut(){
		return cfOut;
	}

	public void setIsCheck(String isCheck){
		this.isCheck =  isCheck;
	}
	
	public String getIsCheck(){
		return isCheck;
	}

	public void setActTotal(Double actTotal){
		this.actTotal =  actTotal;
	}
	
	public Double getActTotal(){
		return actTotal;
	}

	public void setActPayFee(Double actPayFee){
		this.actPayFee =  actPayFee;
	}
	
	public Double getActPayFee(){
		return actPayFee;
	}

	public void setActDefInt(Double actDefInt){
		this.actDefInt =  actDefInt;
	}
	
	public Double getActDefInt(){
		return actDefInt;
	}

	public void setActInt(Double actInt){
		this.actInt =  actInt;
	}
	
	public Double getActInt(){
		return actInt;
	}

	public void setActAmt(Double actAmt){
		this.actAmt =  actAmt;
	}
	
	public Double getActAmt(){
		return actAmt;
	}

	public void setResidualAmt(Double residualAmt){
		this.residualAmt =  residualAmt;
	}
	
	public Double getResidualAmt(){
		return residualAmt;
	}

	public void setRptTotal(Double rptTotal){
		this.rptTotal =  rptTotal;
	}
	
	public Double getRptTotal(){
		return rptTotal;
	}

	public void setPayFee(Double payFee){
		this.payFee =  payFee;
	}
	
	public Double getPayFee(){
		return payFee;
	}

	public void setRptDefInt(Double rptDefInt){
		this.rptDefInt =  rptDefInt;
	}
	
	public Double getRptDefInt(){
		return rptDefInt;
	}

	public void setRptRent(Double rptRent){
		this.rptRent =  rptRent;
	}
	
	public Double getRptRent(){
		return rptRent;
	}

	public void setRptInt(Double rptInt){
		this.rptInt =  rptInt;
	}
	
	public Double getRptInt(){
		return rptInt;
	}

	public void setRptAmt(Double rptAmt){
		this.rptAmt =  rptAmt;
	}
	
	public Double getRptAmt(){
		return rptAmt;
	}

	public void setLeaseRate(Double leaseRate){
		this.leaseRate =  leaseRate;
	}
	
	public Double getLeaseRate(){
		return leaseRate;
	}

	public void setDurdays(Integer durdays){
		this.durdays =  durdays;
	}
	
	public Integer getDurdays(){
		return durdays;
	}

	public void setPaydur(String paydur){
		this.paydur =  paydur;
	}
	
	public String getPaydur(){
		return paydur;
	}

	public void setRptDate(String rptDate){
		this.rptDate =  rptDate;
	}
	
	public String getRptDate(){
		return rptDate;
	}

	public void setNum(String num){
		this.num =  num;
	}
	
	public String getNum(){
		return num;
	}
}