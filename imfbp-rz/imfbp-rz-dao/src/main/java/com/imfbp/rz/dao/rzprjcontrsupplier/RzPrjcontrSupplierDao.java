package com.imfbp.rz.dao.rzprjcontrsupplier;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjcontrsupplier.query.RzPrjcontrSupplierQuery;

public interface RzPrjcontrSupplierDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrSupplierByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public boolean updateRzPrjcontrSupplierById(RzPrjcontrSupplier rzPrjcontrSupplier);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public boolean updateRzPrjcontrSupplierByBatchId(List<RzPrjcontrSupplier> rzPrjcontrSupplierList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrSupplierByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrSupplierQuery
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
	public List<RzPrjcontrSupplier> getRzPrjcontrSupplierByPage(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public Integer getRzPrjcontrSupplierByPageCount(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery);
	
}