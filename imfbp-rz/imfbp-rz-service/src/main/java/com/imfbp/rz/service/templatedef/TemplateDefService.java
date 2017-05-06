package com.imfbp.rz.service.templatedef;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;

public interface TemplateDefService {

	/**
	 * 添加
	 * 
	 * @param templateDef
	 * @return
	 */
	public void insertTemplateDef(TemplateDef templateDef);

	/**
	 * 根据Id删除
	 * 
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 * @throws Exception
	 */
	public Result deleteTemplateDefByBatchId(TemplateDefQuery templateDefQuery)
			throws Exception;

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param id
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
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public Result logicDeleteTemplateDefByBatchId(
			TemplateDefQuery templateDefQuery);

	/**
	 * 添加或修改
	 * 
	 * @param templateDef
	 * @throws Exception
	 */
	public Result insertOrUpdate(TemplateDef templateDef) throws Exception;

	/**
	 * 根据Id修改
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateTemplateDefById(TemplateDef templateDef);

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateTemplateDefByCondition(TemplateDefQuery record,
			TemplateDefQuery parameter);

	/**
	 * 根据id查询
	 * 
	 * @param id
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
	public GridResult<TemplateDef> getTemplateDefByPage(
			TemplateDefQuery templateDefQuery);

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	public int getTemplateDefByPageCount(TemplateDefQuery templateDefQuery);

	public List<TemplateDef> getTemplateDefBybatchIds(
			List<String> batchIds) throws Exception;

	public List<TemplateDef> getMaxVersionTemplateDefById(
			TemplateDefQuery templateDefQuery) throws Exception;
}