package com.imfbp.rz.dao.templateinputdef.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;
import com.imfbp.rz.domain.templateinputdef.query.TemplateInputDefQuery;
import com.imfbp.rz.dao.templateinputdef.TemplateInputDefDao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TemplateInputDefDaoImpl extends SqlSessionDaoSupport implements
		TemplateInputDefDao {

	/**
	 * 添加
	 * 
	 * @param templateInputDef
	 * @return
	 */
	@Override
	public void insertTemplateInputDef(TemplateInputDef templateInputDef) {
		this.getSqlSession().insert("templateInputDef.insertTemplateInputDef",
				templateInputDef);
	}

	/**
	 * 删除 (真正删除数据库数据)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().delete(
				"templateInputDef.deleteTemplateInputDefById",
				templateInputDefQuery) > 0;
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateInputDefByCondition(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().delete(
				"templateInputDef.deleteTemplateInputDefByCondition",
				templateInputDefQuery) > 0;
	}

	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteTemplateInputDefByBatchId(Map<String, Object> data) {
		return this.getSqlSession().delete(
				"templateInputDef.deleteTemplateInputDefByBatchId", data) > 0;
	}
	
	@Override
	public boolean deleteInputDefByTemplateBatchId(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete(
				"templateInputDef.deleteInputDefByTemplateBatchId", data) > 0;
	}

	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().update(
				"templateInputDef.logicDeleteTemplateInputDefById",
				templateInputDefQuery) > 0;
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateInputDefByCondition(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().update(
				"templateInputDef.logicDeleteTemplateInputDefById",
				templateInputDefQuery) > 0;
	}

	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateInputDefByBatchId(Map<String, Object> data) {
		return this.getSqlSession().update(
				"templateInputDef.logicDeleteTemplateInputDefByBatchId", data) > 0;
	}

	/**
	 * 修改
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public boolean updateTemplateInputDefById(TemplateInputDef templateInputDef) {
		return this.getSqlSession()
				.update("templateInputDef.updateTemplateInputDefById",
						templateInputDef) > 0;
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateInputDefByCondition(Map<String, Object> data) {
		return this.getSqlSession().update(
				"templateInputDef.updateTemplateInputDefByCondition", data) > 0;
	}

	/**
	 * 根据id查询
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public TemplateInputDef getTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().selectOne(
				"templateInputDef.getTemplateInputDefById",
				templateInputDefQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public List<TemplateInputDef> getTemplateInputDefAll(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().selectList(
				"templateInputDef.getTemplateInputDefAll",
				templateInputDefQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<TemplateInputDef> getTemplateInputDefByPage(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().selectList(
				"templateInputDef.getTemplateInputDefByPage",
				templateInputDefQuery);
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getTemplateInputDefByPageCount(
			TemplateInputDefQuery templateInputDefQuery) {
		return this.getSqlSession().selectOne(
				"templateInputDef.getTemplateInputDefByPageCount",
				templateInputDefQuery);
	}

	@Override
	public void insertTemplateInputDefBatch(
			List<TemplateInputDef> templateInputDefs) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(
				"templateInputDef.insertTemplateInputDefBatch",
				templateInputDefs);

	}

	@Override
	public void updateBatchTemplateInputDefById(
			List<TemplateInputDef> updateLists) {
		// TODO Auto-generated method stub
		this.getSqlSession()
				.insert("templateInputDef.updateBatchTemplateInputDefById",
						updateLists);
	}

}