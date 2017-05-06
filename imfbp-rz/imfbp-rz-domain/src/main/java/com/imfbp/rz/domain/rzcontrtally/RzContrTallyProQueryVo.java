package com.imfbp.rz.domain.rzcontrtally;

public class RzContrTallyProQueryVo {
	    String queryCondition;
	    String tendId;
	    String TableName;
	    private String pkPrjapply;//立项申请表主键
	    private String pkPrjcontr;//合同主键
	    private String pkContrTally;//台账表主键
		private String contrCode;
		private String prjName;
		private String prjCode;
		private String applydate;
		private String pkCustomer;
		//客户名称
		private String customerName;
		private String custtype;
		//业务类型
		private String busitype;
		//业务类型名称
		private String busitypeName;
		//租赁类型
		private Integer leaseway;
		//租赁类型名称
		private String leasewayName;
		private String startLeaseDate;
		private String endLeaseDate;
		private Integer leaseprd;
		private Double itemamt;
		private Double firstpmtamt;
		private Double financeamt;
		private Double pricerate;
		//项目状态
        private Integer prjState;
        //项目状态名称
        private String prjStateName;
        //合同状态
        private Integer contrStatus;
        //合同状态名称
        private String contrStatusName;
        private String operator;
        //当前操作者名称
        private String operatorName;
        private Double optamt;
        private Double depositamt;
        private Double feeamt;
        private Double scamt;
        private Double rentTotalAmt;
        private Double intTotalAmt;
        private Double putAmt;
        private Double curPrinBal;
        private Double curRentBal;
        private Integer reptcycle;
        private Double  irr;   //计划收益irr      
        private Double  curIrr;  //实际收益irr        
        private String pkUserManager;
        //客户经理名称
        private String manageName;
        private String applyStartdate;
        private String applyEnddate;
		public String getPkPrjapply() {
			return pkPrjapply;
		}
		public void setPkPrjapply(String pkPrjapply) {
			this.pkPrjapply = pkPrjapply;
		}
		public String getPkPrjcontr() {
			return pkPrjcontr;
		}
		public void setPkPrjcontr(String pkPrjcontr) {
			this.pkPrjcontr = pkPrjcontr;
		}
		public String getPkContrTally() {
			return pkContrTally;
		}
		public void setPkContrTally(String pkContrTally) {
			this.pkContrTally = pkContrTally;
		}
		public String getContrCode() {
			return contrCode;
		}
		public void setContrCode(String contrCode) {
			this.contrCode = contrCode;
		}
		public String getPrjName() {
			return prjName;
		}
		public void setPrjName(String prjName) {
			this.prjName = prjName;
		}
		public String getApplydate() {
			return applydate;
		}
		public void setApplydate(String applydate) {
			this.applydate = applydate;
		}
		public String getPkCustomer() {
			return pkCustomer;
		}
		public void setPkCustomer(String pkCustomer) {
			this.pkCustomer = pkCustomer;
		}
		public String getCusttype() {
			return custtype;
		}
		public void setCusttype(String custtype) {
			this.custtype = custtype;
		}
		public String getBusitype() {
			return busitype;
		}
		public void setBusitype(String busitype) {
			this.busitype = busitype;
		}
		
		public Integer getLeaseway() {
			return leaseway;
		}
		public void setLeaseway(Integer leaseway) {
			this.leaseway = leaseway;
		}
		public String getStartLeaseDate() {
			return startLeaseDate;
		}
		public void setStartLeaseDate(String startLeaseDate) {
			this.startLeaseDate = startLeaseDate;
		}
		public String getEndLeaseDate() {
			return endLeaseDate;
		}
		public void setEndLeaseDate(String endLeaseDate) {
			this.endLeaseDate = endLeaseDate;
		}
		
