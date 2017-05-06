package com.imfbp.rz.dao.templateinputdef;

import java.util.Map;

import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;
import com.imfbp.rz.domain.templateinputdef.query.TemplateInputDefQuery;

import java.util.List;

public interface TemplateInputDefDao {

	/**
	 * 添加
	 * 
	 * @param templateInputDef
	 * @return
	 */
	public void insertTemplateInputDef(TemplateInputDef templateInputDef);

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public boolean deleteTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public boolean deleteTemplateInputDefByCondition(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public boolean deleteTemplateInputDefByBatchId(Map<String, Object> data);

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateInputDefByCondition(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateInputDefByBatchId(Map<String, Object> data);

	/**
	 * 根据Id修改
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public boolean updateTemplateInputDefById(TemplateInputDef templateInputDef);

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateTemplateInputDefByCondition(Map<String, Object> data);

	/**
	 * 根据id查询
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public TemplateInputDef getTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 查询所有
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public List<TemplateInputDef> getTemplateInputDefAll(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 分页查询
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public List<TemplateInputDef> getTemplateInputDefByPage(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public Integer getTemplateInputDefByPageCount(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 批量插入
	 * 
	 * @param templateInputDefs
	 */
	public void insertTemplateInputDefBatch(
			List<TemplateInputDef> templateInputDefs);

	/**
	 * 批量根据id更新
	 * 
	 * @param updateLists
	 */
	public void updateBatchTemplateInputDefById(
			List<TemplateInputDef> updateLists);

	public boolean deleteInputDefByTemplateBatchId(Map<String, Object> data);

}