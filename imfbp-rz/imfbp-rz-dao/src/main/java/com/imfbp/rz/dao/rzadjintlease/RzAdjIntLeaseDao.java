package com.imfbp.rz.dao.rzadjintlease;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzadjintlease.query.RzAdjIntLeaseQuery;

public interface RzAdjIntLeaseDao{

	/**
	 * 添加
	 * @param rzAdjIntLease
	 * @return
	 */
	public void insertRzAdjIntLease(RzAdjIntLease rzAdjIntLease);
	
	/**
	 * 批量添加
	 * @param List<rzAdjIntLease>
	 * @return
	 */
	public void insertBatchRzAdjIntLease(List<RzAdjIntLease> rzAdjIntLeaseList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public boolean deleteRzAdjIntLeaseById(RzAdjIntLeaseQuery rzAdjIntLeaseQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public boolean deleteRzAdjIntLeaseByCondition(RzAdjIntLeaseQuery rzAdjIntLeaseQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public boolean deleteRzAdjIntLeaseByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public boolean updateRzAdjIntLeaseById(RzAdjIntLease rzAdjIntLease);
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public boolean updateRzAdjIntLeaseByBatchId(List<RzAdjIntLease> rzAdjIntLeaseList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzAdjIntLeaseByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public RzAdjIntLease getRzAdjIntLeaseById(RzAdjIntLeaseQuery rzAdjIntLeaseQuery);
	
	/**
	 * 查询所有
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public List<RzAdjIntLease> getRzAdjIntLeaseAll(RzAdjIntLeaseQuery rzAdjIntLeaseQuery);
	
	/**
	 * 分页查询
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public List<RzAdjIntLease> getRzAdjIntLeaseByPage(RzAdjIntLeaseQuery rzAdjIntLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public Integer getRzAdjIntLeaseByPageCount(RzAdjIntLeaseQuery rzAdjIntLeaseQuery);
	
}