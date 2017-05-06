package com.imfbp.rz.service.rzplanchange;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.rzplanchange.RzPlanChange;
import com.imfbp.rz.domain.rzplanchange.query.RzPlanChangeQuery;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;

public interface RzPlanChangeService{

	/**
	 * 添加
	 * @param rzPlanChange
	 * @return
	 * @throws BusinessException 
	 */
	public void insertRzPlanChange(RzPlanChange rzPlanChange) throws BusinessException;
	
	/**
	 * 批量添加
	 * @param List<rzPlanChange>
	 * @return
	 */
	public void insertBatchRzPlanChange(List<RzPlanChange> rzPlanChangeList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean deleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public Result deleteRzPlanChangeByBatchId(RzPlanChangeQuery rzPlanChangeQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean logicDeleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public Result logicDeleteRzPlanChangeByBatchId(RzPlanChangeQuery rzPlanChangeQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPlanChange
	 */
	public Result insertOrUpdate(RzPlanChange rzPlanChange);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPlanChangeById(RzPlanChange rzPlanChange);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPlanChangeByCondition(RzPlanChangeQuery record,RzPlanChangeQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public Result updateRzPlanChangeByBatchId(List<RzPlanChange> rzPlanChangeList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPlanChange getRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 查询所有
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public List<RzPlanChange> getRzPlanChangeAll(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 分页查询
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public GridResult<RzPlanChange> getRzPlanChangeByPage(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public int getRzPlanChangeByPageCount(RzPlanChangeQuery rzPlanChangeQuery);
	
	public List<RzPmtPlanLease> getPmtPlanLease(RzPlanChange rzPlanChange);
	
	public List<RzPmtPlanCf>  getPmtPlanCf(List<RzPmtPlanLease> RzPmtPlanLeaseList,RzPlanChange rzPlanChange);
	
}