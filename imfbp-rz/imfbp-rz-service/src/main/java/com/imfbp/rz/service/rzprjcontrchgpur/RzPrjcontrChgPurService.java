package com.imfbp.rz.service.rzprjcontrchgpur;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchgpur.RzPrjcontrChgPur;
import com.imfbp.rz.domain.rzprjcontrchgpur.query.RzPrjcontrChgPurQuery;

public interface RzPrjcontrChgPurService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgPurByBatchId(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgPur
	 */
	public Result insertOrUpdate(RzPrjcontrChgPur rzPrjcontrChgPur);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgPurById(RzPrjcontrChgPur rzPrjcontrChgPur);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgPurByCondition(RzPrjcontrChgPurQuery record,RzPrjcontrChgPurQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgPurByBatchId(List<RzPrjcontrChgPur> rzPrjcontrChgPurList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzPrjcontrChgPur> getRzPrjcontrChgPurByPage(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public int getRzPrjcontrChgPurByPageCount(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery);
	
	
}