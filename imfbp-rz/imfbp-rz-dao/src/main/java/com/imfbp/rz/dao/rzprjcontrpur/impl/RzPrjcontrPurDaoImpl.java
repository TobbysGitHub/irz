package com.imfbp.rz.dao.rzprjcontrpur.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrpur.RzPrjcontrPur;
import com.imfbp.rz.domain.rzprjcontrpur.query.RzPrjcontrPurQuery;
import com.imfbp.rz.dao.rzprjcontrpur.RzPrjcontrPurDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrPurDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrPurDao{

	/**
	 * 添加
	 * @param rzPrjcontrPur
	 * @return
	 */
	@Override
	public void insertRzPrjcontrPur(RzPrjcontrPur rzPrjcontrPur){
		this.getSqlSession().insert("rzPrjcontrPur.insertRzPrjcontrPur", rzPrjcontrPur);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrPur>
	 * @return
	 */
	public void insertBatchRzPrjcontrPur(List<RzPrjcontrPur> rzPrjcontrPurList){
		this.getSqlSession().insert("rzPrjcontrPur.insertBatchRzPrjcontrPur", rzPrjcontrPurList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrPurById(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return this.getSqlSession().delete("rzPrjcontrPur.deleteRzPrjcontrPurById", rzPrjcontrPurQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrPurByCondition(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return this.getSqlSession().delete("rzPrjcontrPur.deleteRzPrjcontrPurByCondition", rzPrjcontrPurQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrPurByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrPur.deleteRzPrjcontrPurByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrPurQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrPurById(RzPrjcontrPur rzPrjcontrPur){
		return this.getSqlSession().update("rzPrjcontrPur.updateRzPrjcontrPurById", rzPrjcontrPur)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrPurList
	 * @return
	 */
	public boolean updateRzPrjcontrPurByBatchId(List<RzPrjcontrPur> rzPrjcontrPurList){
		return this.getSqlSession().update("rzPrjcontrPur.updateRzPrjcontrPurByBatchId", rzPrjcontrPurList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrPurByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrPur.updateRzPrjcontrPurByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public RzPrjcontrPur getRzPrjcontrPurById(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return this.getSqlSession().selectOne("rzPrjcontrPur.getRzPrjcontrPurById",rzPrjcontrPurQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrPur> getRzPrjcontrPurAll(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return this.getSqlSession().selectList("rzPrjcontrPur.getRzPrjcontrPurAll",rzPrjcontrPurQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrPur> getRzPrjcontrPurByPage(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return this.getSqlSession().selectList("rzPrjcontrPur.getRzPrjcontrPurByPage",rzPrjcontrPurQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrPurByPageCount(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return this.getSqlSession().selectOne("rzPrjcontrPur.getRzPrjcontrPurByPageCount",rzPrjcontrPurQuery);
	}
}