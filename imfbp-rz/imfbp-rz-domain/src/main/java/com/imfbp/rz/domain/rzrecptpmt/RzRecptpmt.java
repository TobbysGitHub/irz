package com.imfbp.rz.domain.rzrecptpmt;


import java.io.Serializable;

public class RzRecptpmt  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkRecptpmt;
	//编码
	private String code;
	//名称
	private String name;
	//1 租赁业务 2融资业务 3 资金业务
	private String busitype;
	//收付类型 0 收款、1 付款
	private Integer pmttype;
	//0 流入 1 流出 2 无
	private Integer cashflowdir;
	//1 供应商 2保险人 3承租人 4推荐商 5 担保人
	private String pmtrival;
	//备注
	private String remark;
	//启用时间
	private String enabledate;
	//0 未启用 1 已启用
	private Integer enablestate;
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
	

	public void setPkRecptpmt(String pkRecptpmt){
		this.pkRecptpmt =  pkRecptpmt;
	}
	
	public String getPkRecptpmt(){
		return pkRecptpmt;
	}

	public void setCode(String code){
		this.code =  code;
	}
	
	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name =  name;
	}
	
	public String getName(){
		return name;
	}

	public void setBusitype(String busitype){
		this.busitype =  busitype;
	}
	
	public String getBusitype(){
		return busitype;
	}

	public void setPmttype(Integer pmttype){
		this.pmttype =  pmttype;
	}
	
	public Integer getPmttype(){
		return pmttype;
	}

	public void setCashflowdir(Integer cashflowdir){
		this.cashflowdir =  cashflowdir;
	}
	
	public Integer getCashflowdir(){
		return cashflowdir;
	}

	public void setPmtrival(String pmtrival){
		this.pmtrival =  pmtrival;
	}
	
	public String getPmtrival(){
		return pmtrival;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public void setEnabledate(String enabledate){
		this.enabledate =  enabledate;
	}
	
	public String getEnabledate(){
		return enabledate;
	}

	public void setEnablestate(Integer enablestate){
		this.enablestate =  enablestate;
	}
	
	public Integer getEnablestate(){
		return enablestate;
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