package com.imfbp.rz.domain.template;

import java.io.Serializable;

public class TableEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396015118164757395L;

	/**
	 * 表编码
	 */
	private String tableCode;

	/**
	 * 表名称
	 */
	private String tableName;

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
