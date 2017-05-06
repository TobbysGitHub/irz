package com.imfbp.rz.service.orgbankaccref.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.smallorgbankacc.domain.SmallOrgBankAcc;
import com.ifbp.boss.rpc.smallorgbankacc.domain.query.SmallOrgBankAccQuery;
import com.ifbp.boss.rpc.smallorgbankacc.service.BossOrgBankAccRpcService;
import com.imfbp.rz.domain.ref.RefBasePage;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.orgbankaccref.OrgBankAccRpcRefService;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.imfbp.rz.service.ref.impl.DefaultRefServiceImpl;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;

@Component("orgBankAccRpcRefService")
public class OrgBankAccRpcRefServiceImpl implements OrgBankAccRpcRefService {

	@Autowired
	private BossOrgBankAccRpcService bossOrgBankAccRpcClient;

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
				SmallOrgBankAccQuery xmallOrgBankAccQuery = new SmallOrgBankAccQuery();
				xmallOrgBankAccQuery.setTenantId(refBaseQuery.getTenantId());
				xmallOrgBankAccQuery.setFkOrgId(refBaseQuery.getOrgId());
				List<SmallOrgBankAcc> smallOrgBankAccLists = bossOrgBankAccRpcClient
						.getBossOrgBankAccByCondition(xmallOrgBankAccQuery);
				// 如果分页，组装分页数据
				RefBasePage refBasePage = null;
				// 如果是树型参照则不需要分页
				if (refMetaDataService.getRefType().equals(IRZConsts.LIST)) {
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
					result.setRefBasePage(refBasePage);
				}
				// 需要根据主键和父主键组装数据为树型结构
				if (smallOrgBankAccLists != null
						&& smallOrgBankAccLists.size() > 0) {
					// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
					result.setDatas(getObjectListData(smallOrgBankAccLists));
					refBasePage.setTotalRows(smallOrgBankAccLists.size());
				}
				result.setRefBasePage(refBasePage);
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
					StringBuffer ids = new StringBuffer();
					for (int i = 0; i < refBaseQuery.getBatchIds().size(); i++) {
						ids.append("'");
						ids.append(refBaseQuery.getBatchIds().get(i));
						ids.append("'");
						if (i != refBaseQuery.getBatchIds().size() - 1) {
							ids.append(",");
						}
					}
					SmallOrgBankAccQuery xmallOrgBankAccQuery = new SmallOrgBankAccQuery();
					xmallOrgBankAccQuery.setOrgBankAccIds(ids.toString());
					List<SmallOrgBankAcc> smallOrgBankAccLists = bossOrgBankAccRpcClient
							.getBossOrgBankAccByIds(xmallOrgBankAccQuery);
					result.setDatas(getObjectListData(smallOrgBankAccLists));
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

	public List<Object> getObjectListData(
			List<SmallOrgBankAcc> smallOrgBankAccLists) {
		if (smallOrgBankAccLists != null && smallOrgBankAccLists.size() > 0) {
			List<Object> objectUserLists = new ArrayList<Object>(
					smallOrgBankAccLists.size());
			for (int i = 0; i < smallOrgBankAccLists.size(); i++) {
				objectUserLists.add(smallOrgBankAccLists.get(i));
			}
			return objectUserLists;
		}
		return null;
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

	public BossOrgBankAccRpcService getBossOrgBankAccRpcClient() {
		return bossOrgBankAccRpcClient;
	}

	public void setBossOrgBankAccRpcClient(
			BossOrgBankAccRpcService bossOrgBankAccRpcClient) {
		this.bossOrgBankAccRpcClient = bossOrgBankAccRpcClient;
	}

	public RefMetaDataService getRefMetaDataService() {
		return refMetaDataService;
	}

	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
		this.refMetaDataService = refMetaDataService;
	}

}
