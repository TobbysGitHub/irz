package com.imfbp.rz.dao.rzrisktype;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzrisktype.RzRiskType;
import com.imfbp.rz.domain.rzrisktype.query.RzRiskTypeQuery;

public interface RzRiskTypeDao{

	/**
	 * 添加
	 * @param rzRiskType
	 * @return
	 */
	public void insertRzRiskType(RzRiskType rzRiskType);
	
	/**
	 * 批量添加
	 * @param List<rzRiskType>
	 * @return
	 */
	public void insertBatchRzRiskType(List<RzRiskType> rzRiskTypeList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public boolean deleteRzRiskTypeById(RzRiskTypeQuery rzRiskTypeQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public boolean deleteRzRiskTypeByCondition(RzRiskTypeQuery rzRiskTypeQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public boolean deleteRzRiskTypeByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public boolean updateRzRiskTypeById(RzRiskType rzRiskType);
	
	/**
	 * 根据Id批量修改
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public boolean updateRzRiskTypeByBatchId(List<RzRiskType> rzRiskTypeList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRiskTypeByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public RzRiskType getRzRiskTypeById(RzRiskTypeQuery rzRiskTypeQuery);
	
	/**
	 * 查询所有
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public List<RzRiskType> getRzRiskTypeAll(RzRiskTypeQuery rzRiskTypeQuery);
	
	/**
	 * 分页查询
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public List<RzRiskType> getRzRiskTypeByPage(RzRiskTypeQuery rzRiskTypeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public Integer getRzRiskTypeByPageCount(RzRiskTypeQuery rzRiskTypeQuery);
	
}