package com.imfbp.rz.domain.rzbaseparam.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzBaseParamQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkBaseParam;
	//收款提醒通知提前天数
	private Integer advNoticeDays;
	//客户还款期限
	private Integer rpmtTerm;
	//是否启用客户经理数据权限 Y 是 N 否
	private String isEnableAuth;
	//首付款财务审批角色
	private String finApprovalRole;
	//逾期罚息日利率(%)
	private Double overdueRate;
	

	public void setPkBaseParam(String pkBaseParam){
		this.pkBaseParam =  pkBaseParam;
	}
	
	public String getPkBaseParam(){
		return pkBaseParam;
	}

	public void setAdvNoticeDays(Integer advNoticeDays){
		this.advNoticeDays =  advNoticeDays;
	}
	
	public Integer getAdvNoticeDays(){
		return advNoticeDays;
	}

	public void setRpmtTerm(Integer rpmtTerm){
		this.rpmtTerm =  rpmtTerm;
	}
	
	public Integer getRpmtTerm(){
		return rpmtTerm;
	}

	public void setIsEnableAuth(String isEnableAuth){
		this.isEnableAuth =  isEnableAuth;
	}
	
	public String getIsEnableAuth(){
		return isEnableAuth;
	}

	public void setFinApprovalRole(String finApprovalRole){
		this.finApprovalRole =  finApprovalRole;
	}
	
	public String getFinApprovalRole(){
		return finApprovalRole;
	}

	public void setOverdueRate(Double overdueRate){
		this.overdueRate =  overdueRate;
	}
	
	public Double getOverdueRate(){
		return overdueRate;
	}
}




 

