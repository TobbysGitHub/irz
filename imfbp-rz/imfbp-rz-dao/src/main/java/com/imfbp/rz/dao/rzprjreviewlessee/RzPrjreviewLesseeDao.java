package com.imfbp.rz.dao.rzprjreviewlessee;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewlessee.query.RzPrjreviewLesseeQuery;

public interface RzPrjreviewLesseeDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjreviewLesseeQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewLesseeByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public boolean updateRzPrjreviewLesseeById(RzPrjreviewLessee rzPrjreviewLessee);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewLesseeByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewLesseeQuery
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
	public List<RzPrjreviewLessee> getRzPrjreviewLesseeByPage(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	public Integer getRzPrjreviewLesseeByPageCount(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery);
	
}