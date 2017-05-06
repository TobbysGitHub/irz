package com.imfbp.rz.dao.rzplanchangeleasenew.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzplanchangeleasenew.RzPlanChangeLeaseNew;
import com.imfbp.rz.domain.rzplanchangeleasenew.query.RzPlanChangeLeaseNewQuery;
import com.imfbp.rz.dao.rzplanchangeleasenew.RzPlanChangeLeaseNewDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPlanChangeLeaseNewDaoImpl extends SqlSessionDaoSupport implements RzPlanChangeLeaseNewDao{

	/**
	 * 添加
	 * @param rzPlanChangeLeaseNew
	 * @return
	 */
	@Override
	public void insertRzPlanChangeLeaseNew(RzPlanChangeLeaseNew rzPlanChangeLeaseNew){
		this.getSqlSession().insert("rzPlanChangeLeaseNew.insertRzPlanChangeLeaseNew", rzPlanChangeLeaseNew);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPlanChangeLeaseNew>
	 * @return
	 */
	public void insertBatchRzPlanChangeLeaseNew(List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList){
		this.getSqlSession().insert("rzPlanChangeLeaseNew.insertBatchRzPlanChangeLeaseNew", rzPlanChangeLeaseNewList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseNewById(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return this.getSqlSession().delete("rzPlanChangeLeaseNew.deleteRzPlanChangeLeaseNewById", rzPlanChangeLeaseNewQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseNewByCondition(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return this.getSqlSession().delete("rzPlanChangeLeaseNew.deleteRzPlanChangeLeaseNewByCondition", rzPlanChangeLeaseNewQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseNewByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPlanChangeLeaseNew.deleteRzPlanChangeLeaseNewByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPlanChangeLeaseNewById(RzPlanChangeLeaseNew rzPlanChangeLeaseNew){
		return this.getSqlSession().update("rzPlanChangeLeaseNew.updateRzPlanChangeLeaseNewById", rzPlanChangeLeaseNew)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeLeaseNewList
	 * @return
	 */
	public boolean updateRzPlanChangeLeaseNewByBatchId(List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList){
		return this.getSqlSession().update("rzPlanChangeLeaseNew.updateRzPlanChangeLeaseNewByBatchId", rzPlanChangeLeaseNewList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeLeaseNewByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPlanChangeLeaseNew.updateRzPlanChangeLeaseNewByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public RzPlanChangeLeaseNew getRzPlanChangeLeaseNewById(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return this.getSqlSession().selectOne("rzPlanChangeLeaseNew.getRzPlanChangeLeaseNewById",rzPlanChangeLeaseNewQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public List<RzPlanChangeLeaseNew> getRzPlanChangeLeaseNewAll(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return this.getSqlSession().selectList("rzPlanChangeLeaseNew.getRzPlanChangeLeaseNewAll",rzPlanChangeLeaseNewQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPlanChangeLeaseNew> getRzPlanChangeLeaseNewByPage(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return this.getSqlSession().selectList("rzPlanChangeLeaseNew.getRzPlanChangeLeaseNewByPage",rzPlanChangeLeaseNewQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPlanChangeLeaseNewByPageCount(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return this.getSqlSession().selectOne("rzPlanChangeLeaseNew.getRzPlanChangeLeaseNewByPageCount",rzPlanChangeLeaseNewQuery);
	}
}