		public Integer getLeaseprd() {
			return leaseprd;
		}
		public void setLeaseprd(Integer leaseprd) {
			this.leaseprd = leaseprd;
		}
		public Double getItemamt() {
			return itemamt;
		}
		public void setItemamt(Double itemamt) {
			this.itemamt = itemamt;
		}
		public Double getFirstpmtamt() {
			return firstpmtamt;
		}
		public void setFirstpmtamt(Double firstpmtamt) {
			this.firstpmtamt = firstpmtamt;
		}
		public Double getFinanceamt() {
			return financeamt;
		}
		public void setFinanceamt(Double financeamt) {
			this.financeamt = financeamt;
		}
		public Double getPricerate() {
			return pricerate;
		}
		public void setPricerate(Double pricerate) {
			this.pricerate = pricerate;
		}
		public Integer getPrjState() {
			return prjState;
		}
		public void setPrjState(Integer prjState) {
			this.prjState = prjState;
		}
		public Integer getContrStatus() {
			return contrStatus;
		}
		public void setContrStatus(Integer contrStatus) {
			this.contrStatus = contrStatus;
		}
		
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public Double getOptamt() {
			return optamt;
		}
		public void setOptamt(Double optamt) {
			this.optamt = optamt;
		}
		public Double getDepositamt() {
			return depositamt;
		}
		public void setDepositamt(Double depositamt) {
			this.depositamt = depositamt;
		}
		public Double getFeeamt() {
			return feeamt;
		}
		public void setFeeamt(Double feeamt) {
			this.feeamt = feeamt;
		}
		public Double getScamt() {
			return scamt;
		}
		public void setScamt(Double scamt) {
			this.scamt = scamt;
		}
		public Double getRentTotalAmt() {
			return rentTotalAmt;
		}
		public void setRentTotalAmt(Double rentTotalAmt) {
			this.rentTotalAmt = rentTotalAmt;
		}
		public Double getIntTotalAmt() {
			return intTotalAmt;
		}
		public void setIntTotalAmt(Double intTotalAmt) {
			this.intTotalAmt = intTotalAmt;
		}
		public Double getPutAmt() {
			return putAmt;
		}
		public void setPutAmt(Double putAmt) {
			this.putAmt = putAmt;
		}
		public Double getCurPrinBal() {
			return curPrinBal;
		}
		public void setCurPrinBal(Double curPrinBal) {
			this.curPrinBal = curPrinBal;
		}
		public Double getCurRentBal() {
			return curRentBal;
		}
		public void setCurRentBal(Double curRentBal) {
			this.curRentBal = curRentBal;
		}
		public Integer getReptcycle() {
			return reptcycle;
		}
		public void setReptcycle(Integer reptcycle) {
			this.reptcycle = reptcycle;
		}
		public Double getIrr() {
			return irr;
		}
		public void setIrr(Double irr) {
			this.irr = irr;
		}
		public Double getCurIrr() {
			return curIrr;
		}
		public void setCurIrr(Double curIrr) {
			this.curIrr = curIrr;
		}
		public String getPkUserManager() {
			return pkUserManager;
		}
		public void setPkUserManager(String pkUserManager) {
			this.pkUserManager = pkUserManager;
		}
		public String getPrjCode() {
			return prjCode;
		}
		public void setPrjCode(String prjCode) {
			this.prjCode = prjCode;
		}
		public String getApplyStartdate() {
			return applyStartdate;
		}
		public void setApplyStartdate(String applyStartdate) {
			this.applyStartdate = applyStartdate;
		}
		public String getApplyEnddate() {
			return applyEnddate;
		}
		public void setApplyEnddate(String applyEnddate) {
			this.applyEnddate = applyEnddate;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getTendId() {
			return tendId;
		}
		public void setTendId(String tendId) {
			this.tendId = tendId;
		}
		public String getTableName() {
			return TableName;
		}
		public void setTableName(String tableName) {
			TableName = tableName;
		}
		public String getOperatorName() {
			return operatorName;
		}
		public void setOperatorName(String operatorName) {
			this.operatorName = operatorName;
		}
		public String getManageName() {
			return manageName;
		}
		public void setManageName(String manageName) {
			this.manageName = manageName;
		}
		public String getBusitypeName() {
			return busitypeName;
		}
		public void setBusitypeName(String busitypeName) {
			this.busitypeName = busitypeName;
		}
		public String getLeasewayName() {
			return leasewayName;
		}
		public void setLeasewayName(String leasewayName) {
			this.leasewayName = leasewayName;
		}
		public String getPrjStateName() {
			return prjStateName;
		}
		public void setPrjStateName(String prjStateName) {
			this.prjStateName = prjStateName;
		}
		public String getContrStatusName() {
			return contrStatusName;
		}
		public void setContrStatusName(String contrStatusName) {
			this.contrStatusName = contrStatusName;
		}
		public String getQueryCondition() {
			return queryCondition;
		}
		public void setQueryCondition(String queryCondition) {
			this.queryCondition = queryCondition;
		}

}