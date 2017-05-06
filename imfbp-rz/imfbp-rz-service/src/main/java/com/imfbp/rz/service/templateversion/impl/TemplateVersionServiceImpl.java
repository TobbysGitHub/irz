package com.imfbp.rz.service.templateversion.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.templateversion.TemplateVersionDao;
import com.imfbp.rz.domain.templateversion.TemplateVersion;
import com.imfbp.rz.domain.templateversion.query.TemplateVersionQuery;
import com.imfbp.rz.service.templateversion.TemplateVersionService;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

@Component("templateVersionService")
public class TemplateVersionServiceImpl implements TemplateVersionService {

	private TemplateVersionDao templateVersionDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * 
	 * @param templateVersion
	 * @return
	 */
	@Override
	public void insertTemplateVersion(TemplateVersion templateVersion) {
		String pk = primaryKeyUtil.getPrimaryKey();
		templateVersion.setTs(DateUtil.getTs());
		templateVersion.setDr(0);
		templateVersion.setId(pk);
		templateVersionDao.insertTemplateVersion(templateVersion);
	}

	/**
	 * 批量添加
	 * 
	 * @param List
	 *            <templateVersion>
	 * @return
	 */
	public void insertBatchTemplateVersion(
			List<TemplateVersion> templateVersionList) {
		if (templateVersionList != null) {
			for (int i = 0; i < templateVersionList.size(); i++) {
				templateVersionList.get(i).setTs(DateUtil.getTs());
				templateVersionList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				templateVersionList.get(i).setId(pk);
			}
			templateVersionDao.insertBatchTemplateVersion(templateVersionList);
		}
	}

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteTemplateVersionById(
			TemplateVersionQuery templateVersionQuery) {
		return templateVersionDao
				.deleteTemplateVersionById(templateVersionQuery);
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateVersionByCondition(
			TemplateVersionQuery templateVersionQuery) {
		return templateVersionDao
				.deleteTemplateVersionByCondition(templateVersionQuery);
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public Result deleteTemplateVersionByBatchId(
			TemplateVersionQuery templateVersionQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = templateVersionQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			boolean flat = templateVersionDao
					.deleteTemplateVersionByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateVersionById(
			TemplateVersionQuery templateVersionQuery) {
		return templateVersionDao
				.logicDeleteTemplateVersionById(templateVersionQuery);
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateVersionByCondition(
			TemplateVersionQuery templateVersionQuery) {
		return templateVersionDao
				.logicDeleteTemplateVersionByCondition(templateVersionQuery);
	}

	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public Result logicDeleteTemplateVersionByBatchId(
			TemplateVersionQuery templateVersionQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = templateVersionQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			data.put("batchId2", batchIdArr);
			boolean flat = templateVersionDao
					.logicDeleteTemplateVersionByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加或修改
	 * 
	 * @param templateVersion
	 * @return
	 */
	@Override
	public Result insertOrUpdate(TemplateVersion templateVersion)
			throws Exception {
		// 设置调用失败
		Result result = new Result(false);
		if (templateVersion != null) {
			if (StringUtil.isNotEmpty(templateVersion.getId())) {
				updateTemplateVersionById(templateVersion);
			} else {
				insertTemplateVersion(templateVersion);
			}
			// 如果没有异常设置成功
			result.setSuccess(true);
		} else {
			result.setErrorMessage("数据对象不能为空");
		}
		// 设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
		// result.addDefaultModel(templateVersion);
		result.addDefaultModel("templateVersion", templateVersion);
		return result;
	}

	/**
	 * 根据Id修改
	 * 
	 * @param templateVersion
	 * @return
	 */
	@Override
	public boolean updateTemplateVersionById(TemplateVersion templateVersion) {
		return templateVersionDao.updateTemplateVersionById(templateVersion);
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateVersionByCondition(
			TemplateVersionQuery record, TemplateVersionQuery parameter) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record", record);
		data.put("parameter", parameter);
		return templateVersionDao.updateTemplateVersionByCondition(data);
	}

	/**
	 * 根据Id批量修改
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	public Result updateTemplateVersionByBatchId(
			List<TemplateVersion> templateVersionList) {
		Result result = new Result(false);
		try {
			boolean flag = templateVersionDao
					.updateTemplateVersionByBatchId(templateVersionList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据id查询
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public TemplateVersion getTemplateVersionById(
			TemplateVersionQuery templateVersionQuery) {
		return templateVersionDao.getTemplateVersionById(templateVersionQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public List<TemplateVersion> getTemplateVersionAll(
			TemplateVersionQuery templateVersionQuery) {
		return templateVersionDao.getTemplateVersionAll(templateVersionQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public GridResult<TemplateVersion> getTemplateVersionByPage(
			TemplateVersionQuery templateVersionQuery) {
		// 如果排序的字段是空或者空字符串
		if (templateVersionQuery != null
				&& StringUtils.isBlank(templateVersionQuery.getSort())) {
			templateVersionQuery.setSort("id");
			templateVersionQuery.setOrder("desc");
			;
		}
		int total = templateVersionDao
				.getTemplateVersionByPageCount(templateVersionQuery);
		PaginatedList<TemplateVersion> templateVersionPageList = new MysqlPaginatedArrayList<TemplateVersion>(
				templateVersionQuery, total);
		List<TemplateVersion> templateVersionList = templateVersionDao
				.getTemplateVersionByPage(templateVersionQuery);
		templateVersionPageList.addAll(templateVersionList);
		GridResult<TemplateVersion> result = new GridResult<TemplateVersion>(
				templateVersionPageList);
		return result;
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public int getTemplateVersionByPageCount(
			TemplateVersionQuery templateVersionQuery) {
		return templateVersionDao
				.getTemplateVersionByPageCount(templateVersionQuery);
	}

	public void setTemplateVersionDao(TemplateVersionDao templateVersionDao) {
		this.templateVersionDao = templateVersionDao;
	}

}