package com.imfbp.rz.dao.rzprjcontreqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontreqpt.RzPrjcontrEqpt;
import com.imfbp.rz.domain.rzprjcontreqpt.query.RzPrjcontrEqptQuery;

public interface RzPrjcontrEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrEqpt
	 * @return
	 */
	public void insertRzPrjcontrEqpt(RzPrjcontrEqpt rzPrjcontrEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrEqpt(List<RzPrjcontrEqpt> rzPrjcontrEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptById(RzPrjcontrEqptQuery rzPrjcontrEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptByCondition(RzPrjcontrEqptQuery rzPrjcontrEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrEqptById(RzPrjcontrEqpt rzPrjcontrEqpt);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrEqptByBatchId(List<RzPrjcontrEqpt> rzPrjcontrEqptList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public RzPrjcontrEqpt getRzPrjcontrEqptById(RzPrjcontrEqptQuery rzPrjcontrEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public List<RzPrjcontrEqpt> getRzPrjcontrEqptAll(RzPrjcontrEqptQuery rzPrjcontrEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public List<RzPrjcontrEqpt> getRzPrjcontrEqptByPage(RzPrjcontrEqptQuery rzPrjcontrEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public Integer getRzPrjcontrEqptByPageCount(RzPrjcontrEqptQuery rzPrjcontrEqptQuery);
	
}