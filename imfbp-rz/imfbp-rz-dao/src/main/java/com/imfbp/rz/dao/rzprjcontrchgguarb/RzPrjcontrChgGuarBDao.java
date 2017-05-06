package com.imfbp.rz.dao.rzprjcontrchgguarb;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchgguarb.RzPrjcontrChgGuarB;
import com.imfbp.rz.domain.rzprjcontrchgguarb.query.RzPrjcontrChgGuarBQuery;

public interface RzPrjcontrChgGuarBDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgGuarB
	 * @return
	 */
	public void insertRzPrjcontrChgGuarB(RzPrjcontrChgGuarB rzPrjcontrChgGuarB);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgGuarB>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgGuarB(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgGuarBById(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgGuarBByCondition(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgGuarBByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarBById(RzPrjcontrChgGuarB rzPrjcontrChgGuarB);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarBByBatchId(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarBByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public RzPrjcontrChgGuarB getRzPrjcontrChgGuarBById(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public List<RzPrjcontrChgGuarB> getRzPrjcontrChgGuarBAll(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public List<RzPrjcontrChgGuarB> getRzPrjcontrChgGuarBByPage(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgGuarBByPageCount(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);

	/**
	 * 批量修改
	 * @author: zhengjm5
	 * @Date: 2016-12-08 18:48:28
	 * @param editList
	 * @return
	 */
	public boolean updateBatch(List<RzPrjcontrChgGuarB> editList);
	
}