package com.imfbp.rz.service.rzprjreview;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;

public interface RzPrjreviewService{

	/**
	 * 添加
	 * @param rzPrjreview
	 * @return
	 */
	public void insertRzPrjreview(RzPrjreview rzPrjreview);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreview>
	 * @return
	 */
	public void insertBatchRzPrjreview(List<RzPrjreview> rzPrjreviewList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public Result deleteRzPrjreviewByBatchId(RzPrjreviewQuery rzPrjreviewQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public Result logicDeleteRzPrjreviewByBatchId(RzPrjreviewQuery rzPrjreviewQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreview
	 */
	public Result insertOrUpdate(RzPrjreview rzPrjreview);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjreviewById(RzPrjreview rzPrjreview);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewByCondition(RzPrjreviewQuery record,RzPrjreviewQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjreview getRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public List<RzPrjreview> getRzPrjreviewAll(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public GridResult<RzPrjreview> getRzPrjreviewByPage(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public int getRzPrjreviewByPageCount(RzPrjreviewQuery rzPrjreviewQuery);
	
	
}