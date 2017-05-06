package com.imfbp.rz.dao.rzprjcontrchglessee.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrchglessee.RzPrjcontrChgLessee;
import com.imfbp.rz.domain.rzprjcontrchglessee.query.RzPrjcontrChgLesseeQuery;
import com.imfbp.rz.dao.rzprjcontrchglessee.RzPrjcontrChgLesseeDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrChgLesseeDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrChgLesseeDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgLessee
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgLessee(RzPrjcontrChgLessee rzPrjcontrChgLessee){
		this.getSqlSession().insert("rzPrjcontrChgLessee.insertRzPrjcontrChgLessee", rzPrjcontrChgLessee);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgLessee>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgLessee(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList){
		this.getSqlSession().insert("rzPrjcontrChgLessee.insertBatchRzPrjcontrChgLessee", rzPrjcontrChgLesseeList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return this.getSqlSession().delete("rzPrjcontrChgLessee.deleteRzPrjcontrChgLesseeById", rzPrjcontrChgLesseeQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgLesseeByCondition(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return this.getSqlSession().delete("rzPrjcontrChgLessee.deleteRzPrjcontrChgLesseeByCondition", rzPrjcontrChgLesseeQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgLesseeByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrChgLessee.deleteRzPrjcontrChgLesseeByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrChgLesseeById(RzPrjcontrChgLessee rzPrjcontrChgLessee){
		return this.getSqlSession().update("rzPrjcontrChgLessee.updateRzPrjcontrChgLesseeById", rzPrjcontrChgLessee)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgLesseeList
	 * @return
	 */
	public boolean updateRzPrjcontrChgLesseeByBatchId(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList){
		return this.getSqlSession().update("rzPrjcontrChgLessee.updateRzPrjcontrChgLesseeByBatchId", rzPrjcontrChgLesseeList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgLesseeByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrChgLessee.updateRzPrjcontrChgLesseeByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgLessee getRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgLessee.getRzPrjcontrChgLesseeById",rzPrjcontrChgLesseeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeAll(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgLessee.getRzPrjcontrChgLesseeAll",rzPrjcontrChgLesseeQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeByPage(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return this.getSqlSession().selectList("rzPrjcontrChgLessee.getRzPrjcontrChgLesseeByPage",rzPrjcontrChgLesseeQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrChgLesseeByPageCount(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return this.getSqlSession().selectOne("rzPrjcontrChgLessee.getRzPrjcontrChgLesseeByPageCount",rzPrjcontrChgLesseeQuery);
	}
}