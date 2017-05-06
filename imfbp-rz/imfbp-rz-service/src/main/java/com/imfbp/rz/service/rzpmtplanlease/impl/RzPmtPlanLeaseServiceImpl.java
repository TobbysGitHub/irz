package com.imfbp.rz.service.rzpmtplanlease.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.dao.rzpmtplanlease.RzPmtPlanLeaseDao;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.ToolUtils;





@Component("rzPmtPlanLeaseService")
public class RzPmtPlanLeaseServiceImpl implements RzPmtPlanLeaseService{


	private RzPmtPlanLeaseDao rzPmtPlanLeaseDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	 @Autowired
	 private RzPmtPlanService rzPmtPlanService;
	 @Autowired
	private  RzBaseParamService rzBaseParamService;
	 @Autowired
	 private RzPrjcontrService rzPrjcontrService;
	 @Autowired
	private RzContrTallyService rzContrTallyService;

	/**
	 * 添加
	 * @param rzPmtPlanLease
	 * @return
	 */
	@Override
	public void insertRzPmtPlanLease(RzPmtPlanLease rzPmtPlanLease){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPmtPlanLease.setPkPmtPlanLease(pk);
		rzPmtPlanLeaseDao.insertRzPmtPlanLease(rzPmtPlanLease);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlanLease>
	 * @return
	 */
	public void insertBatchRzPmtPlanLease(List<RzPmtPlanLease> rzPmtPlanLeaseList){
		if(rzPmtPlanLeaseList != null){
			for(int i=0;i<rzPmtPlanLeaseList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPmtPlanLeaseList.get(i).setPkPmtPlanLease(pk);
			}
			rzPmtPlanLeaseDao.insertBatchRzPmtPlanLease(rzPmtPlanLeaseList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return rzPmtPlanLeaseDao.deleteRzPmtPlanLeaseById(rzPmtPlanLeaseQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanLeaseByCondition(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return rzPmtPlanLeaseDao.deleteRzPmtPlanLeaseByCondition(rzPmtPlanLeaseQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPmtPlanLeaseByBatchId(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPmtPlanLeaseQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPmtPlanLeaseDao.deleteRzPmtPlanLeaseByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPmtPlanLease
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPmtPlanLease rzPmtPlanLease) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPmtPlanLease!=null){
				if(StringUtil.isNotEmpty(rzPmtPlanLease.getPkPmtPlanLease())){
					updateRzPmtPlanLeaseById(rzPmtPlanLease);
				}else{
					insertRzPmtPlanLease(rzPmtPlanLease);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPmtPlanLease);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPmtPlanLease
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanLeaseById(RzPmtPlanLease rzPmtPlanLease){
		return rzPmtPlanLeaseDao.updateRzPmtPlanLeaseById(rzPmtPlanLease);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanLeaseByCondition(RzPmtPlanLeaseQuery record,RzPmtPlanLeaseQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPmtPlanLeaseDao.updateRzPmtPlanLeaseByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	public Result updateRzPmtPlanLeaseByBatchId(List<RzPmtPlanLease> rzPmtPlanLeaseList){
		Result result = new Result(false);
		try {
			boolean flag = rzPmtPlanLeaseDao.updateRzPmtPlanLeaseByBatchId(rzPmtPlanLeaseList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public RzPmtPlanLease getRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return rzPmtPlanLeaseDao.getRzPmtPlanLeaseById(rzPmtPlanLeaseQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public List<RzPmtPlanLease> getRzPmtPlanLeaseAll(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return rzPmtPlanLeaseDao.getRzPmtPlanLeaseAll(rzPmtPlanLeaseQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public GridResult<RzPmtPlanLease> getRzPmtPlanLeaseByPage(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		//如果排序的字段是空或者空字符串
		if(rzPmtPlanLeaseQuery!=null&&StringUtils.isBlank(rzPmtPlanLeaseQuery.getSort())){
			rzPmtPlanLeaseQuery.setSort("seq_no");
			rzPmtPlanLeaseQuery.setOrder("asc");
		}
		int total = rzPmtPlanLeaseDao.getRzPmtPlanLeaseByPageCount(rzPmtPlanLeaseQuery);
		PaginatedList<RzPmtPlanLease> rzPmtPlanLeasePageList = new MysqlPaginatedArrayList<RzPmtPlanLease>(rzPmtPlanLeaseQuery,total);
		List<RzPmtPlanLease> rzPmtPlanLeaseList = rzPmtPlanLeaseDao.getRzPmtPlanLeaseByPage(rzPmtPlanLeaseQuery);
		rzPmtPlanLeasePageList.addAll(rzPmtPlanLeaseList);
		GridResult<RzPmtPlanLease> result = new GridResult<RzPmtPlanLease>(rzPmtPlanLeasePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanLeaseQuery
	 * @return
	 */
	@Override
	public int getRzPmtPlanLeaseByPageCount(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery){
		return rzPmtPlanLeaseDao.getRzPmtPlanLeaseByPageCount(rzPmtPlanLeaseQuery);
	}

	public void setRzPmtPlanLeaseDao(RzPmtPlanLeaseDao  rzPmtPlanLeaseDao){
		this.rzPmtPlanLeaseDao = rzPmtPlanLeaseDao;
	}

	@Override
	public void insertToTally(RzPmtPlanLease rzPmtPlanLease) {
		// TODO Auto-generated method stub
		   RzContrTally rzContrTally=new RzContrTally();
		    RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery=new RzPmtPlanLeaseQuery();
		    rzPmtPlanLeaseQuery.setPkPmtPlanLease(rzPmtPlanLease.getPkPmtPlanLease());
		    rzPmtPlanLease=   rzPmtPlanLeaseDao.getRzPmtPlanLeaseById(rzPmtPlanLeaseQuery);
		    if(rzPmtPlanLease == null) return;
			RzPmtPlanQuery rzPmtPlanQuery=new RzPmtPlanQuery();
			rzPmtPlanQuery.setPkPmtPlan(rzPmtPlanLease.getPkPmtPlan());
		     List<RzPmtPlan>rzPmtPlanList=	rzPmtPlanService.getRzPmtPlanAll(rzPmtPlanQuery);
		     int rpmtTerm=0;
			double overdueRate=0;
			List<RzBaseParam>list=rzBaseParamService.getRzBaseParamAll(new RzBaseParamQuery());
			if(ToolUtils.isNotEmptyCollection(list)){
					rpmtTerm=list.get(0).getRpmtTerm();
					overdueRate=DoubleUtils.getDoubleNullAsZero(list.get(0).getOverdueRate()/100);
			}
			if(ToolUtils.isNotEmptyCollection(rzPmtPlanList)){
				RzPmtPlan plan=rzPmtPlanList.get(0);
				if(StringUtils.isNotEmpty(plan.getPkPrjcontr())){
					RzPrjcontrQuery rzPrjcontrQuery=new RzPrjcontrQuery();
					rzPrjcontrQuery.setPkPrjcontr(plan.getPkPrjcontr());
					RzPrjcontr rzPrjcontr = rzPrjcontrService.getRzPrjcontrById(rzPrjcontrQuery);
					if(rzPrjcontr != null){
						rzContrTally.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
						rzContrTally.setPrjCode(rzPrjcontr.getPrjCode());
						rzContrTally.setPrjName(rzPrjcontr.getPrjName());
						rzContrTally.setContrName(rzPrjcontr.getContrName());
						rzContrTally.setContrCode(rzPrjcontr.getContrCode());
						rzContrTally.setReceivableAmt(rzPmtPlanLease.getRptAmt());
						rzContrTally.setReceivableFee(rzPmtPlanLease.getPayFee());
						rzContrTally.setReceivableInt(rzPmtPlanLease.getRptInt());
						rzContrTally.setReceivableRent(rzPmtPlanLease.getRptRent());
						rzContrTally.setReceivablDefInt(rzPmtPlanLease.getRptDefInt());
						rzContrTally.setRealPrin(rzPmtPlanLease.getActAmt());
						rzContrTally.setRealInt(rzPmtPlanLease.getActInt());
						rzContrTally.setRealFee(rzPmtPlanLease.getActPayFee());
						rzContrTally.setRealDefInt(rzPmtPlanLease.getActDefInt());
						rzContrTally.setRealRent(DoubleUtils.getDoubleNullAsZero(
								rzPmtPlanLease.getActAmt())+DoubleUtils.getDoubleNullAsZero(rzPmtPlanLease.getActInt()));
						rzContrTally.setAccruedDefInt(DoubleUtils.getDoubleNullAsZero(
								rzPmtPlanLease.getRptDefInt())-DoubleUtils.getDoubleNullAsZero(rzPmtPlanLease.getActDefInt()));
						//设置租赁利率
						rzContrTally.setCurPricerate(rzPmtPlanLease.getLeaseRate());
						String date=rzPmtPlanLease.getRptDate();
						if(StringUtils.isNotEmpty(date)){
							Date dates=new Date();
							SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
						    String nowDate=format.format(dates);
						    if(date.compareTo(nowDate)<0){
						    try {
								int	days = DateUtil.daysBetween(date, nowDate);
								if(days>rpmtTerm) rzContrTally.setIsOverdue("Y");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    }
						}
						rzContrTally.setOverdueRate(overdueRate);
					}
				}
			}
			rzContrTallyService.addRzContrTally(rzContrTally);
		
	}

	@Override
	public List<RzPmtPlanLease> getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery) {
		return rzPmtPlanLeaseDao.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(rzPmtPlanLeaseQuery);
	}

	@Override
	public List<RzPmtPlanLease> getAllMaxVerGroupByrzPmtPlan() {
		return rzPmtPlanLeaseDao.getAllMaxVerGroupByrzPmtPlan();
	}
	
}