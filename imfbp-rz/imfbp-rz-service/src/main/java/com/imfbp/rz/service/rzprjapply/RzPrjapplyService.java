package com.imfbp.rz.service.rzprjapply;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;

public interface RzPrjapplyService{

	/**
	 * 添加
	 * @param rzPrjapply
	 * @return
	 */
	public void insertRzPrjapply(RzPrjapply rzPrjapply,String tenantId);
	
	/**
	 * 批量添加
	 * @param List<rzPrjapply>
	 * @return
	 */
	public void insertBatchRzPrjapply(List<RzPrjapply> rzPrjapplyList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public Result deleteRzPrjapplyByBatchId(RzPrjapplyQuery rzPrjapplyQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public Result logicDeleteRzPrjapplyByBatchId(RzPrjapplyQuery rzPrjapplyQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjapply
	 */
	public Result insertOrUpdate(RzPrjapply rzPrjapply,String tenantId);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjapplyById(RzPrjapply rzPrjapply);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjapplyByCondition(RzPrjapplyQuery record,RzPrjapplyQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjapply getRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public List<RzPrjapply> getRzPrjapplyAll(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public GridResult<RzPrjapply> getRzPrjapplyByPage(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public int getRzPrjapplyByPageCount(RzPrjapplyQuery rzPrjapplyQuery);

	
}