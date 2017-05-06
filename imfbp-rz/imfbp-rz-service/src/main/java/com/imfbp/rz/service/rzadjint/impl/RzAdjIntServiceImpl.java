package com.imfbp.rz.service.rzadjint.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzadjint.RzAdjIntDao;
import com.imfbp.rz.dao.rzadjintlease.RzAdjIntLeaseDao;
import com.imfbp.rz.dao.rzadjintleasenew.RzAdjIntLeaseNewDao;
import com.imfbp.rz.dao.rzpmtplan.RzPmtPlanDao;
import com.imfbp.rz.dao.rzpmtplanlease.RzPmtPlanLeaseDao;
import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjint.query.RzAdjIntQuery;
import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzadjintlease.query.RzAdjIntLeaseQuery;
import com.imfbp.rz.domain.rzadjintleasenew.RzAdjIntLeaseNew;
import com.imfbp.rz.domain.rzadjintleasenew.query.RzAdjIntLeaseNewQuery;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.method.AdjRateCalService;
import com.imfbp.rz.service.rzadjint.RzAdjIntService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.exception.BusinessException;

@Component("rzAdjIntService")
public class RzAdjIntServiceImpl implements RzAdjIntService {

	private RzAdjIntDao rzAdjIntDao;
	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	@Autowired
	private RzAdjIntLeaseDao rzAdjIntLeaseDao;

	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;
	@Autowired
	private BillnoService billnoService;
	@Autowired
	private RzPmtPlanLeaseDao rzPmtPlanLeaseDao;
	@Autowired
	private RzAdjIntLeaseNewDao rzAdjIntLeaseNewDao;
	@Autowired
	private RzPmtPlanDao rzPmtPlanDao;
	@Autowired
	private AdjRateCalService adjRateCalService;

