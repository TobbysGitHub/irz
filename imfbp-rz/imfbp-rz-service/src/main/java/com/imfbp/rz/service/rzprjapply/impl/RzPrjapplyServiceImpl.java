package com.imfbp.rz.service.rzprjapply.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ifbp.boss.rpc.smallorg.domain.SmallOrg;
import com.imfbp.brefdata.rpc.reference.domain.RpcDataQuery;
import com.imfbp.brefdata.rpc.reference.service.ReferenceRpcService;
import com.imfbp.rz.dao.rzeqpt.RzEqptDao;
import com.imfbp.rz.dao.rzpricecaleqpt.RzPricecalEqptDao;
import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import com.imfbp.rz.dao.rzprjapplyeqpt.RzPrjapplyEqptDao;
import com.imfbp.rz.dao.rzprjreview.RzPrjreviewDao;
import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;
import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;
import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.commons.OrgUtil;
import com.imfbp.rz.service.rzprjapply.RzPrjapplyService;
import com.imfbp.rz.service.rzrate.RzRateService;
import com.imfbp.rz.service.rzrateb.RzRateBService;
import com.imfbp.rz.service.rzrateprd.RzRateprdService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.login.enums.LoginEnum;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import net.sf.composite.util.ObjectUtils;
import net.sf.json.JSONObject;

@Component("rzPrjapplyService")
public class RzPrjapplyServiceImpl implements RzPrjapplyService {

	private RzPrjapplyDao rzPrjapplyDao;
	@Autowired
	private RzPrjreviewDao rzPrjreviewDao;

	@Autowired
	private RzRateprdService rzRateprdService;
	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	@Autowired
	private BillnoService billnoService;
	@Autowired
	private ReferenceRpcService referenceRpcService;
	@Autowired
	private RzPricecalEqptDao rzPricecalEqptDao;
	@Autowired
	private RzPrjapplyEqptDao rzPrjapplyEqptDao;
	@Autowired
	private OrgUtil orgUtil;

	@Autowired
	private RzRateBService rzRateBService;

	@Autowired
	private RzRateService rzRateService;

	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;
	@Autowired
	private RzEqptDao rzEqptDao;

