package com.imfbp.rz.service.rzplanchange.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzplanchange.RzPlanChangeDao;
import com.imfbp.rz.dao.rzplanchangelease.RzPlanChangeLeaseDao;
import com.imfbp.rz.dao.rzplanchangeleasenew.RzPlanChangeLeaseNewDao;
import com.imfbp.rz.dao.rzpmtplan.RzPmtPlanDao;
import com.imfbp.rz.dao.rzpmtplancf.RzPmtPlanCfDao;
import com.imfbp.rz.dao.rzpmtplanlease.RzPmtPlanLeaseDao;
import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import com.imfbp.rz.dao.rzprjreview.RzPrjreviewDao;
import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.rzplanchange.RzPlanChange;
import com.imfbp.rz.domain.rzplanchange.query.RzPlanChangeQuery;
import com.imfbp.rz.domain.rzplanchangelease.RzPlanChangeLease;
import com.imfbp.rz.domain.rzplanchangelease.query.RzPlanChangeLeaseQuery;
import com.imfbp.rz.domain.rzplanchangeleasenew.RzPlanChangeLeaseNew;
import com.imfbp.rz.domain.rzplanchangeleasenew.query.RzPlanChangeLeaseNewQuery;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.method.RentMethodCalService;
import com.imfbp.rz.service.rzplanchange.RzPlanChangeService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.service.rzprjcontrpub.RzPrjcontrPubService;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;




@Component("rzPlanChangeService")
public class RzPlanChangeServiceImpl implements RzPlanChangeService{

