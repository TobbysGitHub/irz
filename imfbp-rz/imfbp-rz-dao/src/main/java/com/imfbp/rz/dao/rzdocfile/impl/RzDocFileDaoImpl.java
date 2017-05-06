package com.imfbp.rz.dao.rzdocfile.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzdocfile.RzDocFile;
import com.imfbp.rz.domain.rzdocfile.query.RzDocFileQuery;
import com.imfbp.rz.dao.rzdocfile.RzDocFileDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzDocFileDaoImpl extends SqlSessionDaoSupport implements RzDocFileDao{

	/**
	 * 添加
	 * @param rzDocFile
	 * @return
	 */
	@Override
	public void insertRzDocFile(RzDocFile rzDocFile){
		this.getSqlSession().insert("rzDocFile.insertRzDocFile", rzDocFile);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDocFileById(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().delete("rzDocFile.deleteRzDocFileById", rzDocFileQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDocFileByCondition(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().delete("rzDocFile.deleteRzDocFileById", rzDocFileQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzDocFileByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzDocFile.deleteRzDocFileByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDocFileById(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().update("rzDocFile.logicDeleteRzDocFileById", rzDocFileQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDocFileByCondition(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().update("rzDocFile.logicDeleteRzDocFileById", rzDocFileQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDocFileByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzDocFile.logicDeleteRzDocFileByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzDocFileQuery
	 * @return
	 */	
	@Override
	public boolean updateRzDocFileById(RzDocFile rzDocFile){
		return this.getSqlSession().update("rzDocFile.updateRzDocFileById", rzDocFile)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzDocFileByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzDocFile.updateRzDocFileByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public RzDocFile getRzDocFileById(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().selectOne("rzDocFile.getRzDocFileById",rzDocFileQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public List<RzDocFile> getRzDocFileAll(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().selectList("rzDocFile.getRzDocFileAll",rzDocFileQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzDocFile> getRzDocFileByPage(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().selectList("rzDocFile.getRzDocFileByPage",rzDocFileQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzDocFileByPageCount(RzDocFileQuery rzDocFileQuery){
		return this.getSqlSession().selectOne("rzDocFile.getRzDocFileByPageCount",rzDocFileQuery);
	}
}