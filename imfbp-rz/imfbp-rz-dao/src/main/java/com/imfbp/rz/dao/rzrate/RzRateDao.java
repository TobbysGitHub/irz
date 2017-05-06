package com.imfbp.rz.dao.rzrate;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;

public interface RzRateDao{

	/**
	 * 添加
	 * @param rzRate
	 * @return
	 */
	public void insertRzRate(RzRate rzRate);
	
	/**
	 * 批量添加
	 * @param List<rzRate>
	 * @return
	 */
	public void insertBatchRzRate(List<RzRate> rzRateList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */
	public boolean deleteRzRateById(RzRateQuery rzRateQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */
	public boolean deleteRzRateByCondition(RzRateQuery rzRateQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */
	public boolean deleteRzRateByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */
	public boolean logicDeleteRzRateById(RzRateQuery rzRateQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */
	public boolean logicDeleteRzRateByCondition(RzRateQuery rzRateQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */
	public boolean logicDeleteRzRateByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzRateQuery
	 * @return
	 */
	public boolean updateRzRateById(RzRate rzRate);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRateByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzRateQuery
	 * @return
	 */
	public RzRate getRzRateById(RzRateQuery rzRateQuery);
	
	/**
	 * 查询所有
	 * @param rzRateQuery
	 * @return
	 */
	public List<RzRate> getRzRateAll(RzRateQuery rzRateQuery);
	
	/**
	 * 分页查询
	 * @param rzRateQuery
	 * @return
	 */
	public List<RzRate> getRzRateByPage(RzRateQuery rzRateQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRateQuery
	 * @return
	 */
	public Integer getRzRateByPageCount(RzRateQuery rzRateQuery);
	
}