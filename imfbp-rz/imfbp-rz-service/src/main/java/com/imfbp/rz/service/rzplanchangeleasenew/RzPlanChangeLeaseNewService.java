package com.imfbp.rz.service.rzplanchangeleasenew;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzplanchangeleasenew.RzPlanChangeLeaseNew;
import com.imfbp.rz.domain.rzplanchangeleasenew.query.RzPlanChangeLeaseNewQuery;

public interface RzPlanChangeLeaseNewService{

	/**
	 * 添加
	 * @param rzPlanChangeLeaseNew
	 * @return
	 */
	public void insertRzPlanChangeLeaseNew(RzPlanChangeLeaseNew rzPlanChangeLeaseNew);
	
	/**
	 * 批量添加
	 * @param List<rzPlanChangeLeaseNew>
	 * @return
	 */
	public void insertBatchRzPlanChangeLeaseNew(List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPlanChangeLeaseNewById(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	public boolean deleteRzPlanChangeLeaseNewByCondition(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	public Result deleteRzPlanChangeLeaseNewByBatchId(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPlanChangeLeaseNew
	 */
	public Result insertOrUpdate(RzPlanChangeLeaseNew rzPlanChangeLeaseNew);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseNewById(RzPlanChangeLeaseNew rzPlanChangeLeaseNew);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseNewByCondition(RzPlanChangeLeaseNewQuery record,RzPlanChangeLeaseNewQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	public Result updateRzPlanChangeLeaseNewByBatchId(List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPlanChangeLeaseNew getRzPlanChangeLeaseNewById(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery);
	
	/**
	 * 查询所有
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	public List<RzPlanChangeLeaseNew> getRzPlanChangeLeaseNewAll(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery);
	
	/**
	 * 分页查询
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	public GridResult<RzPlanChangeLeaseNew> getRzPlanChangeLeaseNewByPage(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	public int getRzPlanChangeLeaseNewByPageCount(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery);
	
	
}