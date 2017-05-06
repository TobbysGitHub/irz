package com.imfbp.rz.dao.rzprjcontreqptmngr.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontreqptmngr.RzPrjcontrEqptmngr;
import com.imfbp.rz.domain.rzprjcontreqptmngr.query.RzPrjcontrEqptmngrQuery;
import com.imfbp.rz.dao.rzprjcontreqptmngr.RzPrjcontrEqptmngrDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrEqptmngrDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrEqptmngrDao{

	/**
	 * 添加
	 * @param rzPrjcontrEqptmngr
	 * @return
	 */
	@Override
	public void insertRzPrjcontrEqptmngr(RzPrjcontrEqptmngr rzPrjcontrEqptmngr){
		this.getSqlSession().insert("rzPrjcontrEqptmngr.insertRzPrjcontrEqptmngr", rzPrjcontrEqptmngr);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrEqptmngr>
	 * @return
	 */
	public void insertBatchRzPrjcontrEqptmngr(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList){
		this.getSqlSession().insert("rzPrjcontrEqptmngr.insertBatchRzPrjcontrEqptmngr", rzPrjcontrEqptmngrList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().delete("rzPrjcontrEqptmngr.deleteRzPrjcontrEqptmngrById", rzPrjcontrEqptmngrQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().delete("rzPrjcontrEqptmngr.deleteRzPrjcontrEqptmngrByCondition", rzPrjcontrEqptmngrQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptmngrByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrEqptmngr.deleteRzPrjcontrEqptmngrByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().update("rzPrjcontrEqptmngr.logicDeleteRzPrjcontrEqptmngrById", rzPrjcontrEqptmngrQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().update("rzPrjcontrEqptmngr.logicDeleteRzPrjcontrEqptmngrByCondition", rzPrjcontrEqptmngrQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrEqptmngrByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrEqptmngr.logicDeleteRzPrjcontrEqptmngrByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrEqptmngrById(RzPrjcontrEqptmngr rzPrjcontrEqptmngr){
		return this.getSqlSession().update("rzPrjcontrEqptmngr.updateRzPrjcontrEqptmngrById", rzPrjcontrEqptmngr)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrEqptmngrList
	 * @return
	 */
	public boolean updateRzPrjcontrEqptmngrByBatchId(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList){
		return this.getSqlSession().update("rzPrjcontrEqptmngr.updateRzPrjcontrEqptmngrByBatchId", rzPrjcontrEqptmngrList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrEqptmngrByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrEqptmngr.updateRzPrjcontrEqptmngrByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public RzPrjcontrEqptmngr getRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().selectOne("rzPrjcontrEqptmngr.getRzPrjcontrEqptmngrById",rzPrjcontrEqptmngrQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrAll(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().selectList("rzPrjcontrEqptmngr.getRzPrjcontrEqptmngrAll",rzPrjcontrEqptmngrQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrByPage(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().selectList("rzPrjcontrEqptmngr.getRzPrjcontrEqptmngrByPage",rzPrjcontrEqptmngrQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrEqptmngrByPageCount(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return this.getSqlSession().selectOne("rzPrjcontrEqptmngr.getRzPrjcontrEqptmngrByPageCount",rzPrjcontrEqptmngrQuery);
	}
}