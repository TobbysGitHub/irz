package com.imfbp.rz.service.rzadjintleasenew;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzadjintleasenew.RzAdjIntLeaseNew;
import com.imfbp.rz.domain.rzadjintleasenew.query.RzAdjIntLeaseNewQuery;

public interface RzAdjIntLeaseNewService{

	/**
	 * 添加
	 * @param rzAdjIntLeaseNew
	 * @return
	 */
	public void insertRzAdjIntLeaseNew(RzAdjIntLeaseNew rzAdjIntLeaseNew);
	
	/**
	 * 批量添加
	 * @param List<rzAdjIntLeaseNew>
	 * @return
	 */
	public void insertBatchRzAdjIntLeaseNew(List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzAdjIntLeaseNewById(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	public boolean deleteRzAdjIntLeaseNewByCondition(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	public Result deleteRzAdjIntLeaseNewByBatchId(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzAdjIntLeaseNew
	 */
	public Result insertOrUpdate(RzAdjIntLeaseNew rzAdjIntLeaseNew);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzAdjIntLeaseNewById(RzAdjIntLeaseNew rzAdjIntLeaseNew);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzAdjIntLeaseNewByCondition(RzAdjIntLeaseNewQuery record,RzAdjIntLeaseNewQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	public Result updateRzAdjIntLeaseNewByBatchId(List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzAdjIntLeaseNew getRzAdjIntLeaseNewById(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery);
	
	/**
	 * 查询所有
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	public List<RzAdjIntLeaseNew> getRzAdjIntLeaseNewAll(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery);
	
	/**
	 * 分页查询
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	public GridResult<RzAdjIntLeaseNew> getRzAdjIntLeaseNewByPage(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	public int getRzAdjIntLeaseNewByPageCount(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery);
	
	
}