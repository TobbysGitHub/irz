package com.imfbp.rz.service.rzplanchangeleasenew.impl;

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

import com.imfbp.rz.domain.rzplanchangeleasenew.RzPlanChangeLeaseNew;
import com.imfbp.rz.domain.rzplanchangeleasenew.query.RzPlanChangeLeaseNewQuery;
import com.imfbp.rz.dao.rzplanchangeleasenew.RzPlanChangeLeaseNewDao;
import com.imfbp.rz.service.rzplanchangeleasenew.RzPlanChangeLeaseNewService;





@Component("rzPlanChangeLeaseNewService")
public class RzPlanChangeLeaseNewServiceImpl implements RzPlanChangeLeaseNewService{


	private RzPlanChangeLeaseNewDao rzPlanChangeLeaseNewDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPlanChangeLeaseNew
	 * @return
	 */
	@Override
	public void insertRzPlanChangeLeaseNew(RzPlanChangeLeaseNew rzPlanChangeLeaseNew){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPlanChangeLeaseNew.setPkPlanChangeLeaseNew(pk);
		rzPlanChangeLeaseNewDao.insertRzPlanChangeLeaseNew(rzPlanChangeLeaseNew);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPlanChangeLeaseNew>
	 * @return
	 */
	public void insertBatchRzPlanChangeLeaseNew(List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList){
		if(rzPlanChangeLeaseNewList != null){
			for(int i=0;i<rzPlanChangeLeaseNewList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPlanChangeLeaseNewList.get(i).setPkPlanChangeLeaseNew(pk);
			}
			rzPlanChangeLeaseNewDao.insertBatchRzPlanChangeLeaseNew(rzPlanChangeLeaseNewList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseNewById(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return rzPlanChangeLeaseNewDao.deleteRzPlanChangeLeaseNewById(rzPlanChangeLeaseNewQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeLeaseNewByCondition(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return rzPlanChangeLeaseNewDao.deleteRzPlanChangeLeaseNewByCondition(rzPlanChangeLeaseNewQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPlanChangeLeaseNewByBatchId(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPlanChangeLeaseNewQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPlanChangeLeaseNewDao.deleteRzPlanChangeLeaseNewByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPlanChangeLeaseNew
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPlanChangeLeaseNew rzPlanChangeLeaseNew) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPlanChangeLeaseNew!=null){
				if(StringUtil.isNotEmpty(rzPlanChangeLeaseNew.getPkPlanChangeLeaseNew())){
					updateRzPlanChangeLeaseNewById(rzPlanChangeLeaseNew);
				}else{
					insertRzPlanChangeLeaseNew(rzPlanChangeLeaseNew);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPlanChangeLeaseNew);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPlanChangeLeaseNew
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeLeaseNewById(RzPlanChangeLeaseNew rzPlanChangeLeaseNew){
		return rzPlanChangeLeaseNewDao.updateRzPlanChangeLeaseNewById(rzPlanChangeLeaseNew);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeLeaseNewByCondition(RzPlanChangeLeaseNewQuery record,RzPlanChangeLeaseNewQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPlanChangeLeaseNewDao.updateRzPlanChangeLeaseNewByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	public Result updateRzPlanChangeLeaseNewByBatchId(List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList){
		Result result = new Result(false);
		try {
			boolean flag = rzPlanChangeLeaseNewDao.updateRzPlanChangeLeaseNewByBatchId(rzPlanChangeLeaseNewList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public RzPlanChangeLeaseNew getRzPlanChangeLeaseNewById(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return rzPlanChangeLeaseNewDao.getRzPlanChangeLeaseNewById(rzPlanChangeLeaseNewQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public List<RzPlanChangeLeaseNew> getRzPlanChangeLeaseNewAll(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return rzPlanChangeLeaseNewDao.getRzPlanChangeLeaseNewAll(rzPlanChangeLeaseNewQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public GridResult<RzPlanChangeLeaseNew> getRzPlanChangeLeaseNewByPage(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		//如果排序的字段是空或者空字符串
		if(rzPlanChangeLeaseNewQuery!=null&&StringUtils.isBlank(rzPlanChangeLeaseNewQuery.getSort())){
			rzPlanChangeLeaseNewQuery.setSort("seq_no");
			rzPlanChangeLeaseNewQuery.setOrder("asc");;
		}
		int total = rzPlanChangeLeaseNewDao.getRzPlanChangeLeaseNewByPageCount(rzPlanChangeLeaseNewQuery);
		PaginatedList<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewPageList = new MysqlPaginatedArrayList<RzPlanChangeLeaseNew>(rzPlanChangeLeaseNewQuery,total);
		List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList = rzPlanChangeLeaseNewDao.getRzPlanChangeLeaseNewByPage(rzPlanChangeLeaseNewQuery);
		rzPlanChangeLeaseNewPageList.addAll(rzPlanChangeLeaseNewList);
		GridResult<RzPlanChangeLeaseNew> result = new GridResult<RzPlanChangeLeaseNew>(rzPlanChangeLeaseNewPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeLeaseNewQuery
	 * @return
	 */
	@Override
	public int getRzPlanChangeLeaseNewByPageCount(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery){
		return rzPlanChangeLeaseNewDao.getRzPlanChangeLeaseNewByPageCount(rzPlanChangeLeaseNewQuery);
	}

	public void setRzPlanChangeLeaseNewDao(RzPlanChangeLeaseNewDao  rzPlanChangeLeaseNewDao){
		this.rzPlanChangeLeaseNewDao = rzPlanChangeLeaseNewDao;
	}
	
}