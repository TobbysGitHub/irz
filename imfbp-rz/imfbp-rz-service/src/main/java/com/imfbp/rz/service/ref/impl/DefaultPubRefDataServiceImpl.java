package com.imfbp.rz.service.ref.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.ref.PubRefDataService;
import com.imfbp.rz.service.ref.RefDataService;
import com.platform.common.utils.StringUtil;

@Component("defaultPubRefDataServiceImpl")
public class DefaultPubRefDataServiceImpl implements PubRefDataService {

	/**
	 * 参照对应取数服务Map集合
	 */
	private Map<String, RefDataService> refDataServiceMaps;
	/**
	 * 参照基础档案对应的档案类型编码
	 */
	private Map<String, String> dicItemMaps;

	@Override
	public List<RefResult> getRefTranslateDatas(RefBaseQuery refBaseQuery,
			Map<String, String[]> values) throws Exception {
		if (values != null && values.size() > 0) {
			List<RefResult> refResultLists = new ArrayList<RefResult>();
			String refName = null;
			for (String name : values.keySet()) {
				refName = name.replaceAll("\\[\\]", "");
				// RefBaseQuery refBaseQuery = new RefBaseQuery();
				refBaseQuery.setTypeKey(refName);
				refBaseQuery.setIsTranslate(true);
				String[] ids = values.get(name);
				if (ids != null && ids.length > 0) {
					List<String> batchIds = new ArrayList<String>();
					for (int i = 0; i < ids.length; i++) {
						batchIds.add(ids[i]);
					}
					refBaseQuery.setBatchIds(batchIds);
					RefResult refResult = getRefDatasByQuery(refBaseQuery);
					if (refResult != null) {
						refResult.setResultCode(refName);
						refResult.setSuccess(true);
						refResult
								.setSuccessMessage(IRZConsts.QUERYSUCCESSMESSAGE);
						refResultLists.add(refResult);
					}
				}
			}
			return refResultLists;
		}
		return null;
	}

	@Override
	public RefResult getRefDatasByQuery(RefBaseQuery refBaseQuery)
			throws Exception {
		// TODO Auto-generated method stub
		if (refBaseQuery == null
				|| StringUtil.isEmpty(refBaseQuery.getTypeKey())) {
			throw new Exception("当前查询条件无效，在获取参照数据时，必须知道类型：typeKey值");
		}
		RefResult result = null;
		if (refDataServiceMaps != null
				&& refDataServiceMaps.containsKey(refBaseQuery.getTypeKey())) {
			String dicItemCode = null;
			if (dicItemMaps != null
					&& dicItemMaps.containsKey(refBaseQuery.getTypeKey())) {
				// 参照属于字典档案类型参照
				dicItemCode = dicItemMaps.get(refBaseQuery.getTypeKey());
			}
			// 不判断是否为空，防止误传参数，以配置文件为准
			refBaseQuery.setDicItemCode(dicItemCode);

			RefDataService refDataService = refDataServiceMaps.get(refBaseQuery
					.getTypeKey());
			if (refDataService != null)
				if (refBaseQuery.getIsTranslate()) {
					// 获取翻译数据
					result = refDataService.getRefDatasByBatchId(refBaseQuery);
				} else {
					result = refDataService.getRefDatasByQuery(refBaseQuery);
				}
		}
		if (result == null) {
			result = new RefResult();
			result.setSuccessMessage("获取参照数据成功，但不存在任何可参照数据");
		}
		result.setSuccess(true);
		return result;
	}

	public Map<String, RefDataService> getRefDataServiceMaps() {
		return refDataServiceMaps;
	}

	public void setRefDataServiceMaps(
			Map<String, RefDataService> refDataServiceMaps) {
		this.refDataServiceMaps = refDataServiceMaps;
	}

	public Map<String, String> getDicItemMaps() {
		return dicItemMaps;
	}

	public void setDicItemMaps(Map<String, String> dicItemMaps) {
		this.dicItemMaps = dicItemMaps;
	}

}
