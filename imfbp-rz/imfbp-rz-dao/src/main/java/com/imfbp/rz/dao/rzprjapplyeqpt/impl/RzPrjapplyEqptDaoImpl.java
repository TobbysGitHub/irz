package com.imfbp.rz.dao.rzprjapplyeqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;
import com.imfbp.rz.dao.rzprjapplyeqpt.RzPrjapplyEqptDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjapplyEqptDaoImpl extends SqlSessionDaoSupport implements RzPrjapplyEqptDao{

	/**
	 * 添加
	 * @param rzPrjapplyEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjapplyEqpt(RzPrjapplyEqpt rzPrjapplyEqpt){
		this.getSqlSession().insert("rzPrjapplyEqpt.insertRzPrjapplyEqpt", rzPrjapplyEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjapplyEqpt>
	 * @return
	 */
	public void insertBatchRzPrjapplyEqpt(List<RzPrjapplyEqpt> rzPrjapplyEqptList){
		this.getSqlSession().insert("rzPrjapplyEqpt.insertBatchRzPrjapplyEqpt", rzPrjapplyEqptList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return this.getSqlSession().delete("rzPrjapplyEqpt.deleteRzPrjapplyEqptById", rzPrjapplyEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyEqptByCondition(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return this.getSqlSession().delete("rzPrjapplyEqpt.deleteRzPrjapplyEqptByCondition", rzPrjapplyEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjapplyEqpt.deleteRzPrjapplyEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjapplyEqptById(RzPrjapplyEqpt rzPrjapplyEqpt){
		return this.getSqlSession().update("rzPrjapplyEqpt.updateRzPrjapplyEqptById", rzPrjapplyEqpt)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjapplyEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjapplyEqpt.updateRzPrjapplyEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public RzPrjapplyEqpt getRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return this.getSqlSession().selectOne("rzPrjapplyEqpt.getRzPrjapplyEqptById",rzPrjapplyEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjapplyEqpt> getRzPrjapplyEqptAll(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return this.getSqlSession().selectList("rzPrjapplyEqpt.getRzPrjapplyEqptAll",rzPrjapplyEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjapplyEqpt> getRzPrjapplyEqptByPage(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return this.getSqlSession().selectList("rzPrjapplyEqpt.getRzPrjapplyEqptByPage",rzPrjapplyEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjapplyEqptByPageCount(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return this.getSqlSession().selectOne("rzPrjapplyEqpt.getRzPrjapplyEqptByPageCount",rzPrjapplyEqptQuery);
	}
}