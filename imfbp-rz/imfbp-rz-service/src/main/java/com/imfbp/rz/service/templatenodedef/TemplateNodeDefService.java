package com.imfbp.rz.service.templatenodedef;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.templatenodedef.TemplateNodeDef;
import com.imfbp.rz.domain.templatenodedef.query.TemplateNodeDefQuery;

public interface TemplateNodeDefService{

	/**
	 * 添加
	 * @param templateNodeDef
	 * @return
	 */
	public void insertTemplateNodeDef(TemplateNodeDef templateNodeDef);
	
	/**
	 * 批量添加
	 * @param List<templateNodeDef>
	 * @return
	 */
	public void insertBatchTemplateNodeDef(List<TemplateNodeDef> templateNodeDefList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param templateNodeDefQuery
	 * @return
	 */
	public boolean deleteTemplateNodeDefByCondition(TemplateNodeDefQuery templateNodeDefQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param templateNodeDefQuery
	 * @return
	 */
	public Result deleteTemplateNodeDefByBatchId(TemplateNodeDefQuery templateNodeDefQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param templateNodeDefQuery
	 * @return
	 */
	public boolean logicDeleteTemplateNodeDefByCondition(TemplateNodeDefQuery templateNodeDefQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param templateNodeDefQuery
	 * @return
	 */
	public Result logicDeleteTemplateNodeDefByBatchId(TemplateNodeDefQuery templateNodeDefQuery);
	
	
	/**
	 * 添加或修改
	 * @param templateNodeDef
	 */
	public Result insertOrUpdate(TemplateNodeDef templateNodeDef);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateTemplateNodeDefById(TemplateNodeDef templateNodeDef);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateTemplateNodeDefByCondition(TemplateNodeDefQuery record,TemplateNodeDefQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public TemplateNodeDef getTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery);
	
	/**
	 * 查询所有
	 * @param templateNodeDefQuery
	 * @return
	 */
	public List<TemplateNodeDef> getTemplateNodeDefAll(TemplateNodeDefQuery templateNodeDefQuery);
	
	/**
	 * 分页查询
	 * @param templateNodeDefQuery
	 * @return
	 */
	public GridResult<TemplateNodeDef> getTemplateNodeDefByPage(TemplateNodeDefQuery templateNodeDefQuery);
	
	/**
	 * 分页查询查询总数
	 * @param templateNodeDefQuery
	 * @return
	 */
	public int getTemplateNodeDefByPageCount(TemplateNodeDefQuery templateNodeDefQuery);
	
	
}