package com.imfbp.rz.service.ref;

import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefResult;

/**
 * 参照数据服务接口
 * 
 * @author Xinggh
 *
 */
public interface RefDataService {

	/**
	 * 通过基本查询参数与元数据查询参照数据入口
	 * 
	 * @param refBaseQuery
	 * @return
	 * @throws Exception
	 */
	public RefResult getRefDatasByQuery(RefBaseQuery refBaseQuery)
			throws Exception;

	/**
	 * 获取指定Id集合参照数据的翻译数据
	 * 
	 * @param refBaseQuery
	 * @return
	 * @throws Exception
	 */
	public RefResult getRefDatasByBatchId(RefBaseQuery refBaseQuery)
			throws Exception;

}
