package com.imfbp.rz.dao.pubfilestore.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.pubfilestore.PubFilestoreDao;
import com.imfbp.rz.domain.pubfilestore.PubFilestore;

public class PubFilestoreDaoImpl extends SqlSessionDaoSupport implements PubFilestoreDao{

	/**
	 * 添加
	 * @param pubFilestore
	 * @return
	 */
	@Override
	public void insertPubFilestore(PubFilestore pubFilestore){
		this.getSqlSession().insert("pubFilestore.insertPubFilestore", pubFilestore);
	}
	
	/**
	 * 删除
	 * @param pubFilestoreQuery
	 * @return
	 */
	@Override
	public boolean deletePubFilestoreById(PubFilestore pubFilestoreQuery){
		return this.getSqlSession().delete("pubFilestore.deletePubFilestoreById", pubFilestoreQuery)>0;
	}
	
	/**
	 * 根据id查询
	 * @param pubFilestoreQuery
	 * @return
	 */
	@Override
	public PubFilestore getPubFilestoreById(PubFilestore pubFilestoreQuery){
		return this.getSqlSession().selectOne("pubFilestore.getPubFilestoreById",pubFilestoreQuery);
	}
	
	
}