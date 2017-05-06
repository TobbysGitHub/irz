package com.imfbp.rz.domain.rzprjcontrchg;


import java.io.Serializable;

import com.imfbp.rz.domain.pub.SuperHeadBean;

public class RzPrjcontrChg extends SuperHeadBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrChg;
	//项目合同主键
	private String pkPrjcontr;
	//项目评审主键
	private String pkPrjreview;
	//项目申请主键
	private String pkPrjapply;
	//项目编号
	private String prjCode;
	//项目名称
	private String prjName;
	//客户名称外键
	private String pkCustomer;
	//报价方案(报价测算主键)
	private String pkPricecal;
	//参照数据字典
	private String busitype;
	//租赁方式 0 直租、1 售后回租、2 其他
	private Integer leaseway;
	//租赁物类型 ： 0 有形动产、1 不动产
	private Integer leasetype;
	//项目来源，数据字典
	private String prjSrc;
	//项目经理
	private String pkUserManager;
	//申请部门
	private String pkDeptApply;
	//项目金额(元)
	private Double itemamt;
	//项目余额(元)
	private Double itembal;
	//是否投保 ：Y 是 N否
	private String isInsure;
	//是否有担保：Y 是 N否
	private String isGuar;
	//合同编号
	private String contrCode;
	//合同名称
	private String contrName;
	//合同年份
	private String contrYear;
	//合同金额(元)
	private Double contrAmt;
	//合同启租日期
	private String startLeaseDate;
	//合同结束日期
	private String endLeaseDate;
	//合同预计签订日期
	private String planSignedDate;
	//起租方式：0 投放即起租、1 先起租后投放
	private Integer startLeaseway;
	//币种主键
	private String pkCurrency;
	//合同类型：0 融资租赁合同、1 担保合同、2 买卖合同、3 抵质押合同、4 转让合同、5 租赁合同
	private String contrType;
	//租金付款方式：0 现金、1 网银支付、2汇款、3承兑汇票、4支付宝
	private Integer paymentmethod;
	//调息类型：0 不调息、1 随基准调息、2 下一收租日调息
	private Integer intType;
	//合同签订日期
	private String signedDate;
	//合同签订人
	private String pkUserSigned;
	//合同签订地址
	private String signedAddress;
	//合同签订描述
	private String signedRemark;
	//合同状态 :0 已生成、1 已生效、2 起租、3 已结清、4 已作废
	private Integer contrStatus;
	//是否开票 ：Y 是 N否
	private String isInvoice;
	//开票类型
	private String invoiceType;
	//名义货价
	private Double nomPrice;
	//报价单号
	private String priceno;
	//资产余值
	private Double residValue;
	//首付款金额
	private Double firstpmtamt;
	//融资金额
	private Double financeamt;
	//0浮动 1 固定
	private Integer ratetype;
	//基准利率(%)
	private Double baserate;
	//上下浮比例(%)
	private Integer floatpct;
	//报价利率(%)
	private Double pricerate;
	//租赁期限(月)
	private Integer leaseprd;
	//计划收租日
	private String planrentdate;
	//还款周期(月)
	private Integer reptcycle;
	//还款方式：0 等额租金法、1等额本息法、2平息法、3自由还款法
	private Integer reptway;
	//租金支付方式：0 期末支付、1 期初支付
	private Integer paymentway;
	//年化天数 360/365
	private Integer yeardays;
	//经营性每期租金
	private Double optamt;
	//客户保证金金额
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
	//合同收益收益IRR(%)
	private Double irr;
	//本金增值税率(%)：6%、11%、17%、零税率
	private Double prinVatrate;
	//利息增值税率(%)：6%、11%、17%、零税率
	private Double intVatrate;
	//手续费税率(%)：6%、11%、17%、零税率
	private Double feetaxrate;
	//服务费税率(%)：6%、11%、17%、零税率
	private Double sctaxrate;
	//出租人主键
	private String pkOrgLessor;
	//备注
	private String remark;
	//推荐人(客户主键)
	private String pkCustomerRcmd;
	//备注(推荐)
	private String remarkRcmd;
	//合同版本
	private String contrVer;
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
	//承租人（客户主键）
	private String pkCustomerLessee;
	//是否主承租人 Y是 N否
	private String isMainLessee;
	//承租人备注
	private String remarkLessee;
	

	public void setPkPrjcontrChg(String pkPrjcontrChg){
		this.pkPrjcontrChg =  pkPrjcontrChg;
	}
	
	public String getPkPrjcontrChg(){
		return pkPrjcontrChg;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setPkPrjreview(String pkPrjreview){
		this.pkPrjreview =  pkPrjreview;
	}
	
	public String getPkPrjreview(){
		return pkPrjreview;
	}

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

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setPkPricecal(String pkPricecal){
		this.pkPricecal =  pkPricecal;
	}
	
	public String getPkPricecal(){
		return pkPricecal;
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

	public void setItembal(Double itembal){
		this.itembal =  itembal;
	}
	
	public Double getItembal(){
		return itembal;
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

	public void setContrCode(String contrCode){
		this.contrCode =  contrCode;
	}
	
	public String getContrCode(){
		return contrCode;
	}

	public void setContrName(String contrName){
		this.contrName =  contrName;
	}
	
	public String getContrName(){
		return contrName;
	}

	public void setContrYear(String contrYear){
		this.contrYear =  contrYear;
	}
	
	public String getContrYear(){
		return contrYear;
	}

	public void setContrAmt(Double contrAmt){
		this.contrAmt =  contrAmt;
	}
	
	public Double getContrAmt(){
		return contrAmt;
	}

	public void setStartLeaseDate(String startLeaseDate){
		this.startLeaseDate =  startLeaseDate;
	}
	
	public String getStartLeaseDate(){
		return startLeaseDate;
	}

	public void setEndLeaseDate(String endLeaseDate){
		this.endLeaseDate =  endLeaseDate;
	}
	
	public String getEndLeaseDate(){
		return endLeaseDate;
	}

	public void setPlanSignedDate(String planSignedDate){
		this.planSignedDate =  planSignedDate;
	}
	
	public String getPlanSignedDate(){
		return planSignedDate;
	}

	public void setStartLeaseway(Integer startLeaseway){
		this.startLeaseway =  startLeaseway;
	}
	
	public Integer getStartLeaseway(){
		return startLeaseway;
	}

	public void setPkCurrency(String pkCurrency){
		this.pkCurrency =  pkCurrency;
	}
	
	public String getPkCurrency(){
		return pkCurrency;
	}

	public void setContrType(String contrType){
		this.contrType =  contrType;
	}
	
	public String getContrType(){
		return contrType;
	}

	public void setPaymentmethod(Integer paymentmethod){
		this.paymentmethod =  paymentmethod;
	}
	
	public Integer getPaymentmethod(){
		return paymentmethod;
	}

	public void setIntType(Integer intType){
		this.intType =  intType;
	}
	
	public Integer getIntType(){
		return intType;
	}

	public void setSignedDate(String signedDate){
		this.signedDate =  signedDate;
	}
	
	public String getSignedDate(){
		return signedDate;
	}

	public void setPkUserSigned(String pkUserSigned){
		this.pkUserSigned =  pkUserSigned;
	}
	
	public String getPkUserSigned(){
		return pkUserSigned;
	}

	public void setSignedAddress(String signedAddress){
		this.signedAddress =  signedAddress;
	}
	
	public String getSignedAddress(){
		return signedAddress;
	}

	public void setSignedRemark(String signedRemark){
		this.signedRemark =  signedRemark;
	}
	
	public String getSignedRemark(){
		return signedRemark;
	}

	public void setContrStatus(Integer contrStatus){
		this.contrStatus =  contrStatus;
	}
	
	public Integer getContrStatus(){
		return contrStatus;
	}

	public void setIsInvoice(String isInvoice){
		this.isInvoice =  isInvoice;
	}
	
	public String getIsInvoice(){
		return isInvoice;
	}

	public void setInvoiceType(String invoiceType){
		this.invoiceType =  invoiceType;
	}
	
	public String getInvoiceType(){
		return invoiceType;
	}

	public void setNomPrice(Double nomPrice){
		this.nomPrice =  nomPrice;
	}
	
	public Double getNomPrice(){
		return nomPrice;
	}

	public void setPriceno(String priceno){
		this.priceno =  priceno;
	}
	
	public String getPriceno(){
		return priceno;
	}

	public void setResidValue(Double residValue){
		this.residValue =  residValue;
	}
	
	public Double getResidValue(){
		return residValue;
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

	public void setPricerate(Double pricerate){
		this.pricerate =  pricerate;
	}
	
	public Double getPricerate(){
		return pricerate;
	}

	public void setLeaseprd(Integer leaseprd){
		this.leaseprd =  leaseprd;
	}
	
	public Integer getLeaseprd(){
		return leaseprd;
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

	public void setYeardays(Integer yeardays){
		this.yeardays =  yeardays;
	}
	
	public Integer getYeardays(){
		return yeardays;
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

	public void setPrinVatrate(Double prinVatrate){
		this.prinVatrate =  prinVatrate;
	}
	
	public Double getPrinVatrate(){
		return prinVatrate;
	}

	public void setIntVatrate(Double intVatrate){
		this.intVatrate =  intVatrate;
	}
	
	public Double getIntVatrate(){
		return intVatrate;
	}

	public void setFeetaxrate(Double feetaxrate){
		this.feetaxrate =  feetaxrate;
	}
	
	public Double getFeetaxrate(){
		return feetaxrate;
	}

	public void setSctaxrate(Double sctaxrate){
		this.sctaxrate =  sctaxrate;
	}
	
	public Double getSctaxrate(){
		return sctaxrate;
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

	public void setPkCustomerRcmd(String pkCustomerRcmd){
		this.pkCustomerRcmd =  pkCustomerRcmd;
	}
	
	public String getPkCustomerRcmd(){
		return pkCustomerRcmd;
	}

	public void setRemarkRcmd(String remarkRcmd){
		this.remarkRcmd =  remarkRcmd;
	}
	
	public String getRemarkRcmd(){
		return remarkRcmd;
	}

	public void setContrVer(String contrVer){
		this.contrVer =  contrVer;
	}
	
	public String getContrVer(){
		return contrVer;
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

	public void setPkCustomerLessee(String pkCustomerLessee){
		this.pkCustomerLessee =  pkCustomerLessee;
	}
	
	public String getPkCustomerLessee(){
		return pkCustomerLessee;
	}

	public void setIsMainLessee(String isMainLessee){
		this.isMainLessee =  isMainLessee;
	}
	
	public String getIsMainLessee(){
		return isMainLessee;
	}

	public void setRemarkLessee(String remarkLessee){
		this.remarkLessee =  remarkLessee;
	}
	
	public String getRemarkLessee(){
		return remarkLessee;
	}

	@Override
	public String billNoFieldName() {
		return "pkPrjcontrChg";
	}

	@Override
	public String getPkFieldName() {
		return "pkPrjcontrChg";
	}

	@Override
	public String getTableName() {
		return "rz_prjcontr_chg";
	}
}