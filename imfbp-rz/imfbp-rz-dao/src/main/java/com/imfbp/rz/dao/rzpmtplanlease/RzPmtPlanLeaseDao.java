package com.imfbp.rz.dao.rzpmtplanlease;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;

public interface RzPmtPlanLeaseDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public boolean deleteRzPmtPlanLeaseByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public boolean updateRzPmtPlanLeaseById(RzPmtPlanLease rzPmtPlanLease);
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public boolean updateRzPmtPlanLeaseByBatchId(List<RzPmtPlanLease> rzPmtPlanLeaseList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPmtPlanLeaseByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public RzPmtPlanLease getRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 查询所有
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public List<RzPmtPlanLease> getRzPmtPlanLeaseAll(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	/**
	 * 查询所有
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public List<RzPmtPlanLease> getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 分页查询
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public List<RzPmtPlanLease> getRzPmtPlanLeaseByPage(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public Integer getRzPmtPlanLeaseByPageCount(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery);
	/**
	* @Title: getAllMaxVerGroupByrzPmtPlan 
	* @Description: 各个最大版本
	* @param @return    
	* @return List<RzAdjIntLease>   
	* @user qinhuimin
	* @date 2017年1月17日上午11:55:21
	* @throws
	 */
	public List<RzPmtPlanLease> getAllMaxVerGroupByrzPmtPlan();
	
}