	/**
	 * 添加
	 * 
	 * @param rzPrjapply
	 * @return
	 */
	@Override
	public void insertRzPrjapply(RzPrjapply rzPrjapply, String tenantId) {
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjapply.setTs(DateUtil.getTs());
		rzPrjapply.setDr(0);
		rzPrjapply.setPkPrjapply(pk);

		if (StringUtil.isEmpty(rzPrjapply.getPkPricecal()) && rzPrjapply.getRatetype() == 0) {
			RzPricecalQuery rzPricecalQuery = new RzPricecalQuery();
			rzPricecalQuery.setLaunchdate(rzPrjapply.getLaunchdate());
			rzPricecalQuery.setLeaseprd(rzPrjapply.getLeaseprd());
			Double result = 0.0;
			try {
				result = getRateByLauchdateAndPrd(rzPricecalQuery);
			} catch (Exception e) {
			}
			rzPrjapply.setBaserate(result);
			rzPrjapply.setPricerate(result);
		}
		if (rzPrjapply.getFloatpct() == null) {
			rzPrjapply.setFloatpct(0.0);
		}

		if (StringUtil.isEmpty(rzPrjapply.getPkOrgLessor())) {
			try {
				ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
						.getRequestAttributes();
				HttpSession session = requestAttributes.getRequest().getSession();
				Object orgId = session.getAttribute(LoginEnum.LONGIN_ORG_ID.getloginInfo());
				SmallOrg org = orgUtil.getTopSmallOrgById(tenantId, (String) orgId);
				if (null != org) {
					rzPrjapply.setPkOrgLessor(org.getId());
				}
			} catch (Exception e) {
				// 没有查询组织机构报错
				rzPrjapply.setPkOrgLessor("0");
			}
		}

		try {
			String billno = billnoService.getBillno(INodeConsts.RZ_PRJAPPLY_NO);
			rzPrjapply.setPrjCode(billno);
			if (StringUtil.isEmpty(rzPrjapply.getPrjName())) {
				String pkCustomer = rzPrjapply.getPkCustomer();
				RpcDataQuery query = new RpcDataQuery();
				JSONObject json = new JSONObject();
				List<JSONObject> conditions = new ArrayList<JSONObject>();
				json.element("_id", JSONObject.fromObject(
						"{\"keyword\" : \"" + RpcDataQuery.KeyWord.EQ + "\",value:\"" + pkCustomer + "\"}"));
				conditions.add(json);
				query.setConditions(conditions);
				List<JSONObject> dataList = referenceRpcService.getDataByReferences(tenantId, "CRM_CONSUMERCLIENT",
						query);
				// TODO 参照翻译
				String customerName = "";
				if (ToolUtils.isNotEmptyCollection(dataList)) {
					customerName = dataList.get(0).getString("userName");
				}
				RzPrjapplyQuery rzPrjapplyQuery = new RzPrjapplyQuery();
				rzPrjapplyQuery.setPkCustomer(pkCustomer);
				int count = rzPrjapplyDao.getRzPrjapplyByPageCount(rzPrjapplyQuery) + 1;
				String floatNum = String.format("%03d", count);
				rzPrjapply.setPrjName(billno + customerName + Integer.toString(count) + floatNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Double total = 0.0;
		RzPricecalEqptQuery eqptQuery = new RzPricecalEqptQuery();
		// 录入设备：拷贝设备
		if (StringUtil.isNotEmpty(rzPrjapply.getPkPricecal())) {
			eqptQuery.setPkPricecal(rzPrjapply.getPkPricecal());
			List<RzPricecalEqpt> list = rzPricecalEqptDao.getRzPricecalEqptAll(eqptQuery);
			if (ToolUtils.isNotEmptyCollection(list)) {
				List<RzPrjapplyEqpt> rzPrjapplyEqptList = new ArrayList<RzPrjapplyEqpt>();
				for (RzPricecalEqpt eqpt : list) {
					RzPrjapplyEqpt applyEqpt = new RzPrjapplyEqpt();
					applyEqpt.setPkPrjapplyEqpt(primaryKeyIdWorker.getPrimaryKey());
					RzEqptQuery rzEqptQuery = new RzEqptQuery();
					rzEqptQuery.setPkEqpt(eqpt.getPkEqpt());
					RzEqpt rzEqpt = rzEqptDao.getRzEqptById(rzEqptQuery);
					try {
						BeanUtils.copyProperties(applyEqpt, rzEqpt);
						applyEqpt.setEqptNum(eqpt.getNum());
						applyEqpt.setPkPrjapply(pk);
						applyEqpt.setEqptPriceTotal(eqpt.getPrice());
						applyEqpt.setNetVal(eqpt.getPrice());
						applyEqpt.setAssessPrice(eqpt.getPrice());
						total += eqpt.getPrice();
						applyEqpt.setDeliveryDate(DateUtil.getCurDateStr());
						rzPrjapplyEqptList.add(applyEqpt);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				rzPrjapplyEqptDao.insertBatchRzPrjapplyEqpt(rzPrjapplyEqptList);
			}
		}
		total = total > 0.0 ? total : rzPrjapply.getItemamt();
		if (StringUtil.isEmpty(rzPrjapply.getEqptPriceTotal())) {
			rzPrjapply.setEqptPriceTotal(total);
		}
		if (StringUtil.isEmpty(rzPrjapply.getPlanrentdate())) {
			if (StringUtil.isNotEmpty(rzPrjapply.getLaunchdate()))
				rzPrjapply.setPlanrentdate(rzPrjapply.getLaunchdate().split("-")[2]);
		}
		if (StringUtil.isEmpty(rzPrjapply.getFinanceamt())) {
			rzPrjapply.setFinanceamt(total - DoubleUtils.getDoubleNullAsZero(rzPrjapply.getFirstpmtamt()));
		}
		rzPrjapplyDao.insertRzPrjapply(rzPrjapply);
	}

	/**
	 * 批量添加
	 * 
	 * @param List<rzPrjapply>
	 * @return
	 */
	public void insertBatchRzPrjapply(List<RzPrjapply> rzPrjapplyList) {
		if (rzPrjapplyList != null) {
			for (int i = 0; i < rzPrjapplyList.size(); i++) {
				rzPrjapplyList.get(i).setTs(DateUtil.getTs());
				rzPrjapplyList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjapplyList.get(i).setPkPrjapply(pk);
			}
			rzPrjapplyDao.insertBatchRzPrjapply(rzPrjapplyList);
		}
	}

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery) {
		return rzPrjapplyDao.deleteRzPrjapplyById(rzPrjapplyQuery);
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery) {
		return rzPrjapplyDao.deleteRzPrjapplyByCondition(rzPrjapplyQuery);
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public Result deleteRzPrjapplyByBatchId(RzPrjapplyQuery rzPrjapplyQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = rzPrjapplyQuery.getBatchId().split(",");
			List<String> batchIdList = new ArrayList<String>();
			Collections.addAll(batchIdList, batchIdArr);
			data.put("batchId1", batchIdArr);
			// 删除校验
			List<String> list = rzPrjreviewDao.getRzPrjreviewByFkId(data);
			if (!list.isEmpty()) {
				List<RzPrjapply> applyList = rzPrjapplyDao.getRzPrjapplyBatchId(list);
				result.addDefaultModel("errorList", applyList);
				result.setSuccess(false);
				batchIdList.removeAll(list);
			}
			data.put("batchId1", batchIdList);
			if (ToolUtils.isNotEmptyCollection(batchIdList)) {
				boolean flat = rzPrjapplyDao.deleteRzPrjapplyByBatchId(data);
				result.setSuccess(flat);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery) {
		return rzPrjapplyDao.logicDeleteRzPrjapplyById(rzPrjapplyQuery);
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjapplyByCondition(RzPrjapplyQuery rzPrjapplyQuery) {
		return rzPrjapplyDao.logicDeleteRzPrjapplyByCondition(rzPrjapplyQuery);
	}

	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public Result logicDeleteRzPrjapplyByBatchId(RzPrjapplyQuery rzPrjapplyQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = rzPrjapplyQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			data.put("batchId2", batchIdArr);
			boolean flat = rzPrjapplyDao.logicDeleteRzPrjapplyByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加或修改
	 * 
	 * @param rzPrjapply
	 * @return
	 */
	@Override
	public Result insertOrUpdate(RzPrjapply rzPrjapply, String tenantId) {
		// 设置调用失败
		Result result = new Result(false);
		try {
			if (rzPrjapply != null) {
				// 如果没有从报价单带入设备：报价单不存在：从立项录入未保存设备。需要对设备删除，保证数据一致
				insertOrUpdateRzPrjapplyEqpt(rzPrjapply);
				rzPrjapply.setDef1(null);
				rzPrjapply.setDef2(null);
				if (StringUtil.isNotEmpty(rzPrjapply.getPkPrjapply())) {
					updateRzPrjapplyById(rzPrjapply);
				} else {
					insertRzPrjapply(rzPrjapply, tenantId);
				}
				// 如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			// 设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjapply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void insertOrUpdateRzPrjapplyEqpt(RzPrjapply rzPrjapply) {
		//没有值，为不同更改：不涉及变化
		if (StringUtil.isNotEmpty(rzPrjapply.getDef1())) {
			// 删除存在设备
			RzPrjapplyEqptQuery rzPrjapplyEqptQuery = new RzPrjapplyEqptQuery();
			rzPrjapplyEqptQuery.setPkPrjapply(rzPrjapply.getPkPrjapply());
			// 查询已经存在的不做更改
			rzPrjapplyEqptQuery.setPkPrjapply(rzPrjapply.getPkPrjapply());
			List<RzPrjapplyEqpt> listInsys = rzPrjapplyEqptDao.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
			Map<String, RzPrjapplyEqpt> mapInSys = new HashMap<String, RzPrjapplyEqpt>();
			if (ToolUtils.isNotEmptyCollection(listInsys)) {
				for (RzPrjapplyEqpt e : listInsys)
					mapInSys.put(e.getPkEqpt(), e);
			}
			rzPrjapplyEqptDao.deleteRzPrjapplyEqptByCondition(rzPrjapplyEqptQuery);
			// 入库设备：更改相关值
			List<RzPrjapplyEqpt> list = new ArrayList<RzPrjapplyEqpt>();
			Map<String, RzEqpt> map = getRzEqptMap();
			String[] pks = rzPrjapply.getDef1().split(",");
			String nums[] = rzPrjapply.getDef2().split(",");
			for (int i = 0; i < pks.length; i++) {
				if (map.containsKey(pks[i])) {
					// 已经录入设备不做更改
					// if(mapInSys.containsKey(pks[i]) &&
					// String.valueOf(mapInSys.get(pks[i]).getEqptNum()).equals(nums[i])){
					// continue;
					// }
					RzEqpt eqpt = map.get(pks[i]);
					RzPrjapplyEqpt eq = new RzPrjapplyEqpt();
					eq.setEqptNum(Integer.valueOf(nums[i]));
					eq.setPkPrjapply(rzPrjapply.getPkPrjapply());
					try {
						BeanUtils.copyProperties(eq, eqpt);
					} catch (Exception e) {
						// TODO 数据拷贝异常
					}
					eq.setPkPrjapplyEqpt(primaryKeyIdWorker.getPrimaryKey());
					eq.setEqptPriceTotal(
							Integer.valueOf(nums[i]) * DoubleUtils.getDoubleNullAsZero(eqpt.getAssessPrice()));
					eq.setDeliveryDate(DateUtil.getCurDateStr());
					eq.setNetVal(Integer.valueOf(nums[i]) * DoubleUtils.getDoubleNullAsZero(eqpt.getAssessPrice()));
					list.add(eq);
				}
			}
			rzPrjapplyEqptDao.insertBatchRzPrjapplyEqpt(list);
		}

	}

	/**
	 * @Title: getRzEqptMap @Description: 缓存融资设备数据 @param @return @return Map
	 *         <String,RzEqpt> @user qinhuimin @date
	 *         2017年1月16日上午10:31:32 @throws
	 */
	private Map<String, RzEqpt> getRzEqptMap() {
		List<RzEqpt> list = rzEqptDao.getRzEqptAll(new RzEqptQuery());
		Map<String, RzEqpt> map = new HashMap<String, RzEqpt>();
		for (RzEqpt eqpt : list) {
			map.put(eqpt.getPkEqpt(), eqpt);
		}
		return map;
	}

	/**
	 * 根据Id修改
	 * 
	 * @param rzPrjapply
	 * @return
	 */
	@Override
	public boolean updateRzPrjapplyById(RzPrjapply rzPrjapply) {
		return rzPrjapplyDao.updateRzPrjapplyById(rzPrjapply);
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjapplyByCondition(RzPrjapplyQuery record, RzPrjapplyQuery parameter) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record", record);
		data.put("parameter", parameter);
		return rzPrjapplyDao.updateRzPrjapplyByCondition(data);
	}

	/**
	 * 根据id查询
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public RzPrjapply getRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery) {
		return rzPrjapplyDao.getRzPrjapplyById(rzPrjapplyQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public List<RzPrjapply> getRzPrjapplyAll(RzPrjapplyQuery rzPrjapplyQuery) {
		return rzPrjapplyDao.getRzPrjapplyAll(rzPrjapplyQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjapply> getRzPrjapplyByPage(RzPrjapplyQuery rzPrjapplyQuery) {
		// 如果排序的字段是空或者空字符串
		if (rzPrjapplyQuery != null && StringUtils.isBlank(rzPrjapplyQuery.getSort())) {
			rzPrjapplyQuery.setSort("pk_prjapply");
			rzPrjapplyQuery.setOrder("desc");
			;
		}
		int total = rzPrjapplyDao.getRzPrjapplyByPageCount(rzPrjapplyQuery);
		PaginatedList<RzPrjapply> rzPrjapplyPageList = new MysqlPaginatedArrayList<RzPrjapply>(rzPrjapplyQuery, total);
		List<RzPrjapply> rzPrjapplyList = rzPrjapplyDao.getRzPrjapplyByPage(rzPrjapplyQuery);
		rzPrjapplyPageList.addAll(rzPrjapplyList);
		GridResult<RzPrjapply> result = new GridResult<RzPrjapply>(rzPrjapplyPageList);
		return result;
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param rzPrjapplyQuery
	 * @return
	 */

	private Double getRateByLauchdateAndPrd(RzPricecalQuery rzPricecalQuery) {
		if (rzPricecalQuery == null)
			return null;
		Double result = 0.0000;
		List<RzRateprd> rzRateprdList = null;
		List<RzRateB> rzRateBList = null;
		List<RzRate> rzRateList = null;
		RzRateQuery query = new RzRateQuery();
		query.setApprovestatus(3);
		rzRateprdList = rzRateprdService.getRzRateprdAll(new RzRateprdQuery());
		rzRateBList = rzRateBService.getRzRateBAll(new RzRateBQuery());
		rzRateList = rzRateService.getRzRateAll(query);
		String lauchdate = rzPricecalQuery.getLaunchdate();
		int prd = rzPricecalQuery.getLeaseprd() == null ? 0 : rzPricecalQuery.getLeaseprd() * 30;
		String pkPrd = null, pkRate = null;
		if (ToolUtils.isNotEmptyCollection(rzRateprdList)) {
			for (RzRateprd prdtemp : rzRateprdList) {
				if (prdtemp != null) {
					int start = prdtemp.getStartTerm();
					int end = prdtemp.getEndTerm();
					if (prd >= start && prd <= end) {
						pkPrd = prdtemp.getPkRateprd();
						break;
					}
				}
			}
		}
		if (!ObjectUtils.isEmpty(rzRateList)) {
			for (RzRate rate : rzRateList) {
				if (rate != null) {
					String start = rate.getStartdate();
					String end = rate.getEnddate();
					if (StringUtils.isNotEmpty(end)
							&& (lauchdate.compareTo(start) > 0 || lauchdate.compareTo(start) == 0)
							&& (lauchdate.compareTo(end) < 0 || lauchdate.compareTo(end) == 0)) {
						pkRate = rate.getPkRate();
						break;
					} else if (StringUtils.isEmpty(end)) {
						if (lauchdate.compareTo(start) > 0 || lauchdate.compareTo(start) == 0) {
							if (rate.getApprovestatus() == 3) {
								pkRate = rate.getPkRate();
								break;
							}
						}
					}
				}
			}
		}
		if (!ObjectUtils.isEmpty(rzRateBList)) {
			for (RzRateB rateb : rzRateBList) {
				if (rateb != null) {
					if (StringUtils.isNotEmpty(rateb.getPkRateB()) && pkPrd != null && pkRate != null
							&& pkPrd.equals(rateb.getPkRateprd()) && pkRate.equals(rateb.getPkRate())) {
						result = rateb.getRate();
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public int getRzPrjapplyByPageCount(RzPrjapplyQuery rzPrjapplyQuery) {
		return rzPrjapplyDao.getRzPrjapplyByPageCount(rzPrjapplyQuery);
	}

	public void setRzPrjapplyDao(RzPrjapplyDao rzPrjapplyDao) {
		this.rzPrjapplyDao = rzPrjapplyDao;
	}

}