package com.imfbp.rz.domain.rzprjcontreqptmngr;


import java.io.Serializable;

public class RzPrjcontrEqptmngr  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrEqptmngr;
	//项目合同主键
	private String pkPrjcontr;
	//合同编号
	private String contrCode;
	//合同名称
	private String contrName;
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
	//供应商主键
	private String pkCustomerSupp;
	//租赁设备外键
	private String pkEqpt;
	//制造商
	private String mfg;
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
	//数量
	private Integer eqptNum;
	//设备总价(元)
	private Double eqptPriceTotal;
	//采购日期
	private String purcDate;
	//采购地址
	private String purcAddr;
	//采购发票号码
	private String purcNo;
	//交货日期
	private String deliveryDate;
	//交货地点
	private String deliveryAddr;
	//使用地址
	private String useAddr;
	//总使用寿命(年)
	private Integer usedLife;
	//立项日期
	private String prjapplyDate;
	//验收人
	private String checker;
	//项目余额(元)
	private Double itembal;
	//项目金额(元)
	private Double itemamt;
	//合同启租日期
	private String startLeaseDate;
	//合同结束日期
	private String endLeaseDate;
	//融资金额
	private Double financeamt;
	//租赁期限(月)
	private Integer leaseprd;
	//是否方便移动(Y 是 /N 否)
	private String ismove;
	//是否处置变现(Y 是 N 否)
	private String isliquidate;
	//总使用寿命（年）
	private Integer totallife;
	//尚可使用年限（年）
	private Integer restlife;
	//设备净值(元)
	private Double netVal;
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
	//录入人
	private String operator;
	//录入时间
	private String operatordatetime;
	//修改人
	private String modifor;
	//修改时间
	private String modifydatetime;
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
	

	public void setPkPrjcontrEqptmngr(String pkPrjcontrEqptmngr){
		this.pkPrjcontrEqptmngr =  pkPrjcontrEqptmngr;
	}
	
	public String getPkPrjcontrEqptmngr(){
		return pkPrjcontrEqptmngr;
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

	public void setPkCustomerSupp(String pkCustomerSupp){
		this.pkCustomerSupp =  pkCustomerSupp;
	}
	
	public String getPkCustomerSupp(){
		return pkCustomerSupp;
	}

	public void setPkEqpt(String pkEqpt){
		this.pkEqpt =  pkEqpt;
	}
	
	public String getPkEqpt(){
		return pkEqpt;
	}

	public void setMfg(String mfg){
		this.mfg =  mfg;
	}
	
	public String getMfg(){
		return mfg;
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

	public void setEqptNum(Integer eqptNum){
		this.eqptNum =  eqptNum;
	}
	
	public Integer getEqptNum(){
		return eqptNum;
	}

	public void setEqptPriceTotal(Double eqptPriceTotal){
		this.eqptPriceTotal =  eqptPriceTotal;
	}
	
	public Double getEqptPriceTotal(){
		return eqptPriceTotal;
	}

	public void setPurcDate(String purcDate){
		this.purcDate =  purcDate;
	}
	
	public String getPurcDate(){
		return purcDate;
	}

	public void setPurcAddr(String purcAddr){
		this.purcAddr =  purcAddr;
	}
	
	public String getPurcAddr(){
		return purcAddr;
	}

	public void setPurcNo(String purcNo){
		this.purcNo =  purcNo;
	}
	
	public String getPurcNo(){
		return purcNo;
	}

	public void setDeliveryDate(String deliveryDate){
		this.deliveryDate =  deliveryDate;
	}
	
	public String getDeliveryDate(){
		return deliveryDate;
	}

	public void setDeliveryAddr(String deliveryAddr){
		this.deliveryAddr =  deliveryAddr;
	}
	
	public String getDeliveryAddr(){
		return deliveryAddr;
	}

	public void setUseAddr(String useAddr){
		this.useAddr =  useAddr;
	}
	
	public String getUseAddr(){
		return useAddr;
	}

	public void setUsedLife(Integer usedLife){
		this.usedLife =  usedLife;
	}
	
	public Integer getUsedLife(){
		return usedLife;
	}

	public void setPrjapplyDate(String prjapplyDate){
		this.prjapplyDate =  prjapplyDate;
	}
	
	public String getPrjapplyDate(){
		return prjapplyDate;
	}

	public void setChecker(String checker){
		this.checker =  checker;
	}
	
	public String getChecker(){
		return checker;
	}

	public void setItembal(Double itembal){
		this.itembal =  itembal;
	}
	
	public Double getItembal(){
		return itembal;
	}

	public void setItemamt(Double itemamt){
		this.itemamt =  itemamt;
	}
	
	public Double getItemamt(){
		return itemamt;
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

	public void setFinanceamt(Double financeamt){
		this.financeamt =  financeamt;
	}
	
	public Double getFinanceamt(){
		return financeamt;
	}

	public void setLeaseprd(Integer leaseprd){
		this.leaseprd =  leaseprd;
	}
	
	public Integer getLeaseprd(){
		return leaseprd;
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

	public void setNetVal(Double netVal){
		this.netVal =  netVal;
	}
	
	public Double getNetVal(){
		return netVal;
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