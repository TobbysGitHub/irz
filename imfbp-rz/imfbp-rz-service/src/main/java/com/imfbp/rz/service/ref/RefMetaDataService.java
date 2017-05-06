package com.imfbp.rz.service.ref;

import java.util.Map;

import com.imfbp.rz.domain.ref.RefComboxMetaData;
import com.imfbp.rz.domain.ref.RefShowNameMetaData;

/**
 * 参照元数据服务接口
 * 
 * @author Xinggh
 *
 */
public interface RefMetaDataService {
	/**
	 * 树型参照显示字段
	 * 
	 * @return
	 */
	public String getTreeShowItem();

	/**
	 * 父级主键
	 * 
	 * @return
	 */
	public String getParentItem();

	/**
	 * 排序字段
	 * 
	 * @return
	 */
	public String getOrder();

	/**
	 * 当前主键（id）字段
	 * 
	 * @return
	 */
	public String getIdItem();

	/**
	 * 显示字段，key：字段英文名，value：字段中文名
	 * 
	 * @return
	 */
	public Map<String, String> getShowItemsMap();

	/**
	 * 标题
	 * 
	 * @return
	 */
	public String getTitle();

	/**
	 * 表名
	 * 
	 * @return
	 */
	public String getTableName();

	/**
	 * 条件
	 * 
	 * @return
	 */
	public String getCondition();

	/**
	 * 界面显示字段
	 * 
	 * @return
	 */
	public String getMainShowItem();

	/**
	 * 参照类型list:列表 tree:树
	 * 
	 * @return
	 */
	public String getRefType();

	/**
	 * 是否分页
	 * 
	 * @return
	 */
	public boolean getPagination();

	/**
	 * 其他必要数据：参照界面不显示，但是数据集里面需要包含这些数据
	 */
	public Map<String, String> getOtherDataItemsMap();

	// /**
	// * 档案类型编码
	// *
	// * @return
	// */
	// public String getDicItemCode();
	/**
	 * 根据Key显示value元数据Map集合
	 * 
	 * @return
	 */
	public Map<String, RefShowNameMetaData> getRefShowNameMetaDatasMap();

	/**
	 * 获取下拉数据参照元数据
	 * 
	 * @return
	 */
	public Map<String, RefComboxMetaData> getRefComboxMetaDataMap();
}
