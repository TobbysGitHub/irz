package com.imfbp.rz.dao.rzpricecal;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;

public interface RzPricecalDao{

	/**
	 * 添加
	 * @param rzPricecal
	 * @return
	 */
	public void insertRzPricecal(RzPricecal rzPricecal);
	
	/**
	 * 批量添加
	 * @param List<rzPricecal>
	 * @return
	 */
	public void insertBatchRzPricecal(List<RzPricecal> rzPricecalList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean deleteRzPricecalById(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean deleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean deleteRzPricecalByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean logicDeleteRzPricecalById(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean logicDeleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean logicDeleteRzPricecalByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean updateRzPricecalById(RzPricecal rzPricecal);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPricecalQuery
	 * @return
	 */
	public RzPricecal getRzPricecalById(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 查询所有
	 * @param rzPricecalQuery
	 * @return
	 */
	public List<RzPricecal> getRzPricecalAll(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 分页查询
	 * @param rzPricecalQuery
	 * @return
	 */
	public List<RzPricecal> getRzPricecalByPage(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalQuery
	 * @return
	 */
	public Integer getRzPricecalByPageCount(RzPricecalQuery rzPricecalQuery);
	
	public boolean updateByBatchId(Map<String, Object> data);
	
}