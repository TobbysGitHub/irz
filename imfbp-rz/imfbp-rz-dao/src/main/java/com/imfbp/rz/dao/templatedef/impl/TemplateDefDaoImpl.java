package com.imfbp.rz.dao.templatedef.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;
import com.imfbp.rz.dao.templatedef.TemplateDefDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TemplateDefDaoImpl extends SqlSessionDaoSupport implements
		TemplateDefDao {

	/**
	 * 添加
	 * 
	 * @param templateDef
	 * @return
	 */
	@Override
	public void insertTemplateDef(TemplateDef templateDef) {
		this.getSqlSession().insert("templateDef.insertTemplateDef",
				templateDef);
	}

	/**
	 * 删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateDefById(TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().delete("templateDef.deleteTemplateDefById",
				templateDefQuery) > 0;
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateDefByCondition(
			TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().delete("templateDef.deleteTemplateDefById",
				templateDefQuery) > 0;
	}

	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteTemplateDefByBatchId(Map<String, Object> data) {
		return this.getSqlSession().delete(
				"templateDef.deleteTemplateDefByBatchId", data) > 0;
	}

	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateDefById(TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().update(
				"templateDef.logicDeleteTemplateDefById", templateDefQuery) > 0;
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateDefByCondition(
			TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().update(
				"templateDef.logicDeleteTemplateDefById", templateDefQuery) > 0;
	}

	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateDefByBatchId(Map<String, Object> data) {
		return this.getSqlSession().update(
				"templateDef.logicDeleteTemplateDefByBatchId", data) > 0;
	}

	/**
	 * 修改
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public boolean updateTemplateDefById(TemplateDef templateDef) {
		return this.getSqlSession().update("templateDef.updateTemplateDefById",
				templateDef) > 0;
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateDefByCondition(Map<String, Object> data) {
		return this.getSqlSession().update(
				"templateDef.updateTemplateDefByCondition", data) > 0;
	}

	/**
	 * 根据id查询
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public TemplateDef getTemplateDefById(TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().selectOne("templateDef.getTemplateDefById",
				templateDefQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public List<TemplateDef> getTemplateDefAll(TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().selectList("templateDef.getTemplateDefAll",
				templateDefQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<TemplateDef> getTemplateDefByPage(
			TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().selectList(
				"templateDef.getTemplateDefByPage", templateDefQuery);
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getTemplateDefByPageCount(TemplateDefQuery templateDefQuery) {
		return this.getSqlSession().selectOne(
				"templateDef.getTemplateDefByPageCount", templateDefQuery);
	}

	@Override
	public List<TemplateDef> getTemplateDefBybatchIds(
			List<String> batchIds) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(
				"templateDef.getTemplateDefBybatchIds", batchIds);
	}

	@Override
	public List<TemplateDef> getMaxVersionTemplateDefById(
			TemplateDefQuery templateDefQuery) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(
				"templateDef.getMaxVersionTemplateDefById", templateDefQuery);
	}
}