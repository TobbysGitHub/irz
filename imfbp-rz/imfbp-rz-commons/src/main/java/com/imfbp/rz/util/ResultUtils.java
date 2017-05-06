package com.imfbp.rz.util;

import java.util.Map;

import com.platform.common.utils.StringUtil;
import com.platform.common.web.result.Result;

public class ResultUtils {

	// private static volatile Result result = new Result();

	/**
	 * 获取异常时返回的结果
	 * 
	 * @param message
	 * @return
	 */
	public final static Result getExceptionResult(String message) {
		return getTargetResult(null, null, false, null, message);
	}

	/**
	 * 获取指定返回结果集
	 * 
	 * @param result
	 * @param dataItem
	 * @param itemData
	 * @param isSuccess
	 * @param successMessage
	 * @param errorMessage
	 * @return
	 */
	public final static Result getTargetResult(Result result, String dataItem,
			Object itemData, boolean isSuccess, String message) {
		result.addDefaultModel(dataItem, itemData);
		result.setSuccess(isSuccess);
		if (isSuccess) {
			result.setSuccessMessage(message);
		} else {
			result.setErrorMessage(message);
		}
		return result;
	}

	/**
	 * 获取返回结果数据
	 * 
	 * @param dataItem
	 * @param itemData
	 * @param isSuccess
	 * @param successMessage
	 * @param errorMessage
	 * @return
	 */
	public final static Result getTargetResult(String dataItem,
			Object itemData, boolean isSuccess, String successMessage,
			String errorMessage) {
		Result result = new Result();
		if (!StringUtil.isEmpty(dataItem) && !StringUtil.isEmpty(itemData)) {
			result.addDefaultModel(dataItem, itemData);
		}
		result.setSuccess(isSuccess);
		result.setSuccessMessage(successMessage);
		result.setErrorMessage(errorMessage);
		return result;
	}

	/**
	 * 获取返回结果数据
	 * 
	 * @param dataItem
	 * @param itemData
	 * @param isSuccess
	 * @param successMessage
	 * @param errorMessage
	 * @return
	 */
	public final static Result getTargetResult(Map<String, Object> dataMap,
			boolean isSuccess, String successMessage, String errorMessage) {
		Result result = new Result();
		if (dataMap != null && dataMap.size() > 0) {
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				if (!StringUtil.isEmpty(entry.getKey())
						&& !StringUtil.isEmpty(entry.getValue())) {
					result.addDefaultModel(entry.getKey(), entry.getValue());
				}
			}
		}
		result.setSuccess(isSuccess);
		result.setSuccessMessage(successMessage);
		result.setErrorMessage(errorMessage);
		return result;
	}
}
