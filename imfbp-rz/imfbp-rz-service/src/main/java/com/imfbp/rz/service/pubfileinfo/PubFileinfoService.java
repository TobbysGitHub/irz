package com.imfbp.rz.service.pubfileinfo;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;

public interface PubFileinfoService{

	/**
	 * 添加
	 * @param pubFileinfo
	 * @return
	 */
	public void insertPubFileinfo(PubFileinfo pubFileinfo,PubFileinfo info);
	
	/**
	 * 批量添加
	 * @param List<pubFileinfo>
	 * @return
	 */
	public void insertBatchPubFileinfo(List<PubFileinfo> pubFileinfoList);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public boolean deletePubFileinfoById(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 根据条件删除 (真正删除数据库数据),同时删除主表中的附件记录
	 * @param pubFileinfoQuery
	 * @return
	 */
	public Result deletePubFileinfoByCondition(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 批量删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */
	public Result deletePubFileinfoByBatchId(PubFileinfoQuery pubFileinfoQuery);
	
	
	/**
	 * 添加或修改
	 * @param pubFileinfo
	 */
	public Result insertOrUpdate(PubFileinfo pubFileinfo,PubFileinfo info);

	
	/**
	 * 根据Id修改
	 * @param id
	 * @return
	 */
	public boolean updatePubFileinfoById(PubFileinfo pubFileinfo,PubFileinfo info);
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	public boolean updatePubFileinfoByCondition(PubFileinfoQuery record,PubFileinfoQuery parameter);
	
	/**
	 * 根据Id批量修改
	 * @param pubFileinfoQuery
	 * @return
	 */
	public Result updatePubFileinfoByBatchId(List<PubFileinfo> pubFileinfoList);
	
	/**
	 * 根据id查询
	 * @param id
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
	public GridResult<PubFileinfo> getPubFileinfoByPage(PubFileinfoQuery pubFileinfoQuery);
	
	/**
	 * 分页查询查询总数
	 * @param pubFileinfoQuery
	 * @return
	 */
	public int getPubFileinfoByPageCount(PubFileinfoQuery pubFileinfoQuery);
	
	//附件下载
	public Result downLoadFile(String id);
	
	public Map<String, Object> saveFile(MultipartFile[] files, String pkBill,String pkFile,List<String>systemMessage);
	public Map<String, Object> saveFile(MultipartFile[] files);
}