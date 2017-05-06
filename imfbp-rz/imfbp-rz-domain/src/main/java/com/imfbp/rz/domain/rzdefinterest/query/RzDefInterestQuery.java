package com.imfbp.rz.domain.rzdefinterest.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzDefInterestQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkDefInterest;
	//合同主键
	private String pkPrjcontr;
	//合同编号
	private String contrCode;
	//合同名称
	private String contrName;
	//项目编号
	private String prjCode;
	//项目名称
	private String prjName;
	//项目余额(元)
	private Double itembal;
	//罚息单号
	private String defIntNo;
	//客户名称主键
	private String pkCustomer;
	//罚息日期
	private String defIntDate;
	//合同金额(元)
	private Double contrAmt;
	//合同余额(元)
	private Double contrBal;
	//项目金额(元)
	private Double itemamt;
	//当前租金余额
	private Double curRentBal;
	//合同剩余本金(元)
	private Double contrPrinBal;
	//租赁利率(%)
	private Double pricerate;
	//当前租赁利率(%)
	private Double curPricerate;
	//还款宽限期
	private Integer rpmtGrace;
	//逾期金额(元)
	private Double overdueAmt;
	//逾期天数
	private Integer overdueDays;
	//应收罚息金额((元)
	private Double receivableDefIntAmt;
	//罚息应还款日期
	private String defIntRmptDate;
	//合同启租日期
	private String startLeaseDate;
	//合同结束日期
	private String endLeaseDate;
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
	//接收子表传来的数据
	private String interestLease;
	//业务类型
	private String busitype;
	

	public void setPkDefInterest(String pkDefInterest){
		this.pkDefInterest =  pkDefInterest;
	}
	
	public String getPkDefInterest(){
		return pkDefInterest;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
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

	public void setItembal(Double itembal){
		this.itembal =  itembal;
	}
	
	public Double getItembal(){
		return itembal;
	}

	public void setDefIntNo(String defIntNo){
		this.defIntNo =  defIntNo;
	}
	
	public String getDefIntNo(){
		return defIntNo;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setDefIntDate(String defIntDate){
		this.defIntDate =  defIntDate;
	}
	
	public String getDefIntDate(){
		return defIntDate;
	}

	public void setContrAmt(Double contrAmt){
		this.contrAmt =  contrAmt;
	}
	
	public Double getContrAmt(){
		return contrAmt;
	}

	public void setContrBal(Double contrBal){
		this.contrBal =  contrBal;
	}
	
	public Double getContrBal(){
		return contrBal;
	}

	public void setItemamt(Double itemamt){
		this.itemamt =  itemamt;
	}
	
	public Double getItemamt(){
		return itemamt;
	}

	public void setCurRentBal(Double curRentBal){
		this.curRentBal =  curRentBal;
	}
	
	public Double getCurRentBal(){
		return curRentBal;
	}

	public void setContrPrinBal(Double contrPrinBal){
		this.contrPrinBal =  contrPrinBal;
	}
	
	public Double getContrPrinBal(){
		return contrPrinBal;
	}

	public void setPricerate(Double pricerate){
		this.pricerate =  pricerate;
	}
	
	public Double getPricerate(){
		return pricerate;
	}

	public void setCurPricerate(Double curPricerate){
		this.curPricerate =  curPricerate;
	}
	
	public Double getCurPricerate(){
		return curPricerate;
	}

	public void setRpmtGrace(Integer rpmtGrace){
		this.rpmtGrace =  rpmtGrace;
	}
	
	public Integer getRpmtGrace(){
		return rpmtGrace;
	}

	public void setOverdueAmt(Double overdueAmt){
		this.overdueAmt =  overdueAmt;
	}
	
	public Double getOverdueAmt(){
		return overdueAmt;
	}

	public void setOverdueDays(Integer overdueDays){
		this.overdueDays =  overdueDays;
	}
	
	public Integer getOverdueDays(){
		return overdueDays;
	}

	public void setReceivableDefIntAmt(Double receivableDefIntAmt){
		this.receivableDefIntAmt =  receivableDefIntAmt;
	}
	
	public Double getReceivableDefIntAmt(){
		return receivableDefIntAmt;
	}

	public void setDefIntRmptDate(String defIntRmptDate){
		this.defIntRmptDate =  defIntRmptDate;
	}
	
	public String getDefIntRmptDate(){
		return defIntRmptDate;
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

	public String getInterestLease() {
		return interestLease;
	}

	public void setInterestLease(String interestLease) {
		this.interestLease = interestLease;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}
	
}




 

