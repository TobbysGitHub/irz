package com.imfbp.rz.dao.rzrisktype.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzrisktype.RzRiskType;
import com.imfbp.rz.domain.rzrisktype.query.RzRiskTypeQuery;
import com.imfbp.rz.dao.rzrisktype.RzRiskTypeDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzRiskTypeDaoImpl extends SqlSessionDaoSupport implements RzRiskTypeDao{

	/**
	 * 添加
	 * @param rzRiskType
	 * @return
	 */
	@Override
	public void insertRzRiskType(RzRiskType rzRiskType){
		this.getSqlSession().insert("rzRiskType.insertRzRiskType", rzRiskType);
	}
	
	/**
	 * 批量添加
	 * @param List<rzRiskType>
	 * @return
	 */
	public void insertBatchRzRiskType(List<RzRiskType> rzRiskTypeList){
		this.getSqlSession().insert("rzRiskType.insertBatchRzRiskType", rzRiskTypeList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRiskTypeById(RzRiskTypeQuery rzRiskTypeQuery){
		return this.getSqlSession().delete("rzRiskType.deleteRzRiskTypeById", rzRiskTypeQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRiskTypeByCondition(RzRiskTypeQuery rzRiskTypeQuery){
		return this.getSqlSession().delete("rzRiskType.deleteRzRiskTypeByCondition", rzRiskTypeQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzRiskTypeByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzRiskType.deleteRzRiskTypeByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzRiskTypeQuery
	 * @return
	 */	
	@Override
	public boolean updateRzRiskTypeById(RzRiskType rzRiskType){
		return this.getSqlSession().update("rzRiskType.updateRzRiskTypeById", rzRiskType)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzRiskTypeList
	 * @return
	 */
	public boolean updateRzRiskTypeByBatchId(List<RzRiskType> rzRiskTypeList){
		return this.getSqlSession().update("rzRiskType.updateRzRiskTypeByBatchId", rzRiskTypeList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRiskTypeByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzRiskType.updateRzRiskTypeByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public RzRiskType getRzRiskTypeById(RzRiskTypeQuery rzRiskTypeQuery){
		return this.getSqlSession().selectOne("rzRiskType.getRzRiskTypeById",rzRiskTypeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public List<RzRiskType> getRzRiskTypeAll(RzRiskTypeQuery rzRiskTypeQuery){
		return this.getSqlSession().selectList("rzRiskType.getRzRiskTypeAll",rzRiskTypeQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzRiskType> getRzRiskTypeByPage(RzRiskTypeQuery rzRiskTypeQuery){
		return this.getSqlSession().selectList("rzRiskType.getRzRiskTypeByPage",rzRiskTypeQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzRiskTypeByPageCount(RzRiskTypeQuery rzRiskTypeQuery){
		return this.getSqlSession().selectOne("rzRiskType.getRzRiskTypeByPageCount",rzRiskTypeQuery);
	}
}