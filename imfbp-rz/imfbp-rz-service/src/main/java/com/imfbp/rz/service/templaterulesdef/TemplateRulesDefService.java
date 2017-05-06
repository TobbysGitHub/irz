package com.imfbp.rz.service.templaterulesdef;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.templaterulesdef.TemplateRulesDef;
import com.imfbp.rz.domain.templaterulesdef.query.TemplateRulesDefQuery;

public interface TemplateRulesDefService {

	/**
	 * 添加
	 * 
	 * @param templateRulesDef
	 * @return
	 */
	public void insertTemplateRulesDef(TemplateRulesDef templateRulesDef);

	/**
	 * 批量添加
	 * 
	 * @param List
	 *            <templateRulesDef>
	 * @return
	 */
	public void insertBatchTemplateRulesDef(
			List<TemplateRulesDef> templateRulesDefList);

	/**
	 * 根据Id删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public boolean deleteTemplateRulesDefByCondition(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 批量删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public Result deleteTemplateRulesDefByBatchId(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param id
	 * @return
	 */
	public boolean logicDeleteTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateRulesDefByCondition(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public Result logicDeleteTemplateRulesDefByBatchId(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 添加或修改
	 * 
	 * @param templateRulesDef
	 */
	public Result insertOrUpdate(TemplateRulesDef templateRulesDef);

	/**
	 * 根据Id修改
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateTemplateRulesDefById(TemplateRulesDef templateRulesDef);

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateTemplateRulesDefByCondition(
			TemplateRulesDefQuery record, TemplateRulesDefQuery parameter);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public TemplateRulesDef getTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 查询所有
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public List<TemplateRulesDef> getTemplateRulesDefAll(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 分页查询
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public GridResult<TemplateRulesDef> getTemplateRulesDefByPage(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public int getTemplateRulesDefByPageCount(
			TemplateRulesDefQuery templateRulesDefQuery);

	public Result insertOrUpdateForBatch(
			List<TemplateRulesDef> templateRulesDefList) throws Exception;

	boolean updateTemplateRulesDefByBatchId(
			List<TemplateRulesDef> templateRulesDefList);

}