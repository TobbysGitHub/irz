package com.imfbp.rz.service.rzpmtplan;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.RzpmtplanVo;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;

public interface RzPmtPlanService{

	/**
	 * 添加
	 * @param rzPmtPlan
	 * @return
	 */
	public void insertRzPmtPlan(RzPmtPlan rzPmtPlan);
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlan>
	 * @return
	 */
	public void insertBatchRzPmtPlan(List<RzPmtPlan> rzPmtPlanList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public boolean deleteRzPmtPlanByCondition(RzPmtPlanQuery rzPmtPlanQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public Result deleteRzPmtPlanByBatchId(RzPmtPlanQuery rzPmtPlanQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public boolean logicDeleteRzPmtPlanByCondition(RzPmtPlanQuery rzPmtPlanQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public Result logicDeleteRzPmtPlanByBatchId(RzPmtPlanQuery rzPmtPlanQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPmtPlan
	 */
	public Result insertOrUpdate(RzPmtPlan rzPmtPlan);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPmtPlanById(RzPmtPlan rzPmtPlan);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPmtPlanByCondition(RzPmtPlanQuery record,RzPmtPlanQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public Result updateRzPmtPlanByBatchId(List<RzPmtPlan> rzPmtPlanList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPmtPlan getRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery);
	
	/**
	 * 查询所有
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public List<RzPmtPlan> getRzPmtPlanAll(RzPmtPlanQuery rzPmtPlanQuery);
	
	/**
	 * 分页查询
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public GridResult<RzPmtPlan> getRzPmtPlanByPage(RzpmtplanVo rzPmtPlanQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public int getRzPmtPlanByPageCount(RzpmtplanVo rzPmtPlanQuery);
	
	public String getMaxVersion(RzpmtplanVo rzPmtPlanQuery);
}