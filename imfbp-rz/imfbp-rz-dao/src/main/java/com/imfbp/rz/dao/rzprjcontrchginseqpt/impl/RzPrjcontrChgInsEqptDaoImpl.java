package com.imfbp.rz.dao.rzprjcontrchginseqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchginseqpt.RzPrjcontrChgInsEqpt;
import com.imfbp.rz.domain.rzprjcontrchginseqpt.query.RzPrjcontrChgInsEqptQuery;
import com.imfbp.rz.dao.rzprjcontrchginseqpt.RzPrjcontrChgInsEqptDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgInsEqptDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgInsEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgInsEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgInsEqpt(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt){
		this.getSqlSession().insert("rzPrjcontrChgInsEqpt.insertRzPrjcontrChgInsEqpt", rzPrjcontrChgInsEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgInsEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgInsEqpt(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList){
		this.getSqlSession().insert("rzPrjcontrChgInsEqpt.insertBatchRzPrjcontrChgInsEqpt", rzPrjcontrChgInsEqptList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrChgInsEqpt.deleteRzPrjcontrChgInsEqptById", rzPrjcontrChgInsEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsEqptByCondition(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrChgInsEqpt.deleteRzPrjcontrChgInsEqptByCondition", rzPrjcontrChgInsEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgInsEqpt.deleteRzPrjcontrChgInsEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt){
		return this.getSqlSession().update("rzPrjcontrChgInsEqpt.updateRzPrjcontrChgInsEqptById", rzPrjcontrChgInsEqpt)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsEqptList
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsEqptByBatchId(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList){
		return this.getSqlSession().update("rzPrjcontrChgInsEqpt.updateRzPrjcontrChgInsEqptByBatchId", rzPrjcontrChgInsEqptList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgInsEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgInsEqpt.updateRzPrjcontrChgInsEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgInsEqpt getRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgInsEqpt.getRzPrjcontrChgInsEqptById",rzPrjcontrChgInsEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptAll(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgInsEqpt.getRzPrjcontrChgInsEqptAll",rzPrjcontrChgInsEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptByPage(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgInsEqpt.getRzPrjcontrChgInsEqptByPage",rzPrjcontrChgInsEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgInsEqptByPageCount(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgInsEqpt.getRzPrjcontrChgInsEqptByPageCount",rzPrjcontrChgInsEqptQuery);
	}
}