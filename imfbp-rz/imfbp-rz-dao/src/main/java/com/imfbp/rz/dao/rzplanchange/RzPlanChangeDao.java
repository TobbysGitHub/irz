package com.imfbp.rz.dao.rzplanchange;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzplanchange.RzPlanChange;
import com.imfbp.rz.domain.rzplanchange.query.RzPlanChangeQuery;

public interface RzPlanChangeDao{

	/**
	 * 添加
	 * @param rzPlanChange
	 * @return
	 */
	public void insertRzPlanChange(RzPlanChange rzPlanChange);
	
	/**
	 * 批量添加
	 * @param List<rzPlanChange>
	 * @return
	 */
	public void insertBatchRzPlanChange(List<RzPlanChange> rzPlanChangeList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean deleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean deleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean deleteRzPlanChangeByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean logicDeleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean logicDeleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean logicDeleteRzPlanChangeByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean updateRzPlanChangeById(RzPlanChange rzPlanChange);
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public boolean updateRzPlanChangeByBatchId(List<RzPlanChange> rzPlanChangeList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPlanChangeByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public RzPlanChange getRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 查询所有
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public List<RzPlanChange> getRzPlanChangeAll(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 分页查询
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public List<RzPlanChange> getRzPlanChangeByPage(RzPlanChangeQuery rzPlanChangeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public Integer getRzPlanChangeByPageCount(RzPlanChangeQuery rzPlanChangeQuery);
	
}