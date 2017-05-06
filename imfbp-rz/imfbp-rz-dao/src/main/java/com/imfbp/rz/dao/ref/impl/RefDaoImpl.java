package com.imfbp.rz.dao.ref.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.ref.RefDao;

public class RefDaoImpl extends SqlSessionDaoSupport implements RefDao {

	@Override
	public List<Object> getRefDataValueBySql(String sql) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList("pubref.getRefDataValueBySql",
				sql);
	}

	@Override
	public Integer getRefDataCountbySql(String sql) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("pubref.getRefDataCountbySql",
				sql);

	}

}
