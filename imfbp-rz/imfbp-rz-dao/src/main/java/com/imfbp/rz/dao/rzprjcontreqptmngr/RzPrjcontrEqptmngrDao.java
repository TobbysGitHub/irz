package com.imfbp.rz.dao.rzprjcontreqptmngr;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontreqptmngr.RzPrjcontrEqptmngr;
import com.imfbp.rz.domain.rzprjcontreqptmngr.query.RzPrjcontrEqptmngrQuery;

public interface RzPrjcontrEqptmngrDao{

	/**
	 * 添加
	 * @param rzPrjcontrEqptmngr
	 * @return
	 */
	public void insertRzPrjcontrEqptmngr(RzPrjcontrEqptmngr rzPrjcontrEqptmngr);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrEqptmngr>
	 * @return
	 */
	public void insertBatchRzPrjcontrEqptmngr(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrEqptmngrByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrEqptmngrByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean updateRzPrjcontrEqptmngrById(RzPrjcontrEqptmngr rzPrjcontrEqptmngr);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public boolean updateRzPrjcontrEqptmngrByBatchId(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrEqptmngrByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public RzPrjcontrEqptmngr getRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public List<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrAll(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public List<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrByPage(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public Integer getRzPrjcontrEqptmngrByPageCount(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery);
	
}