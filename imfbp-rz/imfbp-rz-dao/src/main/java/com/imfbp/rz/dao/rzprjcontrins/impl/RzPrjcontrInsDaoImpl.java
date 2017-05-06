package com.imfbp.rz.dao.rzprjcontrins.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrins.RzPrjcontrIns;
import com.imfbp.rz.domain.rzprjcontrins.query.RzPrjcontrInsQuery;
import com.imfbp.rz.dao.rzprjcontrins.RzPrjcontrInsDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrInsDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrInsDao{

	/**
	 * 添加
	 * @param rzPrjcontrIns
	 * @return
	 */
	@Override
	public void insertRzPrjcontrIns(RzPrjcontrIns rzPrjcontrIns){
		this.getSqlSession().insert("rzPrjcontrIns.insertRzPrjcontrIns", rzPrjcontrIns);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrIns(List<RzPrjcontrIns> rzPrjcontrInsList){
		this.getSqlSession().insert("rzPrjcontrIns.insertBatchRzPrjcontrIns", rzPrjcontrInsList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return this.getSqlSession().delete("rzPrjcontrIns.deleteRzPrjcontrInsById", rzPrjcontrInsQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsByCondition(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return this.getSqlSession().delete("rzPrjcontrIns.deleteRzPrjcontrInsByCondition", rzPrjcontrInsQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrIns.deleteRzPrjcontrInsByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrInsQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrInsById(RzPrjcontrIns rzPrjcontrIns){
		return this.getSqlSession().update("rzPrjcontrIns.updateRzPrjcontrInsById", rzPrjcontrIns)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrInsList
	 * @return
	 */
	public boolean updateRzPrjcontrInsByBatchId(List<RzPrjcontrIns> rzPrjcontrInsList){
		return this.getSqlSession().update("rzPrjcontrIns.updateRzPrjcontrInsByBatchId", rzPrjcontrInsList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrInsByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrIns.updateRzPrjcontrInsByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public RzPrjcontrIns getRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return this.getSqlSession().selectOne("rzPrjcontrIns.getRzPrjcontrInsById",rzPrjcontrInsQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrIns> getRzPrjcontrInsAll(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return this.getSqlSession().selectList("rzPrjcontrIns.getRzPrjcontrInsAll",rzPrjcontrInsQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrIns> getRzPrjcontrInsByPage(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return this.getSqlSession().selectList("rzPrjcontrIns.getRzPrjcontrInsByPage",rzPrjcontrInsQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrInsByPageCount(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return this.getSqlSession().selectOne("rzPrjcontrIns.getRzPrjcontrInsByPageCount",rzPrjcontrInsQuery);
	}
}