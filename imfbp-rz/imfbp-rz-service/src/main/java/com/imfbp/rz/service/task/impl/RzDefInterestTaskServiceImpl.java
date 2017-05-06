package com.imfbp.rz.service.task.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ifbp.boss.rpc.smalluser.domain.SmallUser;
import com.ifbp.boss.rpc.smalluser.domain.query.SmallUserQuery;
import com.ifbp.boss.rpc.smalluser.service.BossUserRpcService;
import com.imfbp.rz.dao.rzdefinterest.RzDefInterestDao;
import com.imfbp.rz.dao.rzdefinterestplan.RzDefInterestPlanDao;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;
import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterest.query.RzDefInterestQuery;
import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzdefinterest.RzDefInterestService;
import com.imfbp.rz.service.rzdefinterestplan.RzDefInterestPlanService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.service.task.BackgroundTaskService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;

public class RzDefInterestTaskServiceImpl implements BackgroundTaskService {
    @Autowired
	private RzDefInterestService rzDefInterestService;
    @Autowired
    private RzPmtPlanService rzPmtPlanService;
    @Autowired
    private RzPrjcontrService rzPrjcontrService;
    @Autowired
    private RzContrTallyService rzContrTallyService;
    @Autowired
	private PrimaryKeyUtil primaryKeyUtil;
    @Autowired
    private RzDefInterestPlanService rzDefInterestPlanService;
    private RzDefInterestPlanDao rzDefInterestPlanDao;
    private RzDefInterestDao rzDefInterestDao;
	@Autowired
	private BillnoService billnoService;
	@Autowired
	private BossUserRpcService bossUserRpcService;
	
