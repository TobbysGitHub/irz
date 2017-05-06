package com.imfbp.rz.dao.rzplanchange.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzplanchange.RzPlanChange;
import com.imfbp.rz.domain.rzplanchange.query.RzPlanChangeQuery;
import com.imfbp.rz.dao.rzplanchange.RzPlanChangeDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPlanChangeDaoImpl extends SqlSessionDaoSupport implements RzPlanChangeDao{

	/**
	 * 添加
	 * @param rzPlanChange
	 * @return
	 */
	@Override
	public void insertRzPlanChange(RzPlanChange rzPlanChange){
		this.getSqlSession().insert("rzPlanChange.insertRzPlanChange", rzPlanChange);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPlanChange>
	 * @return
	 */
	public void insertBatchRzPlanChange(List<RzPlanChange> rzPlanChangeList){
		this.getSqlSession().insert("rzPlanChange.insertBatchRzPlanChange", rzPlanChangeList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().delete("rzPlanChange.deleteRzPlanChangeById", rzPlanChangeQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().delete("rzPlanChange.deleteRzPlanChangeByCondition", rzPlanChangeQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPlanChange.deleteRzPlanChangeByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().update("rzPlanChange.logicDeleteRzPlanChangeById", rzPlanChangeQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().update("rzPlanChange.logicDeleteRzPlanChangeByCondition", rzPlanChangeQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPlanChangeByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPlanChange.logicDeleteRzPlanChangeByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPlanChangeQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPlanChangeById(RzPlanChange rzPlanChange){
		return this.getSqlSession().update("rzPlanChange.updateRzPlanChangeById", rzPlanChange)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeList
	 * @return
	 */
	public boolean updateRzPlanChangeByBatchId(List<RzPlanChange> rzPlanChangeList){
		return this.getSqlSession().update("rzPlanChange.updateRzPlanChangeByBatchId", rzPlanChangeList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPlanChange.updateRzPlanChangeByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public RzPlanChange getRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().selectOne("rzPlanChange.getRzPlanChangeById",rzPlanChangeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public List<RzPlanChange> getRzPlanChangeAll(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().selectList("rzPlanChange.getRzPlanChangeAll",rzPlanChangeQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPlanChange> getRzPlanChangeByPage(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().selectList("rzPlanChange.getRzPlanChangeByPage",rzPlanChangeQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPlanChangeByPageCount(RzPlanChangeQuery rzPlanChangeQuery){
		return this.getSqlSession().selectOne("rzPlanChange.getRzPlanChangeByPageCount",rzPlanChangeQuery);
	}
}