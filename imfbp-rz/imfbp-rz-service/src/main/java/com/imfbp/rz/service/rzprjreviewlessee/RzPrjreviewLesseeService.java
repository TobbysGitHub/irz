package com.imfbp.rz.service.rzprjreviewlessee;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewlessee.query.RzPrjreviewLesseeQuery;

public interface RzPrjreviewLesseeService{

	/**
	 * 添加
	 * @param rzPrjreviewLessee
	 * @return
	 */
	public void insertRzPrjreviewLessee(RzPrjreviewLessee rzPrjreviewLessee);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewLessee>
	 * @return
	 */
	public void insertBatchRzPrjreviewLessee(List<RzPrjreviewLessee> rzPrjreviewLesseeList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjreviewLesseeById(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewLesseeByCondition(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public Result deleteRzPrjreviewLesseeByBatchId(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewLessee
	 */
	public Result insertOrUpdate(RzPrjreviewLessee rzPrjreviewLessee);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjreviewLesseeById(RzPrjreviewLessee rzPrjreviewLessee);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewLesseeByCondition(RzPrjreviewLesseeQuery record,RzPrjreviewLesseeQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjreviewLessee getRzPrjreviewLesseeById(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public List<RzPrjreviewLessee> getRzPrjreviewLesseeAll(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public GridResult<RzPrjreviewLessee> getRzPrjreviewLesseeByPage(String tenantId,RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public int getRzPrjreviewLesseeByPageCount(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	
}