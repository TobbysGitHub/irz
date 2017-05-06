package com.imfbp.rz.domain.rzplanchange;


import java.io.Serializable;

import com.imfbp.rz.domain.pub.SuperHeadBean;

public class RzPlanChange  extends SuperHeadBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPlanChange;
	//项目合同主键
	private String pkPrjcontr;
	//计划变更单号
	private String changeNo;
	//变更日期
	private String changeDate;
	//计划变更类型：0 提前偿还、1 提前结清、2 缩期、3 展期
	private Integer changeType;
	//合同编号
	private String contrCode;
	//合同启租日期
	private String startLeaseDate;
	//合同结束日期
	private String endLeaseDate;
	//合同名称
	private String contrName;
	//客户名称外键
	private String pkCustomer;
	//项目经理
	private String pkUserManager;
	//租赁期限(月)
	private Integer leaseprd;
	//提前偿还率(%)
	private Double prinReturnRate;
	//本次应偿还本金
	private Double curRptPrin;
	//本次应偿还利息
	private Double curRptInt;
	//本次应偿还手续费
	private Double curRptFee;
	//本次应偿还金额
	private Double curRptAmt;
	//提前偿还手续费率(%)
	private Double prinReturnFeeRate;
	//剩余核销其次数
	private Integer residueCheckNum;
	//缩期后剩余期次数
	private Integer contractionResiNum;
	//展期后剩余其次数
	private Integer extResiNum;
	//变更生效日期
	private String effectiveDate;
	//变更备注
	private String remark;
	//本金金额
	private Double prinAmt;
	//租金金额(元)
	private Double rentAmt;
	//应收未收罚息
	private Double unpmtInt;
	//待核销期次
	private Integer waitCheckNum;
	//参照数据字典
	private String busitype;
	//项目金额
	private Double itemamt;
	//首付款金额
	private Double firstpmtamt;
	//当前总利息
	private Double curIntTotal;
	//报价利率(%)
	private Double pricerate;
	//项目余额(元)
	private Double itembal;
	//名义货价
	private Double nomPrice;
	//合同收益收益IRR(%)
	private Double irr;
	//变更后合同IRR(%)
	private Double changeIrr;
	//变更后总利息
	private Double changeIntTotal;
	//变更前租金合计
	private Double rentTotal;
	//变更后租金合计
	private Double rentTotalChange;
	//总利息差额
	private Double diffIntTotal;
	//融资金额
	private Double financeamt;
	//合同金额(元)
	private Double contrAmt;
	//合同变更版本
	private String contrVer;
	//计划变更版本
	private String planChangeVer;
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
	//
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
	

	public void setPkPlanChange(String pkPlanChange){
		this.pkPlanChange =  pkPlanChange;
	}
	
	public String getPkPlanChange(){
		return pkPlanChange;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setChangeNo(String changeNo){
		this.changeNo =  changeNo;
	}
	
	public String getChangeNo(){
		return changeNo;
	}

	public void setChangeDate(String changeDate){
		this.changeDate =  changeDate;
	}
	
	public String getChangeDate(){
		return changeDate;
	}

	public void setChangeType(Integer changeType){
		this.changeType =  changeType;
	}
	
	public Integer getChangeType(){
		return changeType;
	}

	public void setContrCode(String contrCode){
		this.contrCode =  contrCode;
	}
	
	public String getContrCode(){
		return contrCode;
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

	public void setContrName(String contrName){
		this.contrName =  contrName;
	}
	
	public String getContrName(){
		return contrName;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setPkUserManager(String pkUserManager){
		this.pkUserManager =  pkUserManager;
	}
	
	public String getPkUserManager(){
		return pkUserManager;
	}

	public void setLeaseprd(Integer leaseprd){
		this.leaseprd =  leaseprd;
	}
	
	public Integer getLeaseprd(){
		return leaseprd;
	}

	public void setPrinReturnRate(Double prinReturnRate){
		this.prinReturnRate =  prinReturnRate;
	}
	
	public Double getPrinReturnRate(){
		return prinReturnRate;
	}

	public void setCurRptPrin(Double curRptPrin){
		this.curRptPrin =  curRptPrin;
	}
	
	public Double getCurRptPrin(){
		return curRptPrin;
	}

	public void setCurRptInt(Double curRptInt){
		this.curRptInt =  curRptInt;
	}
	
	public Double getCurRptInt(){
		return curRptInt;
	}

	public void setCurRptFee(Double curRptFee){
		this.curRptFee =  curRptFee;
	}
	
	public Double getCurRptFee(){
		return curRptFee;
	}

	public void setCurRptAmt(Double curRptAmt){
		this.curRptAmt =  curRptAmt;
	}
	
	public Double getCurRptAmt(){
		return curRptAmt;
	}

	public void setPrinReturnFeeRate(Double prinReturnFeeRate){
		this.prinReturnFeeRate =  prinReturnFeeRate;
	}
	
	public Double getPrinReturnFeeRate(){
		return prinReturnFeeRate;
	}

	public void setResidueCheckNum(Integer residueCheckNum){
		this.residueCheckNum =  residueCheckNum;
	}
	
	public Integer getResidueCheckNum(){
		return residueCheckNum;
	}

	public void setContractionResiNum(Integer contractionResiNum){
		this.contractionResiNum =  contractionResiNum;
	}
	
	public Integer getContractionResiNum(){
		return contractionResiNum;
	}

	public void setExtResiNum(Integer extResiNum){
		this.extResiNum =  extResiNum;
	}
	
	public Integer getExtResiNum(){
		return extResiNum;
	}

	public void setEffectiveDate(String effectiveDate){
		this.effectiveDate =  effectiveDate;
	}
	
	public String getEffectiveDate(){
		return effectiveDate;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public void setPrinAmt(Double prinAmt){
		this.prinAmt =  prinAmt;
	}
	
	public Double getPrinAmt(){
		return prinAmt;
	}

	public void setRentAmt(Double rentAmt){
		this.rentAmt =  rentAmt;
	}
	
	public Double getRentAmt(){
		return rentAmt;
	}

	public void setUnpmtInt(Double unpmtInt){
		this.unpmtInt =  unpmtInt;
	}
	
	public Double getUnpmtInt(){
		return unpmtInt;
	}

	public void setWaitCheckNum(Integer waitCheckNum){
		this.waitCheckNum =  waitCheckNum;
	}
	
	public Integer getWaitCheckNum(){
		return waitCheckNum;
	}

	public void setBusitype(String busitype){
		this.busitype =  busitype;
	}
	
	public String getBusitype(){
		return busitype;
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

	public void setCurIntTotal(Double curIntTotal){
		this.curIntTotal =  curIntTotal;
	}
	
	public Double getCurIntTotal(){
		return curIntTotal;
	}

	public void setPricerate(Double pricerate){
		this.pricerate =  pricerate;
	}
	
	public Double getPricerate(){
		return pricerate;
	}

	public void setItembal(Double itembal){
		this.itembal =  itembal;
	}
	
	public Double getItembal(){
		return itembal;
	}

	public void setNomPrice(Double nomPrice){
		this.nomPrice =  nomPrice;
	}
	
	public Double getNomPrice(){
		return nomPrice;
	}

	public void setIrr(Double irr){
		this.irr =  irr;
	}
	
	public Double getIrr(){
		return irr;
	}

	public void setChangeIrr(Double changeIrr){
		this.changeIrr =  changeIrr;
	}
	
	public Double getChangeIrr(){
		return changeIrr;
	}

	public void setChangeIntTotal(Double changeIntTotal){
		this.changeIntTotal =  changeIntTotal;
	}
	
	public Double getChangeIntTotal(){
		return changeIntTotal;
	}

	public void setRentTotal(Double rentTotal){
		this.rentTotal =  rentTotal;
	}
	
	public Double getRentTotal(){
		return rentTotal;
	}

	public void setRentTotalChange(Double rentTotalChange){
		this.rentTotalChange =  rentTotalChange;
	}
	
	public Double getRentTotalChange(){
		return rentTotalChange;
	}

	public void setDiffIntTotal(Double diffIntTotal){
		this.diffIntTotal =  diffIntTotal;
	}
	
	public Double getDiffIntTotal(){
		return diffIntTotal;
	}

	public void setFinanceamt(Double financeamt){
		this.financeamt =  financeamt;
	}
	
	public Double getFinanceamt(){
		return financeamt;
	}

	public void setContrAmt(Double contrAmt){
		this.contrAmt =  contrAmt;
	}
	
	public Double getContrAmt(){
		return contrAmt;
	}

	public void setContrVer(String contrVer){
		this.contrVer =  contrVer;
	}
	
	public String getContrVer(){
		return contrVer;
	}

	public void setPlanChangeVer(String planChangeVer){
		this.planChangeVer =  planChangeVer;
	}
	
	public String getPlanChangeVer(){
		return planChangeVer;
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
		return "changeNo";
	}

	@Override
	public String getPkFieldName() {
		return "pkPlanChange";
	}

	@Override
	public String getTableName() {
		return "rz_plan_change";
	}
}