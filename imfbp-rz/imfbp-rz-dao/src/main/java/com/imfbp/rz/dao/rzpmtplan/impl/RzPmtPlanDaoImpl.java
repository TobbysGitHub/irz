package com.imfbp.rz.dao.rzpmtplan.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.rzpmtplan.RzPmtPlanDao;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.RzpmtplanVo;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;

public class RzPmtPlanDaoImpl extends SqlSessionDaoSupport implements RzPmtPlanDao{

	/**
	 * 添加
	 * @param rzPmtPlan
	 * @return
	 */
	@Override
	public void insertRzPmtPlan(RzPmtPlan rzPmtPlan){
		this.getSqlSession().insert("rzPmtPlan.insertRzPmtPlan", rzPmtPlan);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlan>
	 * @return
	 */
	public void insertBatchRzPmtPlan(List<RzPmtPlan> rzPmtPlanList){
		this.getSqlSession().insert("rzPmtPlan.insertBatchRzPmtPlan", rzPmtPlanList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery){
		return this.getSqlSession().delete("rzPmtPlan.deleteRzPmtPlanById", rzPmtPlanQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanByCondition(RzPmtPlanQuery rzPmtPlanQuery){
		return this.getSqlSession().delete("rzPmtPlan.deleteRzPmtPlanByCondition", rzPmtPlanQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPmtPlan.deleteRzPmtPlanByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery){
		return this.getSqlSession().update("rzPmtPlan.logicDeleteRzPmtPlanById", rzPmtPlanQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPmtPlanByCondition(RzPmtPlanQuery rzPmtPlanQuery){
		return this.getSqlSession().update("rzPmtPlan.logicDeleteRzPmtPlanByCondition", rzPmtPlanQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPmtPlanByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPmtPlan.logicDeleteRzPmtPlanByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPmtPlanQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPmtPlanById(RzPmtPlan rzPmtPlan){
		return this.getSqlSession().update("rzPmtPlan.updateRzPmtPlanById", rzPmtPlan)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanList
	 * @return
	 */
	public boolean updateRzPmtPlanByBatchId(List<RzPmtPlan> rzPmtPlanList){
		return this.getSqlSession().update("rzPmtPlan.updateRzPmtPlanByBatchId", rzPmtPlanList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPmtPlan.updateRzPmtPlanByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public RzPmtPlan getRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery){
		return this.getSqlSession().selectOne("rzPmtPlan.getRzPmtPlanById",rzPmtPlanQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public List<RzPmtPlan> getRzPmtPlanAll(RzPmtPlanQuery rzPmtPlanQuery){
		return this.getSqlSession().selectList("rzPmtPlan.getRzPmtPlanAll",rzPmtPlanQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPmtPlan> getRzPmtPlanByPage(RzpmtplanVo rzPmtPlanQuery){
		return this.getSqlSession().selectList("rzPmtPlan.getRzPmtPlanByPage",rzPmtPlanQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPmtPlanByPageCount(RzpmtplanVo rzPmtPlanQuery){
		return this.getSqlSession().selectOne("rzPmtPlan.getRzPmtPlanByPageCount",rzPmtPlanQuery);
	}

	@Override
	public String getMaxVersion(RzpmtplanVo rzPmtPlanQuery) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("rzPmtPlan.getMaxVersion",rzPmtPlanQuery);
	}
}