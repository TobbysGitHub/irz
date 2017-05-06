package com.imfbp.rz.service.rzprjstate;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.domain.rzprjstate.query.RzPrjStateQuery;

public interface RzPrjStateService{

	/**
	 * 添加
	 * @param rzPrjState
	 * @return
	 */
	public void insertRzPrjState(RzPrjState rzPrjState);
	
	/**
	 * 批量添加
	 * @param List<rzPrjState>
	 * @return
	 */
	public void insertBatchRzPrjState(List<RzPrjState> rzPrjStateList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjStateById(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	public boolean deleteRzPrjStateByCondition(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	public Result deleteRzPrjStateByBatchId(RzPrjStateQuery rzPrjStateQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjState
	 */
	public Result insertOrUpdate(RzPrjState rzPrjState);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjStateById(RzPrjState rzPrjState);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjStateByCondition(RzPrjStateQuery record,RzPrjStateQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjStateQuery
	 * @return
	 */
	public Result updateRzPrjStateByBatchId(List<RzPrjState> rzPrjStateList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjState getRzPrjStateById(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjStateQuery
	 * @return
	 */
	public List<RzPrjState> getRzPrjStateAll(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjStateQuery
	 * @return
	 */
	public GridResult<RzPrjState> getRzPrjStateByPage(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjStateQuery
	 * @return
	 */
	public int getRzPrjStateByPageCount(RzPrjStateQuery rzPrjStateQuery);
	
	
	/******
	 * 
	 * 项目状态更新
	 * 
	 * @param rzPrjState
	 */
	public void insertOrUpdatePrjState(RzPrjState rzPrjState);
	
	
}