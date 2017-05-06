package com.imfbp.rz.domain.rzcontrtally.query;

import java.io.Serializable;
import java.util.List;

import com.platform.common.utils.query.BaseQuery;

public class RzContrTallyQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkContrTally;
	//操作序号
	private Integer operSeq;
	//合同主键
	private String pkPrjcontr;
	//项目经理
	private String pkUserManager;
	//原合同主键
	private String pkPrjcontrOri;
	//合同编号
	private String contrCode;
	//项目申请主键
	private String pkPrjapply;
	//合同名称
	private String contrName;
	//项目编号
	private String prjCode;
	//项目名称
	private String prjName;
	//调息类型：0 不调息、1 随基准调息、2 下一收租日调息
	private Integer intType;
	//还款方式：0 等额租金法、1等额本息法、2平息法、3自由还款法
	private Integer reptway;
	//计划收租日
	private String planrentdate;
	//还款周期(月)
	private Integer reptcycle;
	//客户名称主键
	private String pkCustomer;
	//租赁方式 0 直租、1 售后回租、2 其他
	private Integer leaseway;
	//币种主键
	private String pkCurrency;
	//合同启租日期
	private String startLeaseDate;
	//合同结束日期
	private String endLeaseDate;
	//实际合同到期日
	private String realEndDate;
	//合同收益收益IRR(%)
	private Double irr;
	//当前合同收益率IRR(%)
	private Double curIrr;
	//融资金额
	private Double financeamt;
	//租赁期限(月)
	private Integer leaseprd;
	//项目金额(元)
	private Double itemamt;
	//项目余额(元)
	private Double itembal;
	//合同金额(元)
	private Double contrAmt;
	//合同余额(元)
	private Double contrBal;
	//参照数据字典
	private String busitype;
	//合同租赁利率(%)
	private Double pricerate;
	//当前租赁利率(%)
	private Double curPricerate;
	//是否到期：Y/N
	private String isExpire;
	//应收本金
	private Double receivableAmt;
	//应收利息
	private Double receivableInt;
	//应收租金
	private Double receivableRent;
	//应收罚息
	private Double receivablDefInt;
	//应收手续费
	private Double receivableFee;
	//累计应罚息金额
	private Double receivablDefIntTotal;
	//应收服务费
	private Double receivableSc;
	//实收本金
	private Double realPrin;
	//实收利息
	private Double realInt;
	//实收租金
	private Double realRent;
	//实收手续费
	private Double realFee;
	//实收罚息
	private Double realDefInt;
	//应收未收罚息
	private Double accruedDefInt;
	//实收服务费
	private Double realSc;
	//是否逾期(Y,N)
	private String isOverdue;
	//逾期次数
	private Integer overdueTimes;
	//逾期利率(%)
	private Double overdueRate;
	//逾期本金
	private Double overduePrin;
	//实际减免罚息
	private Double derateDefInt;
	//当前本金余额
	private Double curPrinBal;
	//当前租金余额
	private Double curRentBal;
	//已投放金额
	private Double putAmt;
	//资产风险分类
	private Integer riskType;
	//合同状态 :0 已生成、1 已生效、2 起租、3 已结清、4 已作废
	private Integer contrStatus;
	//数据日期
	private String datadate;
	//是否展期(Y/N)
	private String isExtend;
	//展期次数
	private Integer extendTimes;
	//是否缩期(Y/N)
	private String isReduce;
	//缩期次数
	private Integer reduceTimes;
	//剩余核销次数
	private Integer residueCheckTimes;
	//合同签订日期
	private String signedDate;
	//业务日期
	private String tradedate;
	//操作人员
	private String pkUserOper;
	//组织
	private String pkOrg;
	//部门主键
	private String pkDeptdoc;
	//时间戳
	private String ts;
	//项目状态
	private String prjState;
	
	private List<String> prjStateBatchId;
	

	public void setPkContrTally(String pkContrTally){
		this.pkContrTally =  pkContrTally;
	}
	
	public String getPkContrTally(){
		return pkContrTally;
	}

	public void setOperSeq(Integer operSeq){
		this.operSeq =  operSeq;
	}
	
	public Integer getOperSeq(){
		return operSeq;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setPkUserManager(String pkUserManager){
		this.pkUserManager =  pkUserManager;
	}
	
	public String getPkUserManager(){
		return pkUserManager;
	}

	public void setPkPrjcontrOri(String pkPrjcontrOri){
		this.pkPrjcontrOri =  pkPrjcontrOri;
	}
	
	public String getPkPrjcontrOri(){
		return pkPrjcontrOri;
	}

	public void setContrCode(String contrCode){
		this.contrCode =  contrCode;
	}
	
	public String getContrCode(){
		return contrCode;
	}

	public void setPkPrjapply(String pkPrjapply){
		this.pkPrjapply =  pkPrjapply;
	}
	
	public String getPkPrjapply(){
		return pkPrjapply;
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

	public void setIntType(Integer intType){
		this.intType =  intType;
	}
	
	public Integer getIntType(){
		return intType;
	}

	public void setReptway(Integer reptway){
		this.reptway =  reptway;
	}
	
	public Integer getReptway(){
		return reptway;
	}

	public void setPlanrentdate(String planrentdate){
		this.planrentdate =  planrentdate;
	}
	
	public String getPlanrentdate(){
		return planrentdate;
	}

	public void setReptcycle(Integer reptcycle){
		this.reptcycle =  reptcycle;
	}
	
	public Integer getReptcycle(){
		return reptcycle;
	}

	public void setPkCustomer(String pkCustomer){
		this.pkCustomer =  pkCustomer;
	}
	
	public String getPkCustomer(){
		return pkCustomer;
	}

	public void setLeaseway(Integer leaseway){
		this.leaseway =  leaseway;
	}
	
	public Integer getLeaseway(){
		return leaseway;
	}

	public void setPkCurrency(String pkCurrency){
		this.pkCurrency =  pkCurrency;
	}
	
	public String getPkCurrency(){
		return pkCurrency;
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

	public void setRealEndDate(String realEndDate){
		this.realEndDate =  realEndDate;
	}
	
	public String getRealEndDate(){
		return realEndDate;
	}

	public void setIrr(Double irr){
		this.irr =  irr;
	}
	
	public Double getIrr(){
		return irr;
	}

	public void setCurIrr(Double curIrr){
		this.curIrr =  curIrr;
	}
	
	public Double getCurIrr(){
		return curIrr;
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

	public void setItemamt(Double itemamt){
		this.itemamt =  itemamt;
	}
	
	public Double getItemamt(){
		return itemamt;
	}

	public void setItembal(Double itembal){
		this.itembal =  itembal;
	}
	
	public Double getItembal(){
		return itembal;
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

	public void setBusitype(String busitype){
		this.busitype =  busitype;
	}
	
	public String getBusitype(){
		return busitype;
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

	public void setIsExpire(String isExpire){
		this.isExpire =  isExpire;
	}
	
	public String getIsExpire(){
		return isExpire;
	}

	public void setReceivableAmt(Double receivableAmt){
		this.receivableAmt =  receivableAmt;
	}
	
	public Double getReceivableAmt(){
		return receivableAmt;
	}

	public void setReceivableInt(Double receivableInt){
		this.receivableInt =  receivableInt;
	}
	
	public Double getReceivableInt(){
		return receivableInt;
	}

	public void setReceivableRent(Double receivableRent){
		this.receivableRent =  receivableRent;
	}
	
	public Double getReceivableRent(){
		return receivableRent;
	}

	public void setReceivablDefInt(Double receivablDefInt){
		this.receivablDefInt =  receivablDefInt;
	}
	
	public Double getReceivablDefInt(){
		return receivablDefInt;
	}

	public void setReceivableFee(Double receivableFee){
		this.receivableFee =  receivableFee;
	}
	
	public Double getReceivableFee(){
		return receivableFee;
	}

	public void setReceivablDefIntTotal(Double receivablDefIntTotal){
		this.receivablDefIntTotal =  receivablDefIntTotal;
	}
	
	public Double getReceivablDefIntTotal(){
		return receivablDefIntTotal;
	}

	public void setReceivableSc(Double receivableSc){
		this.receivableSc =  receivableSc;
	}
	
	public Double getReceivableSc(){
		return receivableSc;
	}

	public void setRealPrin(Double realPrin){
		this.realPrin =  realPrin;
	}
	
	public Double getRealPrin(){
		return realPrin;
	}

	public void setRealInt(Double realInt){
		this.realInt =  realInt;
	}
	
	public Double getRealInt(){
		return realInt;
	}

	public void setRealRent(Double realRent){
		this.realRent =  realRent;
	}
	
	public Double getRealRent(){
		return realRent;
	}

	public void setRealFee(Double realFee){
		this.realFee =  realFee;
	}
	
	public Double getRealFee(){
		return realFee;
	}

	public void setRealDefInt(Double realDefInt){
		this.realDefInt =  realDefInt;
	}
	
	public Double getRealDefInt(){
		return realDefInt;
	}

	public void setAccruedDefInt(Double accruedDefInt){
		this.accruedDefInt =  accruedDefInt;
	}
	
	public Double getAccruedDefInt(){
		return accruedDefInt;
	}

	public void setRealSc(Double realSc){
		this.realSc =  realSc;
	}
	
	public Double getRealSc(){
		return realSc;
	}

	public void setIsOverdue(String isOverdue){
		this.isOverdue =  isOverdue;
	}
	
	public String getIsOverdue(){
		return isOverdue;
	}

	public void setOverdueTimes(Integer overdueTimes){
		this.overdueTimes =  overdueTimes;
	}
	
	public Integer getOverdueTimes(){
		return overdueTimes;
	}

	public void setOverdueRate(Double overdueRate){
		this.overdueRate =  overdueRate;
	}
	
	public Double getOverdueRate(){
		return overdueRate;
	}

	public void setOverduePrin(Double overduePrin){
		this.overduePrin =  overduePrin;
	}
	
	public Double getOverduePrin(){
		return overduePrin;
	}

	public void setDerateDefInt(Double derateDefInt){
		this.derateDefInt =  derateDefInt;
	}
	
	public Double getDerateDefInt(){
		return derateDefInt;
	}

	public void setCurPrinBal(Double curPrinBal){
		this.curPrinBal =  curPrinBal;
	}
	
	public Double getCurPrinBal(){
		return curPrinBal;
	}

	public void setCurRentBal(Double curRentBal){
		this.curRentBal =  curRentBal;
	}
	
	public Double getCurRentBal(){
		return curRentBal;
	}

	public void setPutAmt(Double putAmt){
		this.putAmt =  putAmt;
	}
	
	public Double getPutAmt(){
		return putAmt;
	}

	public void setRiskType(Integer riskType){
		this.riskType =  riskType;
	}
	
	public Integer getRiskType(){
		return riskType;
	}

	public void setContrStatus(Integer contrStatus){
		this.contrStatus =  contrStatus;
	}
	
	public Integer getContrStatus(){
		return contrStatus;
	}

	public void setDatadate(String datadate){
		this.datadate =  datadate;
	}
	
	public String getDatadate(){
		return datadate;
	}

	public void setIsExtend(String isExtend){
		this.isExtend =  isExtend;
	}
	
	public String getIsExtend(){
		return isExtend;
	}

	public void setExtendTimes(Integer extendTimes){
		this.extendTimes =  extendTimes;
	}
	
	public Integer getExtendTimes(){
		return extendTimes;
	}

	public void setIsReduce(String isReduce){
		this.isReduce =  isReduce;
	}
	
	public String getIsReduce(){
		return isReduce;
	}

	public void setReduceTimes(Integer reduceTimes){
		this.reduceTimes =  reduceTimes;
	}
	
	public Integer getReduceTimes(){
		return reduceTimes;
	}

	public void setResidueCheckTimes(Integer residueCheckTimes){
		this.residueCheckTimes =  residueCheckTimes;
	}
	
	public Integer getResidueCheckTimes(){
		return residueCheckTimes;
	}

	public void setSignedDate(String signedDate){
		this.signedDate =  signedDate;
	}
	
	public String getSignedDate(){
		return signedDate;
	}

	public void setTradedate(String tradedate){
		this.tradedate =  tradedate;
	}
	
	public String getTradedate(){
		return tradedate;
	}

	public void setPkUserOper(String pkUserOper){
		this.pkUserOper =  pkUserOper;
	}
	
	public String getPkUserOper(){
		return pkUserOper;
	}

	public void setPkOrg(String pkOrg){
		this.pkOrg =  pkOrg;
	}
	
	public String getPkOrg(){
		return pkOrg;
	}

	public void setPkDeptdoc(String pkDeptdoc){
		this.pkDeptdoc =  pkDeptdoc;
	}
	
	public String getPkDeptdoc(){
		return pkDeptdoc;
	}

	public void setTs(String ts){
		this.ts =  ts;
	}
	
	public String getTs(){
		return ts;
	}

	public List<String> getPrjStateBatchId() {
		return prjStateBatchId;
	}

	public void setPrjStateBatchId(List<String> prjStateBatchId) {
		this.prjStateBatchId = prjStateBatchId;
	}

	public String getPrjState() {
		return prjState;
	}

	public void setPrjState(String prjState) {
		this.prjState = prjState;
	}
	
}




 

