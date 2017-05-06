package com.imfbp.rz.service.rzrecptpmt;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzrecptpmt.RzRecptpmt;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;

public interface RzRecptpmtService{

	/**
	 * 添加
	 * @param rzRecptpmt
	 * @return
	 */
	public void insertRzRecptpmt(RzRecptpmt rzRecptpmt);
	
	/**
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public Result deleteRzRecptpmtByBatchId(RzRecptpmtQuery rzRecptpmtQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
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
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public Result logicDeleteRzRecptpmtByBatchId(RzRecptpmtQuery rzRecptpmtQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzRecptpmt
	 */
	public Result insertOrUpdate(RzRecptpmt rzRecptpmt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzRecptpmtById(RzRecptpmt rzRecptpmt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRecptpmtByCondition(RzRecptpmtQuery record,RzRecptpmtQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzRecptpmt> getRzRecptpmtByPage(RzRecptpmtQuery rzRecptpmtQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRecptpmtQuery
	 * @return
	 */
	public int getRzRecptpmtByPageCount(RzRecptpmtQuery rzRecptpmtQuery);
	//批量更新
	public Result updateByBatchId(RzRecptpmtQuery rzRecptpmt);
}