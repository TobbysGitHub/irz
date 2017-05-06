package com.imfbp.rz.service.rzprjcontrchg;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchg.RzPrjcontrChg;
import com.imfbp.rz.domain.rzprjcontrchg.query.RzPrjcontrChgQuery;

public interface RzPrjcontrChgService{

	/**
	 * 添加
	 * @param rzPrjcontrChg
	 * @return
	 */
	public void insertRzPrjcontrChg(RzPrjcontrChg rzPrjcontrChg);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChg>
	 * @return
	 */
	public void insertBatchRzPrjcontrChg(List<RzPrjcontrChg> rzPrjcontrChgList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgByCondition(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgByBatchId(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrChgByCondition(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public Result logicDeleteRzPrjcontrChgByBatchId(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChg
	 */
	public Result insertOrUpdate(RzPrjcontrChg rzPrjcontrChg);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgById(RzPrjcontrChg rzPrjcontrChg);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgByCondition(RzPrjcontrChgQuery record,RzPrjcontrChgQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgByBatchId(List<RzPrjcontrChg> rzPrjcontrChgList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrChg getRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public List<RzPrjcontrChg> getRzPrjcontrChgAll(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public GridResult<RzPrjcontrChg> getRzPrjcontrChgByPage(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public int getRzPrjcontrChgByPageCount(RzPrjcontrChgQuery rzPrjcontrChgQuery);
	
	
}