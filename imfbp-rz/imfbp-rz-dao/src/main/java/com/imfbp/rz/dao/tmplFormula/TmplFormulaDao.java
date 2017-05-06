package com.imfbp.rz.dao.tmplFormula;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.tmpl.TmplFormula;

public interface TmplFormulaDao {

	/****
	 * 根据节点编码查询
	 * @param mapWhere
	 * @return
	 */
	public List<TmplFormula> getTmplFormulaByNodeCode(Map<String,Object> mapWhere);

}
