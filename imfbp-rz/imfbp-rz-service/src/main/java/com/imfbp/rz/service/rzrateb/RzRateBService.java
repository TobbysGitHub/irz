package com.imfbp.rz.service.rzrateb;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;

public interface RzRateBService{

	/**
	 * 添加
	 * @param rzRateB
	 * @return
	 */
	public void insertRzRateB(RzRateB rzRateB);
	
	/**
	 * 批量添加
	 * @param List<rzRateB>
	 * @return
	 */
	public void insertBatchRzRateB(List<RzRateB> rzRateBList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzRateBById(RzRateBQuery rzRateBQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	public boolean deleteRzRateBByCondition(RzRateBQuery rzRateBQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	public Result deleteRzRateBByBatchId(RzRateBQuery rzRateBQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzRateB
	 */
	public Result insertOrUpdate(RzRateB rzRateB);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzRateBById(RzRateB rzRateB);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRateBByCondition(RzRateBQuery record,RzRateBQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzRateB getRzRateBById(RzRateBQuery rzRateBQuery);
	
	/**
	 * 查询所有
	 * @param rzRateBQuery
	 * @return
	 */
	public List<RzRateB> getRzRateBAll(RzRateBQuery rzRateBQuery);
	
	/**
	 * 分页查询
	 * @param rzRateBQuery
	 * @return
	 */
	public GridResult<RzRateB> getRzRateBByPage(RzRateBQuery rzRateBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRateBQuery
	 * @return
	 */
	public int getRzRateBByPageCount(RzRateBQuery rzRateBQuery);
	
	
}