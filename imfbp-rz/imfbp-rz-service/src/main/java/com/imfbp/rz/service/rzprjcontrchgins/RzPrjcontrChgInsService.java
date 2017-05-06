package com.imfbp.rz.service.rzprjcontrchgins;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchgins.RzPrjcontrChgIns;
import com.imfbp.rz.domain.rzprjcontrchgins.query.RzPrjcontrChgInsQuery;

public interface RzPrjcontrChgInsService{

	/**
	 * 添加
	 * @param rzPrjcontrChgIns
	 * @return
	 */
	public void insertRzPrjcontrChgIns(RzPrjcontrChgIns rzPrjcontrChgIns);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgIns(List<RzPrjcontrChgIns> rzPrjcontrChgInsList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsByCondition(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgInsByBatchId(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgIns
	 */
	public Result insertOrUpdate(RzPrjcontrChgIns rzPrjcontrChgIns);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsById(RzPrjcontrChgIns rzPrjcontrChgIns);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsByCondition(RzPrjcontrChgInsQuery record,RzPrjcontrChgInsQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgInsByBatchId(List<RzPrjcontrChgIns> rzPrjcontrChgInsList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrChgIns getRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public List<RzPrjcontrChgIns> getRzPrjcontrChgInsAll(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public GridResult<RzPrjcontrChgIns> getRzPrjcontrChgInsByPage(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public int getRzPrjcontrChgInsByPageCount(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	
}