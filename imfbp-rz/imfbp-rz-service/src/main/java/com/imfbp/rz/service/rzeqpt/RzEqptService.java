package com.imfbp.rz.service.rzeqpt;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;

public interface RzEqptService{

	/**
	 * 添加
	 * @param rzEqpt
	 * @return
	 */
	public void insertRzEqpt(RzEqpt rzEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzEqpt>
	 * @return
	 */
	public void insertBatchRzEqpt(List<RzEqpt> rzEqptList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzEqptById(RzEqptQuery rzEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean deleteRzEqptByCondition(RzEqptQuery rzEqptQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	public Result deleteRzEqptByBatchId(RzEqptQuery rzEqptQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzEqptById(RzEqptQuery rzEqptQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean logicDeleteRzEqptByCondition(RzEqptQuery rzEqptQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	public Result logicDeleteRzEqptByBatchId(RzEqptQuery rzEqptQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzEqpt
	 */
	public Result insertOrUpdate(RzEqpt rzEqpt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzEqptById(RzEqpt rzEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzEqptByCondition(RzEqptQuery record,RzEqptQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzEqptQuery
	 * @return
	 */
	public Result updateRzEqptByBatchId(List<RzEqpt> rzEqptList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzEqpt getRzEqptById(RzEqptQuery rzEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzEqptQuery
	 * @return
	 */
	public List<RzEqpt> getRzEqptAll(RzEqptQuery rzEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzEqptQuery
	 * @return
	 */
	public GridResult<RzEqpt> getRzEqptByPage(RzEqptQuery rzEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzEqptQuery
	 * @return
	 */
	public int getRzEqptByPageCount(RzEqptQuery rzEqptQuery);
	
	
}