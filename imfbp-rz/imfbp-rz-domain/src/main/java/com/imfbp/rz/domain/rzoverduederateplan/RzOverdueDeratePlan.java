package com.imfbp.rz.domain.rzoverduederateplan;


import java.io.Serializable;

public class RzOverdueDeratePlan  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkOverdueDeratePlan;
	//主表主键
	private String pkOverdueDerate;
	//期数
	private String num;
	//序号
	private Integer seqno;
	//应收款日期
	private String receivableDate;
	//应收款合计(元)
	private Double receivableTotal;
	//实收款合计(元)
	private Double realTotal;
	//应收未收合计(元)
	private Double receivableNrcyTotal;
	//逾期天数
	private Integer overdueDays;
	//逾期利率(%)
	private Double overdueRate;
	//应收罚息(元)
	private Double receivableDefInt;
	//应收未收罚息(元)
	private Double receivableNrcyDefInt;
	//实际减免罚息(元)
	private Double actOverdur;
	//本次申请减免罚息(元)
	private Double curOverdurApply;
	

	public void setPkOverdueDeratePlan(String pkOverdueDeratePlan){
		this.pkOverdueDeratePlan =  pkOverdueDeratePlan;
	}
	
	public String getPkOverdueDeratePlan(){
		return pkOverdueDeratePlan;
	}

	public void setPkOverdueDerate(String pkOverdueDerate){
		this.pkOverdueDerate =  pkOverdueDerate;
	}
	
	public String getPkOverdueDerate(){
		return pkOverdueDerate;
	}

	public void setNum(String num){
		this.num =  num;
	}
	
	public String getNum(){
		return num;
	}

	public void setSeqno(Integer seqno){
		this.seqno =  seqno;
	}
	
	public Integer getSeqno(){
		return seqno;
	}

	public void setReceivableDate(String receivableDate){
		this.receivableDate =  receivableDate;
	}
	
	public String getReceivableDate(){
		return receivableDate;
	}

	public void setReceivableTotal(Double receivableTotal){
		this.receivableTotal =  receivableTotal;
	}
	
	public Double getReceivableTotal(){
		return receivableTotal;
	}

	public void setRealTotal(Double realTotal){
		this.realTotal =  realTotal;
	}
	
	public Double getRealTotal(){
		return realTotal;
	}

	public void setReceivableNrcyTotal(Double receivableNrcyTotal){
		this.receivableNrcyTotal =  receivableNrcyTotal;
	}
	
	public Double getReceivableNrcyTotal(){
		return receivableNrcyTotal;
	}

	public void setOverdueDays(Integer overdueDays){
		this.overdueDays =  overdueDays;
	}
	
	public Integer getOverdueDays(){
		return overdueDays;
	}

	public void setOverdueRate(Double overdueRate){
		this.overdueRate =  overdueRate;
	}
	
	public Double getOverdueRate(){
		return overdueRate;
	}

	public void setReceivableDefInt(Double receivableDefInt){
		this.receivableDefInt =  receivableDefInt;
	}
	
	public Double getReceivableDefInt(){
		return receivableDefInt;
	}

	public void setReceivableNrcyDefInt(Double receivableNrcyDefInt){
		this.receivableNrcyDefInt =  receivableNrcyDefInt;
	}
	
	public Double getReceivableNrcyDefInt(){
		return receivableNrcyDefInt;
	}

	public void setActOverdur(Double actOverdur){
		this.actOverdur =  actOverdur;
	}
	
	public Double getActOverdur(){
		return actOverdur;
	}

	public void setCurOverdurApply(Double curOverdurApply){
		this.curOverdurApply =  curOverdurApply;
	}
	
	public Double getCurOverdurApply(){
		return curOverdurApply;
	}
}