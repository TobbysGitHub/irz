package com.imfbp.rz.service.rzprjcontr.impl;

import java.util.*;

import com.imfbp.brefdata.rpc.reference.domain.RpcDataQuery;
import com.imfbp.brefdata.rpc.reference.service.ReferenceRpcService;
import com.imfbp.rz.dao.rzpricecalcf.RzPricecalCfDao;
import com.imfbp.rz.dao.rzpricecallease.RzPricecalLeaseDao;
import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import com.imfbp.rz.dao.rzprjcontrcf.RzPrjcontrCfDao;
import com.imfbp.rz.dao.rzprjcontreqpt.RzPrjcontrEqptDao;
import com.imfbp.rz.dao.rzprjcontrguar.RzPrjcontrGuarDao;
import com.imfbp.rz.dao.rzprjcontrguarb.RzPrjcontrGuarBDao;
import com.imfbp.rz.dao.rzprjcontrlease.RzPrjcontrLeaseDao;
import com.imfbp.rz.dao.rzprjcontrlessee.RzPrjcontrLesseeDao;
import com.imfbp.rz.dao.rzprjcontrsupplier.RzPrjcontrSupplierDao;
import com.imfbp.rz.dao.rzprjrevieweqpt.RzPrjreviewEqptDao;
import com.imfbp.rz.dao.rzprjreviewguar.RzPrjreviewGuarDao;
import com.imfbp.rz.dao.rzprjreviewguarb.RzPrjreviewGuarBDao;
import com.imfbp.rz.dao.rzprjreviewlessee.RzPrjreviewLesseeDao;
import com.imfbp.rz.dao.rzprjreviewsupplier.RzPrjreviewSupplierDao;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjcontrcf.RzPrjcontrCf;
import com.imfbp.rz.domain.rzprjcontreqpt.RzPrjcontrEqpt;
import com.imfbp.rz.domain.rzprjcontrguar.RzPrjcontrGuar;
import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrlease.RzPrjcontrLease;
import com.imfbp.rz.domain.rzprjcontrlessee.RzPrjcontrLessee;
import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjrevieweqpt.RzPrjreviewEqpt;
import com.imfbp.rz.domain.rzprjrevieweqpt.query.RzPrjreviewEqptQuery;
import com.imfbp.rz.domain.rzprjreviewguar.RzPrjreviewGuar;
import com.imfbp.rz.domain.rzprjreviewguar.query.RzPrjreviewGuarQuery;
import com.imfbp.rz.domain.rzprjreviewguarb.RzPrjreviewGuarB;
import com.imfbp.rz.domain.rzprjreviewguarb.query.RzPrjreviewGuarBQuery;
import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewlessee.query.RzPrjreviewLesseeQuery;
import com.imfbp.rz.domain.rzprjreviewsupplier.RzPrjreviewSupplier;
import com.imfbp.rz.domain.rzprjreviewsupplier.query.RzPrjreviewSupplierQuery;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.util.DateUtil;





@Component("rzPrjcontrService")
public class RzPrjcontrServiceImpl implements RzPrjcontrService{


	private RzPrjcontrDao rzPrjcontrDao;

	@Autowired
	private BillnoService billnoService;
	//报价方案现金流计划Dao
	private RzPricecalCfDao rzPricecalCfDao;
	//合同现金流计划Dao
	private RzPrjcontrCfDao rzPrjcontrCfDao;
	//报价方案租金计划Dao
	private RzPricecalLeaseDao rzPricecalLeaseDao;
	//合同租金计划Dao
	private RzPrjcontrLeaseDao rzPrjcontrLeaseDao;

	//评审单承租人Dao
	private RzPrjreviewLesseeDao rzPrjreviewLesseeDao;
	//合同承租人Dao
	private RzPrjcontrLesseeDao rzPrjcontrLesseeDao;

	//评审单供应商Dao
	private RzPrjreviewSupplierDao rzPrjreviewSupplierDao;
	//合同供应商Dao
	private RzPrjcontrSupplierDao rzPrjcontrSupplierDao;

