package com.imfbp.rz.service.rzprjcontrchglessee;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchglessee.RzPrjcontrChgLessee;
import com.imfbp.rz.domain.rzprjcontrchglessee.query.RzPrjcontrChgLesseeQuery;

public interface RzPrjcontrChgLesseeService{

	/**
	 * 添加
	 * @param rzPrjcontrChgLessee
	 * @return
	 */
	public void insertRzPrjcontrChgLessee(RzPrjcontrChgLessee rzPrjcontrChgLessee);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgLessee>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgLessee(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgLesseeByCondition(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgLesseeByBatchId(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgLessee
	 */
	public Result insertOrUpdate(RzPrjcontrChgLessee rzPrjcontrChgLessee);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgLesseeById(RzPrjcontrChgLessee rzPrjcontrChgLessee);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgLesseeByCondition(RzPrjcontrChgLesseeQuery record,RzPrjcontrChgLesseeQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgLesseeByBatchId(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrChgLessee getRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public List<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeAll(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public GridResult<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeByPage(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public int getRzPrjcontrChgLesseeByPageCount(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	
}