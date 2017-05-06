package com.imfbp.rz.service.rzprjreviewguarb;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjreviewguarb.RzPrjreviewGuarB;
import com.imfbp.rz.domain.rzprjreviewguarb.query.RzPrjreviewGuarBQuery;

public interface RzPrjreviewGuarBService{

	/**
	 * 添加
	 * @param rzPrjreviewGuarB
	 * @return
	 */
	public void insertRzPrjreviewGuarB(RzPrjreviewGuarB rzPrjreviewGuarB);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewGuarB>
	 * @return
	 */
	public Result insertBatchRzPrjreviewGuarB(List<RzPrjreviewGuarB> rzPrjreviewGuarBList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjreviewGuarBById(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewGuarBByCondition(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public Result deleteRzPrjreviewGuarBByBatchId(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewGuarB
	 */
	public Result insertOrUpdate(RzPrjreviewGuarB rzPrjreviewGuarB);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjreviewGuarBById(RzPrjreviewGuarB rzPrjreviewGuarB);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewGuarBByCondition(RzPrjreviewGuarBQuery record,RzPrjreviewGuarBQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjreviewGuarB getRzPrjreviewGuarBById(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public List<RzPrjreviewGuarB> getRzPrjreviewGuarBAll(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public GridResult<RzPrjreviewGuarB> getRzPrjreviewGuarBByPage(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public int getRzPrjreviewGuarBByPageCount(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);

	public Result updateByBatch(List<RzPrjreviewGuarB> editList);
	
	
}