	private static final String SEND_USER = "SENDUSER";
	private static final String ERROR = "ERRORMSG";
	@SuppressWarnings("unchecked")
	@Override
	public Object executeTask(Object obj) throws Exception {
		//批量删除逾期罚息主表
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		//批量删除逾期罚息子表
		List<RzDefInterestPlan> deleteObjs2=rzDefInterestPlanService.getRzDefInterestPlanAll(new RzDefInterestPlanQuery());
		RzDefInterestPlanQuery query2=rzDefInterestPlanService.getAllDeleteIds(deleteObjs2);
		if(query2 != null && StringUtils.isNotEmpty(query2.getBatchId()))
		rzDefInterestPlanService.deleteRzDefInterestPlanByBatchId(query2);
		List<RzDefInterest> deleteObjs=rzDefInterestService.getRzDefInterestAll(new RzDefInterestQuery());
		RzDefInterestQuery query1=rzDefInterestService.getAllDeleteIds(deleteObjs);
		if(query1 != null && StringUtils.isNotEmpty(query1.getBatchId()))
		rzDefInterestService.deleteRzDefInterestByBatchId(query1);
		List<RzPmtPlan>rzPmtPlanList=rzPmtPlanService.getRzPmtPlanAll(new RzPmtPlanQuery());
		List<RzDefInterest>resultListFather=new ArrayList<RzDefInterest>();
		List<RzDefInterestPlan>resultListSon=new ArrayList<RzDefInterestPlan>();
		if(ToolUtils.isNotEmptyCollection(rzPmtPlanList)){
			for(RzPmtPlan plan:rzPmtPlanList){
				if(plan != null && StringUtils.isNotEmpty(plan.getPkPmtPlan())){
					String pkPrjcontr=plan.getPkPrjcontr();
					RzDefInterestQuery query=new RzDefInterestQuery();
					query.setPkPrjcontr(pkPrjcontr);
					Map<String,Object>map=rzDefInterestService.getLeaseByContr(query);
					List<RzPmtPlanLease>rzPmtPlanLeaseList=null;
					if(map != null)rzPmtPlanLeaseList= (List<RzPmtPlanLease>) map.get("rzPmtPlanLeaseLists");
					if(ToolUtils.isNotEmptyCollection(rzPmtPlanLeaseList)){
						RzPrjcontrQuery rzPrjcontrQuery=new RzPrjcontrQuery();
						rzPrjcontrQuery.setPkPrjcontr(pkPrjcontr);
						RzPrjcontr rzPrjcontr=rzPrjcontrService.getRzPrjcontrById(rzPrjcontrQuery);
						if(rzPrjcontr != null){
							RzDefInterest interest=new RzDefInterest();
							String pk=primaryKeyUtil.getPrimaryKey();
							interest.setApprovestatus(0);
							interest.setPkPrjcontr(pkPrjcontr);
							interest.setPkDefInterest(pk);
							interest.setDefIntDate(format.format(new Date()));
							interest.setDefIntRmptDate(format.format(new Date()));
							String billno;
							try {
								billno = billnoService.getBillno(INodeConsts.RZ_PRICE_NO);
								interest.setDefIntNo(billno);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//设置项目经理
							interest.setPkUserManager(rzPrjcontr.getPkUserManager());
							interest.setPrjCode(rzPrjcontr.getPrjCode());
							interest.setPrjName(rzPrjcontr.getPrjName());
							interest.setContrCode(rzPrjcontr.getContrCode());
							interest.setContrName(rzPrjcontr.getContrName());
							interest.setPkCustomer(rzPrjcontr.getPkCustomer());
							interest.setItemamt(rzPrjcontr.getItemamt());
							interest.setBilldate(format.format(date));
							interest.setStartLeaseDate(rzPrjcontr.getStartLeaseDate());
							interest.setEndLeaseDate(rzPrjcontr.getEndLeaseDate());
							//interest.setBillmaker(loginInfoManage.getUserId(arg0));
							//interest.setPkDept(loginInfoManage.getDeptId(null));
							interest.setOperatordatetime(DateUtil.getTs());
							interest.setTs(DateUtil.getTs());
							RzContrTallyQuery  rzContrTallyQuery=new RzContrTallyQuery();
							//根据合同编码和最大序列来获取台账信息
							rzContrTallyQuery.setContrCode(rzPrjcontr.getContrCode());
							List<RzContrTally>rzContrTallyList=rzContrTallyService.getRzContrTallyAll(rzContrTallyQuery);
							if(ToolUtils.isNotEmptyCollection(rzContrTallyList)){
								//获取最大序列号
								int max=-1;
								RzContrTally rzContrTally =	rzContrTallyList.get(0);
								for(RzContrTally tally:rzContrTallyList){
									if(tally != null &&tally.getOperSeq()>max){
										max=tally.getOperSeq();
										rzContrTally=tally;
									}
								}
								interest.setItembal(rzContrTally.getItembal());
								interest.setContrBal(rzContrTally.getContrBal());
								interest.setContrPrinBal(rzContrTally.getCurPrinBal());
								interest.setCurPricerate(rzContrTally.getCurPricerate());
								interest.setPricerate(rzContrTally.getPricerate());
							}
							interest.setRpmtGrace((Integer)map.get("rpmtTerm"));
							//逾期金额总数，逾期天数总数，逾期罚息总数
							double overdueAmts=0;
							int overdueDays=0;
							double receivableDefIntAmt=0;
							for(RzPmtPlanLease le:rzPmtPlanLeaseList){
								RzDefInterestPlan rzDefInterestPlan=new RzDefInterestPlan();
								String pkPlan=primaryKeyUtil.getPrimaryKey();
								rzDefInterestPlan.setPkDefInterestPlan(pkPlan);
								//设置序列号
								rzDefInterestPlan.setSeqno(le.getSeqNo());
								rzDefInterestPlan.setPkDefInterest(pk);
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
								/**结束逾期类型的判断**/
								resultListSon.add(rzDefInterestPlan);
							}
							interest.setOverdueAmt(overdueAmts);
							interest.setOverdueDays(overdueDays);
							interest.setReceivableDefIntAmt(receivableDefIntAmt);
							resultListFather.add(interest);
						}
					}
					
					
				}
			}
		}
		if(ToolUtils.isNotEmptyCollection(resultListFather)){
			Map<String,List<RzDefInterestPlan>>record=new HashMap<String,List<RzDefInterestPlan>>();
			rzDefInterestDao.insertBatchRzDefInterest(resultListFather);
			if(ToolUtils.isNotEmptyCollection(resultListSon)){
				rzDefInterestPlanDao.insertBatchRzDefInterestPlan(resultListSon);
				for(RzDefInterest in:resultListFather){
					String tempPk=in.getPkDefInterest();
					List<RzDefInterestPlan>temp=new ArrayList<RzDefInterestPlan>();
					for(RzDefInterestPlan plan:resultListSon){
						if(plan.getPkDefInterest().equals(tempPk)){
							temp.add(plan);
						}
					}
					record.put(tempPk, temp);
				}
			}
			/** 开始发送消息**/
			//当前项目经理id集
			StringBuffer managerBuffer=new StringBuffer();
			for(int i=0;i<resultListFather.size();i++){
				String usmanager="'"+resultListFather.get(i).getPkUserManager()+"'";
				if(StringUtils.isNotEmpty(usmanager) && managerBuffer.indexOf(usmanager) <0 && i==0)  managerBuffer.append(usmanager);
				else if(StringUtils.isNotEmpty(usmanager)  && managerBuffer.indexOf(usmanager) <0 && i !=0 ) managerBuffer.append(","+usmanager);
			}
			//存放项目经理的主键和真实名称
			Map<String,SmallUser>manages=translateUser(managerBuffer.toString());
			List<Map<String,Object>> errMessList = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < resultListFather.size(); i++) {
				RzDefInterest vo = resultListFather.get(i);
				String pkUserManager=vo.getPkUserManager();
				List<RzDefInterestPlan>sonList=record.get(vo.getPkDefInterest());
				if(ToolUtils.isNotEmptyCollection(sonList)){
					for(RzDefInterestPlan plan:sonList){
				Map<String,Object> map = new HashMap<String,Object>();
				String errMess = "逾期提醒通知：尊敬的" + manages.get(pkUserManager).getUserRealName() + "，您好！合同："
						+ vo.getContrName() + "编号为：" + vo.getContrCode() + " ：" +plan.getNum()+ "，于"
						+ plan.getRptDate() + "到期,现已逾期" + plan.getOverdueDays() + "天，请及时还款。";
				map.put(SEND_USER,manages.get(vo.getPkUserManager()));
				map.put(ERROR,errMess);
				errMessList.add(map);
					}
				}
			}
			return errMessList;
		}
		return null;
	}
    
	public Map<String,SmallUser> translateUser(String pkUsers){
		if(StringUtils.isEmpty(pkUsers)) return null;
		Map<String,SmallUser> result=new HashMap<String,SmallUser>();
		SmallUserQuery smallUserQuery =new SmallUserQuery();
		smallUserQuery.setIds(pkUsers.toString());
		List<SmallUser>smallUserQueryList=bossUserRpcService.getBossUserByIds(smallUserQuery);
		if(ToolUtils.isNotEmptyCollection(smallUserQueryList)){
			for(SmallUser user:smallUserQueryList){
				result.put(user.getId(), user);
			}
		}
		return result;
	}
	public RzDefInterestPlanDao getRzDefInterestPlanDao() {
		return rzDefInterestPlanDao;
	}

	public void setRzDefInterestPlanDao(RzDefInterestPlanDao rzDefInterestPlanDao) {
		this.rzDefInterestPlanDao = rzDefInterestPlanDao;
	}

	public RzDefInterestDao getRzDefInterestDao() {
		return rzDefInterestDao;
	}

	public void setRzDefInterestDao(RzDefInterestDao rzDefInterestDao) {
		this.rzDefInterestDao = rzDefInterestDao;
	}

}
