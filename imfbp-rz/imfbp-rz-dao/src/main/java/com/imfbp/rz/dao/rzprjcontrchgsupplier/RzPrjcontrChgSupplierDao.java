package com.imfbp.rz.dao.rzprjcontrchgsupplier;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchgsupplier.RzPrjcontrChgSupplier;
import com.imfbp.rz.domain.rzprjcontrchgsupplier.query.RzPrjcontrChgSupplierQuery;

public interface RzPrjcontrChgSupplierDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgSupplier
	 * @return
	 */
	public void insertRzPrjcontrChgSupplier(RzPrjcontrChgSupplier rzPrjcontrChgSupplier);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgSupplier>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgSupplier(List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgSupplierById(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgSupplierByCondition(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgSupplierByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgSupplierById(RzPrjcontrChgSupplier rzPrjcontrChgSupplier);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgSupplierByBatchId(List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgSupplierByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public RzPrjcontrChgSupplier getRzPrjcontrChgSupplierById(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public List<RzPrjcontrChgSupplier> getRzPrjcontrChgSupplierAll(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public List<RzPrjcontrChgSupplier> getRzPrjcontrChgSupplierByPage(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgSupplierByPageCount(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery);
	
}