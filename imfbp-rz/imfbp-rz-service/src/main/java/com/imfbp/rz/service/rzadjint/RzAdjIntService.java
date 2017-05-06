package com.imfbp.rz.service.rzadjint;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjint.query.RzAdjIntQuery;

public interface RzAdjIntService{

	/**
	 * 添加
	 * @param rzAdjInt
	 * @return
	 */
	public void insertRzAdjInt(RzAdjInt rzAdjInt);
	
	/**
	 * 批量添加
	 * @param List<rzAdjInt>
	 * @return
	 */
	public void insertBatchRzAdjInt(List<RzAdjInt> rzAdjIntList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean deleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public Result deleteRzAdjIntByBatchId(RzAdjIntQuery rzAdjIntQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean logicDeleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public Result logicDeleteRzAdjIntByBatchId(RzAdjIntQuery rzAdjIntQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzAdjInt
	 */
	public Result insertOrUpdate(RzAdjInt rzAdjInt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzAdjIntById(RzAdjInt rzAdjInt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzAdjIntByCondition(RzAdjIntQuery record,RzAdjIntQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntQuery
	 * @return
	 */
	public Result updateRzAdjIntByBatchId(List<RzAdjInt> rzAdjIntList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzAdjInt getRzAdjIntById(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 查询所有
	 * @param rzAdjIntQuery
	 * @return
	 */
	public List<RzAdjInt> getRzAdjIntAll(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 分页查询
	 * @param rzAdjIntQuery
	 * @return
	 */
	public GridResult<RzAdjInt> getRzAdjIntByPage(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzAdjIntQuery
	 * @return
	 */
	public int getRzAdjIntByPageCount(RzAdjIntQuery rzAdjIntQuery);
	
	
}