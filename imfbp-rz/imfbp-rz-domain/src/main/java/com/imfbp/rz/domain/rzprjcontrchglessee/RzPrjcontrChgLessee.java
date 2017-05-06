package com.imfbp.rz.domain.rzprjcontrchglessee;


import java.io.Serializable;

public class RzPrjcontrChgLessee  implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrChgLessee;
	//项目评审主键  
	private String pkPrjcontrChg;
	//承租人（客户主键）
	private String pkCustomer;
	//是否主承租人 Y是 N否
	private String isMainLessee;
	//备注
	private String remark;
	

	public void setPkPrjcontrChgLessee(String pkPrjcontrChgLessee){
		this.pkPrjcontrChgLessee =  pkPrjcontrChgLessee;
	}
	
	public String getPkPrjcontrChgLessee(){
		return pkPrjcontrChgLessee;
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