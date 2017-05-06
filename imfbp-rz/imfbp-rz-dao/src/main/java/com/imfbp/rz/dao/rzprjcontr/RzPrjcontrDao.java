package com.imfbp.rz.dao.rzprjcontr;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;

public interface RzPrjcontrDao{

	/**
	 * 添加
	 * @param rzPrjcontr
	 * @return
	 */
	public void insertRzPrjcontr(RzPrjcontr rzPrjcontr);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontr>
	 * @return
	 */
	public void insertBatchRzPrjcontr(List<RzPrjcontr> rzPrjcontrList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
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
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public boolean logicDeleteRzPrjcontrByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public boolean updateRzPrjcontrById(RzPrjcontr rzPrjcontr);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public boolean updateRzPrjcontrByBatchId(List<RzPrjcontr> rzPrjcontrList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrQuery
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
	public List<RzPrjcontr> getRzPrjcontrByPage(RzPrjcontrQuery rzPrjcontrQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public Integer getRzPrjcontrByPageCount(RzPrjcontrQuery rzPrjcontrQuery);
	
}