package com.imfbp.rz.dao.templatenodedef.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.templatenodedef.TemplateNodeDef;
import com.imfbp.rz.domain.templatenodedef.query.TemplateNodeDefQuery;
import com.imfbp.rz.dao.templatenodedef.TemplateNodeDefDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TemplateNodeDefDaoImpl extends SqlSessionDaoSupport implements TemplateNodeDefDao{

	/**
	 * 添加
	 * @param templateNodeDef
	 * @return
	 */
	@Override
	public void insertTemplateNodeDef(TemplateNodeDef templateNodeDef){
		this.getSqlSession().insert("templateNodeDef.insertTemplateNodeDef", templateNodeDef);
	}
	
	/**
	 * 批量添加
	 * @param List<templateNodeDef>
	 * @return
	 */
	public void insertBatchTemplateNodeDef(List<TemplateNodeDef> templateNodeDefList){
		this.getSqlSession().insert("templateNodeDef.insertBatchTemplateNodeDef", templateNodeDefList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().delete("templateNodeDef.deleteTemplateNodeDefById", templateNodeDefQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateNodeDefByCondition(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().delete("templateNodeDef.deleteTemplateNodeDefById", templateNodeDefQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteTemplateNodeDefByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("templateNodeDef.deleteTemplateNodeDefByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().update("templateNodeDef.logicDeleteTemplateNodeDefById", templateNodeDefQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateNodeDefByCondition(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().update("templateNodeDef.logicDeleteTemplateNodeDefById", templateNodeDefQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateNodeDefByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("templateNodeDef.logicDeleteTemplateNodeDefByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param templateNodeDefQuery
	 * @return
	 */	
	@Override
	public boolean updateTemplateNodeDefById(TemplateNodeDef templateNodeDef){
		return this.getSqlSession().update("templateNodeDef.updateTemplateNodeDefById", templateNodeDef)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateNodeDefByCondition(Map<String,Object> data){
		return this.getSqlSession().update("templateNodeDef.updateTemplateNodeDefByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public TemplateNodeDef getTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().selectOne("templateNodeDef.getTemplateNodeDefById",templateNodeDefQuery);
	}
	
	/**
	 * 查询所有
	 * @param templateNodeDefQuery
	 * @return
	 */
	@Override
	public List<TemplateNodeDef> getTemplateNodeDefAll(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().selectList("templateNodeDef.getTemplateNodeDefAll",templateNodeDefQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<TemplateNodeDef> getTemplateNodeDefByPage(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().selectList("templateNodeDef.getTemplateNodeDefByPage",templateNodeDefQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getTemplateNodeDefByPageCount(TemplateNodeDefQuery templateNodeDefQuery){
		return this.getSqlSession().selectOne("templateNodeDef.getTemplateNodeDefByPageCount",templateNodeDefQuery);
	}
}