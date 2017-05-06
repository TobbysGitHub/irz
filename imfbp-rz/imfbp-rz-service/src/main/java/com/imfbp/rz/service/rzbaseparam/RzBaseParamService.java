package com.imfbp.rz.service.rzbaseparam;

import java.util.List;

import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

public interface RzBaseParamService{

	/**
	 * 添加
	 * @param rzBaseParam
	 * @return
	 */
	public void insertRzBaseParam(RzBaseParam rzBaseParam);
	
	/**
	 * 批量添加
	 * @param List<rzBaseParam>
	 * @return
	 */
	public void insertBatchRzBaseParam(List<RzBaseParam> rzBaseParamList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzBaseParamById(RzBaseParamQuery rzBaseParamQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
	 * @return
	 */
	public boolean deleteRzBaseParamByCondition(RzBaseParamQuery rzBaseParamQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
	 * @return
	 */
	public Result deleteRzBaseParamByBatchId(RzBaseParamQuery rzBaseParamQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzBaseParam
	 */
	public Result insertOrUpdate(RzBaseParam rzBaseParam);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzBaseParamById(RzBaseParam rzBaseParam);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzBaseParamByCondition(RzBaseParamQuery record,RzBaseParamQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzBaseParamQuery
	 * @return
	 */
	public Result updateRzBaseParamByBatchId(List<RzBaseParam> rzBaseParamList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzBaseParam getRzBaseParamById(RzBaseParamQuery rzBaseParamQuery);
	
	/**
	 * 查询所有
	 * @param rzBaseParamQuery
	 * @return
	 */
	public List<RzBaseParam> getRzBaseParamAll(RzBaseParamQuery rzBaseParamQuery);
	
	/**
	 * 分页查询
	 * @param rzBaseParamQuery
	 * @return
	 */
	public GridResult<RzBaseParam> getRzBaseParamByPage(RzBaseParamQuery rzBaseParamQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzBaseParamQuery
	 * @return
	 */
	public int getRzBaseParamByPageCount(RzBaseParamQuery rzBaseParamQuery);
	
	
}