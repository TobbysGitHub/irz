package com.imfbp.rz.service.rzprjreviewlessee.impl;

import java.util.List;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

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

import net.sf.json.JSONObject;

import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewlessee.query.RzPrjreviewLesseeQuery;
import com.imfbp.brefdata.rpc.reference.domain.RpcDataQuery;
import com.imfbp.brefdata.rpc.reference.service.ReferenceRpcService;
import com.imfbp.rz.dao.rzprjreviewlessee.RzPrjreviewLesseeDao;
import com.imfbp.rz.service.rzprjreviewlessee.RzPrjreviewLesseeService;
import com.imfbp.rz.util.ToolUtils;

@Component("rzPrjreviewLesseeService")
public class RzPrjreviewLesseeServiceImpl implements RzPrjreviewLesseeService {

	private RzPrjreviewLesseeDao rzPrjreviewLesseeDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	@Autowired
	private ReferenceRpcService referenceRpcService;

	/**
	 * 添加
	 * 
	 * @param rzPrjreviewLessee
	 * @return
	 */
	@Override
	public void insertRzPrjreviewLessee(RzPrjreviewLessee rzPrjreviewLessee) {
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjreviewLessee.setPkPrjreviewLessee(pk);
		rzPrjreviewLesseeDao.insertRzPrjreviewLessee(rzPrjreviewLessee);
	}

