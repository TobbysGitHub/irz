package com.imfbp.rz.dao.rzcontrtally;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;

public interface RzContrTallyDao{

	/**
	 * 添加
	 * @param rzContrTally
	 * @return
	 */
	public void insertRzContrTally(RzContrTally rzContrTally);
	
	/**
	 * 批量添加
	 * @param List<rzContrTally>
	 * @return
	 */
	public void insertBatchRzContrTally(List<RzContrTally> rzContrTallyList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */
	public boolean deleteRzContrTallyById(RzContrTallyQuery rzContrTallyQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */
	public boolean deleteRzContrTallyByCondition(RzContrTallyQuery rzContrTallyQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */
	public boolean deleteRzContrTallyByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzContrTallyQuery
	 * @return
	 */
	public boolean updateRzContrTallyById(RzContrTally rzContrTally);
	
	/**
	 * 根据Id批量修改
	 * @param rzContrTallyQuery
	 * @return
	 */
	public boolean updateRzContrTallyByBatchId(List<RzContrTally> rzContrTallyList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzContrTallyByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzContrTallyQuery
	 * @return
	 */
	public RzContrTally getRzContrTallyById(RzContrTallyQuery rzContrTallyQuery);
	
	/**
	 * 查询所有
	 * @param rzContrTallyQuery
	 * @return
	 */
	public List<RzContrTally> getRzContrTallyAll(RzContrTallyQuery rzContrTallyQuery);
	
	/**
	 * 分页查询
	 * @param rzContrTallyQuery
	 * @return
	 */
	public List<RzContrTally> getRzContrTallyByPage(RzContrTallyQuery rzContrTallyQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzContrTallyQuery
	 * @return
	 */
	public Integer getRzContrTallyByPageCount(RzContrTallyQuery rzContrTallyQuery);
	
	
	/**
	 * 查询最大序号台账数据 
	 * @param rzContrTallyQuery
	 * @return
	 */
	public List<RzContrTally> getRzContrTallyMaxOperSeq(RzContrTallyQuery rzContrTallyQuery);
	
	/*****
	 * 项目合同余额台账
	 * @param rzContrTallyQuery
	 * @return
	 */
	public List<RzContrTally> getRzContrTallyBalance(RzContrTallyQuery rzContrTallyQuery);
	
	
}