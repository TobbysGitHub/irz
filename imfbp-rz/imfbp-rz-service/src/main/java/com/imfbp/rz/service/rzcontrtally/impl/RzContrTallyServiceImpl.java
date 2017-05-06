package com.imfbp.rz.service.rzcontrtally.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.dic.domain.RpcDicItem;
import com.ifbp.boss.rpc.dic.service.DicItemRpcService;
import com.ifbp.boss.rpc.smalluser.domain.SmallUser;
import com.ifbp.boss.rpc.smalluser.domain.query.SmallUserQuery;
import com.ifbp.boss.rpc.smalluser.service.BossUserRpcService;
import com.imfbp.brefdata.rpc.reference.domain.RpcDataQuery;
import com.imfbp.brefdata.rpc.reference.service.ReferenceRpcService;
import com.imfbp.rz.dao.rzcontrtally.RzContrTallyDao;
import com.imfbp.rz.dao.rzpricecalcf.RzPricecalCfDao;
import com.imfbp.rz.dao.rzpricecallease.RzPricecalLeaseDao;
import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import com.imfbp.rz.dao.rzprjstate.RzPrjStateDao;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzcontrtally.RzContrTallyProQueryVo;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.domain.rzprjstate.query.RzPrjStateQuery;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;



/*****
 * 
 * 合同台账
 * @author Administrator
 *
 */

@Component("rzContrTallyService")
public class RzContrTallyServiceImpl implements RzContrTallyService{


	private RzContrTallyDao rzContrTallyDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	
	private RzPrjapplyDao rzPrjapplyDao;
	
	private RzPrjcontrDao rzPrjcontrDao;
	
	private RzPrjStateDao rzPrjStateDao;
	
	private RzPricecalLeaseDao rzPricecalLeaseDao;
	
	private RzPricecalCfDao rzPricecalCfDao;
	@Autowired
	private ReferenceRpcService referenceRpcService;
	@Autowired
	private BossUserRpcService bossUserRpcService;
	@Autowired
	private DicItemRpcService dicItemRpcClient;

