package com.imfbp.rz.service.rzprjcontr;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;

public interface RzPrjcontrService{

	/**
	 * 添加
	 * @param rzPrjcontr
	 * @return
	 */
	public void insertRzPrjcontr(RzPrjcontr rzPrjcontr, String tenantId);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontr>
	 * @return
	 */
	public void insertBatchRzPrjcontr(List<RzPrjcontr> rzPrjcontrList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrByCondition(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public Result deleteRzPrjcontrByBatchId(RzPrjcontrQuery rzPrjcontrQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrByCondition(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public Result logicDeleteRzPrjcontrByBatchId(RzPrjcontrQuery rzPrjcontrQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontr
	 */
	public Result insertOrUpdate(RzPrjcontr rzPrjcontr, String tenantId);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrById(RzPrjcontr rzPrjcontr);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrByCondition(RzPrjcontrQuery record,RzPrjcontrQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public Result updateRzPrjcontrByBatchId(List<RzPrjcontr> rzPrjcontrList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontr getRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public List<RzPrjcontr> getRzPrjcontrAll(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public GridResult<RzPrjcontr> getRzPrjcontrByPage(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public int getRzPrjcontrByPageCount(RzPrjcontrQuery rzPrjcontrQuery);
	
	
}