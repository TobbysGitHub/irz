package com.imfbp.rz.dao.rzrateprd;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;

public interface RzRateprdDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
	 * @return
	 */
	public boolean deleteRzRateprdByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzRateprdQuery
	 * @return
	 */
	public boolean updateRzRateprdById(RzRateprd rzRateprd);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRateprdByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzRateprdQuery
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
	public List<RzRateprd> getRzRateprdByPage(RzRateprdQuery rzRateprdQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRateprdQuery
	 * @return
	 */
	public Integer getRzRateprdByPageCount(RzRateprdQuery rzRateprdQuery);
	
}