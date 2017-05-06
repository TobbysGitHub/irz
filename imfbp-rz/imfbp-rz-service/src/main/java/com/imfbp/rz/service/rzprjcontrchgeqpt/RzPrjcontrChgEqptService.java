package com.imfbp.rz.service.rzprjcontrchgeqpt;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchgeqpt.RzPrjcontrChgEqpt;
import com.imfbp.rz.domain.rzprjcontrchgeqpt.query.RzPrjcontrChgEqptQuery;

public interface RzPrjcontrChgEqptService{

	/**
	 * 添加
	 * @param rzPrjcontrChgEqpt
	 * @return
	 */
	public void insertRzPrjcontrChgEqpt(RzPrjcontrChgEqpt rzPrjcontrChgEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgEqpt(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgEqptByCondition(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgEqptByBatchId(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgEqpt
	 */
	public Result insertOrUpdate(RzPrjcontrChgEqpt rzPrjcontrChgEqpt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgEqptById(RzPrjcontrChgEqpt rzPrjcontrChgEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgEqptByCondition(RzPrjcontrChgEqptQuery record,RzPrjcontrChgEqptQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgEqptByBatchId(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrChgEqpt getRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public List<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptAll(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public GridResult<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptByPage(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public int getRzPrjcontrChgEqptByPageCount(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	
}