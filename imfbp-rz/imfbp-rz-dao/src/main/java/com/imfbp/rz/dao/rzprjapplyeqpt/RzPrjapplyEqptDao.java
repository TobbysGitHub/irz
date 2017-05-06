package com.imfbp.rz.dao.rzprjapplyeqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;

public interface RzPrjapplyEqptDao{

	/**
	 * 添加
	 * @param rzPrjapplyEqpt
	 * @return
	 */
	public void insertRzPrjapplyEqpt(RzPrjapplyEqpt rzPrjapplyEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjapplyEqpt>
	 * @return
	 */
	public void insertBatchRzPrjapplyEqpt(List<RzPrjapplyEqpt> rzPrjapplyEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyEqptByCondition(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public boolean updateRzPrjapplyEqptById(RzPrjapplyEqpt rzPrjapplyEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjapplyEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public RzPrjapplyEqpt getRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public List<RzPrjapplyEqpt> getRzPrjapplyEqptAll(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public List<RzPrjapplyEqpt> getRzPrjapplyEqptByPage(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	public Integer getRzPrjapplyEqptByPageCount(RzPrjapplyEqptQuery rzPrjapplyEqptQuery);
	
}