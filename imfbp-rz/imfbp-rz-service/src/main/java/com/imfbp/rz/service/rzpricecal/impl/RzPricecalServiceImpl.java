package com.imfbp.rz.service.rzpricecal.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.composite.util.ObjectUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzpricecal.RzPricecalDao;
import com.imfbp.rz.dao.rzpricecalcf.RzPricecalCfDao;
import com.imfbp.rz.dao.rzpricecaleqpt.RzPricecalEqptDao;
import com.imfbp.rz.dao.rzpricecallease.RzPricecalLeaseDao;
import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.method.RentMethodCalService;
import com.imfbp.rz.service.ref.impl.DefaultPubRefDataServiceImpl;
import com.imfbp.rz.service.rzeqpt.RzEqptService;
import com.imfbp.rz.service.rzpricecal.RzPricecalService;
import com.imfbp.rz.service.rzpricecaleqpt.RzPricecalEqptService;
import com.imfbp.rz.service.rzprjapply.RzPrjapplyService;
import com.imfbp.rz.service.rzprjstate.RzPrjStateService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;





@Component("rzPricecalService")
public class RzPricecalServiceImpl implements RzPricecalService{


	private RzPricecalDao rzPricecalDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	
	@Autowired
	private RzEqptService rzEqptService;
	
	@Autowired
	private RzPricecalEqptService rzPricecalEqptService;
	@Autowired
	private  RzPrjapplyService rzPrjapplyService;
	@Autowired
	private BillnoService billnoService;
	@Autowired
	private  RentMethodCalService rentMethodCalService;
	
	@Autowired
	private  RzPrjStateService rzPrjStateService;
	
	private RzPricecalEqptDao rzPricecalEqptDao;
	
	private RzPricecalLeaseDao rzPricecalLeaseDao;
	
	private RzPricecalCfDao rzPricecalCfDao;
	
	private RzPrjapplyDao rzPrjapplyDao;
	@Autowired
	private DefaultPubRefDataServiceImpl defaultPubRefDataServiceImpl;
	
	List<RzPricecalEqpt>list1=null;
	
	List<RzPricecalLease>list2=null;
	
	List<RzPricecalCf>list3=null;
	

