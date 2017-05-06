package com.imfbp.rz.dao.rzbaseparam.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.dao.rzbaseparam.RzBaseParamDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzBaseParamDaoImpl extends SqlSessionDaoSupport implements RzBaseParamDao{

	/**
	 * 添加
	 * @param rzBaseParam
	 * @return
	 */
	@Override
	public void insertRzBaseParam(RzBaseParam rzBaseParam){
		this.getSqlSession().insert("rzBaseParam.insertRzBaseParam", rzBaseParam);
	}
	
	/**
	 * 批量添加
	 * @param List<rzBaseParam>
	 * @return
	 */
	public void insertBatchRzBaseParam(List<RzBaseParam> rzBaseParamList){
		this.getSqlSession().insert("rzBaseParam.insertBatchRzBaseParam", rzBaseParamList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public boolean deleteRzBaseParamById(RzBaseParamQuery rzBaseParamQuery){
		return this.getSqlSession().delete("rzBaseParam.deleteRzBaseParamById", rzBaseParamQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public boolean deleteRzBaseParamByCondition(RzBaseParamQuery rzBaseParamQuery){
		return this.getSqlSession().delete("rzBaseParam.deleteRzBaseParamByCondition", rzBaseParamQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzBaseParamByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzBaseParam.deleteRzBaseParamByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzBaseParamQuery
	 * @return
	 */	
	@Override
	public boolean updateRzBaseParamById(RzBaseParam rzBaseParam){
		return this.getSqlSession().update("rzBaseParam.updateRzBaseParamById", rzBaseParam)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzBaseParamList
	 * @return
	 */
	public boolean updateRzBaseParamByBatchId(List<RzBaseParam> rzBaseParamList){
		return this.getSqlSession().update("rzBaseParam.updateRzBaseParamByBatchId", rzBaseParamList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzBaseParamByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzBaseParam.updateRzBaseParamByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public RzBaseParam getRzBaseParamById(RzBaseParamQuery rzBaseParamQuery){
		return this.getSqlSession().selectOne("rzBaseParam.getRzBaseParamById",rzBaseParamQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public List<RzBaseParam> getRzBaseParamAll(RzBaseParamQuery rzBaseParamQuery){
		return this.getSqlSession().selectList("rzBaseParam.getRzBaseParamAll",rzBaseParamQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzBaseParam> getRzBaseParamByPage(RzBaseParamQuery rzBaseParamQuery){
		return this.getSqlSession().selectList("rzBaseParam.getRzBaseParamByPage",rzBaseParamQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzBaseParamByPageCount(RzBaseParamQuery rzBaseParamQuery){
		return this.getSqlSession().selectOne("rzBaseParam.getRzBaseParamByPageCount",rzBaseParamQuery);
	}
}