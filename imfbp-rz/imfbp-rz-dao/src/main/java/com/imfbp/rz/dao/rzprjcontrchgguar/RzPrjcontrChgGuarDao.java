package com.imfbp.rz.dao.rzprjcontrchgguar;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjcontrchgguar.RzPrjcontrChgGuar;
import com.imfbp.rz.domain.rzprjcontrchgguar.query.RzPrjcontrChgGuarQuery;

public interface RzPrjcontrChgGuarDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public boolean deleteRzPrjcontrChgGuarByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarById(RzPrjcontrChgGuar rzPrjcontrChgGuar);
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarByBatchId(List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjcontrChgGuarByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgGuarQuery
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
	public List<RzPrjcontrChgGuar> getRzPrjcontrChgGuarByPage(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public Integer getRzPrjcontrChgGuarByPageCount(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery);
	
}