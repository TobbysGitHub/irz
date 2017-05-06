package com.imfbp.rz.dao.templatedef;

import java.util.Map;

import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;

import java.util.List;

public interface TemplateDefDao {

	/**
	 * 添加
	 * 
	 * @param templateDef
	 * @return
	 */
	public void insertTemplateDef(TemplateDef templateDef);

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public boolean deleteTemplateDefById(TemplateDefQuery templateDefQuery);

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public boolean deleteTemplateDefByCondition(
			TemplateDefQuery templateDefQuery);

	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public boolean deleteTemplateDefByBatchId(Map<String, Object> data);

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateDefById(TemplateDefQuery templateDefQuery);

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateDefByCondition(
			TemplateDefQuery templateDefQuery);

	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateDefByBatchId(Map<String, Object> data);

	/**
	 * 根据Id修改
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public boolean updateTemplateDefById(TemplateDef templateDef);

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateTemplateDefByCondition(Map<String, Object> data);

	/**
	 * 根据id查询
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public TemplateDef getTemplateDefById(TemplateDefQuery templateDefQuery);

	/**
	 * 查询所有
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public List<TemplateDef> getTemplateDefAll(TemplateDefQuery templateDefQuery);

	/**
	 * 分页查询
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public List<TemplateDef> getTemplateDefByPage(
			TemplateDefQuery templateDefQuery);

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public Integer getTemplateDefByPageCount(TemplateDefQuery templateDefQuery);

	public List<TemplateDef> getTemplateDefBybatchIds(
			List<String> batchIds);

	public List<TemplateDef> getMaxVersionTemplateDefById(
			TemplateDefQuery templateDefQuery);

}