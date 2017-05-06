package com.imfbp.rz.dao.rzprjreviewguar;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.rzprjreviewguar.RzPrjreviewGuar;
import com.imfbp.rz.domain.rzprjreviewguar.query.RzPrjreviewGuarQuery;

public interface RzPrjreviewGuarDao{

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
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
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
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public boolean deleteRzPrjreviewGuarByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public boolean updateRzPrjreviewGuarById(RzPrjreviewGuar rzPrjreviewGuar);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzPrjreviewGuarByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewGuarQuery
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
	public List<RzPrjreviewGuar> getRzPrjreviewGuarByPage(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	public Integer getRzPrjreviewGuarByPageCount(RzPrjreviewGuarQuery rzPrjreviewGuarQuery);
	
}