	/**
	 * 添加
	 * @param rzPricecal
	 * @return
	 */
	@Override
	public void insertRzPricecal(RzPricecal rzPricecal){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPricecal.setTs(DateUtil.getTs());
		rzPricecal.setDr(0);
		rzPricecal.setPkPricecal(pk);
		rzPricecal.setProjectstate(0);
		String billno;
		try {
			billno = billnoService.getBillno(INodeConsts.RZ_PRICE_NO);
			rzPricecal.setPriceno(billno);
			if(StringUtils.isEmpty(rzPricecal .getPricename())) rzPricecal.setPricename(billno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insertBatchSon(rzPricecal);
		rzPricecal.setDef1(null);
		rzPricecal.setDef2(null);
		rzPricecal.setDef3(null);
		rzPricecalDao.insertRzPricecal(rzPricecal);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecal>
	 * @return
	 */
	public void insertBatchRzPricecal(List<RzPricecal> rzPricecalList){
		if(rzPricecalList != null){
			for(int i=0;i<rzPricecalList.size();i++){
				rzPricecalList.get(i).setTs(DateUtil.getTs());
				rzPricecalList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPricecalList.get(i).setPkPricecal(pk);
			}
			rzPricecalDao.insertBatchRzPricecal(rzPricecalList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalById(RzPricecalQuery rzPricecalQuery){
		
		return rzPricecalDao.deleteRzPricecalById(rzPricecalQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery){
		if(!isTrue(rzPricecalQuery)) return false;
		return rzPricecalDao.deleteRzPricecalByCondition(rzPricecalQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPricecalQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPricecalByBatchId(RzPricecalQuery rzPricecalQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPricecalQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			for(String pk:batchIdArr){
				RzPricecalQuery temp=new RzPricecalQuery();
				temp.setPkPricecal(pk);
				if(! isTrue(temp)) return result;
			}
			boolean flat = rzPricecalDao.deleteRzPricecalByBatchId(data);	
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
	public boolean logicDeleteRzPricecalById(RzPricecalQuery rzPricecalQuery){
		if(!isTrue(rzPricecalQuery)) return false;
		return rzPricecalDao.logicDeleteRzPricecalById(rzPricecalQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPricecalByCondition(RzPricecalQuery rzPricecalQuery){
		if(!isTrue(rzPricecalQuery)) return false;
		return rzPricecalDao.logicDeleteRzPricecalByCondition(rzPricecalQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzPricecalQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzPricecalByBatchId(RzPricecalQuery rzPricecalQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPricecalQuery.getBatchId().split(",");
			for(String pk:batchIdArr){
				RzPricecalQuery temp=new RzPricecalQuery();
				temp.setPkPricecal(pk);
				if(! isTrue(temp)) return result;
			}
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzPricecalDao.logicDeleteRzPricecalByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPricecal
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPricecal rzPricecal) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPricecal!=null){
				if(StringUtil.isNotEmpty(rzPricecal.getPkPricecal())){
					updateRzPricecalById(rzPricecal);
				}else{
					insertRzPricecal(rzPricecal);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPricecal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPricecal
	 * @return
	 */
	@Override
	public boolean updateRzPricecalById(RzPricecal rzPricecal){
		if(rzPricecal == null || StringUtils.isEmpty(rzPricecal.getPkPricecal())) return false;
		RzPricecalCfQuery query=new RzPricecalCfQuery();
		query.setPkPricecal(rzPricecal.getPkPricecal());
		List<RzPricecalCf>rzPricecalCfList1=rzPricecalCfDao.getRzPricecalCfAll(query);
		if(ToolUtils.isNotEmptyCollection(rzPricecalCfList1)){
			Map<String,Object>data=new HashMap<String,Object>();
			List<String>pkList=new ArrayList<String>();
			for(RzPricecalCf cf:rzPricecalCfList1){
				pkList.add(cf.getPkPricecalCf());
			}
			data.put("batchId1",pkList);
			rzPricecalCfDao.deleteRzPricecalCfByBatchId(data);
		}
		RzPricecalLeaseQuery query2=new RzPricecalLeaseQuery();
		query2.setPkPricecal(rzPricecal.getPkPricecal());
		List<RzPricecalLease>rzPricecalLeaseList1=rzPricecalLeaseDao.getRzPricecalLeaseAll(query2);
		if(ToolUtils.isNotEmptyCollection(rzPricecalLeaseList1)){
			Map<String,Object>data=new HashMap<String,Object>();
			List<String>pkList=new ArrayList<String>();
			for(RzPricecalLease le:rzPricecalLeaseList1){
				pkList.add(le.getPkPricecalLease());
			}
			data.put("batchId1",pkList);
			rzPricecalLeaseDao.deleteRzPricecalLeaseByBatchId(data);
		}
		RzPricecalEqptQuery query3=new RzPricecalEqptQuery();
		query3.setPkPricecal(rzPricecal.getPkPricecal());
		List<RzPricecalEqpt>rzPricecalEqptList=rzPricecalEqptDao.getRzPricecalEqptAll(query3);
		if(ToolUtils.isNotEmptyCollection(rzPricecalEqptList)){
			Map<String,Object>data=new HashMap<String,Object>();
			List<String>pkList=new ArrayList<String>();
			for(RzPricecalEqpt eq:rzPricecalEqptList){
				pkList.add(eq.getPkPricecalEqpt());
			}
			data.put("batchId1",pkList);
			rzPricecalEqptDao.deleteRzPricecalEqptByBatchId(data);
		}
		insertBatchSon(rzPricecal);
		rzPricecal.setDef1(null);
		rzPricecal.setDef2(null);
		rzPricecal.setDef3(null);
		return rzPricecalDao.updateRzPricecalById(rzPricecal);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalByCondition(RzPricecalQuery record,RzPricecalQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPricecalDao.updateRzPricecalByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public RzPricecal getRzPricecalById(RzPricecalQuery rzPricecalQuery){
		return rzPricecalDao.getRzPricecalById(rzPricecalQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public List<RzPricecal> getRzPricecalAll(RzPricecalQuery rzPricecalQuery){
		if(list1 == null) list1=rzPricecalEqptDao.getRzPricecalEqptAll(new RzPricecalEqptQuery());
		if(list2 == null) list2=rzPricecalLeaseDao.getRzPricecalLeaseAll(new RzPricecalLeaseQuery());
		if(list3 == null) list3=rzPricecalCfDao.getRzPricecalCfAll(new RzPricecalCfQuery());
		return rzPricecalDao.getRzPricecalAll(rzPricecalQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public GridResult<RzPricecal> getRzPricecalByPage(RzPricecalQuery rzPricecalQuery){
		if(list1 == null) list1=rzPricecalEqptDao.getRzPricecalEqptAll(new RzPricecalEqptQuery());
		if(list2 == null) list2=rzPricecalLeaseDao.getRzPricecalLeaseAll(new RzPricecalLeaseQuery());
		if(list3 == null) list3=rzPricecalCfDao.getRzPricecalCfAll(new RzPricecalCfQuery());
		//如果排序的字段是空或者空字符串
		if(rzPricecalQuery!=null&&StringUtils.isBlank(rzPricecalQuery.getSort())){
			rzPricecalQuery.setSort("priceno");
			rzPricecalQuery.setOrder("desc");;
		}
		int total = rzPricecalDao.getRzPricecalByPageCount(rzPricecalQuery);
		PaginatedList<RzPricecal> rzPricecalPageList = new MysqlPaginatedArrayList<RzPricecal>(rzPricecalQuery,total);
		List<RzPricecal> rzPricecalList = rzPricecalDao.getRzPricecalByPage(rzPricecalQuery);
		rzPricecalPageList.addAll(rzPricecalList);
		GridResult<RzPricecal> result = new GridResult<RzPricecal>(rzPricecalPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalQuery
	 * @return
	 */
	@Override
	public int getRzPricecalByPageCount(RzPricecalQuery rzPricecalQuery){
		return rzPricecalDao.getRzPricecalByPageCount(rzPricecalQuery);
	}

	public void setRzPricecalDao(RzPricecalDao  rzPricecalDao){
		this.rzPricecalDao = rzPricecalDao;
	}

	@Override
	public List<RzEqpt> getAllRzEqpt(RzPricecalQuery rzPricecalQuery) {
		// TODO Auto-generated method stub
		RzEqptQuery rzEqptQuery=new RzEqptQuery();
		rzEqptQuery.setDr(0);
		rzEqptQuery.setEnablestate(1);
		List<RzEqpt>list=rzEqptService.getRzEqptAll(rzEqptQuery);
		if(ToolUtils.isNotEmptyCollection(list)){
			for(Iterator<RzEqpt>it=list.iterator();it.hasNext();){
				RzEqpt eq=it.next();
				if(eq != null){
					if(StringUtils.isEmpty(eq.getEqptName())){
						it.remove();
						continue;
					}
					eq.setEqptPrice(DoubleUtils.getDoubleNullAsZero(eq.getEqptPrice()));
					eq.setAssessPrice(DoubleUtils.getDoubleNullAsZero(eq.getAssessPrice()));
				}
			}
		}
		return list;
	}

	public void setRzPricecalEqptDao(RzPricecalEqptDao rzPricecalEqptDao) {
		this.rzPricecalEqptDao = rzPricecalEqptDao;
	}

	public void setRzPricecalLeaseDao(RzPricecalLeaseDao rzPricecalLeaseDao) {
		this.rzPricecalLeaseDao = rzPricecalLeaseDao;
	}

	public void setRzPricecalCfDao(RzPricecalCfDao rzPricecalCfDao) {
		this.rzPricecalCfDao = rzPricecalCfDao;
	}
	public boolean isTrue(RzPricecalQuery rzPricecalQuery){
		if(rzPricecalQuery == null) return false;
		String pk=rzPricecalQuery.getPkPricecal();
		List<RzPrjapply>list4=rzPrjapplyDao.getRzPrjapplyAll(new RzPrjapplyQuery());
		if(ToolUtils.isNotEmptyCollection(list4)){
			for(RzPrjapply ly:list4){
				if(ObjectUtils.isEmpty(ly) && StringUtils.isNotEmpty(ly.getPkPricecal()) && pk.equals(ly.getPkPricecal())){
					return false;
				}
			}
		}
		RzPricecalEqptQuery query1=new RzPricecalEqptQuery();
		query1.setPkPricecal(pk);
		List<RzPricecalEqpt>list1=rzPricecalEqptDao.getRzPricecalEqptAll(query1);
		if(ToolUtils.isNotEmptyCollection(list1)){
			List<String>pk1=new ArrayList<String>();
			for(RzPricecalEqpt qt:list1){
				if(qt != null && StringUtils.isNotEmpty(qt.getPkPricecalEqpt())){
					pk1.add(qt.getPkPricecalEqpt());
				}
			}
			Map<String,Object>data=new HashMap<String,Object>();
			data.put("batchId1",pk1);
			rzPricecalEqptDao.deleteRzPricecalEqptByBatchId(data);
		}
		RzPricecalLeaseQuery query2=new RzPricecalLeaseQuery();
		query2.setPkPricecal(pk);
		List<RzPricecalLease>list2=rzPricecalLeaseDao.getRzPricecalLeaseAll(query2);
		if(ToolUtils.isNotEmptyCollection(list2)){
			List<String>pk2=new ArrayList<String>();
			for(RzPricecalLease qt:list2){
				if(qt != null && StringUtils.isNotEmpty(qt.getPkPricecalLease())){
					pk2.add(qt.getPkPricecalLease());
				}
			}
			Map<String,Object>data=new HashMap<String,Object>();
			data.put("batchId1",pk2);
			rzPricecalLeaseDao.deleteRzPricecalLeaseByBatchId(data);
		}
		RzPricecalCfQuery query3=new RzPricecalCfQuery();
		query3.setPkPricecal(pk);
		List<RzPricecalCf>list3=rzPricecalCfDao.getRzPricecalCfAll(query3);
		if(ToolUtils.isNotEmptyCollection(list3)){
			List<String>pk3=new ArrayList<String>();
			for(RzPricecalCf qt:list3){
				if(qt != null && StringUtils.isNotEmpty(qt.getPkPricecalCf())){
					pk3.add(qt.getPkPricecalCf());
				}
			}
			Map<String,Object>data=new HashMap<String,Object>();
			data.put("batchId1",pk3);
			rzPricecalCfDao.deleteRzPricecalCfByBatchId(data);
		}
		return true;
	}

	@Override
	public Result updateByBatchId(RzPricecalQuery rzPricecalQuery) {
			Result result = new Result();
			result.setSuccess(false);
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPricecalQuery.getBatchId().split(",");
			if(rzPricecalQuery.getProjectstate() == 0){
			List<RzPrjapply>list4=rzPrjapplyDao.getRzPrjapplyAll(new RzPrjapplyQuery());
			if(batchIdArr != null){
				for(String id:batchIdArr){
					if(StringUtils.isNotEmpty(id)){
						for(RzPrjapply ly:list4){
							if(!ObjectUtils.isEmpty(ly) && StringUtils.isNotEmpty(ly.getPkPricecal()) && id.equals(ly.getPkPricecal())){
								result.setErrorMessage("该立项申请表已经被使用,无法取消立项");
								return result;
							}
						}
						 //取消立项更新项目状态表
						 RzPrjState rzPrjState=new RzPrjState();
						 rzPrjState.setPkPricecal(id);
						 rzPrjState.setPrjState(0);
						 rzPrjStateService.insertOrUpdatePrjState(rzPrjState);
					}
				}
			}
		 }else{
			 Date date=new Date();
			 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");		 
			 //启动立项，往立项申请中插入记录
		for(String id:batchIdArr){
			 RzPrjapply rzPrjapply=new RzPrjapply();
			 RzPricecalQuery query=new RzPricecalQuery();
			 query.setPkPricecal(id);
			 RzPricecal rzPricecal=rzPricecalDao.getRzPricecalById(query);
			 try {
				BeanUtils.copyProperties(rzPrjapply, rzPricecal);
				rzPrjapply.setApplydate(format.format(date));
				rzPrjapply.setPlanrentdate(rzPricecal.getPlanleasedate());
				rzPrjapplyService.insertRzPrjapply(rzPrjapply, rzPricecalQuery.getDef1());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			 //启动立项更新项目状态表
			 RzPrjState rzPrjState=new RzPrjState();
			 rzPrjState.setPkPricecal(id);
			 rzPrjState.setPrjState(1);
			 rzPrjStateService.insertOrUpdatePrjState(rzPrjState);
		   }
		 }
			rzPricecalQuery.setDef1(null);
			data.put("batchId",batchIdArr);
			data.put("action",rzPricecalQuery.getProjectstate());
			boolean flag = rzPricecalDao.updateByBatchId(data);
			result.setSuccess(flag);
		    return result;
	}
    
	//批量插入3个子表
	public void insertBatchSon(RzPricecal rzPricecal){
		if(StringUtils.isNotEmpty(rzPricecal.getDef1())){
			List<RzPricecalEqpt>list=new ArrayList<RzPricecalEqpt>();
			String[]pks=rzPricecal.getDef1().split(",");
			String nums[]=rzPricecal.getDef2().split(",");
			String []prices=rzPricecal.getDef3().split(",");
			for(int i=0;i<pks.length;i++){
				RzPricecalEqpt eq=new RzPricecalEqpt();
				String pkEq=primaryKeyUtil.getPrimaryKey();
				eq.setPkEqpt(pks[i]);
				eq.setNum(Integer.valueOf(nums[i]));
				eq.setPkPricecal(rzPricecal.getPkPricecal());
				eq.setPrice(Double.valueOf(prices[i]));
				eq.setPkPricecalEqpt(pkEq);
				list.add(eq);
			}
			rzPricecalEqptService.insertBatchRzPricecalEqpt(list);
		}
		try {
			List<Double>irrs=new ArrayList<Double>();
			List<RzPricecalLease>rzPricecalLeaseList=rentMethodCalService.calLease(rzPricecal);
			if(ToolUtils.isNotEmptyCollection(rzPricecalLeaseList)){
				for(RzPricecalLease ls:rzPricecalLeaseList){
					String rzPricecalLeasePk=primaryKeyUtil.getPrimaryKey();
					ls.setPkPricecalLease(rzPricecalLeasePk);
					irrs.add(ls.getNetCfIn());
					if(StringUtils.isNotEmpty(rzPricecal.getPkPricecal())) ls.setPkPricecal(rzPricecal.getPkPricecal());
				}
			}
			rzPricecalLeaseDao.insertBatchRzPricecalLease(rzPricecalLeaseList);
			List<RzPricecalCf>rzPricecalCfList=rentMethodCalService.calCf(rzPricecal);
			if(ToolUtils.isNotEmptyCollection(rzPricecalCfList)){
				for(RzPricecalCf cf:rzPricecalCfList){
					String cfpk=primaryKeyUtil.getPrimaryKey();
					cf.setPkPricecalCf(cfpk);
					if(StringUtils.isNotEmpty(rzPricecal.getPkPricecal())) cf.setPkPricecal(rzPricecal.getPkPricecal());
				}
				rzPricecalCfDao.insertBatchRzPricecalCf(rzPricecalCfList);
			}
			Double irr=DoubleUtils.getDoubleNullAsZero(rentMethodCalService.calIRR(irrs, rzPricecal));
			rzPricecal.setIrr(irr);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setRzPrjapplyDao(RzPrjapplyDao rzPrjapplyDao) {
		this.rzPrjapplyDao = rzPrjapplyDao;
	}

	@Override
	public boolean updateRzPricecalByState(RzPricecal rzPricecal) {
		// TODO Auto-generated method stub
		if(rzPricecal == null || StringUtils.isEmpty(rzPricecal.getPkPricecal())) return false;
		int state=rzPricecal.getProjectstate();
		String pk=rzPricecal.getPkPricecal();
		if(state == 0){
			List<RzPrjapply>list4=rzPrjapplyDao.getRzPrjapplyAll(new RzPrjapplyQuery());
			if(ToolUtils.isNotEmptyCollection(list4)){
				for(RzPrjapply ly:list4){
					if(!ObjectUtils.isEmpty(ly) && StringUtils.isNotEmpty(ly.getPkPricecal()) && pk.equals(ly.getPkPricecal())){
						return false;
					}
				}
			}
			 //取消立项更新项目状态表
			 RzPrjState rzPrjState=new RzPrjState();
			 rzPrjState.setPkPricecal(rzPricecal.getPkPricecal());
			 rzPrjState.setPrjState(0);
			 rzPrjStateService.insertOrUpdatePrjState(rzPrjState);
		}else{
			 Date date=new Date();
			 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");		 
			 //启动立项，往立项申请中插入记录
			 RzPrjapply rzPrjapply=new RzPrjapply();
			 RzPricecalQuery query=new RzPricecalQuery();
			 query.setPkPricecal(rzPricecal.getPkPricecal());
			 RzPricecal rzPricecal1=rzPricecalDao.getRzPricecalById(query);
			 try {
				BeanUtils.copyProperties(rzPrjapply, rzPricecal1);
				rzPrjapply.setApplydate(format.format(date));
				rzPrjapplyService.insertRzPrjapply(rzPrjapply, rzPricecal.getDef1());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			 //启动立项更新项目状态表
			 RzPrjState rzPrjState=new RzPrjState();
			 rzPrjState.setPkPricecal(rzPricecal1.getPkPricecal());
			 rzPrjState.setPrjState(1);
			 rzPrjStateService.insertOrUpdatePrjState(rzPrjState);
		   }
		return rzPricecalDao.updateRzPricecalById(rzPricecal);
	}
    
	/**
	 * 根据前台传的表头获取翻译数据
	 */
	@Override
	public List<RzPricecal> getTranslateData(List<RzPricecal>rzPricecalList,String str, RefBaseQuery query) {
		// TODO Auto-generated method stub
		Map<String,String[]>transData=new HashMap<String,String[]>();//需要翻译的数据
		@SuppressWarnings("unchecked")
		List<JSONObject>list2=JSONArray.fromObject(str.trim());
		if(ToolUtils.isNotEmptyCollection(list2)){
			for(JSONObject json:list2){
				List<String>value=new ArrayList<String>();
				if(json.has("imfbpRefType") && StringUtils.isNotEmpty(json.getString("imfbpRefType"))){
					for(RzPricecal cal:rzPricecalList){
						PropertyDescriptor pd;
						try {
							pd = new PropertyDescriptor(json.getString("field"), cal.getClass());
							Method getMethod = pd.getReadMethod();
							if(pd != null){
								Object obj=getMethod.invoke(cal);
								if(StringUtils.isNotEmpty(obj.toString())){
									value.add(obj.toString());
								}
							}
						} catch (IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						transData.put(json.getString("imfbpRefType"),value.toArray(new String[0]));
						
					}
					
				}
			}
		}
		if(transData.size()>0){
			Map<String,String>tranResult=new HashMap<String,String>();
			try {
				//将翻译数据以id,value的形式保存在map中
				List<RefResult>result=defaultPubRefDataServiceImpl.getRefTranslateDatas(query, transData);
				if(ToolUtils.isNotEmptyCollection(result)){
					for(RefResult refs:result){
						List<Object>temp=refs.getDatas();
						String mainShowItem=refs.getRefMetaDataBean().getMainShowItem();
						String idItem=refs.getRefMetaDataBean().getIdItem();
						String type=refs.getRefMetaDataBean().getRefType();
						 if(ToolUtils.isNotEmptyCollection(temp)){
							for(Object obj:temp){
								String showValue="",id="";
								Class clazz=obj.getClass();
								if(obj instanceof JSONObject){
									id=((JSONObject) obj).getString(idItem);
									showValue=((JSONObject) obj).getString(mainShowItem);
								}else{
								PropertyDescriptor pd=null;
								if("list".equals(type)) pd = new PropertyDescriptor(mainShowItem,clazz);
								else if("tree".equals(type)) pd = new PropertyDescriptor("text",clazz);
						    	if(pd != null){
						    		Method method=pd.getReadMethod();
						    		showValue= (String) method.invoke(obj);
						    	}
						    	pd=new PropertyDescriptor(idItem,clazz);
						    	if(pd != null){
						    		Method method=pd.getReadMethod();
							    	id= (String) method.invoke(obj);	
						    	  }
								}
						    	tranResult.put(id,showValue );
						     }
						  }
					}
					//开始翻译数据的回写
					if(ToolUtils.isNotEmptyCollection(list2)){
						for(JSONObject json:list2){
							if(json.has("imfbpRefType") && StringUtils.isNotEmpty(json.getString("imfbpRefType"))){
								for(RzPricecal cal:rzPricecalList){
									PropertyDescriptor pd;
										pd = new PropertyDescriptor(json.getString("field"), cal.getClass());
										Method setMethod = pd.getWriteMethod();
										Method getMethod = pd.getReadMethod();
										if(pd != null){
											String id=(String) getMethod.invoke(cal);
											String showValue=tranResult.get(id);
											setMethod.invoke(cal,showValue);
										}
								}
							}
						}
					}
				}
				return rzPricecalList;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}