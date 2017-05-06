package com.imfbp.rz.service.rzprjcontrins;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrins.RzPrjcontrIns;
import com.imfbp.rz.domain.rzprjcontrins.query.RzPrjcontrInsQuery;

public interface RzPrjcontrInsService{

	/**
	 * 添加
	 * @param rzPrjcontrIns
	 * @return
	 */
	public void insertRzPrjcontrIns(RzPrjcontrIns rzPrjcontrIns);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrIns(List<RzPrjcontrIns> rzPrjcontrInsList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrInsByCondition(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public Result deleteRzPrjcontrInsByBatchId(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrIns
	 */
	public Result insertOrUpdate(RzPrjcontrIns rzPrjcontrIns);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrInsById(RzPrjcontrIns rzPrjcontrIns);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrInsByCondition(RzPrjcontrInsQuery record,RzPrjcontrInsQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public Result updateRzPrjcontrInsByBatchId(List<RzPrjcontrIns> rzPrjcontrInsList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrIns getRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public List<RzPrjcontrIns> getRzPrjcontrInsAll(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public GridResult<RzPrjcontrIns> getRzPrjcontrInsByPage(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public int getRzPrjcontrInsByPageCount(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	
}