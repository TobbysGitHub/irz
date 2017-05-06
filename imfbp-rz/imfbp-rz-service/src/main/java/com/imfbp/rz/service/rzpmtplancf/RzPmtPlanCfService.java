package com.imfbp.rz.service.rzpmtplancf;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;

public interface RzPmtPlanCfService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public Result deleteRzPmtPlanCfByBatchId(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPmtPlanCf
	 */
	public Result insertOrUpdate(RzPmtPlanCf rzPmtPlanCf);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPmtPlanCfById(RzPmtPlanCf rzPmtPlanCf);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPmtPlanCfByCondition(RzPmtPlanCfQuery record,RzPmtPlanCfQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public Result updateRzPmtPlanCfByBatchId(List<RzPmtPlanCf> rzPmtPlanCfList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzPmtPlanCf> getRzPmtPlanCfByPage(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public int getRzPmtPlanCfByPageCount(RzPmtPlanCfQuery rzPmtPlanCfQuery);
	
	public void insertToTally(RzPmtPlanCf rzPmtPlanCf);
	
	//public void checkOut(RzPmtPlanCf rzPmtPlanCf,@RequestParam("data") String data,RzPmtPlanCfQuery rzPmtPlanCf1);
}