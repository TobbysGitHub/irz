package com.imfbp.rz.domain.rzpmtplancf.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzPmtPlanCfQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPmtPlanCf;
	//主键
	private String pkPmtPlan;
	//计划收支日期
	private String planpmtdate;
	//收付计划期次
	private String prd;
	//计划收付类型
	private Integer planpmttype;
	//计划收付类别
	private Integer planpmtcategory;
	//偿还期间
	private String pmtdur;
	//期间天数
	private Integer durdays;
	//租赁利率(%)
	private Double leaseRate;
	//应收本金
	private Double receivableAmt;
	//应收利息
	private Double receivableInt;
	//应收租金
	private Double receivableRent;
	//应收罚息
	private Double receivablDefInt;
	//应收手续费
	private Double receivableFee;
	//应收款合计
	private Double receivableTotal;
	//应付融资金额
	private Double payableFa;
	//应付服务费
	private Double payableSc;
	//应付款项金额
	private Double payableAmt;
	//实收本金
	private Double actAmt;
	//实收利息
	private Double actInt;
	//实收租金
	private Double actRent;
	//实收罚息
	private Double actDefInt;
	//实收手续费
	private Double actFee;
	//实际收付合计
	private Double actPmtTotal;
	//实收合计
	private Double actTotal;
	//实际收款日期
	private String actPmtDate;
	//实付融资金额
	private Double actFa;
	//实付服务费
	private Double actSc;
	//是否核销:Y是N否
	private String isCheck;
	//实付款项合计
	private Double actAmtTotal;
	//
	private Integer seqNo;
	//
	private Double ver;
	//接受改变合同状态的参数
	private String state;

	public void setPkPmtPlanCf(String pkPmtPlanCf){
		this.pkPmtPlanCf =  pkPmtPlanCf;
	}
	
	public String getPkPmtPlanCf(){
		return pkPmtPlanCf;
	}

	public void setPkPmtPlan(String pkPmtPlan){
		this.pkPmtPlan =  pkPmtPlan;
	}
	
	public String getPkPmtPlan(){
		return pkPmtPlan;
	}

	public void setPlanpmtdate(String planpmtdate){
		this.planpmtdate =  planpmtdate;
	}
	
	public String getPlanpmtdate(){
		return planpmtdate;
	}

	public void setPrd(String prd){
		this.prd =  prd;
	}
	
	public String getPrd(){
		return prd;
	}

	public void setPlanpmttype(Integer planpmttype){
		this.planpmttype =  planpmttype;
	}
	
	public Integer getPlanpmttype(){
		return planpmttype;
	}

	public void setPlanpmtcategory(Integer planpmtcategory){
		this.planpmtcategory =  planpmtcategory;
	}
	
	public Integer getPlanpmtcategory(){
		return planpmtcategory;
	}

	public void setPmtdur(String pmtdur){
		this.pmtdur =  pmtdur;
	}
	
	public String getPmtdur(){
		return pmtdur;
	}

	public void setDurdays(Integer durdays){
		this.durdays =  durdays;
	}
	
	public Integer getDurdays(){
		return durdays;
	}

	public void setLeaseRate(Double leaseRate){
		this.leaseRate =  leaseRate;
	}
	
	public Double getLeaseRate(){
		return leaseRate;
	}

	public void setReceivableAmt(Double receivableAmt){
		this.receivableAmt =  receivableAmt;
	}
	
	public Double getReceivableAmt(){
		return receivableAmt;
	}

	public void setReceivableInt(Double receivableInt){
		this.receivableInt =  receivableInt;
	}
	
	public Double getReceivableInt(){
		return receivableInt;
	}

	public void setReceivableRent(Double receivableRent){
		this.receivableRent =  receivableRent;
	}
	
	public Double getReceivableRent(){
		return receivableRent;
	}

	public void setReceivablDefInt(Double receivablDefInt){
		this.receivablDefInt =  receivablDefInt;
	}
	
	public Double getReceivablDefInt(){
		return receivablDefInt;
	}

	public void setReceivableFee(Double receivableFee){
		this.receivableFee =  receivableFee;
	}
	
	public Double getReceivableFee(){
		return receivableFee;
	}

	public void setReceivableTotal(Double receivableTotal){
		this.receivableTotal =  receivableTotal;
	}
	
	public Double getReceivableTotal(){
		return receivableTotal;
	}

	public void setPayableFa(Double payableFa){
		this.payableFa =  payableFa;
	}
	
	public Double getPayableFa(){
		return payableFa;
	}

	public void setPayableSc(Double payableSc){
		this.payableSc =  payableSc;
	}
	
	public Double getPayableSc(){
		return payableSc;
	}

	public void setPayableAmt(Double payableAmt){
		this.payableAmt =  payableAmt;
	}
	
	public Double getPayableAmt(){
		return payableAmt;
	}

	public void setActAmt(Double actAmt){
		this.actAmt =  actAmt;
	}
	
	public Double getActAmt(){
		return actAmt;
	}

	public void setActInt(Double actInt){
		this.actInt =  actInt;
	}
	
	public Double getActInt(){
		return actInt;
	}

	public void setActRent(Double actRent){
		this.actRent =  actRent;
	}
	
	public Double getActRent(){
		return actRent;
	}

	public void setActDefInt(Double actDefInt){
		this.actDefInt =  actDefInt;
	}
	
	public Double getActDefInt(){
		return actDefInt;
	}

	public void setActFee(Double actFee){
		this.actFee =  actFee;
	}
	
	public Double getActFee(){
		return actFee;
	}

	public void setActPmtTotal(Double actPmtTotal){
		this.actPmtTotal =  actPmtTotal;
	}
	
	public Double getActPmtTotal(){
		return actPmtTotal;
	}

	public void setActTotal(Double actTotal){
		this.actTotal =  actTotal;
	}
	
	public Double getActTotal(){
		return actTotal;
	}

	public void setActPmtDate(String actPmtDate){
		this.actPmtDate =  actPmtDate;
	}
	
	public String getActPmtDate(){
		return actPmtDate;
	}

	public void setActFa(Double actFa){
		this.actFa =  actFa;
	}
	
	public Double getActFa(){
		return actFa;
	}

	public void setActSc(Double actSc){
		this.actSc =  actSc;
	}
	
	public Double getActSc(){
		return actSc;
	}

	public void setIsCheck(String isCheck){
		this.isCheck =  isCheck;
	}
	
	public String getIsCheck(){
		return isCheck;
	}

	public void setActAmtTotal(Double actAmtTotal){
		this.actAmtTotal =  actAmtTotal;
	}
	
	public Double getActAmtTotal(){
		return actAmtTotal;
	}

	public void setSeqNo(Integer seqNo){
		this.seqNo =  seqNo;
	}
	
	public Integer getSeqNo(){
		return seqNo;
	}

	public void setVer(Double ver){
		this.ver =  ver;
	}
	
	public Double getVer(){
		return ver;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}




 

