package com.imfbp.rz.dao.rzprjcontrchg.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchg.RzPrjcontrChg;
import com.imfbp.rz.domain.rzprjcontrchg.query.RzPrjcontrChgQuery;
import com.imfbp.rz.dao.rzprjcontrchg.RzPrjcontrChgDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgDao{

	/**
	 * 添加
	 * @param rzPrjcontrChg
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChg(RzPrjcontrChg rzPrjcontrChg){
		this.getSqlSession().insert("rzPrjcontrChg.insertRzPrjcontrChg", rzPrjcontrChg);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChg>
	 * @return
	 */
	public void insertBatchRzPrjcontrChg(List<RzPrjcontrChg> rzPrjcontrChgList){
		this.getSqlSession().insert("rzPrjcontrChg.insertBatchRzPrjcontrChg", rzPrjcontrChgList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().delete("rzPrjcontrChg.deleteRzPrjcontrChgById", rzPrjcontrChgQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgByCondition(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().delete("rzPrjcontrChg.deleteRzPrjcontrChgByCondition", rzPrjcontrChgQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChg.deleteRzPrjcontrChgByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().update("rzPrjcontrChg.logicDeleteRzPrjcontrChgById", rzPrjcontrChgQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrChgByCondition(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().update("rzPrjcontrChg.logicDeleteRzPrjcontrChgByCondition", rzPrjcontrChgQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrChgByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChg.logicDeleteRzPrjcontrChgByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgById(RzPrjcontrChg rzPrjcontrChg){
		return this.getSqlSession().update("rzPrjcontrChg.updateRzPrjcontrChgById", rzPrjcontrChg)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgList
	 * @return
	 */
	public boolean updateRzPrjcontrChgByBatchId(List<RzPrjcontrChg> rzPrjcontrChgList){
		return this.getSqlSession().update("rzPrjcontrChg.updateRzPrjcontrChgByBatchId", rzPrjcontrChgList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChg.updateRzPrjcontrChgByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChg getRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChg.getRzPrjcontrChgById",rzPrjcontrChgQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChg> getRzPrjcontrChgAll(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().selectList("rzPrjcontrChg.getRzPrjcontrChgAll",rzPrjcontrChgQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChg> getRzPrjcontrChgByPage(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().selectList("rzPrjcontrChg.getRzPrjcontrChgByPage",rzPrjcontrChgQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgByPageCount(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChg.getRzPrjcontrChgByPageCount",rzPrjcontrChgQuery);
	}
}