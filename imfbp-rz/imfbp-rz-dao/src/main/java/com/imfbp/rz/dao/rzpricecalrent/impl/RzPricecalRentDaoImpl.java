package com.imfbp.rz.dao.rzpricecalrent.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzpricecalrent.RzPricecalRent;
import com.imfbp.rz.domain.rzpricecalrent.query.RzPricecalRentQuery;
import com.imfbp.rz.dao.rzpricecalrent.RzPricecalRentDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPricecalRentDaoImpl extends SqlSessionDaoSupport implements RzPricecalRentDao{

	/**
	 * 添加
	 * @param rzPricecalRent
	 * @return
	 */
	@Override
	public void insertRzPricecalRent(RzPricecalRent rzPricecalRent){
		this.getSqlSession().insert("rzPricecalRent.insertRzPricecalRent", rzPricecalRent);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecalRent>
	 * @return
	 */
	public void insertBatchRzPricecalRent(List<RzPricecalRent> rzPricecalRentList){
		this.getSqlSession().insert("rzPricecalRent.insertBatchRzPricecalRent", rzPricecalRentList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPricecalRentQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalRentById(RzPricecalRentQuery rzPricecalRentQuery){
		return this.getSqlSession().delete("rzPricecalRent.deleteRzPricecalRentById", rzPricecalRentQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalRentQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalRentByCondition(RzPricecalRentQuery rzPricecalRentQuery){
		return this.getSqlSession().delete("rzPricecalRent.deleteRzPricecalRentById", rzPricecalRentQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalRentByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPricecalRent.deleteRzPricecalRentByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPricecalRentQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPricecalRentById(RzPricecalRent rzPricecalRent){
		return this.getSqlSession().update("rzPricecalRent.updateRzPricecalRentById", rzPricecalRent)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalRentByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPricecalRent.updateRzPricecalRentByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalRentQuery
	 * @return
	 */
	@Override
	public RzPricecalRent getRzPricecalRentById(RzPricecalRentQuery rzPricecalRentQuery){
		return this.getSqlSession().selectOne("rzPricecalRent.getRzPricecalRentById",rzPricecalRentQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalRentQuery
	 * @return
	 */
	@Override
	public List<RzPricecalRent> getRzPricecalRentAll(RzPricecalRentQuery rzPricecalRentQuery){
		return this.getSqlSession().selectList("rzPricecalRent.getRzPricecalRentAll",rzPricecalRentQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPricecalRent> getRzPricecalRentByPage(RzPricecalRentQuery rzPricecalRentQuery){
		return this.getSqlSession().selectList("rzPricecalRent.getRzPricecalRentByPage",rzPricecalRentQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPricecalRentByPageCount(RzPricecalRentQuery rzPricecalRentQuery){
		return this.getSqlSession().selectOne("rzPricecalRent.getRzPricecalRentByPageCount",rzPricecalRentQuery);
	}
}