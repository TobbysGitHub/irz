package com.imfbp.rz.domain.rzprjcontrchgins;


import java.io.Serializable;

public class RzPrjcontrChgIns  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrChgIns;
	//立项申请主键
	private String pkPrjcontrChg;
	//保单号
	private String insNo;
	//合同类型：参照设备分类字典档案数据
	private String insContrType;
	//保险公司
	private String insCorp;
	//险种
	private String insType;
	//支付方
	private String pkCustomerPay;
	//支付金额(元)
	private Double payAmt;
	//保额(元)
	private Double insAmt;
	//受益人
	private String pkUserBen;
	//制造商
	private String startDate;
	//保险期限(月)
	private Integer insPrd;
	//保险结束日期
	private String endDate;
	//
	private String remark;
	

	public void setPkPrjcontrChgIns(String pkPrjcontrChgIns){
		this.pkPrjcontrChgIns =  pkPrjcontrChgIns;
	}
	
	public String getPkPrjcontrChgIns(){
		return pkPrjcontrChgIns;
	}

	public void setPkPrjcontrChg(String pkPrjcontrChg){
		this.pkPrjcontrChg =  pkPrjcontrChg;
	}
	
	public String getPkPrjcontrChg(){
		return pkPrjcontrChg;
	}

	public void setInsNo(String insNo){
		this.insNo =  insNo;
	}
	
	public String getInsNo(){
		return insNo;
	}

	public void setInsContrType(String insContrType){
		this.insContrType =  insContrType;
	}
	
	public String getInsContrType(){
		return insContrType;
	}

	public void setInsCorp(String insCorp){
		this.insCorp =  insCorp;
	}
	
	public String getInsCorp(){
		return insCorp;
	}

	public void setInsType(String insType){
		this.insType =  insType;
	}
	
	public String getInsType(){
		return insType;
	}

	public void setPkCustomerPay(String pkCustomerPay){
		this.pkCustomerPay =  pkCustomerPay;
	}
	
	public String getPkCustomerPay(){
		return pkCustomerPay;
	}

	public void setPayAmt(Double payAmt){
		this.payAmt =  payAmt;
	}
	
	public Double getPayAmt(){
		return payAmt;
	}

	public void setInsAmt(Double insAmt){
		this.insAmt =  insAmt;
	}
	
	public Double getInsAmt(){
		return insAmt;
	}

	public void setPkUserBen(String pkUserBen){
		this.pkUserBen =  pkUserBen;
	}
	
	public String getPkUserBen(){
		return pkUserBen;
	}

	public void setStartDate(String startDate){
		this.startDate =  startDate;
	}
	
	public String getStartDate(){
		return startDate;
	}

	public void setInsPrd(Integer insPrd){
		this.insPrd =  insPrd;
	}
	
	public Integer getInsPrd(){
		return insPrd;
	}

	public void setEndDate(String endDate){
		this.endDate =  endDate;
	}
	
	public String getEndDate(){
		return endDate;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}
}