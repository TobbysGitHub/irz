package com.imfbp.rz.service.rzprjapplyeqpt;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;

public interface RzPrjapplyEqptService{

	/**
	 * 添加
	 * @param rzPrjapplyEqpt
	 * @return
	 */
	public void insertRzPrjapplyEqpt(RzPrjapplyEqpt rzPrjapplyEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjapplyEqpt>
	 * @return
	 */
	public void insertBatchRzPrjapplyEqpt(List<RzPrjapplyEqpt> rzPrjapplyEqptList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyEqptByCondition(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public Result deleteRzPrjapplyEqptByBatchId(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjapplyEqpt
	 */
	public Result insertOrUpdate(RzPrjapplyEqpt rzPrjapplyEqpt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjapplyEqptById(RzPrjapplyEqpt rzPrjapplyEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjapplyEqptByCondition(RzPrjapplyEqptQuery record,RzPrjapplyEqptQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjapplyEqpt getRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public List<RzPrjapplyEqpt> getRzPrjapplyEqptAll(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public GridResult<RzPrjapplyEqpt> getRzPrjapplyEqptByPage(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public int getRzPrjapplyEqptByPageCount(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	
}