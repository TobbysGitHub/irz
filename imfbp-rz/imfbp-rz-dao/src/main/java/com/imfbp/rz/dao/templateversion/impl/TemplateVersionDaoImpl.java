package com.imfbp.rz.dao.templateversion.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.templateversion.TemplateVersion;
import com.imfbp.rz.domain.templateversion.query.TemplateVersionQuery;
import com.imfbp.rz.dao.templateversion.TemplateVersionDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TemplateVersionDaoImpl extends SqlSessionDaoSupport implements TemplateVersionDao{

	/**
	 * 添加
	 * @param templateVersion
	 * @return
	 */
	@Override
	public void insertTemplateVersion(TemplateVersion templateVersion){
		this.getSqlSession().insert("templateVersion.insertTemplateVersion", templateVersion);
	}
	
	/**
	 * 批量添加
	 * @param List<templateVersion>
	 * @return
	 */
	public void insertBatchTemplateVersion(List<TemplateVersion> templateVersionList){
		this.getSqlSession().insert("templateVersion.insertBatchTemplateVersion", templateVersionList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateVersionById(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().delete("templateVersion.deleteTemplateVersionById", templateVersionQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public boolean deleteTemplateVersionByCondition(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().delete("templateVersion.deleteTemplateVersionByCondition", templateVersionQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteTemplateVersionByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("templateVersion.deleteTemplateVersionByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateVersionById(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().update("templateVersion.logicDeleteTemplateVersionById", templateVersionQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateVersionByCondition(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().update("templateVersion.logicDeleteTemplateVersionByCondition", templateVersionQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteTemplateVersionByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("templateVersion.logicDeleteTemplateVersionByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param templateVersionQuery
	 * @return
	 */	
	@Override
	public boolean updateTemplateVersionById(TemplateVersion templateVersion){
		return this.getSqlSession().update("templateVersion.updateTemplateVersionById", templateVersion)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param templateVersionList
	 * @return
	 */
	public boolean updateTemplateVersionByBatchId(List<TemplateVersion> templateVersionList){
		return this.getSqlSession().update("templateVersion.updateTemplateVersionByBatchId", templateVersionList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateTemplateVersionByCondition(Map<String,Object> data){
		return this.getSqlSession().update("templateVersion.updateTemplateVersionByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public TemplateVersion getTemplateVersionById(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().selectOne("templateVersion.getTemplateVersionById",templateVersionQuery);
	}
	
	/**
	 * 查询所有
	 * @param templateVersionQuery
	 * @return
	 */
	@Override
	public List<TemplateVersion> getTemplateVersionAll(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().selectList("templateVersion.getTemplateVersionAll",templateVersionQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<TemplateVersion> getTemplateVersionByPage(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().selectList("templateVersion.getTemplateVersionByPage",templateVersionQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getTemplateVersionByPageCount(TemplateVersionQuery templateVersionQuery){
		return this.getSqlSession().selectOne("templateVersion.getTemplateVersionByPageCount",templateVersionQuery);
	}
}