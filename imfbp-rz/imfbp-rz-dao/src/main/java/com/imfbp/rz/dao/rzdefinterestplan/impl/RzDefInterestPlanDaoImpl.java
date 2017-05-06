package com.imfbp.rz.dao.rzdefinterestplan.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;
import com.imfbp.rz.dao.rzdefinterestplan.RzDefInterestPlanDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzDefInterestPlanDaoImpl extends SqlSessionDaoSupport implements RzDefInterestPlanDao{

	/**
	 * 添加
	 * @param rzDefInterestPlan
	 * @return
	 */
	@Override
	public void insertRzDefInterestPlan(RzDefInterestPlan rzDefInterestPlan){
		this.getSqlSession().insert("rzDefInterestPlan.insertRzDefInterestPlan", rzDefInterestPlan);
	}
	
	/**
	 * 批量添加
	 * @param List<rzDefInterestPlan>
	 * @return
	 */
	public void insertBatchRzDefInterestPlan(List<RzDefInterestPlan> rzDefInterestPlanList){
		this.getSqlSession().insert("rzDefInterestPlan.insertBatchRzDefInterestPlan", rzDefInterestPlanList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestPlanById(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return this.getSqlSession().delete("rzDefInterestPlan.deleteRzDefInterestPlanById", rzDefInterestPlanQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestPlanByCondition(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return this.getSqlSession().delete("rzDefInterestPlan.deleteRzDefInterestPlanByCondition", rzDefInterestPlanQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestPlanByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzDefInterestPlan.deleteRzDefInterestPlanByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzDefInterestPlanQuery
	 * @return
	 */	
	@Override
	public boolean updateRzDefInterestPlanById(RzDefInterestPlan rzDefInterestPlan){
		return this.getSqlSession().update("rzDefInterestPlan.updateRzDefInterestPlanById", rzDefInterestPlan)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestPlanList
	 * @return
	 */
	public boolean updateRzDefInterestPlanByBatchId(List<RzDefInterestPlan> rzDefInterestPlanList){
		return this.getSqlSession().update("rzDefInterestPlan.updateRzDefInterestPlanByBatchId", rzDefInterestPlanList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzDefInterestPlanByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzDefInterestPlan.updateRzDefInterestPlanByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public RzDefInterestPlan getRzDefInterestPlanById(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return this.getSqlSession().selectOne("rzDefInterestPlan.getRzDefInterestPlanById",rzDefInterestPlanQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public List<RzDefInterestPlan> getRzDefInterestPlanAll(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return this.getSqlSession().selectList("rzDefInterestPlan.getRzDefInterestPlanAll",rzDefInterestPlanQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzDefInterestPlan> getRzDefInterestPlanByPage(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return this.getSqlSession().selectList("rzDefInterestPlan.getRzDefInterestPlanByPage",rzDefInterestPlanQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzDefInterestPlanByPageCount(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return this.getSqlSession().selectOne("rzDefInterestPlan.getRzDefInterestPlanByPageCount",rzDefInterestPlanQuery);
	}
}