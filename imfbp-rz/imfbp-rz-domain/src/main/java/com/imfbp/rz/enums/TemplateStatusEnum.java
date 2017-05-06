package com.imfbp.rz.enums;

/**
 * @Title : 模板状态
 * @Description : 模板状态
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月5日 下午2:11:03
 */
public enum TemplateStatusEnum {
	/**
	 * 已停用
	 */
	UNUSED("已停用", 0),
	/**
	 * 已启用
	 */
	USED("已启用", 1),
	/**
	 * 已分配
	 */
	DISTRIBUTED("已分配", 2);

	private String value;

	private Integer index;

	private TemplateStatusEnum(String value, Integer index) {
		this.value = value;
		this.index = index;
	}

	public String getValue() {
		return this.value;
	}

	public int getIndex() {
		return this.index;
	}

	public String toString() {
		return "{pk:'" + this.index + "',name:'" + this.value + "',value:'"
				+ this.index + "'}";
	}
}
