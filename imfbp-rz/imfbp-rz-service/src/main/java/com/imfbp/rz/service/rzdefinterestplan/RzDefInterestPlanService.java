package com.imfbp.rz.service.rzdefinterestplan;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;

public interface RzDefInterestPlanService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public Result deleteRzDefInterestPlanByBatchId(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzDefInterestPlan
	 */
	public Result insertOrUpdate(RzDefInterestPlan rzDefInterestPlan);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzDefInterestPlanById(RzDefInterestPlan rzDefInterestPlan);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzDefInterestPlanByCondition(RzDefInterestPlanQuery record,RzDefInterestPlanQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public Result updateRzDefInterestPlanByBatchId(List<RzDefInterestPlan> rzDefInterestPlanList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzDefInterestPlan> getRzDefInterestPlanByPage(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public int getRzDefInterestPlanByPageCount(RzDefInterestPlanQuery rzDefInterestPlanQuery);
	
	 public RzDefInterestPlanQuery getAllDeleteIds(List<RzDefInterestPlan>list);
}