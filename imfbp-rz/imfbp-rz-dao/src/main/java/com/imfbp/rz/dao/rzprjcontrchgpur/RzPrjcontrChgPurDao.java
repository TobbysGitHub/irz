package com.imfbp.rz.dao.rzprjcontrchgpur;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchgpur.RzPrjcontrChgPur;
import com.imfbp.rz.domain.rzprjcontrchgpur.query.RzPrjcontrChgPurQuery;

public interface RzPrjcontrChgPurDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgPur
	 * @return
	 */
	public void insertRzPrjcontrChgPur(RzPrjcontrChgPur rzPrjcontrChgPur);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgPur>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgPur(List<RzPrjcontrChgPur> rzPrjcontrChgPurList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgPurById(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgPurByCondition(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgPurByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgPurById(RzPrjcontrChgPur rzPrjcontrChgPur);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgPurByBatchId(List<RzPrjcontrChgPur> rzPrjcontrChgPurList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgPurByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public RzPrjcontrChgPur getRzPrjcontrChgPurById(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public List<RzPrjcontrChgPur> getRzPrjcontrChgPurAll(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public List<RzPrjcontrChgPur> getRzPrjcontrChgPurByPage(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgPurByPageCount(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
}