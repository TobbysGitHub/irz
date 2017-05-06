package com.imfbp.rz.dao.rzeqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;
import com.imfbp.rz.dao.rzeqpt.RzEqptDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzEqptDaoImpl extends SqlSessionDaoSupport implements RzEqptDao{

	/**
	 * 添加
	 * @param rzEqpt
	 * @return
	 */
	@Override
	public void insertRzEqpt(RzEqpt rzEqpt){
		this.getSqlSession().insert("rzEqpt.insertRzEqpt", rzEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzEqpt>
	 * @return
	 */
	public void insertBatchRzEqpt(List<RzEqpt> rzEqptList){
		this.getSqlSession().insert("rzEqpt.insertBatchRzEqpt", rzEqptList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzEqptById(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().delete("rzEqpt.deleteRzEqptById", rzEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzEqptByCondition(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().delete("rzEqpt.deleteRzEqptByCondition", rzEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzEqpt.deleteRzEqptByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzEqptById(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().update("rzEqpt.logicDeleteRzEqptById", rzEqptQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzEqptByCondition(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().update("rzEqpt.logicDeleteRzEqptByCondition", rzEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzEqpt.logicDeleteRzEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzEqptById(RzEqpt rzEqpt){
		return this.getSqlSession().update("rzEqpt.updateRzEqptById", rzEqpt)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzEqptList
	 * @return
	 */
	public boolean updateRzEqptByBatchId(List<RzEqpt> rzEqptList){
		return this.getSqlSession().update("rzEqpt.updateRzEqptByBatchId", rzEqptList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzEqpt.updateRzEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public RzEqpt getRzEqptById(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().selectOne("rzEqpt.getRzEqptById",rzEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public List<RzEqpt> getRzEqptAll(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().selectList("rzEqpt.getRzEqptAll",rzEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzEqpt> getRzEqptByPage(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().selectList("rzEqpt.getRzEqptByPage",rzEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzEqptByPageCount(RzEqptQuery rzEqptQuery){
		return this.getSqlSession().selectOne("rzEqpt.getRzEqptByPageCount",rzEqptQuery);
	}
}