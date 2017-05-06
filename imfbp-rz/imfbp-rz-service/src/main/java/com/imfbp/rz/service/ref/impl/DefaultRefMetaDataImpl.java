package com.imfbp.rz.service.ref.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imfbp.rz.domain.ref.RefComboxMetaData;
import com.imfbp.rz.domain.ref.RefShowNameMetaData;
import com.imfbp.rz.service.ref.RefMetaDataService;

/**
 * 参照元数据接口默认实现
 * 
 * @author Administrator
 *
 */
@Component("defaultRefMetaDataService")
public class DefaultRefMetaDataImpl implements RefMetaDataService, Cloneable,
		Serializable {

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
	 * 排序字段
	 */
	private String order;
	/**
	 * 父级主键
	 */
	private String parentItem;

	/**
	 * 树型参照显示字段
	 */
	private String treeShowItem;

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
	/**
	 * 其他必要数据：参照界面不显示，但是数据集里面需要包含这些数据
	 */
	private Map<String, String> otherDataItemsMap;

	// /**
	// * 档案类型编码
	// */
	// private String dicItemCode;
	//
	// public String getDicItemCode() {
	// return dicItemCode;
	// }
	//
	// public void setDicItemCode(String dicItemCode) {
	// this.dicItemCode = dicItemCode;
	// }
	/**
	 * 根据Key显示value元数据Map集合
	 */
	private Map<String, RefShowNameMetaData> refShowNameMetaDatasMap;

	/**
	 * 获取下拉数据元数据
	 */
	private Map<String, RefComboxMetaData> refComboxMetaDataMap;

	public Map<String, RefComboxMetaData> getRefComboxMetaDataMap() {
		return refComboxMetaDataMap;
	}

	public void setRefComboxMetaDataMap(
			Map<String, RefComboxMetaData> refComboxMetaDataMap) {
		this.refComboxMetaDataMap = refComboxMetaDataMap;
	}

	public Map<String, RefShowNameMetaData> getRefShowNameMetaDatasMap() {
		return refShowNameMetaDatasMap;
	}

	public void setRefShowNameMetaDatasMap(
			Map<String, RefShowNameMetaData> refShowNameMetaDatasMap) {
		this.refShowNameMetaDatasMap = refShowNameMetaDatasMap;
	}

	public Map<String, String> getOtherDataItemsMap() {
		return otherDataItemsMap;
	}

	public void setOtherDataItemsMap(Map<String, String> otherDataItemsMap) {
		this.otherDataItemsMap = otherDataItemsMap;
	}

	public boolean getPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
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

	public String getTreeShowItem() {
		return treeShowItem;
	}

	public void setTreeShowItem(String treeShowItem) {
		this.treeShowItem = treeShowItem;
	}

	public String getParentItem() {
		return parentItem;
	}

	public void setParentItem(String parentItem) {
		this.parentItem = parentItem;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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
