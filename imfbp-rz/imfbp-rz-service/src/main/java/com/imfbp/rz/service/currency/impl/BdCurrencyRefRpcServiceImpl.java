package com.imfbp.rz.service.currency.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.currency.domain.Currency;
import com.ifbp.boss.rpc.currency.domain.query.CurrencyQuery;
import com.ifbp.boss.rpc.currency.service.BossCurrencyRpcService;
import com.imfbp.rz.domain.ref.RefBasePage;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefMetaDataViewBean;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.currency.BdCurrencyRefRpcService;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.imfbp.rz.service.ref.impl.DefaultRefServiceImpl;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;

@Component("bdCurrencyRefRpcService")
public class BdCurrencyRefRpcServiceImpl implements BdCurrencyRefRpcService {



	private BossCurrencyRpcService currencyRpcClient;

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
				// 校验配置文件是否满足要求
				checkrefMetaData(refMetaDataService, refBaseQuery);
				// 获取返回给前端的元数据
				RefMetaDataViewBean refMetaDataViewBean = getRefMetaDataViewBean(
						refMetaDataService, refBaseQuery);
				if (refMetaDataViewBean != null) {
					result.setRefMetaDataBean(refMetaDataViewBean);
				}
				// 获取数据
				CurrencyQuery currencyQuery = new CurrencyQuery();
				List<Currency> currencyList = currencyRpcClient
						.getCurrencyAll(currencyQuery);
				// 币种默认不分页，但是分页参数依然传回去
				RefBasePage refBasePage = new RefBasePage();
				int page = refBaseQuery.getPage();
				page = page != 0 ? page : 1;
				int rows = refBaseQuery.getRows();
				rows = rows != 0 ? rows : IRZConsts.PAGESIZE;
				result.setRefBasePage(refBasePage);
				if (currencyList != null)
					refBasePage.setTotalRows(currencyList.size());
				result.setRefBasePage(refBasePage);
				if (currencyList != null && currencyList.size() > 0) {
					result.setDatas(getObjectListData(currencyList));
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
	public RefResult getRefDatasByBatchId(RefBaseQuery refBaseQuery)
			throws Exception {
		// 字典类型数据的参照，获取翻译数据时，默认返回全部数据
		return getRefDatasByQuery(refBaseQuery);
	}

	public List<Object> getObjectListData(List<Currency> currencyList) {
		if (currencyList != null && currencyList.size() > 0) {
			List<Object> objectDicItemLists = new ArrayList<Object>(
					currencyList.size());
			for (int i = 0; i < currencyList.size(); i++) {
				objectDicItemLists.add(currencyList.get(i));
			}
			return objectDicItemLists;
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
			checkrefMetaData(refMetaDataService, refBaseQuery);
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
			refMetaDataViewBean.setPagination(refMetaDataService
					.getPagination());
			return refMetaDataViewBean;
		}
		return null;
	}

	/**
	 * 校验配置文件是否满足要求
	 * 
	 * @param refMetaDataService
	 * @param refBaseQuery
	 * @return
	 */
	public boolean checkrefMetaData(RefMetaDataService refMetaDataService,
			RefBaseQuery refBaseQuery) {
		if (refMetaDataService == null || refBaseQuery == null) {
			logger.debug(errorMessage);
			throw new ResultException(errorMessage);
		}
		//String dicItemCode = refBaseQuery.getDicItemCode();
		String idItem = refMetaDataService.getIdItem();
		Map<String, String> showItemsMap = refMetaDataService.getShowItemsMap();
		String title = refMetaDataService.getTitle();
		String refType = refMetaDataService.getRefType();
		String parentItem = refMetaDataService.getParentItem();
		String mainShowItem = refMetaDataService.getMainShowItem();
		if ((StringUtil.isEmpty(idItem) || StringUtil.isEmpty(showItemsMap)
				|| StringUtil.isEmpty(title)
				|| StringUtil.isEmpty(mainShowItem)
				|| StringUtil.isEmpty(refType) || (refType.equals(IRZConsts.TREE) && StringUtil
						.isEmpty(parentItem)))) {
			logger.debug(errorMessage);
			throw new ResultException(errorMessage);
		}
		return true;
	}

	public BossCurrencyRpcService getCurrencyRpcClient() {
		return currencyRpcClient;
	}

	public void setCurrencyRpcClient(BossCurrencyRpcService currencyRpcClient) {
		this.currencyRpcClient = currencyRpcClient;
	}

	public RefMetaDataService getRefMetaDataService() {
		return refMetaDataService;
	}

	public void setRefMetaDataService(RefMetaDataService refMetaDataService) {
		this.refMetaDataService = refMetaDataService;
	}



}
