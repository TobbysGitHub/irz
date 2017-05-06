package com.imfbp.rz.dao.rzprjrevieweqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjrevieweqpt.RzPrjreviewEqpt;
import com.imfbp.rz.domain.rzprjrevieweqpt.query.RzPrjreviewEqptQuery;
import com.imfbp.rz.dao.rzprjrevieweqpt.RzPrjreviewEqptDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjreviewEqptDaoImpl extends SqlSessionDaoSupport implements RzPrjreviewEqptDao{

	/**
	 * 添加
	 * @param rzPrjreviewEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjreviewEqpt(RzPrjreviewEqpt rzPrjreviewEqpt){
		this.getSqlSession().insert("rzPrjreviewEqpt.insertRzPrjreviewEqpt", rzPrjreviewEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewEqpt>
	 * @return
	 */
	public void insertBatchRzPrjreviewEqpt(List<RzPrjreviewEqpt> rzPrjreviewEqptList){
		this.getSqlSession().insert("rzPrjreviewEqpt.insertBatchRzPrjreviewEqpt", rzPrjreviewEqptList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return this.getSqlSession().delete("rzPrjreviewEqpt.deleteRzPrjreviewEqptById", rzPrjreviewEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewEqptByCondition(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return this.getSqlSession().delete("rzPrjreviewEqpt.deleteRzPrjreviewEqptById", rzPrjreviewEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjreviewEqpt.deleteRzPrjreviewEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjreviewEqptById(RzPrjreviewEqpt rzPrjreviewEqpt){
		return this.getSqlSession().update("rzPrjreviewEqpt.updateRzPrjreviewEqptById", rzPrjreviewEqpt)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjreviewEqpt.updateRzPrjreviewEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public RzPrjreviewEqpt getRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return this.getSqlSession().selectOne("rzPrjreviewEqpt.getRzPrjreviewEqptById",rzPrjreviewEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewEqpt> getRzPrjreviewEqptAll(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return this.getSqlSession().selectList("rzPrjreviewEqpt.getRzPrjreviewEqptAll",rzPrjreviewEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjreviewEqpt> getRzPrjreviewEqptByPage(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return this.getSqlSession().selectList("rzPrjreviewEqpt.getRzPrjreviewEqptByPage",rzPrjreviewEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjreviewEqptByPageCount(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return this.getSqlSession().selectOne("rzPrjreviewEqpt.getRzPrjreviewEqptByPageCount",rzPrjreviewEqptQuery);
	}
}