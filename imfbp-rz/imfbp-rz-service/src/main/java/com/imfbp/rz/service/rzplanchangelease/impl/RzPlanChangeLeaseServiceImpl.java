package com.imfbp.rz.service.rzplanchangelease.impl;

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

import com.imfbp.rz.domain.rzplanchangelease.RzPlanChangeLease;
import com.imfbp.rz.domain.rzplanchangelease.query.RzPlanChangeLeaseQuery;
import com.imfbp.rz.dao.rzplanchangelease.RzPlanChangeLeaseDao;
import com.imfbp.rz.service.rzplanchangelease.RzPlanChangeLeaseService;





@Component("rzPlanChangeLeaseService")
public class RzPlanChangeLeaseServiceImpl implements RzPlanChangeLeaseService{


	private RzPlanChangeLeaseDao rzPlanChangeLeaseDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPlanChangeLease
	 * @return
	 */
	@Override
	public void insertRzPlanChangeLease(RzPlanChangeLease rzPlanChangeLease){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPlanChangeLease.setPkPlanChangeLease(pk);
		rzPlanChangeLeaseDao.insertRzPlanChangeLease(rzPlanChangeLease);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPlanChangeLease>
	 * @return
	 */
	public void insertBatchRzPlanChangeLease(List<RzPlanChangeLease> rzPlanChangeLeaseList){
		if(rzPlanChangeLeaseList != null){
			for(int i=0;i<rzPlanChangeLeaseList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPlanChangeLeaseList.get(i).setPkPlanChangeLease(pk);
			}
			rzPlanChangeLeaseDao.insertBatchRzPlanChangeLease(rzPlanChangeLeaseList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseById(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return rzPlanChangeLeaseDao.deleteRzPlanChangeLeaseById(rzPlanChangeLeaseQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseByCondition(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return rzPlanChangeLeaseDao.deleteRzPlanChangeLeaseByCondition(rzPlanChangeLeaseQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPlanChangeLeaseByBatchId(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPlanChangeLeaseQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPlanChangeLeaseDao.deleteRzPlanChangeLeaseByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPlanChangeLease
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPlanChangeLease rzPlanChangeLease) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPlanChangeLease!=null){
				if(StringUtil.isNotEmpty(rzPlanChangeLease.getPkPlanChangeLease())){
					updateRzPlanChangeLeaseById(rzPlanChangeLease);
				}else{
					insertRzPlanChangeLease(rzPlanChangeLease);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPlanChangeLease);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPlanChangeLease
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeLeaseById(RzPlanChangeLease rzPlanChangeLease){
		return rzPlanChangeLeaseDao.updateRzPlanChangeLeaseById(rzPlanChangeLease);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeLeaseByCondition(RzPlanChangeLeaseQuery record,RzPlanChangeLeaseQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPlanChangeLeaseDao.updateRzPlanChangeLeaseByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	public Result updateRzPlanChangeLeaseByBatchId(List<RzPlanChangeLease> rzPlanChangeLeaseList){
		Result result = new Result(false);
		try {
			boolean flag = rzPlanChangeLeaseDao.updateRzPlanChangeLeaseByBatchId(rzPlanChangeLeaseList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public RzPlanChangeLease getRzPlanChangeLeaseById(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return rzPlanChangeLeaseDao.getRzPlanChangeLeaseById(rzPlanChangeLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPlanChangeLease> getRzPlanChangeLeaseAll(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return rzPlanChangeLeaseDao.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public GridResult<RzPlanChangeLease> getRzPlanChangeLeaseByPage(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		//如果排序的字段是空或者空字符串
		if(rzPlanChangeLeaseQuery!=null&&StringUtils.isBlank(rzPlanChangeLeaseQuery.getSort())){
			rzPlanChangeLeaseQuery.setSort("seq_no");
			rzPlanChangeLeaseQuery.setOrder("asc");
		}
		int total = rzPlanChangeLeaseDao.getRzPlanChangeLeaseByPageCount(rzPlanChangeLeaseQuery);
		PaginatedList<RzPlanChangeLease> rzPlanChangeLeasePageList = new MysqlPaginatedArrayList<RzPlanChangeLease>(rzPlanChangeLeaseQuery,total);
		List<RzPlanChangeLease> rzPlanChangeLeaseList = rzPlanChangeLeaseDao.getRzPlanChangeLeaseByPage(rzPlanChangeLeaseQuery);
		rzPlanChangeLeasePageList.addAll(rzPlanChangeLeaseList);
		GridResult<RzPlanChangeLease> result = new GridResult<RzPlanChangeLease>(rzPlanChangeLeasePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeLeaseQuery
	 * @return
	 */
	@Override
	public int getRzPlanChangeLeaseByPageCount(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery){
		return rzPlanChangeLeaseDao.getRzPlanChangeLeaseByPageCount(rzPlanChangeLeaseQuery);
	}

	public void setRzPlanChangeLeaseDao(RzPlanChangeLeaseDao  rzPlanChangeLeaseDao){
		this.rzPlanChangeLeaseDao = rzPlanChangeLeaseDao;
	}
	
}