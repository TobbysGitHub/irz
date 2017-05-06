package com.imfbp.rz.domain.rzprjreviewlessee.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzPrjreviewLesseeQuery extends BaseQuery implements Serializable{

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
}




 

