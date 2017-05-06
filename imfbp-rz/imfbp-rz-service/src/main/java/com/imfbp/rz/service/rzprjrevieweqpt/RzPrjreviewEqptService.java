package com.imfbp.rz.service.rzprjrevieweqpt;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjrevieweqpt.RzPrjreviewEqpt;
import com.imfbp.rz.domain.rzprjrevieweqpt.query.RzPrjreviewEqptQuery;

public interface RzPrjreviewEqptService{

	/**
	 * 添加
	 * @param rzPrjreviewEqpt
	 * @return
	 */
	public void insertRzPrjreviewEqpt(RzPrjreviewEqpt rzPrjreviewEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewEqpt>
	 * @return
	 */
	public void insertBatchRzPrjreviewEqpt(List<RzPrjreviewEqpt> rzPrjreviewEqptList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewEqptByCondition(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public Result deleteRzPrjreviewEqptByBatchId(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewEqpt
	 */
	public Result insertOrUpdate(RzPrjreviewEqpt rzPrjreviewEqpt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjreviewEqptById(RzPrjreviewEqpt rzPrjreviewEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewEqptByCondition(RzPrjreviewEqptQuery record,RzPrjreviewEqptQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjreviewEqpt getRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public List<RzPrjreviewEqpt> getRzPrjreviewEqptAll(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public GridResult<RzPrjreviewEqpt> getRzPrjreviewEqptByPage(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public int getRzPrjreviewEqptByPageCount(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	
}