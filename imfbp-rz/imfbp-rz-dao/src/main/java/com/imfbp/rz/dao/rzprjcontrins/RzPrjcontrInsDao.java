package com.imfbp.rz.dao.rzprjcontrins;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrins.RzPrjcontrIns;
import com.imfbp.rz.domain.rzprjcontrins.query.RzPrjcontrInsQuery;

public interface RzPrjcontrInsDao{

	/**
	 * 添加
	 * @param rzPrjcontrIns
	 * @return
	 */
	public void insertRzPrjcontrIns(RzPrjcontrIns rzPrjcontrIns);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrIns(List<RzPrjcontrIns> rzPrjcontrInsList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrInsByCondition(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrInsByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public boolean updateRzPrjcontrInsById(RzPrjcontrIns rzPrjcontrIns);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public boolean updateRzPrjcontrInsByBatchId(List<RzPrjcontrIns> rzPrjcontrInsList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrInsByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public RzPrjcontrIns getRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public List<RzPrjcontrIns> getRzPrjcontrInsAll(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public List<RzPrjcontrIns> getRzPrjcontrInsByPage(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public Integer getRzPrjcontrInsByPageCount(RzPrjcontrInsQuery rzPrjcontrInsQuery);
	
}