package com.imfbp.rz.domain.msgvo;

import java.io.Serializable;

public class RzOverDateMsgVo implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;

//	<result property="rptdate" column="rpt_date" />
//	<result property="prjcode" column="prj_code" />
//	<result property="prjname" column="prj_name" />
//	<result property="contrcode" column="contr_code" />
//	<result property="contrName" column="contr_name" />
//	<result property="pkUserManager" column="pk_user_manager" />
	private String rptdate;
	
	private String prjcode;
	
	private String prjname;
	
	private String contrcode;
	
	private String contrName;
	
	private String pkUserManager;
	
	private String num;

	public String getRptdate() {
		return rptdate;
	}

	public void setRptdate(String rptdate) {
		this.rptdate = rptdate;
	}

	public String getPrjcode() {
		return prjcode;
	}

	public void setPrjcode(String prjcode) {
		this.prjcode = prjcode;
	}

	public String getPrjname() {
		return prjname;
	}

	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}

	public String getContrcode() {
		return contrcode;
	}

	public void setContrcode(String contrcode) {
		this.contrcode = contrcode;
	}

	public String getContrName() {
		return contrName;
	}

	public void setContrName(String contrName) {
		this.contrName = contrName;
	}

	public String getPkUserManager() {
		return pkUserManager;
	}

	public void setPkUserManager(String pkUserManager) {
		this.pkUserManager = pkUserManager;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}
