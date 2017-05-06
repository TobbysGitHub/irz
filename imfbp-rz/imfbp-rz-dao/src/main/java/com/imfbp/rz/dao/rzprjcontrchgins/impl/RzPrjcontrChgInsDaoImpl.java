package com.imfbp.rz.dao.rzprjcontrchgins.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchgins.RzPrjcontrChgIns;
import com.imfbp.rz.domain.rzprjcontrchgins.query.RzPrjcontrChgInsQuery;
import com.imfbp.rz.dao.rzprjcontrchgins.RzPrjcontrChgInsDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgInsDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgInsDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgIns
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgIns(RzPrjcontrChgIns rzPrjcontrChgIns){
		this.getSqlSession().insert("rzPrjcontrChgIns.insertRzPrjcontrChgIns", rzPrjcontrChgIns);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgIns(List<RzPrjcontrChgIns> rzPrjcontrChgInsList){
		this.getSqlSession().insert("rzPrjcontrChgIns.insertBatchRzPrjcontrChgIns", rzPrjcontrChgInsList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return this.getSqlSession().delete("rzPrjcontrChgIns.deleteRzPrjcontrChgInsById", rzPrjcontrChgInsQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsByCondition(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return this.getSqlSession().delete("rzPrjcontrChgIns.deleteRzPrjcontrChgInsByCondition", rzPrjcontrChgInsQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgIns.deleteRzPrjcontrChgInsByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgInsById(RzPrjcontrChgIns rzPrjcontrChgIns){
		return this.getSqlSession().update("rzPrjcontrChgIns.updateRzPrjcontrChgInsById", rzPrjcontrChgIns)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsList
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsByBatchId(List<RzPrjcontrChgIns> rzPrjcontrChgInsList){
		return this.getSqlSession().update("rzPrjcontrChgIns.updateRzPrjcontrChgInsByBatchId", rzPrjcontrChgInsList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgInsByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgIns.updateRzPrjcontrChgInsByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgIns getRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgIns.getRzPrjcontrChgInsById",rzPrjcontrChgInsQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgIns> getRzPrjcontrChgInsAll(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgIns.getRzPrjcontrChgInsAll",rzPrjcontrChgInsQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgIns> getRzPrjcontrChgInsByPage(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgIns.getRzPrjcontrChgInsByPage",rzPrjcontrChgInsQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgInsByPageCount(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgIns.getRzPrjcontrChgInsByPageCount",rzPrjcontrChgInsQuery);
	}
}