package com.imfbp.rz.service.rzpricecal;

import java.util.List;

import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

public interface RzPricecalService{

	/**
	 * 添加
	 * @param rzPricecal
	 * @return
	 */
	public void insertRzPricecal(RzPricecal rzPricecal);
	
	/**
	 * 批量添加
	 * @param List<rzPricecal>
	 * @return
	 */
	public void insertBatchRzPricecal(List<RzPricecal> rzPricecalList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPricecalById(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean deleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	public Result deleteRzPricecalByBatchId(RzPricecalQuery rzPricecalQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzPricecalById(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	public boolean logicDeleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	public Result logicDeleteRzPricecalByBatchId(RzPricecalQuery rzPricecalQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPricecal
	 */
	public Result insertOrUpdate(RzPricecal rzPricecal);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPricecalById(RzPricecal rzPricecal);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalByCondition(RzPricecalQuery record,RzPricecalQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPricecal getRzPricecalById(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 查询所有
	 * @param rzPricecalQuery
	 * @return
	 */
	public List<RzPricecal> getRzPricecalAll(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 分页查询
	 * @param rzPricecalQuery
	 * @return
	 */
	public GridResult<RzPricecal> getRzPricecalByPage(RzPricecalQuery rzPricecalQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalQuery
	 * @return
	 */
	public int getRzPricecalByPageCount(RzPricecalQuery rzPricecalQuery);
	//获取所有设备信息
	public List<RzEqpt>getAllRzEqpt(RzPricecalQuery rzPricecalQuery );
	//批量更新
	public Result updateByBatchId(RzPricecalQuery rzPricecalQuery);
	
	public boolean updateRzPricecalByState(RzPricecal rzPricecal);
	
	public List<RzPricecal>getTranslateData(List<RzPricecal>rzPricecalList,String str, RefBaseQuery query);
}