package com.imfbp.rz.dao.rzadjint.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjint.query.RzAdjIntQuery;
import com.imfbp.rz.dao.rzadjint.RzAdjIntDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzAdjIntDaoImpl extends SqlSessionDaoSupport implements RzAdjIntDao{

	/**
	 * 添加
	 * @param rzAdjInt
	 * @return
	 */
	@Override
	public void insertRzAdjInt(RzAdjInt rzAdjInt){
		this.getSqlSession().insert("rzAdjInt.insertRzAdjInt", rzAdjInt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzAdjInt>
	 * @return
	 */
	public void insertBatchRzAdjInt(List<RzAdjInt> rzAdjIntList){
		this.getSqlSession().insert("rzAdjInt.insertBatchRzAdjInt", rzAdjIntList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().delete("rzAdjInt.deleteRzAdjIntById", rzAdjIntQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().delete("rzAdjInt.deleteRzAdjIntByCondition", rzAdjIntQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzAdjInt.deleteRzAdjIntByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().update("rzAdjInt.logicDeleteRzAdjIntById", rzAdjIntQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().update("rzAdjInt.logicDeleteRzAdjIntByCondition", rzAdjIntQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAdjIntByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzAdjInt.logicDeleteRzAdjIntByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzAdjIntQuery
	 * @return
	 */	
	@Override
	public boolean updateRzAdjIntById(RzAdjInt rzAdjInt){
		return this.getSqlSession().update("rzAdjInt.updateRzAdjIntById", rzAdjInt)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntList
	 * @return
	 */
	public boolean updateRzAdjIntByBatchId(List<RzAdjInt> rzAdjIntList){
		return this.getSqlSession().update("rzAdjInt.updateRzAdjIntByBatchId", rzAdjIntList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzAdjInt.updateRzAdjIntByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public RzAdjInt getRzAdjIntById(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().selectOne("rzAdjInt.getRzAdjIntById",rzAdjIntQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public List<RzAdjInt> getRzAdjIntAll(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().selectList("rzAdjInt.getRzAdjIntAll",rzAdjIntQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzAdjInt> getRzAdjIntByPage(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().selectList("rzAdjInt.getRzAdjIntByPage",rzAdjIntQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzAdjIntByPageCount(RzAdjIntQuery rzAdjIntQuery){
		return this.getSqlSession().selectOne("rzAdjInt.getRzAdjIntByPageCount",rzAdjIntQuery);
	}
}