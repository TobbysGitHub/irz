package com.imfbp.rz.dao.rzdefinterestplan;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;

public interface RzDefInterestPlanDao{

	/**
	 * 添加
	 * @param rzDefInterestPlan
	 * @return
	 */
	public void insertRzDefInterestPlan(RzDefInterestPlan rzDefInterestPlan);
	
	/**
	 * 批量添加
	 * @param List<rzDefInterestPlan>
	 * @return
	 */
	public void insertBatchRzDefInterestPlan(List<RzDefInterestPlan> rzDefInterestPlanList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public boolean deleteRzDefInterestPlanById(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public boolean deleteRzDefInterestPlanByCondition(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public boolean deleteRzDefInterestPlanByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public boolean updateRzDefInterestPlanById(RzDefInterestPlan rzDefInterestPlan);
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public boolean updateRzDefInterestPlanByBatchId(List<RzDefInterestPlan> rzDefInterestPlanList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzDefInterestPlanByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public RzDefInterestPlan getRzDefInterestPlanById(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	/**
	 * 查询所有
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public List<RzDefInterestPlan> getRzDefInterestPlanAll(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	/**
	 * 分页查询
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public List<RzDefInterestPlan> getRzDefInterestPlanByPage(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public Integer getRzDefInterestPlanByPageCount(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
}