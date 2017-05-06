package com.imfbp.rz.dao.rzprjreview.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.dao.rzprjreview.RzPrjreviewDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjreviewDaoImpl extends SqlSessionDaoSupport implements RzPrjreviewDao{

	/**
	 * 添加
	 * @param rzPrjreview
	 * @return
	 */
	@Override
	public void insertRzPrjreview(RzPrjreview rzPrjreview){
		this.getSqlSession().insert("rzPrjreview.insertRzPrjreview", rzPrjreview);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreview>
	 * @return
	 */
	public void insertBatchRzPrjreview(List<RzPrjreview> rzPrjreviewList){
		this.getSqlSession().insert("rzPrjreview.insertBatchRzPrjreview", rzPrjreviewList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().delete("rzPrjreview.deleteRzPrjreviewById", rzPrjreviewQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().delete("rzPrjreview.deleteRzPrjreviewById", rzPrjreviewQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjreview.deleteRzPrjreviewByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().update("rzPrjreview.logicDeleteRzPrjreviewById", rzPrjreviewQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().update("rzPrjreview.logicDeleteRzPrjreviewById", rzPrjreviewQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjreviewByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjreview.logicDeleteRzPrjreviewByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjreviewQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjreviewById(RzPrjreview rzPrjreview){
		return this.getSqlSession().update("rzPrjreview.updateRzPrjreviewById", rzPrjreview)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjreview.updateRzPrjreviewByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public RzPrjreview getRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().selectOne("rzPrjreview.getRzPrjreviewById",rzPrjreviewQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public List<RzPrjreview> getRzPrjreviewAll(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().selectList("rzPrjreview.getRzPrjreviewAll",rzPrjreviewQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjreview> getRzPrjreviewByPage(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().selectList("rzPrjreview.getRzPrjreviewByPage",rzPrjreviewQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjreviewByPageCount(RzPrjreviewQuery rzPrjreviewQuery){
		return this.getSqlSession().selectOne("rzPrjreview.getRzPrjreviewByPageCount",rzPrjreviewQuery);
	}

	@Override
	public List<String> getRzPrjreviewByFkId(Map<String, Object> map) {
		return this.getSqlSession().selectList("rzPrjreview.getRzPrjreviewByFkId",map);
	}
}