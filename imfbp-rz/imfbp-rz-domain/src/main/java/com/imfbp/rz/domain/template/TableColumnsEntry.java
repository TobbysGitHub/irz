package com.imfbp.rz.domain.template;

import java.io.Serializable;

public class TableColumnsEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7673281141139318267L;

	/**
	 * 列编码
	 */
	private String colCode;
	/**
	 * 列名称
	 */
	private String colName;
	public String getColCode() {
		return colCode;
	}
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	
	
}
