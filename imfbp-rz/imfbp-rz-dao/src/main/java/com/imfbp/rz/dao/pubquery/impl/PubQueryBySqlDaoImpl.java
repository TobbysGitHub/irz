package com.imfbp.rz.dao.pubquery.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.pubquery.PubQueryBySqlDao;
public class PubQueryBySqlDaoImpl extends SqlSessionDaoSupport implements
		PubQueryBySqlDao {

	@Override
	public List<Map<String, Object>> getRefDataValueBySql(String sql) {
		return this.getSqlSession().selectList("pubQuery.getRefDataValueBySql",
				sql);
	}

}
