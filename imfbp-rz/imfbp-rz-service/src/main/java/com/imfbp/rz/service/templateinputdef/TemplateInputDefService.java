package com.imfbp.rz.service.templateinputdef;

import java.util.List;

import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;
import com.imfbp.rz.domain.templateinputdef.query.TemplateInputDefQuery;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

public interface TemplateInputDefService {

	/**
	 * 添加
	 * 
	 * @param templateInputDef
	 * @return
	 */
	public void insertTemplateInputDef(TemplateInputDef templateInputDef);

	/**
	 * 根据Id删除
	 * 
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 * @throws Exception
	 */
	public Result deleteTemplateInputDefByBatchId(
			TemplateInputDefQuery templateInputDefQuery) throws Exception;

	/**
	 * 
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param id
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
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public Result logicDeleteTemplateInputDefByBatchId(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 添加或修改
	 * 
	 * @param templateInputDef
	 */
	public Result insertOrUpdate(TemplateInputDef templateInputDef);

	/**
	 * 根据Id修改
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateTemplateInputDefById(TemplateInputDef templateInputDef);

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateTemplateInputDefByCondition(
			TemplateInputDefQuery record, TemplateInputDefQuery parameter);

	/**
	 * 根据id查询
	 * 
	 * @param id
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
	public GridResult<TemplateInputDef> getTemplateInputDefByPage(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	public int getTemplateInputDefByPageCount(
			TemplateInputDefQuery templateInputDefQuery);

	/**
	 * 批量插入
	 * 
	 * @param templateInputDefs
	 *            模板输入项集合
	 * @throws Exception
	 */
	public void insertTemplateInputDefBatch(
			List<TemplateInputDef> templateInputDefs) throws Exception;

	/**
	 * 批量根据id更新
	 * 
	 * @param updateLists
	 *            模板输入项集合
	 * @throws Exception
	 */
	public void updateBatchTemplateInputDefById(
			List<TemplateInputDef> updateLists) throws Exception;

	public Result deleteInputDefByTemplateBatchId(
			TemplateInputDefQuery templateInputDefQuery) throws Exception;

}