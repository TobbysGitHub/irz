package com.imfbp.rz.domain.ref;

import java.io.Serializable;
import java.util.Map;

public class RefShowNameMetaData implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1531628962827766600L;

	private String id;

	private String keyItems;

	private String valueItems;

	private String tableName;

	private String condition;
	/**
	 * 虚拟字段集合
	 */
	private Map<String, String> refVirtualItemsMap;

	public Map<String, String> getRefVirtualItemsMap() {
		return refVirtualItemsMap;
	}

	public void setRefVirtualItemsMap(Map<String, String> refVirtualItemsMap) {
		this.refVirtualItemsMap = refVirtualItemsMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyItems() {
		return keyItems;
	}

	public void setKeyItems(String keyItems) {
		this.keyItems = keyItems;
	}

	public String getValueItems() {
		return valueItems;
	}

	public void setValueItems(String valueItems) {
		this.valueItems = valueItems;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
