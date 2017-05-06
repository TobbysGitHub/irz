package com.imfbp.rz.dao.rzrate.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;
import com.imfbp.rz.dao.rzrate.RzRateDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzRateDaoImpl extends SqlSessionDaoSupport implements RzRateDao{

	/**
	 * 添加
	 * @param rzRate
	 * @return
	 */
	@Override
	public void insertRzRate(RzRate rzRate){
		this.getSqlSession().insert("rzRate.insertRzRate", rzRate);
	}
	
	/**
	 * 批量添加
	 * @param List<rzRate>
	 * @return
	 */
	public void insertBatchRzRate(List<RzRate> rzRateList){
		this.getSqlSession().insert("rzRate.insertBatchRzRate", rzRateList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateById(RzRateQuery rzRateQuery){
		return this.getSqlSession().delete("rzRate.deleteRzRateById", rzRateQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateByCondition(RzRateQuery rzRateQuery){
		return this.getSqlSession().delete("rzRate.deleteRzRateById", rzRateQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzRateByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzRate.deleteRzRateByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRateById(RzRateQuery rzRateQuery){
		return this.getSqlSession().update("rzRate.logicDeleteRzRateById", rzRateQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRateByCondition(RzRateQuery rzRateQuery){
		return this.getSqlSession().update("rzRate.logicDeleteRzRateById", rzRateQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRateByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzRate.logicDeleteRzRateByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzRateQuery
	 * @return
	 */	
	@Override
	public boolean updateRzRateById(RzRate rzRate){
		return this.getSqlSession().update("rzRate.updateRzRateById", rzRate)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRateByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzRate.updateRzRateByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public RzRate getRzRateById(RzRateQuery rzRateQuery){
		return this.getSqlSession().selectOne("rzRate.getRzRateById",rzRateQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public List<RzRate> getRzRateAll(RzRateQuery rzRateQuery){
		return this.getSqlSession().selectList("rzRate.getRzRateAll",rzRateQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzRate> getRzRateByPage(RzRateQuery rzRateQuery){
		return this.getSqlSession().selectList("rzRate.getRzRateByPage",rzRateQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzRateByPageCount(RzRateQuery rzRateQuery){
		return this.getSqlSession().selectOne("rzRate.getRzRateByPageCount",rzRateQuery);
	}
}