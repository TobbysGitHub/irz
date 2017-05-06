package com.imfbp.rz.service.rzdocfile;

import java.util.List;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzdocfile.RzDocFile;
import com.imfbp.rz.domain.rzdocfile.query.RzDocFileQuery;

public interface RzDocFileService{

	/**
	 * 添加
	 * @param rzDocFile
	 * @return
	 */
	public void insertRzDocFile(RzDocFile rzDocFile);
	
	/**
	 * 根据Id删除
	 * @param id
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
	 * 批量删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */
	public Result deleteRzDocFileByBatchId(RzDocFileQuery rzDocFileQuery);
	
   	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
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
	 * 批量逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */
	public Result logicDeleteRzDocFileByBatchId(RzDocFileQuery rzDocFileQuery);
	
	
	/**
	 * 添加或修改
	 * @param rzDocFile
	 */
	public Result insertOrUpdate(RzDocFile rzDocFile);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updateRzDocFileById(RzDocFile rzDocFile);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updateRzDocFileByCondition(RzDocFileQuery record,RzDocFileQuery parameter);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<RzDocFile> getRzDocFileByPage(RzDocFileQuery rzDocFileQuery);
	
	/**
	 * 分页查询查询总数
	 * @param rzDocFileQuery
	 * @return
	 */
	public int getRzDocFileByPageCount(RzDocFileQuery rzDocFileQuery);
	
	
}