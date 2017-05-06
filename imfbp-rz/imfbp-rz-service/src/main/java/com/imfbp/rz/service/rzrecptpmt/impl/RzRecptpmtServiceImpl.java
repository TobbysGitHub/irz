package com.imfbp.rz.service.rzrecptpmt.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzrecptpmt.RzRecptpmt;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;
import com.imfbp.rz.dao.rzrecptpmt.RzRecptpmtDao;
import com.imfbp.rz.service.rzrecptpmt.RzRecptpmtService;
import com.imfbp.rz.util.DateUtil;



@Component("rzRecptpmtService")
public class RzRecptpmtServiceImpl implements RzRecptpmtService{


	private RzRecptpmtDao rzRecptpmtDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzRecptpmt
	 * @return
	 */
	@Override
	public void insertRzRecptpmt(RzRecptpmt rzRecptpmt){
		RzRecptpmtQuery rzRecptpmtQuery=new RzRecptpmtQuery();
		rzRecptpmtQuery.setCode(rzRecptpmt.getCode());
		List<RzRecptpmt>list=rzRecptpmtDao.getRzRecptpmtAll(rzRecptpmtQuery);
		//编码不为空就对编码进行唯一性校验
		if(list != null && list.size()>0) return;
		RzRecptpmtQuery rzRecptpmtQuery1=new RzRecptpmtQuery();
		rzRecptpmtQuery1.setName(rzRecptpmt.getName());
		List<RzRecptpmt>list1=rzRecptpmtDao.getRzRecptpmtAll(rzRecptpmtQuery1);
		if(list1 != null && list1.size()>0) return;
		String pk = primaryKeyUtil.getPrimaryKey();
		rzRecptpmt.setPkRecptpmt(pk);
		rzRecptpmt.setTs(DateUtil.getTs());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String date=format.format(new java.util.Date());
		rzRecptpmt.setEnabledate(date);
		rzRecptpmt.setDr(0);
		rzRecptpmt.setEnablestate(1);
		rzRecptpmtDao.insertRzRecptpmt(rzRecptpmt);	
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery){
		return rzRecptpmtDao.deleteRzRecptpmtById(rzRecptpmtQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRecptpmtByCondition(RzRecptpmtQuery rzRecptpmtQuery){
		return rzRecptpmtDao.deleteRzRecptpmtByCondition(rzRecptpmtQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzRecptpmtQuery
	 * @return
	 */	
	@Override
	public Result deleteRzRecptpmtByBatchId(RzRecptpmtQuery rzRecptpmtQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRecptpmtQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzRecptpmtDao.deleteRzRecptpmtByBatchId(data);	
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
	public boolean logicDeleteRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery){
		return rzRecptpmtDao.logicDeleteRzRecptpmtById(rzRecptpmtQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzRecptpmtByCondition(RzRecptpmtQuery rzRecptpmtQuery){
		return rzRecptpmtDao.logicDeleteRzRecptpmtByCondition(rzRecptpmtQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzRecptpmtQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzRecptpmtByBatchId(RzRecptpmtQuery rzRecptpmtQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRecptpmtQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzRecptpmtDao.logicDeleteRzRecptpmtByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzRecptpmt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzRecptpmt rzRecptpmt) {
		Result result = new Result();
		//设置调用失败
		result.setSuccess(false);
		try {
			if(rzRecptpmt!=null){
				if(StringUtil.isNotEmpty(rzRecptpmt.getPkRecptpmt())){
					updateRzRecptpmtById(rzRecptpmt);
				}else{
					insertRzRecptpmt(rzRecptpmt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzRecptpmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzRecptpmt
	 * @return
	 */
	@Override
	public boolean updateRzRecptpmtById(RzRecptpmt rzRecptpmt){
		String pk=rzRecptpmt.getPkRecptpmt();
		RzRecptpmtQuery rzRecptpmtQuery=new RzRecptpmtQuery();
		rzRecptpmtQuery.setCode(rzRecptpmt.getCode());
		List<RzRecptpmt>list=rzRecptpmtDao.getRzRecptpmtAll(rzRecptpmtQuery);
		//编码不为空就对编码进行唯一性校验
		if(list != null && list.size()>0){
			String tempPk=list.get(0).getPkRecptpmt();
			if(StringUtils.isNotEmpty(pk) && StringUtils.isNotEmpty(tempPk) && !pk.equals(tempPk))
			return false;
		}
		RzRecptpmtQuery rzRecptpmtQuery1=new RzRecptpmtQuery();
		rzRecptpmtQuery1.setName(rzRecptpmt.getName());
		List<RzRecptpmt>list1=rzRecptpmtDao.getRzRecptpmtAll(rzRecptpmtQuery1);
		if(list1 != null && list1.size()>0){
			String tempPk=list1.get(0).getPkRecptpmt();
			if(StringUtils.isNotEmpty(pk) && StringUtils.isNotEmpty(tempPk) && !pk.equals(tempPk))
			return false;
		}
		return rzRecptpmtDao.updateRzRecptpmtById(rzRecptpmt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRecptpmtByCondition(RzRecptpmtQuery record,RzRecptpmtQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzRecptpmtDao.updateRzRecptpmtByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public RzRecptpmt getRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery){
		return rzRecptpmtDao.getRzRecptpmtById(rzRecptpmtQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public List<RzRecptpmt> getRzRecptpmtAll(RzRecptpmtQuery rzRecptpmtQuery){
		return rzRecptpmtDao.getRzRecptpmtAll(rzRecptpmtQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public GridResult<RzRecptpmt> getRzRecptpmtByPage(RzRecptpmtQuery rzRecptpmtQuery){
		//如果排序的字段是空或者空字符串
		if(rzRecptpmtQuery!=null&&StringUtils.isBlank(rzRecptpmtQuery.getSort())){
			rzRecptpmtQuery.setSort("pk_recptpmt");
			rzRecptpmtQuery.setOrder("desc");;
		}
		int total = rzRecptpmtDao.getRzRecptpmtByPageCount(rzRecptpmtQuery);
		PaginatedList<RzRecptpmt> rzRecptpmtPageList = new MysqlPaginatedArrayList<RzRecptpmt>(rzRecptpmtQuery,total);
		List<RzRecptpmt> rzRecptpmtList = rzRecptpmtDao.getRzRecptpmtByPage(rzRecptpmtQuery);
		rzRecptpmtPageList.addAll(rzRecptpmtList);
		GridResult<RzRecptpmt> result = new GridResult<RzRecptpmt>(rzRecptpmtPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzRecptpmtQuery
	 * @return
	 */
	@Override
	public int getRzRecptpmtByPageCount(RzRecptpmtQuery rzRecptpmtQuery){
		return rzRecptpmtDao.getRzRecptpmtByPageCount(rzRecptpmtQuery);
	}

	public void setRzRecptpmtDao(RzRecptpmtDao  rzRecptpmtDao){
		this.rzRecptpmtDao = rzRecptpmtDao;
	}
   //批量更新
	@Override
	public Result updateByBatchId(RzRecptpmtQuery rzRecptpmtQuery) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRecptpmtQuery.getBatchId().split(",");
			data.put("batchId",batchIdArr);
			data.put("action",rzRecptpmtQuery.getEnablestate());
			boolean flag = rzRecptpmtDao.updateByBatchId(data);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}