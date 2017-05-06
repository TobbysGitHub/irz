package com.imfbp.rz.service.rzprjreview.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import com.imfbp.rz.dao.rzprjapplyeqpt.RzPrjapplyEqptDao;
import com.imfbp.rz.dao.rzprjreview.RzPrjreviewDao;
import com.imfbp.rz.dao.rzprjrevieweqpt.RzPrjreviewEqptDao;
import com.imfbp.rz.dao.rzprjreviewlessee.RzPrjreviewLesseeDao;
import com.imfbp.rz.dao.rzprjreviewsupplier.RzPrjreviewSupplierDao;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.domain.rzprjrevieweqpt.RzPrjreviewEqpt;
import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewsupplier.RzPrjreviewSupplier;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.rzprjreview.RzPrjreviewService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

@Component("rzPrjreviewService")
public class RzPrjreviewServiceImpl implements RzPrjreviewService{


	private RzPrjreviewDao rzPrjreviewDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	
	private RzPrjapplyEqptDao rzPrjapplyEqptDao;
	
	private RzPrjreviewEqptDao rzPrjreviewEqptDao;
	@Autowired
	private RzPrjreviewSupplierDao rzPrjreviewSupplierDao;
	@Autowired
	private RzPrjapplyDao rzPrjapplyDao;
	@Autowired
	private RzPrjreviewLesseeDao rzPrjreviewLesseeDao;
	@Autowired
	private BillnoService billnoService;
	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;
	/**
	 * 添加
	 * @param rzPrjreview
	 * @return
	 */
	@Override
	public void insertRzPrjreview(RzPrjreview rzPrjreview){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjreview.setTs(DateUtil.getTs());
		rzPrjreview.setDr(0);
		rzPrjreview.setPkPrjreview(pk);
		rzPrjreview.setApprovestatus(0);
		try {
			String reviewCode=billnoService.getBillno(INodeConsts.RZ_PRJREVIEW_NO);
			rzPrjreview.setReviewCode(reviewCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rzPrjreviewDao.insertRzPrjreview(rzPrjreview);
		insertRzPrjEqpt(rzPrjreview);//根据立项编码查询立项设备，插入审核项目设备表
		insertRzPrjreviewSupplier(rzPrjreview);//插入供应商表
		insertRzPrjreviewLease(rzPrjreview);//插入承租人表
		RzPrjcontr rzPrjcontr=new RzPrjcontr();
		try {
			BeanUtils.copyProperties(rzPrjcontr, rzPrjreview);
			rzPrjcontr.setApproveid("");
			rzPrjcontr.setApprovestatus(0);
			rzPrjcontr.setApprovedate("");
			rzPrjcontr.setApprovenote("");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//根据立项编码查询立项设备，插入审核项目表
	public void insertRzPrjEqpt(RzPrjreview rzPrjreview){
		String pkPrjapply= rzPrjreview.getPkPrjapply();
		RzPrjapplyEqptQuery rzPrjapplyEqptQuery=new RzPrjapplyEqptQuery();
		rzPrjapplyEqptQuery.setPkPrjapply(pkPrjapply);
		List<RzPrjapplyEqpt> rzPrjapplyEqptList= rzPrjapplyEqptDao.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
		List<RzPrjreviewEqpt> rzPrjreviewEqptList=new LinkedList<RzPrjreviewEqpt>();
		if(rzPrjapplyEqptList.size()>0){
			for (int i = 0; i < rzPrjapplyEqptList.size(); i++) {
				String pk = primaryKeyUtil.getPrimaryKey();
				RzPrjreviewEqpt rzPrjreviewEqpt=new RzPrjreviewEqpt();
				rzPrjreviewEqpt.setPkPrjreviewEqpt(pk);
				rzPrjreviewEqpt.setEqptType(rzPrjapplyEqptList.get(i).getEqptType());
				rzPrjreviewEqpt.setPkPrjreview(rzPrjreview.getPkPrjreview());
				rzPrjreviewEqpt.setPkCustomer(rzPrjapplyEqptList.get(i).getPkCustomer());
				rzPrjreviewEqpt.setPkEqpt(rzPrjapplyEqptList.get(i).getPkEqpt());
				rzPrjreviewEqpt.setEqptBrand(rzPrjapplyEqptList.get(i).getEqptBrand());
				rzPrjreviewEqpt.setEqptVer(rzPrjapplyEqptList.get(i).getEqptVer());
				rzPrjreviewEqpt.setMfgNo(rzPrjapplyEqptList.get(i).getMfgNo());
				rzPrjreviewEqpt.setMfgDate(rzPrjapplyEqptList.get(i).getMfgDate());
				rzPrjreviewEqpt.setEqptPrice(rzPrjapplyEqptList.get(i).getEqptPrice());
				rzPrjreviewEqpt.setMfg(rzPrjapplyEqptList.get(i).getMfg());
				rzPrjreviewEqpt.setEqptNum(rzPrjapplyEqptList.get(i).getEqptNum());
				rzPrjreviewEqpt.setEqptPrice(rzPrjapplyEqptList.get(i).getEqptPrice());
				rzPrjreviewEqpt.setAssessPrice(rzPrjapplyEqptList.get(i).getAssessPrice());
				rzPrjreviewEqpt.setDeliveryDate(rzPrjapplyEqptList.get(i).getDeliveryDate());
				rzPrjreviewEqpt.setNetVal(rzPrjapplyEqptList.get(i).getNetVal());
				rzPrjreviewEqptList.add(rzPrjreviewEqpt);
			}
			rzPrjreviewEqptDao.insertBatchRzPrjreviewEqpt(rzPrjreviewEqptList);
		}
	}
	
	//插入项目审核供应商
	public void insertRzPrjreviewSupplier(RzPrjreview rzPrjreview){
		String pkPrjapply= rzPrjreview.getPkPrjapply();
		RzPrjapplyEqptQuery rzPrjapplyEqptQuery=new RzPrjapplyEqptQuery();
		rzPrjapplyEqptQuery.setPkPrjapply(pkPrjapply);
		List<RzPrjapplyEqpt> rzPrjapplyEqptList= rzPrjapplyEqptDao.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
		List<RzPrjreviewSupplier> rzPrjreviewSupplierList=new ArrayList<RzPrjreviewSupplier>();
		if(rzPrjapplyEqptList.size()>0){
			for (int i = 0; i < rzPrjapplyEqptList.size(); i++) {
				RzPrjreviewSupplier rzPrjreviewSupplier=new RzPrjreviewSupplier();
				String pkCustomer= rzPrjapplyEqptList.get(i).getPkCustomer();//获取供应商信息
				rzPrjreviewSupplier.setPkCustomer(pkCustomer);
				String pkPrjreviewSupplier = primaryKeyIdWorker.getPrimaryKey();
				rzPrjreviewSupplier.setPkPrjreviewSupplier(pkPrjreviewSupplier);
				rzPrjreviewSupplier.setPkPrjreview(rzPrjreview.getPkPrjreview());
				rzPrjreviewSupplierList.add(rzPrjreviewSupplier);
				//？？？？其它信息？供应商参照其它信息怎么带出并插入？？？
			}
			rzPrjreviewSupplierDao.insertBatchRzPrjreviewSupplier(rzPrjreviewSupplierList);
		}
	}
	
	//插入承租人表
	public void insertRzPrjreviewLease(RzPrjreview rzPrjreview){
		String pkPrjapply= rzPrjreview.getPkPrjapply();
		RzPrjapplyQuery rzPrjapplyQuery=new RzPrjapplyQuery();
		rzPrjapplyQuery.setPkPrjapply(pkPrjapply);
		RzPrjapply rzPrjapply= rzPrjapplyDao.getRzPrjapplyById(rzPrjapplyQuery);
		if(rzPrjapply!=null){
		}String pkCustomer= rzPrjapply.getPkCustomer();
		RzPrjreviewLessee rzPrjreviewLessee=new RzPrjreviewLessee();
		rzPrjreviewLessee.setPkCustomer(pkCustomer);
		rzPrjreviewLessee.setIsMainLessee("Y");
		String pkPrjreviewLessee = primaryKeyIdWorker.getPrimaryKey();
		rzPrjreviewLessee.setPkPrjreviewLessee(pkPrjreviewLessee);
		rzPrjreviewLessee.setPkPrjreview(rzPrjreview.getPkPrjreview());
		rzPrjreviewLesseeDao.insertRzPrjreviewLessee(rzPrjreviewLessee);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreview>
	 * @return
	 */
	public void insertBatchRzPrjreview(List<RzPrjreview> rzPrjreviewList){
		if(rzPrjreviewList != null){
			for(int i=0;i<rzPrjreviewList.size();i++){
				rzPrjreviewList.get(i).setTs(DateUtil.getTs());
				rzPrjreviewList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjreviewList.get(i).setPkPrjreview(pk);
			}
			rzPrjreviewDao.insertBatchRzPrjreview(rzPrjreviewList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery){
		return rzPrjreviewDao.deleteRzPrjreviewById(rzPrjreviewQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery){
		return rzPrjreviewDao.deleteRzPrjreviewByCondition(rzPrjreviewQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjreviewByBatchId(RzPrjreviewQuery rzPrjreviewQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjreviewQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjreviewDao.deleteRzPrjreviewByBatchId(data);	
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
	public boolean logicDeleteRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery){
		return rzPrjreviewDao.logicDeleteRzPrjreviewById(rzPrjreviewQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjreviewByCondition(RzPrjreviewQuery rzPrjreviewQuery){
		return rzPrjreviewDao.logicDeleteRzPrjreviewByCondition(rzPrjreviewQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzPrjreviewQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzPrjreviewByBatchId(RzPrjreviewQuery rzPrjreviewQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjreviewQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzPrjreviewDao.logicDeleteRzPrjreviewByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreview
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjreview rzPrjreview) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjreview!=null){
				if(StringUtil.isNotEmpty(rzPrjreview.getPkPrjreview())){
					updateRzPrjreviewById(rzPrjreview);
				}else{
					insertRzPrjreview(rzPrjreview);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjreview);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjreview
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewById(RzPrjreview rzPrjreview){
		return rzPrjreviewDao.updateRzPrjreviewById(rzPrjreview);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewByCondition(RzPrjreviewQuery record,RzPrjreviewQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjreviewDao.updateRzPrjreviewByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public RzPrjreview getRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery){
		return rzPrjreviewDao.getRzPrjreviewById(rzPrjreviewQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public List<RzPrjreview> getRzPrjreviewAll(RzPrjreviewQuery rzPrjreviewQuery){
		return rzPrjreviewDao.getRzPrjreviewAll(rzPrjreviewQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjreview> getRzPrjreviewByPage(RzPrjreviewQuery rzPrjreviewQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjreviewQuery!=null&&StringUtils.isBlank(rzPrjreviewQuery.getSort())){
			rzPrjreviewQuery.setSort("pk_prjreview");
			rzPrjreviewQuery.setOrder("desc");;
		}
		int total = rzPrjreviewDao.getRzPrjreviewByPageCount(rzPrjreviewQuery);
		PaginatedList<RzPrjreview> rzPrjreviewPageList = new MysqlPaginatedArrayList<RzPrjreview>(rzPrjreviewQuery,total);
		List<RzPrjreview> rzPrjreviewList = rzPrjreviewDao.getRzPrjreviewByPage(rzPrjreviewQuery);
		rzPrjreviewPageList.addAll(rzPrjreviewList);
		GridResult<RzPrjreview> result = new GridResult<RzPrjreview>(rzPrjreviewPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewQuery
	 * @return
	 */
	@Override
	public int getRzPrjreviewByPageCount(RzPrjreviewQuery rzPrjreviewQuery){
		return rzPrjreviewDao.getRzPrjreviewByPageCount(rzPrjreviewQuery);
	}

	public void setRzPrjreviewDao(RzPrjreviewDao  rzPrjreviewDao){
		this.rzPrjreviewDao = rzPrjreviewDao;
	}

	public RzPrjapplyEqptDao getRzPrjapplyEqptDao() {
		return rzPrjapplyEqptDao;
	}

	public void setRzPrjapplyEqptDao(RzPrjapplyEqptDao rzPrjapplyEqptDao) {
		this.rzPrjapplyEqptDao = rzPrjapplyEqptDao;
	}

	public RzPrjreviewEqptDao getRzPrjreviewEqptDao() {
		return rzPrjreviewEqptDao;
	}

	public void setRzPrjreviewEqptDao(RzPrjreviewEqptDao rzPrjreviewEqptDao) {
		this.rzPrjreviewEqptDao = rzPrjreviewEqptDao;
	}

	
}