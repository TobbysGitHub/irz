package com.imfbp.rz.dao.templaterulesdef.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.templaterulesdef.TemplateRulesDef;
import com.imfbp.rz.domain.templaterulesdef.query.TemplateRulesDefQuery;
import com.imfbp.rz.dao.templaterulesdef.TemplateRulesDefDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TemplateRulesDefDaoImpl extends SqlSessionDaoSupport implements
		TemplateRulesDefDao {

	/**
	 * 添加
	 * 
	 * @param templateRulesDef
	 * @return
	 */
	@Override
	public void insertTemplateRulesDef(TemplateRulesDef templateRulesDef) {
		this.getSqlSession().insert("templateRulesDef.insertTemplateRulesDef",
				templateRulesDef);
	}

	/**
	 * 批量添加
	 * 
	 * @param List
	 *            <templateRulesDef>
	 * @return
	 */
	public void insertBatchTemplateRulesDef(
			List<TemplateRulesDef> templateRulesDefList) {
		this.getSqlSession().insert(
				"templateRulesDef.insertBatchTemplateRulesDef",
				templateRulesDefList);
	}

	/**
	 * 删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().delete(
				"templateRulesDef.deleteTemplateRulesDefById",
				templateRulesDefQuery) > 0;
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateRulesDefByCondition(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().delete(
				"templateRulesDef.deleteTemplateRulesDefById",
				templateRulesDefQuery) > 0;
	}

	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteTemplateRulesDefByBatchId(Map<String, Object> data) {
		return this.getSqlSession().delete(
				"templateRulesDef.deleteTemplateRulesDefByBatchId", data) > 0;
	}

	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().update(
				"templateRulesDef.logicDeleteTemplateRulesDefById",
				templateRulesDefQuery) > 0;
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateRulesDefByCondition(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().update(
				"templateRulesDef.logicDeleteTemplateRulesDefById",
				templateRulesDefQuery) > 0;
	}

	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateRulesDefByBatchId(Map<String, Object> data) {
		return this.getSqlSession().update(
				"templateRulesDef.logicDeleteTemplateRulesDefByBatchId", data) > 0;
	}

	/**
	 * 修改
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public boolean updateTemplateRulesDefById(TemplateRulesDef templateRulesDef) {
		return this.getSqlSession()
				.update("templateRulesDef.updateTemplateRulesDefById",
						templateRulesDef) > 0;
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateRulesDefByCondition(Map<String, Object> data) {
		return this.getSqlSession().update(
				"templateRulesDef.updateTemplateRulesDefByCondition", data) > 0;
	}

	/**
	 * 根据id查询
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public TemplateRulesDef getTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().selectOne(
				"templateRulesDef.getTemplateRulesDefById",
				templateRulesDefQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public List<TemplateRulesDef> getTemplateRulesDefAll(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().selectList(
				"templateRulesDef.getTemplateRulesDefAll",
				templateRulesDefQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<TemplateRulesDef> getTemplateRulesDefByPage(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().selectList(
				"templateRulesDef.getTemplateRulesDefByPage",
				templateRulesDefQuery);
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getTemplateRulesDefByPageCount(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return this.getSqlSession().selectOne(
				"templateRulesDef.getTemplateRulesDefByPageCount",
				templateRulesDefQuery);
	}

	@Override
	public boolean updateTemplateRulesDefByBatchId(
			List<TemplateRulesDef> templateRulesDefList) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update(
				"templateRulesDef.updateTemplateRulesDefByBatchId",
				templateRulesDefList) > 0;

	}
}