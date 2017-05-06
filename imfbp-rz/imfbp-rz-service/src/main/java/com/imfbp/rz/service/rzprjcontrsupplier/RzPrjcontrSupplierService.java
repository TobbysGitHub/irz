package com.imfbp.rz.service.rzprjcontrsupplier;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjcontrsupplier.query.RzPrjcontrSupplierQuery;

public interface RzPrjcontrSupplierService{

	/**
	 * 添加
	 * @param rzPrjcontrSupplier
	 * @return
	 */
	public void insertRzPrjcontrSupplier(RzPrjcontrSupplier rzPrjcontrSupplier);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrSupplier>
	 * @return
	 */
	public void insertBatchRzPrjcontrSupplier(List<RzPrjcontrSupplier> rzPrjcontrSupplierList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrSupplierById(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrSupplierByCondition(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public Result deleteRzPrjcontrSupplierByBatchId(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrSupplier
	 */
	public Result insertOrUpdate(RzPrjcontrSupplier rzPrjcontrSupplier);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrSupplierById(RzPrjcontrSupplier rzPrjcontrSupplier);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrSupplierByCondition(RzPrjcontrSupplierQuery record,RzPrjcontrSupplierQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public Result updateRzPrjcontrSupplierByBatchId(List<RzPrjcontrSupplier> rzPrjcontrSupplierList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrSupplier getRzPrjcontrSupplierById(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public List<RzPrjcontrSupplier> getRzPrjcontrSupplierAll(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public GridResult<RzPrjcontrSupplier> getRzPrjcontrSupplierByPage(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public int getRzPrjcontrSupplierByPageCount(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	
}