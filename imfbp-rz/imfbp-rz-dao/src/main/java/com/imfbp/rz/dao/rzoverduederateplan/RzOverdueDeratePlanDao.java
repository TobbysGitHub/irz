package com.imfbp.rz.dao.rzoverduederateplan;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzoverduederateplan.RzOverdueDeratePlan;
import com.imfbp.rz.domain.rzoverduederateplan.query.RzOverdueDeratePlanQuery;

public interface RzOverdueDeratePlanDao{

	/**
	 * 添加
	 * @param rzOverdueDeratePlan
	 * @return
	 */
	public void insertRzOverdueDeratePlan(RzOverdueDeratePlan rzOverdueDeratePlan);
	
	/**
	 * 批量添加
	 * @param List<rzOverdueDeratePlan>
	 * @return
	 */
	public void insertBatchRzOverdueDeratePlan(List<RzOverdueDeratePlan> rzOverdueDeratePlanList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public boolean deleteRzOverdueDeratePlanById(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public boolean deleteRzOverdueDeratePlanByCondition(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public boolean deleteRzOverdueDeratePlanByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public boolean updateRzOverdueDeratePlanById(RzOverdueDeratePlan rzOverdueDeratePlan);
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public boolean updateRzOverdueDeratePlanByBatchId(List<RzOverdueDeratePlan> rzOverdueDeratePlanList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzOverdueDeratePlanByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public RzOverdueDeratePlan getRzOverdueDeratePlanById(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	/**
	 * 查询所有
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public List<RzOverdueDeratePlan> getRzOverdueDeratePlanAll(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	/**
	 * 分页查询
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public List<RzOverdueDeratePlan> getRzOverdueDeratePlanByPage(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public Integer getRzOverdueDeratePlanByPageCount(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
}