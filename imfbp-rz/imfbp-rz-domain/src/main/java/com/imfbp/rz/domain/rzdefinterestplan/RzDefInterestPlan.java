package com.imfbp.rz.domain.rzdefinterestplan;


import java.io.Serializable;

public class RzDefInterestPlan  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkDefInterestPlan;
	//主表主键
	private String pkDefInterest;
	//期数
	private String num;
	//应还款日期
	private String rptDate;
	//应收款合计(元)
	private Double receivableTotal;
	//实收款合计(元)
	private Double realTotal;
	//应收未收合计(元)
	private String receivableNrcyTotal;
	//逾期天数
	private Integer overdueDays;
	//逾期利率(%)
	private Double overdueRate;
	//应罚息金额(元)
	private Double defIntAmt;
	//逾期类型：0 本金逾期、1 利息逾期、2 本息逾期
	private Integer overType;
	//序号
	private Integer seqno;
	

	public void setPkDefInterestPlan(String pkDefInterestPlan){
		this.pkDefInterestPlan =  pkDefInterestPlan;
	}
	
	public String getPkDefInterestPlan(){
		return pkDefInterestPlan;
	}

	public void setPkDefInterest(String pkDefInterest){
		this.pkDefInterest =  pkDefInterest;
	}
	
	public String getPkDefInterest(){
		return pkDefInterest;
	}

	public void setNum(String num){
		this.num =  num;
	}
	
	public String getNum(){
		return num;
	}

	public void setRptDate(String rptDate){
		this.rptDate =  rptDate;
	}
	
	public String getRptDate(){
		return rptDate;
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

	public void setReceivableNrcyTotal(String receivableNrcyTotal){
		this.receivableNrcyTotal =  receivableNrcyTotal;
	}
	
	public String getReceivableNrcyTotal(){
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

	public void setDefIntAmt(Double defIntAmt){
		this.defIntAmt =  defIntAmt;
	}
	
	public Double getDefIntAmt(){
		return defIntAmt;
	}

	public void setOverType(Integer overType){
		this.overType =  overType;
	}
	
	public Integer getOverType(){
		return overType;
	}

	public void setSeqno(Integer seqno){
		this.seqno =  seqno;
	}
	
	public Integer getSeqno(){
		return seqno;
	}
}