package com.imfbp.rz.service.rzprjcontrchginseqpt;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchginseqpt.RzPrjcontrChgInsEqpt;
import com.imfbp.rz.domain.rzprjcontrchginseqpt.query.RzPrjcontrChgInsEqptQuery;

public interface RzPrjcontrChgInsEqptService{

	/**
	 * 添加
	 * @param rzPrjcontrChgInsEqpt
	 * @return
	 */
	public void insertRzPrjcontrChgInsEqpt(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgInsEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgInsEqpt(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsEqptByCondition(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgInsEqptByBatchId(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgInsEqpt
	 */
	public Result insertOrUpdate(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsEqptByCondition(RzPrjcontrChgInsEqptQuery record,RzPrjcontrChgInsEqptQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgInsEqptByBatchId(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrChgInsEqpt getRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public List<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptAll(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public GridResult<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptByPage(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public int getRzPrjcontrChgInsEqptByPageCount(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	
}