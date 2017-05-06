package com.imfbp.rz.service.rzoverduederate;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzoverduederate.RzOverdueDerate;
import com.imfbp.rz.domain.rzoverduederate.query.RzOverdueDerateQuery;

public interface RzOverdueDerateService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public Result deleteRzOverdueDerateByBatchId(RzOverdueDerateQuery rzOverdueDerateQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
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
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public Result logicDeleteRzOverdueDerateByBatchId(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzOverdueDerate
	 */
	public Result insertOrUpdate(RzOverdueDerate rzOverdueDerate);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzOverdueDerateById(RzOverdueDerate rzOverdueDerate);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzOverdueDerateByCondition(RzOverdueDerateQuery record,RzOverdueDerateQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public Result updateRzOverdueDerateByBatchId(List<RzOverdueDerate> rzOverdueDerateList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzOverdueDerate> getRzOverdueDerateByPage(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public int getRzOverdueDerateByPageCount(RzOverdueDerateQuery rzOverdueDerateQuery);
	
	
}