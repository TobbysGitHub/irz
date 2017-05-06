package com.imfbp.rz.service.templatedef.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.constant.RZConstants;
import com.imfbp.rz.dao.templatedef.TemplateDefDao;
import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;
import com.imfbp.rz.service.templatedef.TemplateDefService;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

@Component("templateDefService")
public class TemplateDefServiceImpl implements TemplateDefService {

	private TemplateDefDao templateDefDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * 
	 * @param templateDef
	 * @return
	 */
	@Override
	public void insertTemplateDef(TemplateDef templateDef) {
		String pk = primaryKeyUtil.getPrimaryKey();
		templateDef.setId(pk);
		templateDefDao.insertTemplateDef(templateDef);
	}

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteTemplateDefById(TemplateDefQuery templateDefQuery) {
		return templateDefDao.deleteTemplateDefById(templateDefQuery);
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateDefByCondition(
			TemplateDefQuery templateDefQuery) {
		return templateDefDao.deleteTemplateDefByCondition(templateDefQuery);
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public Result deleteTemplateDefByBatchId(TemplateDefQuery templateDefQuery)
			throws Exception {
		Result result = new Result();
		result.setSuccess(false);
		Map<String, Object> data = new Hashtable<String, Object>();
		// TODO 如果是多主键修要修改，如果不是删除就可以
		String[] batchIdArr = templateDefQuery.getBatchId().split(",");
		data.put("batchId1", batchIdArr);
		boolean flat = templateDefDao.deleteTemplateDefByBatchId(data);
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
	public boolean logicDeleteTemplateDefById(TemplateDefQuery templateDefQuery) {
		return templateDefDao.logicDeleteTemplateDefById(templateDefQuery);
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateDefByCondition(
			TemplateDefQuery templateDefQuery) {
		return templateDefDao
				.logicDeleteTemplateDefByCondition(templateDefQuery);
	}

	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public Result logicDeleteTemplateDefByBatchId(
			TemplateDefQuery templateDefQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = templateDefQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			data.put("batchId2", batchIdArr);
			boolean flat = templateDefDao.logicDeleteTemplateDefByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据Id修改
	 * 
	 * @param templateDef
	 * @return
	 */
	@Override
	public Result insertOrUpdate(TemplateDef templateDef) throws Exception {
		Result result = new Result();
		// 设置调用失败
		result.setSuccess(false);
		// 校验数据必输项是否填写
		if (templateDef == null) {
			throw new Exception("保存模板时，模板定义对象不能为空");
		}
		if (templateDef != null) {
			if (StringUtil.isNotEmpty(templateDef.getId())) {
				updateTemplateDefById(templateDef);
			} else {
				String pk = primaryKeyUtil.getPrimaryKey();
				templateDef.setId(pk);
				insertTemplateDef(templateDef);
			}
			// 如果没有异常设置成功
			result.setSuccess(true);
		}
		// 设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
		result.addDefaultModel("templateDef", templateDef);

		return result;
	}

	/**
	 * 根据Id修改
	 * 
	 * @param templateDef
	 * @return
	 */
	@Override
	public boolean updateTemplateDefById(TemplateDef templateDef) {
		return templateDefDao.updateTemplateDefById(templateDef);
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateDefByCondition(TemplateDefQuery record,
			TemplateDefQuery parameter) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record", record);
		data.put("parameter", parameter);
		return templateDefDao.updateTemplateDefByCondition(data);
	}

	/**
	 * 根据id查询
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public TemplateDef getTemplateDefById(TemplateDefQuery templateDefQuery) {
		TemplateDef templateDef = templateDefDao
				.getTemplateDefById(templateDefQuery);
		templateDef.setTemplateFileName(templateDef.getTemplateName()
				+ RZConstants.FILE_SPLIT + RZConstants.FILE_TYPE_DOC);
		return templateDef;
	}

	/**
	 * 查询所有
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public List<TemplateDef> getTemplateDefAll(TemplateDefQuery templateDefQuery) {
		List<TemplateDef> list = templateDefDao
				.getTemplateDefAll(templateDefQuery);
		changeBeanData(list);
		return list;
	}

	private void changeBeanData(List<TemplateDef> list) {
		if (list != null && list.size() > 0) {
			for (TemplateDef td : list) {
				td.setTemplateFileName(td.getTemplateName()
						+ RZConstants.FILE_SPLIT + RZConstants.FILE_TYPE_DOC);
			}
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public GridResult<TemplateDef> getTemplateDefByPage(
			TemplateDefQuery templateDefQuery) {
		// 如果排序的字段是空或者空字符串
		if (templateDefQuery != null
				&& StringUtils.isBlank(templateDefQuery.getSort())) {
			templateDefQuery.setSort("id");
			templateDefQuery.setOrder("desc");
			;
		}
		int total = templateDefDao.getTemplateDefByPageCount(templateDefQuery);
		PaginatedList<TemplateDef> templateDefPageList = new MysqlPaginatedArrayList<TemplateDef>(
				templateDefQuery, total);
		List<TemplateDef> templateDefList = templateDefDao
				.getTemplateDefByPage(templateDefQuery);
		changeBeanData(templateDefList);
		templateDefPageList.addAll(templateDefList);
		GridResult<TemplateDef> result = new GridResult<TemplateDef>(
				templateDefPageList);
		return result;
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param templateDefQuery
	 * @return
	 */
	@Override
	public int getTemplateDefByPageCount(TemplateDefQuery templateDefQuery) {
		return templateDefDao.getTemplateDefByPageCount(templateDefQuery);
	}

	public void setTemplateDefDao(TemplateDefDao templateDefDao) {
		this.templateDefDao = templateDefDao;
	}

	@Override
	public List<TemplateDef> getTemplateDefBybatchIds(List<String> batchIds) {
		// TODO Auto-generated method stub
		List<TemplateDef> templateDefList = templateDefDao
				.getTemplateDefBybatchIds(batchIds);
		changeBeanData(templateDefList);
		return templateDefList;
	}

	@Override
	public List<TemplateDef> getMaxVersionTemplateDefById(
			TemplateDefQuery templateDefQuery) throws Exception {
		// TODO Auto-generated method stub
		List<TemplateDef> templateDefList = templateDefDao
				.getMaxVersionTemplateDefById(templateDefQuery);
		changeBeanData(templateDefList);
		return templateDefList;
	}

}
