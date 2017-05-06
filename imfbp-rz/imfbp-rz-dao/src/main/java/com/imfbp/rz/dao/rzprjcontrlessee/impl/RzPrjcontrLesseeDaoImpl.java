package com.imfbp.rz.dao.rzprjcontrlessee.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzprjcontrlessee.RzPrjcontrLessee;
import com.imfbp.rz.domain.rzprjcontrlessee.query.RzPrjcontrLesseeQuery;
import com.imfbp.rz.dao.rzprjcontrlessee.RzPrjcontrLesseeDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzPrjcontrLesseeDaoImpl extends SqlSessionDaoSupport implements RzPrjcontrLesseeDao{

	/**
	 * 添加
	 * @param rzPrjcontrLessee
	 * @return
	 */
	@Override
	public void insertRzPrjcontrLessee(RzPrjcontrLessee rzPrjcontrLessee){
		this.getSqlSession().insert("rzPrjcontrLessee.insertRzPrjcontrLessee", rzPrjcontrLessee);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrLessee>
	 * @return
	 */
	public void insertBatchRzPrjcontrLessee(List<RzPrjcontrLessee> rzPrjcontrLesseeList){
		this.getSqlSession().insert("rzPrjcontrLessee.insertBatchRzPrjcontrLessee", rzPrjcontrLesseeList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLesseeById(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return this.getSqlSession().delete("rzPrjcontrLessee.deleteRzPrjcontrLesseeById", rzPrjcontrLesseeQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLesseeByCondition(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return this.getSqlSession().delete("rzPrjcontrLessee.deleteRzPrjcontrLesseeByCondition", rzPrjcontrLesseeQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLesseeByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzPrjcontrLessee.deleteRzPrjcontrLesseeByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */	
	@Override
	public boolean updateRzPrjcontrLesseeById(RzPrjcontrLessee rzPrjcontrLessee){
		return this.getSqlSession().update("rzPrjcontrLessee.updateRzPrjcontrLesseeById", rzPrjcontrLessee)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrLesseeList
	 * @return
	 */
	public boolean updateRzPrjcontrLesseeByBatchId(List<RzPrjcontrLessee> rzPrjcontrLesseeList){
		return this.getSqlSession().update("rzPrjcontrLessee.updateRzPrjcontrLesseeByBatchId", rzPrjcontrLesseeList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrLesseeByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzPrjcontrLessee.updateRzPrjcontrLesseeByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public RzPrjcontrLessee getRzPrjcontrLesseeById(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return this.getSqlSession().selectOne("rzPrjcontrLessee.getRzPrjcontrLesseeById",rzPrjcontrLesseeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrLessee> getRzPrjcontrLesseeAll(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return this.getSqlSession().selectList("rzPrjcontrLessee.getRzPrjcontrLesseeAll",rzPrjcontrLesseeQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzPrjcontrLessee> getRzPrjcontrLesseeByPage(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return this.getSqlSession().selectList("rzPrjcontrLessee.getRzPrjcontrLesseeByPage",rzPrjcontrLesseeQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzPrjcontrLesseeByPageCount(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return this.getSqlSession().selectOne("rzPrjcontrLessee.getRzPrjcontrLesseeByPageCount",rzPrjcontrLesseeQuery);
	}
}