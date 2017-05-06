package com.imfbp.rz.service.rzoverduederate.impl;

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

import com.imfbp.rz.domain.rzoverduederate.RzOverdueDerate;
import com.imfbp.rz.domain.rzoverduederate.query.RzOverdueDerateQuery;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.dao.rzoverduederate.RzOverdueDerateDao;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.rzoverduederate.RzOverdueDerateService;
import com.imfbp.rz.util.DateUtil;





@Component("rzOverdueDerateService")
public class RzOverdueDerateServiceImpl implements RzOverdueDerateService{


	private RzOverdueDerateDao rzOverdueDerateDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	@Autowired
	private BillnoService billnoService;

	/**
	 * 添加
	 * @param rzOverdueDerate
	 * @return
	 */
	@Override
	public void insertRzOverdueDerate(RzOverdueDerate rzOverdueDerate){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzOverdueDerate.setTs(DateUtil.getTs());
		rzOverdueDerate.setDr(0);
		rzOverdueDerate.setPkOverdueDerate(pk);
		try {
			String billno = billnoService.getBillno(INodeConsts.RZ_RZDERATE_NO);
			rzOverdueDerate.setDerateNo(billno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rzOverdueDerateDao.insertRzOverdueDerate(rzOverdueDerate);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzOverdueDerate>
	 * @return
	 */
	public void insertBatchRzOverdueDerate(List<RzOverdueDerate> rzOverdueDerateList){
		if(rzOverdueDerateList != null){
			for(int i=0;i<rzOverdueDerateList.size();i++){
				rzOverdueDerateList.get(i).setTs(DateUtil.getTs());
				rzOverdueDerateList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzOverdueDerateList.get(i).setPkOverdueDerate(pk);
			}
			rzOverdueDerateDao.insertBatchRzOverdueDerate(rzOverdueDerateList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery){
		return rzOverdueDerateDao.deleteRzOverdueDerateById(rzOverdueDerateQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzOverdueDerateByCondition(RzOverdueDerateQuery rzOverdueDerateQuery){
		return rzOverdueDerateDao.deleteRzOverdueDerateByCondition(rzOverdueDerateQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzOverdueDerateQuery
	 * @return
	 */	
	@Override
	public Result deleteRzOverdueDerateByBatchId(RzOverdueDerateQuery rzOverdueDerateQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzOverdueDerateQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzOverdueDerateDao.deleteRzOverdueDerateByBatchId(data);	
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
	public boolean logicDeleteRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery){
		return rzOverdueDerateDao.logicDeleteRzOverdueDerateById(rzOverdueDerateQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzOverdueDerateByCondition(RzOverdueDerateQuery rzOverdueDerateQuery){
		return rzOverdueDerateDao.logicDeleteRzOverdueDerateByCondition(rzOverdueDerateQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzOverdueDerateQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzOverdueDerateByBatchId(RzOverdueDerateQuery rzOverdueDerateQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzOverdueDerateQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzOverdueDerateDao.logicDeleteRzOverdueDerateByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzOverdueDerate
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzOverdueDerate rzOverdueDerate) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzOverdueDerate!=null){
				if(StringUtil.isNotEmpty(rzOverdueDerate.getPkOverdueDerate())){
					updateRzOverdueDerateById(rzOverdueDerate);
				}else{
					insertRzOverdueDerate(rzOverdueDerate);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzOverdueDerate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzOverdueDerate
	 * @return
	 */
	@Override
	public boolean updateRzOverdueDerateById(RzOverdueDerate rzOverdueDerate){
		return rzOverdueDerateDao.updateRzOverdueDerateById(rzOverdueDerate);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzOverdueDerateByCondition(RzOverdueDerateQuery record,RzOverdueDerateQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzOverdueDerateDao.updateRzOverdueDerateByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	public Result updateRzOverdueDerateByBatchId(List<RzOverdueDerate> rzOverdueDerateList){
		Result result = new Result(false);
		try {
			boolean flag = rzOverdueDerateDao.updateRzOverdueDerateByBatchId(rzOverdueDerateList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public RzOverdueDerate getRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery){
		return rzOverdueDerateDao.getRzOverdueDerateById(rzOverdueDerateQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public List<RzOverdueDerate> getRzOverdueDerateAll(RzOverdueDerateQuery rzOverdueDerateQuery){
		return rzOverdueDerateDao.getRzOverdueDerateAll(rzOverdueDerateQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public GridResult<RzOverdueDerate> getRzOverdueDerateByPage(RzOverdueDerateQuery rzOverdueDerateQuery){
		//如果排序的字段是空或者空字符串
		if(rzOverdueDerateQuery!=null&&StringUtils.isBlank(rzOverdueDerateQuery.getSort())){
			rzOverdueDerateQuery.setSort("pk_overdue_derate");
			rzOverdueDerateQuery.setOrder("desc");;
		}
		int total = rzOverdueDerateDao.getRzOverdueDerateByPageCount(rzOverdueDerateQuery);
		PaginatedList<RzOverdueDerate> rzOverdueDeratePageList = new MysqlPaginatedArrayList<RzOverdueDerate>(rzOverdueDerateQuery,total);
		List<RzOverdueDerate> rzOverdueDerateList = rzOverdueDerateDao.getRzOverdueDerateByPage(rzOverdueDerateQuery);
		rzOverdueDeratePageList.addAll(rzOverdueDerateList);
		GridResult<RzOverdueDerate> result = new GridResult<RzOverdueDerate>(rzOverdueDeratePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzOverdueDerateQuery
	 * @return
	 */
	@Override
	public int getRzOverdueDerateByPageCount(RzOverdueDerateQuery rzOverdueDerateQuery){
		return rzOverdueDerateDao.getRzOverdueDerateByPageCount(rzOverdueDerateQuery);
	}

	public void setRzOverdueDerateDao(RzOverdueDerateDao  rzOverdueDerateDao){
		this.rzOverdueDerateDao = rzOverdueDerateDao;
	}
	
}