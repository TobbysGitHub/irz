package com.imfbp.rz.dao.rzpmtplancf.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;
import com.imfbp.rz.dao.rzpmtplancf.RzPmtPlanCfDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPmtPlanCfDaoImpl extends SqlSessionDaoSupport implements RzPmtPlanCfDao{

	/**
	 * 添加
	 * @param rzPmtPlanCf
	 * @return
	 */
	@Override
	public void insertRzPmtPlanCf(RzPmtPlanCf rzPmtPlanCf){
		this.getSqlSession().insert("rzPmtPlanCf.insertRzPmtPlanCf", rzPmtPlanCf);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlanCf>
	 * @return
	 */
	public void insertBatchRzPmtPlanCf(List<RzPmtPlanCf> rzPmtPlanCfList){
		this.getSqlSession().insert("rzPmtPlanCf.insertBatchRzPmtPlanCf", rzPmtPlanCfList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanCfById(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return this.getSqlSession().delete("rzPmtPlanCf.deleteRzPmtPlanCfById", rzPmtPlanCfQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanCfByCondition(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return this.getSqlSession().delete("rzPmtPlanCf.deleteRzPmtPlanCfByCondition", rzPmtPlanCfQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanCfByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPmtPlanCf.deleteRzPmtPlanCfByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPmtPlanCfQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPmtPlanCfById(RzPmtPlanCf rzPmtPlanCf){
		return this.getSqlSession().update("rzPmtPlanCf.updateRzPmtPlanCfById", rzPmtPlanCf)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanCfList
	 * @return
	 */
	public boolean updateRzPmtPlanCfByBatchId(List<RzPmtPlanCf> rzPmtPlanCfList){
		return this.getSqlSession().update("rzPmtPlanCf.updateRzPmtPlanCfByBatchId", rzPmtPlanCfList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanCfByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPmtPlanCf.updateRzPmtPlanCfByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public RzPmtPlanCf getRzPmtPlanCfById(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return this.getSqlSession().selectOne("rzPmtPlanCf.getRzPmtPlanCfById",rzPmtPlanCfQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public List<RzPmtPlanCf> getRzPmtPlanCfAll(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return this.getSqlSession().selectList("rzPmtPlanCf.getRzPmtPlanCfAll",rzPmtPlanCfQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPmtPlanCf> getRzPmtPlanCfByPage(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return this.getSqlSession().selectList("rzPmtPlanCf.getRzPmtPlanCfByPage",rzPmtPlanCfQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPmtPlanCfByPageCount(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return this.getSqlSession().selectOne("rzPmtPlanCf.getRzPmtPlanCfByPageCount",rzPmtPlanCfQuery);
	}
}