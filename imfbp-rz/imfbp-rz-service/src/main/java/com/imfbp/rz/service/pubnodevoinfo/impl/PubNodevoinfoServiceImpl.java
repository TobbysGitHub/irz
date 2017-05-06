package com.imfbp.rz.service.pubnodevoinfo.impl;

import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.pubnodevoinfo.PubNodevoinfoDao;
import com.imfbp.rz.domain.pubnodevoinfo.PubNodevoinfo;
import com.imfbp.rz.domain.pubnodevoinfo.query.PubNodevoinfoQuery;
import com.imfbp.rz.service.pubnodevoinfo.PubNodevoinfoService;


@Component("pubNodevoinfoService")
public class PubNodevoinfoServiceImpl implements PubNodevoinfoService{


	private PubNodevoinfoDao pubNodevoinfoDao;


	/**
	 * 根据功能编码查询
	 * @param pubNodevoinfoQuery
	 * @return
	 */
	@Override
	public PubNodevoinfo getPubNodevoinfoByFuncode(PubNodevoinfoQuery pubNodevoinfoQuery){
		return pubNodevoinfoDao.getPubNodevoinfoByFuncode(pubNodevoinfoQuery);
	}
	
	public void setPubNodevoinfoDao(PubNodevoinfoDao  pubNodevoinfoDao){
		this.pubNodevoinfoDao = pubNodevoinfoDao;
	}
	
}