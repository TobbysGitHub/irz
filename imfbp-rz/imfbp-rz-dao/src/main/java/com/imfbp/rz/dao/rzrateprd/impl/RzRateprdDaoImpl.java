package com.imfbp.rz.dao.rzrateprd.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;
import com.imfbp.rz.dao.rzrateprd.RzRateprdDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzRateprdDaoImpl extends SqlSessionDaoSupport implements RzRateprdDao{

	/**
	 * 添加
	 * @param rzRateprd
	 * @return
	 */
	@Override
	public void insertRzRateprd(RzRateprd rzRateprd){
		this.getSqlSession().insert("rzRateprd.insertRzRateprd", rzRateprd);
	}
	
	/**
	 * 批量添加
	 * @param List<rzRateprd>
	 * @return
	 */
	public void insertBatchRzRateprd(List<RzRateprd> rzRateprdList){
		this.getSqlSession().insert("rzRateprd.insertBatchRzRateprd", rzRateprdList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateprdById(RzRateprdQuery rzRateprdQuery){
		return this.getSqlSession().delete("rzRateprd.deleteRzRateprdById", rzRateprdQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateprdByCondition(RzRateprdQuery rzRateprdQuery){
		return this.getSqlSession().delete("rzRateprd.deleteRzRateprdById", rzRateprdQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzRateprdByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzRateprd.deleteRzRateprdByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzRateprdQuery
	 * @return
	 */	
	@Override
	public boolean updateRzRateprdById(RzRateprd rzRateprd){
		return this.getSqlSession().update("rzRateprd.updateRzRateprdById", rzRateprd)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRateprdByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzRateprd.updateRzRateprdByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public RzRateprd getRzRateprdById(RzRateprdQuery rzRateprdQuery){
		return this.getSqlSession().selectOne("rzRateprd.getRzRateprdById",rzRateprdQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public List<RzRateprd> getRzRateprdAll(RzRateprdQuery rzRateprdQuery){
		return this.getSqlSession().selectList("rzRateprd.getRzRateprdAll",rzRateprdQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzRateprd> getRzRateprdByPage(RzRateprdQuery rzRateprdQuery){
		return this.getSqlSession().selectList("rzRateprd.getRzRateprdByPage",rzRateprdQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzRateprdByPageCount(RzRateprdQuery rzRateprdQuery){
		return this.getSqlSession().selectOne("rzRateprd.getRzRateprdByPageCount",rzRateprdQuery);
	}

	@Override
	public List<RzRateprd> getRzRateprdByDays(RzRateprdQuery rzRateprdQuery) {
		return this.getSqlSession().selectList("rzRateprd.getRzRateprdByDays",rzRateprdQuery);
	}
}