package com.imfbp.rz.service.rzplanchangelease;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzplanchangelease.RzPlanChangeLease;
import com.imfbp.rz.domain.rzplanchangelease.query.RzPlanChangeLeaseQuery;

public interface RzPlanChangeLeaseService{

	/**
	 * 添加
	 * @param rzPlanChangeLease
	 * @return
	 */
	public void insertRzPlanChangeLease(RzPlanChangeLease rzPlanChangeLease);
	
	/**
	 * 批量添加
	 * @param List<rzPlanChangeLease>
	 * @return
	 */
	public void insertBatchRzPlanChangeLease(List<RzPlanChangeLease> rzPlanChangeLeaseList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPlanChangeLeaseById(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public boolean deleteRzPlanChangeLeaseByCondition(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public Result deleteRzPlanChangeLeaseByBatchId(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPlanChangeLease
	 */
	public Result insertOrUpdate(RzPlanChangeLease rzPlanChangeLease);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseById(RzPlanChangeLease rzPlanChangeLease);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseByCondition(RzPlanChangeLeaseQuery record,RzPlanChangeLeaseQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public Result updateRzPlanChangeLeaseByBatchId(List<RzPlanChangeLease> rzPlanChangeLeaseList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPlanChangeLease getRzPlanChangeLeaseById(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	/**
	 * 查询所有
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public List<RzPlanChangeLease> getRzPlanChangeLeaseAll(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	/**
	 * 分页查询
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public GridResult<RzPlanChangeLease> getRzPlanChangeLeaseByPage(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public int getRzPlanChangeLeaseByPageCount(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	
}