package com.imfbp.rz.domain.rzprjapply;


import java.io.Serializable;

import com.imfbp.rz.domain.pub.SuperHeadBean;

public class RzPrjapply extends SuperHeadBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjapply;
	//项目编号
	private String prjCode;
	//项目名称
	private String prjName;
	//报价测算主键
	private String pkPricecal;
	//客户名称外键
	private String pkCustomer;
	//客户类型
	private String custtype;
	//参照数据字典
	private String busitype;
	//租赁方式 0 直租、1 售后回租、2 其他
	private Integer leaseway;
	//租赁物类型 ： 0 有形动产、1 不动产
	private Integer leasetype;
	//申请日期
	private String applydate;
	//投放日期
	private String launchdate;
	//项目来源，数据字典
	private String prjSrc;
	//项目经理
	private String pkUserManager;
	//申请部门
	private String pkDeptApply;
	//项目金额
	private Double itemamt;
	//租赁期限(月)
	private Integer leaseprd;
	//项目承做原因
	private String prjReason;
	//出租人主键
	private String pkOrgLessor;
	//备注
	private String remark;
	//设备总额
	private Double eqptPriceTotal;
	//首付款比例(%)
	private Integer firstpmtpct;
	//首付款金额
	private Double firstpmtamt;
	//融资金额
	private Double financeamt;
	//币种主键
	private String pkCurrency;
	//0浮动 1 固定
	private Integer ratetype;
	//基准利率(%)
	private Double baserate;
	//上下浮比例(%)
	private Double floatpct;
	//报价利率(%)
	private Double pricerate;
	//计划收租日
	private String planrentdate;
	//还款周期(月)
	private Integer reptcycle;
	//经营性每期租金
	private Double optamt;
	//360 365
	private Integer yeardays;
	//还款方式：0 等额租金法、1等额本息法、2平息法、3自由还款法
	private Integer reptway;
	//租金支付方式：0 期末支付、1 期初支付
	private Integer paymentway;
	//租金付款方式：0 现金、1 网银支付、2汇款、3承兑汇票、4支付宝
	private Integer paymentmethod;
	//项目收益IRR(%)
	private Double irr;
	//客户保证金比例(%)
	private Double depositpct;
	//客户保证金金额
	private Double depositamt;
	//客户保证金退回方式：0期末退回  1 冲抵租金
	private Integer returndepositway;
	//0 合同金额、1 融资金额
	private Integer feeradix;
	//手续费比例(%)
	private Double feepct;
	//手续费金额
	private Double feeamt;
	//6%、11%、17%、零税率
	private Integer sctaxrate;
	//0 一次性支付、1 随租金支付
	private Integer feepayway;
	//服务费比例(%)
	private Double scpct;
	//服务费总额
	private Double scamt;
	//6%、11%、17%、零税率
	private Integer feetaxrate;
	//是否选择租赁物： Y 是 N否
	private String isselectrent;
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
	

	public void setPkPrjapply(String pkPrjapply){
		this.pkPrjapply =  pkPrjapply;
	}
	
	public String getPkPrjapply(){
		return pkPrjapply;
	}

	public void setPrjCode(String prjCode){
		this.prjCode =  prjCode;
	}
	
	public String getPrjCode(){
		return prjCode;
	}

	public void setPrjName(String prjName){
		this.prjName =  prjName;
	}
	
	public String getPrjName(){
		return prjName;
	}

	public void setPkPricecal(String pkPricecal){
		this.pkPricecal =  pkPricecal;
	}
	
	public String getPkPricecal(){
		return pkPricecal;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setCusttype(String custtype){
		this.custtype =  custtype;
	}
	
	public String getCusttype(){
		return custtype;
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

	public void setItemamt(Double itemamt){
		this.itemamt =  itemamt;
	}
	
	public Double getItemamt(){
		return itemamt;
	}

	public void setLeaseprd(Integer leaseprd){
		this.leaseprd =  leaseprd;
	}
	
	public Integer getLeaseprd(){
		return leaseprd;
	}

	public void setPrjReason(String prjReason){
		this.prjReason =  prjReason;
	}
	
	public String getPrjReason(){
		return prjReason;
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

	public void setFloatpct(Double floatpct){
		this.floatpct =  floatpct;
	}
	
	public Double getFloatpct(){
		return floatpct;
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

	public void setOptamt(Double optamt){
		this.optamt =  optamt;
	}
	
	public Double getOptamt(){
		return optamt;
	}

	public void setYeardays(Integer yeardays){
		this.yeardays =  yeardays;
	}
	
	public Integer getYeardays(){
		return yeardays;
	}

	public void setReptway(Integer reptway){
		this.reptway =  reptway;
	}
	
	public Integer getReptway(){
		return reptway;
	}

	public void setPaymentway(Integer paymentway){
		this.paymentway =  paymentway;
	}
	
	public Integer getPaymentway(){
		return paymentway;
	}

	public void setPaymentmethod(Integer paymentmethod){
		this.paymentmethod =  paymentmethod;
	}
	
	public Integer getPaymentmethod(){
		return paymentmethod;
	}

	public void setIrr(Double irr){
		this.irr =  irr;
	}
	
	public Double getIrr(){
		return irr;
	}

	public void setDepositpct(Double depositpct){
		this.depositpct =  depositpct;
	}
	
	public Double getDepositpct(){
		return depositpct;
	}

	public void setDepositamt(Double depositamt){
		this.depositamt =  depositamt;
	}
	
	public Double getDepositamt(){
		return depositamt;
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

	public void setFeeamt(Double feeamt){
		this.feeamt =  feeamt;
	}
	
	public Double getFeeamt(){
		return feeamt;
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

	public void setScpct(Double scpct){
		this.scpct =  scpct;
	}
	
	public Double getScpct(){
		return scpct;
	}

	public void setScamt(Double scamt){
		this.scamt =  scamt;
	}
	
	public Double getScamt(){
		return scamt;
	}

	public void setFeetaxrate(Integer feetaxrate){
		this.feetaxrate =  feetaxrate;
	}
	
	public Integer getFeetaxrate(){
		return feetaxrate;
	}

	public void setIsselectrent(String isselectrent){
		this.isselectrent =  isselectrent;
	}
	
	public String getIsselectrent(){
		return isselectrent;
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
	public String getTableName() {
		// TODO Auto-generated method stub
		return "rz_prjapply";
	}

	@Override
	public String getPkFieldName() {
		return "pkPrjapply";
	}

	@Override
	public String billNoFieldName() {
		return "prjCode";
	}
}