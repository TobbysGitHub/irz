package com.imfbp.rz.service.ref.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.ref.RefDao;
import com.imfbp.rz.domain.ref.RefBasePage;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefComboxMetaData;
import com.imfbp.rz.domain.ref.RefLevelResultData;
import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.domain.ref.RefShowNameMetaData;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.ref.RefDataService;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;

/**
 * 参照数据服务接口实现
 * 
 * @author Xinggh
 *
 */
@Component("defaultRefService")
public class DefaultRefServiceImpl implements RefDataService {

	// @Autowired
	private RefMetaDataService refMetaDataService;

	@Autowired
	private RefDao refDao;

	private final static Logger logger = Logger.getLogger(DefaultRefServiceImpl.class);

	private final static String errorMessage = "参照元数据配置文件配置的元数据不对，请检查";

	/**
	 * 获取指定Id集合参照数据的翻译数据
	 * 
	 * @param refBaseQuery
	 * @return
	 * @throws Exception
	 */
	public RefResult getRefDatasByBatchId(RefBaseQuery refBaseQuery) throws Exception {
		RefResult result = new RefResult();
		try {
			if (refMetaDataService != null && refBaseQuery != null) {
				String idItem = refMetaDataService.getIdItem();
				String tableName = refMetaDataService.getTableName();
				Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
				String title = refMetaDataService.getTitle();
				String refType = refMetaDataService.getRefType();
				String parentItem = refMetaDataService.getParentItem();
				String treeShowItem = refMetaDataService.getTreeShowItem();
				if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(tableName) || StringUtil.isEmpty(showItemsMap)
						|| StringUtil.isEmpty(title) || StringUtil.isEmpty(refType))
						|| (refType.equals(IRZConsts.TREE)
								&& (StringUtil.isEmpty(parentItem) || StringUtil.isEmpty(treeShowItem)))) {
					logger.debug(errorMessage);
					throw new ResultException(errorMessage);
				}

				String sql = getQueryTranslateDataSql(refMetaDataService, refBaseQuery);
				List<Object> dataListMap = refDao.getRefDataValueBySql(sql);
				exeRefComboxShowName(dataListMap, refMetaDataService);
				exeRefShowName(dataListMap, refMetaDataService);
				exeEnsureItemValue(dataListMap, refMetaDataService);
				if (refType.equals(IRZConsts.TREE)) {
					// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
					result.setDatas(
							getRefLevelResultDataBeanLists(dataListMap, idItem, parentItem, treeShowItem, true));
				} else {
					result.setDatas(dataListMap);
				}
				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(refMetaDataService, refBaseQuery);
				if (refMetaDataViewBean != null) {
					result.setRefMetaDataBean(refMetaDataViewBean);
				}
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public RefResult getRefDatasByQuery(RefBaseQuery refBaseQuery) throws Exception {
		RefResult result = new RefResult();
		try {
			if (refMetaDataService != null) {
				String idItem = refMetaDataService.getIdItem();
				String tableName = refMetaDataService.getTableName();
				Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
				String title = refMetaDataService.getTitle();
				String condition = refMetaDataService.getCondition();
				String refType = refMetaDataService.getRefType();
				String parentItem = refMetaDataService.getParentItem();
				String treeShowItem = refMetaDataService.getTreeShowItem();
				if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(tableName) || StringUtil.isEmpty(showItemsMap)
						|| StringUtil.isEmpty(title) || StringUtil.isEmpty(refType))
						|| (refType.equals(IRZConsts.TREE)
								&& (StringUtil.isEmpty(parentItem) || StringUtil.isEmpty(treeShowItem)))) {
					logger.debug(errorMessage);
					throw new ResultException(errorMessage);
				}

				// 如果分页，组装分页数据
				RefBasePage refBasePage = null;
				// 如果是树型参照则不需要分页
				if (refType.equals(IRZConsts.LIST)) {
					refBasePage = new RefBasePage();
					int page = refBaseQuery.getPage();
					page = page != 0 ? page : 1;
					int rows = refBaseQuery.getRows();
					rows = rows != 0 ? rows : IRZConsts.PAGESIZE;
					refBasePage.setPage(page);
					refBasePage.setStartRow((page - 1) * rows + 1);
					refBasePage.setEndRow(page * rows);
					refBasePage.setPageSize(rows);
					result.setRefBasePage(refBasePage);
					String countSql = getQueryDataCountSql(tableName, condition, refBaseQuery);
					if (!StringUtil.isEmpty(countSql)) {
						Integer count = refDao.getRefDataCountbySql(countSql);
						count = count != null ? count : 0;
						refBasePage.setTotalRows(count);
					}
					result.setRefBasePage(refBasePage);
				}
				String sql = getQueryDataSql(refMetaDataService, refBasePage, refBaseQuery);
				if (!StringUtil.isEmpty(sql)) {
					List<Object> dataListMap = refDao.getRefDataValueBySql(sql);
					if (dataListMap != null && dataListMap.size() > 0) {
						exeRefComboxShowName(dataListMap, refMetaDataService);
						exeRefShowName(dataListMap, refMetaDataService);
						exeEnsureItemValue(dataListMap, refMetaDataService);
						if (refType.equals(IRZConsts.TREE)) {
							// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
							result.setDatas(getRefLevelResultDataBeanLists(dataListMap, idItem, parentItem,
									treeShowItem, false));
						} else {
							result.setDatas(dataListMap);
						}
					}
				}
				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(refMetaDataService, refBaseQuery);
				if (refMetaDataViewBean != null) {
					result.setRefMetaDataBean(refMetaDataViewBean);
				}
			}
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public void exeRefComboxShowName(List<Object> dataListMap, RefMetaDataService refMetaDataService) {
		if (dataListMap == null || refMetaDataService == null || refMetaDataService.getRefComboxMetaDataMap() == null) {
			return;
		}
		Map<String, RefComboxMetaData> refComboxMetaDataMap = refMetaDataService.getRefComboxMetaDataMap();
		if (refComboxMetaDataMap == null || refComboxMetaDataMap.size() == 0) {
			return;
		}
		for (int i = 0; i < dataListMap.size(); i++) {
			Map<String, Object> objMap = (Map<String, Object>) dataListMap.get(i);
			// modify by tuxl on 2016-06-15
			// 遍历时修改map，会造成ConcurrentModificationException异常
			Map<String, Object> newobjMap = new HashMap<String, Object>();
			newobjMap.putAll(objMap);
			if (objMap != null) {
				for (Map.Entry<String, Object> entry : newobjMap.entrySet()) {
					if (refComboxMetaDataMap.containsKey(entry.getKey())) {
						Map<Object, Object> keyValueMap = refComboxMetaDataMap.get(entry.getKey()).getKeyValueMap();
						if (keyValueMap != null && keyValueMap.size() > 0) {
							String comboxShowName = refComboxMetaDataMap.get(entry.getKey()).getComboxShowName();
							// 查到需要显示的数据
							((Map<String, Object>) dataListMap.get(i)).put(comboxShowName, refComboxMetaDataMap
									.get(entry.getKey()).getKeyValueMap().get(entry.getValue().toString()));
						}
					}
				}

			}
		}
	}

	public void exeRefShowName(List<Object> dataListMap, RefMetaDataService refMetaDataService) {
		if (dataListMap == null || refMetaDataService == null) {
			return;
		}
		Map<String, RefShowNameMetaData> refShowNameMetaDatasMap = refMetaDataService.getRefShowNameMetaDatasMap();
		if (refShowNameMetaDatasMap == null || refShowNameMetaDatasMap.size() == 0) {
			return;
		}
		// 如果存在虚拟字段，虚拟字段需要显示在参照弹出界面上
		// if (refShowNameMetaDatasMap != null
		// && refShowNameMetaDatasMap.size() > 0) {
		// for (Entry<String, RefShowNameMetaData> entry :
		// refShowNameMetaDatasMap
		// .entrySet()) {
		// if (entry != null && entry.getValue() != null) {
		// Map<String, String> refVirtualItemsMap = entry.getValue()
		// .getRefVirtualItemsMap();
		// if (refVirtualItemsMap == null
		// || refVirtualItemsMap.size() == 0) {
		// return;
		// }
		// }
		// }
		// }

		for (Map.Entry<String, RefShowNameMetaData> entry : refShowNameMetaDatasMap.entrySet()) {
			if (entry != null && entry.getKey() != null && entry.getValue() != null) {
				String id = entry.getValue().getId();
				String keyItems = entry.getValue().getKeyItems();
				String valueItems = entry.getValue().getValueItems();
				String tableName = entry.getValue().getTableName();
				String condition = entry.getValue().getCondition();
				String refItemName = entry.getKey();
				Map<String, String> refVirtualItemsMap = entry.getValue().getRefVirtualItemsMap();
				if (!StringUtil.isEmpty(keyItems) && !StringUtil.isEmpty(valueItems)) {
					String[] keyArray = keyItems.split(",");
					String[] valueArray = valueItems.split(",");
					if (keyArray.length == valueArray.length) {
						exeShowNameForId(refItemName, dataListMap, id, keyArray, valueArray, tableName, condition,
								refVirtualItemsMap);
					}
				}
			}
		}
	}

	/**
	 * 将主键字段转换为需要显示的字段
	 * 
	 * @param dataListMap
	 * @param id
	 * @param keyArray
	 * @param valueArray
	 * @param tableName
	 * @param condition
	 */
	@SuppressWarnings("unchecked")
	public void exeShowNameForId(String refItemName, List<Object> dataListMap, String id, String[] keyArray,
			String[] valueArray, String tableName, String condition, Map<String, String> refVirtualItemsMap) {
		if (dataListMap == null || StringUtil.isEmpty(id) || StringUtil.isEmpty(keyArray) || valueArray == null
				|| valueArray.length != keyArray.length || StringUtil.isEmpty(tableName)
				|| StringUtil.isEmpty(condition) || refVirtualItemsMap == null) {
			return;
		}
		Map<String, String> dbItemShowItemMap = new HashMap<String, String>();
		for (Entry<String, String> virEntry : refVirtualItemsMap.entrySet()) {
			if (virEntry != null) {
				String key = virEntry.getKey();
				String[] keys = key.split(",");
				dbItemShowItemMap.put(keys[0], keys[1]);
			}
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select ").append(id).append(",");
		Map<String, String> keyValueRefMap = new HashMap<String, String>();
		for (int i = 0; i < valueArray.length; i++) {
			sql.append(valueArray[i]);
			if (i != valueArray.length - 1) {
				sql.append(",");
			}
			keyValueRefMap.put(keyArray[i], valueArray[i]);
		}
		StringBuffer idLists = new StringBuffer();
		// idLists.append(id).append(" in (");
		for (int i = 0; i < dataListMap.size(); i++) {
			Map<String, Object> objMap = (Map<String, Object>) dataListMap.get(i);
			if (objMap != null) {

				Object idValue = objMap.get(refItemName);
				if (!StringUtil.isEmpty(idValue)) {
					idLists.append("'").append(idValue).append("',");
				}
				// for (Map.Entry<String, String> entry : keyValueRefMap
				// .entrySet()) {
				// if (entry != null) {
				// Object idValue = objMap.get(entry.getKey());
				// if (!StringUtil.isEmpty(idValue)) {
				// idLists.append("'").append(idValue).append("',");
				// }
				// }
				// }
			}
		}
		if (idLists == null || idLists.length() == 0) {
			return;
		}
		sql.append(" from ").append(tableName);
		sql.append(" where ").append(id).append(" in (");
		sql.append(idLists.substring(0, idLists.length() - 1)).append(")");
		if (!StringUtil.isEmpty(condition)) {
			sql.append(" and ").append(condition);
		}
		List<Object> objectLists = refDao.getRefDataValueBySql(sql.toString());
		if (objectLists != null && objectLists.size() > 0) {
			for (int i = 0; i < objectLists.size(); i++) {
				if (objectLists.get(i) != null) {
					Map<String, Object> objValueMap = (Map<String, Object>) objectLists.get(i);
					for (int j = 0; j < dataListMap.size(); j++) {
						if (dataListMap.get(j) != null) {
							Map<String, Object> objMap = (Map<String, Object>) dataListMap.get(j);
							if (objValueMap.get(id).equals(objMap.get(refItemName))) {
								// 判断objValueMap主键字段与objMap对应数据字段是否相同,相同，将虚拟字段设置值

								for (Map.Entry<String, String> entry : dbItemShowItemMap.entrySet()) {
									// 查到需要显示的数据
									((Map<String, Object>) dataListMap.get(j)).put(entry.getValue(),
											objValueMap.get(entry.getKey()));
								}

								// }
								// if (objValueMap.containsKey(entry.getKey()))
								// {
							}

							// for (Map.Entry<String, Object> entry : objMap
							// .entrySet()) {}

						}
					}
				}
			}
		}
	}

	/**
	 * 确保每一个需要显示的字段都有值，即使值为null也要存在
	 * 
	 * @param dataListMap
	 * @param refMetaDataService
	 */
	@SuppressWarnings("unchecked")
	public void exeEnsureItemValue(List<Object> dataListMap, RefMetaDataService refMetaDataService) {

		if (dataListMap == null || refMetaDataService == null) {
			return;
		}
		List<String> itemLists = new ArrayList<String>();
		Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
		if (showItemsMap != null && showItemsMap.size() > 0) {
			for (Map.Entry<String, String> entry : showItemsMap.entrySet()) {
				itemLists.add(entry.getKey());
			}
		}
		Map<String, String> otherDataItemsMap = refMetaDataService.getOtherDataItemsMap();
		if (otherDataItemsMap != null && otherDataItemsMap.size() > 0) {
			for (Map.Entry<String, String> entry : otherDataItemsMap.entrySet()) {
				itemLists.add(entry.getKey());
			}
		}
		if (itemLists.size() == 0) {
			return;
		}
		Map<String, Object> data = null;
		for (int i = 0; i < dataListMap.size(); i++) {
			data = (Map<String, Object>) dataListMap.get(i);
			for (int j = 0; j < itemLists.size(); j++) {
				if (!data.containsKey(itemLists.get(j))) {
					((Map<String, Object>) dataListMap.get(i)).put(itemLists.get(j), IRZConsts.NULLSTRVALUE);
				}
			}
		}
	}

	/**
	 * 获取返回给前台的元数据
	 * 
	 * @param refMetaDataService
	 * @return
	 */
	public RefMetaDataViewBean getRefMetaDataViewBean(RefMetaDataService refMetaDataService,
			RefBaseQuery refBaseQuery) {
		if (refMetaDataService != null) {
			String idItem = refMetaDataService.getIdItem();
			String tableName = refMetaDataService.getTableName();
			Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
			String title = refMetaDataService.getTitle();
			String refType = refMetaDataService.getRefType();
			String parentItem = refMetaDataService.getParentItem();
			String mainShowItem = refMetaDataService.getMainShowItem();
			if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(tableName) || StringUtil.isEmpty(showItemsMap)
					|| StringUtil.isEmpty(title) || StringUtil.isEmpty(mainShowItem) || StringUtil.isEmpty(refType))
					|| (refType.equals(IRZConsts.TREE) && StringUtil.isEmpty(parentItem))) {
				logger.debug(errorMessage);
				throw new ResultException(errorMessage);
			}
			RefMetaDataViewBean refMetaDataViewBean = new RefMetaDataViewBean();
			refMetaDataViewBean.setIdItem(idItem);
			// refMetaDataViewBean.setShowItemsMap(showItemsMap);
			refMetaDataViewBean.setTitle(title);
			refMetaDataViewBean.setParentItem(parentItem);
			refMetaDataViewBean.setRefType(refType);
			refMetaDataViewBean.setMainShowItem(mainShowItem);
			refMetaDataViewBean.setRefKey(refBaseQuery.getTypeKey());
			refMetaDataViewBean.setPagination(refMetaDataService.getPagination());
			Map<String, String> tempShowItemsMap = new HashMap<String, String>();
			for (Entry<String, String> entry : showItemsMap.entrySet()) {
				tempShowItemsMap.put(entry.getKey(), entry.getValue());
			}
			// 如果存在虚拟字段，虚拟字段需要显示在参照弹出界面上
			Map<String, RefShowNameMetaData> refShowNameMetaDatasMap = refMetaDataService.getRefShowNameMetaDatasMap();
			if (refShowNameMetaDatasMap != null && refShowNameMetaDatasMap.size() > 0) {
				for (Entry<String, RefShowNameMetaData> entry : refShowNameMetaDatasMap.entrySet()) {
					if (entry != null && entry.getValue() != null) {
						Map<String, String> refVirtualItemsMap = entry.getValue().getRefVirtualItemsMap();
						if (refVirtualItemsMap != null && refVirtualItemsMap.size() > 0) {
							for (Entry<String, String> virEntry : refVirtualItemsMap.entrySet()) {
								if (virEntry != null) {
									String key = virEntry.getKey();
									String[] keys = key.split(",");
									tempShowItemsMap.put(keys[1], virEntry.getValue());
								}
							}
						}
					}
				}
			}
			// 看看是否配置了下拉
			Map<String, RefComboxMetaData> refComboxMetaDataMap = refMetaDataService.getRefComboxMetaDataMap();
			if (refComboxMetaDataMap != null && refComboxMetaDataMap.size() > 0) {
				for (Map.Entry<String, RefComboxMetaData> entry : refComboxMetaDataMap.entrySet()) {
					tempShowItemsMap.put(entry.getValue().getComboxShowName(), entry.getValue().getComboxName());
				}
			}
			refMetaDataViewBean.setShowItemsMap(tempShowItemsMap);
			return refMetaDataViewBean;
		}
		return null;
	}

