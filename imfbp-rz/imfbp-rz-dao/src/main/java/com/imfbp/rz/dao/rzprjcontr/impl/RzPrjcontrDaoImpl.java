package com.imfbp.rz.dao.rzprjcontr.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrDao{

	/**
	 * 添加
	 * @param rzPrjcontr
	 * @return
	 */
	@Override
	public void insertRzPrjcontr(RzPrjcontr rzPrjcontr){
		this.getSqlSession().insert("rzPrjcontr.insertRzPrjcontr", rzPrjcontr);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontr>
	 * @return
	 */
	public void insertBatchRzPrjcontr(List<RzPrjcontr> rzPrjcontrList){
		this.getSqlSession().insert("rzPrjcontr.insertBatchRzPrjcontr", rzPrjcontrList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().delete("rzPrjcontr.deleteRzPrjcontrById", rzPrjcontrQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrByCondition(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().delete("rzPrjcontr.deleteRzPrjcontrByCondition", rzPrjcontrQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontr.deleteRzPrjcontrByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().update("rzPrjcontr.logicDeleteRzPrjcontrById", rzPrjcontrQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrByCondition(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().update("rzPrjcontr.logicDeleteRzPrjcontrByCondition", rzPrjcontrQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontr.logicDeleteRzPrjcontrByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrById(RzPrjcontr rzPrjcontr){
		return this.getSqlSession().update("rzPrjcontr.updateRzPrjcontrById", rzPrjcontr)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrList
	 * @return
	 */
	public boolean updateRzPrjcontrByBatchId(List<RzPrjcontr> rzPrjcontrList){
		return this.getSqlSession().update("rzPrjcontr.updateRzPrjcontrByBatchId", rzPrjcontrList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontr.updateRzPrjcontrByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public RzPrjcontr getRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().selectOne("rzPrjcontr.getRzPrjcontrById",rzPrjcontrQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontr> getRzPrjcontrAll(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().selectList("rzPrjcontr.getRzPrjcontrAll",rzPrjcontrQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontr> getRzPrjcontrByPage(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().selectList("rzPrjcontr.getRzPrjcontrByPage",rzPrjcontrQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrByPageCount(RzPrjcontrQuery rzPrjcontrQuery){
		return this.getSqlSession().selectOne("rzPrjcontr.getRzPrjcontrByPageCount",rzPrjcontrQuery);
	}
}