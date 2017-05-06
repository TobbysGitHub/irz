package com.imfbp.rz.dao.rzprjcontrchgsupplier.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchgsupplier.RzPrjcontrChgSupplier;
import com.imfbp.rz.domain.rzprjcontrchgsupplier.query.RzPrjcontrChgSupplierQuery;
import com.imfbp.rz.dao.rzprjcontrchgsupplier.RzPrjcontrChgSupplierDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgSupplierDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgSupplierDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgSupplier
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgSupplier(RzPrjcontrChgSupplier rzPrjcontrChgSupplier){
		this.getSqlSession().insert("rzPrjcontrChgSupplier.insertRzPrjcontrChgSupplier", rzPrjcontrChgSupplier);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgSupplier>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgSupplier(List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList){
		this.getSqlSession().insert("rzPrjcontrChgSupplier.insertBatchRzPrjcontrChgSupplier", rzPrjcontrChgSupplierList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgSupplierById(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return this.getSqlSession().delete("rzPrjcontrChgSupplier.deleteRzPrjcontrChgSupplierById", rzPrjcontrChgSupplierQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgSupplierByCondition(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return this.getSqlSession().delete("rzPrjcontrChgSupplier.deleteRzPrjcontrChgSupplierByCondition", rzPrjcontrChgSupplierQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgSupplierByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgSupplier.deleteRzPrjcontrChgSupplierByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgSupplierById(RzPrjcontrChgSupplier rzPrjcontrChgSupplier){
		return this.getSqlSession().update("rzPrjcontrChgSupplier.updateRzPrjcontrChgSupplierById", rzPrjcontrChgSupplier)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgSupplierList
	 * @return
	 */
	public boolean updateRzPrjcontrChgSupplierByBatchId(List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList){
		return this.getSqlSession().update("rzPrjcontrChgSupplier.updateRzPrjcontrChgSupplierByBatchId", rzPrjcontrChgSupplierList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgSupplierByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgSupplier.updateRzPrjcontrChgSupplierByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgSupplier getRzPrjcontrChgSupplierById(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgSupplier.getRzPrjcontrChgSupplierById",rzPrjcontrChgSupplierQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgSupplier> getRzPrjcontrChgSupplierAll(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgSupplier.getRzPrjcontrChgSupplierAll",rzPrjcontrChgSupplierQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgSupplier> getRzPrjcontrChgSupplierByPage(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgSupplier.getRzPrjcontrChgSupplierByPage",rzPrjcontrChgSupplierQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgSupplierByPageCount(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgSupplier.getRzPrjcontrChgSupplierByPageCount",rzPrjcontrChgSupplierQuery);
	}
}