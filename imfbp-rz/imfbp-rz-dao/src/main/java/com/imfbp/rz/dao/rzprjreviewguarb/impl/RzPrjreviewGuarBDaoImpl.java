package com.imfbp.rz.dao.rzprjreviewguarb.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjreviewguarb.RzPrjreviewGuarB;
import com.imfbp.rz.domain.rzprjreviewguarb.query.RzPrjreviewGuarBQuery;
import com.imfbp.rz.dao.rzprjreviewguarb.RzPrjreviewGuarBDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjreviewGuarBDaoImpl extends SqlSessionDaoSupport implements RzPrjreviewGuarBDao{

	/**
	 * 添加
	 * @param rzPrjreviewGuarB
	 * @return
	 */
	@Override
	public void insertRzPrjreviewGuarB(RzPrjreviewGuarB rzPrjreviewGuarB){
		this.getSqlSession().insert("rzPrjreviewGuarB.insertRzPrjreviewGuarB", rzPrjreviewGuarB);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewGuarB>
	 * @return
	 */
	public void insertBatchRzPrjreviewGuarB(List<RzPrjreviewGuarB> rzPrjreviewGuarBList){
		this.getSqlSession().insert("rzPrjreviewGuarB.insertBatchRzPrjreviewGuarB", rzPrjreviewGuarBList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarBById(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return this.getSqlSession().delete("rzPrjreviewGuarB.deleteRzPrjreviewGuarBById", rzPrjreviewGuarBQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarBByCondition(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return this.getSqlSession().delete("rzPrjreviewGuarB.deleteRzPrjreviewGuarBById", rzPrjreviewGuarBQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarBByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjreviewGuarB.deleteRzPrjreviewGuarBByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjreviewGuarBById(RzPrjreviewGuarB rzPrjreviewGuarB){
		return this.getSqlSession().update("rzPrjreviewGuarB.updateRzPrjreviewGuarBById", rzPrjreviewGuarB)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewGuarBByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjreviewGuarB.updateRzPrjreviewGuarBByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public RzPrjreviewGuarB getRzPrjreviewGuarBById(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return this.getSqlSession().selectOne("rzPrjreviewGuarB.getRzPrjreviewGuarBById",rzPrjreviewGuarBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewGuarB> getRzPrjreviewGuarBAll(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return this.getSqlSession().selectList("rzPrjreviewGuarB.getRzPrjreviewGuarBAll",rzPrjreviewGuarBQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjreviewGuarB> getRzPrjreviewGuarBByPage(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return this.getSqlSession().selectList("rzPrjreviewGuarB.getRzPrjreviewGuarBByPage",rzPrjreviewGuarBQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjreviewGuarBByPageCount(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return this.getSqlSession().selectOne("rzPrjreviewGuarB.getRzPrjreviewGuarBByPageCount",rzPrjreviewGuarBQuery);
	}

	@Override
	public boolean updateBatch(List<RzPrjreviewGuarB> editList) {
		return this.getSqlSession().update("rzPrjreviewGuarB.updateByBatch",editList)>0;
	}
}