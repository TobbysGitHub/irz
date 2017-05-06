package com.imfbp.rz.dao.templaterulesdef;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.templaterulesdef.TemplateRulesDef;
import com.imfbp.rz.domain.templaterulesdef.query.TemplateRulesDefQuery;

public interface TemplateRulesDefDao {

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
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public boolean deleteTemplateRulesDefByBatchId(Map<String, Object> data);

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
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
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateRulesDefByBatchId(Map<String, Object> data);

	/**
	 * 根据Id修改
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public boolean updateTemplateRulesDefById(TemplateRulesDef templateRulesDef);

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateTemplateRulesDefByCondition(Map<String, Object> data);

	/**
	 * 根据id查询
	 * 
	 * @param templateRulesDefQuery
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
	public List<TemplateRulesDef> getTemplateRulesDefByPage(
			TemplateRulesDefQuery templateRulesDefQuery);

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	public Integer getTemplateRulesDefByPageCount(
			TemplateRulesDefQuery templateRulesDefQuery);

	public boolean updateTemplateRulesDefByBatchId(
			List<TemplateRulesDef> templateRulesDefList);

}