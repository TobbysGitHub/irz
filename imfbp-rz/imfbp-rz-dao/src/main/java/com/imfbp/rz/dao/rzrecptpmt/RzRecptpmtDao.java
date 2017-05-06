package com.imfbp.rz.dao.rzrecptpmt;

import java.util.Map;

import com.imfbp.rz.domain.rzrecptpmt.RzRecptpmt;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;

import java.util.List;

public interface RzRecptpmtDao{

	/**
	 * 添加
	 * @param rzRecptpmt
	 * @return
	 */
	public void insertRzRecptpmt(RzRecptpmt rzRecptpmt);
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public boolean deleteRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public boolean deleteRzRecptpmtByCondition(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public boolean deleteRzRecptpmtByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public boolean logicDeleteRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public boolean logicDeleteRzRecptpmtByCondition(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public boolean logicDeleteRzRecptpmtByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public boolean updateRzRecptpmtById(RzRecptpmt rzRecptpmt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRecptpmtByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public RzRecptpmt getRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 查询所有
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public List<RzRecptpmt> getRzRecptpmtAll(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 分页查询
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public List<RzRecptpmt> getRzRecptpmtByPage(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public Integer getRzRecptpmtByPageCount(RzRecptpmtQuery rzRecptpmtQuery);
	
	public boolean updateByBatchId(Map<String, Object> data);
	
}