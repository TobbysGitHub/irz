//package com.imfbp.rz.service.basecorpref.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.imfbp.basedoc.service.bdbasecorp.RpcBasecorpService;
//import com.imfbp.basedoc.service.domain.bdbasecorp.RpcBasecorp;
//import com.imfbp.basedoc.service.domain.bdbasecorp.query.RpcBasecorpQuery;
//import com.imfbp.rz.domain.pub.BeanHelper;
//import com.imfbp.rz.domain.ref.RefBaseQuery;
//import com.imfbp.rz.domain.ref.RefLevelResultData;
//import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
//import com.imfbp.rz.domain.ref.RefResult;
//import com.imfbp.rz.pub.IRZConsts;
//import com.imfbp.rz.service.basecorpref.BdBasecorpRefService;
//import com.imfbp.rz.service.ref.RefMetaDataService;
//import com.platform.common.utils.StringUtil;
//import com.platform.common.utils.exception.ResultException;
//
//public class BdBasecorpRefServiceImpl implements BdBasecorpRefService {
//
//	@Autowired
//	private RpcBasecorpService rpcBasecorpService;
//	
//	private RefMetaDataService refMetaDataService;
//
//	private final static Logger logger = Logger.getLogger(BdBasecorpRefServiceImpl.class);
//	
//
//	private final static String errorMessage = "参照元数据配置文件配置的元数据不对，请检查";
//	@Override
//	public RefResult getRefDatasByQuery(RefBaseQuery refBaseQuery)
//			throws Exception {
//		RefResult result = new RefResult();
//		try{
//			
//			checkrefMetaData(refMetaDataService);
//			RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(
//					refMetaDataService, refBaseQuery);
//			if (refMetaDataViewBean != null) {
//				result.setRefMetaDataBean(refMetaDataViewBean);
//			}
//			
//			RpcBasecorpQuery basecorpquery = new RpcBasecorpQuery();
//			List<RpcBasecorp> basecorpList = rpcBasecorpService.getBdBasecorpAll(basecorpquery);
//			
//			// 需要根据主键和父主键组装数据为树型结构
//			if (basecorpList != null && basecorpList.size() > 0) {
//				// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
//				result.setDatas(getRefLevelResultDataBeanLists(
//						basecorpList, refMetaDataService.getTreeShowItem(),
//						false));
//			}
//				
//			result.setSuccess(true);
//		} catch (Exception ex){
//			result.setSuccess(false);
//			logger.error(ex.getMessage(), ex);
//		}
//		return result;
//	}
//		
//	public boolean checkrefMetaData(RefMetaDataService refMetaDataService) {
//		if (refMetaDataService == null) {
//			logger.debug(errorMessage);
//			throw new ResultException(errorMessage);
//		}
//		String idItem = refMetaDataService.getIdItem();
//		Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
//		String title = refMetaDataService.getTitle();
//		String refType = refMetaDataService.getRefType();
//		String parentItem = refMetaDataService.getParentItem();
//		String mainShowItem = refMetaDataService.getMainShowItem();
//		if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(showItemsMap)
//				|| StringUtil.isEmpty(title)
//				|| StringUtil.isEmpty(mainShowItem) || StringUtil
//					.isEmpty(refType))
//				|| (refType.equals(IRZConsts.TREE) && StringUtil
//						.isEmpty(parentItem))) {
//			logger.debug(errorMessage);
//			throw new ResultException(errorMessage);
//		}
//		return true;
//	}
//	
//	/**
//	 * 获取返回给前台的元数据
//	 * 
//	 * @param refMetaDataService
//	 * @return
//	 */
//	public RefMetaDataViewBean getRefMetaDataViewBean(
//			RefMetaDataService refMetaDataService, RefBaseQuery refBaseQuery) {
//		if (refMetaDataService != null) {
//			checkrefMetaData(refMetaDataService);
//			RefMetaDataViewBean refMetaDataViewBean = new RefMetaDataViewBean();
//			refMetaDataViewBean.setIdItem(refMetaDataService.getIdItem());
//			refMetaDataViewBean.setShowItemsMap(refMetaDataService
//					.getShowItemsMap());
//			refMetaDataViewBean.setTitle(refMetaDataService.getTitle());
//			refMetaDataViewBean.setParentItem(refMetaDataService
//					.getParentItem());
//			refMetaDataViewBean.setRefType(refMetaDataService.getRefType());
//			refMetaDataViewBean.setMainShowItem(refMetaDataService
//					.getMainShowItem());
//			refMetaDataViewBean.setRefKey(refBaseQuery.getTypeKey());
//			return refMetaDataViewBean;
//		}
//		return null;
//	}
//	
//	/**
//	 * 获取树性参照数据
//	 * 
//	 * @param dataListMap
//	 * @param idItem
//	 * @param parentItem
//	 * @param treeShowItem
//	 * @return
//	 */
//	public List<Object> getRefLevelResultDataBeanLists(
//			List<RpcBasecorp> dataLists, String treeShowItem, boolean isListData) {
//		if (dataLists == null || dataLists.size() == 0) {
//			return null;
//		}
//		// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
//		List<Object> refLevelResultDataBeanLists = new ArrayList<Object>();
//		Map<String, String> isChildMap = new HashMap<String, String>();
//		for (int i = 0; i < dataLists.size(); i++) {
//			RpcBasecorp smallOrg = dataLists.get(i);
//			if (smallOrg != null) {
//				if (isChildMap.containsKey(smallOrg.getId())) {
//					continue;
//				}
//				RefLevelResultData refLevelResultData = new RefLevelResultData();
//				refLevelResultData.setId(smallOrg.getId());
//				refLevelResultData.setNodeId(smallOrg.getId());
//				refLevelResultData.setText(getTreeShowName(smallOrg,
//						treeShowItem));
//				refLevelResultData.setData(getMapObjectData(smallOrg));
//				if (!isListData) {
//					getChildrenDatas(smallOrg, dataLists, isChildMap,
//							refLevelResultData, treeShowItem);
//				}
//				refLevelResultDataBeanLists.add(refLevelResultData);
//			}
//		}
//		return refLevelResultDataBeanLists;
//	}
//	
//	/*
//	 * 获取孩子节点数据
//	 */
//	public void getChildrenDatas(RpcBasecorp orgEntry, List<RpcBasecorp> dataLists,
//			Map<String, String> isChildMap,
//			RefLevelResultData refLevelResultData, String treeShowItem) {
//		if (dataLists != null && dataLists.size() > 0
//				&& !StringUtil.isEmpty(orgEntry.getId()) && isChildMap != null) {
//			List<RefLevelResultData> refLevelResultDataLists = new ArrayList<RefLevelResultData>();
//			for (int j = 0; j < dataLists.size(); j++) {
//				RpcBasecorp child = dataLists.get(j);
//				if (child != null && child.getFkParent() != null
//						&& child.getFkParent().equals(orgEntry.getId())
//						&& !child.getFkParent().equals(child.getId())) {
//					RefLevelResultData refLevelResult = new RefLevelResultData();
//					refLevelResult.setId(child.getId());
//					refLevelResult.setNodeId(child.getId());
//					refLevelResult
//							.setText(getTreeShowName(child, treeShowItem));
//					refLevelResult.setData(getMapObjectData(child));
//					refLevelResultDataLists.add(refLevelResult);
//					// 如果是孩子节点
//					isChildMap.put(child.getId(), child.getId());
//					getChildrenDatas(child, dataLists, isChildMap,
//							refLevelResult, treeShowItem);
//				}
//			}
//			if (refLevelResultDataLists.size() > 0) {
//				refLevelResultData.setNodes(refLevelResultDataLists);
//			}
//		}
//	}
//	
//	@Override
//	public RefResult getRefDatasByBatchId(RefBaseQuery refBaseQuery)
//			throws Exception {
//		RefResult result = new RefResult();
//		try {
//			if (refMetaDataService != null && refBaseQuery != null) {
//				checkrefMetaData(refMetaDataService);
//				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(
//						refMetaDataService, refBaseQuery);
//				if (refMetaDataViewBean != null) {
//					result.setRefMetaDataBean(refMetaDataViewBean);
//				}
//				List<String> batchIds = refBaseQuery.getBatchIds();
//				if (batchIds != null && batchIds.size() > 0) {
//					StringBuffer inscorpIds = new StringBuffer();
//					for (int i = 0; i < batchIds.size(); i++) {
//						inscorpIds.append(batchIds.get(i));
//						if (i != batchIds.size() - 1) {
//							inscorpIds.append(",");
//						}
//					}
//					RpcBasecorpQuery basecorpquery = new RpcBasecorpQuery();
//					basecorpquery.setBatchId(inscorpIds.toString());
//					List<RpcBasecorp> dataLists = rpcBasecorpService
//							.getBdBasecorpAll(basecorpquery);
//					result.setDatas(getRefLevelResultDataBeanLists(dataLists,
//							refMetaDataService.getTreeShowItem(), true));
//				}
//			}
//			result.setSuccess(true);
//		} catch (Exception e) {
//			result.setSuccess(false);
//			logger.error(e.getMessage(), e);
//		}
//
//		return result;
//	}
//
//	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
//		this.refMetaDataService = refMetaDataService;
//	}
//	
//	/**
//	 * 获取树显示名称
//	 * 
//	 * @param entry
//	 * @param idItem
//	 * @param treeShowItem
//	 * @return
//	 */
//	public String getTreeShowName(RpcBasecorp basecorp, String treeShowItem) {
//		String[] treeShowItems = treeShowItem.split(",");
//		StringBuffer shouName = new StringBuffer();
//		if (treeShowItems != null && treeShowItems.length > 0) {
//			String temp = null;
//			for (int j = 0; j < treeShowItems.length; j++) {
//				temp = BeanHelper.getProperty(basecorp, treeShowItems[j]) != null ? BeanHelper
//						.getProperty(basecorp, treeShowItems[j]).toString()
//						: "";
//				if (j == 0) {
//					shouName.append(temp).append(" ");
//				} else if (j > 0 && j != treeShowItems.length - 1) {
//					shouName.append(" ").append(temp).append(" ");
//				} else {
//					shouName.append(temp);
//				}
//			}
//		} else {
//			shouName.append(basecorp.getName());
//		}
//		return shouName.toString();
//	}
//
//	public Map<String, Object> getMapObjectData(RpcBasecorp basecorp) {
//		if (basecorp == null) {
//			return null;
//		}
//		Map<String, Object> orgEntry = new HashMap<String, Object>();
//
//		String[] fields = BeanHelper.getPropertiesAry(basecorp);
//		if (fields != null) {
//			for (int i = 0; i < fields.length; i++) {
//				orgEntry.put(fields[i],
//						BeanHelper.getProperty(basecorp, fields[i]));
//			}
//		}
//		return orgEntry;
//
//	}
//
//
//}
