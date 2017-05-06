package com.imfbp.rz.service.rzrisktype;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzrisktype.RzRiskType;
import com.imfbp.rz.domain.rzrisktype.query.RzRiskTypeQuery;

public interface RzRiskTypeService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public Result deleteRzRiskTypeByBatchId(RzRiskTypeQuery rzRiskTypeQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzRiskType
	 */
	public Result insertOrUpdate(RzRiskType rzRiskType);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzRiskTypeById(RzRiskType rzRiskType);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRiskTypeByCondition(RzRiskTypeQuery record,RzRiskTypeQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public Result updateRzRiskTypeByBatchId(List<RzRiskType> rzRiskTypeList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzRiskType> getRzRiskTypeByPage(RzRiskTypeQuery rzRiskTypeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public int getRzRiskTypeByPageCount(RzRiskTypeQuery rzRiskTypeQuery);
	
	
}