	/**
	 * 批量添加
	 * 
	 * @param List<rzPrjreviewLessee>
	 * @return
	 */
	public void insertBatchRzPrjreviewLessee(List<RzPrjreviewLessee> rzPrjreviewLesseeList) {
		if (rzPrjreviewLesseeList != null) {
			for (int i = 0; i < rzPrjreviewLesseeList.size(); i++) {
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjreviewLesseeList.get(i).setPkPrjreviewLessee(pk);
			}
			rzPrjreviewLesseeDao.insertBatchRzPrjreviewLessee(rzPrjreviewLesseeList);
		}
	}

	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewLesseeById(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery) {
		return rzPrjreviewLesseeDao.deleteRzPrjreviewLesseeById(rzPrjreviewLesseeQuery);
	}

	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * 
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewLesseeByCondition(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery) {
		return rzPrjreviewLesseeDao.deleteRzPrjreviewLesseeByCondition(rzPrjreviewLesseeQuery);
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public Result deleteRzPrjreviewLesseeByBatchId(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			// TODO 如果是多主键修要修改，如果不是删除就可以
			String[] batchIdArr = rzPrjreviewLesseeQuery.getBatchId().split(",");
			data.put("batchId1", batchIdArr);
			boolean flat = rzPrjreviewLesseeDao.deleteRzPrjreviewLesseeByBatchId(data);
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加或修改
	 * 
	 * @param rzPrjreviewLessee
	 * @return
	 */
	@Override
	public Result insertOrUpdate(RzPrjreviewLessee rzPrjreviewLessee) {
		// 设置调用失败
		Result result = new Result(false);
		try {
			if (rzPrjreviewLessee != null) {
				if (StringUtil.isNotEmpty(rzPrjreviewLessee.getPkPrjreviewLessee())) {
					updateRzPrjreviewLesseeById(rzPrjreviewLessee);
				} else {
					insertRzPrjreviewLessee(rzPrjreviewLessee);
				}
				// 如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			// 设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjreviewLessee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据Id修改
	 * 
	 * @param rzPrjreviewLessee
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewLesseeById(RzPrjreviewLessee rzPrjreviewLessee) {
		return rzPrjreviewLesseeDao.updateRzPrjreviewLesseeById(rzPrjreviewLessee);
	}

	/**
	 * 根据条件修改
	 * 
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewLesseeByCondition(RzPrjreviewLesseeQuery record, RzPrjreviewLesseeQuery parameter) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record", record);
		data.put("parameter", parameter);
		return rzPrjreviewLesseeDao.updateRzPrjreviewLesseeByCondition(data);
	}

	/**
	 * 根据id查询
	 * 
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public RzPrjreviewLessee getRzPrjreviewLesseeById(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery) {
		return rzPrjreviewLesseeDao.getRzPrjreviewLesseeById(rzPrjreviewLesseeQuery);
	}

	/**
	 * 查询所有
	 * 
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewLessee> getRzPrjreviewLesseeAll(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery) {
		return rzPrjreviewLesseeDao.getRzPrjreviewLesseeAll(rzPrjreviewLesseeQuery);
	}

	/**
	 * 分页查询
	 * 
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjreviewLessee> getRzPrjreviewLesseeByPage(String tenantId,
			RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery) {
		// 如果排序的字段是空或者空字符串
		if (rzPrjreviewLesseeQuery != null && StringUtils.isBlank(rzPrjreviewLesseeQuery.getSort())) {
			rzPrjreviewLesseeQuery.setSort("pk_prjreview_lessee");
			rzPrjreviewLesseeQuery.setOrder("desc");
			;
		}
		int total = rzPrjreviewLesseeDao.getRzPrjreviewLesseeByPageCount(rzPrjreviewLesseeQuery);
		PaginatedList<RzPrjreviewLessee> rzPrjreviewLesseePageList = new MysqlPaginatedArrayList<RzPrjreviewLessee>(
				rzPrjreviewLesseeQuery, total);
		List<RzPrjreviewLessee> rzPrjreviewLesseeList = rzPrjreviewLesseeDao
				.getRzPrjreviewLesseeByPage(rzPrjreviewLesseeQuery);
		List<RzPrjreviewLessee> transRzPrjreviewLesseeList = new ArrayList<RzPrjreviewLessee>();
		if (ToolUtils.isNotEmptyCollection(rzPrjreviewLesseeList)) {
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<rzPrjreviewLesseeList.size(); i++){
				sb.append(rzPrjreviewLesseeList.get(i).getPkCustomer());
				if (i !=  rzPrjreviewLesseeList.size()- 1) {
					sb.append(",");
				}
			}
			List<JSONObject> jsonList = referenceRpcService.getDataByReferences(tenantId, "CRM_CONSUMERCLIENT", getQueryTranslateDataSql(sb.toString()));
			Map<String,JSONObject> map = new HashMap<String,JSONObject>();
			for (JSONObject objJson:jsonList){
				map.put(objJson.getString("_id"), objJson);
			}
			for(RzPrjreviewLessee lessee:rzPrjreviewLesseeList){
				Object obj =(Object)map.get(lessee.getPkCustomer());
				try {
					BeanUtils.copyProperties(lessee, obj);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lessee.setPkPrjreview(rzPrjreviewLesseeQuery.getPkPrjreview());
				transRzPrjreviewLesseeList.add(lessee);
			}
			rzPrjreviewLesseePageList.addAll(transRzPrjreviewLesseeList);
			
		}
		GridResult<RzPrjreviewLessee> result = new GridResult<RzPrjreviewLessee>(rzPrjreviewLesseePageList);
		return result;
	}

	private RpcDataQuery getQueryTranslateDataSql(String batchIds) {
		// RPC条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", RpcDataQuery.KeyWord.IN);
		map.put("value", batchIds.toString());
		RpcDataQuery query = new RpcDataQuery();
		query.setLimit(batchIds.split(",").length);
		JSONObject json = new JSONObject();
		List<JSONObject> conditions = new ArrayList<JSONObject>();
		json.element("_id", JSONObject.fromObject(map));
		conditions.add(json);
		query.setConditions(conditions);
		return query;
	}

	/**
	 * 分页查询查询总数
	 * 
	 * @param rzPrjreviewLesseeQuery
	 * @return
	 */
	@Override
	public int getRzPrjreviewLesseeByPageCount(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery) {
		return rzPrjreviewLesseeDao.getRzPrjreviewLesseeByPageCount(rzPrjreviewLesseeQuery);
	}

	public void setRzPrjreviewLesseeDao(RzPrjreviewLesseeDao rzPrjreviewLesseeDao) {
		this.rzPrjreviewLesseeDao = rzPrjreviewLesseeDao;
	}

}