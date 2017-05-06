package com.imfbp.rz.dao.rzpricecalcf.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.dao.rzpricecalcf.RzPricecalCfDao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPricecalCfDaoImpl extends SqlSessionDaoSupport implements RzPricecalCfDao{

	/**
	 * 添加
	 * @param rzPricecalCf
	 * @return
	 */
	@Override
	public void insertRzPricecalCf(RzPricecalCf rzPricecalCf){
		this.getSqlSession().insert("rzPricecalCf.insertRzPricecalCf", rzPricecalCf);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecalCf>
	 * @return
	 */
	public void insertBatchRzPricecalCf(List<RzPricecalCf> rzPricecalCfList){
		this.getSqlSession().insert("rzPricecalCf.insertBatchRzPricecalCf", rzPricecalCfList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalCfById(RzPricecalCfQuery rzPricecalCfQuery){
		return this.getSqlSession().delete("rzPricecalCf.deleteRzPricecalCfById", rzPricecalCfQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalCfByCondition(RzPricecalCfQuery rzPricecalCfQuery){
		return this.getSqlSession().delete("rzPricecalCf.deleteRzPricecalCfById", rzPricecalCfQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalCfByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPricecalCf.deleteRzPricecalCfByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPricecalCfQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPricecalCfById(RzPricecalCf rzPricecalCf){
		return this.getSqlSession().update("rzPricecalCf.updateRzPricecalCfById", rzPricecalCf)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalCfByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPricecalCf.updateRzPricecalCfByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public RzPricecalCf getRzPricecalCfById(RzPricecalCfQuery rzPricecalCfQuery){
		return this.getSqlSession().selectOne("rzPricecalCf.getRzPricecalCfById",rzPricecalCfQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public List<RzPricecalCf> getRzPricecalCfAll(RzPricecalCfQuery rzPricecalCfQuery){
		return this.getSqlSession().selectList("rzPricecalCf.getRzPricecalCfAll",rzPricecalCfQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPricecalCf> getRzPricecalCfByPage(RzPricecalCfQuery rzPricecalCfQuery){
		return this.getSqlSession().selectList("rzPricecalCf.getRzPricecalCfByPage",rzPricecalCfQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPricecalCfByPageCount(RzPricecalCfQuery rzPricecalCfQuery){
		return this.getSqlSession().selectOne("rzPricecalCf.getRzPricecalCfByPageCount",rzPricecalCfQuery);
	}

	@Override
	public boolean updateByBatchId(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("rzPricecalCf.updateByBatchId",data)>0;
	}

}