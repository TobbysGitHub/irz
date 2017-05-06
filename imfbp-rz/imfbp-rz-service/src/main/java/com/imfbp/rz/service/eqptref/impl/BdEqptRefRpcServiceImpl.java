package com.imfbp.rz.service.eqptref.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.smallorg.domain.SmallOrg;
import com.ifbp.boss.rpc.smallorg.domain.query.SmallOrgQuery;
import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.ref.RefBasePage;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefLevelResultData;
import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.orgref.BdOrgRefRpcService;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.imfbp.rz.service.ref.impl.DefaultRefServiceImpl;
import com.imfbp.rz.service.rzeqpt.RzEqptService;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;

@Component("eqptRefService")
public class BdEqptRefRpcServiceImpl implements BdOrgRefRpcService {

	@Autowired
	private RzEqptService rzEqptService;

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
				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(
						refMetaDataService, refBaseQuery);
				result.setRefMetaDataBean(refMetaDataViewBean);
				
				RzEqptQuery rzEqptQuery = new RzEqptQuery();
						rzEqptQuery.setStartRow((refBaseQuery.getPage() - 1) * refBaseQuery.getRows());
				rzEqptQuery.setLimit(refBaseQuery.getRows());
				
				// 如果分页，组装分页数据
				RefBasePage refBasePage = null;
				// 如果是树型参照则不需要分页
				if (refMetaDataService.getRefType().equals(IRZConsts.LIST)) {
					refBasePage = new RefBasePage();
					int page = refBaseQuery.getPage();
					page = page != 0 ? page : 1;
					int rows = refBaseQuery.getRows();
					rows = rows != 0 ? rows : 5;
					refBasePage.setPage(page);
					refBasePage.setStartRow((page - 1) * rows + 1);
					refBasePage.setEndRow(page * rows);
					refBasePage.setPageSize(rows);
					result.setRefBasePage(refBasePage);
//					String countSql = getQueryDataCountSql(tableName,
//							condition, refBaseQuery);
					int count = rzEqptService.getRzEqptByPageCount(new RzEqptQuery());
					refBasePage.setTotalRows(count);
					result.setRefBasePage(refBasePage);
				}
				// 取出所有机构，单独处理
				List<RzEqpt> rzEqptLists = rzEqptService
						.getRzEqptAll(rzEqptQuery);
				List<Object> filterdLists = new ArrayList<Object>();
				
				filterdLists.addAll(rzEqptLists);
				result.setDatas(filterdLists);
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
					SmallOrgQuery smallOrgQuery = new SmallOrgQuery();
					smallOrgQuery.setTenantId(refBaseQuery.getTenantId());
					// 取出所有机构，单独处理
					List<RzEqpt> rzEqptLists = rzEqptService
							.getRzEqptAll(new RzEqptQuery());
					List<Object> filterdLists = new ArrayList<Object>();
					filterdLists.addAll(rzEqptLists);
					result.setDatas(filterdLists);
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


	public RefMetaDataService getRefMetaDataService() {
		return refMetaDataService;
	}

	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
		this.refMetaDataService = refMetaDataService;
	}

	public void setRzEqptService(RzEqptService rzEqptService) {
		this.rzEqptService = rzEqptService;
	}
	
}
