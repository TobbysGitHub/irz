package com.imfbp.rz.dao.rzprjcontrguarb.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;
import com.imfbp.rz.dao.rzprjcontrguarb.RzPrjcontrGuarBDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrGuarBDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrGuarBDao{

	/**
	 * 添加
	 * @param rzPrjcontrGuarB
	 * @return
	 */
	@Override
	public void insertRzPrjcontrGuarB(RzPrjcontrGuarB rzPrjcontrGuarB){
		this.getSqlSession().insert("rzPrjcontrGuarB.insertRzPrjcontrGuarB", rzPrjcontrGuarB);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrGuarB>
	 * @return
	 */
	public void insertBatchRzPrjcontrGuarB(List<RzPrjcontrGuarB> rzPrjcontrGuarBList){
		this.getSqlSession().insert("rzPrjcontrGuarB.insertBatchRzPrjcontrGuarB", rzPrjcontrGuarBList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarBById(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return this.getSqlSession().delete("rzPrjcontrGuarB.deleteRzPrjcontrGuarBById", rzPrjcontrGuarBQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarBByCondition(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return this.getSqlSession().delete("rzPrjcontrGuarB.deleteRzPrjcontrGuarBByCondition", rzPrjcontrGuarBQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarBByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrGuarB.deleteRzPrjcontrGuarBByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrGuarBById(RzPrjcontrGuarB rzPrjcontrGuarB){
		return this.getSqlSession().update("rzPrjcontrGuarB.updateRzPrjcontrGuarBById", rzPrjcontrGuarB)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrGuarBList
	 * @return
	 */
	public boolean updateRzPrjcontrGuarBByBatchId(List<RzPrjcontrGuarB> rzPrjcontrGuarBList){
		return this.getSqlSession().update("rzPrjcontrGuarB.updateRzPrjcontrGuarBByBatchId", rzPrjcontrGuarBList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrGuarBByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrGuarB.updateRzPrjcontrGuarBByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public RzPrjcontrGuarB getRzPrjcontrGuarBById(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return this.getSqlSession().selectOne("rzPrjcontrGuarB.getRzPrjcontrGuarBById",rzPrjcontrGuarBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrGuarB> getRzPrjcontrGuarBAll(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return this.getSqlSession().selectList("rzPrjcontrGuarB.getRzPrjcontrGuarBAll",rzPrjcontrGuarBQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrGuarB> getRzPrjcontrGuarBByPage(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return this.getSqlSession().selectList("rzPrjcontrGuarB.getRzPrjcontrGuarBByPage",rzPrjcontrGuarBQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrGuarBByPageCount(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return this.getSqlSession().selectOne("rzPrjcontrGuarB.getRzPrjcontrGuarBByPageCount",rzPrjcontrGuarBQuery);
	}

	@Override
	public boolean updateBatch(List<RzPrjcontrGuarB> editList) {
		return this.getSqlSession().update("rzPrjcontrGuarB.updateByBatch",editList) > 0;
	}
}