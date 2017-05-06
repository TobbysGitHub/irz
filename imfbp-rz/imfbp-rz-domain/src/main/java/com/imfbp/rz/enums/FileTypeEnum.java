package com.imfbp.rz.enums;

/**
 * @Title : 文件类型枚举
 * @Description : 文件类型枚举，提供常用的文件类型-文件后缀名
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年11月28日 下午5:22:43
 */
public enum FileTypeEnum {

	/**
	 * doc类型
	 */
	DOC("doc"),
	/**
	 * docx类型
	 */
	DOCX("docx"),
	/**
	 * xls类型
	 */
	XLS("xls"),
	/**
	 * xlsx类型
	 */
	XLSX("xlsx"),
	/**
	 * pdf类型
	 */
	PDF("pdf");

	private String value;

	private FileTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public String toString() {
		return "{pk:'" + this.value + "',name:'" + this.value + "',value:'"
				+ this.value + "'}";
	}
}
