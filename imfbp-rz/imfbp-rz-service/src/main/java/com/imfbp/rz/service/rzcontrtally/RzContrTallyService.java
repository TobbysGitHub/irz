package com.imfbp.rz.service.rzcontrtally;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzcontrtally.RzContrTallyProQueryVo;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;

public interface RzContrTallyService{

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
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */
	public Result deleteRzContrTallyByBatchId(RzContrTallyQuery rzContrTallyQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzContrTally
	 */
	public Result insertOrUpdate(RzContrTally rzContrTally);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzContrTallyById(RzContrTally rzContrTally);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzContrTallyByCondition(RzContrTallyQuery record,RzContrTallyQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzContrTallyQuery
	 * @return
	 */
	public Result updateRzContrTallyByBatchId(List<RzContrTally> rzContrTallyList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzContrTally> getRzContrTallyByPage(RzContrTallyQuery rzContrTallyQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzContrTallyQuery
	 * @return
	 */
	public int getRzContrTallyByPageCount(RzContrTallyQuery rzContrTallyQuery);
	
	public RzContrTally getRzContrTallyLatest(RzContrTallyQuery rzContrTallyQuery);
	
	/**
	 * 台账流水数据添加
	 * 
	 * @param rzContrTally
	 * @return
	 */
	public Result addRzContrTally(RzContrTally rzContrTally);
	
	
	public List<RzContrTallyProQueryVo> getRzContrTallyProQueryAll(RzContrTallyProQueryVo rzContrTallyProQueryVo);
	
	public List<RzContrTallyProQueryVo> getRzContrTallyProQueryByCondition(RzContrTallyProQueryVo rzContrTallyProQueryVo);

	public List<RzContrTally> getRzContrTallyBalance(RzContrTallyQuery rzContrTallyQuery);
}