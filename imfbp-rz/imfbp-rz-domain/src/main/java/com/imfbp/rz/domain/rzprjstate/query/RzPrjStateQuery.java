package com.imfbp.rz.domain.rzprjstate.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzPrjStateQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//项目合同主键
	private String pkPrjState;
	//0 报价单生成、1 立项中、2 项目评审中、3合同签订中、4 项目已起租、5 项目租后管理中、6 项目结清、7 项目合同作废
	private Integer prjState;
	//报价测算主键
	private String pkPricecal;
	//项目评审主键
	private String pkPrjreview;
	//立项申请主键
	private String pkPrjapply;
	//合同主键
	private String pkPrjcontr;
	//原合同主键
	private String pkPrjcontrori;
	//合同编号
	private String contrCode;
	//操作人员
	private String pkUserOper;
	//组织
	private String pkOrg;
	//部门主键
	private String pkDeptdoc;
	//时间戳
	private String ts;
	

	public void setPkPrjState(String pkPrjState){
		this.pkPrjState =  pkPrjState;
	}
	
	public String getPkPrjState(){
		return pkPrjState;
	}

	public void setPrjState(Integer prjState){
		this.prjState =  prjState;
	}
	
	public Integer getPrjState(){
		return prjState;
	}

	public void setPkPricecal(String pkPricecal){
		this.pkPricecal =  pkPricecal;
	}
	
	public String getPkPricecal(){
		return pkPricecal;
	}

	public void setPkPrjreview(String pkPrjreview){
		this.pkPrjreview =  pkPrjreview;
	}
	
	public String getPkPrjreview(){
		return pkPrjreview;
	}

	public void setPkPrjapply(String pkPrjapply){
		this.pkPrjapply =  pkPrjapply;
	}
	
	public String getPkPrjapply(){
		return pkPrjapply;
	}

	public void setPkPrjcontr(String pkPrjcontr){
		this.pkPrjcontr =  pkPrjcontr;
	}
	
	public String getPkPrjcontr(){
		return pkPrjcontr;
	}

	public void setPkPrjcontrori(String pkPrjcontrori){
		this.pkPrjcontrori =  pkPrjcontrori;
	}
	
	public String getPkPrjcontrori(){
		return pkPrjcontrori;
	}

	public void setContrCode(String contrCode){
		this.contrCode =  contrCode;
	}
	
	public String getContrCode(){
		return contrCode;
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
}




 

