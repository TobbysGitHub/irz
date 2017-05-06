package com.imfbp.rz.dao.pubnodevoinfo;

import com.imfbp.rz.domain.pubnodevoinfo.PubNodevoinfo;
import com.imfbp.rz.domain.pubnodevoinfo.query.PubNodevoinfoQuery;


public interface PubNodevoinfoDao{

	
	/**
	 * 根据功能编码查询
	 * @param pubNodevoinfoQuery
	 * @return
	 */
	public PubNodevoinfo getPubNodevoinfoByFuncode(PubNodevoinfoQuery pubNodevoinfoQuery);
	
}