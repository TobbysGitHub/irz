package com.imfbp.rz.service.personref.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ifbp.boss.rpc.smallpersonnelinformation.domain.SmallPersonnelInformation;
import com.ifbp.boss.rpc.smallpersonnelinformation.domain.query.SmallPersonnelInformationQuery;
import com.ifbp.boss.rpc.smallpersonnelinformation.service.BossPersonnelInformationRpcService;
import com.imfbp.rz.domain.ref.RefBasePage;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.personref.BdPersonRefService;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;

public class BdPersonRefServiceImpl implements BdPersonRefService {

	@Autowired
	private BossPersonnelInformationRpcService bossPersonService;

	private RefMetaDataService refMetaDataService;

	private final static Logger logger = Logger.getLogger(BdPersonRefServiceImpl.class);

	private final static String errorMessage = "参照元数据配置文件配置的元数据不对，请检查";

	@Override
	public RefResult getRefDatasByQuery(RefBaseQuery refBaseQuery) throws Exception {

		RefResult result = new RefResult();
		try {

			checkrefMetaData(refMetaDataService);
			RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(refMetaDataService, refBaseQuery);
			if (refMetaDataViewBean != null) {
				result.setRefMetaDataBean(refMetaDataViewBean);
			}
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
			}

			SmallPersonnelInformationQuery personQuery = new SmallPersonnelInformationQuery();
			personQuery.setFkOrgId(refBaseQuery.getOrgId());
			personQuery.setDelState(0);
			personQuery.setListPersonnelState(1);
			List<SmallPersonnelInformation> personList = bossPersonService
					.getBossPersonnelInformationByCondition(personQuery);

			if (personList != null && personList.size() > 0) {

				// 需要根据主键和父主键组装数据为树型结构
				if (personList != null && personList.size() > 0) {
					// 如果是树型参照需要重新组装一次数据,让数据保持层级关系
					result.setDatas(getObjectListData(personList));
					refBasePage.setTotalRows(personList.size());
				}

			}

			result.setSuccess(true);
		} catch (Exception ex) {
			result.setSuccess(false);
			logger.error(ex.getMessage(), ex);
		}
		return result;

	}

	public List<Object> getObjectListData(List<SmallPersonnelInformation> smallOrgBankAccLists) {
		if (smallOrgBankAccLists != null && smallOrgBankAccLists.size() > 0) {
			List<Object> objectUserLists = new ArrayList<Object>(smallOrgBankAccLists.size());
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
	public RefMetaDataViewBean getRefMetaDataViewBean(RefMetaDataService refMetaDataService,
			RefBaseQuery refBaseQuery) {
		if (refMetaDataService != null) {
			checkrefMetaData(refMetaDataService);
			RefMetaDataViewBean refMetaDataViewBean = new RefMetaDataViewBean();
			refMetaDataViewBean.setIdItem(refMetaDataService.getIdItem());
			refMetaDataViewBean.setShowItemsMap(refMetaDataService.getShowItemsMap());
			refMetaDataViewBean.setTitle(refMetaDataService.getTitle());
			refMetaDataViewBean.setParentItem(refMetaDataService.getParentItem());
			refMetaDataViewBean.setRefType(refMetaDataService.getRefType());
			refMetaDataViewBean.setMainShowItem(refMetaDataService.getMainShowItem());
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
		if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(showItemsMap) || StringUtil.isEmpty(title)
				|| StringUtil.isEmpty(mainShowItem) || StringUtil.isEmpty(refType))
				|| (refType.equals(IRZConsts.TREE) && StringUtil.isEmpty(parentItem))) {
			logger.debug(errorMessage);
			throw new ResultException(errorMessage);
		}
		return true;
	}

	@Override
	public RefResult getRefDatasByBatchId(RefBaseQuery refBaseQuery) throws Exception {

		RefResult result = new RefResult();
		try {
			if (refMetaDataService != null && refBaseQuery != null) {
				checkrefMetaData(refMetaDataService);
				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(refMetaDataService, refBaseQuery);
				if (refMetaDataViewBean != null) {
					result.setRefMetaDataBean(refMetaDataViewBean);
				}
				List<String> batchIds = refBaseQuery.getBatchIds();
				if (batchIds != null && batchIds.size() > 0) {
					StringBuffer personIds = new StringBuffer();
					for (int i = 0; i < batchIds.size(); i++) {
						personIds.append(batchIds.get(i));
						if (i != batchIds.size() - 1) {
							personIds.append(",");
						}
					}
					SmallPersonnelInformationQuery personQuery = new SmallPersonnelInformationQuery();
					personQuery.setBatchId(personIds.toString());
					List<SmallPersonnelInformation> personList = bossPersonService
							.getBossPersonnelInformationByCondition(personQuery);

					if (personList != null && personList.size() > 0) {
						if (personList != null && personList.size() > 0) {
							result.setDatas(getObjectListData(personList));
						}
					}
				}
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	public void setBossPersonService(BossPersonnelInformationRpcService bossPersonService) {
		this.bossPersonService = bossPersonService;
	}

	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
		this.refMetaDataService = refMetaDataService;
	}

}
