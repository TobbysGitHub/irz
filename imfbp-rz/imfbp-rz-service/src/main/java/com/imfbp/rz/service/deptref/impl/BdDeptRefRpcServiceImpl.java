package com.imfbp.rz.service.deptref.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.smalldeptinfo.domain.SmallDeptInfo;
import com.ifbp.boss.rpc.smalldeptinfo.domain.query.SmallDeptInfoQuery;
import com.ifbp.boss.rpc.smalldeptinfo.service.BossDeptInfoRpcService;
import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefLevelResultData;
import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.deptref.BdDeptRefRpcService;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.imfbp.rz.service.ref.impl.DefaultRefServiceImpl;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;

@Component("bdDeptRefRpcService")
public class BdDeptRefRpcServiceImpl implements BdDeptRefRpcService {

	@Autowired
	private BossDeptInfoRpcService bossDeptInfoRpcService;

	private RefMetaDataService refMetaDataService;

	private final static Logger logger = Logger
			.getLogger(DefaultRefServiceImpl.class);

	private final static String errorMessage = "参照元数据配置文件配置的元数据不对，请检查";

	@Override
	public RefResult getRefDatasByQuery(RefBaseQuery refBaseQuery)
			throws Exception {
		// TODO Auto-generated method stub
		RefResult result = new RefResult();
		try {
			if (refMetaDataService != null) {
				checkrefMetaData(refMetaDataService);
				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(
						refMetaDataService, refBaseQuery);
				if (refMetaDataViewBean != null) {
					result.setRefMetaDataBean(refMetaDataViewBean);
				}
				// 默认查询当前登录人只能查询当前机构及下属机构
				SmallDeptInfoQuery smallDeptInfoQuery = new SmallDeptInfoQuery();
				String orgId = "";
				if (refBaseQuery != null && refBaseQuery.getOrgId() != null) {
					// 当前登录人只能查询当前机构及下属机构
					orgId = refBaseQuery.getOrgId();
				}
				// smallDeptInfoQuery.setFkOrgId("000001BOSS0FK5000005");
				smallDeptInfoQuery.setTenantId(refBaseQuery.getTenantId());
				// 取出所有部门，单独处理
				List<SmallDeptInfo> deptLists = bossDeptInfoRpcService
						.getBossDeptInfoByCondition(smallDeptInfoQuery);

				List<SmallDeptInfo> filterdLists = new ArrayList<SmallDeptInfo>();

				// 需要根据主键和父主键组装数据为树型结构
				if (deptLists != null && deptLists.size() > 0) {
					findSubOrg(deptLists, filterdLists, orgId);
					// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
					result.setDatas(getRefLevelResultDataBeanLists(
							filterdLists, refMetaDataService.getTreeShowItem(),
							false));
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

	@Override
	public RefResult getRefDatasByBatchId(RefBaseQuery refBaseQuery)
			throws Exception {
		// TODO Auto-generated method stub

		RefResult result = new RefResult();
		try {
			if (refMetaDataService != null && refBaseQuery != null) {
				checkrefMetaData(refMetaDataService);
				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(
						refMetaDataService, refBaseQuery);
				if (refMetaDataViewBean != null) {
					result.setRefMetaDataBean(refMetaDataViewBean);
				}
				List<String> batchIds = refBaseQuery.getBatchIds();
				if (batchIds != null && batchIds.size() > 0) {
					StringBuffer orgIds = new StringBuffer();
					for (int i = 0; i < refBaseQuery.getBatchIds().size(); i++) {
						orgIds.append("'");
						orgIds.append(refBaseQuery.getBatchIds().get(i));
						orgIds.append("'");
						if (i != refBaseQuery.getBatchIds().size() - 1) {
							orgIds.append(",");
						}
					}
					SmallDeptInfoQuery smallDeptInfoQuery = new SmallDeptInfoQuery();
					smallDeptInfoQuery.setDeptInfoIds(orgIds.toString());
					List<SmallDeptInfo> dataLists = bossDeptInfoRpcService
							.getBossDeptInfoByIds(smallDeptInfoQuery);
					result.setDatas(getRefLevelResultDataBeanLists(dataLists,
							refMetaDataService.getTreeShowItem(), true));
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

	@SuppressWarnings("rawtypes")
	private void findSubOrg(List<SmallDeptInfo> deptLists,
			List<SmallDeptInfo> filterdLists, String orgId) {
		for (Iterator iterator = deptLists.iterator(); iterator.hasNext();) {
			SmallDeptInfo smallDeptInfo = (SmallDeptInfo) iterator.next();
			if (smallDeptInfo.getId().equals(orgId)) {
				filterdLists.add(smallDeptInfo);
			} else if (smallDeptInfo.getDeptPid().equals(orgId)) {
				findSubOrg(deptLists, filterdLists, smallDeptInfo.getId());
			}
		}

	}

	public boolean checkrefMetaData(RefMetaDataService refMetaDataService) {
		if (refMetaDataService == null) {
			logger.debug(errorMessage);
			throw new ResultException(errorMessage);
		}
		String idItem = refMetaDataService.getIdItem();
		Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
		String title = refMetaDataService.getTitle();
		String refType = refMetaDataService.getRefType();
		String parentItem = refMetaDataService.getParentItem();
		String mainShowItem = refMetaDataService.getMainShowItem();
		if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(showItemsMap)
				|| StringUtil.isEmpty(title)
				|| StringUtil.isEmpty(mainShowItem) || StringUtil
					.isEmpty(refType))
				|| (refType.equals(IRZConsts.TREE) && StringUtil
						.isEmpty(parentItem))) {
			logger.debug(errorMessage);
			throw new ResultException(errorMessage);
		}
		return true;
	}

	/**
	 * 获取返回给前台的元数据
	 * 
	 * @param refMetaDataService
	 * @return
	 */
	public RefMetaDataViewBean getRefMetaDataViewBean(
			RefMetaDataService refMetaDataService, RefBaseQuery refBaseQuery) {
		if (refMetaDataService != null) {
			checkrefMetaData(refMetaDataService);
			RefMetaDataViewBean refMetaDataViewBean = new RefMetaDataViewBean();
			refMetaDataViewBean.setIdItem(refMetaDataService.getIdItem());
			refMetaDataViewBean.setShowItemsMap(refMetaDataService
					.getShowItemsMap());
			refMetaDataViewBean.setTitle(refMetaDataService.getTitle());
			refMetaDataViewBean.setParentItem(refMetaDataService
					.getParentItem());
			refMetaDataViewBean.setRefType(refMetaDataService.getRefType());
			refMetaDataViewBean.setMainShowItem(refMetaDataService
					.getMainShowItem());
			refMetaDataViewBean.setRefKey(refBaseQuery.getTypeKey());
			return refMetaDataViewBean;
		}
		return null;
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
	public List<Object> getRefLevelResultDataBeanLists(
			List<SmallDeptInfo> dataLists, String treeShowItem,
			boolean isListData) {
		if (dataLists == null || dataLists.size() == 0) {
			return null;
		}
		// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
		List<Object> refLevelResultDataBeanLists = new ArrayList<Object>();
		Map<String, String> isChildMap = new HashMap<String, String>();
		for (int i = 0; i < dataLists.size(); i++) {
			SmallDeptInfo smallDeptInfo = dataLists.get(i);
			if (smallDeptInfo != null) {
				if (isChildMap.containsKey(smallDeptInfo.getId())) {
					continue;
				}
				RefLevelResultData refLevelResultData = new RefLevelResultData();
				refLevelResultData.setId(smallDeptInfo.getId());
				refLevelResultData.setNodeId(smallDeptInfo.getId());
				refLevelResultData.setText(getTreeShowName(smallDeptInfo,
						treeShowItem));
				refLevelResultData.setData(getMapObjectData(smallDeptInfo));
				if (!isListData) {
					getChildrenDatas(smallDeptInfo, dataLists, isChildMap,
							refLevelResultData, treeShowItem);
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
	public String getTreeShowName(SmallDeptInfo smallDeptInfo,
			String treeShowItem) {
		String[] treeShowItems = treeShowItem.split(",");
		StringBuffer shouName = new StringBuffer();
		if (treeShowItems != null && treeShowItems.length > 0) {
			String temp = null;
			for (int j = 0; j < treeShowItems.length; j++) {
				temp = BeanHelper.getProperty(smallDeptInfo, treeShowItems[j]) != null ? BeanHelper
						.getProperty(smallDeptInfo, treeShowItems[j])
						.toString() : "";
				if (j == 0) {
					shouName.append(temp).append(" ");
				} else if (j > 0 && j != treeShowItems.length - 1) {
					shouName.append(" ").append(temp).append(" ");
				} else {
					shouName.append(temp);
				}
			}
		} else {
			shouName.append(smallDeptInfo.getDeptName());
		}
		return shouName.toString();
	}

	/*
	 * 获取孩子节点数据
	 */
	public void getChildrenDatas(SmallDeptInfo smallDeptInfo,
			List<SmallDeptInfo> dataLists, Map<String, String> isChildMap,
			RefLevelResultData refLevelResultData, String treeShowItem) {
		if (dataLists != null && dataLists.size() > 0
				&& !StringUtil.isEmpty(smallDeptInfo.getId())
				&& isChildMap != null) {
			List<RefLevelResultData> refLevelResultDataLists = new ArrayList<RefLevelResultData>();
			for (int j = 0; j < dataLists.size(); j++) {
				SmallDeptInfo child = dataLists.get(j);
				if (child != null && child.getDeptPid() != null
						&& child.getDeptPid().equals(smallDeptInfo.getId())
						&& !child.getDeptPid().equals(child.getId())) {
					RefLevelResultData refLevelResult = new RefLevelResultData();
					refLevelResult.setId(child.getId());
					refLevelResult.setNodeId(child.getId());
					refLevelResult
							.setText(getTreeShowName(child, treeShowItem));
					refLevelResult.setData(getMapObjectData(child));
					refLevelResultDataLists.add(refLevelResult);
					// 如果是孩子节点
					isChildMap.put(child.getId(), child.getId());
					getChildrenDatas(child, dataLists, isChildMap,
							refLevelResult, treeShowItem);
				}
			}
			if (refLevelResultDataLists.size() > 0) {
				refLevelResultData.setNodes(refLevelResultDataLists);
			}
		}
	}

	public Map<String, Object> getMapObjectData(SmallDeptInfo smallDeptInfo) {
		if (smallDeptInfo == null) {
			return null;
		}
		Map<String, Object> orgEntry = new HashMap<String, Object>();

		String[] fields = BeanHelper.getPropertiesAry(smallDeptInfo);
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				orgEntry.put(fields[i],
						BeanHelper.getProperty(smallDeptInfo, fields[i]));
			}
		}
		return orgEntry;

	}

	public BossDeptInfoRpcService getBossDeptInfoRpcService() {
		return bossDeptInfoRpcService;
	}

	public void setBossDeptInfoRpcService(
			BossDeptInfoRpcService bossDeptInfoRpcService) {
		this.bossDeptInfoRpcService = bossDeptInfoRpcService;
	}

	public RefMetaDataService getRefMetaDataService() {
		return refMetaDataService;
	}

	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
		this.refMetaDataService = refMetaDataService;
	}

}
