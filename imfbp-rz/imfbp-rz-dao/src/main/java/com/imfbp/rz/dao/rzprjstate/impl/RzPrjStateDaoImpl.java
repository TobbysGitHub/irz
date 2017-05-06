package com.imfbp.rz.dao.rzprjstate.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.domain.rzprjstate.query.RzPrjStateQuery;
import com.imfbp.rz.dao.rzprjstate.RzPrjStateDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjStateDaoImpl extends SqlSessionDaoSupport implements RzPrjStateDao{

	/**
	 * 添加
	 * @param rzPrjState
	 * @return
	 */
	@Override
	public void insertRzPrjState(RzPrjState rzPrjState){
		this.getSqlSession().insert("rzPrjState.insertRzPrjState", rzPrjState);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjState>
	 * @return
	 */
	public void insertBatchRzPrjState(List<RzPrjState> rzPrjStateList){
		this.getSqlSession().insert("rzPrjState.insertBatchRzPrjState", rzPrjStateList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjStateById(RzPrjStateQuery rzPrjStateQuery){
		return this.getSqlSession().delete("rzPrjState.deleteRzPrjStateById", rzPrjStateQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjStateByCondition(RzPrjStateQuery rzPrjStateQuery){
		return this.getSqlSession().delete("rzPrjState.deleteRzPrjStateByCondition", rzPrjStateQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjStateByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjState.deleteRzPrjStateByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjStateQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjStateById(RzPrjState rzPrjState){
		return this.getSqlSession().update("rzPrjState.updateRzPrjStateById", rzPrjState)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjStateList
	 * @return
	 */
	public boolean updateRzPrjStateByBatchId(List<RzPrjState> rzPrjStateList){
		return this.getSqlSession().update("rzPrjState.updateRzPrjStateByBatchId", rzPrjStateList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjStateByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjState.updateRzPrjStateByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public RzPrjState getRzPrjStateById(RzPrjStateQuery rzPrjStateQuery){
		return this.getSqlSession().selectOne("rzPrjState.getRzPrjStateById",rzPrjStateQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public List<RzPrjState> getRzPrjStateAll(RzPrjStateQuery rzPrjStateQuery){
		return this.getSqlSession().selectList("rzPrjState.getRzPrjStateAll",rzPrjStateQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjState> getRzPrjStateByPage(RzPrjStateQuery rzPrjStateQuery){
		return this.getSqlSession().selectList("rzPrjState.getRzPrjStateByPage",rzPrjStateQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjStateByPageCount(RzPrjStateQuery rzPrjStateQuery){
		return this.getSqlSession().selectOne("rzPrjState.getRzPrjStateByPageCount",rzPrjStateQuery);
	}
}