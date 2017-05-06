package com.imfbp.rz.domain.rzprjreview;


import java.io.Serializable;

import com.imfbp.rz.domain.pub.SuperHeadBean;

public class RzPrjreview  extends SuperHeadBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjreview;
	//评审单编码
	private String reviewCode;
	//立项申请主键
	private String pkPrjapply;
	//客户名称外键
	private String pkCustomer;
	//参照数据字典
	private String busitype;
	//租赁方式 0 直租、1 售后回租、2 其他
	private Integer leaseway;
	//租赁物类型 ： 0 有形动产、1 不动产
	private Integer leasetype;
	//申请日期
	private String applydate;
	//预计投放日期
	private String launchdate;
	//项目来源，数据字典
	private String prjSrc;
	//项目经理
	private String pkUserManager;
	//申请部门
	private String pkDeptApply;
	//租赁期限(月)
	private Integer leaseprd;
	//是否投保 ：Y 是 N否
	private String isInsure;
	//是否有担保：Y 是 N否
	private String isGuar;
	//项目承做原因
	private String prjReason;
	//报价测算主键
	private String pkPricecal;
	//项目金额
	private Double itemamt;
	//首付款金额
	private Double firstpmtamt;
	//融资金额
	private Double financeamt;
	//报价利率(%)
	private Double pricerate;
	//计划收租日
	private String planrentdate;
	//还款周期(月)
	private Integer reptcycle;
	//租金支付方式：0 期末支付、1 期初支付
	private Integer paymentway;
	//还款方式：0 等额租金法、1等额本息法、2平息法、3自由还款法
	private Integer reptway;
	//经营性每期租金
	private Double optamt;
	//客户保证金金额（元）
	private Double depositamt;
	//手续费收入总额（元）
	private Double feeIntotalAmt;
	//服务费支出总额（元）
	private Double scOuttotalAmt;
	//总利息（元）
	private Double intTotalAmt;
	//总本金（元）
	private Double prinTotalAmt;
	//总租金（元）
	private Double rentTotalAmt;
	//项目收益IRR(%)
	private Double irr;
	//出租人主键
	private String pkOrgLessor;
	//备注
	private String remark;
	//设备总额
	private Double eqptPriceTotal;
	//首付款比例(%)
	private Integer firstpmtpct;
	//币种主键
	private String pkCurrency;
	//0浮动 1 固定
	private Integer ratetype;
	//基准利率(%)
	private Double baserate;
	//上下浮比例(%)
	private Integer floatpct;
	//360 365
	private Integer yeardays;
	//租金付款方式：0 现金、1 网银支付、2汇款、3承兑汇票、4支付宝
	private Integer paymentmethod;
	//客户保证金比例(%)
	private Integer depositpct;
	//客户保证金退回方式：0期末退回  1 冲抵租金
	private Integer returndepositway;
	//0 合同金额、1 融资金额
	private Integer feeradix;
	//手续费比例(%)
	private Double feepct;
	//6%、11%、17%、零税率
	private Integer sctaxrate;
	//0 一次性支付、1 随租金支付
	private Integer feepayway;
	//服务费比例(%)
	private Integer scpct;
	//6%、11%、17%、零税率
	private Integer feetaxrate;
	//机构外键
	private String pkOrg;
	//部门外键
	private String pkDept;
	//制单人
	private String billmaker;
	//制单日期
	private String billdate;
	//审批人
	private String approveid;
	//审批时间
	private String approvedate;
	//审批状态(0 自由 1提交 2 审批中 3 审批通过 4 审批不通过)
	private Integer approvestatus;
	//审批语
	private String approvenote;
	//录入人
	private String operator;
	//录入时间
	private String operatordatetime;
	//修改人
	private String modifor;
	//修改时间
	private String modifydatetime;
	//流程实例ID
	private String flowinstanceid;
	//
	private String ts;
	//0 未删除 1已删除
	private Integer dr;
	//
	private String def1;
	//
	private String def2;
	//
	private String def3;
	//
	private String def4;
	//
	private String def5;
	

	public void setPkPrjreview(String pkPrjreview){
		this.pkPrjreview =  pkPrjreview;
	}
	
	public String getPkPrjreview(){
		return pkPrjreview;
	}

	public void setReviewCode(String reviewCode){
		this.reviewCode =  reviewCode;
	}
	
	public String getReviewCode(){
		return reviewCode;
	}

	public void setPkPrjapply(String pkPrjapply){
		this.pkPrjapply =  pkPrjapply;
	}
	
	public String getPkPrjapply(){
		return pkPrjapply;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setBusitype(String busitype){
		this.busitype =  busitype;
	}
	
	public String getBusitype(){
		return busitype;
	}

	public void setLeaseway(Integer leaseway){
		this.leaseway =  leaseway;
	}
	
	public Integer getLeaseway(){
		return leaseway;
	}

	public void setLeasetype(Integer leasetype){
		this.leasetype =  leasetype;
	}
	
	public Integer getLeasetype(){
		return leasetype;
	}

	public void setApplydate(String applydate){
		this.applydate =  applydate;
	}
	
	public String getApplydate(){
		return applydate;
	}

	public void setLaunchdate(String launchdate){
		this.launchdate =  launchdate;
	}
	
	public String getLaunchdate(){
		return launchdate;
	}

	public void setPrjSrc(String prjSrc){
		this.prjSrc =  prjSrc;
	}
	
	public String getPrjSrc(){
		return prjSrc;
	}

	public void setPkUserManager(String pkUserManager){
		this.pkUserManager =  pkUserManager;
	}
	
	public String getPkUserManager(){
		return pkUserManager;
	}

	public void setPkDeptApply(String pkDeptApply){
		this.pkDeptApply =  pkDeptApply;
	}
	
	public String getPkDeptApply(){
		return pkDeptApply;
	}

	public void setLeaseprd(Integer leaseprd){
		this.leaseprd =  leaseprd;
	}
	
	public Integer getLeaseprd(){
		return leaseprd;
	}

	public void setIsInsure(String isInsure){
		this.isInsure =  isInsure;
	}
	
	public String getIsInsure(){
		return isInsure;
	}

	public void setIsGuar(String isGuar){
		this.isGuar =  isGuar;
	}
	
	public String getIsGuar(){
		return isGuar;
	}

	public void setPrjReason(String prjReason){
		this.prjReason =  prjReason;
	}
	
	public String getPrjReason(){
		return prjReason;
	}

	public void setPkPricecal(String pkPricecal){
		this.pkPricecal =  pkPricecal;
	}
	
	public String getPkPricecal(){
		return pkPricecal;
	}

	public void setItemamt(Double itemamt){
		this.itemamt =  itemamt;
	}
	
	public Double getItemamt(){
		return itemamt;
	}

	public void setFirstpmtamt(Double firstpmtamt){
		this.firstpmtamt =  firstpmtamt;
	}
	
	public Double getFirstpmtamt(){
		return firstpmtamt;
	}

	public void setFinanceamt(Double financeamt){
		this.financeamt =  financeamt;
	}
	
	public Double getFinanceamt(){
		return financeamt;
	}

	public void setPricerate(Double pricerate){
		this.pricerate =  pricerate;
	}
	
	public Double getPricerate(){
		return pricerate;
	}

	public void setPlanrentdate(String planrentdate){
		this.planrentdate =  planrentdate;
	}
	
	public String getPlanrentdate(){
		return planrentdate;
	}

	public void setReptcycle(Integer reptcycle){
		this.reptcycle =  reptcycle;
	}
	
	public Integer getReptcycle(){
		return reptcycle;
	}

	public void setPaymentway(Integer paymentway){
		this.paymentway =  paymentway;
	}
	
	public Integer getPaymentway(){
		return paymentway;
	}

	public void setReptway(Integer reptway){
		this.reptway =  reptway;
	}
	
	public Integer getReptway(){
		return reptway;
	}

	public void setOptamt(Double optamt){
		this.optamt =  optamt;
	}
	
	public Double getOptamt(){
		return optamt;
	}

	public void setDepositamt(Double depositamt){
		this.depositamt =  depositamt;
	}
	
	public Double getDepositamt(){
		return depositamt;
	}

	public void setFeeIntotalAmt(Double feeIntotalAmt){
		this.feeIntotalAmt =  feeIntotalAmt;
	}
	
	public Double getFeeIntotalAmt(){
		return feeIntotalAmt;
	}

	public void setScOuttotalAmt(Double scOuttotalAmt){
		this.scOuttotalAmt =  scOuttotalAmt;
	}
	
	public Double getScOuttotalAmt(){
		return scOuttotalAmt;
	}

	public void setIntTotalAmt(Double intTotalAmt){
		this.intTotalAmt =  intTotalAmt;
	}
	
	public Double getIntTotalAmt(){
		return intTotalAmt;
	}

	public void setPrinTotalAmt(Double prinTotalAmt){
		this.prinTotalAmt =  prinTotalAmt;
	}
	
	public Double getPrinTotalAmt(){
		return prinTotalAmt;
	}

	public void setRentTotalAmt(Double rentTotalAmt){
		this.rentTotalAmt =  rentTotalAmt;
	}
	
	public Double getRentTotalAmt(){
		return rentTotalAmt;
	}

	public void setIrr(Double irr){
		this.irr =  irr;
	}
	
	public Double getIrr(){
		return irr;
	}

	public void setPkOrgLessor(String pkOrgLessor){
		this.pkOrgLessor =  pkOrgLessor;
	}
	
	public String getPkOrgLessor(){
		return pkOrgLessor;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public void setEqptPriceTotal(Double eqptPriceTotal){
		this.eqptPriceTotal =  eqptPriceTotal;
	}
	
	public Double getEqptPriceTotal(){
		return eqptPriceTotal;
	}

	public void setFirstpmtpct(Integer firstpmtpct){
		this.firstpmtpct =  firstpmtpct;
	}
	
	public Integer getFirstpmtpct(){
		return firstpmtpct;
	}

	public void setPkCurrency(String pkCurrency){
		this.pkCurrency =  pkCurrency;
	}
	
	public String getPkCurrency(){
		return pkCurrency;
	}

	public void setRatetype(Integer ratetype){
		this.ratetype =  ratetype;
	}
	
	public Integer getRatetype(){
		return ratetype;
	}

	public void setBaserate(Double baserate){
		this.baserate =  baserate;
	}
	
	public Double getBaserate(){
		return baserate;
	}

	public void setFloatpct(Integer floatpct){
		this.floatpct =  floatpct;
	}
	
	public Integer getFloatpct(){
		return floatpct;
	}

	public void setYeardays(Integer yeardays){
		this.yeardays =  yeardays;
	}
	
	public Integer getYeardays(){
		return yeardays;
	}

	public void setPaymentmethod(Integer paymentmethod){
		this.paymentmethod =  paymentmethod;
	}
	
	public Integer getPaymentmethod(){
		return paymentmethod;
	}

	public void setDepositpct(Integer depositpct){
		this.depositpct =  depositpct;
	}
	
	public Integer getDepositpct(){
		return depositpct;
	}

	public void setReturndepositway(Integer returndepositway){
		this.returndepositway =  returndepositway;
	}
	
	public Integer getReturndepositway(){
		return returndepositway;
	}

	public void setFeeradix(Integer feeradix){
		this.feeradix =  feeradix;
	}
	
	public Integer getFeeradix(){
		return feeradix;
	}

	public void setFeepct(Double feepct){
		this.feepct =  feepct;
	}
	
	public Double getFeepct(){
		return feepct;
	}

	public void setSctaxrate(Integer sctaxrate){
		this.sctaxrate =  sctaxrate;
	}
	
	public Integer getSctaxrate(){
		return sctaxrate;
	}

	public void setFeepayway(Integer feepayway){
		this.feepayway =  feepayway;
	}
	
	public Integer getFeepayway(){
		return feepayway;
	}

	public void setScpct(Integer scpct){
		this.scpct =  scpct;
	}
	
	public Integer getScpct(){
		return scpct;
	}

	public void setFeetaxrate(Integer feetaxrate){
		this.feetaxrate =  feetaxrate;
	}
	
	public Integer getFeetaxrate(){
		return feetaxrate;
	}

	public void setPkOrg(String pkOrg){
		this.pkOrg =  pkOrg;
	}
	
	public String getPkOrg(){
		return pkOrg;
	}

	public void setPkDept(String pkDept){
		this.pkDept =  pkDept;
	}
	
	public String getPkDept(){
		return pkDept;
	}

	public void setBillmaker(String billmaker){
		this.billmaker =  billmaker;
	}
	
	public String getBillmaker(){
		return billmaker;
	}

	public void setBilldate(String billdate){
		this.billdate =  billdate;
	}
	
	public String getBilldate(){
		return billdate;
	}

	public void setApproveid(String approveid){
		this.approveid =  approveid;
	}
	
	public String getApproveid(){
		return approveid;
	}

	public void setApprovedate(String approvedate){
		this.approvedate =  approvedate;
	}
	
	public String getApprovedate(){
		return approvedate;
	}

	public void setApprovestatus(Integer approvestatus){
		this.approvestatus =  approvestatus;
	}
	
	public Integer getApprovestatus(){
		return approvestatus;
	}

	public void setApprovenote(String approvenote){
		this.approvenote =  approvenote;
	}
	
	public String getApprovenote(){
		return approvenote;
	}

	public void setOperator(String operator){
		this.operator =  operator;
	}
	
	public String getOperator(){
		return operator;
	}

	public void setOperatordatetime(String operatordatetime){
		this.operatordatetime =  operatordatetime;
	}
	
	public String getOperatordatetime(){
		return operatordatetime;
	}

	public void setModifor(String modifor){
		this.modifor =  modifor;
	}
	
	public String getModifor(){
		return modifor;
	}

	public void setModifydatetime(String modifydatetime){
		this.modifydatetime =  modifydatetime;
	}
	
	public String getModifydatetime(){
		return modifydatetime;
	}

	public void setFlowinstanceid(String flowinstanceid){
		this.flowinstanceid =  flowinstanceid;
	}
	
	public String getFlowinstanceid(){
		return flowinstanceid;
	}

	public void setTs(String ts){
		this.ts =  ts;
	}
	
	public String getTs(){
		return ts;
	}

	public void setDr(Integer dr){
		this.dr =  dr;
	}
	
	public Integer getDr(){
		return dr;
	}

	public void setDef1(String def1){
		this.def1 =  def1;
	}
	
	public String getDef1(){
		return def1;
	}

	public void setDef2(String def2){
		this.def2 =  def2;
	}
	
	public String getDef2(){
		return def2;
	}

	public void setDef3(String def3){
		this.def3 =  def3;
	}
	
	public String getDef3(){
		return def3;
	}

	public void setDef4(String def4){
		this.def4 =  def4;
	}
	
	public String getDef4(){
		return def4;
	}

	public void setDef5(String def5){
		this.def5 =  def5;
	}
	
	public String getDef5(){
		return def5;
	}

	@Override
	public String billNoFieldName() {
		return "reviewCode";
	}

	@Override
	public String getPkFieldName() {
		return "pkPrjreview";
	}

	@Override
	public String getTableName() {
		return "rz_prjreview";
	}
}