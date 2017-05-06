package com.imfbp.rz.dao.rzprjcontrguarb;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;

public interface RzPrjcontrGuarBDao{

	/**
	 * 添加
	 * @param rzPrjcontrGuarB
	 * @return
	 */
	public void insertRzPrjcontrGuarB(RzPrjcontrGuarB rzPrjcontrGuarB);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrGuarB>
	 * @return
	 */
	public void insertBatchRzPrjcontrGuarB(List<RzPrjcontrGuarB> rzPrjcontrGuarBList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrGuarBById(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrGuarBByCondition(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrGuarBByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public boolean updateRzPrjcontrGuarBById(RzPrjcontrGuarB rzPrjcontrGuarB);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public boolean updateRzPrjcontrGuarBByBatchId(List<RzPrjcontrGuarB> rzPrjcontrGuarBList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrGuarBByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public RzPrjcontrGuarB getRzPrjcontrGuarBById(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public List<RzPrjcontrGuarB> getRzPrjcontrGuarBAll(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public List<RzPrjcontrGuarB> getRzPrjcontrGuarBByPage(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public Integer getRzPrjcontrGuarBByPageCount(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);

	/**
	 * 批量修改
	 * @author: zhengjm5
	 * @Date: 2016-12-08 18:48:28
	 * @param editList
	 * @return
	 */
	public boolean updateBatch(List<RzPrjcontrGuarB> editList);
	
}