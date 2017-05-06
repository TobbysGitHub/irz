package com.imfbp.rz.dao.rzoverduederate;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzoverduederate.RzOverdueDerate;
import com.imfbp.rz.domain.rzoverduederate.query.RzOverdueDerateQuery;

public interface RzOverdueDerateDao{

	/**
	 * 添加
	 * @param rzOverdueDerate
	 * @return
	 */
	public void insertRzOverdueDerate(RzOverdueDerate rzOverdueDerate);
	
	/**
	 * 批量添加
	 * @param List<rzOverdueDerate>
	 * @return
	 */
	public void insertBatchRzOverdueDerate(List<RzOverdueDerate> rzOverdueDerateList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean deleteRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean deleteRzOverdueDerateByCondition(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean deleteRzOverdueDerateByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean logicDeleteRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean logicDeleteRzOverdueDerateByCondition(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean logicDeleteRzOverdueDerateByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean updateRzOverdueDerateById(RzOverdueDerate rzOverdueDerate);
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public boolean updateRzOverdueDerateByBatchId(List<RzOverdueDerate> rzOverdueDerateList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzOverdueDerateByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public RzOverdueDerate getRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 查询所有
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public List<RzOverdueDerate> getRzOverdueDerateAll(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 分页查询
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public List<RzOverdueDerate> getRzOverdueDerateByPage(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public Integer getRzOverdueDerateByPageCount(RzOverdueDerateQuery rzOverdueDerateQuery);
	
}