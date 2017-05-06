package com.imfbp.rz.dao.rzpmtplanlease.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.dao.rzpmtplanlease.RzPmtPlanLeaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPmtPlanLeaseDaoImpl extends SqlSessionDaoSupport implements RzPmtPlanLeaseDao{

	/**
	 * 添加
	 * @param rzPmtPlanLease
	 * @return
	 */
	@Override
	public void insertRzPmtPlanLease(RzPmtPlanLease rzPmtPlanLease){
		this.getSqlSession().insert("rzPmtPlanLease.insertRzPmtPlanLease", rzPmtPlanLease);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlanLease>
	 * @return
	 */
	public void insertBatchRzPmtPlanLease(List<RzPmtPlanLease> rzPmtPlanLeaseList){
		this.getSqlSession().insert("rzPmtPlanLease.insertBatchRzPmtPlanLease", rzPmtPlanLeaseList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return this.getSqlSession().delete("rzPmtPlanLease.deleteRzPmtPlanLeaseById", rzPmtPlanLeaseQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanLeaseByCondition(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return this.getSqlSession().delete("rzPmtPlanLease.deleteRzPmtPlanLeaseByCondition", rzPmtPlanLeaseQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanLeaseByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPmtPlanLease.deleteRzPmtPlanLeaseByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPmtPlanLeaseById(RzPmtPlanLease rzPmtPlanLease){
		return this.getSqlSession().update("rzPmtPlanLease.updateRzPmtPlanLeaseById", rzPmtPlanLease)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanLeaseList
	 * @return
	 */
	public boolean updateRzPmtPlanLeaseByBatchId(List<RzPmtPlanLease> rzPmtPlanLeaseList){
		return this.getSqlSession().update("rzPmtPlanLease.updateRzPmtPlanLeaseByBatchId", rzPmtPlanLeaseList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanLeaseByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPmtPlanLease.updateRzPmtPlanLeaseByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public RzPmtPlanLease getRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return this.getSqlSession().selectOne("rzPmtPlanLease.getRzPmtPlanLeaseById",rzPmtPlanLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPmtPlanLease> getRzPmtPlanLeaseAll(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return this.getSqlSession().selectList("rzPmtPlanLease.getRzPmtPlanLeaseAll",rzPmtPlanLeaseQuery);
	}
	@Override
	public List<RzPmtPlanLease> getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return this.getSqlSession().selectList("rzPmtPlanLease.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan",rzPmtPlanLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPmtPlanLease> getRzPmtPlanLeaseByPage(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return this.getSqlSession().selectList("rzPmtPlanLease.getRzPmtPlanLeaseByPage",rzPmtPlanLeaseQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPmtPlanLeaseByPageCount(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return this.getSqlSession().selectOne("rzPmtPlanLease.getRzPmtPlanLeaseByPageCount",rzPmtPlanLeaseQuery);
	}

	@Override
	public List<RzPmtPlanLease> getAllMaxVerGroupByrzPmtPlan() {
		return this.getSqlSession().selectList("rzPmtPlanLease.getAllMaxVerGroupByrzPmtPlan");
	}
}