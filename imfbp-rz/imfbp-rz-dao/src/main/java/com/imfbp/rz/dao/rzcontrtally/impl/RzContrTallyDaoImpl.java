package com.imfbp.rz.dao.rzcontrtally.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;
import com.imfbp.rz.dao.rzcontrtally.RzContrTallyDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzContrTallyDaoImpl extends SqlSessionDaoSupport implements RzContrTallyDao{

	/**
	 * 添加
	 * @param rzContrTally
	 * @return
	 */
	@Override
	public void insertRzContrTally(RzContrTally rzContrTally){
		this.getSqlSession().insert("rzContrTally.insertRzContrTally", rzContrTally);
	}
	
	/**
	 * 批量添加
	 * @param List<rzContrTally>
	 * @return
	 */
	public void insertBatchRzContrTally(List<RzContrTally> rzContrTallyList){
		this.getSqlSession().insert("rzContrTally.insertBatchRzContrTally", rzContrTallyList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public boolean deleteRzContrTallyById(RzContrTallyQuery rzContrTallyQuery){
		return this.getSqlSession().delete("rzContrTally.deleteRzContrTallyById", rzContrTallyQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public boolean deleteRzContrTallyByCondition(RzContrTallyQuery rzContrTallyQuery){
		return this.getSqlSession().delete("rzContrTally.deleteRzContrTallyByCondition", rzContrTallyQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzContrTallyByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzContrTally.deleteRzContrTallyByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzContrTallyQuery
	 * @return
	 */	
	@Override
	public boolean updateRzContrTallyById(RzContrTally rzContrTally){
		return this.getSqlSession().update("rzContrTally.updateRzContrTallyById", rzContrTally)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzContrTallyList
	 * @return
	 */
	public boolean updateRzContrTallyByBatchId(List<RzContrTally> rzContrTallyList){
		return this.getSqlSession().update("rzContrTally.updateRzContrTallyByBatchId", rzContrTallyList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzContrTallyByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzContrTally.updateRzContrTallyByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public RzContrTally getRzContrTallyById(RzContrTallyQuery rzContrTallyQuery){
		return this.getSqlSession().selectOne("rzContrTally.getRzContrTallyById",rzContrTallyQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public List<RzContrTally> getRzContrTallyAll(RzContrTallyQuery rzContrTallyQuery){
		return this.getSqlSession().selectList("rzContrTally.getRzContrTallyAll",rzContrTallyQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzContrTally> getRzContrTallyByPage(RzContrTallyQuery rzContrTallyQuery){
		return this.getSqlSession().selectList("rzContrTally.getRzContrTallyByPage",rzContrTallyQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzContrTallyByPageCount(RzContrTallyQuery rzContrTallyQuery){
		return this.getSqlSession().selectOne("rzContrTally.getRzContrTallyByPageCount",rzContrTallyQuery);
	}

	@Override
	public List<RzContrTally> getRzContrTallyMaxOperSeq(RzContrTallyQuery rzContrTallyQuery) {
		return this.getSqlSession().selectList("rzContrTally.getRzContrTallyMaxOperSeq",rzContrTallyQuery);
	}

	@Override
	public List<RzContrTally> getRzContrTallyBalance(RzContrTallyQuery rzContrTallyQuery) {
		return this.getSqlSession().selectList("rzContrTally.getRzContrTallyBalance",rzContrTallyQuery);
	}
}