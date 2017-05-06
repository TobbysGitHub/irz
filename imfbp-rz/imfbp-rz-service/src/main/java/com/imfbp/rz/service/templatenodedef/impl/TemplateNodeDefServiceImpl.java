package com.imfbp.rz.service.templatenodedef.impl;

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

import com.imfbp.rz.domain.templatenodedef.TemplateNodeDef;
import com.imfbp.rz.domain.templatenodedef.query.TemplateNodeDefQuery;
import com.imfbp.rz.dao.templatenodedef.TemplateNodeDefDao;
import com.imfbp.rz.service.templatenodedef.TemplateNodeDefService;
import com.imfbp.rz.util.DateUtil;





@Component("templateNodeDefService")
public class TemplateNodeDefServiceImpl implements TemplateNodeDefService{


	private TemplateNodeDefDao templateNodeDefDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param templateNodeDef
	 * @return
	 */
	@Override
	public void insertTemplateNodeDef(TemplateNodeDef templateNodeDef){
		String pk = primaryKeyUtil.getPrimaryKey();
		templateNodeDef.setTs(DateUtil.getTs());
		templateNodeDef.setDr(0);
		templateNodeDef.setId(pk);
		templateNodeDefDao.insertTemplateNodeDef(templateNodeDef);	
	}
	
	/**
	 * 批量添加
	 * @param List<templateNodeDef>
	 * @return
	 */
	public void insertBatchTemplateNodeDef(List<TemplateNodeDef> templateNodeDefList){
		if(templateNodeDefList != null){
			for(int i=0;i<templateNodeDefList.size();i++){
				templateNodeDefList.get(i).setTs(DateUtil.getTs());
				templateNodeDefList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				templateNodeDefList.get(i).setId(pk);
			}
			templateNodeDefDao.insertBatchTemplateNodeDef(templateNodeDefList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery){
		return templateNodeDefDao.deleteTemplateNodeDefById(templateNodeDefQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateNodeDefByCondition(TemplateNodeDefQuery templateNodeDefQuery){
		return templateNodeDefDao.deleteTemplateNodeDefByCondition(templateNodeDefQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param templateNodeDefQuery
	 * @return
	 */	
	@Override
	public Result deleteTemplateNodeDefByBatchId(TemplateNodeDefQuery templateNodeDefQuery) {
		Result result = new Result(false);
		Map<String, Object> data = new Hashtable<String, Object>();
		//TODO 如果是多主键修要修改，如果不是删除就可以
		String [] batchIdArr = templateNodeDefQuery.getBatchId().split(",");
		data.put("batchId1",batchIdArr);
		boolean flat = templateNodeDefDao.deleteTemplateNodeDefByBatchId(data);	
		result.setSuccess(flat);
		return result;
	}
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery){
		return templateNodeDefDao.logicDeleteTemplateNodeDefById(templateNodeDefQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateNodeDefByCondition(TemplateNodeDefQuery templateNodeDefQuery){
		return templateNodeDefDao.logicDeleteTemplateNodeDefByCondition(templateNodeDefQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param templateNodeDefQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteTemplateNodeDefByBatchId(TemplateNodeDefQuery templateNodeDefQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = templateNodeDefQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = templateNodeDefDao.logicDeleteTemplateNodeDefByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param templateNodeDef
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(TemplateNodeDef templateNodeDef) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(templateNodeDef!=null){
				if(StringUtil.isNotEmpty(templateNodeDef.getId())){
					updateTemplateNodeDefById(templateNodeDef);
				}else{
					insertTemplateNodeDef(templateNodeDef);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(templateNodeDef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param templateNodeDef
	 * @return
	 */
	@Override
	public boolean updateTemplateNodeDefById(TemplateNodeDef templateNodeDef){
		return templateNodeDefDao.updateTemplateNodeDefById(templateNodeDef);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateNodeDefByCondition(TemplateNodeDefQuery record,TemplateNodeDefQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return templateNodeDefDao.updateTemplateNodeDefByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public TemplateNodeDef getTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery){
		return templateNodeDefDao.getTemplateNodeDefById(templateNodeDefQuery);
	}
	
	/**
	 * 查询所有
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public List<TemplateNodeDef> getTemplateNodeDefAll(TemplateNodeDefQuery templateNodeDefQuery){
		return templateNodeDefDao.getTemplateNodeDefAll(templateNodeDefQuery);
	}
	
	/**
	 * 分页查询
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public GridResult<TemplateNodeDef> getTemplateNodeDefByPage(TemplateNodeDefQuery templateNodeDefQuery){
		//如果排序的字段是空或者空字符串
		if(templateNodeDefQuery!=null&&StringUtils.isBlank(templateNodeDefQuery.getSort())){
			templateNodeDefQuery.setSort("id");
			templateNodeDefQuery.setOrder("desc");;
		}
		int total = templateNodeDefDao.getTemplateNodeDefByPageCount(templateNodeDefQuery);
		PaginatedList<TemplateNodeDef> templateNodeDefPageList = new MysqlPaginatedArrayList<TemplateNodeDef>(templateNodeDefQuery,total);
		List<TemplateNodeDef> templateNodeDefList = templateNodeDefDao.getTemplateNodeDefByPage(templateNodeDefQuery);
		templateNodeDefPageList.addAll(templateNodeDefList);
		GridResult<TemplateNodeDef> result = new GridResult<TemplateNodeDef>(templateNodeDefPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public int getTemplateNodeDefByPageCount(TemplateNodeDefQuery templateNodeDefQuery){
		return templateNodeDefDao.getTemplateNodeDefByPageCount(templateNodeDefQuery);
	}

	public void setTemplateNodeDefDao(TemplateNodeDefDao  templateNodeDefDao){
		this.templateNodeDefDao = templateNodeDefDao;
	}
	
}