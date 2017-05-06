package com.imfbp.rz.service.rzpricecaleqpt;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;

public interface RzPricecalEqptService{

	/**
	 * 添加
	 * @param rzPricecalEqpt
	 * @return
	 */
	public void insertRzPricecalEqpt(RzPricecalEqpt rzPricecalEqpt);
	
	/**
	 * 批量添加
	 * @param List<rzPricecalEqpt>
	 * @return
	 */
	public void insertBatchRzPricecalEqpt(List<RzPricecalEqpt> rzPricecalEqptList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public boolean deleteRzPricecalEqptByCondition(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public Result deleteRzPricecalEqptByBatchId(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPricecalEqpt
	 */
	public Result insertOrUpdate(RzPricecalEqpt rzPricecalEqpt);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPricecalEqptById(RzPricecalEqpt rzPricecalEqpt);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalEqptByCondition(RzPricecalEqptQuery record,RzPricecalEqptQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPricecalEqpt getRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 查询所有
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public List<RzPricecalEqpt> getRzPricecalEqptAll(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 分页查询
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public GridResult<RzPricecalEqpt> getRzPricecalEqptByPage(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	public int getRzPricecalEqptByPageCount(RzPricecalEqptQuery rzPricecalEqptQuery);
	
	public Result updateByBatchId(List<RzPricecalEqpt> list);
}