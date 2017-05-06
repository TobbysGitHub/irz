package com.imfbp.rz.dao.rzeqpt;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;

public interface RzEqptDao{

	/**
	 * 添加
	 * @param rzEqpt
	 * @return
	 */
	public void insertRzEqpt(RzEqpt rzEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzEqpt>
	 * @return
	 */
	public void insertBatchRzEqpt(List<RzEqpt> rzEqptList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean deleteRzEqptById(RzEqptQuery rzEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean deleteRzEqptByCondition(RzEqptQuery rzEqptQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean deleteRzEqptByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean logicDeleteRzEqptById(RzEqptQuery rzEqptQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean logicDeleteRzEqptByCondition(RzEqptQuery rzEqptQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean logicDeleteRzEqptByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean updateRzEqptById(RzEqpt rzEqpt);
	
	/**
	 * 根据Id批量修改
	 * @param rzEqptQuery
	 * @return
	 */
	public boolean updateRzEqptByBatchId(List<RzEqpt> rzEqptList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzEqptByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzEqptQuery
	 * @return
	 */
	public RzEqpt getRzEqptById(RzEqptQuery rzEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzEqptQuery
	 * @return
	 */
	public List<RzEqpt> getRzEqptAll(RzEqptQuery rzEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzEqptQuery
	 * @return
	 */
	public List<RzEqpt> getRzEqptByPage(RzEqptQuery rzEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzEqptQuery
	 * @return
	 */
	public Integer getRzEqptByPageCount(RzEqptQuery rzEqptQuery);
	
}