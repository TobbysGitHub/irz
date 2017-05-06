package com.imfbp.rz.service.rzpmtplanlease;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;

public interface RzPmtPlanLeaseService{

	/**
	 * 添加
	 * @param rzPmtPlanLease
	 * @return
	 */
	public void insertRzPmtPlanLease(RzPmtPlanLease rzPmtPlanLease);
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlanLease>
	 * @return
	 */
	public void insertBatchRzPmtPlanLease(List<RzPmtPlanLease> rzPmtPlanLeaseList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public boolean deleteRzPmtPlanLeaseByCondition(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public Result deleteRzPmtPlanLeaseByBatchId(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	//往台账表插入数据
	public void insertToTally(RzPmtPlanLease rzPmtPlanLease);
	
	
	/**
	 * 添加或修改
	 * @param rzPmtPlanLease
	 */
	public Result insertOrUpdate(RzPmtPlanLease rzPmtPlanLease);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPmtPlanLeaseById(RzPmtPlanLease rzPmtPlanLease);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPmtPlanLeaseByCondition(RzPmtPlanLeaseQuery record,RzPmtPlanLeaseQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public Result updateRzPmtPlanLeaseByBatchId(List<RzPmtPlanLease> rzPmtPlanLeaseList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPmtPlanLease getRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 查询所有
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public List<RzPmtPlanLease> getRzPmtPlanLeaseAll(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	public List<RzPmtPlanLease> getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 分页查询
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public GridResult<RzPmtPlanLease> getRzPmtPlanLeaseByPage(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public int getRzPmtPlanLeaseByPageCount(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	public List<RzPmtPlanLease> getAllMaxVerGroupByrzPmtPlan();
	
}