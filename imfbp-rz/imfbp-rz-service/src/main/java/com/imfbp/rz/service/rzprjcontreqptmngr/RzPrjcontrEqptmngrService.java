package com.imfbp.rz.service.rzprjcontreqptmngr;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontreqptmngr.RzPrjcontrEqptmngr;
import com.imfbp.rz.domain.rzprjcontreqptmngr.query.RzPrjcontrEqptmngrQuery;

public interface RzPrjcontrEqptmngrService{

	/**
	 * 添加
	 * @param rzPrjcontrEqptmngr
	 * @return
	 */
	public void insertRzPrjcontrEqptmngr(RzPrjcontrEqptmngr rzPrjcontrEqptmngr);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrEqptmngr>
	 * @return
	 */
	public void insertBatchRzPrjcontrEqptmngr(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public Result deleteRzPrjcontrEqptmngrByBatchId(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public Result logicDeleteRzPrjcontrEqptmngrByBatchId(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrEqptmngr
	 */
	public Result insertOrUpdate(RzPrjcontrEqptmngr rzPrjcontrEqptmngr);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrEqptmngrById(RzPrjcontrEqptmngr rzPrjcontrEqptmngr);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery record,RzPrjcontrEqptmngrQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public Result updateRzPrjcontrEqptmngrByBatchId(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrEqptmngr getRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public List<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrAll(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public GridResult<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrByPage(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public int getRzPrjcontrEqptmngrByPageCount(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	
}