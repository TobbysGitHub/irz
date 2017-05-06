package com.imfbp.rz.dao.rzprjrevieweqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjrevieweqpt.RzPrjreviewEqpt;
import com.imfbp.rz.domain.rzprjrevieweqpt.query.RzPrjreviewEqptQuery;

public interface RzPrjreviewEqptDao{

	/**
	 * 添加
	 * @param rzPrjreviewEqpt
	 * @return
	 */
	public void insertRzPrjreviewEqpt(RzPrjreviewEqpt rzPrjreviewEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewEqpt>
	 * @return
	 */
	public void insertBatchRzPrjreviewEqpt(List<RzPrjreviewEqpt> rzPrjreviewEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewEqptByCondition(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public boolean updateRzPrjreviewEqptById(RzPrjreviewEqpt rzPrjreviewEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public RzPrjreviewEqpt getRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public List<RzPrjreviewEqpt> getRzPrjreviewEqptAll(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public List<RzPrjreviewEqpt> getRzPrjreviewEqptByPage(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	public Integer getRzPrjreviewEqptByPageCount(RzPrjreviewEqptQuery rzPrjreviewEqptQuery);
	
}