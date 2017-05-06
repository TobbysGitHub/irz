package com.imfbp.rz.dao.rzprjstate;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.domain.rzprjstate.query.RzPrjStateQuery;

public interface RzPrjStateDao{

	/**
	 * 添加
	 * @param rzPrjState
	 * @return
	 */
	public void insertRzPrjState(RzPrjState rzPrjState);
	
	/**
	 * 批量添加
	 * @param List<rzPrjState>
	 * @return
	 */
	public void insertBatchRzPrjState(List<RzPrjState> rzPrjStateList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	public boolean deleteRzPrjStateById(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	public boolean deleteRzPrjStateByCondition(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	public boolean deleteRzPrjStateByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjStateQuery
	 * @return
	 */
	public boolean updateRzPrjStateById(RzPrjState rzPrjState);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjStateQuery
	 * @return
	 */
	public boolean updateRzPrjStateByBatchId(List<RzPrjState> rzPrjStateList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjStateByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjStateQuery
	 * @return
	 */
	public RzPrjState getRzPrjStateById(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjStateQuery
	 * @return
	 */
	public List<RzPrjState> getRzPrjStateAll(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjStateQuery
	 * @return
	 */
	public List<RzPrjState> getRzPrjStateByPage(RzPrjStateQuery rzPrjStateQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjStateQuery
	 * @return
	 */
	public Integer getRzPrjStateByPageCount(RzPrjStateQuery rzPrjStateQuery);
	
}