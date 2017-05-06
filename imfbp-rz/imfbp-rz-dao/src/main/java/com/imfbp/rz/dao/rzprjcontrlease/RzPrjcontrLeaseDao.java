package com.imfbp.rz.dao.rzprjcontrlease;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrlease.RzPrjcontrLease;
import com.imfbp.rz.domain.rzprjcontrlease.query.RzPrjcontrLeaseQuery;

public interface RzPrjcontrLeaseDao{

	/**
	 * 添加
	 * @param rzPrjcontrLease
	 * @return
	 */
	public void insertRzPrjcontrLease(RzPrjcontrLease rzPrjcontrLease);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrLease>
	 * @return
	 */
	public void insertBatchRzPrjcontrLease(List<RzPrjcontrLease> rzPrjcontrLeaseList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrLeaseById(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrLeaseByCondition(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrLeaseByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public boolean updateRzPrjcontrLeaseById(RzPrjcontrLease rzPrjcontrLease);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public boolean updateRzPrjcontrLeaseByBatchId(List<RzPrjcontrLease> rzPrjcontrLeaseList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrLeaseByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public RzPrjcontrLease getRzPrjcontrLeaseById(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public List<RzPrjcontrLease> getRzPrjcontrLeaseAll(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public List<RzPrjcontrLease> getRzPrjcontrLeaseByPage(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public Integer getRzPrjcontrLeaseByPageCount(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery);
	
}