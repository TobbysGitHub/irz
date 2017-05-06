package com.imfbp.rz.domain.rzprjreviewsupplier;


import java.io.Serializable;

public class RzPrjreviewSupplier  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjreviewSupplier;
	//项目评审主键  
	private String pkPrjreview;
	//供应商（客户主键）
	private String pkCustomer;
	//备注
	private String remark;
	

	public void setPkPrjreviewSupplier(String pkPrjreviewSupplier){
		this.pkPrjreviewSupplier =  pkPrjreviewSupplier;
	}
	
	public String getPkPrjreviewSupplier(){
		return pkPrjreviewSupplier;
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

	public void setRemark(String remark){
		this.remark =  remark;
	}
	
	public String getRemark(){
		return remark;
	}
}