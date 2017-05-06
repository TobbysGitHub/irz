package com.imfbp.rz.dao.rzpricecalrent;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzpricecalrent.RzPricecalRent;
import com.imfbp.rz.domain.rzpricecalrent.query.RzPricecalRentQuery;

public interface RzPricecalRentDao{

	/**
	 * 添加
	 * @param rzPricecalRent
	 * @return
	 */
	public void insertRzPricecalRent(RzPricecalRent rzPricecalRent);
	
	/**
	 * 批量添加
	 * @param List<rzPricecalRent>
	 * @return
	 */
	public void insertBatchRzPricecalRent(List<RzPricecalRent> rzPricecalRentList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public boolean deleteRzPricecalRentById(RzPricecalRentQuery rzPricecalRentQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public boolean deleteRzPricecalRentByCondition(RzPricecalRentQuery rzPricecalRentQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public boolean deleteRzPricecalRentByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public boolean updateRzPricecalRentById(RzPricecalRent rzPricecalRent);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalRentByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public RzPricecalRent getRzPricecalRentById(RzPricecalRentQuery rzPricecalRentQuery);
	
	/**
	 * 查询所有
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public List<RzPricecalRent> getRzPricecalRentAll(RzPricecalRentQuery rzPricecalRentQuery);
	
	/**
	 * 分页查询
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public List<RzPricecalRent> getRzPricecalRentByPage(RzPricecalRentQuery rzPricecalRentQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalRentQuery
	 * @return
	 */
	public Integer getRzPricecalRentByPageCount(RzPricecalRentQuery rzPricecalRentQuery);
	
}