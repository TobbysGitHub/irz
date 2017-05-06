package com.imfbp.rz.service.rzdefinterestplan.impl;

import java.util.ArrayList;
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
import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterest.query.RzDefInterestQuery;
import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;
import com.imfbp.rz.dao.rzdefinterestplan.RzDefInterestPlanDao;
import com.imfbp.rz.service.rzdefinterestplan.RzDefInterestPlanService;
import com.imfbp.rz.util.ToolUtils;





@Component("rzDefInterestPlanService")
public class RzDefInterestPlanServiceImpl implements RzDefInterestPlanService{


	private RzDefInterestPlanDao rzDefInterestPlanDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzDefInterestPlan
	 * @return
	 */
	@Override
	public void insertRzDefInterestPlan(RzDefInterestPlan rzDefInterestPlan){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzDefInterestPlan.setPkDefInterestPlan(pk);
		rzDefInterestPlanDao.insertRzDefInterestPlan(rzDefInterestPlan);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzDefInterestPlan>
	 * @return
	 */
	public void insertBatchRzDefInterestPlan(List<RzDefInterestPlan> rzDefInterestPlanList){
		if(rzDefInterestPlanList != null){
			for(int i=0;i<rzDefInterestPlanList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzDefInterestPlanList.get(i).setPkDefInterestPlan(pk);
			}
			rzDefInterestPlanDao.insertBatchRzDefInterestPlan(rzDefInterestPlanList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestPlanById(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return rzDefInterestPlanDao.deleteRzDefInterestPlanById(rzDefInterestPlanQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestPlanByCondition(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return rzDefInterestPlanDao.deleteRzDefInterestPlanByCondition(rzDefInterestPlanQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzDefInterestPlanQuery
	 * @return
	 */	
	@Override
	public Result deleteRzDefInterestPlanByBatchId(RzDefInterestPlanQuery rzDefInterestPlanQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzDefInterestPlanQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzDefInterestPlanDao.deleteRzDefInterestPlanByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzDefInterestPlan
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzDefInterestPlan rzDefInterestPlan) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzDefInterestPlan!=null){
				if(StringUtil.isNotEmpty(rzDefInterestPlan.getPkDefInterestPlan())){
					updateRzDefInterestPlanById(rzDefInterestPlan);
				}else{
					insertRzDefInterestPlan(rzDefInterestPlan);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzDefInterestPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzDefInterestPlan
	 * @return
	 */
	@Override
	public boolean updateRzDefInterestPlanById(RzDefInterestPlan rzDefInterestPlan){
		return rzDefInterestPlanDao.updateRzDefInterestPlanById(rzDefInterestPlan);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzDefInterestPlanByCondition(RzDefInterestPlanQuery record,RzDefInterestPlanQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzDefInterestPlanDao.updateRzDefInterestPlanByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	public Result updateRzDefInterestPlanByBatchId(List<RzDefInterestPlan> rzDefInterestPlanList){
		Result result = new Result(false);
		try {
			boolean flag = rzDefInterestPlanDao.updateRzDefInterestPlanByBatchId(rzDefInterestPlanList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public RzDefInterestPlan getRzDefInterestPlanById(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return rzDefInterestPlanDao.getRzDefInterestPlanById(rzDefInterestPlanQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public List<RzDefInterestPlan> getRzDefInterestPlanAll(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return rzDefInterestPlanDao.getRzDefInterestPlanAll(rzDefInterestPlanQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public GridResult<RzDefInterestPlan> getRzDefInterestPlanByPage(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		//如果排序的字段是空或者空字符串
		if(rzDefInterestPlanQuery!=null&&StringUtils.isBlank(rzDefInterestPlanQuery.getSort())){
			rzDefInterestPlanQuery.setSort("pk_def_interest_plan");
			rzDefInterestPlanQuery.setOrder("desc");;
		}
		int total = rzDefInterestPlanDao.getRzDefInterestPlanByPageCount(rzDefInterestPlanQuery);
		PaginatedList<RzDefInterestPlan> rzDefInterestPlanPageList = new MysqlPaginatedArrayList<RzDefInterestPlan>(rzDefInterestPlanQuery,total);
		List<RzDefInterestPlan> rzDefInterestPlanList = rzDefInterestPlanDao.getRzDefInterestPlanByPage(rzDefInterestPlanQuery);
		rzDefInterestPlanPageList.addAll(rzDefInterestPlanList);
		GridResult<RzDefInterestPlan> result = new GridResult<RzDefInterestPlan>(rzDefInterestPlanPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzDefInterestPlanQuery
	 * @return
	 */
	@Override
	public int getRzDefInterestPlanByPageCount(RzDefInterestPlanQuery rzDefInterestPlanQuery){
		return rzDefInterestPlanDao.getRzDefInterestPlanByPageCount(rzDefInterestPlanQuery);
	}

	public void setRzDefInterestPlanDao(RzDefInterestPlanDao  rzDefInterestPlanDao){
		this.rzDefInterestPlanDao = rzDefInterestPlanDao;
	}

	@Override
	public RzDefInterestPlanQuery getAllDeleteIds(List<RzDefInterestPlan> list) {
		// TODO Auto-generated method stub
		RzDefInterestPlanQuery query=new RzDefInterestPlanQuery();
		StringBuffer buffer=new StringBuffer();
		if(ToolUtils.isNotEmptyCollection(list)){
			int index=0;
			for(RzDefInterestPlan in:list){
			if(in != null){
				if((index != list.size()-1) && StringUtils.isNotEmpty(in.getPkDefInterestPlan())) buffer.append(in.getPkDefInterestPlan()+",");
				else if(StringUtils.isNotEmpty(in.getPkDefInterestPlan())) buffer.append(in.getPkDefInterestPlan());
			    }
			index++;
			}
		}
		if(buffer.length()>0) query.setBatchId(buffer.toString());
		return query;
	}
	
}