package com.imfbp.rz.dao.rzadjint;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjint.query.RzAdjIntQuery;

public interface RzAdjIntDao{

	/**
	 * 添加
	 * @param rzAdjInt
	 * @return
	 */
	public void insertRzAdjInt(RzAdjInt rzAdjInt);
	
	/**
	 * 批量添加
	 * @param List<rzAdjInt>
	 * @return
	 */
	public void insertBatchRzAdjInt(List<RzAdjInt> rzAdjIntList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean deleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean deleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean deleteRzAdjIntByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean logicDeleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean logicDeleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean logicDeleteRzAdjIntByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean updateRzAdjIntById(RzAdjInt rzAdjInt);
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntQuery
	 * @return
	 */
	public boolean updateRzAdjIntByBatchId(List<RzAdjInt> rzAdjIntList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzAdjIntByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzAdjIntQuery
	 * @return
	 */
	public RzAdjInt getRzAdjIntById(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 查询所有
	 * @param rzAdjIntQuery
	 * @return
	 */
	public List<RzAdjInt> getRzAdjIntAll(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 分页查询
	 * @param rzAdjIntQuery
	 * @return
	 */
	public List<RzAdjInt> getRzAdjIntByPage(RzAdjIntQuery rzAdjIntQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzAdjIntQuery
	 * @return
	 */
	public Integer getRzAdjIntByPageCount(RzAdjIntQuery rzAdjIntQuery);
	
}