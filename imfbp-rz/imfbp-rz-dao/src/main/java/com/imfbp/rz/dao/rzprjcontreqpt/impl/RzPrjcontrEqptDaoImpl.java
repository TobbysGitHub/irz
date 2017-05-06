package com.imfbp.rz.dao.rzprjcontreqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontreqpt.RzPrjcontrEqpt;
import com.imfbp.rz.domain.rzprjcontreqpt.query.RzPrjcontrEqptQuery;
import com.imfbp.rz.dao.rzprjcontreqpt.RzPrjcontrEqptDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrEqptDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrEqpt(RzPrjcontrEqpt rzPrjcontrEqpt){
		this.getSqlSession().insert("rzPrjcontrEqpt.insertRzPrjcontrEqpt", rzPrjcontrEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrEqpt(List<RzPrjcontrEqpt> rzPrjcontrEqptList){
		this.getSqlSession().insert("rzPrjcontrEqpt.insertBatchRzPrjcontrEqpt", rzPrjcontrEqptList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptById(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrEqpt.deleteRzPrjcontrEqptById", rzPrjcontrEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptByCondition(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrEqpt.deleteRzPrjcontrEqptByCondition", rzPrjcontrEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrEqpt.deleteRzPrjcontrEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrEqptById(RzPrjcontrEqpt rzPrjcontrEqpt){
		return this.getSqlSession().update("rzPrjcontrEqpt.updateRzPrjcontrEqptById", rzPrjcontrEqpt)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrEqptList
	 * @return
	 */
	public boolean updateRzPrjcontrEqptByBatchId(List<RzPrjcontrEqpt> rzPrjcontrEqptList){
		return this.getSqlSession().update("rzPrjcontrEqpt.updateRzPrjcontrEqptByBatchId", rzPrjcontrEqptList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrEqpt.updateRzPrjcontrEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrEqpt getRzPrjcontrEqptById(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrEqpt.getRzPrjcontrEqptById",rzPrjcontrEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrEqpt> getRzPrjcontrEqptAll(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrEqpt.getRzPrjcontrEqptAll",rzPrjcontrEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrEqpt> getRzPrjcontrEqptByPage(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrEqpt.getRzPrjcontrEqptByPage",rzPrjcontrEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrEqptByPageCount(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrEqpt.getRzPrjcontrEqptByPageCount",rzPrjcontrEqptQuery);
	}
}