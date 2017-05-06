package com.imfbp.rz.dao.rzprjreviewguar.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjreviewguar.RzPrjreviewGuar;
import com.imfbp.rz.domain.rzprjreviewguar.query.RzPrjreviewGuarQuery;
import com.imfbp.rz.dao.rzprjreviewguar.RzPrjreviewGuarDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjreviewGuarDaoImpl extends SqlSessionDaoSupport implements RzPrjreviewGuarDao{

	/**
	 * 添加
	 * @param rzPrjreviewGuar
	 * @return
	 */
	@Override
	public void insertRzPrjreviewGuar(RzPrjreviewGuar rzPrjreviewGuar){
		this.getSqlSession().insert("rzPrjreviewGuar.insertRzPrjreviewGuar", rzPrjreviewGuar);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewGuar>
	 * @return
	 */
	public void insertBatchRzPrjreviewGuar(List<RzPrjreviewGuar> rzPrjreviewGuarList){
		this.getSqlSession().insert("rzPrjreviewGuar.insertBatchRzPrjreviewGuar", rzPrjreviewGuarList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarById(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return this.getSqlSession().delete("rzPrjreviewGuar.deleteRzPrjreviewGuarById", rzPrjreviewGuarQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarByCondition(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return this.getSqlSession().delete("rzPrjreviewGuar.deleteRzPrjreviewGuarById", rzPrjreviewGuarQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjreviewGuar.deleteRzPrjreviewGuarByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjreviewGuarById(RzPrjreviewGuar rzPrjreviewGuar){
		return this.getSqlSession().update("rzPrjreviewGuar.updateRzPrjreviewGuarById", rzPrjreviewGuar)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewGuarByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjreviewGuar.updateRzPrjreviewGuarByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public RzPrjreviewGuar getRzPrjreviewGuarById(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return this.getSqlSession().selectOne("rzPrjreviewGuar.getRzPrjreviewGuarById",rzPrjreviewGuarQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewGuar> getRzPrjreviewGuarAll(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return this.getSqlSession().selectList("rzPrjreviewGuar.getRzPrjreviewGuarAll",rzPrjreviewGuarQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjreviewGuar> getRzPrjreviewGuarByPage(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return this.getSqlSession().selectList("rzPrjreviewGuar.getRzPrjreviewGuarByPage",rzPrjreviewGuarQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjreviewGuarByPageCount(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return this.getSqlSession().selectOne("rzPrjreviewGuar.getRzPrjreviewGuarByPageCount",rzPrjreviewGuarQuery);
	}
}