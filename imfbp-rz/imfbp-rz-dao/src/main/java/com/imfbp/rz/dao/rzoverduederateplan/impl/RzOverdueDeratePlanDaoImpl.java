package com.imfbp.rz.dao.rzoverduederateplan.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzoverduederateplan.RzOverdueDeratePlan;
import com.imfbp.rz.domain.rzoverduederateplan.query.RzOverdueDeratePlanQuery;
import com.imfbp.rz.dao.rzoverduederateplan.RzOverdueDeratePlanDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzOverdueDeratePlanDaoImpl extends SqlSessionDaoSupport implements RzOverdueDeratePlanDao{

	/**
	 * 添加
	 * @param rzOverdueDeratePlan
	 * @return
	 */
	@Override
	public void insertRzOverdueDeratePlan(RzOverdueDeratePlan rzOverdueDeratePlan){
		this.getSqlSession().insert("rzOverdueDeratePlan.insertRzOverdueDeratePlan", rzOverdueDeratePlan);
	}
	
	/**
	 * 批量添加
	 * @param List<rzOverdueDeratePlan>
	 * @return
	 */
	public void insertBatchRzOverdueDeratePlan(List<RzOverdueDeratePlan> rzOverdueDeratePlanList){
		this.getSqlSession().insert("rzOverdueDeratePlan.insertBatchRzOverdueDeratePlan", rzOverdueDeratePlanList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDeratePlanById(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return this.getSqlSession().delete("rzOverdueDeratePlan.deleteRzOverdueDeratePlanById", rzOverdueDeratePlanQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDeratePlanByCondition(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return this.getSqlSession().delete("rzOverdueDeratePlan.deleteRzOverdueDeratePlanByCondition", rzOverdueDeratePlanQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDeratePlanByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzOverdueDeratePlan.deleteRzOverdueDeratePlanByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */	
	@Override
	public boolean updateRzOverdueDeratePlanById(RzOverdueDeratePlan rzOverdueDeratePlan){
		return this.getSqlSession().update("rzOverdueDeratePlan.updateRzOverdueDeratePlanById", rzOverdueDeratePlan)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDeratePlanList
	 * @return
	 */
	public boolean updateRzOverdueDeratePlanByBatchId(List<RzOverdueDeratePlan> rzOverdueDeratePlanList){
		return this.getSqlSession().update("rzOverdueDeratePlan.updateRzOverdueDeratePlanByBatchId", rzOverdueDeratePlanList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzOverdueDeratePlanByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzOverdueDeratePlan.updateRzOverdueDeratePlanByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public RzOverdueDeratePlan getRzOverdueDeratePlanById(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return this.getSqlSession().selectOne("rzOverdueDeratePlan.getRzOverdueDeratePlanById",rzOverdueDeratePlanQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public List<RzOverdueDeratePlan> getRzOverdueDeratePlanAll(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return this.getSqlSession().selectList("rzOverdueDeratePlan.getRzOverdueDeratePlanAll",rzOverdueDeratePlanQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzOverdueDeratePlan> getRzOverdueDeratePlanByPage(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return this.getSqlSession().selectList("rzOverdueDeratePlan.getRzOverdueDeratePlanByPage",rzOverdueDeratePlanQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzOverdueDeratePlanByPageCount(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return this.getSqlSession().selectOne("rzOverdueDeratePlan.getRzOverdueDeratePlanByPageCount",rzOverdueDeratePlanQuery);
	}
}