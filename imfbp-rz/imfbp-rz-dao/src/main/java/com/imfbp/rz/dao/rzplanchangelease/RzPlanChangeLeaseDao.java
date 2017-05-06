package com.imfbp.rz.dao.rzplanchangelease;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzplanchangelease.RzPlanChangeLease;
import com.imfbp.rz.domain.rzplanchangelease.query.RzPlanChangeLeaseQuery;

public interface RzPlanChangeLeaseDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public boolean deleteRzPlanChangeLeaseByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseById(RzPlanChangeLease rzPlanChangeLease);
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseByBatchId(List<RzPlanChangeLease> rzPlanChangeLeaseList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeLeaseQuery
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
	public List<RzPlanChangeLease> getRzPlanChangeLeaseByPage(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public Integer getRzPlanChangeLeaseByPageCount(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery);
	
}