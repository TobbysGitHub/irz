package com.imfbp.rz.dao.rzbaseparam;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;

public interface RzBaseParamDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
	 * @return
	 */
	public boolean deleteRzBaseParamByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzBaseParamQuery
	 * @return
	 */
	public boolean updateRzBaseParamById(RzBaseParam rzBaseParam);
	
	/**
	 * 根据Id批量修改
	 * @param rzBaseParamQuery
	 * @return
	 */
	public boolean updateRzBaseParamByBatchId(List<RzBaseParam> rzBaseParamList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzBaseParamByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzBaseParamQuery
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
	public List<RzBaseParam> getRzBaseParamByPage(RzBaseParamQuery rzBaseParamQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzBaseParamQuery
	 * @return
	 */
	public Integer getRzBaseParamByPageCount(RzBaseParamQuery rzBaseParamQuery);
	
}