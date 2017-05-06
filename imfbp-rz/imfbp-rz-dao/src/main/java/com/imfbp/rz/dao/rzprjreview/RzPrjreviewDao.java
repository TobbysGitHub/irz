package com.imfbp.rz.dao.rzprjreview;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;

public interface RzPrjreviewDao{

	/**
	 * 添加
	 * @param rzPrjreview
	 * @return
	 */
	public void insertRzPrjreview(RzPrjreview rzPrjreview);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreview>
	 * @return
	 */
	public void insertBatchRzPrjreview(List<RzPrjreview> rzPrjreviewList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjreviewByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public boolean updateRzPrjreviewById(RzPrjreview rzPrjreview);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public RzPrjreview getRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public List<RzPrjreview> getRzPrjreviewAll(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public List<RzPrjreview> getRzPrjreviewByPage(RzPrjreviewQuery rzPrjreviewQuery);
	
	/**
	* @Title: getRzPrjreviewByFkId 
	* @Description: 查询立项单被引用的审核单
	* @param @param rzPrjreviewQuery
	* @param @return    
	* @return List<RzPrjreview>   
	* @user qinhuimin
	* @date 2016年12月2日下午1:31:28
	* @throws
	 */
	public List<String> getRzPrjreviewByFkId(Map<String,Object> map);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewQuery
	 * @return
	 */
	public Integer getRzPrjreviewByPageCount(RzPrjreviewQuery rzPrjreviewQuery);
	
}