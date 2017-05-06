package com.imfbp.rz.dao.pubnodevoinfo.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.pubnodevoinfo.PubNodevoinfoDao;
import com.imfbp.rz.domain.pubnodevoinfo.PubNodevoinfo;
import com.imfbp.rz.domain.pubnodevoinfo.query.PubNodevoinfoQuery;

public class PubNodevoinfoDaoImpl extends SqlSessionDaoSupport implements PubNodevoinfoDao{

	/**
	 * 根据功能编码查询
	 * @param pubNodevoinfoQuery
	 * @return
	 */
	@Override
	public PubNodevoinfo getPubNodevoinfoByFuncode(PubNodevoinfoQuery pubNodevoinfoQuery){
		return this.getSqlSession().selectOne("pubNodevoinfo.getPubNodevoinfoByFuncode",pubNodevoinfoQuery);
	}
	
}