package com.imfbp.rz.dao.rzpricecalcf;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.platform.common.web.result.Result;

public interface RzPricecalCfDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public boolean deleteRzPricecalCfByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public boolean updateRzPricecalCfById(RzPricecalCf rzPricecalCf);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPricecalCfByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPricecalCfQuery
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
	public List<RzPricecalCf> getRzPricecalCfByPage(RzPricecalCfQuery rzPricecalCfQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalCfQuery
	 * @return
	 */
	public Integer getRzPricecalCfByPageCount(RzPricecalCfQuery rzPricecalCfQuery);
	//批量更新
	public boolean updateByBatchId(Map<String, Object> data);
}