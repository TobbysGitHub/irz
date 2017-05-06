package com.imfbp.rz.service.rzprjreviewguar;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjreviewguar.RzPrjreviewGuar;
import com.imfbp.rz.domain.rzprjreviewguar.query.RzPrjreviewGuarQuery;

public interface RzPrjreviewGuarService{

	/**
	 * 添加
	 * @param rzPrjreviewGuar
	 * @return
	 */
	public void insertRzPrjreviewGuar(RzPrjreviewGuar rzPrjreviewGuar);
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewGuar>
	 * @return
	 */
	public void insertBatchRzPrjreviewGuar(List<RzPrjreviewGuar> rzPrjreviewGuarList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjreviewGuarById(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewGuarByCondition(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public Result deleteRzPrjreviewGuarByBatchId(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewGuar
	 */
	public Result insertOrUpdate(RzPrjreviewGuar rzPrjreviewGuar);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjreviewGuarById(RzPrjreviewGuar rzPrjreviewGuar);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewGuarByCondition(RzPrjreviewGuarQuery record,RzPrjreviewGuarQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjreviewGuar getRzPrjreviewGuarById(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public List<RzPrjreviewGuar> getRzPrjreviewGuarAll(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public GridResult<RzPrjreviewGuar> getRzPrjreviewGuarByPage(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public int getRzPrjreviewGuarByPageCount(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	
}