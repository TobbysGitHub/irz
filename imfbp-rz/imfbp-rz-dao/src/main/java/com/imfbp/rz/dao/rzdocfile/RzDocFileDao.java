package com.imfbp.rz.dao.rzdocfile;

import java.util.Map;

import com.imfbp.rz.domain.rzdocfile.RzDocFile;
import com.imfbp.rz.domain.rzdocfile.query.RzDocFileQuery;
import java.util.List;

public interface RzDocFileDao{

	/**
	 * 添加
	 * @param rzDocFile
	 * @return
	 */
	public void insertRzDocFile(RzDocFile rzDocFile);
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */
	public boolean deleteRzDocFileById(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */
	public boolean deleteRzDocFileByCondition(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */
	public boolean deleteRzDocFileByBatchId(Map<String,Object> data);
	
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */
	public boolean logicDeleteRzDocFileById(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */
	public boolean logicDeleteRzDocFileByCondition(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */
	public boolean logicDeleteRzDocFileByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param rzDocFileQuery
	 * @return
	 */
	public boolean updateRzDocFileById(RzDocFile rzDocFile);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzDocFileByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param rzDocFileQuery
	 * @return
	 */
	public RzDocFile getRzDocFileById(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 查询所有
	 * @param rzDocFileQuery
	 * @return
	 */
	public List<RzDocFile> getRzDocFileAll(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 分页查询
	 * @param rzDocFileQuery
	 * @return
	 */
	public List<RzDocFile> getRzDocFileByPage(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzDocFileQuery
	 * @return
	 */
	public Integer getRzDocFileByPageCount(RzDocFileQuery rzDocFileQuery);
	
}