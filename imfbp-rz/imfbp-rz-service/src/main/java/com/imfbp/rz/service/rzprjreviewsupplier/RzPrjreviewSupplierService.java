package com.imfbp.rz.service.rzprjreviewsupplier;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjreviewsupplier.RzPrjreviewSupplier;
import com.imfbp.rz.domain.rzprjreviewsupplier.query.RzPrjreviewSupplierQuery;

public interface RzPrjreviewSupplierService{

	/**
	 * 添加
	 * @param rzPrjreviewSupplier
	 * @return
	 */
	public void insertRzPrjreviewSupplier(RzPrjreviewSupplier rzPrjreviewSupplier);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewSupplier>
	 * @return
	 */
	public void insertBatchRzPrjreviewSupplier(List<RzPrjreviewSupplier> rzPrjreviewSupplierList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjreviewSupplierById(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewSupplierByCondition(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public Result deleteRzPrjreviewSupplierByBatchId(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewSupplier
	 */
	public Result insertOrUpdate(RzPrjreviewSupplier rzPrjreviewSupplier);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjreviewSupplierById(RzPrjreviewSupplier rzPrjreviewSupplier);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewSupplierByCondition(RzPrjreviewSupplierQuery record,RzPrjreviewSupplierQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjreviewSupplier getRzPrjreviewSupplierById(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public List<RzPrjreviewSupplier> getRzPrjreviewSupplierAll(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public GridResult<RzPrjreviewSupplier> getRzPrjreviewSupplierByPage(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public int getRzPrjreviewSupplierByPageCount(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	
}