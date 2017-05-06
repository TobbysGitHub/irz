package com.imfbp.rz.dao.rzrateb.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.dao.rzrateb.RzRateBDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzRateBDaoImpl extends SqlSessionDaoSupport implements RzRateBDao{

	/**
	 * 添加
	 * @param rzRateB
	 * @return
	 */
	@Override
	public void insertRzRateB(RzRateB rzRateB){
		this.getSqlSession().insert("rzRateB.insertRzRateB", rzRateB);
	}
	
	/**
	 * 批量添加
	 * @param List<rzRateB>
	 * @return
	 */
	public void insertBatchRzRateB(List<RzRateB> rzRateBList){
		this.getSqlSession().insert("rzRateB.insertBatchRzRateB", rzRateBList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateBById(RzRateBQuery rzRateBQuery){
		return this.getSqlSession().delete("rzRateB.deleteRzRateBById", rzRateBQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateBByCondition(RzRateBQuery rzRateBQuery){
		return this.getSqlSession().delete("rzRateB.deleteRzRateBByCondition", rzRateBQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzRateBByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzRateB.deleteRzRateBByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzRateBQuery
	 * @return
	 */	
	@Override
	public boolean updateRzRateBById(RzRateB rzRateB){
		return this.getSqlSession().update("rzRateB.updateRzRateBById", rzRateB)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRateBByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzRateB.updateRzRateBByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public RzRateB getRzRateBById(RzRateBQuery rzRateBQuery){
		return this.getSqlSession().selectOne("rzRateB.getRzRateBById",rzRateBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public List<RzRateB> getRzRateBAll(RzRateBQuery rzRateBQuery){
		return this.getSqlSession().selectList("rzRateB.getRzRateBAll",rzRateBQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzRateB> getRzRateBByPage(RzRateBQuery rzRateBQuery){
		return this.getSqlSession().selectList("rzRateB.getRzRateBByPage",rzRateBQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzRateBByPageCount(RzRateBQuery rzRateBQuery){
		return this.getSqlSession().selectOne("rzRateB.getRzRateBByPageCount",rzRateBQuery);
	}
}