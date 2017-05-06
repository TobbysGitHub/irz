package com.imfbp.rz.domain.rzprjreviewlessee;


import java.io.Serializable;

public class RzPrjreviewLessee  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjreviewLessee;
	//项目评审主键  
	private String pkPrjreview;
	//承租人（客户主键）
	private String pkCustomer;
	//是否主承租人 Y是 N否
	private String isMainLessee;
	//备注
	private String remark;
	
	private String enterpriseLegalPerson;
	
	private String enterprisePersonIdentificationNo;
	
	private String enterpriseAddr;
	
	private String enterprisePostcode;
	
	private String enterpriseContacts;
	
	private String enterpriseTelphone;
	
	private String enterpriseFax;
	
	private String enterpriseMobilephone;
	
	private String enterpriseMail;
	
	private String userName;
	
	private String enterpriseMnemonic;
	
	private String userIdentificationNo;
	
	private String enterpriseCode;
	

	public void setPkPrjreviewLessee(String pkPrjreviewLessee){
		this.pkPrjreviewLessee =  pkPrjreviewLessee;
	}
	
	public String getPkPrjreviewLessee(){
		return pkPrjreviewLessee;
	}

	public void setPkPrjreview(String pkPrjreview){
		this.pkPrjreview =  pkPrjreview;
	}
	
	public String getPkPrjreview(){
		return pkPrjreview;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setIsMainLessee(String isMainLessee){
		this.isMainLessee =  isMainLessee;
	}
	
	public String getIsMainLessee(){
		return isMainLessee;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public String getEnterpriseLegalPerson() {
		return enterpriseLegalPerson;
	}

	public void setEnterpriseLegalPerson(String enterpriseLegalPerson) {
		this.enterpriseLegalPerson = enterpriseLegalPerson;
	}

	public String getEnterprisePersonIdentificationNo() {
		return enterprisePersonIdentificationNo;
	}

	public void setEnterprisePersonIdentificationNo(String enterprisePersonIdentificationNo) {
		this.enterprisePersonIdentificationNo = enterprisePersonIdentificationNo;
	}

	public String getEnterpriseAddr() {
		return enterpriseAddr;
	}

	public void setEnterpriseAddr(String enterpriseAddr) {
		this.enterpriseAddr = enterpriseAddr;
	}

	public String getEnterprisePostcode() {
		return enterprisePostcode;
	}

	public void setEnterprisePostcode(String enterprisePostcode) {
		this.enterprisePostcode = enterprisePostcode;
	}

	public String getEnterpriseContacts() {
		return enterpriseContacts;
	}

	public void setEnterpriseContacts(String enterpriseContacts) {
		this.enterpriseContacts = enterpriseContacts;
	}

	public String getEnterpriseTelphone() {
		return enterpriseTelphone;
	}

	public void setEnterpriseTelphone(String enterpriseTelphone) {
		this.enterpriseTelphone = enterpriseTelphone;
	}

	public String getEnterpriseFax() {
		return enterpriseFax;
	}

	public void setEnterpriseFax(String enterpriseFax) {
		this.enterpriseFax = enterpriseFax;
	}

	public String getEnterpriseMobilephone() {
		return enterpriseMobilephone;
	}

	public void setEnterpriseMobilephone(String enterpriseMobilephone) {
		this.enterpriseMobilephone = enterpriseMobilephone;
	}

	public String getEnterpriseMail() {
		return enterpriseMail;
	}

	public void setEnterpriseMail(String enterpriseMail) {
		this.enterpriseMail = enterpriseMail;
	}

	public String getEnterpriseMnemonic() {
		return enterpriseMnemonic;
	}

	public void setEnterpriseMnemonic(String enterpriseMnemonic) {
		this.enterpriseMnemonic = enterpriseMnemonic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIdentificationNo() {
		return userIdentificationNo;
	}

	public void setUserIdentificationNo(String userIdentificationNo) {
		this.userIdentificationNo = userIdentificationNo;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	
	
}