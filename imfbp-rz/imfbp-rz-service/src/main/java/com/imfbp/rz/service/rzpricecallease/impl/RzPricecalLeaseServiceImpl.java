package com.imfbp.rz.service.rzpricecallease.impl;

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
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;
import com.imfbp.rz.dao.rzpricecallease.RzPricecalLeaseDao;
import com.imfbp.rz.service.rzpricecallease.RzPricecalLeaseService;





@Component("rzPricecalLeaseService")
public class RzPricecalLeaseServiceImpl implements RzPricecalLeaseService{


	private RzPricecalLeaseDao rzPricecalLeaseDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPricecalLease
	 * @return
	 */
	@Override
	public void insertRzPricecalLease(RzPricecalLease rzPricecalLease){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPricecalLease.setPkPricecalLease(pk);
		rzPricecalLeaseDao.insertRzPricecalLease(rzPricecalLease);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecalLease>
	 * @return
	 */
	public void insertBatchRzPricecalLease(List<RzPricecalLease> rzPricecalLeaseList){
		if(rzPricecalLeaseList != null){
			for(int i=0;i<rzPricecalLeaseList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPricecalLeaseList.get(i).setPkPricecalLease(pk);
			}
			rzPricecalLeaseDao.insertBatchRzPricecalLease(rzPricecalLeaseList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalLeaseById(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return rzPricecalLeaseDao.deleteRzPricecalLeaseById(rzPricecalLeaseQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalLeaseByCondition(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return rzPricecalLeaseDao.deleteRzPricecalLeaseByCondition(rzPricecalLeaseQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPricecalLeaseQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPricecalLeaseByBatchId(RzPricecalLeaseQuery rzPricecalLeaseQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPricecalLeaseQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPricecalLeaseDao.deleteRzPricecalLeaseByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPricecalLease
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPricecalLease rzPricecalLease) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPricecalLease!=null){
				if(StringUtil.isNotEmpty(rzPricecalLease.getPkPricecalLease())){
					updateRzPricecalLeaseById(rzPricecalLease);
				}else{
					insertRzPricecalLease(rzPricecalLease);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPricecalLease);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPricecalLease
	 * @return
	 */
	@Override
	public boolean updateRzPricecalLeaseById(RzPricecalLease rzPricecalLease){
		return rzPricecalLeaseDao.updateRzPricecalLeaseById(rzPricecalLease);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalLeaseByCondition(RzPricecalLeaseQuery record,RzPricecalLeaseQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPricecalLeaseDao.updateRzPricecalLeaseByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public RzPricecalLease getRzPricecalLeaseById(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return rzPricecalLeaseDao.getRzPricecalLeaseById(rzPricecalLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPricecalLease> getRzPricecalLeaseAll(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return rzPricecalLeaseDao.getRzPricecalLeaseAll(rzPricecalLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public GridResult<RzPricecalLease> getRzPricecalLeaseByPage(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		//如果排序的字段是空或者空字符串
		if(rzPricecalLeaseQuery!=null&&StringUtils.isBlank(rzPricecalLeaseQuery.getSort())){
			rzPricecalLeaseQuery.setSort("seq_no");
			rzPricecalLeaseQuery.setOrder("asc");;
		}
		int total = rzPricecalLeaseDao.getRzPricecalLeaseByPageCount(rzPricecalLeaseQuery);
		PaginatedList<RzPricecalLease> rzPricecalLeasePageList = new MysqlPaginatedArrayList<RzPricecalLease>(rzPricecalLeaseQuery,total);
		List<RzPricecalLease> rzPricecalLeaseList = rzPricecalLeaseDao.getRzPricecalLeaseByPage(rzPricecalLeaseQuery);
		rzPricecalLeasePageList.addAll(rzPricecalLeaseList);
		GridResult<RzPricecalLease> result = new GridResult<RzPricecalLease>(rzPricecalLeasePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalLeaseQuery
	 * @return
	 */
	@Override
	public int getRzPricecalLeaseByPageCount(RzPricecalLeaseQuery rzPricecalLeaseQuery){
		return rzPricecalLeaseDao.getRzPricecalLeaseByPageCount(rzPricecalLeaseQuery);
	}

	public void setRzPricecalLeaseDao(RzPricecalLeaseDao  rzPricecalLeaseDao){
		this.rzPricecalLeaseDao = rzPricecalLeaseDao;
	}

	@Override
	public Result updateByBatchId(List<RzPricecalLease> list) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			data.put("list",list);
			boolean flag = rzPricecalLeaseDao.updateByBatchId(data);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}