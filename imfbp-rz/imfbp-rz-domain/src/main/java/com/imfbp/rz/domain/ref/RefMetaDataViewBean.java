package com.imfbp.rz.domain.ref;

import java.io.Serializable;
import java.util.Map;

public class RefMetaDataViewBean implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5923710165098721601L;

	/**
	 * 参照名称
	 */
	private String refKey;

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
	// /**
	// * 是否支持分页
	// */
	// private boolean isPage;
	// /**
	// * 是否是树形参照
	// */
	// private boolean isTree;
	/**
	 * 父级主键
	 */
	private String parentItem;

	/**
	 * 界面显示字段
	 */
	private String mainShowItem;
	/**
	 * 参照类型
	 */
	private String refType;
	/**
	 * 是否分页
	 */
	private boolean pagination;

	public boolean getPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getMainShowItem() {
		return mainShowItem;
	}

	public void setMainShowItem(String mainShowItem) {
		this.mainShowItem = mainShowItem;
	}

	public String getParentItem() {
		return parentItem;
	}

	public void setParentItem(String parentItem) {
		this.parentItem = parentItem;
	}

	// public boolean getIsTree() {
	// return isTree;
	// }
	//
	// public void setIsTree(boolean isTree) {
	// this.isTree = isTree;
	// }
	//
	// public boolean getIsPage() {
	// return isPage;
	// }
	//
	// public void setIsPage(boolean isPage) {
	// this.isPage = isPage;
	// }

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

}
