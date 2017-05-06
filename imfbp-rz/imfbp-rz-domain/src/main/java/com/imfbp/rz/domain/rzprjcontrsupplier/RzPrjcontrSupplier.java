package com.imfbp.rz.domain.rzprjcontrsupplier;


import java.io.Serializable;

public class RzPrjcontrSupplier  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrSupplier;
	//项目评审主键  
	private String pkPrjcontr;
	//供应商（客户主键）
	private String pkCustomer;
	//备注
	private String remark;
	

	public void setPkPrjcontrSupplier(String pkPrjcontrSupplier){
		this.pkPrjcontrSupplier =  pkPrjcontrSupplier;
	}
	
	public String getPkPrjcontrSupplier(){
		return pkPrjcontrSupplier;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
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