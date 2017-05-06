package com.imfbp.rz.domain.ref;

import java.io.Serializable;

public class RefBasePage implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8716741264308047832L;

	/**
	 * 开始行
	 */
	private int startRow;
	/**
	 * 结束行
	 */
	private int endRow;
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 每页的记录数
	 */
	private int pageSize;
	/**
	 * 总记录数
	 */
	private int totalRows;

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

}
