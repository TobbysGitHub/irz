package com.imfbp.rz.dao.rzrecptpmt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzrecptpmt.RzRecptpmt;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;
import com.imfbp.rz.dao.rzrecptpmt.RzRecptpmtDao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzRecptpmtDaoImpl extends SqlSessionDaoSupport implements RzRecptpmtDao{

	/**
	 * 添加
	 * @param rzRecptpmt
	 * @return
	 */
	@Override
	public void insertRzRecptpmt(RzRecptpmt rzRecptpmt){
		this.getSqlSession().insert("rzRecptpmt.insertRzRecptpmt", rzRecptpmt);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().delete("rzRecptpmt.deleteRzRecptpmtById", rzRecptpmtQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRecptpmtByCondition(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().delete("rzRecptpmt.deleteRzRecptpmtById", rzRecptpmtQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzRecptpmtByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzRecptpmt.deleteRzRecptpmtByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().update("rzRecptpmt.logicDeleteRzRecptpmtById", rzRecptpmtQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRecptpmtByCondition(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().update("rzRecptpmt.logicDeleteRzRecptpmtById", rzRecptpmtQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRecptpmtByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzRecptpmt.logicDeleteRzRecptpmtByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzRecptpmtQuery
	 * @return
	 */	
	@Override
	public boolean updateRzRecptpmtById(RzRecptpmt rzRecptpmt){
		return this.getSqlSession().update("rzRecptpmt.updateRzRecptpmtById", rzRecptpmt)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRecptpmtByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzRecptpmt.updateRzRecptpmtByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public RzRecptpmt getRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().selectOne("rzRecptpmt.getRzRecptpmtById",rzRecptpmtQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public List<RzRecptpmt> getRzRecptpmtAll(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().selectList("rzRecptpmt.getRzRecptpmtAll",rzRecptpmtQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzRecptpmt> getRzRecptpmtByPage(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().selectList("rzRecptpmt.getRzRecptpmtByPage",rzRecptpmtQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzRecptpmtByPageCount(RzRecptpmtQuery rzRecptpmtQuery){
		return this.getSqlSession().selectOne("rzRecptpmt.getRzRecptpmtByPageCount",rzRecptpmtQuery);
	}

	@Override
	public boolean updateByBatchId(Map<String, Object> data) {
		// TODO Auto-generated method stub
	return this.getSqlSession().update("rzRecptpmt.updateByBatchId",data)>0;
	}

	
}