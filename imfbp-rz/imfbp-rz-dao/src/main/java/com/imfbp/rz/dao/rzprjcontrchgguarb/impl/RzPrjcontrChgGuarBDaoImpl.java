package com.imfbp.rz.dao.rzprjcontrchgguarb.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchgguarb.RzPrjcontrChgGuarB;
import com.imfbp.rz.domain.rzprjcontrchgguarb.query.RzPrjcontrChgGuarBQuery;
import com.imfbp.rz.dao.rzprjcontrchgguarb.RzPrjcontrChgGuarBDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgGuarBDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgGuarBDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgGuarB
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgGuarB(RzPrjcontrChgGuarB rzPrjcontrChgGuarB){
		this.getSqlSession().insert("rzPrjcontrChgGuarB.insertRzPrjcontrChgGuarB", rzPrjcontrChgGuarB);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgGuarB>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgGuarB(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList){
		this.getSqlSession().insert("rzPrjcontrChgGuarB.insertBatchRzPrjcontrChgGuarB", rzPrjcontrChgGuarBList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarBById(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return this.getSqlSession().delete("rzPrjcontrChgGuarB.deleteRzPrjcontrChgGuarBById", rzPrjcontrChgGuarBQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarBByCondition(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return this.getSqlSession().delete("rzPrjcontrChgGuarB.deleteRzPrjcontrChgGuarBByCondition", rzPrjcontrChgGuarBQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarBByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgGuarB.deleteRzPrjcontrChgGuarBByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgGuarBById(RzPrjcontrChgGuarB rzPrjcontrChgGuarB){
		return this.getSqlSession().update("rzPrjcontrChgGuarB.updateRzPrjcontrChgGuarBById", rzPrjcontrChgGuarB)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarBList
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarBByBatchId(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList){
		return this.getSqlSession().update("rzPrjcontrChgGuarB.updateRzPrjcontrChgGuarBByBatchId", rzPrjcontrChgGuarBList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgGuarBByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgGuarB.updateRzPrjcontrChgGuarBByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgGuarB getRzPrjcontrChgGuarBById(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgGuarB.getRzPrjcontrChgGuarBById",rzPrjcontrChgGuarBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgGuarB> getRzPrjcontrChgGuarBAll(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgGuarB.getRzPrjcontrChgGuarBAll",rzPrjcontrChgGuarBQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgGuarB> getRzPrjcontrChgGuarBByPage(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgGuarB.getRzPrjcontrChgGuarBByPage",rzPrjcontrChgGuarBQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgGuarBByPageCount(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgGuarB.getRzPrjcontrChgGuarBByPageCount",rzPrjcontrChgGuarBQuery);
	}

	@Override
	public boolean updateBatch(List<RzPrjcontrChgGuarB> editList) {
		return this.getSqlSession().update("rzPrjcontrChgGuarB.updateByBatch",editList) > 0;
	}
}