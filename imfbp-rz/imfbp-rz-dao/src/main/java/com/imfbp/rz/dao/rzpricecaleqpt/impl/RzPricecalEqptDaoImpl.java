package com.imfbp.rz.dao.rzpricecaleqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;
import com.imfbp.rz.dao.rzpricecaleqpt.RzPricecalEqptDao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPricecalEqptDaoImpl extends SqlSessionDaoSupport implements RzPricecalEqptDao{

	/**
	 * 添加
	 * @param rzPricecalEqpt
	 * @return
	 */
	@Override
	public void insertRzPricecalEqpt(RzPricecalEqpt rzPricecalEqpt){
		this.getSqlSession().insert("rzPricecalEqpt.insertRzPricecalEqpt", rzPricecalEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecalEqpt>
	 * @return
	 */
	public void insertBatchRzPricecalEqpt(List<RzPricecalEqpt> list){
		this.getSqlSession().insert("rzPricecalEqpt.insertBatchRzPricecalEqpt", list);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery){
		return this.getSqlSession().delete("rzPricecalEqpt.deleteRzPricecalEqptById", rzPricecalEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalEqptByCondition(RzPricecalEqptQuery rzPricecalEqptQuery){
		return this.getSqlSession().delete("rzPricecalEqpt.deleteRzPricecalEqptById", rzPricecalEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPricecalEqpt.deleteRzPricecalEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPricecalEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPricecalEqptById(RzPricecalEqpt rzPricecalEqpt){
		return this.getSqlSession().update("rzPricecalEqpt.updateRzPricecalEqptById", rzPricecalEqpt)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPricecalEqpt.updateRzPricecalEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public RzPricecalEqpt getRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery){
		return this.getSqlSession().selectOne("rzPricecalEqpt.getRzPricecalEqptById",rzPricecalEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public List<RzPricecalEqpt> getRzPricecalEqptAll(RzPricecalEqptQuery rzPricecalEqptQuery){
		return this.getSqlSession().selectList("rzPricecalEqpt.getRzPricecalEqptAll",rzPricecalEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPricecalEqpt> getRzPricecalEqptByPage(RzPricecalEqptQuery rzPricecalEqptQuery){
		return this.getSqlSession().selectList("rzPricecalEqpt.getRzPricecalEqptByPage",rzPricecalEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPricecalEqptByPageCount(RzPricecalEqptQuery rzPricecalEqptQuery){
		return this.getSqlSession().selectOne("rzPricecalEqpt.getRzPricecalEqptByPageCount",rzPricecalEqptQuery);
	}

	@Override
	public boolean updateByBatchId(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("rzPricecalEqpt.updateByBatchId",data)>0;
	}
}