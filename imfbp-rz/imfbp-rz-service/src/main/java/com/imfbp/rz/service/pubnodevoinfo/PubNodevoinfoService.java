package com.imfbp.rz.service.pubnodevoinfo;

import com.imfbp.rz.domain.pubnodevoinfo.PubNodevoinfo;
import com.imfbp.rz.domain.pubnodevoinfo.query.PubNodevoinfoQuery;

public interface PubNodevoinfoService{


	/**
	 * 根据功能编码查询
	 * @param id
	 * @return
	 */
	public PubNodevoinfo getPubNodevoinfoByFuncode(PubNodevoinfoQuery pubNodevoinfoQuery);
	
	
}