	@Autowired
	private RzPlanChangeDao rzPlanChangeDao;
	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	@Autowired
	private RzPmtPlanDao rzPmtPlanDao;
	@Autowired
	private RzPmtPlanLeaseDao rzPmtPlanLeaseDao;
	@Autowired
	private RzPlanChangeLeaseDao rzPlanChangeLeaseDao;
	@Autowired
	private RzPlanChangeLeaseNewDao rzPlanChangeLeaseNewDao;
	@Autowired
	private RzPrjcontrDao rzPrjcontrDao;
	@Autowired
	private RzPrjreviewDao rzPrjreviewDao;
	@Autowired
	private RentMethodCalService rentMethodCalService;
	@Autowired
	private BillnoService billnoService;
	@Autowired
    private RzPrjcontrService rzPrjcontrService;
	@Autowired
    private RzPrjcontrPubService rzPrjcontrPubService;
	@Autowired
	private RzPmtPlanCfDao rzPmtPlanCfDao;
	@Autowired
	private RzPmtPlanService rzPmtPlanService;
	/**
	 * 添加
	 * @param rzPlanChange
	 * @return
	 * @throws BusinessException 
	 */
	@Override
	public void insertRzPlanChange(RzPlanChange rzPlanChange) throws BusinessException{
		String pk = primaryKeyUtil.getPrimaryKey();
		try {
			String changNO=billnoService.getBillno(INodeConsts.RZ_PLANCHANGE_NO);
			rzPlanChange.setTs(DateUtil.getTs());
			rzPlanChange.setDr(0);
			rzPlanChange.setPkPlanChange(pk);
			rzPlanChange.setChangeNo(changNO);
			rzPlanChange.setPlanChangeVer("1.0");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String pkPrjcontr = rzPlanChange.getPkPrjcontr();//获取合同主键
		RzPmtPlanQuery rzPmtPlanQuery=new RzPmtPlanQuery();
		rzPmtPlanQuery.setPkPrjcontr(pkPrjcontr);
		RzPmtPlan rzPmtPlan=rzPmtPlanDao.getRzPmtPlanById(rzPmtPlanQuery);
		String pkPmtPlan = rzPmtPlan.getPkPmtPlan();//获取首付计划主键
		RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery=new RzPmtPlanLeaseQuery();
		rzPmtPlanLeaseQuery.setPkPmtPlan(pkPmtPlan);
		Double maxVer=rzPmtPlanLeaseDao.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(rzPmtPlanLeaseQuery).get(0).getVer();
		rzPmtPlanLeaseQuery.setVer(maxVer);
		List<RzPmtPlanLease> rzPmtPlanLeaseList = rzPmtPlanLeaseDao.getRzPmtPlanLeaseAll(rzPmtPlanLeaseQuery);
		List<RzPlanChangeLease> rzPlanChangeLeaseList=new ArrayList<RzPlanChangeLease>();
		if(rzPmtPlanLeaseList!=null&&rzPmtPlanLeaseList.size()>0){
			for (int i = 0; i < rzPmtPlanLeaseList.size(); i++) {
				RzPlanChangeLease rzPlanChangeLease=new RzPlanChangeLease();
				try {
					BeanUtils.copyProperties(rzPlanChangeLease, rzPmtPlanLeaseList.get(i));
					String pkPlanChangeLease = primaryKeyUtil.getPrimaryKey();
					rzPlanChangeLease.setPkPlanChangeLease(pkPlanChangeLease);
					rzPlanChangeLease.setPkPlanChange(pk);
					rzPlanChangeLeaseList.add(rzPlanChangeLease);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			rzPlanChangeDao.insertRzPlanChange(rzPlanChange);//插入租金变更主表
			rzPlanChangeLeaseDao.insertBatchRzPlanChangeLease(rzPlanChangeLeaseList);//原租金数据子表
			List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList= changePayWay( rzPlanChangeLeaseList, rzPlanChange);//通过原租金数据字表计算新租金子表
			if(rzPlanChangeLeaseNewList.size()==0){
				RzPlanChangeQuery rzPlanChangeQuery=new RzPlanChangeQuery();
				RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery=new RzPlanChangeLeaseQuery();
				rzPlanChangeQuery.setPkPlanChange(rzPlanChange.getPkPlanChange());
				rzPlanChangeLeaseQuery.setPkPlanChange(rzPlanChange.getPkPlanChange());
				rzPlanChangeDao.deleteRzPlanChangeById(rzPlanChangeQuery);
				rzPlanChangeLeaseDao.deleteRzPlanChangeLeaseByCondition(rzPlanChangeLeaseQuery);
				throw new BusinessException();
			}
			if(rzPlanChangeLeaseNewList!=null&&rzPlanChangeLeaseNewList.size()>0){
				rzPlanChangeLeaseNewDao.insertBatchRzPlanChangeLeaseNew(rzPlanChangeLeaseNewList);//新数据子表
			}
		}
		
	}
	
	public List<RzPlanChangeLeaseNew> changePayWay(List<RzPlanChangeLease> rzPlanChangeLeaseList,RzPlanChange rzPlanChange){
		Integer changeType = rzPlanChange.getChangeType();
		List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList=new ArrayList<RzPlanChangeLeaseNew>();
		List<RzPlanChangeLease> rzPlanChangeLeaseListForInsert=new ArrayList<RzPlanChangeLease>();
		RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery=new RzPlanChangeLeaseQuery();
		rzPlanChangeLeaseQuery.setIsCheck("Y");
		List<RzPlanChangeLease> rzPlanChangeLeaseRemainningList=rzPlanChangeLeaseDao.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery);//已还清租金List
		if(rzPlanChangeLeaseRemainningList.size()>0){
			RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery1=new RzPlanChangeLeaseQuery();
			rzPlanChangeLeaseQuery1.setSort("seq_no");
			rzPlanChangeLeaseQuery1.setOrder("asc");
			rzPlanChangeLeaseQuery1.setIsCheck("N");
			int total1=rzPlanChangeLeaseDao.getRzPlanChangeLeaseByPageCount(rzPlanChangeLeaseQuery1);
			PaginatedList<RzPlanChangeLease> RzPlanChangeLeasePageListN= new MysqlPaginatedArrayList<RzPlanChangeLease>(rzPlanChangeLeaseQuery1,total1);
			List<RzPlanChangeLease> rzPlanChangeLeaseListB = rzPlanChangeLeaseDao.getRzPlanChangeLeaseByPage(rzPlanChangeLeaseQuery1);
			RzPlanChangeLeasePageListN.addAll(rzPlanChangeLeaseListB);//未还清租金List
			if(RzPlanChangeLeasePageListN!=null&&RzPlanChangeLeasePageListN.size()>0&&total1>0){
				switch (changeType) {
				case 0://提前偿还
					rzPlanChangeLeaseListForInsert=payInadvance(RzPlanChangeLeasePageListN,rzPlanChangeLeaseRemainningList,rzPlanChange);
					break;
				case 1://提前还清
					rzPlanChangeLeaseListForInsert=payoffAdvance(RzPlanChangeLeasePageListN,rzPlanChangeLeaseRemainningList,rzPlanChange);
					break;
				case 2://缩期
					rzPlanChangeLeaseListForInsert=payExtendOrShrink(RzPlanChangeLeasePageListN,rzPlanChangeLeaseRemainningList,rzPlanChange);
					break;
				case 3://展期
					rzPlanChangeLeaseListForInsert=payExtendOrShrink(RzPlanChangeLeasePageListN,rzPlanChangeLeaseRemainningList,rzPlanChange);
					break;
				}
				//转换RzPlanChangeLease List对象为RzPlanChangeLeaseNew List对象
				if(rzPlanChangeLeaseListForInsert.size()>0){
					for (int i = 0; i < rzPlanChangeLeaseListForInsert.size(); i++) {
						rzPlanChangeLeaseListForInsert.get(i);
						try {
							RzPlanChangeLeaseNew rzPlanChangeLeaseNew=new RzPlanChangeLeaseNew();
							BeanUtils.copyProperties(rzPlanChangeLeaseNew, rzPlanChangeLeaseListForInsert.get(i));
							rzPlanChangeLeaseNew.setPkPlanChange(rzPlanChange.getPkPlanChange());
							String pkPlanChange = rzPlanChangeLeaseListForInsert.get(i).getPkPlanChange();
							String pkPlanChangeLeaseNew = primaryKeyUtil.getPrimaryKey();
							rzPlanChangeLeaseNew.setPkPlanChangeLeaseNew(pkPlanChangeLeaseNew);
							rzPlanChangeLeaseNew.setPkPlanChange(pkPlanChange);
							rzPlanChangeLeaseNew.setVer(rzPlanChangeLeaseList.get(0).getVer());
							rzPlanChangeLeaseNewList.add(rzPlanChangeLeaseNew);
						} catch (IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
				return rzPlanChangeLeaseNewList;
			}else{
				return rzPlanChangeLeaseNewList;
			}
		}
		return rzPlanChangeLeaseNewList;

		
	}
	
	
	//提前偿还
	public  List<RzPlanChangeLease> payInadvance(List<RzPlanChangeLease> RzPlanChangeLeasePageListN,List<RzPlanChangeLease> rzPlanChangeLeaseRemainningList, RzPlanChange rzPlanChange){
		RzPlanChangeLease payInadvanceLease = RzPlanChangeLeasePageListN.get(0);
		String beging=payInadvanceLease.getPaydur().split("~")[0];
		Double rptAmt=payInadvanceLease.getRptAmt();//应还款本金
		String end=rzPlanChange.getEffectiveDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    Date beginDate=null; 
	    Date endDate = null;
		try {
			endDate = sdf.parse(end);
			beginDate=sdf.parse(beging); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
		Double rate=rzPlanChange.getPrinReturnRate();
		Double feeRate=rzPlanChange.getPrinReturnFeeRate();
		RzPrjcontrQuery rzPrjcontrQuery=new RzPrjcontrQuery();
		rzPrjcontrQuery.setPkPrjcontr(rzPlanChange.getPkPrjcontr());
		RzPrjcontr rzPrjcontr=rzPrjcontrDao.getRzPrjcontrById(rzPrjcontrQuery);
		int yearDays=rzPrjcontr.getYeardays();
		Double rptInt=rptAmt*rate*((double)day/yearDays)/100;//应还款利息
		java.text.DecimalFormat df =new java.text.DecimalFormat("#.00");
		df.format(rptInt);
		Double rptRent=rptInt+rptAmt;
		Double residualAmt=new Double(0.00);
		String paydur=beging+"~"+end;
		payInadvanceLease.setRptInt(rptInt);//应还款利息
		payInadvanceLease.setRptRent(rptRent);//应还款租金
		payInadvanceLease.setResidualAmt(residualAmt);//剩余本金
		payInadvanceLease.setDurdays((int)day);//期间天数
		payInadvanceLease.setLeaseRate(rate);//利率
		payInadvanceLease.setRptDate(end);//应还款日期
		payInadvanceLease.setPaydur(paydur);//收付期间
		payInadvanceLease.setIsCheck("N");
		if(feeRate!=null){
			payInadvanceLease.setRptInt(rptInt*(1+feeRate));
		}
		rzPlanChangeLeaseRemainningList.add(payInadvanceLease);
		return rzPlanChangeLeaseRemainningList;
	}
	
	//提前还清
	public List<RzPlanChangeLease> payoffAdvance(List<RzPlanChangeLease> RzPlanChangeLeasePageListN,List<RzPlanChangeLease> rzPlanChangeLeaseRemainningList, RzPlanChange rzPlanChange){
		RzPlanChangeLease payInadvanceLease = RzPlanChangeLeasePageListN.get(0);//提前还清单条数据
		RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery2=new RzPlanChangeLeaseQuery();
		rzPlanChangeLeaseQuery2.setSeqNo(payInadvanceLease.getSeqNo()-1);
		if(rzPlanChangeLeaseDao.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery2).size()>0){
			RzPlanChangeLease payInadvanceLease1= rzPlanChangeLeaseDao.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery2).get(0);
			String beging=payInadvanceLease.getPaydur().split("~")[0];
			String end=RzPlanChangeLeasePageListN.get(RzPlanChangeLeasePageListN.size()-1).getRptDate();//应还款日期
			String paydur=beging+"~"+end;//收付期间
			Double residualAmt=new Double(0.00);//剩余本金
			Double rptAmt=payInadvanceLease1.getResidualAmt();//上一期剩余本金是这一期全部应还本金
			Double rptInt=rptAmt*payInadvanceLease1.getLeaseRate()/100;//应还利息
			Double rptRent=rptInt+rptAmt;//应还款租金（本息全部还清）
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date beginDate=null; 
		    Date endDate = null;
			try {
				endDate = sdf.parse(end);
				beginDate=sdf.parse(beging); 
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
		    payInadvanceLease.setDurdays((int)day);//期间天数
		    payInadvanceLease.setRptRent(rptRent);//应还款租金（本息相加）
		    payInadvanceLease.setPaydur(paydur);//收付期间
		    payInadvanceLease.setResidualAmt(residualAmt);//剩余本金
		    payInadvanceLease.setRptDate(end);//应还款日期
		    payInadvanceLease.setRptInt(rptInt);//应还利息
		    payInadvanceLease.setIsCheck("N");
		    Double payFee=0.00;
		    for (int i = 0; i < RzPlanChangeLeasePageListN.size(); i++) {
		    	payFee+=RzPlanChangeLeasePageListN.get(i).getPayFee();
			}
		    payInadvanceLease.setPayFee(payFee);
		    rzPlanChangeLeaseRemainningList.add(payInadvanceLease);
		}
		return rzPlanChangeLeaseRemainningList;
	}
	
	//缩期或展期
	public List<RzPlanChangeLease> payExtendOrShrink(List<RzPlanChangeLease> RzPlanChangeLeasePageListN,List<RzPlanChangeLease> rzPlanChangeLeaseRemainningList, RzPlanChange rzPlanChange){
		RzPlanChangeLease payInadvanceLease = RzPlanChangeLeasePageListN.get(0);//待还的第一条数据
		RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery2=new RzPlanChangeLeaseQuery();
		rzPlanChangeLeaseQuery2.setSeqNo(payInadvanceLease.getSeqNo() -1);
		RzPlanChangeLease payInadvanceLease1= rzPlanChangeLeaseDao.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery2).get(0);//已还清的最后一条数据
		Double rptAmt=payInadvanceLease1.getResidualAmt();//上一期剩余本金是这一期全部应还本金
		
		String pkPrjcontr=rzPlanChange.getPkPrjcontr();
		RzPrjcontrQuery rzPrjcontrQuery=new RzPrjcontrQuery();
		rzPrjcontrQuery.setPkPrjcontr(pkPrjcontr);
		RzPrjcontr rzPrjcontr=rzPrjcontrDao.getRzPrjcontrAll(rzPrjcontrQuery).get(0);
		Double rate= rzPrjcontr.getBaserate();//利率
		RzPrjreviewQuery rzPrjreviewQuery=new RzPrjreviewQuery();
		rzPrjreviewQuery.setPkPrjreview(rzPrjcontr.getPkPrjreview());
		RzPrjreview rzprjreview =rzPrjreviewDao.getRzPrjreviewAll(rzPrjreviewQuery).get(0);//通过合同关联立项申请查询租金支付方式
		RzPricecal rzPricecal = new RzPricecal();
		rzPricecal.setFinanceamt(rptAmt);
		rzPricecal.setPricerate(rate);
		if(rzPlanChange.getContractionResiNum()!=null&&rzPlanChange.getExtResiNum()!=0){
			rzPricecal.setLeaseprd(rzPlanChange.getContractionResiNum());//设置租赁期限？？？
		}else{
			rzPricecal.setLeaseprd(rzPlanChange.getExtResiNum());//设置租赁期限？？？
		}
		rzPricecal.setReptway(rzPrjcontr.getReptway());
		rzPricecal.setReptcycle(rzPrjcontr.getReptcycle());
		rzPricecal.setPaymentway(rzPrjcontr.getPaymentway());
		rzPricecal.setFeepayway(rzprjreview.getFeepayway());//租金支付方式
		rzPricecal.setLaunchdate(payInadvanceLease1.getRptDate());//已还清的最后一条数据的结束日期是待还周期的开始日期
		List<RzPricecalLease> rzPricecalLeaseList=new ArrayList<RzPricecalLease>();
		try {
			rzPricecalLeaseList=rentMethodCalService.calLeaseChange(rzPricecal);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rzPricecalLeaseList!=null&&rzPricecalLeaseList.size()>0){
			Integer seqNo=payInadvanceLease.getSeqNo();
			for (int i = 0; i < rzPricecalLeaseList.size(); i++) {
				rzPricecalLeaseList.get(i);
				try {
					RzPlanChangeLease rzPlanChangeLease=new RzPlanChangeLease();
					BeanUtils.copyProperties(rzPlanChangeLease, rzPricecalLeaseList.get(i));
					rzPlanChangeLease.setPkPlanChange(rzPlanChange.getPkPlanChange());
					String pkPlanChangeLease = primaryKeyUtil.getPrimaryKey();
					rzPlanChangeLease.setPkPlanChangeLease(pkPlanChangeLease);
					rzPlanChangeLease.setSeqNo(seqNo++);
					rzPlanChangeLease.setIsCheck("N");
					rzPlanChangeLeaseRemainningList.add(rzPlanChangeLease);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return rzPlanChangeLeaseRemainningList;
		
	}
	
	/**
	 * 批量添加
	 * @param List<rzPlanChange>
	 * @return
	 */
	public void insertBatchRzPlanChange(List<RzPlanChange> rzPlanChangeList){
		if(rzPlanChangeList != null){
			for(int i=0;i<rzPlanChangeList.size();i++){
				rzPlanChangeList.get(i).setTs(DateUtil.getTs());
				rzPlanChangeList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPlanChangeList.get(i).setPkPlanChange(pk);
			}
			rzPlanChangeDao.insertBatchRzPlanChange(rzPlanChangeList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery){
		return rzPlanChangeDao.deleteRzPlanChangeById(rzPlanChangeQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery){
		return rzPlanChangeDao.deleteRzPlanChangeByCondition(rzPlanChangeQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPlanChangeQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPlanChangeByBatchId(RzPlanChangeQuery rzPlanChangeQuery) {
		Result result = new Result(false);
		RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery=new RzPlanChangeLeaseQuery();
		RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery=new RzPlanChangeLeaseNewQuery();
		rzPlanChangeQuery.setPkPlanChange(rzPlanChangeQuery.getBatchId().split(",")[0]);
		rzPlanChangeLeaseQuery.setPkPlanChange(rzPlanChangeQuery.getBatchId().split(",")[0]);
		rzPlanChangeLeaseNewQuery.setPkPlanChange(rzPlanChangeQuery.getBatchId().split(",")[0]);
		List<RzPlanChangeLease> rzPlanChangeLeaseList=rzPlanChangeLeaseDao.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery);
		List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList=rzPlanChangeLeaseNewDao.getRzPlanChangeLeaseNewAll(rzPlanChangeLeaseNewQuery);
		boolean flat=false;boolean flatlease=true; boolean flatleaseNew=true;
		flat = rzPlanChangeDao.deleteRzPlanChangeById(rzPlanChangeQuery);
		if(rzPlanChangeLeaseList!=null&&rzPlanChangeLeaseList.size()>0){
			flatlease=rzPlanChangeLeaseDao.deleteRzPlanChangeLeaseByCondition(rzPlanChangeLeaseQuery);
		}
		if(rzPlanChangeLeaseNewList!=null&&rzPlanChangeLeaseNewList.size()>0){
			flatleaseNew=rzPlanChangeLeaseNewDao.deleteRzPlanChangeLeaseNewByCondition(rzPlanChangeLeaseNewQuery);
		}
		result.setSuccess(flat&&flatlease&&flatleaseNew);
		return result;
	}
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery){
		return rzPlanChangeDao.logicDeleteRzPlanChangeById(rzPlanChangeQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPlanChangeByCondition(RzPlanChangeQuery rzPlanChangeQuery){
		return rzPlanChangeDao.logicDeleteRzPlanChangeByCondition(rzPlanChangeQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzPlanChangeQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzPlanChangeByBatchId(RzPlanChangeQuery rzPlanChangeQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPlanChangeQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzPlanChangeDao.logicDeleteRzPlanChangeByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPlanChange
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPlanChange rzPlanChange) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPlanChange!=null){
				if(StringUtil.isNotEmpty(rzPlanChange.getPkPlanChange())){
					updateRzPlanChangeById(rzPlanChange);
					result.setSuccess(true);
				}else{
					RzPlanChangeQuery rzPlanChangeQuery1=new RzPlanChangeQuery();
					rzPlanChangeQuery1.setPkPrjcontr(rzPlanChange.getPkPrjcontr());
					int num= rzPlanChangeDao.getRzPlanChangeByPageCount(rzPlanChangeQuery1);
					if(num>0){
						result.setErrorMessage("已做过租金计划变更的合同只能更改租金计划，不能继续添加！！");
					}else{
						insertRzPlanChange(rzPlanChange);
						//如果没有异常设置成功
						result.setSuccess(true);
					}
				}
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPlanChange);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("起租日没有核销的租金计划无法变更！");
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPlanChange
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeById(RzPlanChange rzPlanChange){
		RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery=new RzPlanChangeLeaseNewQuery();
		rzPlanChangeLeaseNewQuery.setPkPlanChange(rzPlanChange.getPkPlanChange());
		rzPlanChangeLeaseNewDao.deleteRzPlanChangeLeaseNewByCondition(rzPlanChangeLeaseNewQuery);
		RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery=new RzPlanChangeLeaseQuery();
		rzPlanChangeLeaseQuery.setPkPlanChange(rzPlanChange.getPkPlanChange());
		List<RzPlanChangeLease> rzPlanChangeLeaseList=rzPlanChangeLeaseDao.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery);
		List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList=changePayWay(rzPlanChangeLeaseList, rzPlanChange);
		rzPlanChangeLeaseNewDao.insertBatchRzPlanChangeLeaseNew(rzPlanChangeLeaseNewList);
		Double newVersion=Double.parseDouble(rzPlanChange.getPlanChangeVer())+0.1;
		rzPlanChange.setPlanChangeVer(newVersion+"");
		return rzPlanChangeDao.updateRzPlanChangeById(rzPlanChange);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPlanChangeByCondition(RzPlanChangeQuery record,RzPlanChangeQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPlanChangeDao.updateRzPlanChangeByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPlanChangeQuery
	 * @return
	 */
	public Result updateRzPlanChangeByBatchId(List<RzPlanChange> rzPlanChangeList){
		Result result = new Result(false);
		try {
			boolean flag = rzPlanChangeDao.updateRzPlanChangeByBatchId(rzPlanChangeList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public RzPlanChange getRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery){
		return rzPlanChangeDao.getRzPlanChangeById(rzPlanChangeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public List<RzPlanChange> getRzPlanChangeAll(RzPlanChangeQuery rzPlanChangeQuery){
		return rzPlanChangeDao.getRzPlanChangeAll(rzPlanChangeQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public GridResult<RzPlanChange> getRzPlanChangeByPage(RzPlanChangeQuery rzPlanChangeQuery){
		//如果排序的字段是空或者空字符串
		if(rzPlanChangeQuery!=null&&StringUtils.isBlank(rzPlanChangeQuery.getSort())){
			rzPlanChangeQuery.setSort("pk_plan_change");
			rzPlanChangeQuery.setOrder("desc");;
		}
		int total = rzPlanChangeDao.getRzPlanChangeByPageCount(rzPlanChangeQuery);
		PaginatedList<RzPlanChange> rzPlanChangePageList = new MysqlPaginatedArrayList<RzPlanChange>(rzPlanChangeQuery,total);
		List<RzPlanChange> rzPlanChangeList = rzPlanChangeDao.getRzPlanChangeByPage(rzPlanChangeQuery);
		rzPlanChangePageList.addAll(rzPlanChangeList);
		GridResult<RzPlanChange> result = new GridResult<RzPlanChange>(rzPlanChangePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPlanChangeQuery
	 * @return
	 */
	@Override
	public int getRzPlanChangeByPageCount(RzPlanChangeQuery rzPlanChangeQuery){
		return rzPlanChangeDao.getRzPlanChangeByPageCount(rzPlanChangeQuery);
	}
	
	@Override
	public List<RzPmtPlanLease> getPmtPlanLease(RzPlanChange rzPlanChange){
       RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery=new RzPlanChangeLeaseNewQuery();
       rzPlanChangeLeaseNewQuery.setPkPlanChange(rzPlanChange.getPkPlanChange());
       List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewQueryList=rzPlanChangeLeaseNewDao.getRzPlanChangeLeaseNewAll(rzPlanChangeLeaseNewQuery);
       List<RzPmtPlanLease> RzPmtPlanLeaseList=new ArrayList<RzPmtPlanLease>();
	   String pkPrjcontr = rzPlanChange.getPkPrjcontr();//获取合同主键
	   RzPmtPlanQuery rzPmtPlanQuery=new RzPmtPlanQuery();
	   rzPmtPlanQuery.setPkPrjcontr(pkPrjcontr);
	   RzPmtPlan rzPmtPlan=rzPmtPlanDao.getRzPmtPlanById(rzPmtPlanQuery);
	   //获取最大版本号
	   RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery=new RzPmtPlanLeaseQuery();
 		rzPmtPlanLeaseQuery.setPkPmtPlan(rzPmtPlan.getPkPmtPlan());
 		List<RzPmtPlanLease> rzPmtPlanLeaseA=rzPmtPlanLeaseDao.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(rzPmtPlanLeaseQuery);
 		Double maxVer=rzPmtPlanLeaseA.get(0).getVer();
       for(int i=0;i<rzPlanChangeLeaseNewQueryList.size();i++){
    	   RzPmtPlanLease rzPmtPlanLease=new RzPmtPlanLease();
    	   try {
			BeanUtils.copyProperties(rzPmtPlanLease, rzPlanChangeLeaseNewQueryList.get(i));
			String pk = primaryKeyUtil.getPrimaryKey();
			rzPmtPlanLease.setPkPmtPlanLease(pk);
			rzPmtPlanLease.setPkPmtPlan(rzPmtPlan.getPkPmtPlan());
			rzPmtPlanLease.setVer(maxVer+0.1);
	    	RzPmtPlanLeaseList.add(rzPmtPlanLease);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
    	   
       }
		return RzPmtPlanLeaseList;
	}
	
	@Override
	public List<RzPmtPlanCf>  getPmtPlanCf(List<RzPmtPlanLease> RzPmtPlanLeaseList,RzPlanChange rzPlanChange){
		String pkPrjcontr = rzPlanChange.getPkPrjcontr();//获取合同主键
		RzPmtPlanQuery rzPmtPlanQuery=new RzPmtPlanQuery();
		rzPmtPlanQuery.setPkPrjcontr(pkPrjcontr);
		RzPmtPlan rzPmtPlan=rzPmtPlanDao.getRzPmtPlanById(rzPmtPlanQuery);
		String pkPmtPlan = rzPmtPlan.getPkPmtPlan();//获取首付计划主键
		RzPmtPlanCfQuery rzPmtPlanCfQuery0=new RzPmtPlanCfQuery();
		rzPmtPlanCfQuery0.setPkPmtPlan(pkPmtPlan);
		rzPmtPlanCfQuery0.setPrd("0");
		//获取最大版本号
		RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery=new RzPmtPlanLeaseQuery();
		rzPmtPlanLeaseQuery.setPkPmtPlan(pkPmtPlan);
		List<RzPmtPlanLease> rzPmtPlanLeaseA=rzPmtPlanLeaseDao.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(rzPmtPlanLeaseQuery);
		Double maxVer=rzPmtPlanLeaseA.get(0).getVer();
		rzPmtPlanCfQuery0.setVer(maxVer);
		List<RzPmtPlanCf> rzPmtPlanCfListRemain = rzPmtPlanCfDao.getRzPmtPlanCfAll(rzPmtPlanCfQuery0);//获取收付计划期次为0的List
		RzPmtPlanCfQuery rzPmtPlanCfQueryLast=new RzPmtPlanCfQuery();
		rzPmtPlanCfQueryLast.setPkPmtPlan(pkPmtPlan);
		rzPmtPlanCfQueryLast.setPlanpmtcategory(6);
		rzPmtPlanCfQuery0.setVer(maxVer);
		List<RzPmtPlanCf> rzPmtPlanCfListRemainLast=rzPmtPlanCfDao.getRzPmtPlanCfAll(rzPmtPlanCfQueryLast);//获取合同到期的数据
		rzPmtPlanCfListRemain.add(rzPmtPlanCfListRemainLast.get(0));
		for(int i=0;i<RzPmtPlanLeaseList.size();i++){
			RzPmtPlanCf rzPmtPlanCf=new RzPmtPlanCf();
				try {
					BeanUtils.copyProperties(rzPmtPlanCf,RzPmtPlanLeaseList.get(i));
					rzPmtPlanCf.setPmtdur(RzPmtPlanLeaseList.get(i).getPaydur());
					rzPmtPlanCf.setVer(maxVer+0.1);
					rzPmtPlanCf.setPkPmtPlan(pkPmtPlan);
					rzPmtPlanCf.setPlanpmtcategory(5);
					rzPmtPlanCf.setPrd(RzPmtPlanLeaseList.get(i).getNum());
					rzPmtPlanCfListRemain.add(rzPmtPlanCf);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		}
		for(int i=0;i<rzPmtPlanCfListRemain.size();i++){
			String pk = primaryKeyUtil.getPrimaryKey();
			rzPmtPlanCfListRemain.get(i).setPkPmtPlanCf(pk);
		}
		return rzPmtPlanCfListRemain;
		
	}
	
}