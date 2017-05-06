package com.imfbp.rz.dao.rzpricecal.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.dao.rzpricecal.RzPricecalDao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPricecalDaoImpl extends SqlSessionDaoSupport implements RzPricecalDao{

	/**
	 * 添加
	 * @param rzPricecal
	 * @return
	 */
	@Override
	public void insertRzPricecal(RzPricecal rzPricecal){
		this.getSqlSession().insert("rzPricecal.insertRzPricecal", rzPricecal);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecal>
	 * @return
	 */
	public void insertBatchRzPricecal(List<RzPricecal> rzPricecalList){
		this.getSqlSession().insert("rzPricecal.insertBatchRzPricecal", rzPricecalList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalById(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().delete("rzPricecal.deleteRzPricecalById", rzPricecalQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().delete("rzPricecal.deleteRzPricecalById", rzPricecalQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPricecal.deleteRzPricecalByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPricecalById(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().update("rzPricecal.logicDeleteRzPricecalById", rzPricecalQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().update("rzPricecal.logicDeleteRzPricecalById", rzPricecalQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPricecalByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPricecal.logicDeleteRzPricecalByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPricecalQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPricecalById(RzPricecal rzPricecal){
		return this.getSqlSession().update("rzPricecal.updateRzPricecalById", rzPricecal)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPricecal.updateRzPricecalByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public RzPricecal getRzPricecalById(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().selectOne("rzPricecal.getRzPricecalById",rzPricecalQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public List<RzPricecal> getRzPricecalAll(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().selectList("rzPricecal.getRzPricecalAll",rzPricecalQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPricecal> getRzPricecalByPage(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().selectList("rzPricecal.getRzPricecalByPage",rzPricecalQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPricecalByPageCount(RzPricecalQuery rzPricecalQuery){
		return this.getSqlSession().selectOne("rzPricecal.getRzPricecalByPageCount",rzPricecalQuery);
	}

	@Override
	public boolean updateByBatchId(Map<String, Object> data) {
		return this.getSqlSession().update("rzPricecal.updateByBatchId",data)>0;
	}
}