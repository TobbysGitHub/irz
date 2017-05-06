package com.imfbp.rz.domain.rzrisktype.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzRiskTypeQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkRiskType;
	//资产风险类别：0正常类 1 关注类 2次级类 3可疑 4 损失
	private Integer riskType;
	//逾期天数最小值(含)
	private Integer overdueMin;
	//逾期天数最大值(含)
	private Integer overdueMax;
	//备注
	private String remark;
	

	public void setPkRiskType(String pkRiskType){
		this.pkRiskType =  pkRiskType;
	}
	
	public String getPkRiskType(){
		return pkRiskType;
	}

	public void setRiskType(Integer riskType){
		this.riskType =  riskType;
	}
	
	public Integer getRiskType(){
		return riskType;
	}

	public void setOverdueMin(Integer overdueMin){
		this.overdueMin =  overdueMin;
	}
	
	public Integer getOverdueMin(){
		return overdueMin;
	}

	public void setOverdueMax(Integer overdueMax){
		this.overdueMax =  overdueMax;
	}
	
	public Integer getOverdueMax(){
		return overdueMax;
	}

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}
}




 