	/**
	 * 添加
	 * 
	 * @param rzAdjInt
	 * @return
	 */
	@Override
	public void insertRzAdjInt(RzAdjInt rzAdjInt) {
		String pk = primaryKeyUtil.getPrimaryKey();
		rzAdjInt.setTs(DateUtil.getTs());
		rzAdjInt.setDr(0);
		rzAdjInt.setPkAdjInt(pk);
		try {
			String billno = billnoService.getBillno(INodeConsts.RZ_RZADJINT_NO);
			rzAdjInt.setAdjIntNo(billno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rzAdjIntDao.insertRzAdjInt(rzAdjInt);
		insertBatchAdjIntLease(rzAdjInt);
	}

	/**
	 * 批量添加
	 * 
	 * @param List<rzAdjInt>
	 * @return
	 */
	public void insertBatchRzAdjInt(List<RzAdjInt> rzAdjIntList) {
		if (rzAdjIntList != null) {
			for (int i = 0; i < rzAdjIntList.size(); i++) {
				rzAdjIntList.get(i).setTs(DateUtil.getTs());
				rzAdjIntList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzAdjIntList.get(i).setPkPrjcontr(pk);
			}
			rzAdjIntDao.insertBatchRzAdjInt(rzAdjIntList);
		}
	}

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery) {
		return rzAdjIntDao.deleteRzAdjIntById(rzAdjIntQuery);
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery) {
		return rzAdjIntDao.deleteRzAdjIntByCondition(rzAdjIntQuery);
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public Result deleteRzAdjIntByBatchId(RzAdjIntQuery rzAdjIntQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = rzAdjIntQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			boolean flat = rzAdjIntDao.deleteRzAdjIntByBatchId(data);
			//删除子表
			rzAdjIntLeaseDao.deleteRzAdjIntLeaseByBatchId(data);
			rzAdjIntLeaseNewDao.deleteRzAdjIntLeaseNewByBatchId(data);
			result.setSuccess(flat);
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
	public boolean logicDeleteRzAdjIntById(RzAdjIntQuery rzAdjIntQuery) {
		return rzAdjIntDao.logicDeleteRzAdjIntById(rzAdjIntQuery);
	}

	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAdjIntByCondition(RzAdjIntQuery rzAdjIntQuery) {
		return rzAdjIntDao.logicDeleteRzAdjIntByCondition(rzAdjIntQuery);
	}

	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public Result logicDeleteRzAdjIntByBatchId(RzAdjIntQuery rzAdjIntQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = rzAdjIntQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			data.put("batchId2", batchIdArr);
			boolean flat = rzAdjIntDao.logicDeleteRzAdjIntByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加或修改
	 * 
	 * @param rzAdjInt
	 * @return
	 */
	@Override
	public Result insertOrUpdate(RzAdjInt rzAdjInt) {
		// 设置调用失败
		Result result = new Result(false);
		try {
			if (rzAdjInt != null) {
				if (StringUtil.isNotEmpty(rzAdjInt.getPkAdjInt())) {
					updateRzAdjIntById(rzAdjInt);
					//删除子表
					RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery = new RzAdjIntLeaseNewQuery();
					rzAdjIntLeaseNewQuery.setPkAdjInt(rzAdjInt.getPkAdjInt());
					rzAdjIntLeaseNewDao.deleteRzAdjIntLeaseNewByCondition(rzAdjIntLeaseNewQuery);
					RzAdjIntLeaseQuery rzAdjIntLeaseQuery = new RzAdjIntLeaseQuery();
					rzAdjIntLeaseQuery.setPkAdjInt(rzAdjInt.getPkAdjInt());
					rzAdjIntLeaseDao.deleteRzAdjIntLeaseByCondition(rzAdjIntLeaseQuery);
					//批量插入子表
					insertBatchAdjIntLease(rzAdjInt);
				} else {
					insertRzAdjInt(rzAdjInt);
					// 保存子表信息
				}
				// 如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			// 设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzAdjInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void insertBatchAdjIntLease(RzAdjInt rzAdjInt) {
		RzPmtPlanQuery rzPmtPlan = new RzPmtPlanQuery();
		rzPmtPlan.setPkPrjcontr(rzAdjInt.getPkPrjcontr());
		//rzAdjInt.setAdjLeaseRate(3.0);
		List<RzPmtPlan> rzPmtPlanList = rzPmtPlanDao.getRzPmtPlanAll(rzPmtPlan);
		List<RzAdjIntLease> leaseList = new ArrayList<RzAdjIntLease>();
		if (ToolUtils.isNotEmptyCollection(rzPmtPlanList)) {
			// 某个合同的调息计划表
			RzPmtPlanLeaseQuery query = new RzPmtPlanLeaseQuery();
			// query.setIsCheck("N");
			query.setPkPmtPlan(rzPmtPlanList.get(0).getPkPmtPlan());
			List<RzPmtPlanLease> list = rzPmtPlanLeaseDao.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(query);
			for (RzPmtPlanLease lease : list) {
				RzAdjIntLease adjIntLease = new RzAdjIntLease();
				try {
					BeanUtils.copyProperties(adjIntLease, lease);
					adjIntLease.setPkAdjIntLease(primaryKeyIdWorker.getPrimaryKey());
					adjIntLease.setPkAdjInt(rzAdjInt.getPkAdjInt());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				leaseList.add(adjIntLease);
			}

			List<RzAdjIntLeaseNew> adjIntLeaseNewList = new ArrayList<RzAdjIntLeaseNew>();
			try {
				List<RzPricecalLease> leaseListNew = adjRateCalService.calLease(rzAdjInt, leaseList);
				for (RzPricecalLease leasenew : leaseListNew) {
					RzAdjIntLeaseNew adjIntLeaseNew = new RzAdjIntLeaseNew();
					adjIntLeaseNew.setPkAdjInt(rzAdjInt.getPkAdjInt());
					adjIntLeaseNew.setPkAdjIntLeaseNew(primaryKeyIdWorker.getPrimaryKey());
					try {
						BeanUtils.copyProperties(adjIntLeaseNew, leasenew);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					adjIntLeaseNewList.add(adjIntLeaseNew);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if (ToolUtils.isNotEmptyCollection(leaseList)) {
				rzAdjIntLeaseDao.insertBatchRzAdjIntLease(leaseList);
			}
			if (ToolUtils.isNotEmptyCollection(adjIntLeaseNewList)) {
				rzAdjIntLeaseNewDao.insertBatchRzAdjIntLeaseNew(adjIntLeaseNewList);
			}
		}
	}

	/**
	 * 根据Id修改
	 * 
	 * @param rzAdjInt
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntById(RzAdjInt rzAdjInt) {
		return rzAdjIntDao.updateRzAdjIntById(rzAdjInt);
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntByCondition(RzAdjIntQuery record, RzAdjIntQuery parameter) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record", record);
		data.put("parameter", parameter);
		return rzAdjIntDao.updateRzAdjIntByCondition(data);
	}

	/**
	 * 根据Id批量修改
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	public Result updateRzAdjIntByBatchId(List<RzAdjInt> rzAdjIntList) {
		Result result = new Result(false);
		try {
			boolean flag = rzAdjIntDao.updateRzAdjIntByBatchId(rzAdjIntList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据id查询
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public RzAdjInt getRzAdjIntById(RzAdjIntQuery rzAdjIntQuery) {
		return rzAdjIntDao.getRzAdjIntById(rzAdjIntQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public List<RzAdjInt> getRzAdjIntAll(RzAdjIntQuery rzAdjIntQuery) {
		return rzAdjIntDao.getRzAdjIntAll(rzAdjIntQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public GridResult<RzAdjInt> getRzAdjIntByPage(RzAdjIntQuery rzAdjIntQuery) {
		// 如果排序的字段是空或者空字符串
		if (rzAdjIntQuery != null && StringUtils.isBlank(rzAdjIntQuery.getSort())) {
			rzAdjIntQuery.setSort("pk_prjcontr");
			rzAdjIntQuery.setOrder("desc");
			;
		}
		int total = rzAdjIntDao.getRzAdjIntByPageCount(rzAdjIntQuery);
		PaginatedList<RzAdjInt> rzAdjIntPageList = new MysqlPaginatedArrayList<RzAdjInt>(rzAdjIntQuery, total);
		List<RzAdjInt> rzAdjIntList = rzAdjIntDao.getRzAdjIntByPage(rzAdjIntQuery);
		rzAdjIntPageList.addAll(rzAdjIntList);
		GridResult<RzAdjInt> result = new GridResult<RzAdjInt>(rzAdjIntPageList);
		return result;
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param rzAdjIntQuery
	 * @return
	 */
	@Override
	public int getRzAdjIntByPageCount(RzAdjIntQuery rzAdjIntQuery) {
		return rzAdjIntDao.getRzAdjIntByPageCount(rzAdjIntQuery);
	}

	public void setRzAdjIntDao(RzAdjIntDao rzAdjIntDao) {
		this.rzAdjIntDao = rzAdjIntDao;
	}

}