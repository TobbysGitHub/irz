package com.imfbp.rz.domain.rzeqpt.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzEqptQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkEqpt;
	//供应商主键（客户主键）
	private String pkCustomer;
	//制造商
	private String mfg;
	//设备编码
	private String eqptCode;
	//设备名称
	private String eqptName;
	//参照设备分类字典档案数据
	private String eqptType;
	//设备品牌
	private String eqptBrand;
	//设备型号
	private String eqptVer;
	//出厂编码
	private String mfgNo;
	//出厂日期
	private String mfgDate;
	//设备价格
	private Double eqptPrice;
	//设备评估价值（元）
	private Double assessPrice;
	//Y 是 N 否
	private String ismove;
	//Y 是 N 否
	private String isliquidate;
	//总使用寿命（年）
	private Integer totallife;
	//尚可使用年限（年）
	private Integer restlife;
	//备注
	private String remark;
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
	private String enabledate;
	//
	private Integer enablestate;
	

	public void setPkEqpt(String pkEqpt){
		this.pkEqpt =  pkEqpt;
	}
	
	public String getPkEqpt(){
		return pkEqpt;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setMfg(String mfg){
		this.mfg =  mfg;
	}
	
	public String getMfg(){
		return mfg;
	}

	public void setEqptCode(String eqptCode){
		this.eqptCode =  eqptCode;
	}
	
	public String getEqptCode(){
		return eqptCode;
	}

	public void setEqptName(String eqptName){
		this.eqptName =  eqptName;
	}
	
	public String getEqptName(){
		return eqptName;
	}

	public void setEqptType(String eqptType){
		this.eqptType =  eqptType;
	}
	
	public String getEqptType(){
		return eqptType;
	}

	public void setEqptBrand(String eqptBrand){
		this.eqptBrand =  eqptBrand;
	}
	
	public String getEqptBrand(){
		return eqptBrand;
	}

	public void setEqptVer(String eqptVer){
		this.eqptVer =  eqptVer;
	}
	
	public String getEqptVer(){
		return eqptVer;
	}

	public void setMfgNo(String mfgNo){
		this.mfgNo =  mfgNo;
	}
	
	public String getMfgNo(){
		return mfgNo;
	}

	public void setMfgDate(String mfgDate){
		this.mfgDate =  mfgDate;
	}
	
	public String getMfgDate(){
		return mfgDate;
	}

	public void setEqptPrice(Double eqptPrice){
		this.eqptPrice =  eqptPrice;
	}
	
	public Double getEqptPrice(){
		return eqptPrice;
	}

	public void setAssessPrice(Double assessPrice){
		this.assessPrice =  assessPrice;
	}
	
	public Double getAssessPrice(){
		return assessPrice;
	}

	public void setIsmove(String ismove){
		this.ismove =  ismove;
	}
	
	public String getIsmove(){
		return ismove;
	}

	public void setIsliquidate(String isliquidate){
		this.isliquidate =  isliquidate;
	}
	
	public String getIsliquidate(){
		return isliquidate;
	}

	public void setTotallife(Integer totallife){
		this.totallife =  totallife;
	}
	
	public Integer getTotallife(){
		return totallife;
	}

	public void setRestlife(Integer restlife){
		this.restlife =  restlife;
	}
	
	public Integer getRestlife(){
		return restlife;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
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
}




 

