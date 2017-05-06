package com.imfbp.rz.dao.rzpricecaleqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;

public interface RzPricecalEqptDao{

	/**
	 * 添加
	 * @param rzPricecalEqpt
	 * @return
	 */
	public void insertRzPricecalEqpt(RzPricecalEqpt rzPricecalEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPricecalEqpt>
	 * @return
	 */
	public void insertBatchRzPricecalEqpt(List<RzPricecalEqpt> rzPricecalEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public boolean deleteRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public boolean deleteRzPricecalEqptByCondition(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public boolean deleteRzPricecalEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public boolean updateRzPricecalEqptById(RzPricecalEqpt rzPricecalEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public RzPricecalEqpt getRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public List<RzPricecalEqpt> getRzPricecalEqptAll(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public List<RzPricecalEqpt> getRzPricecalEqptByPage(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public Integer getRzPricecalEqptByPageCount(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	public boolean updateByBatchId(Map<String, Object> data);
}