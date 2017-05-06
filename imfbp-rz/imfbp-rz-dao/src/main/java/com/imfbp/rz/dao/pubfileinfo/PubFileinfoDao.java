package com.imfbp.rz.dao.pubfileinfo;

import java.util.Map;
import java.util.List;

import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;

public interface PubFileinfoDao{

	/**
	 * 添加
	 * @param pubFileinfo
	 * @return
	 */
	public void insertPubFileinfo(PubFileinfo pubFileinfo);
	
	/**
	 * 批量添加
	 * @param List<pubFileinfo>
	 * @return
	 */
	public void insertBatchPubFileinfo(List<PubFileinfo> pubFileinfoList);
	
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */
	public boolean deletePubFileinfoById(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */
	public boolean deletePubFileinfoByCondition(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 根据主键批量删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */
	public boolean deletePubFileinfoByBatchId(Map<String,Object> data);
	
	
	
	/**
	 * 根据Id修改
	 * @param pubFileinfoQuery
	 * @return
	 */
	public boolean updatePubFileinfoById(PubFileinfo pubFileinfo);
	
	/**
	 * 根据Id批量修改
	 * @param pubFileinfoQuery
	 * @return
	 */
	public boolean updatePubFileinfoByBatchId(List<PubFileinfo> pubFileinfoList);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updatePubFileinfoByCondition(Map<String,Object> data);
	
	/**
	 * 根据id查询
	 * @param pubFileinfoQuery
	 * @return
	 */
	public PubFileinfo getPubFileinfoById(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 查询所有
	 * @param pubFileinfoQuery
	 * @return
	 */
	public List<PubFileinfo> getPubFileinfoAll(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 分页查询
	 * @param pubFileinfoQuery
	 * @return
	 */
	public List<PubFileinfo> getPubFileinfoByPage(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 分页查询查询总数
	 * @param pubFileinfoQuery
	 * @return
	 */
	public Integer getPubFileinfoByPageCount(PubFileinfoQuery pubFileinfoQuery);
	
	public boolean clearFile(PubFileinfoQuery pubFileinfoQuery);
	
}