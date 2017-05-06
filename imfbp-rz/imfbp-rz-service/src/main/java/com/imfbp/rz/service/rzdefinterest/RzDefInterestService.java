package com.imfbp.rz.service.rzdefinterest;

import java.util.List;
import java.util.Map;


import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterest.query.RzDefInterestQuery;

public interface RzDefInterestService{

	/**
	 * 添加
	 * @param rzDefInterest
	 * @return
	 */
	public void insertRzDefInterest(RzDefInterest rzDefInterest);
	
	/**
	 * 批量添加
	 * @param List<rzDefInterest>
	 * @return
	 */
	public void insertBatchRzDefInterest(List<RzDefInterest> rzDefInterestList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean deleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public Result deleteRzDefInterestByBatchId(RzDefInterestQuery rzDefInterestQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public boolean logicDeleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	public Result logicDeleteRzDefInterestByBatchId(RzDefInterestQuery rzDefInterestQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzDefInterest
	 */
	public Result insertOrUpdate(RzDefInterest rzDefInterest);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzDefInterestById(RzDefInterest rzDefInterest);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzDefInterestByCondition(RzDefInterestQuery record,RzDefInterestQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestQuery
	 * @return
	 */
	public Result updateRzDefInterestByBatchId(List<RzDefInterest> rzDefInterestList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzDefInterest getRzDefInterestById(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 查询所有
	 * @param rzDefInterestQuery
	 * @return
	 */
	public List<RzDefInterest> getRzDefInterestAll(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 分页查询
	 * @param rzDefInterestQuery
	 * @return
	 */
	public GridResult<RzDefInterest> getRzDefInterestByPage(RzDefInterestQuery rzDefInterestQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzDefInterestQuery
	 * @return
	 */
	public int getRzDefInterestByPageCount(RzDefInterestQuery rzDefInterestQuery);
	//根据合同的主键获取收付管理主键，从而获取租金计划表主键信息
	public Map<String,Object> getLeaseByContr(RzDefInterestQuery rzDefInterestQuery);
	public RzDefInterestQuery getAllDeleteIds(List<RzDefInterest>list);
	//根据合同的主键获取收付管理主键，从而获取租金计划表主键信息,以及构建逾期计划表子表数据
	public Map<String,Object> getPlanByContr(RzDefInterestQuery rzDefInterestQuery);
	//获取所有有逾期记录的合同
	public List<String>getContrByDef(RzDefInterestQuery rzDefInterestQuery);
	//获取所有有逾期记录的合同
	public List<String>getContrByDefEdit(RzDefInterestQuery rzDefInterestQuery);
}