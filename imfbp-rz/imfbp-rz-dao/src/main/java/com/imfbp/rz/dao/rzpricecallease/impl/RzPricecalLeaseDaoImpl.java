package com.imfbp.rz.dao.rzpricecallease.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;
import com.imfbp.rz.dao.rzpricecallease.RzPricecalLeaseDao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPricecalLeaseDaoImpl extends SqlSessionDaoSupport implements RzPricecalLeaseDao{

	/**
	 * 添加
	 * @param rzPricecalLease
	 * @return
	 */
	@Override
	public void insertRzPricecalLease(RzPricecalLease rzPricecalLease){
		this.getSqlSession().insert("rzPricecalLease.insertRzPricecalLease", rzPricecalLease);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecalLease>
	 * @return
	 */
	public void insertBatchRzPricecalLease(List<RzPricecalLease> rzPricecalLeaseList){
		this.getSqlSession().insert("rzPricecalLease.insertBatchRzPricecalLease", rzPricecalLeaseList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalLeaseById(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return this.getSqlSession().delete("rzPricecalLease.deleteRzPricecalLeaseById", rzPricecalLeaseQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalLeaseByCondition(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return this.getSqlSession().delete("rzPricecalLease.deleteRzPricecalLeaseById", rzPricecalLeaseQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalLeaseByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPricecalLease.deleteRzPricecalLeaseByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPricecalLeaseQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPricecalLeaseById(RzPricecalLease rzPricecalLease){
		return this.getSqlSession().update("rzPricecalLease.updateRzPricecalLeaseById", rzPricecalLease)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalLeaseByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPricecalLease.updateRzPricecalLeaseByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public RzPricecalLease getRzPricecalLeaseById(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return this.getSqlSession().selectOne("rzPricecalLease.getRzPricecalLeaseById",rzPricecalLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPricecalLease> getRzPricecalLeaseAll(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return this.getSqlSession().selectList("rzPricecalLease.getRzPricecalLeaseAll",rzPricecalLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPricecalLease> getRzPricecalLeaseByPage(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return this.getSqlSession().selectList("rzPricecalLease.getRzPricecalLeaseByPage",rzPricecalLeaseQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPricecalLeaseByPageCount(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return this.getSqlSession().selectOne("rzPricecalLease.getRzPricecalLeaseByPageCount",rzPricecalLeaseQuery);
	}

	@Override
	public boolean updateByBatchId(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("rzPricecalLease.updateByBatchId",data)>0;
	}
}