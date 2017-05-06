package com.imfbp.rz.service.rzprjcontrchglessee.impl;

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

import com.imfbp.rz.domain.rzprjcontrchglessee.RzPrjcontrChgLessee;
import com.imfbp.rz.domain.rzprjcontrchglessee.query.RzPrjcontrChgLesseeQuery;
import com.imfbp.rz.dao.rzprjcontrchglessee.RzPrjcontrChgLesseeDao;
import com.imfbp.rz.service.rzprjcontrchglessee.RzPrjcontrChgLesseeService;





@Component("rzPrjcontrChgLesseeService")
public class RzPrjcontrChgLesseeServiceImpl implements RzPrjcontrChgLesseeService{


	private RzPrjcontrChgLesseeDao rzPrjcontrChgLesseeDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrChgLessee
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgLessee(RzPrjcontrChgLessee rzPrjcontrChgLessee){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgLessee.setPkPrjcontrChgLessee(pk);
		rzPrjcontrChgLesseeDao.insertRzPrjcontrChgLessee(rzPrjcontrChgLessee);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgLessee>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgLessee(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList){
		if(rzPrjcontrChgLesseeList != null){
			for(int i=0;i<rzPrjcontrChgLesseeList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgLesseeList.get(i).setPkPrjcontrChgLessee(pk);
			}
			rzPrjcontrChgLesseeDao.insertBatchRzPrjcontrChgLessee(rzPrjcontrChgLesseeList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return rzPrjcontrChgLesseeDao.deleteRzPrjcontrChgLesseeById(rzPrjcontrChgLesseeQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgLesseeByCondition(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return rzPrjcontrChgLesseeDao.deleteRzPrjcontrChgLesseeByCondition(rzPrjcontrChgLesseeQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgLesseeByBatchId(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgLesseeQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgLesseeDao.deleteRzPrjcontrChgLesseeByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgLessee
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgLessee rzPrjcontrChgLessee) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgLessee!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgLessee.getPkPrjcontrChgLessee())){
					updateRzPrjcontrChgLesseeById(rzPrjcontrChgLessee);
				}else{
					insertRzPrjcontrChgLessee(rzPrjcontrChgLessee);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgLessee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgLessee
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgLesseeById(RzPrjcontrChgLessee rzPrjcontrChgLessee){
		return rzPrjcontrChgLesseeDao.updateRzPrjcontrChgLesseeById(rzPrjcontrChgLessee);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgLesseeByCondition(RzPrjcontrChgLesseeQuery record,RzPrjcontrChgLesseeQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgLesseeDao.updateRzPrjcontrChgLesseeByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgLesseeByBatchId(List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgLesseeDao.updateRzPrjcontrChgLesseeByBatchId(rzPrjcontrChgLesseeList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgLessee getRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return rzPrjcontrChgLesseeDao.getRzPrjcontrChgLesseeById(rzPrjcontrChgLesseeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeAll(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return rzPrjcontrChgLesseeDao.getRzPrjcontrChgLesseeAll(rzPrjcontrChgLesseeQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgLessee> getRzPrjcontrChgLesseeByPage(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgLesseeQuery!=null&&StringUtils.isBlank(rzPrjcontrChgLesseeQuery.getSort())){
			rzPrjcontrChgLesseeQuery.setSort("pk_prjcontr_chg_lessee");
			rzPrjcontrChgLesseeQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgLesseeDao.getRzPrjcontrChgLesseeByPageCount(rzPrjcontrChgLesseeQuery);
		PaginatedList<RzPrjcontrChgLessee> rzPrjcontrChgLesseePageList = new MysqlPaginatedArrayList<RzPrjcontrChgLessee>(rzPrjcontrChgLesseeQuery,total);
		List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList = rzPrjcontrChgLesseeDao.getRzPrjcontrChgLesseeByPage(rzPrjcontrChgLesseeQuery);
		rzPrjcontrChgLesseePageList.addAll(rzPrjcontrChgLesseeList);
		GridResult<RzPrjcontrChgLessee> result = new GridResult<RzPrjcontrChgLessee>(rzPrjcontrChgLesseePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgLesseeQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgLesseeByPageCount(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery){
		return rzPrjcontrChgLesseeDao.getRzPrjcontrChgLesseeByPageCount(rzPrjcontrChgLesseeQuery);
	}

	public void setRzPrjcontrChgLesseeDao(RzPrjcontrChgLesseeDao  rzPrjcontrChgLesseeDao){
		this.rzPrjcontrChgLesseeDao = rzPrjcontrChgLesseeDao;
	}
	
}