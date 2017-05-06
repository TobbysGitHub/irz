package com.imfbp.rz.service.rzassetriskclass;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzassetriskclass.RzAssetRiskclass;
import com.imfbp.rz.domain.rzassetriskclass.query.RzAssetRiskclassQuery;

public interface RzAssetRiskclassService{

	/**
	 * 添加
	 * @param rzAssetRiskclass
	 * @return
	 */
	public void insertRzAssetRiskclass(RzAssetRiskclass rzAssetRiskclass);
	
	/**
	 * 批量添加
	 * @param List<rzAssetRiskclass>
	 * @return
	 */
	public void insertBatchRzAssetRiskclass(List<RzAssetRiskclass> rzAssetRiskclassList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public boolean deleteRzAssetRiskclassByCondition(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public Result deleteRzAssetRiskclassByBatchId(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	public boolean logicDeleteRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public boolean logicDeleteRzAssetRiskclassByCondition(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	/**
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public Result logicDeleteRzAssetRiskclassByBatchId(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzAssetRiskclass
	 */
	public Result insertOrUpdate(RzAssetRiskclass rzAssetRiskclass);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzAssetRiskclassById(RzAssetRiskclass rzAssetRiskclass);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzAssetRiskclassByCondition(RzAssetRiskclassQuery record,RzAssetRiskclassQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public Result updateRzAssetRiskclassByBatchId(List<RzAssetRiskclass> rzAssetRiskclassList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzAssetRiskclass getRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	/**
	 * 查询所有
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public List<RzAssetRiskclass> getRzAssetRiskclassAll(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	/**
	 * 分页查询
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public GridResult<RzAssetRiskclass> getRzAssetRiskclassByPage(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public int getRzAssetRiskclassByPageCount(RzAssetRiskclassQuery rzAssetRiskclassQuery);
	
	
}