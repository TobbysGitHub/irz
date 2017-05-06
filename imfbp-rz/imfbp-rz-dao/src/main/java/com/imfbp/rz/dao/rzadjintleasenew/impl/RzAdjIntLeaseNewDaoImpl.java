package com.imfbp.rz.dao.rzadjintleasenew.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzadjintleasenew.RzAdjIntLeaseNew;
import com.imfbp.rz.domain.rzadjintleasenew.query.RzAdjIntLeaseNewQuery;
import com.imfbp.rz.dao.rzadjintleasenew.RzAdjIntLeaseNewDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzAdjIntLeaseNewDaoImpl extends SqlSessionDaoSupport implements RzAdjIntLeaseNewDao{

	/**
	 * 添加
	 * @param rzAdjIntLeaseNew
	 * @return
	 */
	@Override
	public void insertRzAdjIntLeaseNew(RzAdjIntLeaseNew rzAdjIntLeaseNew){
		this.getSqlSession().insert("rzAdjIntLeaseNew.insertRzAdjIntLeaseNew", rzAdjIntLeaseNew);
	}
	
	/**
	 * 批量添加
	 * @param List<rzAdjIntLeaseNew>
	 * @return
	 */
	public void insertBatchRzAdjIntLeaseNew(List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList){
		this.getSqlSession().insert("rzAdjIntLeaseNew.insertBatchRzAdjIntLeaseNew", rzAdjIntLeaseNewList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseNewById(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return this.getSqlSession().delete("rzAdjIntLeaseNew.deleteRzAdjIntLeaseNewById", rzAdjIntLeaseNewQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseNewByCondition(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return this.getSqlSession().delete("rzAdjIntLeaseNew.deleteRzAdjIntLeaseNewByCondition", rzAdjIntLeaseNewQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseNewByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzAdjIntLeaseNew.deleteRzAdjIntLeaseNewByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */	
	@Override
	public boolean updateRzAdjIntLeaseNewById(RzAdjIntLeaseNew rzAdjIntLeaseNew){
		return this.getSqlSession().update("rzAdjIntLeaseNew.updateRzAdjIntLeaseNewById", rzAdjIntLeaseNew)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntLeaseNewList
	 * @return
	 */
	public boolean updateRzAdjIntLeaseNewByBatchId(List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList){
		return this.getSqlSession().update("rzAdjIntLeaseNew.updateRzAdjIntLeaseNewByBatchId", rzAdjIntLeaseNewList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntLeaseNewByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzAdjIntLeaseNew.updateRzAdjIntLeaseNewByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public RzAdjIntLeaseNew getRzAdjIntLeaseNewById(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return this.getSqlSession().selectOne("rzAdjIntLeaseNew.getRzAdjIntLeaseNewById",rzAdjIntLeaseNewQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public List<RzAdjIntLeaseNew> getRzAdjIntLeaseNewAll(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return this.getSqlSession().selectList("rzAdjIntLeaseNew.getRzAdjIntLeaseNewAll",rzAdjIntLeaseNewQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzAdjIntLeaseNew> getRzAdjIntLeaseNewByPage(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return this.getSqlSession().selectList("rzAdjIntLeaseNew.getRzAdjIntLeaseNewByPage",rzAdjIntLeaseNewQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzAdjIntLeaseNewByPageCount(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return this.getSqlSession().selectOne("rzAdjIntLeaseNew.getRzAdjIntLeaseNewByPageCount",rzAdjIntLeaseNewQuery);
	}
}