package com.imfbp.rz.service.templateinputdef.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.templateinputdef.TemplateInputDefDao;
import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;
import com.imfbp.rz.domain.templateinputdef.query.TemplateInputDefQuery;
import com.imfbp.rz.service.templateinputdef.TemplateInputDefService;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

@Component("templateInputDefService")
public class TemplateInputDefServiceImpl implements TemplateInputDefService {

	private TemplateInputDefDao templateInputDefDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * 
	 * @param templateInputDef
	 * @return
	 */
	@Override
	public void insertTemplateInputDef(TemplateInputDef templateInputDef) {
		String pk = primaryKeyUtil.getPrimaryKey();
		templateInputDef.setId(pk);
		templateInputDefDao.insertTemplateInputDef(templateInputDef);
	}

	/**
	 * 批量插入
	 * 
	 * @param templateInputDefs
	 *            批量插入对象
	 * @throws Exception
	 */
	@Override
	public void insertTemplateInputDefBatch(
			List<TemplateInputDef> templateInputDefs) {
		if (templateInputDefs != null) {
			for (TemplateInputDef templateInputDef : templateInputDefs) {
				String pk = primaryKeyUtil.getPrimaryKey();
				templateInputDef.setId(pk);
				templateInputDef.setTs(DateUtil.getTs());
				templateInputDef.setDr(0);
			}
			templateInputDefDao.insertTemplateInputDefBatch(templateInputDefs);
		}
	}

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery) {
		return templateInputDefDao
				.deleteTemplateInputDefById(templateInputDefQuery);
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
		return templateInputDefDao
				.deleteTemplateInputDefByCondition(templateInputDefQuery);
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public Result deleteTemplateInputDefByBatchId(
			TemplateInputDefQuery templateInputDefQuery) throws Exception {
		Result result = new Result();
		result.setSuccess(false);
		Map<String, Object> data = new Hashtable<String, Object>();
		// TODO 如果是多主键修要修改，如果不是删除就可以
		String[] batchIdArr = templateInputDefQuery.getBatchId().split(",");
		data.put("batchId1", batchIdArr);
		boolean flat = templateInputDefDao
				.deleteTemplateInputDefByBatchId(data);
		result.setSuccess(flat);
		return result;
	}

	@Override
	public Result deleteInputDefByTemplateBatchId(
			TemplateInputDefQuery templateInputDefQuery) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setSuccess(false);
		Map<String, Object> data = new Hashtable<String, Object>();
		// TODO 如果是多主键修要修改，如果不是删除就可以
		String[] batchIdArr = templateInputDefQuery.getBatchId().split(",");
		data.put("batchId1", batchIdArr);
		boolean flat = templateInputDefDao
				.deleteInputDefByTemplateBatchId(data);
		result.setSuccess(flat);
		return result;
	}

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery) {
		return templateInputDefDao
				.logicDeleteTemplateInputDefById(templateInputDefQuery);
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
		return templateInputDefDao
				.logicDeleteTemplateInputDefByCondition(templateInputDefQuery);
	}

	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public Result logicDeleteTemplateInputDefByBatchId(
			TemplateInputDefQuery templateInputDefQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = templateInputDefQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			data.put("batchId2", batchIdArr);
			boolean flat = templateInputDefDao
					.logicDeleteTemplateInputDefByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加或修改
	 * 
	 * @param templateInputDef
	 * @return
	 */
	@Override
	public Result insertOrUpdate(TemplateInputDef templateInputDef) {
		Result result = new Result();
		// 设置调用失败
		result.setSuccess(false);
		if (templateInputDef != null) {
			if (StringUtil.isNotEmpty(templateInputDef.getId())) {
				updateTemplateInputDefById(templateInputDef);
			} else {
				String pk = primaryKeyUtil.getPrimaryKey();
				templateInputDef.setId(pk);
				insertTemplateInputDef(templateInputDef);
			}
			// 如果没有异常设置成功
			result.setSuccess(true);
		}
		// 设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
		result.addDefaultModel(templateInputDef);
		// 如果没有异常设置成功
		result.setSuccess(true);
		return null;
	}

	/**
	 * 根据Id修改
	 * 
	 * @param templateInputDef
	 * @return
	 */
	@Override
	public boolean updateTemplateInputDefById(TemplateInputDef templateInputDef) {
		return templateInputDefDao.updateTemplateInputDefById(templateInputDef);
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateInputDefByCondition(
			TemplateInputDefQuery record, TemplateInputDefQuery parameter) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record", record);
		data.put("parameter", parameter);
		return templateInputDefDao.updateTemplateInputDefByCondition(data);
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
		return templateInputDefDao
				.getTemplateInputDefById(templateInputDefQuery);
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
		return templateInputDefDao
				.getTemplateInputDefAll(templateInputDefQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public GridResult<TemplateInputDef> getTemplateInputDefByPage(
			TemplateInputDefQuery templateInputDefQuery) {
		// 如果排序的字段是空或者空字符串
		if (templateInputDefQuery != null
				&& StringUtils.isBlank(templateInputDefQuery.getSort())) {
			templateInputDefQuery.setSort("id");
			templateInputDefQuery.setOrder("desc");
			;
		}
		int total = templateInputDefDao
				.getTemplateInputDefByPageCount(templateInputDefQuery);
		PaginatedList<TemplateInputDef> templateInputDefPageList = new MysqlPaginatedArrayList<TemplateInputDef>(
				templateInputDefQuery, total);
		List<TemplateInputDef> templateInputDefList = templateInputDefDao
				.getTemplateInputDefByPage(templateInputDefQuery);
		templateInputDefPageList.addAll(templateInputDefList);
		GridResult<TemplateInputDef> result = new GridResult<TemplateInputDef>(
				templateInputDefPageList);
		return result;
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateInputDefQuery
	 * @return
	 */
	@Override
	public int getTemplateInputDefByPageCount(
			TemplateInputDefQuery templateInputDefQuery) {
		return templateInputDefDao
				.getTemplateInputDefByPageCount(templateInputDefQuery);
	}

	public void setTemplateInputDefDao(TemplateInputDefDao templateInputDefDao) {
		this.templateInputDefDao = templateInputDefDao;
	}

	/**
	 * 批量根据id更新
	 * 
	 * @param updateLists
	 *            模板输入项集合
	 * @throws Exception
	 */
	@Override
	public void updateBatchTemplateInputDefById(
			List<TemplateInputDef> updateLists) throws Exception {
		// TODO Auto-generated method stub
		templateInputDefDao.updateBatchTemplateInputDefById(updateLists);
	}

}
