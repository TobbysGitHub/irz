package com.imfbp.rz.domain.rzprjcontrchgsupplier;


import java.io.Serializable;

public class RzPrjcontrChgSupplier  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrChgSupplier;
	//项目评审主键  
	private String pkPrjcontrChg;
	//供应商（客户主键）
	private String pkCustomer;
	//备注
	private String remark;
	

	public void setPkPrjcontrChgSupplier(String pkPrjcontrChgSupplier){
		this.pkPrjcontrChgSupplier =  pkPrjcontrChgSupplier;
	}
	
	public String getPkPrjcontrChgSupplier(){
		return pkPrjcontrChgSupplier;
	}

	public void setPkPrjcontrChg(String pkPrjcontrChg){
		this.pkPrjcontrChg =  pkPrjcontrChg;
	}
	
	public String getPkPrjcontrChg(){
		return pkPrjcontrChg;
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