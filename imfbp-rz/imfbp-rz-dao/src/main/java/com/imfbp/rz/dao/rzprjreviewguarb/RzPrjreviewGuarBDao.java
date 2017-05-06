package com.imfbp.rz.dao.rzprjreviewguarb;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjreviewguarb.RzPrjreviewGuarB;
import com.imfbp.rz.domain.rzprjreviewguarb.query.RzPrjreviewGuarBQuery;

public interface RzPrjreviewGuarBDao{

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
	public void insertBatchRzPrjreviewGuarB(List<RzPrjreviewGuarB> rzPrjreviewGuarBList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewGuarBByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public boolean updateRzPrjreviewGuarBById(RzPrjreviewGuarB rzPrjreviewGuarB);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewGuarBByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewGuarBQuery
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
	public List<RzPrjreviewGuarB> getRzPrjreviewGuarBByPage(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	public Integer getRzPrjreviewGuarBByPageCount(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery);

	public boolean updateBatch(List<RzPrjreviewGuarB> editList);
	
}