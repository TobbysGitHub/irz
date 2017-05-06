package com.imfbp.rz.dao.pubfilestore;

import com.imfbp.rz.domain.pubfilestore.PubFilestore;

public interface PubFilestoreDao{

	/**
	 * 添加
	 * @param pubFilestore
	 * @return
	 */
	public void insertPubFilestore(PubFilestore pubFilestore);
	
	/**
	 * 根据Id删除
	 * @param pubFilestoreQuery
	 * @return
	 */
	public boolean deletePubFilestoreById(PubFilestore pubFilestoreQuery);
	
	
	/**
	 * 根据id查询
	 * @param pubFilestoreQuery
	 * @return
	 */
	public PubFilestore getPubFilestoreById(PubFilestore pubFilestoreQuery);
	
}