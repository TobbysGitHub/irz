package com.imfbp.rz.dao.rzoverduederate.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzoverduederate.RzOverdueDerate;
import com.imfbp.rz.domain.rzoverduederate.query.RzOverdueDerateQuery;
import com.imfbp.rz.dao.rzoverduederate.RzOverdueDerateDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzOverdueDerateDaoImpl extends SqlSessionDaoSupport implements RzOverdueDerateDao{

	/**
	 * 添加
	 * @param rzOverdueDerate
	 * @return
	 */
	@Override
	public void insertRzOverdueDerate(RzOverdueDerate rzOverdueDerate){
		this.getSqlSession().insert("rzOverdueDerate.insertRzOverdueDerate", rzOverdueDerate);
	}
	
	/**
	 * 批量添加
	 * @param List<rzOverdueDerate>
	 * @return
	 */
	public void insertBatchRzOverdueDerate(List<RzOverdueDerate> rzOverdueDerateList){
		this.getSqlSession().insert("rzOverdueDerate.insertBatchRzOverdueDerate", rzOverdueDerateList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().delete("rzOverdueDerate.deleteRzOverdueDerateById", rzOverdueDerateQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDerateByCondition(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().delete("rzOverdueDerate.deleteRzOverdueDerateByCondition", rzOverdueDerateQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDerateByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzOverdueDerate.deleteRzOverdueDerateByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().update("rzOverdueDerate.logicDeleteRzOverdueDerateById", rzOverdueDerateQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzOverdueDerateByCondition(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().update("rzOverdueDerate.logicDeleteRzOverdueDerateByCondition", rzOverdueDerateQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzOverdueDerateByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzOverdueDerate.logicDeleteRzOverdueDerateByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzOverdueDerateQuery
	 * @return
	 */	
	@Override
	public boolean updateRzOverdueDerateById(RzOverdueDerate rzOverdueDerate){
		return this.getSqlSession().update("rzOverdueDerate.updateRzOverdueDerateById", rzOverdueDerate)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDerateList
	 * @return
	 */
	public boolean updateRzOverdueDerateByBatchId(List<RzOverdueDerate> rzOverdueDerateList){
		return this.getSqlSession().update("rzOverdueDerate.updateRzOverdueDerateByBatchId", rzOverdueDerateList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzOverdueDerateByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzOverdueDerate.updateRzOverdueDerateByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public RzOverdueDerate getRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().selectOne("rzOverdueDerate.getRzOverdueDerateById",rzOverdueDerateQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public List<RzOverdueDerate> getRzOverdueDerateAll(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().selectList("rzOverdueDerate.getRzOverdueDerateAll",rzOverdueDerateQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzOverdueDerate> getRzOverdueDerateByPage(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().selectList("rzOverdueDerate.getRzOverdueDerateByPage",rzOverdueDerateQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzOverdueDerateByPageCount(RzOverdueDerateQuery rzOverdueDerateQuery){
		return this.getSqlSession().selectOne("rzOverdueDerate.getRzOverdueDerateByPageCount",rzOverdueDerateQuery);
	}
}