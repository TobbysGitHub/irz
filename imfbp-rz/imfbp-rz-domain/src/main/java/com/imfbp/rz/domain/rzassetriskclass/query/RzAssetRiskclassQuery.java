package com.imfbp.rz.domain.rzassetriskclass.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzAssetRiskclassQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkAssetRiskclass;
	//
	private String billNo;
	//项目合同主键
	private String pkPrjcontr;
	//合同编号
	private String contrCode;
	//合同名称
	private String contrName;
	//客户名称外键
	private String pkCustomer;
	//当前风险分类
	private String curRiskclass;
	//变更后风险分类
	private String changeRiskclass;
	//当前变更次数
	private Integer changeTimes;
	//项目经理
	private String pkUserManager;
	//是否列入风险预警(Y/N)
	private String isRiskwarin;
	//分类变更人
	private String changePerson;
	//分类变更日期
	private String changeDate;
	//分类变更原因
	private String changeRemark;
	//项目申请主键
	private String pkPrjapply;
	//项目编号
	private String prjCode;
	//项目名称
	private String prjName;
	//立项日期
	private String prjapplyDate;
	//合同启租日期
	private String startLeaseDate;
	//合同结束日期
	private String endLeaseDate;
	//应收本金
	private Double receivableAmt;
	//合同本金余额
	private Double curPrinBal;
	//应收租金
	private Double receivableRent;
	//合同租金余额
	private Double curRentBal;
	//项目余额(元)
	private Double itembal;
	//逾期次数
	private Integer overdueTimes;
	//实际减免罚息
	private Double derateDefInt;
	//预计回款日期
	private String planReturndate;
	//应收未收罚息
	private Double accruedDefInt;
	//累计应罚息金额
	private Double receivablDefIntTotal;
	//备注
	private String remark;
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
	//时间戳
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
	

	public void setPkAssetRiskclass(String pkAssetRiskclass){
		this.pkAssetRiskclass =  pkAssetRiskclass;
	}
	
	public String getPkAssetRiskclass(){
		return pkAssetRiskclass;
	}

	public void setBillNo(String billNo){
		this.billNo =  billNo;
	}
	
	public String getBillNo(){
		return billNo;
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

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setCurRiskclass(String curRiskclass){
		this.curRiskclass =  curRiskclass;
	}
	
	public String getCurRiskclass(){
		return curRiskclass;
	}

	public void setChangeRiskclass(String changeRiskclass){
		this.changeRiskclass =  changeRiskclass;
	}
	
	public String getChangeRiskclass(){
		return changeRiskclass;
	}

	public void setChangeTimes(Integer changeTimes){
		this.changeTimes =  changeTimes;
	}
	
	public Integer getChangeTimes(){
		return changeTimes;
	}

	public void setPkUserManager(String pkUserManager){
		this.pkUserManager =  pkUserManager;
	}
	
	public String getPkUserManager(){
		return pkUserManager;
	}

	public void setIsRiskwarin(String isRiskwarin){
		this.isRiskwarin =  isRiskwarin;
	}
	
	public String getIsRiskwarin(){
		return isRiskwarin;
	}

	public void setChangePerson(String changePerson){
		this.changePerson =  changePerson;
	}
	
	public String getChangePerson(){
		return changePerson;
	}

	public void setChangeDate(String changeDate){
		this.changeDate =  changeDate;
	}
	
	public String getChangeDate(){
		return changeDate;
	}

	public void setChangeRemark(String changeRemark){
		this.changeRemark =  changeRemark;
	}
	
	public String getChangeRemark(){
		return changeRemark;
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

	public void setPrjapplyDate(String prjapplyDate){
		this.prjapplyDate =  prjapplyDate;
	}
	
	public String getPrjapplyDate(){
		return prjapplyDate;
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

	public void setReceivableAmt(Double receivableAmt){
		this.receivableAmt =  receivableAmt;
	}
	
	public Double getReceivableAmt(){
		return receivableAmt;
	}

	public void setCurPrinBal(Double curPrinBal){
		this.curPrinBal =  curPrinBal;
	}
	
	public Double getCurPrinBal(){
		return curPrinBal;
	}

	public void setReceivableRent(Double receivableRent){
		this.receivableRent =  receivableRent;
	}
	
	public Double getReceivableRent(){
		return receivableRent;
	}

	public void setCurRentBal(Double curRentBal){
		this.curRentBal =  curRentBal;
	}
	
	public Double getCurRentBal(){
		return curRentBal;
	}

	public void setItembal(Double itembal){
		this.itembal =  itembal;
	}
	
	public Double getItembal(){
		return itembal;
	}

	public void setOverdueTimes(Integer overdueTimes){
		this.overdueTimes =  overdueTimes;
	}
	
	public Integer getOverdueTimes(){
		return overdueTimes;
	}

	public void setDerateDefInt(Double derateDefInt){
		this.derateDefInt =  derateDefInt;
	}
	
	public Double getDerateDefInt(){
		return derateDefInt;
	}

	public void setPlanReturndate(String planReturndate){
		this.planReturndate =  planReturndate;
	}
	
	public String getPlanReturndate(){
		return planReturndate;
	}

	public void setAccruedDefInt(Double accruedDefInt){
		this.accruedDefInt =  accruedDefInt;
	}
	
	public Double getAccruedDefInt(){
		return accruedDefInt;
	}

	public void setReceivablDefIntTotal(Double receivablDefIntTotal){
		this.receivablDefIntTotal =  receivablDefIntTotal;
	}
	
	public Double getReceivablDefIntTotal(){
		return receivablDefIntTotal;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
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
}




 

