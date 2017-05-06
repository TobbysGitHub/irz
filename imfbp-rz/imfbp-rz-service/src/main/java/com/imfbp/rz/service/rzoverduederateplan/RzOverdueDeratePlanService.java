package com.imfbp.rz.service.rzoverduederateplan;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzoverduederateplan.RzOverdueDeratePlan;
import com.imfbp.rz.domain.rzoverduederateplan.query.RzOverdueDeratePlanQuery;

public interface RzOverdueDeratePlanService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public Result deleteRzOverdueDeratePlanByBatchId(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzOverdueDeratePlan
	 */
	public Result insertOrUpdate(RzOverdueDeratePlan rzOverdueDeratePlan);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzOverdueDeratePlanById(RzOverdueDeratePlan rzOverdueDeratePlan);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzOverdueDeratePlanByCondition(RzOverdueDeratePlanQuery record,RzOverdueDeratePlanQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public Result updateRzOverdueDeratePlanByBatchId(List<RzOverdueDeratePlan> rzOverdueDeratePlanList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzOverdueDeratePlan> getRzOverdueDeratePlanByPage(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public int getRzOverdueDeratePlanByPageCount(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery);
	
	
}