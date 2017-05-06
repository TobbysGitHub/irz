package com.imfbp.rz.service.templateversion;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.templateversion.TemplateVersion;
import com.imfbp.rz.domain.templateversion.query.TemplateVersionQuery;

public interface TemplateVersionService {

	/**
	 * 添加
	 * 
	 * @param templateVersion
	 * @return
	 */
	public void insertTemplateVersion(TemplateVersion templateVersion);

	/**
	 * 批量添加
	 * 
	 * @param List
	 *            <templateVersion>
	 * @return
	 */
	public void insertBatchTemplateVersion(
			List<TemplateVersion> templateVersionList);

	/**
	 * 根据Id删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTemplateVersionById(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public boolean deleteTemplateVersionByCondition(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 批量删除 (真正删除数据库数据)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public Result deleteTemplateVersionByBatchId(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param id
	 * @return
	 */
	public boolean logicDeleteTemplateVersionById(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public boolean logicDeleteTemplateVersionByCondition(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public Result logicDeleteTemplateVersionByBatchId(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 添加或修改
	 * 
	 * @param templateVersion
	 * @throws Exception
	 */
	public Result insertOrUpdate(TemplateVersion templateVersion)
			throws Exception;

	/**
	 * 根据Id修改
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateTemplateVersionById(TemplateVersion templateVersion);

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	public boolean updateTemplateVersionByCondition(
			TemplateVersionQuery record, TemplateVersionQuery parameter);

	/**
	 * 根据Id批量修改
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public Result updateTemplateVersionByBatchId(
			List<TemplateVersion> templateVersionList);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public TemplateVersion getTemplateVersionById(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 查询所有
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public List<TemplateVersion> getTemplateVersionAll(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 分页查询
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public GridResult<TemplateVersion> getTemplateVersionByPage(
			TemplateVersionQuery templateVersionQuery);

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public int getTemplateVersionByPageCount(
			TemplateVersionQuery templateVersionQuery);

}