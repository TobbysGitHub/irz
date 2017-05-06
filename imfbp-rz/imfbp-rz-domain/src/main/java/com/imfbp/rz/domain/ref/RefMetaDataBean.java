package com.imfbp.rz.domain.ref;

import java.io.Serializable;
import java.util.Map;

public class RefMetaDataBean implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5923710165098721601L;

	/**
	 * 主键属性字段
	 */
	private String idItem;

	/**
	 * 显示字段，key：字段英文名，value：字段中文名
	 */
	private Map<String, String> showItemsMap;
	/**
	 * 参照显示标题
	 */
	private String title;
	/**
	 * 参照对应数据库表名
	 */
	private String tableName;
	/**
	 * 查询条件
	 */
	private String condition;

	/**
	 * 是否数据库取数
	 */
	private boolean isDb;

	/**
	 * 业务取数接口实现类(含全路径)
	 */
	private String implementation;

	/**
	 * 排序字段
	 */
	private String order;
	/**
	 * 是否支持分页
	 */
	private boolean isPage;

	public boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(boolean isPage) {
		this.isPage = isPage;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean getIsDb() {
		return isDb;
	}

	public void setIsDb(boolean isDb) {
		this.isDb = isDb;
	}

	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}

	public Map<String, String> getShowItemsMap() {
		return showItemsMap;
	}

	public void setShowItemsMap(Map<String, String> showItemsMap) {
		this.showItemsMap = showItemsMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
