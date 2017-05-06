package com.imfbp.rz.dao.rzprjcontrlessee;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrlessee.RzPrjcontrLessee;
import com.imfbp.rz.domain.rzprjcontrlessee.query.RzPrjcontrLesseeQuery;

public interface RzPrjcontrLesseeDao{

	/**
	 * 添加
	 * @param rzPrjcontrLessee
	 * @return
	 */
	public void insertRzPrjcontrLessee(RzPrjcontrLessee rzPrjcontrLessee);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrLessee>
	 * @return
	 */
	public void insertBatchRzPrjcontrLessee(List<RzPrjcontrLessee> rzPrjcontrLesseeList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrLesseeById(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrLesseeByCondition(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrLesseeByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public boolean updateRzPrjcontrLesseeById(RzPrjcontrLessee rzPrjcontrLessee);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public boolean updateRzPrjcontrLesseeByBatchId(List<RzPrjcontrLessee> rzPrjcontrLesseeList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrLesseeByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public RzPrjcontrLessee getRzPrjcontrLesseeById(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public List<RzPrjcontrLessee> getRzPrjcontrLesseeAll(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public List<RzPrjcontrLessee> getRzPrjcontrLesseeByPage(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public Integer getRzPrjcontrLesseeByPageCount(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery);
	
}