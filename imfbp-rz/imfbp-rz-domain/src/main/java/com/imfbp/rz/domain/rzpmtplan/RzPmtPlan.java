package com.imfbp.rz.domain.rzpmtplan;


import java.io.Serializable;

public class RzPmtPlan  implements Serializable{

	private static final long serialVersionUID = 1L;

	//收付计划主键
	private String pkPmtPlan;
	//项目合同主键
	private String pkPrjcontr;
	//项目申请主键
	private String pkPrjapply;
	//项目评审主键
	private String pkPrjreview;
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
	

	public void setPkPmtPlan(String pkPmtPlan){
		this.pkPmtPlan =  pkPmtPlan;
	}
	
	public String getPkPmtPlan(){
		return pkPmtPlan;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setPkPrjapply(String pkPrjapply){
		this.pkPrjapply =  pkPrjapply;
	}
	
	public String getPkPrjapply(){
		return pkPrjapply;
	}

	public void setPkPrjreview(String pkPrjreview){
		this.pkPrjreview =  pkPrjreview;
	}
	
	public String getPkPrjreview(){
		return pkPrjreview;
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