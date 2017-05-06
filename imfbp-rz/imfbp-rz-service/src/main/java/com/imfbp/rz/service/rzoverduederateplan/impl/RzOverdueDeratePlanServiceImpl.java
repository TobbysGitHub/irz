package com.imfbp.rz.service.rzoverduederateplan.impl;

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

import com.imfbp.rz.domain.rzoverduederateplan.RzOverdueDeratePlan;
import com.imfbp.rz.domain.rzoverduederateplan.query.RzOverdueDeratePlanQuery;
import com.imfbp.rz.dao.rzoverduederateplan.RzOverdueDeratePlanDao;
import com.imfbp.rz.service.rzoverduederateplan.RzOverdueDeratePlanService;





@Component("rzOverdueDeratePlanService")
public class RzOverdueDeratePlanServiceImpl implements RzOverdueDeratePlanService{


	private RzOverdueDeratePlanDao rzOverdueDeratePlanDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzOverdueDeratePlan
	 * @return
	 */
	@Override
	public void insertRzOverdueDeratePlan(RzOverdueDeratePlan rzOverdueDeratePlan){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzOverdueDeratePlan.setPkOverdueDeratePlan(pk);
		rzOverdueDeratePlanDao.insertRzOverdueDeratePlan(rzOverdueDeratePlan);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzOverdueDeratePlan>
	 * @return
	 */
	public void insertBatchRzOverdueDeratePlan(List<RzOverdueDeratePlan> rzOverdueDeratePlanList){
		if(rzOverdueDeratePlanList != null){
			for(int i=0;i<rzOverdueDeratePlanList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzOverdueDeratePlanList.get(i).setPkOverdueDeratePlan(pk);
			}
			rzOverdueDeratePlanDao.insertBatchRzOverdueDeratePlan(rzOverdueDeratePlanList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDeratePlanById(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return rzOverdueDeratePlanDao.deleteRzOverdueDeratePlanById(rzOverdueDeratePlanQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDeratePlanByCondition(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return rzOverdueDeratePlanDao.deleteRzOverdueDeratePlanByCondition(rzOverdueDeratePlanQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */	
	@Override
	public Result deleteRzOverdueDeratePlanByBatchId(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzOverdueDeratePlanQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzOverdueDeratePlanDao.deleteRzOverdueDeratePlanByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzOverdueDeratePlan
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzOverdueDeratePlan rzOverdueDeratePlan) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzOverdueDeratePlan!=null){
				if(StringUtil.isNotEmpty(rzOverdueDeratePlan.getPkOverdueDeratePlan())){
					updateRzOverdueDeratePlanById(rzOverdueDeratePlan);
				}else{
					insertRzOverdueDeratePlan(rzOverdueDeratePlan);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzOverdueDeratePlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzOverdueDeratePlan
	 * @return
	 */
	@Override
	public boolean updateRzOverdueDeratePlanById(RzOverdueDeratePlan rzOverdueDeratePlan){
		return rzOverdueDeratePlanDao.updateRzOverdueDeratePlanById(rzOverdueDeratePlan);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzOverdueDeratePlanByCondition(RzOverdueDeratePlanQuery record,RzOverdueDeratePlanQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzOverdueDeratePlanDao.updateRzOverdueDeratePlanByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	public Result updateRzOverdueDeratePlanByBatchId(List<RzOverdueDeratePlan> rzOverdueDeratePlanList){
		Result result = new Result(false);
		try {
			boolean flag = rzOverdueDeratePlanDao.updateRzOverdueDeratePlanByBatchId(rzOverdueDeratePlanList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public RzOverdueDeratePlan getRzOverdueDeratePlanById(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return rzOverdueDeratePlanDao.getRzOverdueDeratePlanById(rzOverdueDeratePlanQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public List<RzOverdueDeratePlan> getRzOverdueDeratePlanAll(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return rzOverdueDeratePlanDao.getRzOverdueDeratePlanAll(rzOverdueDeratePlanQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public GridResult<RzOverdueDeratePlan> getRzOverdueDeratePlanByPage(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		//如果排序的字段是空或者空字符串
		if(rzOverdueDeratePlanQuery!=null&&StringUtils.isBlank(rzOverdueDeratePlanQuery.getSort())){
			rzOverdueDeratePlanQuery.setSort("pk_overdue_derate_plan");
			rzOverdueDeratePlanQuery.setOrder("desc");;
		}
		int total = rzOverdueDeratePlanDao.getRzOverdueDeratePlanByPageCount(rzOverdueDeratePlanQuery);
		PaginatedList<RzOverdueDeratePlan> rzOverdueDeratePlanPageList = new MysqlPaginatedArrayList<RzOverdueDeratePlan>(rzOverdueDeratePlanQuery,total);
		List<RzOverdueDeratePlan> rzOverdueDeratePlanList = rzOverdueDeratePlanDao.getRzOverdueDeratePlanByPage(rzOverdueDeratePlanQuery);
		rzOverdueDeratePlanPageList.addAll(rzOverdueDeratePlanList);
		GridResult<RzOverdueDeratePlan> result = new GridResult<RzOverdueDeratePlan>(rzOverdueDeratePlanPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzOverdueDeratePlanQuery
	 * @return
	 */
	@Override
	public int getRzOverdueDeratePlanByPageCount(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery){
		return rzOverdueDeratePlanDao.getRzOverdueDeratePlanByPageCount(rzOverdueDeratePlanQuery);
	}

	public void setRzOverdueDeratePlanDao(RzOverdueDeratePlanDao  rzOverdueDeratePlanDao){
		this.rzOverdueDeratePlanDao = rzOverdueDeratePlanDao;
	}
	
}