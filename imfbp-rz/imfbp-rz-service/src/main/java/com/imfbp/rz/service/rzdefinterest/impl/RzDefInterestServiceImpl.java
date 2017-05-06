package com.imfbp.rz.service.rzdefinterest.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.pubfileinfo.PubFileinfoDao;
import com.imfbp.rz.dao.rzdefinterest.RzDefInterestDao;
import com.imfbp.rz.dao.rzdefinterestplan.RzDefInterestPlanDao;
import com.imfbp.rz.dao.rzpmtplan.RzPmtPlanDao;
import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;
import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterest.query.RzDefInterestQuery;
import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzdefinterest.RzDefInterestService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;





@Component("rzDefInterestService")
public class RzDefInterestServiceImpl implements RzDefInterestService{


	private RzDefInterestDao rzDefInterestDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	
	@Autowired
	private BillnoService billnoService;
	@Autowired
	private RzBaseParamService rzBaseParamService;
	@Autowired
	private RzPmtPlanService rzPmtPlanService;
	@Autowired
	private RzPmtPlanLeaseService rzPmtPlanLeaseService;
	private RzDefInterestPlanDao rzDefInterestPlanDao;
	private RzPrjcontrDao rzPrjcontrDao;
	private RzPmtPlanDao rzPmtPlanDao;
	private PubFileinfoDao pubFileinfoDao;
	/**
	 * 添加
	 * @param rzDefInterest
	 * @return
	 */
	@Override
	public void insertRzDefInterest(RzDefInterest rzDefInterest){
		RzDefInterestQuery query=new RzDefInterestQuery();
		query.setPkPrjcontr(rzDefInterest.getPkPrjcontr());
		List<RzDefInterest>rzDefInterestList=rzDefInterestDao.getRzDefInterestAll(query);
		if(ToolUtils.isNotEmptyCollection(rzDefInterestList)) return;
		String pk = primaryKeyUtil.getPrimaryKey();
		rzDefInterest.setApprovestatus(0);
		rzDefInterest.setTs(DateUtil.getTs());
		rzDefInterest.setDr(0);
		rzDefInterest.setPkDefInterest(pk);
		String billno;
		try {
			billno = billnoService.getBillno(INodeConsts.RZ_PRICE_NO);
			rzDefInterest.setDefIntNo(billno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String interestLease=rzDefInterest.getInterestLease();
		List<RzDefInterestPlan>list=new ArrayList<RzDefInterestPlan>();
		if(StringUtils.isNotEmpty(interestLease)){
			List<?> listTemp= JSONArray.fromObject(interestLease.trim());
			for(Object obj:listTemp) {
				JSONObject json=(JSONObject)obj;
				@SuppressWarnings("static-access")
				RzDefInterestPlan plan=(RzDefInterestPlan) json.toBean(json,RzDefInterestPlan.class);
				plan.setPkDefInterestPlan(primaryKeyUtil.getPrimaryKey());
				plan.setPkDefInterest(pk);
				list.add(plan);
			}
		}
		rzDefInterest.setInterestLease(null);
		rzDefInterestDao.insertRzDefInterest(rzDefInterest);
		if(ToolUtils.isNotEmptyCollection(list)) rzDefInterestPlanDao.insertBatchRzDefInterestPlan(list);
		
	}
	
	/**
	 * 批量添加
	 * @param List<rzDefInterest>
	 * @return
	 */
	public void insertBatchRzDefInterest(List<RzDefInterest> rzDefInterestList){
		if(rzDefInterestList != null){
			for(int i=0;i<rzDefInterestList.size();i++){
				rzDefInterestList.get(i).setTs(DateUtil.getTs());
				rzDefInterestList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzDefInterestList.get(i).setPkDefInterest(pk);
			}
			rzDefInterestDao.insertBatchRzDefInterest(rzDefInterestList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery){
		if(rzDefInterestQuery == null || StringUtils.isEmpty(rzDefInterestQuery.getPkDefInterest())) return false;
		RzDefInterestPlanQuery rzDefInterestPlanQuery=new RzDefInterestPlanQuery();
		rzDefInterestPlanQuery.setPkDefInterest(rzDefInterestQuery.getPkDefInterest());
		List<RzDefInterestPlan>pks=rzDefInterestPlanDao.getRzDefInterestPlanAll(rzDefInterestPlanQuery);
		List<String>pk=new ArrayList<String>();
		if(ToolUtils.isNotEmptyCollection(pks)){
			for(RzDefInterestPlan plan:pks) pk.add(plan.getPkDefInterestPlan());
		}
		//批量删除附件表中的数据
		List<PubFileinfo>pubFileinfoList=pubFileinfoDao.getPubFileinfoAll(new PubFileinfoQuery());
		Map<String,String>tempFile=new HashMap<String,String>();
		if(ToolUtils.isNotEmptyCollection(pubFileinfoList)){
			for(PubFileinfo info:pubFileinfoList){
				if(info != null) tempFile.put(info.getPkFile(), info.getPkBill());
			}
		}
		List<String>infoPks=new ArrayList<String>();
				for(String infoPk:tempFile.keySet()){
					if(StringUtils.isNotEmpty(tempFile.get(infoPk)) && tempFile.get(infoPk).equals(rzDefInterestQuery.getPkDefInterest())){
						infoPks.add(infoPk);
					}
		}
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>data2=new HashMap<String,Object>();
		map.put("batchId1", pk);
		data2.put("batchId1", data2);
		if(ToolUtils.isNotEmptyCollection(pk)){
		rzDefInterestPlanDao.deleteRzDefInterestPlanByBatchId(map);
		}
		if(ToolUtils.isNotEmptyCollection(infoPks)){
			pubFileinfoDao.deletePubFileinfoByBatchId(data2);
	    }
		return rzDefInterestDao.deleteRzDefInterestById(rzDefInterestQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public boolean deleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery){
		return rzDefInterestDao.deleteRzDefInterestByCondition(rzDefInterestQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzDefInterestQuery
	 * @return
	 */	
	@Override
	public Result deleteRzDefInterestByBatchId(RzDefInterestQuery rzDefInterestQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			Map<String,Object>data1=new HashMap<String,Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzDefInterestQuery.getBatchId().split(",");
			//批量删除逾期计划表子表
			List<RzDefInterestPlan>planList=rzDefInterestPlanDao.getRzDefInterestPlanAll(new RzDefInterestPlanQuery());
			Map<String,String>temp=new HashMap<String,String>();
			if(ToolUtils.isNotEmptyCollection(planList)){
				for(RzDefInterestPlan plan:planList){
					if(plan != null) temp.put(plan.getPkDefInterestPlan(), plan.getPkDefInterest());
				}
			}
			//批量删除附件表中的数据
			List<PubFileinfo>pubFileinfoList=pubFileinfoDao.getPubFileinfoAll(new PubFileinfoQuery());
			Map<String,String>tempFile=new HashMap<String,String>();
			if(ToolUtils.isNotEmptyCollection(pubFileinfoList)){
				for(PubFileinfo info:pubFileinfoList){
					if(info != null) tempFile.put(info.getPkFile(), info.getPkBill());
				}
			}
			List<String>planPks=new ArrayList<String>();
			if(batchIdArr != null &&batchIdArr.length>0){
				for(String str:batchIdArr){
					for(String planPk:temp.keySet()){
						if(StringUtils.isNotEmpty(temp.get(planPk)) && temp.get(planPk).equals(str)){
							planPks.add(planPk);
						}
					}
				}
			}
			List<String>infoPks=new ArrayList<String>();
			if(batchIdArr != null &&batchIdArr.length>0){
				for(String str:batchIdArr){
					for(String infoPk:tempFile.keySet()){
						if(StringUtils.isNotEmpty(tempFile.get(infoPk)) && tempFile.get(infoPk).equals(str)){
							infoPks.add(infoPk);
						}
					}
				}
			}
			data.put("batchId1",batchIdArr);
			data1.put("batchId1", planPks);
			Map<String,Object>data2=new HashMap<String,Object>();
			data2.put("batchId1", infoPks);
			boolean flat=true,flag=true,flag2=true;
			flat = rzDefInterestDao.deleteRzDefInterestByBatchId(data);
			if(ToolUtils.isNotEmptyCollection(planPks)){
			flag=rzDefInterestPlanDao.deleteRzDefInterestPlanByBatchId(data1);
			}
			if(ToolUtils.isNotEmptyCollection(infoPks)){
			flag2 =pubFileinfoDao.deletePubFileinfoByBatchId(data2);
			}
			if(flat && flag && flag2)
			result.setSuccess(true);
			else result.setSuccess(false);
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
	public boolean logicDeleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery){
		return rzDefInterestDao.logicDeleteRzDefInterestById(rzDefInterestQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzDefInterestByCondition(RzDefInterestQuery rzDefInterestQuery){
		return rzDefInterestDao.logicDeleteRzDefInterestByCondition(rzDefInterestQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzDefInterestQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzDefInterestByBatchId(RzDefInterestQuery rzDefInterestQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzDefInterestQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzDefInterestDao.logicDeleteRzDefInterestByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzDefInterest
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzDefInterest rzDefInterest) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzDefInterest!=null){
				if(StringUtil.isNotEmpty(rzDefInterest.getPkDefInterest())){
					updateRzDefInterestById(rzDefInterest);
				}else{
					insertRzDefInterest(rzDefInterest);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzDefInterest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzDefInterest
	 * @return
	 */
	@Override
	public boolean updateRzDefInterestById(RzDefInterest rzDefInterest){
		String interestLease=rzDefInterest.getInterestLease();
		List<RzDefInterestPlan>list=new ArrayList<RzDefInterestPlan>();
		if(StringUtils.isNotEmpty(interestLease)){
			try {
				interestLease=new String(interestLease.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//JSONArray jsonArray=new JSONArray();
			List<?> listTemp= JSONArray.fromObject(interestLease.trim());
			for(Object obj:listTemp) {
				JSONObject json=(JSONObject)obj;
				@SuppressWarnings("static-access")
				RzDefInterestPlan plan=(RzDefInterestPlan) json.toBean(json,RzDefInterestPlan.class);
				list.add(plan);
			}
		}
		rzDefInterest.setInterestLease(null);
		if(ToolUtils.isNotEmptyCollection(list)) rzDefInterestPlanDao.updateRzDefInterestPlanByBatchId(list);
		return rzDefInterestDao.updateRzDefInterestById(rzDefInterest);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzDefInterestByCondition(RzDefInterestQuery record,RzDefInterestQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzDefInterestDao.updateRzDefInterestByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzDefInterestQuery
	 * @return
	 */
	public Result updateRzDefInterestByBatchId(List<RzDefInterest> rzDefInterestList){
		Result result = new Result(false);
		try {
			boolean flag = rzDefInterestDao.updateRzDefInterestByBatchId(rzDefInterestList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public RzDefInterest getRzDefInterestById(RzDefInterestQuery rzDefInterestQuery){
		return rzDefInterestDao.getRzDefInterestById(rzDefInterestQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public List<RzDefInterest> getRzDefInterestAll(RzDefInterestQuery rzDefInterestQuery){
		return rzDefInterestDao.getRzDefInterestAll(rzDefInterestQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public GridResult<RzDefInterest> getRzDefInterestByPage(RzDefInterestQuery rzDefInterestQuery){
		//如果排序的字段是空或者空字符串
		if(rzDefInterestQuery!=null&&StringUtils.isBlank(rzDefInterestQuery.getSort())){
			rzDefInterestQuery.setSort("def_int_no");
			rzDefInterestQuery.setOrder("desc");;
		}
		int total = rzDefInterestDao.getRzDefInterestByPageCount(rzDefInterestQuery);
		PaginatedList<RzDefInterest> rzDefInterestPageList = new MysqlPaginatedArrayList<RzDefInterest>(rzDefInterestQuery,total);
		List<RzDefInterest> rzDefInterestList = rzDefInterestDao.getRzDefInterestByPage(rzDefInterestQuery);
		if(StringUtils.isNotEmpty(rzDefInterestQuery.getBusitype())){
			if(ToolUtils.isNotEmptyCollection(rzDefInterestList)){
				List<RzPrjcontr> lists=rzPrjcontrDao.getRzPrjcontrAll(new RzPrjcontrQuery());
				Map<String,String>map=new HashMap<String,String>();
				if(ToolUtils.isNotEmptyCollection(lists)){
					for(RzPrjcontr tr:lists){
						if(tr != null && StringUtils.isNotEmpty(tr.getPkPrjcontr()) && StringUtils.isNotEmpty(tr.getBusitype())){
							map.put(tr.getPkPrjcontr(), tr.getBusitype());
						}
					}
				}
				for(Iterator<RzDefInterest>it=rzDefInterestList.iterator();it.hasNext();){
					RzDefInterest in=it.next();
					if(in != null && StringUtils.isNotEmpty(in.getPkPrjcontr())){
						String type=map.get(in.getPkPrjcontr());
						if( type.equals(rzDefInterestQuery.getBusitype())){
							rzDefInterestList.remove(in);
						}
					}
				}
			}
		}
		rzDefInterestPageList.addAll(rzDefInterestList);
		GridResult<RzDefInterest> result = new GridResult<RzDefInterest>(rzDefInterestPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzDefInterestQuery
	 * @return
	 */
	@Override
	public int getRzDefInterestByPageCount(RzDefInterestQuery rzDefInterestQuery){
		return rzDefInterestDao.getRzDefInterestByPageCount(rzDefInterestQuery);
	}

	public void setRzDefInterestDao(RzDefInterestDao  rzDefInterestDao){
		this.rzDefInterestDao = rzDefInterestDao;
	}

	@Override
	public Map<String,Object> getLeaseByContr(RzDefInterestQuery rzDefInterestQuery) {
		// TODO Auto-generated method stub
		if (rzDefInterestQuery == null) {
			rzDefInterestQuery = new RzDefInterestQuery();
		}
		if(StringUtils.isEmpty(rzDefInterestQuery.getPkPrjcontr())) return null;
		Map<String,Object>map=new HashMap<String, Object>();
		int rpmtTerm=0;
		double overdueRate=0;
		List<RzBaseParam>list=rzBaseParamService.getRzBaseParamAll(new RzBaseParamQuery());
		if(ToolUtils.isNotEmptyCollection(list)){
			rpmtTerm=list.get(0).getRpmtTerm();
			overdueRate=DoubleUtils.getDoubleNullAsZero(list.get(0).getOverdueRate()/100);
		}
		int days=0;
		List<RzPmtPlanLease>rzPmtPlanLeaseLists=new ArrayList<RzPmtPlanLease>();
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=format.format(date);
		if(StringUtils.isEmpty(rzDefInterestQuery.getPkPrjcontr())) return null;
		String pk=rzDefInterestQuery.getPkPrjcontr();
		RzPmtPlanQuery query=new RzPmtPlanQuery();
		query.setPkPrjcontr(pk);
		RzPmtPlan rzPmtPlan = rzPmtPlanService.getRzPmtPlanById(query);
		if(rzPmtPlan != null &&StringUtils.isNotEmpty(rzPmtPlan.getPkPmtPlan())){
		String pkPlan=rzPmtPlan.getPkPmtPlan();
		RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery=new RzPmtPlanLeaseQuery();
		rzPmtPlanLeaseQuery.setPkPmtPlan(pkPlan);
		List<RzPmtPlanLease>rzPmtPlanLeaseList= rzPmtPlanLeaseService.getRzPmtPlanLeaseAll(rzPmtPlanLeaseQuery);
		if(ToolUtils.isNotEmptyCollection(rzPmtPlanLeaseList)){
			for(RzPmtPlanLease le:rzPmtPlanLeaseList){
				if(le != null && le.getSeqNo() !=10){
					String check=le.getIsCheck();
					if(check == null || (StringUtils.isNotEmpty(check)&& !check.equals("Y"))){
						String rptDate=le.getRptDate();
						if(StringUtils.isNotEmpty(rptDate) && currentDate.compareTo(rptDate)>0){
						try {
							days = DateUtil.daysBetween(rptDate, currentDate);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(days >rpmtTerm) rzPmtPlanLeaseLists.add(le);
							}
						}
					}
				}
			}
		}
		//逾期天数，宽限天数,逾期利率
		map.put("overdueDays", days);
		map.put("rpmtTerm", rpmtTerm);
		map.put("overdueRate", overdueRate);
		map.put("rzPmtPlanLeaseLists", rzPmtPlanLeaseLists);
		return map;
	}

	@Override
	public RzDefInterestQuery getAllDeleteIds(List<RzDefInterest> list) {
		// TODO Auto-generated method stub
				RzDefInterestQuery query=new RzDefInterestQuery();
				StringBuffer buffer=new StringBuffer();
				if(ToolUtils.isNotEmptyCollection(list)){
					int index=0;
					for(RzDefInterest in:list){
					if(in != null){
						if((index != list.size()-1) && StringUtils.isNotEmpty(in.getPkDefInterest())) buffer.append(in.getPkDefInterest()+",");
						else if(StringUtils.isNotEmpty(in.getPkDefInterest())) buffer.append(in.getPkDefInterest());
					    }
					index++;
					}
				}
				if(buffer.length()>0) query.setBatchId(buffer.toString());
				return query;
			}

	@Override
	public Map<String,Object> getPlanByContr(RzDefInterestQuery rzDefInterestQuery) {
		// TODO Auto-generated method stub
		Map<String,Object>map=getLeaseByContr(rzDefInterestQuery);
		Map<String,Object>result=new HashMap<String,Object>();
		List<RzDefInterestPlan>rzDefInterestPlanList=new ArrayList<RzDefInterestPlan>();
		if(map != null && map.size()>0){
			@SuppressWarnings("unchecked")
			List<RzPmtPlanLease>rzPmtPlanLeaseList=(List<RzPmtPlanLease>) map.get("rzPmtPlanLeaseLists");
			if(ToolUtils.isNotEmptyCollection(rzPmtPlanLeaseList)){
			double overdueAmts=0;
			int overdueDays=0;
			double receivableDefIntAmt=0;
			for(RzPmtPlanLease le:rzPmtPlanLeaseList){
			RzDefInterestPlan rzDefInterestPlan=new RzDefInterestPlan();
			rzDefInterestPlan.setSeqno(le.getSeqNo());
			rzDefInterestPlan.setNum(le.getNum().toString());
			rzDefInterestPlan.setRptDate(le.getRptDate());
			rzDefInterestPlan.setReceivableTotal(le.getRptTotal());
			rzDefInterestPlan.setRealTotal(le.getActTotal());
			//逾期金额
			double overdueAmt=DoubleUtils.getDoubleNullAsZero(le.getRptTotal())-DoubleUtils.getDoubleNullAsZero(le.getActTotal());
			rzDefInterestPlan.setReceivableNrcyTotal(String.valueOf(overdueAmt));
			//逾期天数
			int overdueDay=map.get("overdueDays")==null?0:(Integer)map.get("overdueDays");
			rzDefInterestPlan.setOverdueDays(overdueDay);
			overdueAmts+=overdueAmt;
			overdueDays+=overdueDay;
			//逾期利率
			double overdueRate=DoubleUtils.getDoubleNullAsZero((Double)map.get("overdueRate"));
			rzDefInterestPlan.setOverdueRate(overdueRate*100);
			receivableDefIntAmt+=overdueAmt*overdueRate*overdueDay;
			rzDefInterestPlan.setDefIntAmt(overdueAmt*overdueRate*overdueDay);
			/**开始逾期类型的判断**/
			double rptAmt=DoubleUtils.getDoubleNullAsZero(le.getRptAmt());//应还款本金
			double rptInt=DoubleUtils.getDoubleNullAsZero(le.getRptInt());//应还款利息
			double rptRent=DoubleUtils.getDoubleNullAsZero(le.getRptRent());//应还款租金
			double actTotal=DoubleUtils.getDoubleNullAsZero(le.getActTotal());//实际还款金额
			if(rptAmt<=actTotal && actTotal< rptRent) rzDefInterestPlan.setOverType(1);
			else if(actTotal < rptAmt && actTotal<rptInt)rzDefInterestPlan.setOverType(2);
			else rzDefInterestPlan.setOverType(0);
			rzDefInterestPlanList.add(rzDefInterestPlan);
			}
			result.put("overdueAmts", overdueAmts);
			result.put("overdueDays", overdueDays);
			result.put("receivableDefIntAmt", receivableDefIntAmt);
			result.put("rzDefInterestPlanList", rzDefInterestPlanList);
		   }
		}
		return result;
	}

	public void setRzDefInterestPlanDao(RzDefInterestPlanDao rzDefInterestPlanDao) {
		this.rzDefInterestPlanDao = rzDefInterestPlanDao;
	}

	public void setRzPrjcontrDao(RzPrjcontrDao rzPrjcontrDao) {
		this.rzPrjcontrDao = rzPrjcontrDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getContrByDef(RzDefInterestQuery rzDefInterestQuery) {
		// TODO Auto-generated method stub
		List<RzPmtPlan>plans=rzPmtPlanDao.getRzPmtPlanAll(new RzPmtPlanQuery());
		List<String>result=new ArrayList<String>();
		Map<String,String>temp=new HashMap<String,String>();
		List<RzDefInterest>rzDefInterestList=rzDefInterestDao.getRzDefInterestAll(new RzDefInterestQuery());
		if(ToolUtils.isNotEmptyCollection(rzDefInterestList)){
			for(RzDefInterest in:rzDefInterestList){
				if(in != null && StringUtils.isNotEmpty(in.getPkPrjcontr())){
					temp.put("pkPrjcontr", in.getPkPrjcontr());
				}
			}
		}
		if(ToolUtils.isNotEmptyCollection(plans)){
			for(RzPmtPlan plan:plans){
				if(plan != null){
					String pk=plan.getPkPrjcontr();
					RzDefInterestQuery query=new RzDefInterestQuery();
					query.setPkPrjcontr(pk);
					Map<String,Object>map=getLeaseByContr(query);
					if(map != null){
						if(ToolUtils.isNotEmptyCollection((List<RzPmtPlan>)map.get("rzPmtPlanLeaseLists"))){
							if(!temp.containsValue(pk))
							result.add(pk);
						}
					}
				}
			}
		}
		
		return result;
	}

	public RzPmtPlanDao getRzPmtPlanDao() {
		return rzPmtPlanDao;
	}

	public void setRzPmtPlanDao(RzPmtPlanDao rzPmtPlanDao) {
		this.rzPmtPlanDao = rzPmtPlanDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getContrByDefEdit(RzDefInterestQuery rzDefInterestQuery) {
		// TODO Auto-generated method stub
		List<RzPmtPlan>plans=rzPmtPlanDao.getRzPmtPlanAll(new RzPmtPlanQuery());
		List<String>result=new ArrayList<String>();
		if(ToolUtils.isNotEmptyCollection(plans)){
			for(RzPmtPlan plan:plans){
				if(plan != null){
					String pk=plan.getPkPrjcontr();
					RzDefInterestQuery query=new RzDefInterestQuery();
					query.setPkPrjcontr(pk);
					Map<String,Object>map=getLeaseByContr(query);
					if(map != null){
						if(ToolUtils.isNotEmptyCollection((List<RzPmtPlan>)map.get("rzPmtPlanLeaseLists"))){
							result.add(pk);
						}
					}
				}
			}
		}
		
		return result;
	}

	public void setPubFileinfoDao(PubFileinfoDao pubFileinfoDao) {
		this.pubFileinfoDao = pubFileinfoDao;
	}

}