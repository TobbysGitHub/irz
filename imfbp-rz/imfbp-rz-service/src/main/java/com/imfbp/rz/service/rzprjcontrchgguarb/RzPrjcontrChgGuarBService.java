package com.imfbp.rz.service.rzprjcontrchgguarb;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchgguarb.RzPrjcontrChgGuarB;
import com.imfbp.rz.domain.rzprjcontrchgguarb.query.RzPrjcontrChgGuarBQuery;

public interface RzPrjcontrChgGuarBService{

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
	public Result insertBatchRzPrjcontrChgGuarB(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList);
	
	/**
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgGuarBByBatchId(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgGuarB
	 */
	public Result insertOrUpdate(RzPrjcontrChgGuarB rzPrjcontrChgGuarB);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarBById(RzPrjcontrChgGuarB rzPrjcontrChgGuarB);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarBByCondition(RzPrjcontrChgGuarBQuery record,RzPrjcontrChgGuarBQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgGuarBByBatchId(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzPrjcontrChgGuarB> getRzPrjcontrChgGuarBByPage(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public int getRzPrjcontrChgGuarBByPageCount(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery);

	/**
	 * 批量修改
	 * @author: zhengjm5
	 * @Date: 2016-12-08 18:45:59
	 * @param editList
	 * @return
	 */
	public Result updateByBatch(List<RzPrjcontrChgGuarB> editList);
}