	/**
	 * 获取查询翻译数据的SQL
	 * 
	 * @param refMetaDataService
	 * @param refBasePage
	 * @return
	 */
	public String getQueryTranslateDataSql(RefMetaDataService refMetaDataService, RefBaseQuery refBaseQuery) {
		if (refMetaDataService != null && refBaseQuery != null && refBaseQuery.getBatchIds() != null
				&& refBaseQuery.getBatchIds().size() > 0) {
			String idItem = refMetaDataService.getIdItem();
			String tableName = refMetaDataService.getTableName();
			Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
			String title = refMetaDataService.getTitle();
			// String condition = refMetaDataService.getCondition();
			String order = refMetaDataService.getOrder();
			String refType = refMetaDataService.getRefType();
			String parentItem = refMetaDataService.getParentItem();
			if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(tableName) || StringUtil.isEmpty(showItemsMap)
					|| StringUtil.isEmpty(title) || StringUtil.isEmpty(refType))
					|| (refType.equals(IRZConsts.TREE) && StringUtil.isEmpty(parentItem))) {
				logger.debug(errorMessage);
				throw new ResultException(errorMessage);
			}
			StringBuffer sql = new StringBuffer();
			sql.append("select ").append(ToolUtils.humpToLine(idItem) + " as " + idItem).append(",");
			if (!StringUtil.isEmpty(parentItem)) {
				sql.append(parentItem).append(",");
			}
			StringBuffer items = new StringBuffer();
			for (Map.Entry<String, String> entry : showItemsMap.entrySet()) {
				if (StringUtil.isEmpty(entry) || StringUtil.isEmpty(entry.getKey())
						|| StringUtil.isEmpty(entry.getValue())) {
					logger.debug(errorMessage);
					throw new ResultException(errorMessage);
				}
				String colKey = entry.getKey();
				String sqlCol = ToolUtils.humpToLine(colKey) + " as " + colKey;
				items.append(sqlCol).append(",");
			}

