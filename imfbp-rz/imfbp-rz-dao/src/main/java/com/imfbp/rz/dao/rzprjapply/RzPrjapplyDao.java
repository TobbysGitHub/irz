package com.imfbp.rz.dao.rzprjapply;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;

public interface RzPrjapplyDao{

	/**
	 * 添加
	 * @param rzPrjapply
	 * @return
	 */
	public void insertRzPrjapply(RzPrjapply rzPrjapply);
	
	/**
	 * 批量添加
	 * @param List<rzPrjapply>
	 * @return
	 */
	public void insertBatchRzPrjapply(List<RzPrjapply> rzPrjapplyList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean deleteRzPrjapplyByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjapplyByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public boolean updateRzPrjapplyById(RzPrjapply rzPrjapply);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjapplyByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public RzPrjapply getRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public List<RzPrjapply> getRzPrjapplyAll(RzPrjapplyQuery rzPrjapplyQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public List<RzPrjapply> getRzPrjapplyByPage(RzPrjapplyQuery rzPrjapplyQuery);
	
	public List<RzPrjapply> getRzPrjapplyBatchId(List<String> list);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjapplyQuery
	 * @return
	 */
	public Integer getRzPrjapplyByPageCount(RzPrjapplyQuery rzPrjapplyQuery);
	
}