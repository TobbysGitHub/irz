package com.imfbp.rz.service.rzpricecalcf;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;

public interface RzPricecalCfService{

	/**
	 * 添加
	 * @param rzPricecalCf
	 * @return
	 */
	public void insertRzPricecalCf(RzPricecalCf rzPricecalCf);
	
	/**
	 * 批量添加
	 * @param List<rzPricecalCf>
	 * @return
	 */
	public void insertBatchRzPricecalCf(List<RzPricecalCf> rzPricecalCfList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPricecalCfById(RzPricecalCfQuery rzPricecalCfQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public boolean deleteRzPricecalCfByCondition(RzPricecalCfQuery rzPricecalCfQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public Result deleteRzPricecalCfByBatchId(RzPricecalCfQuery rzPricecalCfQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPricecalCf
	 */
	public Result insertOrUpdate(RzPricecalCf rzPricecalCf);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPricecalCfById(RzPricecalCf rzPricecalCf);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalCfByCondition(RzPricecalCfQuery record,RzPricecalCfQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPricecalCf getRzPricecalCfById(RzPricecalCfQuery rzPricecalCfQuery);
	
	/**
	 * 查询所有
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public List<RzPricecalCf> getRzPricecalCfAll(RzPricecalCfQuery rzPricecalCfQuery);
	
	/**
	 * 分页查询
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public GridResult<RzPricecalCf> getRzPricecalCfByPage(RzPricecalCfQuery rzPricecalCfQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public int getRzPricecalCfByPageCount(RzPricecalCfQuery rzPricecalCfQuery);
	
	public Result updateByBatchId(List<RzPricecalCf> list);
}