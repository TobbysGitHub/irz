package com.imfbp.rz.dao.rzprjcontrsupplier.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjcontrsupplier.query.RzPrjcontrSupplierQuery;
import com.imfbp.rz.dao.rzprjcontrsupplier.RzPrjcontrSupplierDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrSupplierDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrSupplierDao{

	/**
	 * 添加
	 * @param rzPrjcontrSupplier
	 * @return
	 */
	@Override
	public void insertRzPrjcontrSupplier(RzPrjcontrSupplier rzPrjcontrSupplier){
		this.getSqlSession().insert("rzPrjcontrSupplier.insertRzPrjcontrSupplier", rzPrjcontrSupplier);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrSupplier>
	 * @return
	 */
	public void insertBatchRzPrjcontrSupplier(List<RzPrjcontrSupplier> rzPrjcontrSupplierList){
		this.getSqlSession().insert("rzPrjcontrSupplier.insertBatchRzPrjcontrSupplier", rzPrjcontrSupplierList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrSupplierById(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return this.getSqlSession().delete("rzPrjcontrSupplier.deleteRzPrjcontrSupplierById", rzPrjcontrSupplierQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrSupplierByCondition(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return this.getSqlSession().delete("rzPrjcontrSupplier.deleteRzPrjcontrSupplierByCondition", rzPrjcontrSupplierQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrSupplierByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrSupplier.deleteRzPrjcontrSupplierByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrSupplierById(RzPrjcontrSupplier rzPrjcontrSupplier){
		return this.getSqlSession().update("rzPrjcontrSupplier.updateRzPrjcontrSupplierById", rzPrjcontrSupplier)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrSupplierList
	 * @return
	 */
	public boolean updateRzPrjcontrSupplierByBatchId(List<RzPrjcontrSupplier> rzPrjcontrSupplierList){
		return this.getSqlSession().update("rzPrjcontrSupplier.updateRzPrjcontrSupplierByBatchId", rzPrjcontrSupplierList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrSupplierByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrSupplier.updateRzPrjcontrSupplierByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public RzPrjcontrSupplier getRzPrjcontrSupplierById(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return this.getSqlSession().selectOne("rzPrjcontrSupplier.getRzPrjcontrSupplierById",rzPrjcontrSupplierQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrSupplier> getRzPrjcontrSupplierAll(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return this.getSqlSession().selectList("rzPrjcontrSupplier.getRzPrjcontrSupplierAll",rzPrjcontrSupplierQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrSupplier> getRzPrjcontrSupplierByPage(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return this.getSqlSession().selectList("rzPrjcontrSupplier.getRzPrjcontrSupplierByPage",rzPrjcontrSupplierQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrSupplierByPageCount(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return this.getSqlSession().selectOne("rzPrjcontrSupplier.getRzPrjcontrSupplierByPageCount",rzPrjcontrSupplierQuery);
	}
}