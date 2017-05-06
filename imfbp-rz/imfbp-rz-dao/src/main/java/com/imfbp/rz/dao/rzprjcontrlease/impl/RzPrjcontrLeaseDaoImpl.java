package com.imfbp.rz.dao.rzprjcontrlease.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrlease.RzPrjcontrLease;
import com.imfbp.rz.domain.rzprjcontrlease.query.RzPrjcontrLeaseQuery;
import com.imfbp.rz.dao.rzprjcontrlease.RzPrjcontrLeaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrLeaseDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrLeaseDao{

	/**
	 * 添加
	 * @param rzPrjcontrLease
	 * @return
	 */
	@Override
	public void insertRzPrjcontrLease(RzPrjcontrLease rzPrjcontrLease){
		this.getSqlSession().insert("rzPrjcontrLease.insertRzPrjcontrLease", rzPrjcontrLease);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrLease>
	 * @return
	 */
	public void insertBatchRzPrjcontrLease(List<RzPrjcontrLease> rzPrjcontrLeaseList){
		this.getSqlSession().insert("rzPrjcontrLease.insertBatchRzPrjcontrLease", rzPrjcontrLeaseList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLeaseById(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return this.getSqlSession().delete("rzPrjcontrLease.deleteRzPrjcontrLeaseById", rzPrjcontrLeaseQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLeaseByCondition(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return this.getSqlSession().delete("rzPrjcontrLease.deleteRzPrjcontrLeaseByCondition", rzPrjcontrLeaseQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLeaseByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrLease.deleteRzPrjcontrLeaseByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrLeaseById(RzPrjcontrLease rzPrjcontrLease){
		return this.getSqlSession().update("rzPrjcontrLease.updateRzPrjcontrLeaseById", rzPrjcontrLease)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrLeaseList
	 * @return
	 */
	public boolean updateRzPrjcontrLeaseByBatchId(List<RzPrjcontrLease> rzPrjcontrLeaseList){
		return this.getSqlSession().update("rzPrjcontrLease.updateRzPrjcontrLeaseByBatchId", rzPrjcontrLeaseList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrLeaseByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrLease.updateRzPrjcontrLeaseByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public RzPrjcontrLease getRzPrjcontrLeaseById(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return this.getSqlSession().selectOne("rzPrjcontrLease.getRzPrjcontrLeaseById",rzPrjcontrLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrLease> getRzPrjcontrLeaseAll(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return this.getSqlSession().selectList("rzPrjcontrLease.getRzPrjcontrLeaseAll",rzPrjcontrLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrLease> getRzPrjcontrLeaseByPage(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return this.getSqlSession().selectList("rzPrjcontrLease.getRzPrjcontrLeaseByPage",rzPrjcontrLeaseQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrLeaseByPageCount(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return this.getSqlSession().selectOne("rzPrjcontrLease.getRzPrjcontrLeaseByPageCount",rzPrjcontrLeaseQuery);
	}
}