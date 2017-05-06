package com.imfbp.rz.dao.rzprjcontrinseqpt.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrinseqpt.RzPrjcontrInsEqpt;
import com.imfbp.rz.domain.rzprjcontrinseqpt.query.RzPrjcontrInsEqptQuery;
import com.imfbp.rz.dao.rzprjcontrinseqpt.RzPrjcontrInsEqptDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrInsEqptDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrInsEqptDao{

	/**
	 * 添加
	 * @param rzPrjcontrInsEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrInsEqpt(RzPrjcontrInsEqpt rzPrjcontrInsEqpt){
		this.getSqlSession().insert("rzPrjcontrInsEqpt.insertRzPrjcontrInsEqpt", rzPrjcontrInsEqpt);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrInsEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrInsEqpt(List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList){
		this.getSqlSession().insert("rzPrjcontrInsEqpt.insertBatchRzPrjcontrInsEqpt", rzPrjcontrInsEqptList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsEqptById(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrInsEqpt.deleteRzPrjcontrInsEqptById", rzPrjcontrInsEqptQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsEqptByCondition(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return this.getSqlSession().delete("rzPrjcontrInsEqpt.deleteRzPrjcontrInsEqptByCondition", rzPrjcontrInsEqptQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsEqptByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrInsEqpt.deleteRzPrjcontrInsEqptByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrInsEqptById(RzPrjcontrInsEqpt rzPrjcontrInsEqpt){
		return this.getSqlSession().update("rzPrjcontrInsEqpt.updateRzPrjcontrInsEqptById", rzPrjcontrInsEqpt)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrInsEqptList
	 * @return
	 */
	public boolean updateRzPrjcontrInsEqptByBatchId(List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList){
		return this.getSqlSession().update("rzPrjcontrInsEqpt.updateRzPrjcontrInsEqptByBatchId", rzPrjcontrInsEqptList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrInsEqptByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrInsEqpt.updateRzPrjcontrInsEqptByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrInsEqpt getRzPrjcontrInsEqptById(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrInsEqpt.getRzPrjcontrInsEqptById",rzPrjcontrInsEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrInsEqpt> getRzPrjcontrInsEqptAll(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrInsEqpt.getRzPrjcontrInsEqptAll",rzPrjcontrInsEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrInsEqpt> getRzPrjcontrInsEqptByPage(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return this.getSqlSession().selectList("rzPrjcontrInsEqpt.getRzPrjcontrInsEqptByPage",rzPrjcontrInsEqptQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrInsEqptByPageCount(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return this.getSqlSession().selectOne("rzPrjcontrInsEqpt.getRzPrjcontrInsEqptByPageCount",rzPrjcontrInsEqptQuery);
	}
}