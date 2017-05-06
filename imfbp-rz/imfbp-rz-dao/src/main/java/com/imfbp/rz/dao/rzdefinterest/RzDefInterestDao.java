package com.imfbp.rz.dao.rzdefinterest;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterest.query.RzDefInterestQuery;

public interface RzDefInterestDao{

	/**
	 * 添加
	 * @param rzDefInterest
	 * @return
	 */
	public void insertRzDefInterest(RzDefInterest rzDefInterest);
	
	/**
	 * 批量添加
	 * @param List<rzDefInterest>
	 * @return
	 */
	public void insertBatchRzDefInterest(List<RzDefInterest> rzDefInterestList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean deleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean deleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean deleteRzDefInterestByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean logicDeleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean logicDeleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean logicDeleteRzDefInterestByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean updateRzDefInterestById(RzDefInterest rzDefInterest);
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean updateRzDefInterestByBatchId(List<RzDefInterest> rzDefInterestList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzDefInterestByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzDefInterestQuery
	 * @return
	 */
	public RzDefInterest getRzDefInterestById(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 查询所有
	 * @param rzDefInterestQuery
	 * @return
	 */
	public List<RzDefInterest> getRzDefInterestAll(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 分页查询
	 * @param rzDefInterestQuery
	 * @return
	 */
	public List<RzDefInterest> getRzDefInterestByPage(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzDefInterestQuery
	 * @return
	 */
	public Integer getRzDefInterestByPageCount(RzDefInterestQuery rzDefInterestQuery);
	
}