			sql.append(items.substring(0, items.length() - 1));

			// 其他必要数据：参照界面不显示，但是数据集里面需要包含这些数据
			Map<String, String> otherDataItemsMap = refMetaDataService.getOtherDataItemsMap();
			if (otherDataItemsMap != null && otherDataItemsMap.size() > 0) {
				StringBuffer otherItems = new StringBuffer();
				otherItems.append(",");
				for (Map.Entry<String, String> entry : otherDataItemsMap.entrySet()) {
					if (StringUtil.isEmpty(entry) || StringUtil.isEmpty(entry.getKey())
							|| StringUtil.isEmpty(entry.getValue())) {
						logger.debug(errorMessage);
						throw new ResultException(errorMessage);
					}
					String colKey = entry.getKey();
					String sqlCol = ToolUtils.humpToLine(colKey) + " as " + colKey;
					otherItems.append(sqlCol).append(",");
				}
				sql.append(otherItems.substring(0, otherItems.length() - 1));
			}

			sql.append(" from ").append(tableName);
			// 不需要增加查询条件，只翻译数据
			// boolean whereFlag = false;
			// if (!StringUtil.isEmpty(condition)) {
			// sql.append(" where ").append(condition);
			// whereFlag = true;
			// }
			// if (whereFlag) {
			// sql.append(" and ");
			// } else {
			// sql.append(" where ");
			// }
			sql.append(" where ");
			sql.append(ToolUtils.humpToLine(idItem)).append(" in ( ");
			for (int i = 0; i < refBaseQuery.getBatchIds().size(); i++) {
				sql.append(" '").append(refBaseQuery.getBatchIds().get(i)).append("' ");
				if (i != refBaseQuery.getBatchIds().size() - 1) {
					sql.append(",");
				}
			}
			sql.append(" ) ");
			if (!StringUtil.isEmpty(order)) {
				sql.append(" " + order + " ");
			}
			return sql.toString();
		} else {
			return null;
		}
	}

	/**
	 * 获取查询数据的SQL
	 * 
	 * @param refMetaDataService
	 * @param refBasePage
	 * @return
	 */
	public String getQueryDataSql(RefMetaDataService refMetaDataService, RefBasePage refBasePage,
			RefBaseQuery refBaseQuery) {
		if (refMetaDataService != null) {
			String idItem = refMetaDataService.getIdItem();
			String tableName = refMetaDataService.getTableName();
			Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
			String title = refMetaDataService.getTitle();
			String condition = refMetaDataService.getCondition();
			String order = refMetaDataService.getOrder();
			String refType = refMetaDataService.getRefType();
			String parentItem = refMetaDataService.getParentItem();
			String treeShowItem = refMetaDataService.getTreeShowItem();
			if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(tableName) || StringUtil.isEmpty(showItemsMap)
					|| StringUtil.isEmpty(title) || StringUtil.isEmpty(refType))
					|| (refType.equals(IRZConsts.TREE)
							&& (StringUtil.isEmpty(parentItem) || StringUtil.isEmpty(treeShowItem)))) {
				logger.debug(errorMessage);
				throw new ResultException(errorMessage);
			}
			StringBuffer sql = new StringBuffer();

			sql.append("select ").append(ToolUtils.humpToLine(idItem) + " as " + idItem).append(",");
			if (refType.equals(IRZConsts.TREE)) {
				sql.append(parentItem).append(",");
				sql.append(treeShowItem).append(",");
			}
			StringBuffer items = new StringBuffer();
			for (Map.Entry<String, String> entry : showItemsMap.entrySet()) {
				if (StringUtil.isEmpty(entry) || StringUtil.isEmpty(entry.getKey())
						|| StringUtil.isEmpty(entry.getValue())) {
					logger.debug(errorMessage);
					throw new ResultException(errorMessage);
				}
				String colKey = entry.getKey();
				String sqlCol = ToolUtils.humpToLine(colKey) + " as " + colKey;
				items.append(sqlCol).append(",");
			}
			// 树型参照需要将需要显示的字段也查出来
			if (refType.equals(IRZConsts.TREE)) {
				String[] treeShowItems = treeShowItem.split(",");
				if (treeShowItems != null && treeShowItems.length > 0) {
					for (int i = 0; i < treeShowItems.length; i++) {
						if (!showItemsMap.containsKey(treeShowItems[i])) {
							items.append(treeShowItems[i]).append(",");
						}
					}
				}
			}
			sql.append(items.substring(0, items.length() - 1));

			// 其他必要数据：参照界面不显示，但是数据集里面需要包含这些数据
			Map<String, String> otherDataItemsMap = refMetaDataService.getOtherDataItemsMap();
			if (otherDataItemsMap != null && otherDataItemsMap.size() > 0) {
				StringBuffer otherItems = new StringBuffer();
				otherItems.append(",");
				for (Map.Entry<String, String> entry : otherDataItemsMap.entrySet()) {
					if (StringUtil.isEmpty(entry) || StringUtil.isEmpty(entry.getKey())
							|| StringUtil.isEmpty(entry.getValue())) {
						logger.debug(errorMessage);
						throw new ResultException(errorMessage);
					}
					String colKey = entry.getKey();
					String sqlCol = ToolUtils.humpToLine(colKey) + " as " + colKey;
					otherItems.append(sqlCol).append(",");
				}
				sql.append(otherItems.substring(0, otherItems.length() - 1));
			}
			// 表名
			sql.append(" from ").append(tableName);

			sql.append(" where 1=1");
			if (!StringUtil.isEmpty(refBaseQuery.getQueryCondition())) {
				String[] conditions = refBaseQuery.getQueryCondition().split("and");
				for (String conStr : conditions) {
					sql.append(" and ").append(ToolUtils.humpToLine(conStr.split("=")[0]) + "="
							+ refBaseQuery.getQueryCondition().split("=")[1]);
				}
			}
			if (!StringUtil.isEmpty(condition)) {
				sql.append(" and ").append(condition);
			}

			if (!StringUtil.isEmpty(order)) {
				sql.append(" " + order + " ");
			}
			// 如果是树型参照则不需要分页,如果支持分页，需要根据参数查看当前
			if (refType.equals(IRZConsts.LIST) && refBasePage != null) {
				sql.append(" limit ");
				sql.append(refBasePage.getStartRow() - 1);
				sql.append(",").append(refBasePage.getEndRow());
			}
			return sql.toString();
		} else {
			return null;
		}
	}

	/**
	 * 获取查询记录总数的SQL
	 * 
	 * @param tableName
	 * @param condition
	 * @return
	 */
	public String getQueryDataCountSql(String tableName, String condition, RefBaseQuery refBaseQuery) {
		// 如果是树型参照则不需要分页,如果支持分页，需要根据参数查看当前
		StringBuffer countSql = new StringBuffer();
		countSql.append(" select count(1) from ");
		countSql.append(tableName);
		countSql.append(" where 1=1");
		if (!StringUtil.isEmpty(refBaseQuery.getQueryCondition())) {
			countSql.append(" and ").append(ToolUtils.humpToLine(refBaseQuery.getQueryCondition()));
		}
		if (!StringUtil.isEmpty(condition)) {
			countSql.append(" and ").append(condition);
		}
		return countSql.toString();
	}

	/**
	 * 获取树性参照数据
	 * 
	 * @param dataListMap
	 * @param idItem
	 * @param parentItem
	 * @param treeShowItem
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getRefLevelResultDataBeanLists(List<Object> dataListMap, String idItem, String parentItem,
			String treeShowItem, boolean isListData) {
		if (dataListMap == null || dataListMap.size() == 0 || StringUtil.isEmpty(idItem)
				|| StringUtil.isEmpty(parentItem)) {
			return null;
		}
		// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
		List<Object> refLevelResultDataBeanLists = new ArrayList<Object>();
		Map<String, String> isChildMap = new HashMap<String, String>();
		for (int i = 0; i < dataListMap.size(); i++) {
			Map<String, Object> entry = (Map<String, Object>) dataListMap.get(i);
			if (entry != null) {
				if (isChildMap.containsKey(entry.get(idItem).toString())) {
					continue;
				}
				RefLevelResultData refLevelResultData = new RefLevelResultData();
				refLevelResultData.setId(entry.get(idItem).toString());
				refLevelResultData.setNodeId(entry.get(idItem).toString());
				refLevelResultData.setText(getTreeShowName(entry, idItem, treeShowItem));
				refLevelResultData.setData(entry);
				if (!isListData) {
					getChildrenDatas(idItem, entry, parentItem, dataListMap, isChildMap, refLevelResultData,
							treeShowItem);
				}
				refLevelResultDataBeanLists.add(refLevelResultData);
			}
		}
		return refLevelResultDataBeanLists;
	}

	/**
	 * 获取树显示名称
	 * 
	 * @param entry
	 * @param idItem
	 * @param treeShowItem
	 * @return
	 */
	public String getTreeShowName(Map<String, Object> entry, String idItem, String treeShowItem) {
		String[] treeShowItems = treeShowItem.split(",");
		StringBuffer shouName = new StringBuffer();
		if (treeShowItems != null && treeShowItems.length > 0) {
			String temp = null;
			for (int j = 0; j < treeShowItems.length; j++) {
				temp = entry.get(treeShowItems[j]) != null ? entry.get(treeShowItems[j]).toString() : "";
				if (j == 0) {
					shouName.append(temp).append(" ");
				} else if (j > 0 && j != treeShowItems.length - 1) {
					shouName.append(" ").append(temp).append(" ");
				} else {
					shouName.append(temp);
				}
			}
		} else {
			shouName.append(entry.get(idItem));
		}
		return shouName.toString();
	}

	/*
	 * 获取孩子节点数据
	 */
	@SuppressWarnings("unchecked")
	public void getChildrenDatas(String idItem, Map<String, Object> entry, String parentItem, List<Object> dataListMap,
			Map<String, String> isChildMap, RefLevelResultData refLevelResultData, String treeShowItem) {
		if (dataListMap != null && dataListMap.size() > 0 && !StringUtil.isEmpty(entry.get(idItem).toString())
				&& !StringUtil.isEmpty(parentItem) && isChildMap != null) {
			List<RefLevelResultData> refLevelResultDataLists = new ArrayList<RefLevelResultData>();
			for (int j = 0; j < dataListMap.size(); j++) {
				Map<String, Object> child = (Map<String, Object>) dataListMap.get(j);
				if (child != null && child.get(parentItem) != null && child.get(parentItem).equals(entry.get(idItem))
						&& !child.get(parentItem).equals(child.get(idItem))) {
					RefLevelResultData refLevelResult = new RefLevelResultData();
					refLevelResult.setId(child.get(idItem).toString());
					refLevelResult.setNodeId(child.get(idItem).toString());
					refLevelResult.setText(getTreeShowName(child, idItem, treeShowItem));
					refLevelResult.setData(child);
					refLevelResultDataLists.add(refLevelResult);
					// 如果是孩子节点
					isChildMap.put(child.get(idItem).toString(), child.get(idItem).toString());
					getChildrenDatas(idItem, child, parentItem, dataListMap, isChildMap, refLevelResult, treeShowItem);
				}
			}
			if (refLevelResultDataLists.size() > 0) {
				refLevelResultData.setNodes(refLevelResultDataLists);
			}
		}
	}

	public RefMetaDataService getRefMetaDataService() {
		return refMetaDataService;
	}

	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
		this.refMetaDataService = refMetaDataService;
	}

	public void setRefDao(RefDao refDao) {
		this.refDao = refDao;
	}

	public RefDao getRefDao() {
		return refDao;
	}

}
