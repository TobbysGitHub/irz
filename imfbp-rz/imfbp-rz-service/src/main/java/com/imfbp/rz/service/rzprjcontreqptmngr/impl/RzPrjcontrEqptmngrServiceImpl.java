package com.imfbp.rz.service.rzprjcontreqptmngr.impl;

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

import com.imfbp.rz.domain.rzprjcontreqptmngr.RzPrjcontrEqptmngr;
import com.imfbp.rz.domain.rzprjcontreqptmngr.query.RzPrjcontrEqptmngrQuery;
import com.imfbp.rz.dao.rzprjcontreqptmngr.RzPrjcontrEqptmngrDao;
import com.imfbp.rz.service.rzprjcontreqptmngr.RzPrjcontrEqptmngrService;
import com.imfbp.rz.util.DateUtil;





@Component("rzPrjcontrEqptmngrService")
public class RzPrjcontrEqptmngrServiceImpl implements RzPrjcontrEqptmngrService{


	private RzPrjcontrEqptmngrDao rzPrjcontrEqptmngrDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrEqptmngr
	 * @return
	 */
	@Override
	public void insertRzPrjcontrEqptmngr(RzPrjcontrEqptmngr rzPrjcontrEqptmngr){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrEqptmngr.setTs(DateUtil.getTs());
		rzPrjcontrEqptmngr.setDr(0);
		rzPrjcontrEqptmngr.setPkPrjcontrEqptmngr(pk);
		rzPrjcontrEqptmngrDao.insertRzPrjcontrEqptmngr(rzPrjcontrEqptmngr);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrEqptmngr>
	 * @return
	 */
	public void insertBatchRzPrjcontrEqptmngr(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList){
		if(rzPrjcontrEqptmngrList != null){
			for(int i=0;i<rzPrjcontrEqptmngrList.size();i++){
				rzPrjcontrEqptmngrList.get(i).setTs(DateUtil.getTs());
				rzPrjcontrEqptmngrList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrEqptmngrList.get(i).setPkPrjcontrEqptmngr(pk);
			}
			rzPrjcontrEqptmngrDao.insertBatchRzPrjcontrEqptmngr(rzPrjcontrEqptmngrList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return rzPrjcontrEqptmngrDao.deleteRzPrjcontrEqptmngrById(rzPrjcontrEqptmngrQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return rzPrjcontrEqptmngrDao.deleteRzPrjcontrEqptmngrByCondition(rzPrjcontrEqptmngrQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrEqptmngrByBatchId(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrEqptmngrQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrEqptmngrDao.deleteRzPrjcontrEqptmngrByBatchId(data);	
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
	public boolean logicDeleteRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return rzPrjcontrEqptmngrDao.logicDeleteRzPrjcontrEqptmngrById(rzPrjcontrEqptmngrQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return rzPrjcontrEqptmngrDao.logicDeleteRzPrjcontrEqptmngrByCondition(rzPrjcontrEqptmngrQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzPrjcontrEqptmngrByBatchId(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrEqptmngrQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzPrjcontrEqptmngrDao.logicDeleteRzPrjcontrEqptmngrByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrEqptmngr
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrEqptmngr rzPrjcontrEqptmngr) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrEqptmngr!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrEqptmngr.getPkPrjcontrEqptmngr())){
					updateRzPrjcontrEqptmngrById(rzPrjcontrEqptmngr);
				}else{
					insertRzPrjcontrEqptmngr(rzPrjcontrEqptmngr);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrEqptmngr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrEqptmngr
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrEqptmngrById(RzPrjcontrEqptmngr rzPrjcontrEqptmngr){
		return rzPrjcontrEqptmngrDao.updateRzPrjcontrEqptmngrById(rzPrjcontrEqptmngr);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrEqptmngrByCondition(RzPrjcontrEqptmngrQuery record,RzPrjcontrEqptmngrQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrEqptmngrDao.updateRzPrjcontrEqptmngrByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	public Result updateRzPrjcontrEqptmngrByBatchId(List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrEqptmngrDao.updateRzPrjcontrEqptmngrByBatchId(rzPrjcontrEqptmngrList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public RzPrjcontrEqptmngr getRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return rzPrjcontrEqptmngrDao.getRzPrjcontrEqptmngrById(rzPrjcontrEqptmngrQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrAll(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return rzPrjcontrEqptmngrDao.getRzPrjcontrEqptmngrAll(rzPrjcontrEqptmngrQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrEqptmngr> getRzPrjcontrEqptmngrByPage(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrEqptmngrQuery!=null&&StringUtils.isBlank(rzPrjcontrEqptmngrQuery.getSort())){
			rzPrjcontrEqptmngrQuery.setSort("pk_prjcontr_eqptmngr");
			rzPrjcontrEqptmngrQuery.setOrder("desc");;
		}
		int total = rzPrjcontrEqptmngrDao.getRzPrjcontrEqptmngrByPageCount(rzPrjcontrEqptmngrQuery);
		PaginatedList<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrPageList = new MysqlPaginatedArrayList<RzPrjcontrEqptmngr>(rzPrjcontrEqptmngrQuery,total);
		List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList = rzPrjcontrEqptmngrDao.getRzPrjcontrEqptmngrByPage(rzPrjcontrEqptmngrQuery);
		rzPrjcontrEqptmngrPageList.addAll(rzPrjcontrEqptmngrList);
		GridResult<RzPrjcontrEqptmngr> result = new GridResult<RzPrjcontrEqptmngr>(rzPrjcontrEqptmngrPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrEqptmngrQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrEqptmngrByPageCount(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery){
		return rzPrjcontrEqptmngrDao.getRzPrjcontrEqptmngrByPageCount(rzPrjcontrEqptmngrQuery);
	}

	public void setRzPrjcontrEqptmngrDao(RzPrjcontrEqptmngrDao  rzPrjcontrEqptmngrDao){
		this.rzPrjcontrEqptmngrDao = rzPrjcontrEqptmngrDao;
	}
	
}