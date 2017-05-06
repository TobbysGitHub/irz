package com.imfbp.rz.dao.rzprjcontrinseqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrinseqpt.RzPrjcontrInsEqpt;
import com.imfbp.rz.domain.rzprjcontrinseqpt.query.RzPrjcontrInsEqptQuery;

public interface RzPrjcontrInsEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrInsEqpt
	 * @return
	 */
	public void insertRzPrjcontrInsEqpt(RzPrjcontrInsEqpt rzPrjcontrInsEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrInsEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrInsEqpt(List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrInsEqptById(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrInsEqptByCondition(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrInsEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrInsEqptById(RzPrjcontrInsEqpt rzPrjcontrInsEqpt);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public boolean updateRzPrjcontrInsEqptByBatchId(List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrInsEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public RzPrjcontrInsEqpt getRzPrjcontrInsEqptById(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public List<RzPrjcontrInsEqpt> getRzPrjcontrInsEqptAll(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public List<RzPrjcontrInsEqpt> getRzPrjcontrInsEqptByPage(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public Integer getRzPrjcontrInsEqptByPageCount(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery);
	
}