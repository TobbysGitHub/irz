package com.imfbp.rz.service.rzprjcontrcf;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrcf.RzPrjcontrCf;
import com.imfbp.rz.domain.rzprjcontrcf.query.RzPrjcontrCfQuery;

public interface RzPrjcontrCfService{

	/**
	 * 添加
	 * @param rzPrjcontrCf
	 * @return
	 */
	public void insertRzPrjcontrCf(RzPrjcontrCf rzPrjcontrCf);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrCf>
	 * @return
	 */
	public void insertBatchRzPrjcontrCf(List<RzPrjcontrCf> rzPrjcontrCfList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrCfById(RzPrjcontrCfQuery rzPrjcontrCfQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrCfByCondition(RzPrjcontrCfQuery rzPrjcontrCfQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	public Result deleteRzPrjcontrCfByBatchId(RzPrjcontrCfQuery rzPrjcontrCfQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrCf
	 */
	public Result insertOrUpdate(RzPrjcontrCf rzPrjcontrCf);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrCfById(RzPrjcontrCf rzPrjcontrCf);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrCfByCondition(RzPrjcontrCfQuery record,RzPrjcontrCfQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	public Result updateRzPrjcontrCfByBatchId(List<RzPrjcontrCf> rzPrjcontrCfList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrCf getRzPrjcontrCfById(RzPrjcontrCfQuery rzPrjcontrCfQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	public List<RzPrjcontrCf> getRzPrjcontrCfAll(RzPrjcontrCfQuery rzPrjcontrCfQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	public GridResult<RzPrjcontrCf> getRzPrjcontrCfByPage(RzPrjcontrCfQuery rzPrjcontrCfQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	public int getRzPrjcontrCfByPageCount(RzPrjcontrCfQuery rzPrjcontrCfQuery);
	
	
}