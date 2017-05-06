package com.imfbp.rz.dao.rzprjcontrchginseqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchginseqpt.RzPrjcontrChgInsEqpt;
import com.imfbp.rz.domain.rzprjcontrchginseqpt.query.RzPrjcontrChgInsEqptQuery;

public interface RzPrjcontrChgInsEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgInsEqpt
	 * @return
	 */
	public void insertRzPrjcontrChgInsEqpt(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgInsEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgInsEqpt(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsEqptByCondition(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsEqptByBatchId(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public RzPrjcontrChgInsEqpt getRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public List<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptAll(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public List<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptByPage(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgInsEqptByPageCount(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery);
	
}