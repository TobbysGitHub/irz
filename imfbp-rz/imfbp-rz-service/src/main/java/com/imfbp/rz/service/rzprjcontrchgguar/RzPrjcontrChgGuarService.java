package com.imfbp.rz.service.rzprjcontrchgguar;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchgguar.RzPrjcontrChgGuar;
import com.imfbp.rz.domain.rzprjcontrchgguar.query.RzPrjcontrChgGuarQuery;

public interface RzPrjcontrChgGuarService{

	/**
	 * 添加
	 * @param rzPrjcontrChgGuar
	 * @return
	 */
	public void insertRzPrjcontrChgGuar(RzPrjcontrChgGuar rzPrjcontrChgGuar);
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgGuar>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgGuar(List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deleteRzPrjcontrChgGuarById(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgGuarByCondition(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public Result deleteRzPrjcontrChgGuarByBatchId(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgGuar
	 */
	public Result insertOrUpdate(RzPrjcontrChgGuar rzPrjcontrChgGuar);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarById(RzPrjcontrChgGuar rzPrjcontrChgGuar);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarByCondition(RzPrjcontrChgGuarQuery record,RzPrjcontrChgGuarQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgGuarByBatchId(List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public RzPrjcontrChgGuar getRzPrjcontrChgGuarById(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public List<RzPrjcontrChgGuar> getRzPrjcontrChgGuarAll(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public GridResult<RzPrjcontrChgGuar> getRzPrjcontrChgGuarByPage(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public int getRzPrjcontrChgGuarByPageCount(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	
}