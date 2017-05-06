package com.imfbp.rz.service.orgref.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.smallorg.domain.SmallOrg;
import com.ifbp.boss.rpc.smallorg.domain.query.SmallOrgQuery;
import com.ifbp.boss.rpc.smallorg.service.BossOrgRpcService;
import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefLevelResultData;
import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.orgref.BdOrgRefRpcService;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.imfbp.rz.service.ref.impl.DefaultRefServiceImpl;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;

@Component("bdOrgRpcService")
public class BdOrgRefRpcServiceImpl implements BdOrgRefRpcService {

	@Autowired
	private BossOrgRpcService bossOrgRpcService;

	private RefMetaDataService refMetaDataService;

	private final static Logger logger = Logger
			.getLogger(DefaultRefServiceImpl.class);

	private final static String errorMessage = "参照元数据配置文件配置的元数据不对，请检查";

	@Override
	public RefResult getRefDatasByQuery(RefBaseQuery refBaseQuery)
			throws Exception {
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
				SmallOrgQuery smallOrgQuery = new SmallOrgQuery();
				String orgId = "";
				if (refBaseQuery != null && refBaseQuery.getOrgId() != null) {
					// 当前登录人只能查询当前机构及下属机构
					orgId = refBaseQuery.getOrgId();
				}
				// 000001
				smallOrgQuery.setTenantId(refBaseQuery.getTenantId());
				// 取出所有机构，单独处理
				List<SmallOrg> orgLists = bossOrgRpcService
						.getBossOrgByCondition(smallOrgQuery);

				List<SmallOrg> filterdLists = new ArrayList<SmallOrg>();

				// 需要根据主键和父主键组装数据为树型结构
				if (orgLists != null && orgLists.size() > 0) {
					findSubOrg(orgLists, filterdLists, orgId);
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

	@SuppressWarnings("rawtypes")
	private void findSubOrg(List<SmallOrg> orgLists,
			List<SmallOrg> filterdLists, String orgId) {

		for (Iterator iterator = orgLists.iterator(); iterator.hasNext();) {
			SmallOrg smallOrg = (SmallOrg) iterator.next();
			if (smallOrg.getId().equals(orgId)) {
				filterdLists.add(smallOrg);
			} else if (smallOrg.getOrgPid().equals(orgId)) {
				findSubOrg(orgLists, filterdLists, smallOrg.getId());
			}
		}

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
					SmallOrgQuery smallOrgQuery = new SmallOrgQuery();
					smallOrgQuery.setOrgIds(orgIds.toString());
					List<SmallOrg> dataLists = bossOrgRpcService
							.getBossOrgByIds(smallOrgQuery);
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
			List<SmallOrg> dataLists, String treeShowItem, boolean isListData) {
		if (dataLists == null || dataLists.size() == 0) {
			return null;
		}
		// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
		List<Object> refLevelResultDataBeanLists = new ArrayList<Object>();
		Map<String, String> isChildMap = new HashMap<String, String>();
		for (int i = 0; i < dataLists.size(); i++) {
			SmallOrg smallOrg = dataLists.get(i);
			if (smallOrg != null) {
				if (isChildMap.containsKey(smallOrg.getId())) {
					continue;
				}
				RefLevelResultData refLevelResultData = new RefLevelResultData();
				refLevelResultData.setId(smallOrg.getId());
				refLevelResultData.setNodeId(smallOrg.getId());
				refLevelResultData.setText(getTreeShowName(smallOrg,
						treeShowItem));
				refLevelResultData.setData(getMapObjectData(smallOrg));
				if (!isListData) {
					getChildrenDatas(smallOrg, dataLists, isChildMap,
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
	public String getTreeShowName(SmallOrg smallOrg, String treeShowItem) {
		String[] treeShowItems = treeShowItem.split(",");
		StringBuffer shouName = new StringBuffer();
		if (treeShowItems != null && treeShowItems.length > 0) {
			String temp = null;
			for (int j = 0; j < treeShowItems.length; j++) {
				temp = BeanHelper.getProperty(smallOrg, treeShowItems[j]) != null ? BeanHelper
						.getProperty(smallOrg, treeShowItems[j]).toString()
						: "";
				if (j == 0) {
					shouName.append(temp).append(" ");
				} else if (j > 0 && j != treeShowItems.length - 1) {
					shouName.append(" ").append(temp).append(" ");
				} else {
					shouName.append(temp);
				}
			}
		} else {
			shouName.append(smallOrg.getOrgName());
		}
		return shouName.toString();
	}

	/*
	 * 获取孩子节点数据
	 */
	public void getChildrenDatas(SmallOrg orgEntry, List<SmallOrg> dataLists,
			Map<String, String> isChildMap,
			RefLevelResultData refLevelResultData, String treeShowItem) {
		if (dataLists != null && dataLists.size() > 0
				&& !StringUtil.isEmpty(orgEntry.getId()) && isChildMap != null) {
			List<RefLevelResultData> refLevelResultDataLists = new ArrayList<RefLevelResultData>();
			for (int j = 0; j < dataLists.size(); j++) {
				SmallOrg child = dataLists.get(j);
				if (child != null && child.getOrgPid() != null
						&& child.getOrgPid().equals(orgEntry.getId())
						&& !child.getOrgPid().equals(child.getId())) {
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

	public Map<String, Object> getMapObjectData(SmallOrg smallOrg) {
		if (smallOrg == null) {
			return null;
		}
		Map<String, Object> orgEntry = new HashMap<String, Object>();

		String[] fields = BeanHelper.getPropertiesAry(smallOrg);
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				orgEntry.put(fields[i],
						BeanHelper.getProperty(smallOrg, fields[i]));
			}
		}
		return orgEntry;

	}

	public BossOrgRpcService getBossOrgRpcService() {
		return bossOrgRpcService;
	}

	public void setBossOrgRpcService(BossOrgRpcService bossOrgRpcService) {
		this.bossOrgRpcService = bossOrgRpcService;
	}

	public RefMetaDataService getRefMetaDataService() {
		return refMetaDataService;
	}

	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
		this.refMetaDataService = refMetaDataService;
	}

}
