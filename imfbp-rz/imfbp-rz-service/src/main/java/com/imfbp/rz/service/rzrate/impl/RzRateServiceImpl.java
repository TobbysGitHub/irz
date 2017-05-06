package com.imfbp.rz.service.rzrate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzrate.RzRateDao;
import com.imfbp.rz.dao.rzrateb.RzRateBDao;
import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;
import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.rzrate.RzRateService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import net.sf.json.JSONObject;





@Component("rzRateService")
public class RzRateServiceImpl implements RzRateService{


	private RzRateDao rzRateDao;

	private RzRateBDao rzRateBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;
	
	@Autowired
	private BillnoService billnoService;

	/**
	 * 添加
	 * @param rzRate
	 * @return
	 */
	@Override
	public void insertRzRate(RzRate rzRate){
		String pk = primaryKeyUtil.getPrimaryKey();
		String billno;
		try {
			billno = billnoService.getBillno(INodeConsts.RZ_RATE_NO);
			rzRate.setRateCode(billno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rzRate.setTs(DateUtil.getTs());
		rzRate.setDr(0);
		rzRate.setPkRate(pk);
		rzRateDao.insertRzRate(rzRate);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzRate>
	 * @return
	 */
	public void insertBatchRzRate(List<RzRate> rzRateList){
		if(rzRateList != null){
			for(int i=0;i<rzRateList.size();i++){
				rzRateList.get(i).setTs(DateUtil.getTs());
				rzRateList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzRateList.get(i).setPkRate(pk);
			}
			rzRateDao.insertBatchRzRate(rzRateList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzRateById(RzRateQuery rzRateQuery){
		return rzRateDao.deleteRzRateById(rzRateQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateByCondition(RzRateQuery rzRateQuery){
		return rzRateDao.deleteRzRateByCondition(rzRateQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzRateQuery
	 * @return
	 */	
	@Override
	public Result deleteRzRateByBatchId(RzRateQuery rzRateQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRateQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			//删除主表
			boolean flat = rzRateDao.deleteRzRateByBatchId(data);
			for(String batchId : batchIdArr){
				RzRateBQuery rzRateBQuery = new RzRateBQuery();
				rzRateBQuery.setPkRate(batchId);
				//删除子表
				rzRateBDao.deleteRzRateBByCondition(rzRateBQuery);
			}
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
	public boolean logicDeleteRzRateById(RzRateQuery rzRateQuery){
		return rzRateDao.logicDeleteRzRateById(rzRateQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRateByCondition(RzRateQuery rzRateQuery){
		return rzRateDao.logicDeleteRzRateByCondition(rzRateQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzRateQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzRateByBatchId(RzRateQuery rzRateQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRateQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzRateDao.logicDeleteRzRateByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzRate
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzRate rzRate) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzRate!=null){
				if(StringUtil.isNotEmpty(rzRate.getPkRate())){
					updateRzRateById(rzRate);
				}else{
					insertRzRate(rzRate);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzRate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzRate
	 * @return
	 */
	@Override
	public boolean updateRzRateById(RzRate rzRate){
		return rzRateDao.updateRzRateById(rzRate);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRateByCondition(RzRateQuery record,RzRateQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzRateDao.updateRzRateByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public RzRate getRzRateById(RzRateQuery rzRateQuery){
		return rzRateDao.getRzRateById(rzRateQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public List<RzRate> getRzRateAll(RzRateQuery rzRateQuery){
		return rzRateDao.getRzRateAll(rzRateQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public GridResult<RzRate> getRzRateByPage(RzRateQuery rzRateQuery){
		//如果排序的字段是空或者空字符串
		if(rzRateQuery!=null&&StringUtils.isBlank(rzRateQuery.getSort())){
			rzRateQuery.setSort("pk_rate");
			rzRateQuery.setOrder("desc");;
		}
		int total = rzRateDao.getRzRateByPageCount(rzRateQuery);
		PaginatedList<RzRate> rzRatePageList = new MysqlPaginatedArrayList<RzRate>(rzRateQuery,total);
		List<RzRate> rzRateList = rzRateDao.getRzRateByPage(rzRateQuery);
		rzRatePageList.addAll(rzRateList);
		GridResult<RzRate> result = new GridResult<RzRate>(rzRatePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzRateQuery
	 * @return
	 */
	@Override
	public int getRzRateByPageCount(RzRateQuery rzRateQuery){
		return rzRateDao.getRzRateByPageCount(rzRateQuery);
	}


	public void setRzRateDao(RzRateDao  rzRateDao){
		this.rzRateDao = rzRateDao;
	}

	public RzRateBDao getRzRateBDao() {
		return rzRateBDao;
	}

	public void setRzRateBDao(RzRateBDao rzRateBDao) {
		this.rzRateBDao = rzRateBDao;
	}

	@Override
	public Result insertRzRate(Map<String, String> map) {
		Result result = new Result();
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		Map<String, String> rateMap = new HashMap<String, String>();
		//查找动态生成字段
		while(iterator.hasNext()){
			String key = iterator.next();
			if(key.contains("prd_name")){
				rateMap.put(key, (String)map.get(key));
				iterator.remove();
			}
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		RzRate rzRate = (RzRate)JSONObject.toBean(jsonObject, RzRate.class);
		rzRate.setTs(DateUtil.getTs());
		rzRate.setDr(0);
		//新增审批状态为自由态
		rzRate.setApprovestatus(0);
		String pk = primaryKeyUtil.getPrimaryKey();
		rzRate.setPkRate(pk);
		List<RzRateB> rzRateBList = new ArrayList<RzRateB>();
		for(Map.Entry<String, String> entry : rateMap.entrySet()){
			RzRateB rzRateB = new RzRateB();
			//设置字段主键
			rzRateB.setPkRateprd(entry.getKey().substring(8));
			if(entry.getValue() != null && entry.getValue().length() > 0){
				rzRateB.setRate(Double.parseDouble(entry.getValue()));
			}
			//设置利率主表主键
			rzRateB.setPkRate(rzRate.getPkRate());
			//设置子表主键
			rzRateB.setPkRateB(primaryKeyIdWorker.getPrimaryKey());
			rzRateBList.add(rzRateB);
		}
		try{
			
			String billno = billnoService.getBillno(INodeConsts.RZ_RATE_NO);
			rzRate.setRateCode(billno);
			rzRateDao.insertRzRate(rzRate);
			rzRateBDao.insertBatchRzRateB(rzRateBList);
			result.addDefaultModel("value", rzRate);
			result.setSuccess(true);
		}catch (Exception ex){
			ex.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage(ex.getMessage());
		}
		return result;
	}

	@Override
	public Result updateRzRate(Map<String, String> map) {
		Result result = new Result();
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		Map<String, String> rateMap = new HashMap<String, String>();
		while(iterator.hasNext()){
			String key = iterator.next();
			//查找动态生成的字段
			if(key.contains("prd_name")){
				rateMap.put(key, (String)map.get(key));
				iterator.remove();
			}
		}
		//将map转化为利率主表
		JSONObject jsonObject = JSONObject.fromObject(map);
		RzRate rzRate = (RzRate)JSONObject.toBean(jsonObject, RzRate.class);
		RzRateBQuery rzRateBQuery = new RzRateBQuery();
		rzRateBQuery.setPkRate(rzRate.getPkRate());
		List<RzRateB> rzRateBList = rzRateBDao.getRzRateBAll(rzRateBQuery);
		try{
			rzRateDao.updateRzRateById(rzRate);
			if(rzRateBList != null && rzRateBList.size() > 0){
				//更新利率子表
				for(RzRateB rzRateB : rzRateBList){
					if(rateMap.containsKey("prd_name" + rzRateB.getPkRateprd())){
						if(rateMap.get("prd_name" + rzRateB.getPkRateprd()) != null
								&& rateMap.get("prd_name" + rzRateB.getPkRateprd()).length() > 0){
							//设置新利率
							rzRateB.setRate(Double.parseDouble(rateMap.get("prd_name" + rzRateB.getPkRateprd())));
							rzRateBDao.updateRzRateBById(rzRateB);
						}
					}
				}
			}
			result.setSuccess(true);
			result.addDefaultModel(rzRate);
		}catch (Exception ex){
			ex.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage(ex.getMessage());
		}
		return result;
	}

	@Override
	public Result checkPeriod(RzRate rzRate) {
		Result result = new Result();
		result.setSuccess(false);
		//获取传入利率的生效日期
		String startdate = rzRate.getStartdate();
		//查询审批通过的全部利率
		RzRateQuery rzRateQuery = new RzRateQuery();
		//审批状态为通过
		rzRateQuery.setApprovestatus(3);
		//币种
		rzRateQuery.setPkCurrency(rzRate.getPkCurrency());
		//倒序
		rzRateQuery.setSort("startdate");
		rzRateQuery.setOrder("desc");
		List<RzRate> rzRateList = rzRateDao.getRzRateAll(rzRateQuery);
		if(ToolUtils.isEmptyCollection(rzRateList)){
			result.setSuccess(true);
			result.addDefaultModel(rzRate);
			return result;
		}
		//获取生效日期最晚的利率
		RzRate lastRzRate = rzRateList.get(0);
		String lastStartdate = lastRzRate.getStartdate();
		//获取生效日期最早的利率
		RzRate firstRzRate = rzRateList.get(rzRateList.size() - 1);
		String firstStartdate = firstRzRate.getStartdate();
		if(lastStartdate.compareTo(startdate) >= 0 && firstStartdate.compareTo(startdate) <= 0){
			result.setSuccess(false);
			result.setErrorMessage("该单据在相同币种的区间范围内存在已审核的利率数据，不能再审核，请检查");
			result.addDefaultModel(rzRate);
			return result;
		}
//		//在最晚的生效日期后生效，则要修改最晚利率的失效日期，失效日期为rzRate生效日期的前一天
//		if(lastStartdate.compareTo(startdate) < 0){
//			lastRzRate.setEnddate(DateUtil.getPreviousDay(startdate));
//			rzRateDao.updateRzRateById(lastRzRate);
//		}
//		//在最早的生效日期前生效，则要添加利率的失效日期，失效日期为最早生效日期的前一天
//		if(firstStartdate.compareTo(startdate) > 0){
//			rzRate.setEnddate(DateUtil.getPreviousDay(firstStartdate));
//			rzRateDao.updateRzRateById(rzRate);
//		}
		result.setSuccess(true);
		result.addDefaultModel(rzRate);
		return result;
	}
}