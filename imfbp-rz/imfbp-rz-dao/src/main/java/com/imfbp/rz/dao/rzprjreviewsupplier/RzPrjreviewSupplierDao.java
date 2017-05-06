package com.imfbp.rz.dao.rzprjreviewsupplier;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjreviewsupplier.RzPrjreviewSupplier;
import com.imfbp.rz.domain.rzprjreviewsupplier.query.RzPrjreviewSupplierQuery;

public interface RzPrjreviewSupplierDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewSupplierByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public boolean updateRzPrjreviewSupplierById(RzPrjreviewSupplier rzPrjreviewSupplier);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewSupplierByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewSupplierQuery
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
	public List<RzPrjreviewSupplier> getRzPrjreviewSupplierByPage(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	public Integer getRzPrjreviewSupplierByPageCount(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery);
	
}