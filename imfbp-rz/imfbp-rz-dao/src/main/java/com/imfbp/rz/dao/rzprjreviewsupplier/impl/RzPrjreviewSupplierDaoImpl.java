package com.imfbp.rz.dao.rzprjreviewsupplier.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjreviewsupplier.RzPrjreviewSupplier;
import com.imfbp.rz.domain.rzprjreviewsupplier.query.RzPrjreviewSupplierQuery;
import com.imfbp.rz.dao.rzprjreviewsupplier.RzPrjreviewSupplierDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjreviewSupplierDaoImpl extends SqlSessionDaoSupport implements RzPrjreviewSupplierDao{

	/**
	 * 添加
	 * @param rzPrjreviewSupplier
	 * @return
	 */
	@Override
	public void insertRzPrjreviewSupplier(RzPrjreviewSupplier rzPrjreviewSupplier){
		this.getSqlSession().insert("rzPrjreviewSupplier.insertRzPrjreviewSupplier", rzPrjreviewSupplier);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewSupplier>
	 * @return
	 */
	public void insertBatchRzPrjreviewSupplier(List<RzPrjreviewSupplier> rzPrjreviewSupplierList){
		this.getSqlSession().insert("rzPrjreviewSupplier.insertBatchRzPrjreviewSupplier", rzPrjreviewSupplierList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewSupplierById(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return this.getSqlSession().delete("rzPrjreviewSupplier.deleteRzPrjreviewSupplierById", rzPrjreviewSupplierQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewSupplierByCondition(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return this.getSqlSession().delete("rzPrjreviewSupplier.deleteRzPrjreviewSupplierByCondition", rzPrjreviewSupplierQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewSupplierByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjreviewSupplier.deleteRzPrjreviewSupplierByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjreviewSupplierById(RzPrjreviewSupplier rzPrjreviewSupplier){
		return this.getSqlSession().update("rzPrjreviewSupplier.updateRzPrjreviewSupplierById", rzPrjreviewSupplier)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewSupplierByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjreviewSupplier.updateRzPrjreviewSupplierByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public RzPrjreviewSupplier getRzPrjreviewSupplierById(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return this.getSqlSession().selectOne("rzPrjreviewSupplier.getRzPrjreviewSupplierById",rzPrjreviewSupplierQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewSupplier> getRzPrjreviewSupplierAll(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return this.getSqlSession().selectList("rzPrjreviewSupplier.getRzPrjreviewSupplierAll",rzPrjreviewSupplierQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjreviewSupplier> getRzPrjreviewSupplierByPage(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return this.getSqlSession().selectList("rzPrjreviewSupplier.getRzPrjreviewSupplierByPage",rzPrjreviewSupplierQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjreviewSupplierByPageCount(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return this.getSqlSession().selectOne("rzPrjreviewSupplier.getRzPrjreviewSupplierByPageCount",rzPrjreviewSupplierQuery);
	}
}