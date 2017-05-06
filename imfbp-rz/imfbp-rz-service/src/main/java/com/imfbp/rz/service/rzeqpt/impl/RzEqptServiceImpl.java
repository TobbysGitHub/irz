package com.imfbp.rz.service.rzeqpt.impl;

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

import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;
import com.imfbp.rz.dao.rzeqpt.RzEqptDao;
import com.imfbp.rz.service.rzeqpt.RzEqptService;
import com.imfbp.rz.util.DateUtil;





@Component("rzEqptService")
public class RzEqptServiceImpl implements RzEqptService{


	private RzEqptDao rzEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzEqpt
	 * @return
	 */
	@Override
	public void insertRzEqpt(RzEqpt rzEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzEqpt.setTs(DateUtil.getTs());
		rzEqpt.setDr(0);
		rzEqpt.setPkEqpt(pk);
		rzEqpt.setEnablestate(1);
		rzEqptDao.insertRzEqpt(rzEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzEqpt>
	 * @return
	 */
	public void insertBatchRzEqpt(List<RzEqpt> rzEqptList){
		if(rzEqptList != null){
			for(int i=0;i<rzEqptList.size();i++){
				rzEqptList.get(i).setTs(DateUtil.getTs());
				rzEqptList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzEqptList.get(i).setPkEqpt(pk);
			}
			rzEqptDao.insertBatchRzEqpt(rzEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzEqptById(RzEqptQuery rzEqptQuery){
		return rzEqptDao.deleteRzEqptById(rzEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzEqptByCondition(RzEqptQuery rzEqptQuery){
		return rzEqptDao.deleteRzEqptByCondition(rzEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzEqptByBatchId(RzEqptQuery rzEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzEqptDao.deleteRzEqptByBatchId(data);	
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
	public boolean logicDeleteRzEqptById(RzEqptQuery rzEqptQuery){
		return rzEqptDao.logicDeleteRzEqptById(rzEqptQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzEqptByCondition(RzEqptQuery rzEqptQuery){
		return rzEqptDao.logicDeleteRzEqptByCondition(rzEqptQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzEqptQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzEqptByBatchId(RzEqptQuery rzEqptQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzEqptDao.logicDeleteRzEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzEqpt rzEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzEqpt!=null){
				if(StringUtil.isNotEmpty(rzEqpt.getPkEqpt())){
					updateRzEqptById(rzEqpt);
				}else{
					insertRzEqpt(rzEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzEqpt
	 * @return
	 */
	@Override
	public boolean updateRzEqptById(RzEqpt rzEqpt){
		return rzEqptDao.updateRzEqptById(rzEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzEqptByCondition(RzEqptQuery record,RzEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzEqptDao.updateRzEqptByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzEqptQuery
	 * @return
	 */
	public Result updateRzEqptByBatchId(List<RzEqpt> rzEqptList){
		Result result = new Result(false);
		try {
			boolean flag = rzEqptDao.updateRzEqptByBatchId(rzEqptList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public RzEqpt getRzEqptById(RzEqptQuery rzEqptQuery){
		return rzEqptDao.getRzEqptById(rzEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public List<RzEqpt> getRzEqptAll(RzEqptQuery rzEqptQuery){
		return rzEqptDao.getRzEqptAll(rzEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzEqpt> getRzEqptByPage(RzEqptQuery rzEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzEqptQuery!=null&&StringUtils.isBlank(rzEqptQuery.getSort())){
			rzEqptQuery.setSort("pk_eqpt");
			rzEqptQuery.setOrder("desc");;
		}
		int total = rzEqptDao.getRzEqptByPageCount(rzEqptQuery);
		PaginatedList<RzEqpt> rzEqptPageList = new MysqlPaginatedArrayList<RzEqpt>(rzEqptQuery,total);
		List<RzEqpt> rzEqptList = rzEqptDao.getRzEqptByPage(rzEqptQuery);
		rzEqptPageList.addAll(rzEqptList);
		GridResult<RzEqpt> result = new GridResult<RzEqpt>(rzEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzEqptQuery
	 * @return
	 */
	@Override
	public int getRzEqptByPageCount(RzEqptQuery rzEqptQuery){
		return rzEqptDao.getRzEqptByPageCount(rzEqptQuery);
	}

	public void setRzEqptDao(RzEqptDao  rzEqptDao){
		this.rzEqptDao = rzEqptDao;
	}
	
}