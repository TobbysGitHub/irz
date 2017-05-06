package com.imfbp.rz.dao.rzdefinterest.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterest.query.RzDefInterestQuery;
import com.imfbp.rz.dao.rzdefinterest.RzDefInterestDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzDefInterestDaoImpl extends SqlSessionDaoSupport implements RzDefInterestDao{

	/**
	 * 添加
	 * @param rzDefInterest
	 * @return
	 */
	@Override
	public void insertRzDefInterest(RzDefInterest rzDefInterest){
		this.getSqlSession().insert("rzDefInterest.insertRzDefInterest", rzDefInterest);
	}
	
	/**
	 * 批量添加
	 * @param List<rzDefInterest>
	 * @return
	 */
	public void insertBatchRzDefInterest(List<RzDefInterest> rzDefInterestList){
		this.getSqlSession().insert("rzDefInterest.insertBatchRzDefInterest", rzDefInterestList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().delete("rzDefInterest.deleteRzDefInterestById", rzDefInterestQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().delete("rzDefInterest.deleteRzDefInterestByCondition", rzDefInterestQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzDefInterest.deleteRzDefInterestByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().update("rzDefInterest.logicDeleteRzDefInterestById", rzDefInterestQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().update("rzDefInterest.logicDeleteRzDefInterestByCondition", rzDefInterestQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDefInterestByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzDefInterest.logicDeleteRzDefInterestByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzDefInterestQuery
	 * @return
	 */	
	@Override
	public boolean updateRzDefInterestById(RzDefInterest rzDefInterest){
		return this.getSqlSession().update("rzDefInterest.updateRzDefInterestById", rzDefInterest)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestList
	 * @return
	 */
	public boolean updateRzDefInterestByBatchId(List<RzDefInterest> rzDefInterestList){
		return this.getSqlSession().update("rzDefInterest.updateRzDefInterestByBatchId", rzDefInterestList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzDefInterestByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzDefInterest.updateRzDefInterestByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public RzDefInterest getRzDefInterestById(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().selectOne("rzDefInterest.getRzDefInterestById",rzDefInterestQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public List<RzDefInterest> getRzDefInterestAll(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().selectList("rzDefInterest.getRzDefInterestAll",rzDefInterestQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzDefInterest> getRzDefInterestByPage(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().selectList("rzDefInterest.getRzDefInterestByPage",rzDefInterestQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzDefInterestByPageCount(RzDefInterestQuery rzDefInterestQuery){
		return this.getSqlSession().selectOne("rzDefInterest.getRzDefInterestByPageCount",rzDefInterestQuery);
	}
}