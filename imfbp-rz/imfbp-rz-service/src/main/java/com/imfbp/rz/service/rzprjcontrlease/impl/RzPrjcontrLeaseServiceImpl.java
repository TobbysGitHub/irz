package com.imfbp.rz.service.rzprjcontrlease.impl;

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

import com.imfbp.rz.domain.rzprjcontrlease.RzPrjcontrLease;
import com.imfbp.rz.domain.rzprjcontrlease.query.RzPrjcontrLeaseQuery;
import com.imfbp.rz.dao.rzprjcontrlease.RzPrjcontrLeaseDao;
import com.imfbp.rz.service.rzprjcontrlease.RzPrjcontrLeaseService;





@Component("rzPrjcontrLeaseService")
public class RzPrjcontrLeaseServiceImpl implements RzPrjcontrLeaseService{


	private RzPrjcontrLeaseDao rzPrjcontrLeaseDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrLease
	 * @return
	 */
	@Override
	public void insertRzPrjcontrLease(RzPrjcontrLease rzPrjcontrLease){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrLease.setPkPricecalRent(pk);
		rzPrjcontrLeaseDao.insertRzPrjcontrLease(rzPrjcontrLease);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrLease>
	 * @return
	 */
	public void insertBatchRzPrjcontrLease(List<RzPrjcontrLease> rzPrjcontrLeaseList){
		if(rzPrjcontrLeaseList != null){
			for(int i=0;i<rzPrjcontrLeaseList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrLeaseList.get(i).setPkPricecalRent(pk);
			}
			rzPrjcontrLeaseDao.insertBatchRzPrjcontrLease(rzPrjcontrLeaseList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLeaseById(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return rzPrjcontrLeaseDao.deleteRzPrjcontrLeaseById(rzPrjcontrLeaseQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLeaseByCondition(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return rzPrjcontrLeaseDao.deleteRzPrjcontrLeaseByCondition(rzPrjcontrLeaseQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrLeaseByBatchId(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrLeaseQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrLeaseDao.deleteRzPrjcontrLeaseByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrLease
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrLease rzPrjcontrLease) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrLease!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrLease.getPkPricecalRent())){
					updateRzPrjcontrLeaseById(rzPrjcontrLease);
				}else{
					insertRzPrjcontrLease(rzPrjcontrLease);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrLease);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrLease
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrLeaseById(RzPrjcontrLease rzPrjcontrLease){
		return rzPrjcontrLeaseDao.updateRzPrjcontrLeaseById(rzPrjcontrLease);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrLeaseByCondition(RzPrjcontrLeaseQuery record,RzPrjcontrLeaseQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrLeaseDao.updateRzPrjcontrLeaseByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	public Result updateRzPrjcontrLeaseByBatchId(List<RzPrjcontrLease> rzPrjcontrLeaseList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrLeaseDao.updateRzPrjcontrLeaseByBatchId(rzPrjcontrLeaseList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public RzPrjcontrLease getRzPrjcontrLeaseById(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return rzPrjcontrLeaseDao.getRzPrjcontrLeaseById(rzPrjcontrLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrLease> getRzPrjcontrLeaseAll(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return rzPrjcontrLeaseDao.getRzPrjcontrLeaseAll(rzPrjcontrLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrLease> getRzPrjcontrLeaseByPage(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrLeaseQuery!=null&&StringUtils.isBlank(rzPrjcontrLeaseQuery.getSort())){
			rzPrjcontrLeaseQuery.setSort("pk_pricecal_rent");
			rzPrjcontrLeaseQuery.setOrder("desc");;
		}
		int total = rzPrjcontrLeaseDao.getRzPrjcontrLeaseByPageCount(rzPrjcontrLeaseQuery);
		PaginatedList<RzPrjcontrLease> rzPrjcontrLeasePageList = new MysqlPaginatedArrayList<RzPrjcontrLease>(rzPrjcontrLeaseQuery,total);
		List<RzPrjcontrLease> rzPrjcontrLeaseList = rzPrjcontrLeaseDao.getRzPrjcontrLeaseByPage(rzPrjcontrLeaseQuery);
		rzPrjcontrLeasePageList.addAll(rzPrjcontrLeaseList);
		GridResult<RzPrjcontrLease> result = new GridResult<RzPrjcontrLease>(rzPrjcontrLeasePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrLeaseQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrLeaseByPageCount(RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery){
		return rzPrjcontrLeaseDao.getRzPrjcontrLeaseByPageCount(rzPrjcontrLeaseQuery);
	}

	public void setRzPrjcontrLeaseDao(RzPrjcontrLeaseDao  rzPrjcontrLeaseDao){
		this.rzPrjcontrLeaseDao = rzPrjcontrLeaseDao;
	}
	
}