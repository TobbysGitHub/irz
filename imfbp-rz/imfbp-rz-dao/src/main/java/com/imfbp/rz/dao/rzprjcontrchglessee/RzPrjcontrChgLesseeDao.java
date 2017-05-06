package com.imfbp.rz.dao.rzprjcontrchglessee;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchglessee.RzPrjcontrChgLessee;
import com.imfbp.rz.domain.rzprjcontrchglessee.query.RzPrjcontrChgLesseeQuery;

public interface RzPrjcontrChgLesseeDao{

	/**
	 * 添加
	 * @param rzPrjcontrChgLessee
	 * @return
	 */
	public void insertRzPrjcontrChgLessee(RzPrjcontrChgLessee rzPrjcontrChgLessee);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgLessee>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgLessee(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgLesseeByCondition(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgLesseeByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgLesseeById(RzPrjcontrChgLessee rzPrjcontrChgLessee);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgLesseeByBatchId(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgLesseeByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public RzPrjcontrChgLessee getRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public List<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeAll(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public List<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeByPage(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgLesseeByPageCount(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery);
	
}