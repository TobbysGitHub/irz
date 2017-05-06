package com.imfbp.rz.dao.rzprjreviewlessee.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewlessee.query.RzPrjreviewLesseeQuery;
import com.imfbp.rz.dao.rzprjreviewlessee.RzPrjreviewLesseeDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjreviewLesseeDaoImpl extends SqlSessionDaoSupport implements RzPrjreviewLesseeDao{

	/**
	 * 添加
	 * @param rzPrjreviewLessee
	 * @return
	 */
	@Override
	public void insertRzPrjreviewLessee(RzPrjreviewLessee rzPrjreviewLessee){
		this.getSqlSession().insert("rzPrjreviewLessee.insertRzPrjreviewLessee", rzPrjreviewLessee);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewLessee>
	 * @return
	 */
	public void insertBatchRzPrjreviewLessee(List<RzPrjreviewLessee> rzPrjreviewLesseeList){
		this.getSqlSession().insert("rzPrjreviewLessee.insertBatchRzPrjreviewLessee", rzPrjreviewLesseeList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewLesseeById(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery){
		return this.getSqlSession().delete("rzPrjreviewLessee.deleteRzPrjreviewLesseeById", rzPrjreviewLesseeQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewLesseeByCondition(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery){
		return this.getSqlSession().delete("rzPrjreviewLessee.deleteRzPrjreviewLesseeById", rzPrjreviewLesseeQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewLesseeByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjreviewLessee.deleteRzPrjreviewLesseeByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjreviewLesseeById(RzPrjreviewLessee rzPrjreviewLessee){
		return this.getSqlSession().update("rzPrjreviewLessee.updateRzPrjreviewLesseeById", rzPrjreviewLessee)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewLesseeByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjreviewLessee.updateRzPrjreviewLesseeByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public RzPrjreviewLessee getRzPrjreviewLesseeById(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery){
		return this.getSqlSession().selectOne("rzPrjreviewLessee.getRzPrjreviewLesseeById",rzPrjreviewLesseeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewLessee> getRzPrjreviewLesseeAll(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery){
		return this.getSqlSession().selectList("rzPrjreviewLessee.getRzPrjreviewLesseeAll",rzPrjreviewLesseeQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjreviewLessee> getRzPrjreviewLesseeByPage(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery){
		return this.getSqlSession().selectList("rzPrjreviewLessee.getRzPrjreviewLesseeByPage",rzPrjreviewLesseeQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjreviewLesseeByPageCount(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery){
		return this.getSqlSession().selectOne("rzPrjreviewLessee.getRzPrjreviewLesseeByPageCount",rzPrjreviewLesseeQuery);
	}
}