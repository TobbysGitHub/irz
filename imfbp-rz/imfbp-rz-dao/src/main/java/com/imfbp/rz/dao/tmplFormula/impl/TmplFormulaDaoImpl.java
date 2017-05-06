package com.imfbp.rz.dao.tmplFormula.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.tmplFormula.TmplFormulaDao;
import com.imfbp.rz.domain.tmpl.TmplFormula;

public class TmplFormulaDaoImpl extends SqlSessionDaoSupport implements TmplFormulaDao {

	@Override
	public List<TmplFormula> getTmplFormulaByNodeCode(Map<String, Object> mapWhere) {
		return this.getSqlSession().selectList("tmplFormula.getTmplFormulaByNodeCode", mapWhere);
	}

}
