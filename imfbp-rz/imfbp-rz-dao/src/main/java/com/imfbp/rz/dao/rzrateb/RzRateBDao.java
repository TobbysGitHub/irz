package com.imfbp.rz.dao.rzrateb;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;

public interface RzRateBDao{

	/**
	 * 添加
	 * @param rzRateB
	 * @return
	 */
	public void insertRzRateB(RzRateB rzRateB);
	
	/**
	 * 批量添加
	 * @param List<rzRateB>
	 * @return
	 */
	public void insertBatchRzRateB(List<RzRateB> rzRateBList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	public boolean deleteRzRateBById(RzRateBQuery rzRateBQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	public boolean deleteRzRateBByCondition(RzRateBQuery rzRateBQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	public boolean deleteRzRateBByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzRateBQuery
	 * @return
	 */
	public boolean updateRzRateBById(RzRateB rzRateB);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzRateBByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzRateBQuery
	 * @return
	 */
	public RzRateB getRzRateBById(RzRateBQuery rzRateBQuery);
	
	/**
	 * 查询所有
	 * @param rzRateBQuery
	 * @return
	 */
	public List<RzRateB> getRzRateBAll(RzRateBQuery rzRateBQuery);
	
	/**
	 * 分页查询
	 * @param rzRateBQuery
	 * @return
	 */
	public List<RzRateB> getRzRateBByPage(RzRateBQuery rzRateBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzRateBQuery
	 * @return
	 */
	public Integer getRzRateBByPageCount(RzRateBQuery rzRateBQuery);
	
}