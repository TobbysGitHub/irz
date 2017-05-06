package com.imfbp.rz.service.rzdocfile.impl;

import java.util.List;
import java.util.Map;
import java.util.Hashtable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzdocfile.RzDocFile;
import com.imfbp.rz.domain.rzdocfile.query.RzDocFileQuery;
import com.imfbp.rz.dao.rzdocfile.RzDocFileDao;
import com.imfbp.rz.service.rzdocfile.RzDocFileService;



@Component("rzDocFileService")
public class RzDocFileServiceImpl implements RzDocFileService{


	private RzDocFileDao rzDocFileDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzDocFile
	 * @return
	 */
	@Override
	public void insertRzDocFile(RzDocFile rzDocFile){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzDocFile.setId(pk);
		rzDocFileDao.insertRzDocFile(rzDocFile);	
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzDocFileById(RzDocFileQuery rzDocFileQuery){
		return rzDocFileDao.deleteRzDocFileById(rzDocFileQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDocFileByCondition(RzDocFileQuery rzDocFileQuery){
		return rzDocFileDao.deleteRzDocFileByCondition(rzDocFileQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzDocFileQuery
	 * @return
	 */	
	@Override
	public Result deleteRzDocFileByBatchId(RzDocFileQuery rzDocFileQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzDocFileQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzDocFileDao.deleteRzDocFileByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDocFileById(RzDocFileQuery rzDocFileQuery){
		return rzDocFileDao.logicDeleteRzDocFileById(rzDocFileQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDocFileByCondition(RzDocFileQuery rzDocFileQuery){
		return rzDocFileDao.logicDeleteRzDocFileByCondition(rzDocFileQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzDocFileQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzDocFileByBatchId(RzDocFileQuery rzDocFileQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzDocFileQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzDocFileDao.logicDeleteRzDocFileByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzDocFile
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzDocFile rzDocFile) {
		Result result = new Result();
		//设置调用失败
		result.setSuccess(false);
		try {
			if(rzDocFile!=null){
				if(StringUtil.isNotEmpty(rzDocFile.getId())){
					updateRzDocFileById(rzDocFile);
				}else{
					insertRzDocFile(rzDocFile);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzDocFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzDocFile
	 * @return
	 */
	@Override
	public boolean updateRzDocFileById(RzDocFile rzDocFile){
		return rzDocFileDao.updateRzDocFileById(rzDocFile);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzDocFileByCondition(RzDocFileQuery record,RzDocFileQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzDocFileDao.updateRzDocFileByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public RzDocFile getRzDocFileById(RzDocFileQuery rzDocFileQuery){
		return rzDocFileDao.getRzDocFileById(rzDocFileQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public List<RzDocFile> getRzDocFileAll(RzDocFileQuery rzDocFileQuery){
		return rzDocFileDao.getRzDocFileAll(rzDocFileQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public GridResult<RzDocFile> getRzDocFileByPage(RzDocFileQuery rzDocFileQuery){
		//如果排序的字段是空或者空字符串
		if(rzDocFileQuery!=null&&StringUtils.isBlank(rzDocFileQuery.getSort())){
			rzDocFileQuery.setSort("id");
			rzDocFileQuery.setOrder("desc");;
		}
		int total = rzDocFileDao.getRzDocFileByPageCount(rzDocFileQuery);
		PaginatedList<RzDocFile> rzDocFilePageList = new MysqlPaginatedArrayList<RzDocFile>(rzDocFileQuery,total);
		List<RzDocFile> rzDocFileList = rzDocFileDao.getRzDocFileByPage(rzDocFileQuery);
		rzDocFilePageList.addAll(rzDocFileList);
		GridResult<RzDocFile> result = new GridResult<RzDocFile>(rzDocFilePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzDocFileQuery
	 * @return
	 */
	@Override
	public int getRzDocFileByPageCount(RzDocFileQuery rzDocFileQuery){
		return rzDocFileDao.getRzDocFileByPageCount(rzDocFileQuery);
	}

	public void setRzDocFileDao(RzDocFileDao  rzDocFileDao){
		this.rzDocFileDao = rzDocFileDao;
	}
	
}