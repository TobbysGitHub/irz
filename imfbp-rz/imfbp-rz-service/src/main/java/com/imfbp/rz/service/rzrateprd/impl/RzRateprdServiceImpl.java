package com.imfbp.rz.service.rzrateprd.impl;

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

import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;
import com.imfbp.rz.dao.rzrateprd.RzRateprdDao;
import com.imfbp.rz.service.rzrateprd.RzRateprdService;





@Component("rzRateprdService")
public class RzRateprdServiceImpl implements RzRateprdService{


	private RzRateprdDao rzRateprdDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzRateprd
	 * @return
	 */
	@Override
	public void insertRzRateprd(RzRateprd rzRateprd){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzRateprd.setPkRateprd(pk);
		rzRateprdDao.insertRzRateprd(rzRateprd);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzRateprd>
	 * @return
	 */
	public void insertBatchRzRateprd(List<RzRateprd> rzRateprdList){
		if(rzRateprdList != null){
			for(int i=0;i<rzRateprdList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzRateprdList.get(i).setPkRateprd(pk);
			}
			rzRateprdDao.insertBatchRzRateprd(rzRateprdList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzRateprdById(RzRateprdQuery rzRateprdQuery){
		return rzRateprdDao.deleteRzRateprdById(rzRateprdQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateprdByCondition(RzRateprdQuery rzRateprdQuery){
		return rzRateprdDao.deleteRzRateprdByCondition(rzRateprdQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzRateprdQuery
	 * @return
	 */	
	@Override
	public Result deleteRzRateprdByBatchId(RzRateprdQuery rzRateprdQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRateprdQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzRateprdDao.deleteRzRateprdByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzRateprd
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzRateprd rzRateprd) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzRateprd!=null){
				if(StringUtil.isNotEmpty(rzRateprd.getPkRateprd())){
					updateRzRateprdById(rzRateprd);
				}else{
					insertRzRateprd(rzRateprd);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzRateprd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzRateprd
	 * @return
	 */
	@Override
	public boolean updateRzRateprdById(RzRateprd rzRateprd){
		return rzRateprdDao.updateRzRateprdById(rzRateprd);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRateprdByCondition(RzRateprdQuery record,RzRateprdQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzRateprdDao.updateRzRateprdByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public RzRateprd getRzRateprdById(RzRateprdQuery rzRateprdQuery){
		return rzRateprdDao.getRzRateprdById(rzRateprdQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public List<RzRateprd> getRzRateprdAll(RzRateprdQuery rzRateprdQuery){
		return rzRateprdDao.getRzRateprdAll(rzRateprdQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public GridResult<RzRateprd> getRzRateprdByPage(RzRateprdQuery rzRateprdQuery){
		//如果排序的字段是空或者空字符串
		if(rzRateprdQuery!=null&&StringUtils.isBlank(rzRateprdQuery.getSort())){
			rzRateprdQuery.setSort("pk_rateprd");
			rzRateprdQuery.setOrder("desc");;
		}
		int total = rzRateprdDao.getRzRateprdByPageCount(rzRateprdQuery);
		PaginatedList<RzRateprd> rzRateprdPageList = new MysqlPaginatedArrayList<RzRateprd>(rzRateprdQuery,total);
		List<RzRateprd> rzRateprdList = rzRateprdDao.getRzRateprdByPage(rzRateprdQuery);
		rzRateprdPageList.addAll(rzRateprdList);
		GridResult<RzRateprd> result = new GridResult<RzRateprd>(rzRateprdPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzRateprdQuery
	 * @return
	 */
	@Override
	public int getRzRateprdByPageCount(RzRateprdQuery rzRateprdQuery){
		return rzRateprdDao.getRzRateprdByPageCount(rzRateprdQuery);
	}

	public void setRzRateprdDao(RzRateprdDao  rzRateprdDao){
		this.rzRateprdDao = rzRateprdDao;
	}
	@Override
	public List<RzRateprd> getRzRateprdByDays(RzRateprdQuery rzRateprdQuery) {
		return rzRateprdDao.getRzRateprdByDays(rzRateprdQuery);
	}
}