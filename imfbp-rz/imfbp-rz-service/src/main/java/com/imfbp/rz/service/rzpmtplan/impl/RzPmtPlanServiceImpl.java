package com.imfbp.rz.service.rzpmtplan.impl;

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
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.RzpmtplanVo;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.dao.rzpmtplan.RzPmtPlanDao;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.util.DateUtil;





@Component("rzPmtPlanService")
public class RzPmtPlanServiceImpl implements RzPmtPlanService{


	private RzPmtPlanDao rzPmtPlanDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPmtPlan
	 * @return
	 */
	@Override
	public void insertRzPmtPlan(RzPmtPlan rzPmtPlan){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPmtPlan.setTs(DateUtil.getTs());
		rzPmtPlan.setDr(0);
		rzPmtPlan.setPkPrjcontr(pk);
		rzPmtPlanDao.insertRzPmtPlan(rzPmtPlan);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlan>
	 * @return
	 */
	public void insertBatchRzPmtPlan(List<RzPmtPlan> rzPmtPlanList){
		if(rzPmtPlanList != null){
			for(int i=0;i<rzPmtPlanList.size();i++){
				rzPmtPlanList.get(i).setTs(DateUtil.getTs());
				rzPmtPlanList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPmtPlanList.get(i).setPkPrjcontr(pk);
			}
			rzPmtPlanDao.insertBatchRzPmtPlan(rzPmtPlanList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery){
		return rzPmtPlanDao.deleteRzPmtPlanById(rzPmtPlanQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanByCondition(RzPmtPlanQuery rzPmtPlanQuery){
		return rzPmtPlanDao.deleteRzPmtPlanByCondition(rzPmtPlanQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPmtPlanByBatchId(RzPmtPlanQuery rzPmtPlanQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPmtPlanQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPmtPlanDao.deleteRzPmtPlanByBatchId(data);	
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
	public boolean logicDeleteRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery){
		return rzPmtPlanDao.logicDeleteRzPmtPlanById(rzPmtPlanQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPmtPlanByCondition(RzPmtPlanQuery rzPmtPlanQuery){
		return rzPmtPlanDao.logicDeleteRzPmtPlanByCondition(rzPmtPlanQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzPmtPlanQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzPmtPlanByBatchId(RzPmtPlanQuery rzPmtPlanQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPmtPlanQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzPmtPlanDao.logicDeleteRzPmtPlanByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPmtPlan
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPmtPlan rzPmtPlan) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPmtPlan!=null){
				if(StringUtil.isNotEmpty(rzPmtPlan.getPkPrjcontr())){
					updateRzPmtPlanById(rzPmtPlan);
				}else{
					insertRzPmtPlan(rzPmtPlan);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPmtPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPmtPlan
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanById(RzPmtPlan rzPmtPlan){
		return rzPmtPlanDao.updateRzPmtPlanById(rzPmtPlan);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanByCondition(RzPmtPlanQuery record,RzPmtPlanQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPmtPlanDao.updateRzPmtPlanByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanQuery
	 * @return
	 */
	public Result updateRzPmtPlanByBatchId(List<RzPmtPlan> rzPmtPlanList){
		Result result = new Result(false);
		try {
			boolean flag = rzPmtPlanDao.updateRzPmtPlanByBatchId(rzPmtPlanList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public RzPmtPlan getRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery){
		return rzPmtPlanDao.getRzPmtPlanById(rzPmtPlanQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public List<RzPmtPlan> getRzPmtPlanAll(RzPmtPlanQuery rzPmtPlanQuery){
		return rzPmtPlanDao.getRzPmtPlanAll(rzPmtPlanQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public GridResult<RzPmtPlan> getRzPmtPlanByPage(RzpmtplanVo rzPmtPlanQuery){
		//如果排序的字段是空或者空字符串
		if(rzPmtPlanQuery!=null&&StringUtils.isBlank(rzPmtPlanQuery.getSort())){
			rzPmtPlanQuery.setSort("pk_pmt_plan");
			rzPmtPlanQuery.setOrder("desc");;
		}
		int total = rzPmtPlanDao.getRzPmtPlanByPageCount(rzPmtPlanQuery);
		PaginatedList<RzPmtPlan> rzPmtPlanPageList = new MysqlPaginatedArrayList<RzPmtPlan>(rzPmtPlanQuery,total);
		List<RzPmtPlan> rzPmtPlanList = rzPmtPlanDao.getRzPmtPlanByPage(rzPmtPlanQuery);
		rzPmtPlanPageList.addAll(rzPmtPlanList);
		GridResult<RzPmtPlan> result = new GridResult<RzPmtPlan>(rzPmtPlanPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanQuery
	 * @return
	 */
	@Override
	public int getRzPmtPlanByPageCount(RzpmtplanVo rzPmtPlanQuery){
		return rzPmtPlanDao.getRzPmtPlanByPageCount(rzPmtPlanQuery);
	}

	public void setRzPmtPlanDao(RzPmtPlanDao  rzPmtPlanDao){
		this.rzPmtPlanDao = rzPmtPlanDao;
	}

	@Override
	public String getMaxVersion(RzpmtplanVo rzPmtPlanQuery) {
		// TODO Auto-generated method stub
		return rzPmtPlanDao.getMaxVersion(rzPmtPlanQuery);
	}
	
}