package com.imfbp.rz.dao.rzprjcontrchgguar.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchgguar.RzPrjcontrChgGuar;
import com.imfbp.rz.domain.rzprjcontrchgguar.query.RzPrjcontrChgGuarQuery;
import com.imfbp.rz.dao.rzprjcontrchgguar.RzPrjcontrChgGuarDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgGuarDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgGuarDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgGuar
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgGuar(RzPrjcontrChgGuar rzPrjcontrChgGuar){
		this.getSqlSession().insert("rzPrjcontrChgGuar.insertRzPrjcontrChgGuar", rzPrjcontrChgGuar);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgGuar>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgGuar(List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList){
		this.getSqlSession().insert("rzPrjcontrChgGuar.insertBatchRzPrjcontrChgGuar", rzPrjcontrChgGuarList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarById(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return this.getSqlSession().delete("rzPrjcontrChgGuar.deleteRzPrjcontrChgGuarById", rzPrjcontrChgGuarQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarByCondition(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return this.getSqlSession().delete("rzPrjcontrChgGuar.deleteRzPrjcontrChgGuarByCondition", rzPrjcontrChgGuarQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgGuar.deleteRzPrjcontrChgGuarByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgGuarById(RzPrjcontrChgGuar rzPrjcontrChgGuar){
		return this.getSqlSession().update("rzPrjcontrChgGuar.updateRzPrjcontrChgGuarById", rzPrjcontrChgGuar)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarList
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarByBatchId(List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList){
		return this.getSqlSession().update("rzPrjcontrChgGuar.updateRzPrjcontrChgGuarByBatchId", rzPrjcontrChgGuarList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgGuarByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgGuar.updateRzPrjcontrChgGuarByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgGuar getRzPrjcontrChgGuarById(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgGuar.getRzPrjcontrChgGuarById",rzPrjcontrChgGuarQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgGuar> getRzPrjcontrChgGuarAll(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgGuar.getRzPrjcontrChgGuarAll",rzPrjcontrChgGuarQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgGuar> getRzPrjcontrChgGuarByPage(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgGuar.getRzPrjcontrChgGuarByPage",rzPrjcontrChgGuarQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgGuarByPageCount(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgGuar.getRzPrjcontrChgGuarByPageCount",rzPrjcontrChgGuarQuery);
	}
}