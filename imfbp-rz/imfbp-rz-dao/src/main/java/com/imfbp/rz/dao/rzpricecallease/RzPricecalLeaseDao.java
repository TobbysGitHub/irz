package com.imfbp.rz.dao.rzpricecallease;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;

public interface RzPricecalLeaseDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public boolean deleteRzPricecalLeaseByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public boolean updateRzPricecalLeaseById(RzPricecalLease rzPricecalLease);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalLeaseByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPricecalLeaseQuery
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
	public List<RzPricecalLease> getRzPricecalLeaseByPage(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	public Integer getRzPricecalLeaseByPageCount(RzPricecalLeaseQuery rzPricecalLeaseQuery);
	
	public boolean updateByBatchId(Map<String, Object> data);
}