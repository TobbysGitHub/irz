package com.imfbp.rz.dao.rzplanchangelease.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzplanchangelease.RzPlanChangeLease;
import com.imfbp.rz.domain.rzplanchangelease.query.RzPlanChangeLeaseQuery;
import com.imfbp.rz.dao.rzplanchangelease.RzPlanChangeLeaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPlanChangeLeaseDaoImpl extends SqlSessionDaoSupport implements RzPlanChangeLeaseDao{

	/**
	 * 添加
	 * @param rzPlanChangeLease
	 * @return
	 */
	@Override
	public void insertRzPlanChangeLease(RzPlanChangeLease rzPlanChangeLease){
		this.getSqlSession().insert("rzPlanChangeLease.insertRzPlanChangeLease", rzPlanChangeLease);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPlanChangeLease>
	 * @return
	 */
	public void insertBatchRzPlanChangeLease(List<RzPlanChangeLease> rzPlanChangeLeaseList){
		this.getSqlSession().insert("rzPlanChangeLease.insertBatchRzPlanChangeLease", rzPlanChangeLeaseList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseById(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return this.getSqlSession().delete("rzPlanChangeLease.deleteRzPlanChangeLeaseById", rzPlanChangeLeaseQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseByCondition(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return this.getSqlSession().delete("rzPlanChangeLease.deleteRzPlanChangeLeaseByCondition", rzPlanChangeLeaseQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPlanChangeLease.deleteRzPlanChangeLeaseByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPlanChangeLeaseById(RzPlanChangeLease rzPlanChangeLease){
		return this.getSqlSession().update("rzPlanChangeLease.updateRzPlanChangeLeaseById", rzPlanChangeLease)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeLeaseList
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseByBatchId(List<RzPlanChangeLease> rzPlanChangeLeaseList){
		return this.getSqlSession().update("rzPlanChangeLease.updateRzPlanChangeLeaseByBatchId", rzPlanChangeLeaseList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeLeaseByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPlanChangeLease.updateRzPlanChangeLeaseByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public RzPlanChangeLease getRzPlanChangeLeaseById(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return this.getSqlSession().selectOne("rzPlanChangeLease.getRzPlanChangeLeaseById",rzPlanChangeLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPlanChangeLease> getRzPlanChangeLeaseAll(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return this.getSqlSession().selectList("rzPlanChangeLease.getRzPlanChangeLeaseAll",rzPlanChangeLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPlanChangeLease> getRzPlanChangeLeaseByPage(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return this.getSqlSession().selectList("rzPlanChangeLease.getRzPlanChangeLeaseByPage",rzPlanChangeLeaseQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPlanChangeLeaseByPageCount(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return this.getSqlSession().selectOne("rzPlanChangeLease.getRzPlanChangeLeaseByPageCount",rzPlanChangeLeaseQuery);
	}
}