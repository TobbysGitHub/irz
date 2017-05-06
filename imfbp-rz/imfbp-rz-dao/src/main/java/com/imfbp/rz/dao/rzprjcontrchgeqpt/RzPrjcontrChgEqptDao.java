package com.imfbp.rz.dao.rzprjcontrchgeqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchgeqpt.RzPrjcontrChgEqpt;
import com.imfbp.rz.domain.rzprjcontrchgeqpt.query.RzPrjcontrChgEqptQuery;

public interface RzPrjcontrChgEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgEqpt
	 * @return
	 */
	public void insertRzPrjcontrChgEqpt(RzPrjcontrChgEqpt rzPrjcontrChgEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgEqpt(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgEqptByCondition(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgEqptById(RzPrjcontrChgEqpt rzPrjcontrChgEqpt);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgEqptByBatchId(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public RzPrjcontrChgEqpt getRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public List<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptAll(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public List<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptByPage(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgEqptByPageCount(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery);
	
}