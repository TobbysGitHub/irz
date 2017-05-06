package com.imfbp.rz.service.rzadjintlease.impl;

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

import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzadjintlease.query.RzAdjIntLeaseQuery;
import com.imfbp.rz.dao.rzadjintlease.RzAdjIntLeaseDao;
import com.imfbp.rz.dao.rzpricecallease.RzPricecalLeaseDao;
import com.imfbp.rz.service.rzadjintlease.RzAdjIntLeaseService;





@Component("rzAdjIntLeaseService")
public class RzAdjIntLeaseServiceImpl implements RzAdjIntLeaseService{


	private RzAdjIntLeaseDao rzAdjIntLeaseDao;

	private RzPricecalLeaseDao rzPricecalLeaseDao;
	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzAdjIntLease
	 * @return
	 */
	@Override
	public void insertRzAdjIntLease(RzAdjIntLease rzAdjIntLease){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzAdjIntLease.setPkAdjIntLease(pk);
		rzAdjIntLeaseDao.insertRzAdjIntLease(rzAdjIntLease);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzAdjIntLease>
	 * @return
	 */
	public void insertBatchRzAdjIntLease(List<RzAdjIntLease> rzAdjIntLeaseList){
		if(rzAdjIntLeaseList != null){
			for(int i=0;i<rzAdjIntLeaseList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzAdjIntLeaseList.get(i).setPkAdjIntLease(pk);
			}
			rzAdjIntLeaseDao.insertBatchRzAdjIntLease(rzAdjIntLeaseList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseById(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return rzAdjIntLeaseDao.deleteRzAdjIntLeaseById(rzAdjIntLeaseQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseByCondition(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return rzAdjIntLeaseDao.deleteRzAdjIntLeaseByCondition(rzAdjIntLeaseQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */	
	@Override
	public Result deleteRzAdjIntLeaseByBatchId(RzAdjIntLeaseQuery rzAdjIntLeaseQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzAdjIntLeaseQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzAdjIntLeaseDao.deleteRzAdjIntLeaseByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzAdjIntLease
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzAdjIntLease rzAdjIntLease) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzAdjIntLease!=null){
				if(StringUtil.isNotEmpty(rzAdjIntLease.getPkAdjIntLease())){
					updateRzAdjIntLeaseById(rzAdjIntLease);
				}else{
					insertRzAdjIntLease(rzAdjIntLease);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzAdjIntLease);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzAdjIntLease
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntLeaseById(RzAdjIntLease rzAdjIntLease){
		return rzAdjIntLeaseDao.updateRzAdjIntLeaseById(rzAdjIntLease);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntLeaseByCondition(RzAdjIntLeaseQuery record,RzAdjIntLeaseQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzAdjIntLeaseDao.updateRzAdjIntLeaseByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	public Result updateRzAdjIntLeaseByBatchId(List<RzAdjIntLease> rzAdjIntLeaseList){
		Result result = new Result(false);
		try {
			boolean flag = rzAdjIntLeaseDao.updateRzAdjIntLeaseByBatchId(rzAdjIntLeaseList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public RzAdjIntLease getRzAdjIntLeaseById(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return rzAdjIntLeaseDao.getRzAdjIntLeaseById(rzAdjIntLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public List<RzAdjIntLease> getRzAdjIntLeaseAll(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return rzAdjIntLeaseDao.getRzAdjIntLeaseAll(rzAdjIntLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public GridResult<RzAdjIntLease> getRzAdjIntLeaseByPage(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		//如果排序的字段是空或者空字符串
		if(rzAdjIntLeaseQuery!=null&&StringUtils.isBlank(rzAdjIntLeaseQuery.getSort())){
			rzAdjIntLeaseQuery.setSort("seq_no");
			rzAdjIntLeaseQuery.setOrder("asc");;
		}
		int total = rzAdjIntLeaseDao.getRzAdjIntLeaseByPageCount(rzAdjIntLeaseQuery);
		PaginatedList<RzAdjIntLease> rzAdjIntLeasePageList = new MysqlPaginatedArrayList<RzAdjIntLease>(rzAdjIntLeaseQuery,total);
		List<RzAdjIntLease> rzAdjIntLeaseList = rzAdjIntLeaseDao.getRzAdjIntLeaseByPage(rzAdjIntLeaseQuery);
		rzAdjIntLeasePageList.addAll(rzAdjIntLeaseList);
		GridResult<RzAdjIntLease> result = new GridResult<RzAdjIntLease>(rzAdjIntLeasePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzAdjIntLeaseQuery
	 * @return
	 */
	@Override
	public int getRzAdjIntLeaseByPageCount(RzAdjIntLeaseQuery rzAdjIntLeaseQuery){
		return rzAdjIntLeaseDao.getRzAdjIntLeaseByPageCount(rzAdjIntLeaseQuery);
	}

	public void setRzAdjIntLeaseDao(RzAdjIntLeaseDao  rzAdjIntLeaseDao){
		this.rzAdjIntLeaseDao = rzAdjIntLeaseDao;
	}

	public void setRzPricecalLeaseDao(RzPricecalLeaseDao rzPricecalLeaseDao) {
		this.rzPricecalLeaseDao = rzPricecalLeaseDao;
	}

	public void setPrimaryKeyUtil(PrimaryKeyUtil primaryKeyUtil) {
		this.primaryKeyUtil = primaryKeyUtil;
	}
	
}