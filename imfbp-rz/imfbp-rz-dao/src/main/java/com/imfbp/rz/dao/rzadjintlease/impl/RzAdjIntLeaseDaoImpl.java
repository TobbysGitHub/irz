package com.imfbp.rz.dao.rzadjintlease.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzadjintlease.query.RzAdjIntLeaseQuery;
import com.imfbp.rz.dao.rzadjintlease.RzAdjIntLeaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzAdjIntLeaseDaoImpl extends SqlSessionDaoSupport implements RzAdjIntLeaseDao{

	/**
	 * 添加
	 * @param rzAdjIntLease
	 * @return
	 */
	@Override
	public void insertRzAdjIntLease(RzAdjIntLease rzAdjIntLease){
		this.getSqlSession().insert("rzAdjIntLease.insertRzAdjIntLease", rzAdjIntLease);
	}
	
	/**
	 * 批量添加
	 * @param List<rzAdjIntLease>
	 * @return
	 */
	public void insertBatchRzAdjIntLease(List<RzAdjIntLease> rzAdjIntLeaseList){
		this.getSqlSession().insert("rzAdjIntLease.insertBatchRzAdjIntLease", rzAdjIntLeaseList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseById(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return this.getSqlSession().delete("rzAdjIntLease.deleteRzAdjIntLeaseById", rzAdjIntLeaseQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseByCondition(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return this.getSqlSession().delete("rzAdjIntLease.deleteRzAdjIntLeaseByCondition", rzAdjIntLeaseQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzAdjIntLease.deleteRzAdjIntLeaseByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */	
	@Override
	public boolean updateRzAdjIntLeaseById(RzAdjIntLease rzAdjIntLease){
		return this.getSqlSession().update("rzAdjIntLease.updateRzAdjIntLeaseById", rzAdjIntLease)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntLeaseList
	 * @return
	 */
	public boolean updateRzAdjIntLeaseByBatchId(List<RzAdjIntLease> rzAdjIntLeaseList){
		return this.getSqlSession().update("rzAdjIntLease.updateRzAdjIntLeaseByBatchId", rzAdjIntLeaseList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntLeaseByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzAdjIntLease.updateRzAdjIntLeaseByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public RzAdjIntLease getRzAdjIntLeaseById(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return this.getSqlSession().selectOne("rzAdjIntLease.getRzAdjIntLeaseById",rzAdjIntLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public List<RzAdjIntLease> getRzAdjIntLeaseAll(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return this.getSqlSession().selectList("rzAdjIntLease.getRzAdjIntLeaseAll",rzAdjIntLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzAdjIntLease> getRzAdjIntLeaseByPage(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return this.getSqlSession().selectList("rzAdjIntLease.getRzAdjIntLeaseByPage",rzAdjIntLeaseQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzAdjIntLeaseByPageCount(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return this.getSqlSession().selectOne("rzAdjIntLease.getRzAdjIntLeaseByPageCount",rzAdjIntLeaseQuery);
	}
}