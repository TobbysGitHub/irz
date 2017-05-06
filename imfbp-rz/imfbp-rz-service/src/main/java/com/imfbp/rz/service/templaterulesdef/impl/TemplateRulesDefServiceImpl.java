package com.imfbp.rz.service.templaterulesdef.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.templaterulesdef.TemplateRulesDef;
import com.imfbp.rz.domain.templaterulesdef.query.TemplateRulesDefQuery;
import com.imfbp.rz.dao.templaterulesdef.TemplateRulesDefDao;
import com.imfbp.rz.service.templaterulesdef.TemplateRulesDefService;
import com.imfbp.rz.util.DateUtil;

@Component("templateRulesDefService")
public class TemplateRulesDefServiceImpl implements TemplateRulesDefService {

	private TemplateRulesDefDao templateRulesDefDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * 
	 * @param templateRulesDef
	 * @return
	 */
	@Override
	public void insertTemplateRulesDef(TemplateRulesDef templateRulesDef) {
		String pk = primaryKeyUtil.getPrimaryKey();
		templateRulesDef.setTs(DateUtil.getTs());
		templateRulesDef.setDr(0);
		templateRulesDef.setId(pk);
		templateRulesDefDao.insertTemplateRulesDef(templateRulesDef);
	}

	/**
	 * 批量添加
	 * 
	 * @param List
	 *            <templateRulesDef>
	 * @return
	 */
	public void insertBatchTemplateRulesDef(
			List<TemplateRulesDef> templateRulesDefList) {
		if (templateRulesDefList != null) {
			for (int i = 0; i < templateRulesDefList.size(); i++) {
				templateRulesDefList.get(i).setTs(DateUtil.getTs());
				templateRulesDefList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				templateRulesDefList.get(i).setId(pk);
			}
			templateRulesDefDao
					.insertBatchTemplateRulesDef(templateRulesDefList);
		}
	}

	@Override
	public boolean updateTemplateRulesDefByBatchId(
			List<TemplateRulesDef> templateRulesDefList) {
		return templateRulesDefDao
				.updateTemplateRulesDefByBatchId(templateRulesDefList);
	}

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return templateRulesDefDao
				.deleteTemplateRulesDefById(templateRulesDefQuery);
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateRulesDefByCondition(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return templateRulesDefDao
				.deleteTemplateRulesDefByCondition(templateRulesDefQuery);
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public Result deleteTemplateRulesDefByBatchId(
			TemplateRulesDefQuery templateRulesDefQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = templateRulesDefQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			boolean flat = templateRulesDefDao
					.deleteTemplateRulesDefByBatchId(data);
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
	public boolean logicDeleteTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return templateRulesDefDao
				.logicDeleteTemplateRulesDefById(templateRulesDefQuery);
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateRulesDefByCondition(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return templateRulesDefDao
				.logicDeleteTemplateRulesDefByCondition(templateRulesDefQuery);
	}

	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public Result logicDeleteTemplateRulesDefByBatchId(
			TemplateRulesDefQuery templateRulesDefQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = templateRulesDefQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			data.put("batchId2", batchIdArr);
			boolean flat = templateRulesDefDao
					.logicDeleteTemplateRulesDefByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加或修改
	 * 
	 * @param templateRulesDef
	 * @return
	 */
	@Override
	public Result insertOrUpdate(TemplateRulesDef templateRulesDef) {
		// 设置调用失败
		Result result = new Result(false);
		try {
			if (templateRulesDef != null) {
				if (StringUtil.isNotEmpty(templateRulesDef.getId())) {
					updateTemplateRulesDefById(templateRulesDef);
				} else {
					insertTemplateRulesDef(templateRulesDef);
				}
				// 如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			// 设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(templateRulesDef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据Id修改
	 * 
	 * @param templateRulesDef
	 * @return
	 */
	@Override
	public boolean updateTemplateRulesDefById(TemplateRulesDef templateRulesDef) {
		return templateRulesDefDao.updateTemplateRulesDefById(templateRulesDef);
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateRulesDefByCondition(
			TemplateRulesDefQuery record, TemplateRulesDefQuery parameter) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record", record);
		data.put("parameter", parameter);
		return templateRulesDefDao.updateTemplateRulesDefByCondition(data);
	}

	/**
	 * 根据id查询
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public TemplateRulesDef getTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return templateRulesDefDao
				.getTemplateRulesDefById(templateRulesDefQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public List<TemplateRulesDef> getTemplateRulesDefAll(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return templateRulesDefDao
				.getTemplateRulesDefAll(templateRulesDefQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public GridResult<TemplateRulesDef> getTemplateRulesDefByPage(
			TemplateRulesDefQuery templateRulesDefQuery) {
		// 如果排序的字段是空或者空字符串
		if (templateRulesDefQuery != null
				&& StringUtils.isBlank(templateRulesDefQuery.getSort())) {
			templateRulesDefQuery.setSort("id");
			templateRulesDefQuery.setOrder("desc");
		}
		int total = templateRulesDefDao
				.getTemplateRulesDefByPageCount(templateRulesDefQuery);
		PaginatedList<TemplateRulesDef> templateRulesDefPageList = new MysqlPaginatedArrayList<TemplateRulesDef>(
				templateRulesDefQuery, total);
		List<TemplateRulesDef> templateRulesDefList = templateRulesDefDao
				.getTemplateRulesDefByPage(templateRulesDefQuery);
		templateRulesDefPageList.addAll(templateRulesDefList);
		GridResult<TemplateRulesDef> result = new GridResult<TemplateRulesDef>(
				templateRulesDefPageList);
		return result;
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateRulesDefQuery
	 * @return
	 */
	@Override
	public int getTemplateRulesDefByPageCount(
			TemplateRulesDefQuery templateRulesDefQuery) {
		return templateRulesDefDao
				.getTemplateRulesDefByPageCount(templateRulesDefQuery);
	}

	public void setTemplateRulesDefDao(TemplateRulesDefDao templateRulesDefDao) {
		this.templateRulesDefDao = templateRulesDefDao;
	}

	@Override
	public Result insertOrUpdateForBatch(
			List<TemplateRulesDef> templateRulesDefList) throws Exception {
		// TODO Auto-generated method stub
		// 校验数据
		if (templateRulesDefList == null || templateRulesDefList.size() == 0) {
			throw new Exception("不存在需要保存的模板业务规则数据");
		}
		Result result = new Result();
		result.setSuccess(false);
		// 区分新增与修改数据
		List<TemplateRulesDef> insertLists = new ArrayList<TemplateRulesDef>();
		List<TemplateRulesDef> updateLists = new ArrayList<TemplateRulesDef>();
		for (TemplateRulesDef trd : templateRulesDefList) {
			if (StringUtil.isEmpty(trd.getId())) {
				insertLists.add(trd);
			} else {
				updateLists.add(trd);
			}
		}
		// 持久化
		if (insertLists.size() > 0) {
			insertBatchTemplateRulesDef(insertLists);
		}
		if (updateLists.size() > 0) {
			updateTemplateRulesDefByBatchId(updateLists);
		}
		result.setSuccess(true);
		result.setSuccessMessage("保存模板业务规则数据成功");
		// 返回结果
		return result;
	}

}