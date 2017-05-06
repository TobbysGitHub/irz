package com.imfbp.rz.domain.rzadjint.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzAdjIntQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//调息管理主键
	private String pkAdjInt;
	//项目合同主键
	private String pkPrjcontr;
	//调息单号
	private String adjIntNo;
	//调息日期
	private String adjIntDate;
	//合同编号
	private String contrCode;
	//合同名称
	private String contrName;
	//客户名称外键
	private String pkCustomer;
	//项目经理
	private String pkUserManager;
	//0浮动 1 固定
	private Integer ratetype;
	//租赁期限(月)
	private Integer leaseprd;
	//当前租赁利率(%)
	private Double leaseRate;
	//基准利率(%)
	private Double baserate;
	//上下浮比例(%)
	private Integer floatpct;
	//调息类型：0 不调息、1 随基准调息、2 下一收租日调息
	private Integer intType;
	//报价利率(%)
	private String pricerate;
	//调息后租赁利率(%)
	private Double adjLeaseRate;
	//调息后基准利率(%)
	private Double adjBaserate;
	//人行调息日期
	private String pbcAdjIntDate;
	//调息生效日期
	private String startAdjIntDate;
	//调息幅度(%)
	private Double adjIntPer;
	//参照数据字典
	private String busitype;
	//调息后合同IRR(%)
	private Double adjIrr;
	//调息后总利息(元)
	private Double adjIntTotalAmt;
	//总利息差额(元)
	private Double diffIntTotalAmt;
	//项目金额(元)
	private Double itemamt;
	//首付款金额
	private Double firstpmtamt;
	//融资金额
	private Double financeamt;
	//合同金额(元)
	private Double contrAmt;
	//名义货价
	private Double nomPrice;
	//合同收益收益IRR(%)
	private Double irr;
	//合同变更版本
	private String contrVer;
	//租金计划变更版本
	private String rentPlanVer;
	//机构外键
	private String pkOrg;
	//部门外键
	private String pkDept;
	//总利息（元）
	private Double intTotalAmt;
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
	//时间戳
	private String ts;
	
	private String yeardays;
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
	
	private String startLeaseDate;
	
	private String prjstatus;
	

	public void setPkAdjInt(String pkAdjInt){
		this.pkAdjInt =  pkAdjInt;
	}
	
	public String getPkAdjInt(){
		return pkAdjInt;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setAdjIntNo(String adjIntNo){
		this.adjIntNo =  adjIntNo;
	}
	
	public String getAdjIntNo(){
		return adjIntNo;
	}

	public void setAdjIntDate(String adjIntDate){
		this.adjIntDate =  adjIntDate;
	}
	
	public String getAdjIntDate(){
		return adjIntDate;
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

	public void setRatetype(Integer ratetype){
		this.ratetype =  ratetype;
	}
	
	public Integer getRatetype(){
		return ratetype;
	}

	public void setLeaseprd(Integer leaseprd){
		this.leaseprd =  leaseprd;
	}
	
	public Integer getLeaseprd(){
		return leaseprd;
	}

	public void setLeaseRate(Double leaseRate){
		this.leaseRate =  leaseRate;
	}
	
	public Double getLeaseRate(){
		return leaseRate;
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

	public void setIntType(Integer intType){
		this.intType =  intType;
	}
	
	public Integer getIntType(){
		return intType;
	}

	public void setPricerate(String pricerate){
		this.pricerate =  pricerate;
	}
	
	public String getPricerate(){
		return pricerate;
	}

	public void setAdjLeaseRate(Double adjLeaseRate){
		this.adjLeaseRate =  adjLeaseRate;
	}
	
	public Double getAdjLeaseRate(){
		return adjLeaseRate;
	}

	public void setAdjBaserate(Double adjBaserate){
		this.adjBaserate =  adjBaserate;
	}
	
	public Double getAdjBaserate(){
		return adjBaserate;
	}

	public void setPbcAdjIntDate(String pbcAdjIntDate){
		this.pbcAdjIntDate =  pbcAdjIntDate;
	}
	
	public String getPbcAdjIntDate(){
		return pbcAdjIntDate;
	}

	public void setStartAdjIntDate(String startAdjIntDate){
		this.startAdjIntDate =  startAdjIntDate;
	}
	
	public String getStartAdjIntDate(){
		return startAdjIntDate;
	}

	public void setAdjIntPer(Double adjIntPer){
		this.adjIntPer =  adjIntPer;
	}
	
	public Double getAdjIntPer(){
		return adjIntPer;
	}

	public void setBusitype(String busitype){
		this.busitype =  busitype;
	}
	
	public String getBusitype(){
		return busitype;
	}

	public void setAdjIrr(Double adjIrr){
		this.adjIrr =  adjIrr;
	}
	
	public Double getAdjIrr(){
		return adjIrr;
	}

	public void setAdjIntTotalAmt(Double adjIntTotalAmt){
		this.adjIntTotalAmt =  adjIntTotalAmt;
	}
	
	public Double getAdjIntTotalAmt(){
		return adjIntTotalAmt;
	}

	public void setDiffIntTotalAmt(Double diffIntTotalAmt){
		this.diffIntTotalAmt =  diffIntTotalAmt;
	}
	
	public Double getDiffIntTotalAmt(){
		return diffIntTotalAmt;
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

	public void setContrAmt(Double contrAmt){
		this.contrAmt =  contrAmt;
	}
	
	public Double getContrAmt(){
		return contrAmt;
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

	public void setContrVer(String contrVer){
		this.contrVer =  contrVer;
	}
	
	public String getContrVer(){
		return contrVer;
	}

	public void setRentPlanVer(String rentPlanVer){
		this.rentPlanVer =  rentPlanVer;
	}
	
	public String getRentPlanVer(){
		return rentPlanVer;
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

	public void setIntTotalAmt(Double intTotalAmt){
		this.intTotalAmt =  intTotalAmt;
	}
	
	public Double getIntTotalAmt(){
		return intTotalAmt;
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

	public String getYeardays() {
		return yeardays;
	}

	public void setYeardays(String yeardays) {
		this.yeardays = yeardays;
	}

	public String getStartLeaseDate() {
		return startLeaseDate;
	}

	public void setStartLeaseDate(String startLeaseDate) {
		this.startLeaseDate = startLeaseDate;
	}

	public String getPrjstatus() {
		return prjstatus;
	}

	public void setPrjstatus(String prjstatus) {
		this.prjstatus = prjstatus;
	}
	
	
}




 

