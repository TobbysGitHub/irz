package com.imfbp.rz.service.rzrate;

import java.util.List;
import java.util.Map;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;

public interface RzRateService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */
	public Result deleteRzRateByBatchId(RzRateQuery rzRateQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
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
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */
	public Result logicDeleteRzRateByBatchId(RzRateQuery rzRateQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzRate
	 */
	public Result insertOrUpdate(RzRate rzRate);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzRateById(RzRate rzRate);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRateByCondition(RzRateQuery record,RzRateQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzRate> getRzRateByPage(RzRateQuery rzRateQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRateQuery
	 * @return
	 */
	public int getRzRateByPageCount(RzRateQuery rzRateQuery);


	/**
	 * 新增利率主表和子表
	 * @author: zhengjm5
	 * @Date: 2016-11-29 09:31:28
	 * @param map
	 * @return
	 */
	public Result insertRzRate(Map<String, String> map);

	/**
	 * 修改利率主表和子表
	 * @param map
	 * @return
	 */
	public Result updateRzRate(Map<String, String> map);

	/**
	 * 检查时间区间，检查成功后修改其他利率的时间区间
	 * @param rzRate
	 * @return
	 */
	public Result checkPeriod(RzRate rzRate);

}