package com.imfbp.rz.service.rzprjcontrguarb;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;

public interface RzPrjcontrGuarBService{

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
	public Result insertBatchRzPrjcontrGuarB(List<RzPrjcontrGuarB> rzPrjcontrGuarBList);
	
	/**
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public Result deleteRzPrjcontrGuarBByBatchId(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrGuarB
	 */
	public Result insertOrUpdate(RzPrjcontrGuarB rzPrjcontrGuarB);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrGuarBById(RzPrjcontrGuarB rzPrjcontrGuarB);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrGuarBByCondition(RzPrjcontrGuarBQuery record,RzPrjcontrGuarBQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public Result updateRzPrjcontrGuarBByBatchId(List<RzPrjcontrGuarB> rzPrjcontrGuarBList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzPrjcontrGuarB> getRzPrjcontrGuarBByPage(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public int getRzPrjcontrGuarBByPageCount(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery);

	/**
	 * 批量修改
	 * @author: zhengjm5
	 * @Date: 2016-12-08 18:45:59
	 * @param editList
	 * @return
	 */
	public Result updateByBatch(List<RzPrjcontrGuarB> editList);
}