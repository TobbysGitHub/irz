package com.imfbp.rz.domain.rzprjcontrchginseqpt.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzPrjcontrChgInsEqptQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPrjcontrChgInsEqpt;
	//项目合同保险信息主键
	private String pkPrjcontrChgIns;
	//项目合同主键
	private String pkPrjcontrChg;
	//客户主键
	private String pkCustomer;
	//租赁设备外键
	private String pkEqpt;
	

	public void setPkPrjcontrChgInsEqpt(String pkPrjcontrChgInsEqpt){
		this.pkPrjcontrChgInsEqpt =  pkPrjcontrChgInsEqpt;
	}
	
	public String getPkPrjcontrChgInsEqpt(){
		return pkPrjcontrChgInsEqpt;
	}

	public void setPkPrjcontrChgIns(String pkPrjcontrChgIns){
		this.pkPrjcontrChgIns =  pkPrjcontrChgIns;
	}
	
	public String getPkPrjcontrChgIns(){
		return pkPrjcontrChgIns;
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

	public void setPkEqpt(String pkEqpt){
		this.pkEqpt =  pkEqpt;
	}
	
	public String getPkEqpt(){
		return pkEqpt;
	}
}




 

