package com.imfbp.rz.service.rzpricecallease;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;

public interface RzPricecalLeaseService{

	/**
	 * 添加
	 * @param rzPricecalLease
	 * @return
	 */
	public void insertRzPricecalLease(RzPricecalLease rzPricecalLease);
	
	/**
	 * 批量添加
	 * @param List<rzPricecalLease>
	 * @return
	 */
	public void insertBatchRzPricecalLease(List<RzPricecalLease> rzPricecalLeaseList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPricecalLeaseById(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public boolean deleteRzPricecalLeaseByCondition(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public Result deleteRzPricecalLeaseByBatchId(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPricecalLease
	 */
	public Result insertOrUpdate(RzPricecalLease rzPricecalLease);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPricecalLeaseById(RzPricecalLease rzPricecalLease);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalLeaseByCondition(RzPricecalLeaseQuery record,RzPricecalLeaseQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPricecalLease getRzPricecalLeaseById(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	/**
	 * 查询所有
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public List<RzPricecalLease> getRzPricecalLeaseAll(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	/**
	 * 分页查询
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public GridResult<RzPricecalLease> getRzPricecalLeaseByPage(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public int getRzPricecalLeaseByPageCount(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	public Result updateByBatchId(List<RzPricecalLease> list);
}