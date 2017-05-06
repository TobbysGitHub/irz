package com.imfbp.rz.dao.rzprjcontrcf.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrcf.RzPrjcontrCf;
import com.imfbp.rz.domain.rzprjcontrcf.query.RzPrjcontrCfQuery;
import com.imfbp.rz.dao.rzprjcontrcf.RzPrjcontrCfDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrCfDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrCfDao{

	/**
	 * 添加
	 * @param rzPrjcontrCf
	 * @return
	 */
	@Override
	public void insertRzPrjcontrCf(RzPrjcontrCf rzPrjcontrCf){
		this.getSqlSession().insert("rzPrjcontrCf.insertRzPrjcontrCf", rzPrjcontrCf);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrCf>
	 * @return
	 */
	public void insertBatchRzPrjcontrCf(List<RzPrjcontrCf> rzPrjcontrCfList){
		this.getSqlSession().insert("rzPrjcontrCf.insertBatchRzPrjcontrCf", rzPrjcontrCfList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrCfById(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return this.getSqlSession().delete("rzPrjcontrCf.deleteRzPrjcontrCfById", rzPrjcontrCfQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrCfByCondition(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return this.getSqlSession().delete("rzPrjcontrCf.deleteRzPrjcontrCfByCondition", rzPrjcontrCfQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrCfByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrCf.deleteRzPrjcontrCfByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrCfQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrCfById(RzPrjcontrCf rzPrjcontrCf){
		return this.getSqlSession().update("rzPrjcontrCf.updateRzPrjcontrCfById", rzPrjcontrCf)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrCfList
	 * @return
	 */
	public boolean updateRzPrjcontrCfByBatchId(List<RzPrjcontrCf> rzPrjcontrCfList){
		return this.getSqlSession().update("rzPrjcontrCf.updateRzPrjcontrCfByBatchId", rzPrjcontrCfList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrCfByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrCf.updateRzPrjcontrCfByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public RzPrjcontrCf getRzPrjcontrCfById(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return this.getSqlSession().selectOne("rzPrjcontrCf.getRzPrjcontrCfById",rzPrjcontrCfQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrCf> getRzPrjcontrCfAll(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return this.getSqlSession().selectList("rzPrjcontrCf.getRzPrjcontrCfAll",rzPrjcontrCfQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrCf> getRzPrjcontrCfByPage(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return this.getSqlSession().selectList("rzPrjcontrCf.getRzPrjcontrCfByPage",rzPrjcontrCfQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrCfByPageCount(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return this.getSqlSession().selectOne("rzPrjcontrCf.getRzPrjcontrCfByPageCount",rzPrjcontrCfQuery);
	}
}