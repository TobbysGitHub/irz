package com.imfbp.rz.dao.rzprjcontrchgeqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchgeqpt.RzPrjcontrChgEqpt;
import com.imfbp.rz.domain.rzprjcontrchgeqpt.query.RzPrjcontrChgEqptQuery;
import com.imfbp.rz.dao.rzprjcontrchgeqpt.RzPrjcontrChgEqptDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgEqptDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgEqpt(RzPrjcontrChgEqpt rzPrjcontrChgEqpt){
		this.getSqlSession().insert("rzPrjcontrChgEqpt.insertRzPrjcontrChgEqpt", rzPrjcontrChgEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgEqpt(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList){
		this.getSqlSession().insert("rzPrjcontrChgEqpt.insertBatchRzPrjcontrChgEqpt", rzPrjcontrChgEqptList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrChgEqpt.deleteRzPrjcontrChgEqptById", rzPrjcontrChgEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgEqptByCondition(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrChgEqpt.deleteRzPrjcontrChgEqptByCondition", rzPrjcontrChgEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgEqpt.deleteRzPrjcontrChgEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgEqptById(RzPrjcontrChgEqpt rzPrjcontrChgEqpt){
		return this.getSqlSession().update("rzPrjcontrChgEqpt.updateRzPrjcontrChgEqptById", rzPrjcontrChgEqpt)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgEqptList
	 * @return
	 */
	public boolean updateRzPrjcontrChgEqptByBatchId(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList){
		return this.getSqlSession().update("rzPrjcontrChgEqpt.updateRzPrjcontrChgEqptByBatchId", rzPrjcontrChgEqptList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgEqpt.updateRzPrjcontrChgEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgEqpt getRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgEqpt.getRzPrjcontrChgEqptById",rzPrjcontrChgEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptAll(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgEqpt.getRzPrjcontrChgEqptAll",rzPrjcontrChgEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptByPage(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgEqpt.getRzPrjcontrChgEqptByPage",rzPrjcontrChgEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgEqptByPageCount(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgEqpt.getRzPrjcontrChgEqptByPageCount",rzPrjcontrChgEqptQuery);
	}
}