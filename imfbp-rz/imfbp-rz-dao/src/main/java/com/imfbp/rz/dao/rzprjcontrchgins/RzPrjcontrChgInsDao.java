package com.imfbp.rz.dao.rzprjcontrchgins;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchgins.RzPrjcontrChgIns;
import com.imfbp.rz.domain.rzprjcontrchgins.query.RzPrjcontrChgInsQuery;

public interface RzPrjcontrChgInsDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgIns
	 * @return
	 */
	public void insertRzPrjcontrChgIns(RzPrjcontrChgIns rzPrjcontrChgIns);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgIns(List<RzPrjcontrChgIns> rzPrjcontrChgInsList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsByCondition(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgInsByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsById(RzPrjcontrChgIns rzPrjcontrChgIns);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsByBatchId(List<RzPrjcontrChgIns> rzPrjcontrChgInsList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgInsByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public RzPrjcontrChgIns getRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public List<RzPrjcontrChgIns> getRzPrjcontrChgInsAll(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public List<RzPrjcontrChgIns> getRzPrjcontrChgInsByPage(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgInsByPageCount(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery);
	
}