package com.imfbp.rz.dao.rzprjcontrguar.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrguar.RzPrjcontrGuar;
import com.imfbp.rz.domain.rzprjcontrguar.query.RzPrjcontrGuarQuery;
import com.imfbp.rz.dao.rzprjcontrguar.RzPrjcontrGuarDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrGuarDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrGuarDao{

	/**
	 * 添加
	 * @param rzPrjcontrGuar
	 * @return
	 */
	@Override
	public void insertRzPrjcontrGuar(RzPrjcontrGuar rzPrjcontrGuar){
		this.getSqlSession().insert("rzPrjcontrGuar.insertRzPrjcontrGuar", rzPrjcontrGuar);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrGuar>
	 * @return
	 */
	public void insertBatchRzPrjcontrGuar(List<RzPrjcontrGuar> rzPrjcontrGuarList){
		this.getSqlSession().insert("rzPrjcontrGuar.insertBatchRzPrjcontrGuar", rzPrjcontrGuarList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarById(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return this.getSqlSession().delete("rzPrjcontrGuar.deleteRzPrjcontrGuarById", rzPrjcontrGuarQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarByCondition(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return this.getSqlSession().delete("rzPrjcontrGuar.deleteRzPrjcontrGuarByCondition", rzPrjcontrGuarQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrGuar.deleteRzPrjcontrGuarByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrGuarById(RzPrjcontrGuar rzPrjcontrGuar){
		return this.getSqlSession().update("rzPrjcontrGuar.updateRzPrjcontrGuarById", rzPrjcontrGuar)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrGuarList
	 * @return
	 */
	public boolean updateRzPrjcontrGuarByBatchId(List<RzPrjcontrGuar> rzPrjcontrGuarList){
		return this.getSqlSession().update("rzPrjcontrGuar.updateRzPrjcontrGuarByBatchId", rzPrjcontrGuarList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrGuarByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrGuar.updateRzPrjcontrGuarByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public RzPrjcontrGuar getRzPrjcontrGuarById(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return this.getSqlSession().selectOne("rzPrjcontrGuar.getRzPrjcontrGuarById",rzPrjcontrGuarQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrGuar> getRzPrjcontrGuarAll(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return this.getSqlSession().selectList("rzPrjcontrGuar.getRzPrjcontrGuarAll",rzPrjcontrGuarQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrGuar> getRzPrjcontrGuarByPage(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return this.getSqlSession().selectList("rzPrjcontrGuar.getRzPrjcontrGuarByPage",rzPrjcontrGuarQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrGuarByPageCount(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return this.getSqlSession().selectOne("rzPrjcontrGuar.getRzPrjcontrGuarByPageCount",rzPrjcontrGuarQuery);
	}
}