	//评审单租赁物Dao
	private RzPrjreviewEqptDao rzPrjreviewEqptDao;
	//合同租赁物Dao
	private RzPrjcontrEqptDao rzPrjcontrEqptDao;

	//评审单担保方主表Dao
	private RzPrjreviewGuarDao rzPrjreviewGuarDao;
	//评审单担保方子表Dao
	private RzPrjreviewGuarBDao rzPrjreviewGuarBDao;

	//合同担保方主表Dao
	private RzPrjcontrGuarDao rzPrjcontrGuarDao;
	//合同担保方子表Dao
	private RzPrjcontrGuarBDao rzPrjcontrGuarBDao;
	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	//批量主键生成策略
	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;

	@Autowired
	private ReferenceRpcService referenceRpcService;
	//立项申请Dao
	private RzPrjapplyDao rzPrjapplyDao;

	/**
	 * 添加
	 * @param rzPrjcontr
	 * @return
	 */
	@Override
	public void insertRzPrjcontr(RzPrjcontr rzPrjcontr, String tenantId){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontr.setTs(DateUtil.getTs());
		rzPrjcontr.setDr(0);
		rzPrjcontr.setPkPrjcontr(pk);
		//新增时设置合同版本
		rzPrjcontr.setContrVer("1.0");
		rzPrjcontr.setIsNew("Y");
		try {
			String billno = billnoService.getBillno(INodeConsts.RZ_PRJCONTR_NO);
			if(!StringUtils.isBlank(billno)){
				//自动生成单据号
				rzPrjcontr.setContrCode(billno);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(StringUtils.isBlank(rzPrjcontr.getContrName())){
			String pkCustomer = rzPrjcontr.getPkCustomer();
			RpcDataQuery query = new RpcDataQuery();
			JSONObject json = new JSONObject();
			List<JSONObject> conditions = new ArrayList<JSONObject>();
			json.element("_id", JSONObject.fromObject(
					"{\"keyword\" : \"" + RpcDataQuery.KeyWord.EQ + "\",value:\"" + pkCustomer + "\"}"));
			conditions.add(json);
			query.setConditions(conditions);
			List<JSONObject> dataList = referenceRpcService.getDataByReferences(tenantId, "CRM_CONSUMERCLIENT",
					query);
			//参照翻译
			String customerName = "";
			if (ToolUtils.isNotEmptyCollection(dataList)) {
				customerName = dataList.get(0).getString("userName");
			}
			RzPrjapplyQuery rzPrjapplyQuery = new RzPrjapplyQuery();
			rzPrjapplyQuery.setPkCustomer(pkCustomer);
			int countPrjapply = rzPrjapplyDao.getRzPrjapplyByPageCount(rzPrjapplyQuery) + 1;
			//项目个数3位流水号
			String floatNum = String.format("%03d", countPrjapply);
			RzPrjcontrQuery rzPrjcontrQuery = new RzPrjcontrQuery();
			rzPrjcontrQuery.setPkCustomer(pkCustomer);
			rzPrjcontrQuery.setPkPrjapply(rzPrjcontr.getPkPrjapply());
			int countPrjcontr = rzPrjcontrDao.getRzPrjcontrByPageCount(rzPrjcontrQuery) + 1;
			rzPrjcontr.setContrName(customerName + floatNum + countPrjcontr);
		}
		rzPrjcontrDao.insertRzPrjcontr(rzPrjcontr);
		//插入合同现金流计划
		this.insertRzPrjcontrCf(rzPrjcontr);
		//插入合同租金计划
		this.insertRzPrjcontrLease(rzPrjcontr);
		//插入合同承租人信息
		this.insertRzPrjcontrLessee(rzPrjcontr);
		//插入合同供应商信息
		this.insertRzPrjcontrSupplier(rzPrjcontr);
		//插入合同租赁物信息
		this.insertRzPrjcontrEqpt(rzPrjcontr);
		//插入合同担保方信息
		this.insertRzPrjcontrGuarAndGuarB(rzPrjcontr);
	}

	/**
	 * 根据报价方案查询报价方案租金计划
	 * @author: zhengjm5
	 * @Date: 2016-12-27 20:57:33
	 * @param pkPricecal
	 * @return
	 */
	private List<RzPricecalLease> queryRzPricecalLeaseByCond(String pkPricecal){
		if(StringUtils.isBlank(pkPricecal)){
			return null;
		}
		RzPricecalLeaseQuery rzPricecalLeaseQuery = new RzPricecalLeaseQuery();
		//报价测算主键
		rzPricecalLeaseQuery.setPkPricecal(pkPricecal);
		List<RzPricecalLease> rzPricecalLeaseList = rzPricecalLeaseDao.getRzPricecalLeaseAll(rzPricecalLeaseQuery);
		return rzPricecalLeaseList;
	}

	/**
	 * 插入合同租金计划
	 * @author: zhengjm5
	 * @Date: 2016-12-27 21:03:34
	 * @param rzPrjcontr
	 */
	private void insertRzPrjcontrLease(RzPrjcontr rzPrjcontr){
		//报价方案主键
		String pkPricecal = rzPrjcontr.getPkPricecal();
		if(StringUtils.isBlank(pkPricecal)){
			return;
		}
		List<RzPricecalLease> rzPricecalLeaseList = this.queryRzPricecalLeaseByCond(pkPricecal);
		if(ToolUtils.isEmptyCollection(rzPricecalLeaseList)){
			return;
		}
		List<RzPrjcontrLease> rzPrjcontrLeaseList = new ArrayList<RzPrjcontrLease>();
		for(RzPricecalLease rzPricecalLease : rzPricecalLeaseList){
			RzPrjcontrLease rzPrjcontrLease = new RzPrjcontrLease();
			try {
				BeanUtils.copyProperties(rzPrjcontrLease, rzPricecalLease);
				//合同主键
				rzPrjcontrLease.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
				//合同现金流主键
				rzPrjcontrLease.setPkPricecalRent(primaryKeyIdWorker.getPrimaryKey());
				//租赁利率
				rzPrjcontrLease.setRentRate(rzPricecalLease.getLeaseRate());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrLeaseList.add(rzPrjcontrLease);
		}
		if(!rzPrjcontrLeaseList.isEmpty()){
			rzPrjcontrLeaseDao.insertBatchRzPrjcontrLease(rzPrjcontrLeaseList);
		}
	}
	/**
	 * 插入合同的现金流计划
	 * @author: zhengjm5
	 * @Date: 2016-12-07 18:42:12
	 * @param rzPrjcontr
	 */
	private void insertRzPrjcontrCf(RzPrjcontr rzPrjcontr){
		//报价方案主键
		String pkPricecal = rzPrjcontr.getPkPricecal();
		if(StringUtils.isBlank(pkPricecal)){
			return;
		}
		List<RzPricecalCf> rzPricecalCfList = this.queryRzPricecalCfByCond(pkPricecal);
		if(rzPricecalCfList == null || rzPricecalCfList.isEmpty()){
			return;
		}
		List<RzPrjcontrCf> rzPrjcontrCfList = new ArrayList<RzPrjcontrCf>();
		for(RzPricecalCf rzPricecalCf : rzPricecalCfList){
			RzPrjcontrCf rzPrjcontrCf = new RzPrjcontrCf();
			try {
				BeanUtils.copyProperties(rzPrjcontrCf, rzPricecalCf);
				//合同主键
				rzPrjcontrCf.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
				//合同现金流主键
				rzPrjcontrCf.setPkPrjcontrCf(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrCfList.add(rzPrjcontrCf);
		}
		if(!rzPrjcontrCfList.isEmpty()){
			rzPrjcontrCfDao.insertBatchRzPrjcontrCf(rzPrjcontrCfList);
		}
	}

	/**
	 * 根据报价方案查询报价方案的现金流计划
	 * @author: zhengjm5
	 * @Date: 2016-12-07 18:41:44
	 * @param pkPricecal
	 * @return
	 */
	private List<RzPricecalCf> queryRzPricecalCfByCond(String pkPricecal){
		if(StringUtils.isBlank(pkPricecal)){
			return null;
		}
		RzPricecalCfQuery rzPricecalCfQuery = new RzPricecalCfQuery();
		//报价方案主键
		rzPricecalCfQuery.setPkPricecal(pkPricecal);
		List<RzPricecalCf> rzPricecalCfList = rzPricecalCfDao.getRzPricecalCfAll(rzPricecalCfQuery);
		return rzPricecalCfList;
	}

	/**
	 * 插入合同的承租人信息
	 * @author: zhengjm5
	 * @Date: 2016-12-14 20:21:01
	 * @param rzPrjcontr
	 */
	private void insertRzPrjcontrLessee(RzPrjcontr rzPrjcontr){
		//评审单主键
		String pkPrjreview = rzPrjcontr.getPkPrjreview();
		if(StringUtils.isBlank(pkPrjreview)){
			return;
		}
		List<RzPrjreviewLessee> rzPrjreviewLesseeList = this.queryRzPrjreviewLesseeByCond(pkPrjreview);
		if(rzPrjreviewLesseeList == null || rzPrjreviewLesseeList.isEmpty()){
			return;
		}
		List<RzPrjcontrLessee> rzPrjcontrLesseeList = new ArrayList<RzPrjcontrLessee>();
		for(RzPrjreviewLessee rzPrjreviewLessee : rzPrjreviewLesseeList){
			RzPrjcontrLessee rzPrjcontrLessee = new RzPrjcontrLessee();
			try {
				BeanUtils.copyProperties(rzPrjcontrLessee, rzPrjreviewLessee);
				//合同主键
				rzPrjcontrLessee.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
				//合同承租人主键
				rzPrjcontrLessee.setPkPrjcontrLessee(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrLesseeList.add(rzPrjcontrLessee);
		}
		if(!rzPrjcontrLesseeList.isEmpty()){
			rzPrjcontrLesseeDao.insertBatchRzPrjcontrLessee(rzPrjcontrLesseeList);
		}
	}
	/**
	 * 根据评审单主键查询评审单承租人信息
	 * @author: zhengjm5
	 * @Date: 2016-12-10 16:28:03
	 * @param pkPrjreview
	 * @return
	 */
	private List<RzPrjreviewLessee> queryRzPrjreviewLesseeByCond(String pkPrjreview){
		if(StringUtils.isBlank(pkPrjreview)){
			return null;
		}
		RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery = new RzPrjreviewLesseeQuery();
		//评审单主键
		rzPrjreviewLesseeQuery.setPkPrjreview(pkPrjreview);
		List<RzPrjreviewLessee> rzPrjreviewLesseeList = rzPrjreviewLesseeDao.getRzPrjreviewLesseeAll(rzPrjreviewLesseeQuery);
		return rzPrjreviewLesseeList;
	}

	/**
	 * 根据评审单主键查询评审单供应商信息
	 * @author: zhengjm5
	 * @Date: 2016-12-14 20:11:30
	 * @param pkPrjreview
	 * @return
	 */
	private List<RzPrjreviewSupplier> queryRzPrjreviewSupplierByCond(String pkPrjreview){
		if(StringUtils.isBlank(pkPrjreview)){
			return null;
		}
		RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery = new RzPrjreviewSupplierQuery();
		//评审单主键
		rzPrjreviewSupplierQuery.setPkPrjreview(pkPrjreview);
		List<RzPrjreviewSupplier> rzPrjreviewSupplierList = rzPrjreviewSupplierDao.getRzPrjreviewSupplierAll(rzPrjreviewSupplierQuery);
		return rzPrjreviewSupplierList;
	}

	/**
	 * 插入合同供应商信息
	 * @author: zhengjm5
	 * @Date: 2016-12-14 20:21:01
	 * @param rzPrjcontr
	 */
	private void insertRzPrjcontrSupplier(RzPrjcontr rzPrjcontr){
		//评审单主键
		String pkPrjreview = rzPrjcontr.getPkPrjreview();
		if(StringUtils.isBlank(pkPrjreview)){
			return;
		}
		List<RzPrjreviewSupplier> rzPrjreviewSupplierList = this.queryRzPrjreviewSupplierByCond(pkPrjreview);
		if(rzPrjreviewSupplierList == null || rzPrjreviewSupplierList.isEmpty()){
			return;
		}
		List<RzPrjcontrSupplier> rzPrjcontrSupplierList = new ArrayList<RzPrjcontrSupplier>();
		for(RzPrjreviewSupplier rzPrjreviewSupplier : rzPrjreviewSupplierList){
			RzPrjcontrSupplier rzPrjcontrSupplier = new RzPrjcontrSupplier();
			try {
				BeanUtils.copyProperties(rzPrjcontrSupplier, rzPrjreviewSupplier);
				//合同主键
				rzPrjcontrSupplier.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
				//合同供应商主键
				rzPrjcontrSupplier.setPkPrjcontrSupplier(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrSupplierList.add(rzPrjcontrSupplier);
		}
		if(rzPrjcontrSupplierList.size() > 0){
			rzPrjcontrSupplierDao.insertBatchRzPrjcontrSupplier(rzPrjcontrSupplierList);
		}
	}

	/**
	 * 根据评审单主键查询评审单租赁物信息
	 * @author: zhengjm5
	 * @Date: 2016-12-14 20:26:13
	 * @param pkPrjreview
	 * @return
	 */
	private List<RzPrjreviewEqpt> queryRzPrjreviewEqptByCond(String pkPrjreview){
		if(StringUtils.isBlank(pkPrjreview)){
			return null;
		}
		RzPrjreviewEqptQuery rzPrjreviewEqptQuery = new RzPrjreviewEqptQuery();
		//评审单主键
		rzPrjreviewEqptQuery.setPkPrjreview(pkPrjreview);
		List<RzPrjreviewEqpt> rzPrjreviewEqptList = rzPrjreviewEqptDao.getRzPrjreviewEqptAll(rzPrjreviewEqptQuery);
		return rzPrjreviewEqptList;
	}

	/**
	 * 插入合同租赁物信息
	 * @author: zhengjm5
	 * @Date: 2016-12-14 20:31:33
	 * @param rzPrjcontr
	 */
	private void insertRzPrjcontrEqpt(RzPrjcontr rzPrjcontr){
		//评审单主键
		String pkPrjreview = rzPrjcontr.getPkPrjreview();
		if(StringUtils.isBlank(pkPrjreview)){
			return;
		}
		List<RzPrjreviewEqpt> rzPrjreviewEqptList = this.queryRzPrjreviewEqptByCond(pkPrjreview);
		if(rzPrjreviewEqptList == null || rzPrjreviewEqptList.isEmpty()){
			return;
		}
		List<RzPrjcontrEqpt> rzPrjcontrEqptList = new ArrayList<RzPrjcontrEqpt>();
		for(RzPrjreviewEqpt rzPrjreviewEqpt : rzPrjreviewEqptList){
			RzPrjcontrEqpt rzPrjcontrEqpt = new RzPrjcontrEqpt();
			try {
				BeanUtils.copyProperties(rzPrjcontrEqpt, rzPrjreviewEqpt);
				//合同主键
				rzPrjcontrEqpt.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
				//合同租赁物主键
				rzPrjcontrEqpt.setPkPrjcontrEqpt(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrEqptList.add(rzPrjcontrEqpt);
		}
		if(rzPrjcontrEqptList.size() > 0){
			rzPrjcontrEqptDao.insertBatchRzPrjcontrEqpt(rzPrjcontrEqptList);
		}
	}

	/**
	 * 根据评审单主键查询评审单担保方主表信息
	 * @author: zhengjm5
	 * @Date: 2016-12-15 09:46:57
	 * @param pkPrjreview
	 * @return
	 */
	private List<RzPrjreviewGuar> queryRzPrjreviewGuarByCond(String pkPrjreview){
		if(StringUtils.isBlank(pkPrjreview)){
			return null;
		}
		RzPrjreviewGuarQuery rzPrjreviewGuarQuery = new RzPrjreviewGuarQuery();
		rzPrjreviewGuarQuery.setPkPrjreview(pkPrjreview);
		List<RzPrjreviewGuar> rzPrjreviewGuarList = rzPrjreviewGuarDao.getRzPrjreviewGuarAll(rzPrjreviewGuarQuery);
		return rzPrjreviewGuarList;
	}

	/**
	 * 根据评审单主键查询评审单担保方子表信息
	 * @author: zhengjm5
	 * @Date: 2016-12-15 09:49:30
	 * @param pkPrjreview
	 * @return
	 */
	private List<RzPrjreviewGuarB> queryRzPrjreviewGuarBByCond(String pkPrjreview){
		if(StringUtils.isBlank(pkPrjreview)){
			return null;
		}
		RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery = new RzPrjreviewGuarBQuery();
		rzPrjreviewGuarBQuery.setPkPrjreview(pkPrjreview);
		List<RzPrjreviewGuarB> rzPrjreviewGuarBList = rzPrjreviewGuarBDao.getRzPrjreviewGuarBAll(rzPrjreviewGuarBQuery);
		return rzPrjreviewGuarBList;
	}

	/**
	 * 插入合同担保方信息
	 * @author: zhengjm5
	 * @Date: 2016-12-15 13:46:16
	 * @param rzPrjcontr
	 */
	private void insertRzPrjcontrGuarAndGuarB(RzPrjcontr rzPrjcontr){
		//评审单主键
		String pkPrjreview = rzPrjcontr.getPkPrjreview();
		if(StringUtils.isBlank(pkPrjreview)){
			return;
		}
		//查询评审单担保方主表
		List<RzPrjreviewGuar> rzPrjreviewGuarList = this.queryRzPrjreviewGuarByCond(pkPrjreview);
		if(rzPrjreviewGuarList == null || rzPrjreviewGuarList.isEmpty()){
			return;
		}
		//查询评审单担保方子表
		List<RzPrjreviewGuarB> rzPrjreviewGuarBList = this.queryRzPrjreviewGuarBByCond(pkPrjreview);
		//Map<"担保方主表主键", List<子表>>
		Map<String, List<RzPrjreviewGuarB>> rzPrjreviewGuarBMap = new HashMap<String, List<RzPrjreviewGuarB>>();
		if(ToolUtils.isNotEmptyCollection(rzPrjreviewGuarBList)){
			for(RzPrjreviewGuarB rzPrjreviewGuarB : rzPrjreviewGuarBList){
				String pkPrjreviewGuar = rzPrjreviewGuarB.getPkPrjreviewGuar();
				if(rzPrjreviewGuarBMap.get(pkPrjreviewGuar) != null){
					List<RzPrjreviewGuarB> valueList = rzPrjreviewGuarBMap.get(pkPrjreviewGuar);
					valueList.add(rzPrjreviewGuarB);
				}else{
					List<RzPrjreviewGuarB> valueList = new ArrayList<RzPrjreviewGuarB>();
					valueList.add(rzPrjreviewGuarB);
					rzPrjreviewGuarBMap.put(pkPrjreviewGuar, valueList);
				}
			}
		}
		//合同担保方主表
		List<RzPrjcontrGuar> rzPrjcontrGuarList = new ArrayList<RzPrjcontrGuar>();
		//合同担保方子表
		List<RzPrjcontrGuarB> rzPrjcontrGuarBList = new ArrayList<RzPrjcontrGuarB>();
		for(RzPrjreviewGuar rzPrjreviewGuar : rzPrjreviewGuarList){
			RzPrjcontrGuar rzPrjcontrGuar = new RzPrjcontrGuar();
			try {
				BeanUtils.copyProperties(rzPrjcontrGuar, rzPrjreviewGuar);
				//合同主键
				rzPrjcontrGuar.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
				//合同担保方主表主键
				rzPrjcontrGuar.setPkPrjcontrGuar(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrGuarList.add(rzPrjcontrGuar);
			if(ToolUtils.isNotEmptyCollection(rzPrjreviewGuarBMap.get(rzPrjreviewGuar.getPkPrjreviewGuar()))){
				List<RzPrjreviewGuarB> valueList = rzPrjreviewGuarBMap.get(rzPrjreviewGuar.getPkPrjreview());
				for(RzPrjreviewGuarB rzPrjreviewGuarB : valueList){
					RzPrjcontrGuarB rzPrjcontrGuarB = new RzPrjcontrGuarB();
					try {
						BeanUtils.copyProperties(rzPrjcontrGuarB, rzPrjreviewGuarB);
						//合同主键
						rzPrjcontrGuarB.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
						//合同担保方主表主键
						rzPrjcontrGuarB.setPkPrjcontrGuar(rzPrjcontrGuar.getPkPrjcontrGuar());
						//合同担保方子表主键
						rzPrjcontrGuarB.setPkPrjcontrGuarB(primaryKeyIdWorker.getPrimaryKey());
					}catch (Exception ex){
						ex.printStackTrace();
						continue;
					}
					rzPrjcontrGuarBList.add(rzPrjcontrGuarB);
				}
			}
		}
		//插入主表
		if(rzPrjcontrGuarList.size() > 0){
			rzPrjcontrGuarDao.insertBatchRzPrjcontrGuar(rzPrjcontrGuarList);
		}
		//插入子表
		if(rzPrjcontrGuarBList.size() > 0){
			rzPrjcontrGuarBDao.insertBatchRzPrjcontrGuarB(rzPrjcontrGuarBList);
		}
	}

	/**
	 * 批量添加
	 * @param List<rzPrjcontr>
	 * @return
	 */
	public void insertBatchRzPrjcontr(List<RzPrjcontr> rzPrjcontrList){
		if(rzPrjcontrList != null){
			for(int i=0;i<rzPrjcontrList.size();i++){
				rzPrjcontrList.get(i).setTs(DateUtil.getTs());
				rzPrjcontrList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrList.get(i).setPkPrjcontr(pk);
			}
			rzPrjcontrDao.insertBatchRzPrjcontr(rzPrjcontrList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery){
		return rzPrjcontrDao.deleteRzPrjcontrById(rzPrjcontrQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrByCondition(RzPrjcontrQuery rzPrjcontrQuery){
		return rzPrjcontrDao.deleteRzPrjcontrByCondition(rzPrjcontrQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrByBatchId(RzPrjcontrQuery rzPrjcontrQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrDao.deleteRzPrjcontrByBatchId(data);	
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
	public boolean logicDeleteRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery){
		return rzPrjcontrDao.logicDeleteRzPrjcontrById(rzPrjcontrQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrByCondition(RzPrjcontrQuery rzPrjcontrQuery){
		return rzPrjcontrDao.logicDeleteRzPrjcontrByCondition(rzPrjcontrQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzPrjcontrByBatchId(RzPrjcontrQuery rzPrjcontrQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzPrjcontrDao.logicDeleteRzPrjcontrByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontr
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontr rzPrjcontr, String tenantId) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontr!=null){
				if(StringUtil.isNotEmpty(rzPrjcontr.getPkPrjcontr())){
					updateRzPrjcontrById(rzPrjcontr);
				}else{
					insertRzPrjcontr(rzPrjcontr, tenantId);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontr
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrById(RzPrjcontr rzPrjcontr){
		return rzPrjcontrDao.updateRzPrjcontrById(rzPrjcontr);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrByCondition(RzPrjcontrQuery record,RzPrjcontrQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrDao.updateRzPrjcontrByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrQuery
	 * @return
	 */
	public Result updateRzPrjcontrByBatchId(List<RzPrjcontr> rzPrjcontrList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrDao.updateRzPrjcontrByBatchId(rzPrjcontrList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public RzPrjcontr getRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery){
		return rzPrjcontrDao.getRzPrjcontrById(rzPrjcontrQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontr> getRzPrjcontrAll(RzPrjcontrQuery rzPrjcontrQuery){
		return rzPrjcontrDao.getRzPrjcontrAll(rzPrjcontrQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontr> getRzPrjcontrByPage(RzPrjcontrQuery rzPrjcontrQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrQuery!=null&&StringUtils.isBlank(rzPrjcontrQuery.getSort())){
			rzPrjcontrQuery.setSort("pk_prjcontr");
			rzPrjcontrQuery.setOrder("desc");;
		}
		int total = rzPrjcontrDao.getRzPrjcontrByPageCount(rzPrjcontrQuery);
		PaginatedList<RzPrjcontr> rzPrjcontrPageList = new MysqlPaginatedArrayList<RzPrjcontr>(rzPrjcontrQuery,total);
		List<RzPrjcontr> rzPrjcontrList = rzPrjcontrDao.getRzPrjcontrByPage(rzPrjcontrQuery);
		rzPrjcontrPageList.addAll(rzPrjcontrList);
		GridResult<RzPrjcontr> result = new GridResult<RzPrjcontr>(rzPrjcontrPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrByPageCount(RzPrjcontrQuery rzPrjcontrQuery){
		return rzPrjcontrDao.getRzPrjcontrByPageCount(rzPrjcontrQuery);
	}

	public void setRzPrjcontrDao(RzPrjcontrDao  rzPrjcontrDao){
		this.rzPrjcontrDao = rzPrjcontrDao;
	}

	public void setRzPricecalCfDao(RzPricecalCfDao rzPricecalCfDao) {
		this.rzPricecalCfDao = rzPricecalCfDao;
	}

	public void setRzPrjcontrCfDao(RzPrjcontrCfDao rzPrjcontrCfDao) {
		this.rzPrjcontrCfDao = rzPrjcontrCfDao;
	}

	public void setRzPrjreviewLesseeDao(RzPrjreviewLesseeDao rzPrjreviewLesseeDao) {
		this.rzPrjreviewLesseeDao = rzPrjreviewLesseeDao;
	}

	public void setRzPrjcontrLesseeDao(RzPrjcontrLesseeDao rzPrjcontrLesseeDao) {
		this.rzPrjcontrLesseeDao = rzPrjcontrLesseeDao;
	}

	public void setRzPrjreviewSupplierDao(RzPrjreviewSupplierDao rzPrjreviewSupplierDao) {
		this.rzPrjreviewSupplierDao = rzPrjreviewSupplierDao;
	}

	public void setRzPrjcontrSupplierDao(RzPrjcontrSupplierDao rzPrjcontrSupplierDao) {
		this.rzPrjcontrSupplierDao = rzPrjcontrSupplierDao;
	}

	public void setRzPrjcontrEqptDao(RzPrjcontrEqptDao rzPrjcontrEqptDao) {
		this.rzPrjcontrEqptDao = rzPrjcontrEqptDao;
	}

	public void setRzPrjreviewEqptDao(RzPrjreviewEqptDao rzPrjreviewEqptDao) {
		this.rzPrjreviewEqptDao = rzPrjreviewEqptDao;
	}

	public void setRzPrjcontrGuarBDao(RzPrjcontrGuarBDao rzPrjcontrGuarBDao) {
		this.rzPrjcontrGuarBDao = rzPrjcontrGuarBDao;
	}

	public void setRzPrjreviewGuarDao(RzPrjreviewGuarDao rzPrjreviewGuarDao) {
		this.rzPrjreviewGuarDao = rzPrjreviewGuarDao;
	}

	public void setRzPrjreviewGuarBDao(RzPrjreviewGuarBDao rzPrjreviewGuarBDao) {
		this.rzPrjreviewGuarBDao = rzPrjreviewGuarBDao;
	}

	public void setRzPrjcontrGuarDao(RzPrjcontrGuarDao rzPrjcontrGuarDao) {
		this.rzPrjcontrGuarDao = rzPrjcontrGuarDao;
	}

	public void setRzPrjapplyDao(RzPrjapplyDao rzPrjapplyDao) {
		this.rzPrjapplyDao = rzPrjapplyDao;
	}

	public void setRzPricecalLeaseDao(RzPricecalLeaseDao rzPricecalLeaseDao) {
		this.rzPricecalLeaseDao = rzPricecalLeaseDao;
	}

	public void setRzPrjcontrLeaseDao(RzPrjcontrLeaseDao rzPrjcontrLeaseDao) {
		this.rzPrjcontrLeaseDao = rzPrjcontrLeaseDao;
	}
}