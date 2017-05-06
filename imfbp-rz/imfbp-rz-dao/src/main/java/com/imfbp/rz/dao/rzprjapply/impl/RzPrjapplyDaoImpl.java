package com.imfbp.rz.dao.rzprjapply.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjapplyDaoImpl extends SqlSessionDaoSupport implements RzPrjapplyDao{

	/**
	 * 添加
	 * @param rzPrjapply
	 * @return
	 */
	@Override
	public void insertRzPrjapply(RzPrjapply rzPrjapply){
		this.getSqlSession().insert("rzPrjapply.insertRzPrjapply", rzPrjapply);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjapply>
	 * @return
	 */
	public void insertBatchRzPrjapply(List<RzPrjapply> rzPrjapplyList){
		this.getSqlSession().insert("rzPrjapply.insertBatchRzPrjapply", rzPrjapplyList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().delete("rzPrjapply.deleteRzPrjapplyById", rzPrjapplyQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().delete("rzPrjapply.deleteRzPrjapplyById", rzPrjapplyQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjapply.deleteRzPrjapplyByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().update("rzPrjapply.logicDeleteRzPrjapplyById", rzPrjapplyQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().update("rzPrjapply.logicDeleteRzPrjapplyById", rzPrjapplyQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjapplyByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjapply.logicDeleteRzPrjapplyByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjapplyQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjapplyById(RzPrjapply rzPrjapply){
		return this.getSqlSession().update("rzPrjapply.updateRzPrjapplyById", rzPrjapply)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjapplyByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjapply.updateRzPrjapplyByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public RzPrjapply getRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().selectOne("rzPrjapply.getRzPrjapplyById",rzPrjapplyQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public List<RzPrjapply> getRzPrjapplyAll(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().selectList("rzPrjapply.getRzPrjapplyAll",rzPrjapplyQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjapply> getRzPrjapplyByPage(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().selectList("rzPrjapply.getRzPrjapplyByPage",rzPrjapplyQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjapplyByPageCount(RzPrjapplyQuery rzPrjapplyQuery){
		return this.getSqlSession().selectOne("rzPrjapply.getRzPrjapplyByPageCount",rzPrjapplyQuery);
	}

	@Override
	public List<RzPrjapply> getRzPrjapplyBatchId(List<String> list) {
		return this.getSqlSession().selectList("rzPrjapply.getRzPrjapplyBatchId",list);
	}
}