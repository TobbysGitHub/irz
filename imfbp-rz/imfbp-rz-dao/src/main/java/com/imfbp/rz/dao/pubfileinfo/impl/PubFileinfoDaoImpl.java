package com.imfbp.rz.dao.pubfileinfo.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;
import com.imfbp.rz.dao.pubfileinfo.PubFileinfoDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class PubFileinfoDaoImpl extends SqlSessionDaoSupport implements PubFileinfoDao{

	/**
	 * 添加
	 * @param pubFileinfo
	 * @return
	 */
	@Override
	public void insertPubFileinfo(PubFileinfo pubFileinfo){
		this.getSqlSession().insert("pubFileinfo.insertPubFileinfo", pubFileinfo);
	}
	
	/**
	 * 批量添加
	 * @param List<pubFileinfo>
	 * @return
	 */
	public void insertBatchPubFileinfo(List<PubFileinfo> pubFileinfoList){
		this.getSqlSession().insert("pubFileinfo.insertBatchPubFileinfo", pubFileinfoList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public boolean deletePubFileinfoById(PubFileinfoQuery pubFileinfoQuery){
		return this.getSqlSession().delete("pubFileinfo.deletePubFileinfoById", pubFileinfoQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public boolean deletePubFileinfoByCondition(PubFileinfoQuery pubFileinfoQuery){
		return this.getSqlSession().delete("pubFileinfo.deletePubFileinfoByCondition", pubFileinfoQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deletePubFileinfoByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("pubFileinfo.deletePubFileinfoByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param pubFileinfoQuery
	 * @return
	 */	
	@Override
	public boolean updatePubFileinfoById(PubFileinfo pubFileinfo){
		return this.getSqlSession().update("pubFileinfo.updatePubFileinfoById", pubFileinfo)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param pubFileinfoList
	 * @return
	 */
	public boolean updatePubFileinfoByBatchId(List<PubFileinfo> pubFileinfoList){
		return this.getSqlSession().update("pubFileinfo.updatePubFileinfoByBatchId", pubFileinfoList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updatePubFileinfoByCondition(Map<String,Object> data){
		return this.getSqlSession().update("pubFileinfo.updatePubFileinfoByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public PubFileinfo getPubFileinfoById(PubFileinfoQuery pubFileinfoQuery){
		return this.getSqlSession().selectOne("pubFileinfo.getPubFileinfoById",pubFileinfoQuery);
	}
	
	/**
	 * 查询所有
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public List<PubFileinfo> getPubFileinfoAll(PubFileinfoQuery pubFileinfoQuery){
		return this.getSqlSession().selectList("pubFileinfo.getPubFileinfoAll",pubFileinfoQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<PubFileinfo> getPubFileinfoByPage(PubFileinfoQuery pubFileinfoQuery){
		return this.getSqlSession().selectList("pubFileinfo.getPubFileinfoByPage",pubFileinfoQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getPubFileinfoByPageCount(PubFileinfoQuery pubFileinfoQuery){
		return this.getSqlSession().selectOne("pubFileinfo.getPubFileinfoByPageCount",pubFileinfoQuery);
	}

	@Override
	public boolean clearFile(PubFileinfoQuery pubFileinfoQuery) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("pubFileinfo.clearFile", pubFileinfoQuery)>0;
	}
}