package com.imfbp.rz.service.rzrateprd;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;

public interface RzRateprdService{

	/**
	 * 添加
	 * @param rzRateprd
	 * @return
	 */
	public void insertRzRateprd(RzRateprd rzRateprd);
	
	/**
	 * 批量添加
	 * @param List<rzRateprd>
	 * @return
	 */
	public void insertBatchRzRateprd(List<RzRateprd> rzRateprdList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzRateprdById(RzRateprdQuery rzRateprdQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
	 * @return
	 */
	public boolean deleteRzRateprdByCondition(RzRateprdQuery rzRateprdQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
	 * @return
	 */
	public Result deleteRzRateprdByBatchId(RzRateprdQuery rzRateprdQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzRateprd
	 */
	public Result insertOrUpdate(RzRateprd rzRateprd);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzRateprdById(RzRateprd rzRateprd);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRateprdByCondition(RzRateprdQuery record,RzRateprdQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzRateprd getRzRateprdById(RzRateprdQuery rzRateprdQuery);
	
	/**
	 * 查询所有
	 * @param rzRateprdQuery
	 * @return
	 */
	
	public List<RzRateprd> getRzRateprdAll(RzRateprdQuery rzRateprdQuery);
	
	
	public List<RzRateprd> getRzRateprdByDays(RzRateprdQuery rzRateprdQuery);
	
	/**
	 * 分页查询
	 * @param rzRateprdQuery
	 * @return
	 */
	public GridResult<RzRateprd> getRzRateprdByPage(RzRateprdQuery rzRateprdQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRateprdQuery
	 * @return
	 */
	public int getRzRateprdByPageCount(RzRateprdQuery rzRateprdQuery);
	
	
}