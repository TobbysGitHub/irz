package com.imfbp.rz.dao.rzprjcontrchgpur.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchgpur.RzPrjcontrChgPur;
import com.imfbp.rz.domain.rzprjcontrchgpur.query.RzPrjcontrChgPurQuery;
import com.imfbp.rz.dao.rzprjcontrchgpur.RzPrjcontrChgPurDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgPurDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgPurDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgPur
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgPur(RzPrjcontrChgPur rzPrjcontrChgPur){
		this.getSqlSession().insert("rzPrjcontrChgPur.insertRzPrjcontrChgPur", rzPrjcontrChgPur);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgPur>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgPur(List<RzPrjcontrChgPur> rzPrjcontrChgPurList){
		this.getSqlSession().insert("rzPrjcontrChgPur.insertBatchRzPrjcontrChgPur", rzPrjcontrChgPurList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgPurById(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return this.getSqlSession().delete("rzPrjcontrChgPur.deleteRzPrjcontrChgPurById", rzPrjcontrChgPurQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgPurByCondition(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return this.getSqlSession().delete("rzPrjcontrChgPur.deleteRzPrjcontrChgPurByCondition", rzPrjcontrChgPurQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgPurByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgPur.deleteRzPrjcontrChgPurByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgPurById(RzPrjcontrChgPur rzPrjcontrChgPur){
		return this.getSqlSession().update("rzPrjcontrChgPur.updateRzPrjcontrChgPurById", rzPrjcontrChgPur)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgPurList
	 * @return
	 */
	public boolean updateRzPrjcontrChgPurByBatchId(List<RzPrjcontrChgPur> rzPrjcontrChgPurList){
		return this.getSqlSession().update("rzPrjcontrChgPur.updateRzPrjcontrChgPurByBatchId", rzPrjcontrChgPurList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgPurByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgPur.updateRzPrjcontrChgPurByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgPur getRzPrjcontrChgPurById(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgPur.getRzPrjcontrChgPurById",rzPrjcontrChgPurQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgPur> getRzPrjcontrChgPurAll(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgPur.getRzPrjcontrChgPurAll",rzPrjcontrChgPurQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgPur> getRzPrjcontrChgPurByPage(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgPur.getRzPrjcontrChgPurByPage",rzPrjcontrChgPurQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgPurByPageCount(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgPur.getRzPrjcontrChgPurByPageCount",rzPrjcontrChgPurQuery);
	}
}