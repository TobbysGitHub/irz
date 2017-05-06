package com.imfbp.rz.dao.rzpmtplancf;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;

public interface RzPmtPlanCfDao{

	/**
	 * 添加
	 * @param rzPmtPlanCf
	 * @return
	 */
	public void insertRzPmtPlanCf(RzPmtPlanCf rzPmtPlanCf);
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlanCf>
	 * @return
	 */
	public void insertBatchRzPmtPlanCf(List<RzPmtPlanCf> rzPmtPlanCfList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public boolean deleteRzPmtPlanCfById(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public boolean deleteRzPmtPlanCfByCondition(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public boolean deleteRzPmtPlanCfByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public boolean updateRzPmtPlanCfById(RzPmtPlanCf rzPmtPlanCf);
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public boolean updateRzPmtPlanCfByBatchId(List<RzPmtPlanCf> rzPmtPlanCfList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPmtPlanCfByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public RzPmtPlanCf getRzPmtPlanCfById(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	/**
	 * 查询所有
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public List<RzPmtPlanCf> getRzPmtPlanCfAll(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	/**
	 * 分页查询
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public List<RzPmtPlanCf> getRzPmtPlanCfByPage(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public Integer getRzPmtPlanCfByPageCount(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
}