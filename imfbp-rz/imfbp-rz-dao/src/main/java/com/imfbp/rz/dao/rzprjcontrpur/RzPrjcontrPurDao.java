package com.imfbp.rz.dao.rzprjcontrpur;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrpur.RzPrjcontrPur;
import com.imfbp.rz.domain.rzprjcontrpur.query.RzPrjcontrPurQuery;

public interface RzPrjcontrPurDao{

	/**
	 * 添加
	 * @param rzPrjcontrPur
	 * @return
	 */
	public void insertRzPrjcontrPur(RzPrjcontrPur rzPrjcontrPur);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrPur>
	 * @return
	 */
	public void insertBatchRzPrjcontrPur(List<RzPrjcontrPur> rzPrjcontrPurList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrPurById(RzPrjcontrPurQuery rzPrjcontrPurQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrPurByCondition(RzPrjcontrPurQuery rzPrjcontrPurQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrPurByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public boolean updateRzPrjcontrPurById(RzPrjcontrPur rzPrjcontrPur);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public boolean updateRzPrjcontrPurByBatchId(List<RzPrjcontrPur> rzPrjcontrPurList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrPurByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public RzPrjcontrPur getRzPrjcontrPurById(RzPrjcontrPurQuery rzPrjcontrPurQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public List<RzPrjcontrPur> getRzPrjcontrPurAll(RzPrjcontrPurQuery rzPrjcontrPurQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public List<RzPrjcontrPur> getRzPrjcontrPurByPage(RzPrjcontrPurQuery rzPrjcontrPurQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public Integer getRzPrjcontrPurByPageCount(RzPrjcontrPurQuery rzPrjcontrPurQuery);
	
}