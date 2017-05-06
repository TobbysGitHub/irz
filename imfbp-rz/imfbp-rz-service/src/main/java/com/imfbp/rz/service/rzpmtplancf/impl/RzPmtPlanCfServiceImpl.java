package com.imfbp.rz.service.rzpmtplancf.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.dao.rzpmtplancf.RzPmtPlanCfDao;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplancf.RzPmtPlanCfService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.ToolUtils;





@Component("rzPmtPlanCfService")
public class RzPmtPlanCfServiceImpl implements RzPmtPlanCfService{


	private RzPmtPlanCfDao rzPmtPlanCfDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	 @Autowired
	private RzContrTallyService rzContrTallyService;
	 @Autowired
   private RzPmtPlanService rzPmtPlanService;
	 @Autowired
	 private RzPrjcontrService rzPrjcontrService;
	 @Autowired
	 private RzBaseParamService rzBaseParamService;

	/**
	 * 添加
	 * @param rzPmtPlanCf
	 * @return
	 */
	@Override
	public void insertRzPmtPlanCf(RzPmtPlanCf rzPmtPlanCf){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPmtPlanCf.setPkPmtPlanCf(pk);
		rzPmtPlanCfDao.insertRzPmtPlanCf(rzPmtPlanCf);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPmtPlanCf>
	 * @return
	 */
	public void insertBatchRzPmtPlanCf(List<RzPmtPlanCf> rzPmtPlanCfList){
		if(rzPmtPlanCfList != null){
			for(int i=0;i<rzPmtPlanCfList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPmtPlanCfList.get(i).setPkPmtPlanCf(pk);
			}
			rzPmtPlanCfDao.insertBatchRzPmtPlanCf(rzPmtPlanCfList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanCfById(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return rzPmtPlanCfDao.deleteRzPmtPlanCfById(rzPmtPlanCfQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPmtPlanCfByCondition(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return rzPmtPlanCfDao.deleteRzPmtPlanCfByCondition(rzPmtPlanCfQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPmtPlanCfQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPmtPlanCfByBatchId(RzPmtPlanCfQuery rzPmtPlanCfQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPmtPlanCfQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPmtPlanCfDao.deleteRzPmtPlanCfByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPmtPlanCf
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPmtPlanCf rzPmtPlanCf) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPmtPlanCf!=null){
				if(StringUtil.isNotEmpty(rzPmtPlanCf.getPkPmtPlanCf())){
					updateRzPmtPlanCfById(rzPmtPlanCf);
				}else{
					insertRzPmtPlanCf(rzPmtPlanCf);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPmtPlanCf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPmtPlanCf
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanCfById(RzPmtPlanCf rzPmtPlanCf){
		return rzPmtPlanCfDao.updateRzPmtPlanCfById(rzPmtPlanCf);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPmtPlanCfByCondition(RzPmtPlanCfQuery record,RzPmtPlanCfQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPmtPlanCfDao.updateRzPmtPlanCfByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	public Result updateRzPmtPlanCfByBatchId(List<RzPmtPlanCf> rzPmtPlanCfList){
		Result result = new Result(false);
		try {
			boolean flag = rzPmtPlanCfDao.updateRzPmtPlanCfByBatchId(rzPmtPlanCfList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public RzPmtPlanCf getRzPmtPlanCfById(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return rzPmtPlanCfDao.getRzPmtPlanCfById(rzPmtPlanCfQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public List<RzPmtPlanCf> getRzPmtPlanCfAll(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return rzPmtPlanCfDao.getRzPmtPlanCfAll(rzPmtPlanCfQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public GridResult<RzPmtPlanCf> getRzPmtPlanCfByPage(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		//如果排序的字段是空或者空字符串
		if(rzPmtPlanCfQuery!=null&&StringUtils.isBlank(rzPmtPlanCfQuery.getSort())){
			rzPmtPlanCfQuery.setSort("seq_no");
			rzPmtPlanCfQuery.setOrder("asc");
		}
		int total = rzPmtPlanCfDao.getRzPmtPlanCfByPageCount(rzPmtPlanCfQuery);
		PaginatedList<RzPmtPlanCf> rzPmtPlanCfPageList = new MysqlPaginatedArrayList<RzPmtPlanCf>(rzPmtPlanCfQuery,total);
		List<RzPmtPlanCf> rzPmtPlanCfList = rzPmtPlanCfDao.getRzPmtPlanCfByPage(rzPmtPlanCfQuery);
		rzPmtPlanCfPageList.addAll(rzPmtPlanCfList);
		GridResult<RzPmtPlanCf> result = new GridResult<RzPmtPlanCf>(rzPmtPlanCfPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPmtPlanCfQuery
	 * @return
	 */
	@Override
	public int getRzPmtPlanCfByPageCount(RzPmtPlanCfQuery rzPmtPlanCfQuery){
		return rzPmtPlanCfDao.getRzPmtPlanCfByPageCount(rzPmtPlanCfQuery);
	}

	public void setRzPmtPlanCfDao(RzPmtPlanCfDao  rzPmtPlanCfDao){
		this.rzPmtPlanCfDao = rzPmtPlanCfDao;
	}

	@Override
	public void insertToTally(RzPmtPlanCf rzPmtPlanCf) {
		// TODO Auto-generated method stub
		  RzContrTally rzContrTally=new RzContrTally();
		    RzPmtPlanCfQuery rzPmtPlanCfQuery=new RzPmtPlanCfQuery();
		    rzPmtPlanCfQuery.setPkPmtPlanCf(rzPmtPlanCf.getPkPmtPlanCf());
		    rzPmtPlanCf=   rzPmtPlanCfDao.getRzPmtPlanCfById(rzPmtPlanCfQuery);
		    if(rzPmtPlanCf == null) return;
			RzPmtPlanQuery rzPmtPlanQuery=new RzPmtPlanQuery();
			rzPmtPlanQuery.setPkPmtPlan(rzPmtPlanCf.getPkPmtPlan());
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
						rzContrTally.setReceivableAmt(rzPmtPlanCf.getReceivableAmt());
						rzContrTally.setReceivableFee(rzPmtPlanCf.getReceivableFee());
						rzContrTally.setReceivableInt(rzPmtPlanCf.getReceivableInt());
						rzContrTally.setReceivableRent(rzPmtPlanCf.getReceivableRent());
						rzContrTally.setReceivablDefInt(rzPmtPlanCf.getReceivablDefInt());
						rzContrTally.setRealPrin(rzPmtPlanCf.getActAmt());
						rzContrTally.setRealInt(rzPmtPlanCf.getActInt());
						rzContrTally.setRealFee(rzPmtPlanCf.getActFee());
						rzContrTally.setRealRent(rzPmtPlanCf.getActRent());
						rzContrTally.setRealDefInt(rzPmtPlanCf.getActDefInt());
						rzContrTally.setAccruedDefInt(DoubleUtils.getDoubleNullAsZero(
								rzPmtPlanCf.getReceivablDefInt())-DoubleUtils.getDoubleNullAsZero(rzPmtPlanCf.getActDefInt()));
						rzContrTally.setCurPricerate(rzPmtPlanCf.getLeaseRate());
						String date=rzPmtPlanCf.getPlanpmtdate();
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
}