	/**
	 * 添加
	 * @param rzContrTally
	 * @return
	 */
	@Override
	public void insertRzContrTally(RzContrTally rzContrTally){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzContrTally.setTs(DateUtil.getTs());
		rzContrTally.setPkContrTally(pk);
		rzContrTallyDao.insertRzContrTally(rzContrTally);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzContrTally>
	 * @return
	 */
	public void insertBatchRzContrTally(List<RzContrTally> rzContrTallyList){
		if(rzContrTallyList != null){
			for(int i=0;i<rzContrTallyList.size();i++){
				rzContrTallyList.get(i).setTs(DateUtil.getTs());
				String pk = primaryKeyUtil.getPrimaryKey();
				rzContrTallyList.get(i).setPkContrTally(pk);
			}
			rzContrTallyDao.insertBatchRzContrTally(rzContrTallyList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzContrTallyById(RzContrTallyQuery rzContrTallyQuery){
		return rzContrTallyDao.deleteRzContrTallyById(rzContrTallyQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public boolean deleteRzContrTallyByCondition(RzContrTallyQuery rzContrTallyQuery){
		return rzContrTallyDao.deleteRzContrTallyByCondition(rzContrTallyQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzContrTallyQuery
	 * @return
	 */	
	@Override
	public Result deleteRzContrTallyByBatchId(RzContrTallyQuery rzContrTallyQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzContrTallyQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzContrTallyDao.deleteRzContrTallyByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzContrTally
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzContrTally rzContrTally) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzContrTally!=null){
				if(StringUtil.isNotEmpty(rzContrTally.getPkContrTally())){
					updateRzContrTallyById(rzContrTally);
				}else{
					insertRzContrTally(rzContrTally);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzContrTally);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzContrTally
	 * @return
	 */
	@Override
	public boolean updateRzContrTallyById(RzContrTally rzContrTally){
		return rzContrTallyDao.updateRzContrTallyById(rzContrTally);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzContrTallyByCondition(RzContrTallyQuery record,RzContrTallyQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzContrTallyDao.updateRzContrTallyByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzContrTallyQuery
	 * @return
	 */
	public Result updateRzContrTallyByBatchId(List<RzContrTally> rzContrTallyList){
		Result result = new Result(false);
		try {
			boolean flag = rzContrTallyDao.updateRzContrTallyByBatchId(rzContrTallyList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public RzContrTally getRzContrTallyById(RzContrTallyQuery rzContrTallyQuery){
		return rzContrTallyDao.getRzContrTallyById(rzContrTallyQuery);
	}

    /**
     * 获取最新版本合同的合同状态
     */
    @Override
	public List<RzContrTally> getRzContrTallyBalance(RzContrTallyQuery rzContrTallyQuery){
	     return rzContrTallyDao.getRzContrTallyBalance(rzContrTallyQuery);
	}
	/**
	 * 查询所有
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public List<RzContrTally> getRzContrTallyAll(RzContrTallyQuery rzContrTallyQuery){
		return rzContrTallyDao.getRzContrTallyAll(rzContrTallyQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public GridResult<RzContrTally> getRzContrTallyByPage(RzContrTallyQuery rzContrTallyQuery){
		//如果排序的字段是空或者空字符串
		if(rzContrTallyQuery!=null&&StringUtils.isBlank(rzContrTallyQuery.getSort())){
			rzContrTallyQuery.setSort("pk_contr_tally");
			rzContrTallyQuery.setOrder("desc");;
		}
		int total = rzContrTallyDao.getRzContrTallyByPageCount(rzContrTallyQuery);
		PaginatedList<RzContrTally> rzContrTallyPageList = new MysqlPaginatedArrayList<RzContrTally>(rzContrTallyQuery,total);
		List<RzContrTally> rzContrTallyList = rzContrTallyDao.getRzContrTallyByPage(rzContrTallyQuery);
		rzContrTallyPageList.addAll(rzContrTallyList);
		GridResult<RzContrTally> result = new GridResult<RzContrTally>(rzContrTallyPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzContrTallyQuery
	 * @return
	 */
	@Override
	public int getRzContrTallyByPageCount(RzContrTallyQuery rzContrTallyQuery){
		return rzContrTallyDao.getRzContrTallyByPageCount(rzContrTallyQuery);
	}

	public void setRzContrTallyDao(RzContrTallyDao  rzContrTallyDao){
		this.rzContrTallyDao = rzContrTallyDao;
	}

	@Override
	public RzContrTally getRzContrTallyLatest(
			RzContrTallyQuery rzContrTallyQuery) {
		//如果排序的字段是空或者空字符串
		if(rzContrTallyQuery!=null&&StringUtils.isBlank(rzContrTallyQuery.getSort())){
			rzContrTallyQuery.setSort("oper_seq");
			rzContrTallyQuery.setOrder("desc");;
		}
		int total = rzContrTallyDao.getRzContrTallyByPageCount(rzContrTallyQuery);
		PaginatedList<RzContrTally> rzContrTallyPageList = new MysqlPaginatedArrayList<RzContrTally>(rzContrTallyQuery,total);
		List<RzContrTally> rzContrTallyList = rzContrTallyDao.getRzContrTallyByPage(rzContrTallyQuery);
		rzContrTallyPageList.addAll(rzContrTallyList);
		RzContrTally rzContrTally= rzContrTallyPageList.get(0);
		return rzContrTally;
	}
	
	/**
	 * 台账流水数据添加
	 * 
	 * @param rzContrTally
	 * @return
	 */
	public Result addRzContrTally(RzContrTally rzContrTally) {
		// 设置调用失败
		Result result = new Result(false);
		try {
			if (rzContrTally != null) {
				// 获取合同pk
				String pkPrjcontr = rzContrTally.getPkPrjcontr();

				RzContrTallyQuery rzContrTallyQuery = new RzContrTallyQuery();
				rzContrTallyQuery.setPkPrjcontr(pkPrjcontr);
				List<RzContrTally> rzContrTallyList = rzContrTallyDao.getRzContrTallyMaxOperSeq(rzContrTallyQuery);
				
				RzContrTally rzContrTallyTmp = null;
				RzContrTally rzContrTallyAdd = new RzContrTally();
				int operSeq = 1;
				if (ToolUtils.isNotEmptyCollection(rzContrTallyList)) {
					rzContrTallyTmp = rzContrTallyList.get(0);
					operSeq = rzContrTallyTmp.getOperSeq() + 1;
					BeanUtils.copyProperties(rzContrTallyTmp,rzContrTallyAdd);
				}
				//去掉为空的属性值
				BeanUtils.copyProperties(rzContrTally, rzContrTallyAdd,ToolUtils.getNullPropertyNames(rzContrTally));
				
				
				if(rzContrTallyTmp != null){
					Double receivablDefIntTotal = ToolUtils.getDouNullAsZero(rzContrTally.getReceivablDefInt())
							+ ToolUtils.getDouNullAsZero(rzContrTallyTmp.getRealDefInt());

					rzContrTallyAdd.setReceivablDefIntTotal(receivablDefIntTotal);
					//应收罚息
					/*Double receivablDefInt = rzContrTallyTmp.getReceivablDefInt();
					if(receivablDefInt != null){
						rzContrTallyAdd.setIsOverdue(EumConst.Y);
					} else {
						rzContrTallyAdd.setIsOverdue(EumConst.N);
					}*/
					
				}
				
				
				rzContrTallyAdd.setOperSeq(operSeq);
				rzContrTallyAdd.setDatadate(DateUtil.getCurDateStr());
				rzContrTallyAdd.setTradedate(DateUtil.getCurDateStr());
				rzContrTallyAdd.setTs(DateUtil.getTs());

				this.insertRzContrTally(rzContrTallyAdd);
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			result.addDefaultModel(rzContrTally);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<RzContrTallyProQueryVo> getRzContrTallyProQueryAll(
			RzContrTallyProQueryVo rzContrTallyProQueryVo) {
		DecimalFormat nf = new DecimalFormat("#.00");
        //nf.setMaximumFractionDigits(2);
		DecimalFormat nf1 = new DecimalFormat("#.00");
		List<RpcDicItem> rpcList=dicItemRpcClient.getDicItem("DIC_RZ_BUSI_TYPE");
		//存放所有业务类型数据字典数据
		Map<String,String>dataMap=new HashMap<String,String>();
		if(ToolUtils.isNotEmptyCollection(rpcList)){
			for(RpcDicItem item:rpcList){
				if(item != null) dataMap.put(item.getValue(), item.getText());
			}
		}
        //nf.setMaximumFractionDigits(4);  
		// TODO Auto-generated method stub
		List<RzContrTallyProQueryVo>result=new ArrayList<RzContrTallyProQueryVo>();
		/** 查询条件**/
		String pkCustomer=rzContrTallyProQueryVo.getPkCustomer();
		String pkPrjapply=rzContrTallyProQueryVo.getPkPrjapply();
		String pkPrjcontr=rzContrTallyProQueryVo.getPkPrjcontr();
		int leaseprd=rzContrTallyProQueryVo.getLeaseprd()== null?-1:rzContrTallyProQueryVo.getLeaseprd();
		int contrStatus=rzContrTallyProQueryVo.getContrStatus()==null?-1:rzContrTallyProQueryVo.getContrStatus();
		String busitype=rzContrTallyProQueryVo.getBusitype();
		String pkUserManager=rzContrTallyProQueryVo.getPkUserManager();
		/** 查询条件结束**/
		RzPrjapplyQuery query1=new RzPrjapplyQuery();
		if(StringUtils.isNotEmpty(pkCustomer)) query1.setPkCustomer(rzContrTallyProQueryVo.getPkCustomer());
		if(StringUtils.isNotEmpty(pkPrjapply)) query1.setPkPrjapply(pkPrjapply);
		if(StringUtils.isNotEmpty(busitype)) query1.setBusitype(busitype);
		if(StringUtils.isNotEmpty(pkUserManager)) query1.setPkUserManager(pkUserManager);
		List<RzPrjapply>rzPrjapplyList=rzPrjapplyDao.getRzPrjapplyAll(query1);
		RzPrjcontrQuery query2=new RzPrjcontrQuery();
		if(StringUtils.isNotEmpty(pkCustomer)) query2.setPkCustomer(rzContrTallyProQueryVo.getPkCustomer());
		if(StringUtils.isNotEmpty(pkPrjapply)) query2.setPkPrjapply(pkPrjapply);
		if(StringUtils.isNotEmpty(pkPrjcontr)) query2.setPkPrjcontr(pkPrjcontr);
		if(contrStatus !=-1) query2.setContrStatus(Integer.valueOf(contrStatus));
		if(leaseprd != -1) query2.setLeaseprd(Integer.valueOf(leaseprd));
		if(StringUtils.isNotEmpty(pkUserManager)) query2.setPkUserManager(pkUserManager);
		List<RzPrjcontr>rzPrjcontrList=rzPrjcontrDao.getRzPrjcontrAll(query2);
		List<RzPrjState>rzPrjStateList=rzPrjStateDao.getRzPrjStateAll(new RzPrjStateQuery());
		Map<String,RzPrjcontr>map=new HashMap<String,RzPrjcontr>();
		//存放合同的主键和报价测算的主键
		Map<String,String>calmap=new HashMap<String,String>();
		Map<String,Integer>prjmap=new HashMap<String,Integer>();
		Map<String,Integer>conmap=new HashMap<String,Integer>();
		if(ToolUtils.isNotEmptyCollection(rzPrjStateList)){
			for(RzPrjState state:rzPrjStateList){
				if(state != null){
					if(StringUtils.isNotEmpty(state.getPkPrjcontr())) conmap.put(state.getPkPrjcontr(), state.getPrjState());
					if(StringUtils.isNotEmpty(state.getPkPrjapply())) prjmap.put(state.getPkPrjapply(), state.getPrjState());
				}
			}
		}
		if(ToolUtils.isNotEmptyCollection(rzPrjcontrList)){
			for(RzPrjcontr con:rzPrjcontrList){
				if(con != null && StringUtils.isNotEmpty(con.getPkPrjcontr()) && StringUtils.isNotEmpty(con.getPkPrjapply())){
				map.put(con.getPkPrjapply(), con);
				calmap.put(con.getPkPrjcontr(),con.getPkPricecal());
				}
			}
		}
		if(ToolUtils.isNotEmptyCollection(rzPrjapplyList)){
			for(RzPrjapply apply:rzPrjapplyList){
				if(apply != null){
					RzContrTallyProQueryVo  vo=new RzContrTallyProQueryVo();
					vo.setPkPrjapply(apply.getPkPrjapply());
					vo.setPrjName(apply.getPrjName());
					vo.setPrjCode(apply.getPrjCode());
					vo.setApplydate(apply.getApplydate());
					vo.setPkCustomer(apply.getPkCustomer());
					vo.setCusttype(apply.getCusttype());
					if(vo.getCusttype() != null && vo.getCusttype().equals("0")) vo.setCusttype("个人客户");
					else if(vo.getCusttype() != null && vo.getCusttype().equals("1"))vo.setCusttype("企业客户");
					vo.setBusitype(apply.getBusitype());
					//转化业务类型
					if(dataMap != null) vo.setBusitypeName(dataMap.get(vo.getBusitype()));
					vo.setLeaseway(apply.getLeaseway());
					//转化租赁方式
					vo.setLeasewayName(converLeasewayType(apply.getLeaseway()));
					vo.setLeaseprd(apply.getLeaseprd());
					vo.setItemamt(apply.getItemamt());
					vo.setFirstpmtamt(apply.getFirstpmtamt());
					vo.setPricerate(Double.valueOf(nf1.format(DoubleUtils.getDoubleNullAsZero(apply.getPricerate()))));
					//当前处理人
					vo.setOperator(apply.getOperator());
					vo.setOptamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(apply.getOptamt()))));
					vo.setDepositamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(apply.getDepositamt()))));
					vo.setFeeamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(apply.getFeeamt()))));
					vo.setScamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(apply.getScamt()))));
					Double rentTotalAmt=0.0;
					Double intTotalAmt=0.0;
					if(StringUtils.isNotEmpty(apply.getPkPricecal())){
						RzPricecalLeaseQuery rzPricecalLeaseQuery=new RzPricecalLeaseQuery();
						rzPricecalLeaseQuery.setPkPricecal(apply.getPkPricecal());
						List<RzPricecalLease>rzPricecalLeaseList=rzPricecalLeaseDao.getRzPricecalLeaseAll(rzPricecalLeaseQuery);
						if(ToolUtils.isNotEmptyCollection(rzPricecalLeaseList)){
							for(RzPricecalLease lease:rzPricecalLeaseList){
								rentTotalAmt+=DoubleUtils.getDoubleNullAsZero(lease.getRptRent());
								intTotalAmt+=DoubleUtils.getDoubleNullAsZero(lease.getRptInt());
							}
						}
						
					}
					vo.setRentTotalAmt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(rentTotalAmt))));
					vo.setIntTotalAmt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(intTotalAmt))));
					vo.setReptcycle(apply.getReptcycle());
					vo.setPkUserManager(apply.getPkUserManager());
				 if(map.containsKey(apply.getPkPrjapply())){
					 RzPrjcontr con=map.get(apply.getPkPrjapply());
					 RzContrTally tally=new RzContrTally();
					 vo.setPkPrjapply(con.getPkPrjapply());
					 vo.setPrjName(con.getPrjName());
					 vo.setPrjCode(con.getPrjCode());
					 vo.setPkPrjcontr(con.getPkPrjcontr());
					 vo.setContrCode(con.getContrCode());
					 vo.setStartLeaseDate(con.getStartLeaseDate());
					 vo.setEndLeaseDate(con.getEndLeaseDate());
					 vo.setLeaseprd(con.getLeaseprd());
					 vo.setItemamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getItemamt()))));
					 vo.setFirstpmtamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getFirstpmtamt()))));
					 RzContrTallyQuery rzContrTallyQuery=new RzContrTallyQuery();
					 rzContrTallyQuery.setContrCode(con.getContrCode());
					 List<RzContrTally>rzContrTallyList=rzContrTallyDao.getRzContrTallyAll(rzContrTallyQuery);
					 if(ToolUtils.isNotEmptyCollection(rzContrTallyList)){
						 int maxSq=0;
						 for(RzContrTally tal:rzContrTallyList){
							 if(tal != null && tal.getOperSeq()!= null && tal.getOperSeq()>maxSq){
								 maxSq=tal.getOperSeq();
								 tally=tal;
							 }
						 }
					 }
					 //从台账表获取融资金额
					 vo.setFinanceamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(tally.getFinanceamt()))));
					 vo.setPricerate(Double.valueOf(nf1.format(DoubleUtils.getDoubleNullAsZero(con.getPricerate()))));
					 if(conmap.get(tally.getPkPrjcontr()) != null){
						 vo.setPrjState(conmap.get(tally.getPkPrjcontr()));
					 }else if(prjmap.get(apply.getPkPrjapply()) != null){
						 vo.setPrjState(prjmap.get(apply.getPkPrjapply()));
					 }
					 //转化项目状态
					 vo.setPrjStateName( converPrjStateType(vo.getPrjState()));
					 vo.setContrStatus(con.getContrStatus());
					//转化合同状态
					 vo.setContrStatusName(converConStateType(vo.getContrStatus()));
					 //当前处理人
					 vo.setOptamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getOptamt()))));
					 vo.setDepositamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getDepositamt()))));
					 vo.setFeeamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getFeeIntotalAmt()))));
					 vo.setScamt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getScOuttotalAmt()))));
					 vo.setRentTotalAmt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getRentTotalAmt()))));
					 vo.setIntTotalAmt(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(con.getIntTotalAmt()))));
					 RzPricecalCfQuery rzPricecalCfQuery=new RzPricecalCfQuery();
					 rzPricecalCfQuery.setPkPricecal(calmap.get(tally.getPkPrjcontr()));
					 rzPricecalCfQuery.setPlanpmttype(0);
					 List<RzPricecalCf>rzPricecalCfList=rzPricecalCfDao.getRzPricecalCfAll(rzPricecalCfQuery);
					 if(ToolUtils.isNotEmptyCollection(rzPricecalCfList)){
						 if(rzPricecalCfList.get(0) != null){
							 vo.setPutAmt(rzPricecalCfList.get(0).getPayableAmt());
							 //vo.setCurPrinBal();
						 }
					 }
					 vo.setCurPrinBal(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(tally.getCurPrinBal()))));
					 vo.setCurRentBal(Double.valueOf(nf.format(DoubleUtils.getDoubleNullAsZero(tally.getCurRentBal()))));
					 vo.setReptcycle(tally.getReptcycle());
					 vo.setIrr(Double.valueOf(nf1.format(DoubleUtils.getDoubleNullAsZero(tally.getIrr()))));
					 vo.setCurIrr(Double.valueOf(nf1.format(DoubleUtils.getDoubleNullAsZero(tally.getCurIrr()))));
					 vo.setPkUserManager(con.getPkUserManager());
				  }
				 result.add(vo);
				}
			}
		}
		//翻译开始
		if(ToolUtils.isNotEmptyCollection(result)){
			List<String>pkCustomerList=new ArrayList<String>();
			//当前操作者id集
			StringBuffer operatorBuffer=new StringBuffer();
			//当前项目经理id集
			StringBuffer managerBuffer=new StringBuffer();
			for(int i=0;i<result.size();i++){
				String pkCust=result.get(i).getPkCustomer();
				String opera="'"+result.get(i).getOperator()+"'";
				String usmanager="'"+result.get(i).getPkUserManager()+"'";
				if(StringUtils.isNotEmpty(pkCust)) pkCustomerList.add(pkCust);
				if(StringUtils.isNotEmpty(opera) &&  operatorBuffer.indexOf(opera) <0 && i ==0)  operatorBuffer.append(opera);
				else if(StringUtils.isNotEmpty(opera) && operatorBuffer.indexOf(opera) <0 && i !=0) operatorBuffer.append(","+opera);
				if(StringUtils.isNotEmpty(usmanager) && managerBuffer.indexOf(usmanager) <0 && i==0)  managerBuffer.append(usmanager);
				else if(StringUtils.isNotEmpty(usmanager)  && managerBuffer.indexOf(usmanager) <0 && i !=0 ) managerBuffer.append(","+usmanager);
			}
			//获取客户的翻译
			List<JSONObject>objList =translateDatas(rzContrTallyProQueryVo.getTendId(), "CRM_CONSUMERCLIENT", "_id", pkCustomerList);
			//存放客户主键和名称
			Map<String,String>customMap=new HashMap<String,String>();
			if(ToolUtils.isNotEmptyCollection(objList)){
				for(int i=0;i<objList.size();i++){
					customMap.put((String)objList.get(i).get("_id"),(String)objList.get(i).get("userName"));
				}
			}
			Map<String,String>operatorMap=new HashMap<String,String>();
			if(operatorBuffer.length()>0){
			SmallUserQuery smallUserQuery =new SmallUserQuery();
			smallUserQuery.setIds(operatorBuffer.toString());
			List<SmallUser>smallUserQueryList=bossUserRpcService.getBossUserByIds(smallUserQuery);
			if(ToolUtils.isNotEmptyCollection(smallUserQueryList)){
				for(SmallUser user:smallUserQueryList){
					operatorMap.put(user.getId(), user.getUserRealName());
				}
			}
			}
			Map<String,String>manageMap=new HashMap<String,String>();
			if(managerBuffer.length()>0){
			SmallUserQuery smallUserQuery1 =new SmallUserQuery();
			smallUserQuery1.setIds(managerBuffer.toString());
			List<SmallUser>smallUserQueryList1=bossUserRpcService.getBossUserByIds(smallUserQuery1);
			if(ToolUtils.isNotEmptyCollection(smallUserQueryList1)){
				for(SmallUser user:smallUserQueryList1){
					manageMap.put(user.getId(), user.getUserRealName());
				}
			}
			}
			for(RzContrTallyProQueryVo vo:result){
			if(StringUtils.isNotEmpty(vo.getPkCustomer()))	vo.setCustomerName(customMap.get(vo.getPkCustomer()));
			if(StringUtils.isNotEmpty(vo.getOperator())) vo.setOperatorName(operatorMap.get(vo.getOperator()));
			if(StringUtils.isNotEmpty(vo.getPkUserManager())) vo.setManageName(manageMap.get(vo.getPkUserManager()));
			}
		}
		return result;
	}

	public void setRzPrjapplyDao(RzPrjapplyDao rzPrjapplyDao) {
		this.rzPrjapplyDao = rzPrjapplyDao;
	}

	public void setRzPrjcontrDao(RzPrjcontrDao rzPrjcontrDao) {
		this.rzPrjcontrDao = rzPrjcontrDao;
	}

	public void setRzPrjStateDao(RzPrjStateDao rzPrjStateDao) {
		this.rzPrjStateDao = rzPrjStateDao;
	}

	public void setRzPricecalLeaseDao(RzPricecalLeaseDao rzPricecalLeaseDao) {
		this.rzPricecalLeaseDao = rzPricecalLeaseDao;
	}

	public void setRzPricecalCfDao(RzPricecalCfDao rzPricecalCfDao) {
		this.rzPricecalCfDao = rzPricecalCfDao;
	}

	@Override
	public List<RzContrTallyProQueryVo> getRzContrTallyProQueryByCondition(
			RzContrTallyProQueryVo rzContrTallyProQueryVo) {
		/** 查询条件**/
		//String pkCustomer=rzContrTallyProQueryVo.getPkCustomer();
		//String prjName=rzContrTallyProQueryVo.getPrjName();
		String applyStartdate=rzContrTallyProQueryVo.getApplyStartdate();
		String applyEnddate=rzContrTallyProQueryVo.getApplyEnddate();
		//String contrCode=rzContrTallyProQueryVo.getContrCode();
		//String leaseprd=String.valueOf(rzContrTallyProQueryVo.getLeaseprd());
		int prjState=rzContrTallyProQueryVo.getPrjState()==null?-1:rzContrTallyProQueryVo.getPrjState();
		//String  contrStatus=rzContrTallyProQueryVo.getContrStatus().toString();
		String startLeaseDate=rzContrTallyProQueryVo.getStartLeaseDate();
		String endLeaseDate=rzContrTallyProQueryVo.getEndLeaseDate();
		/** 查询条件结束**/
		 List<RzContrTallyProQueryVo> list=getRzContrTallyProQueryAll(rzContrTallyProQueryVo);
		 if(ToolUtils.isNotEmptyCollection(list)){
			 Iterator<RzContrTallyProQueryVo> it=list.iterator();
			 if(StringUtils.isNotEmpty(applyStartdate)){
				 for(;it.hasNext();){
					 RzContrTallyProQueryVo vo=	 it.next();
					 if(vo.getApplydate().compareTo(applyStartdate)<0) it.remove();
				 }
			 }
			 if(StringUtils.isNotEmpty(applyEnddate)){
				 for(;it.hasNext();){
					 RzContrTallyProQueryVo vo=	 it.next();
					 if(vo.getApplydate().compareTo(applyEnddate)>0) it.remove();
				 }
			 }
			 if(StringUtils.isNotEmpty(startLeaseDate)){
				 for(;it.hasNext();){
					 RzContrTallyProQueryVo vo=	 it.next();
					 if(vo.getStartLeaseDate().compareTo(startLeaseDate)<0) it.remove();
				 }
			 }
			 if(StringUtils.isNotEmpty(endLeaseDate)){
				 for(;it.hasNext();){
					 RzContrTallyProQueryVo vo=	 it.next();
					 if(vo.getStartLeaseDate().compareTo(endLeaseDate)>0) it.remove();
				 }
			 }
			 if(prjState != -1){
				 for(;it.hasNext();){
					 RzContrTallyProQueryVo vo=	 it.next();
					 if(!vo.getPrjState().toString().equals(prjState)) it.remove();
				 }
			 }
		 }
		// TODO Auto-generated method stub
		return list;
	}
	public List<JSONObject>translateDatas(String tenantId,String tablename,String pkItem,List<String>list){
		if(ToolUtils.isEmptyCollection(list)) return null;
		StringBuffer batchIds = new StringBuffer();
		for (int i = 0; i <list.size(); i++) {
			batchIds.append(list.get(i));
			if (i !=list.size() - 1) {
				batchIds.append(",");
			}
		}
		// RPC条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", RpcDataQuery.KeyWord.IN);
		map.put("value", batchIds.toString());
		RpcDataQuery query = new RpcDataQuery();
		JSONObject json = new JSONObject();
		List<JSONObject> conditions = new ArrayList<JSONObject>();
		json.element(pkItem, JSONObject.fromObject(map));
		conditions.add(json);
		List<JSONObject> dataList = referenceRpcService.getDataByReferences(tenantId, tablename,
				query);
		return dataList;
	}
	
	private String converLeasewayType(Integer type){
		if(type == null) return null;
		if(type == 0) return "有形动产";
		else if(type == 1) return "不动产";
		return null;
	}
	private String converPrjStateType(Integer type){
		if(type == null) return null;
		switch(type){
		case(0):return "报价单生成";
		case(1):return "立项中";
		case(2): return "项目评审中";
		case(3):return "合同签订中";
		case(4):return "项目已起租";
		case(5): return "项目租后管理中";
		case(6):return "项目结清";
		case(7): return "项目合同作废";
		default:return null;
		}
	}
	private String converConStateType(Integer type){
		if(type == null) return null;
		switch(type){
		case(0):return "已生成";
		case(1):return "已生效";
		case(2): return "起租";
		case(3):return "已结清";
		case(4):return "已作废";
		default:return null;
		}
	}
}