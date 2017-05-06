package com.imfbp.rz.service.rzprjcontrlessee.impl;

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

import com.imfbp.rz.domain.rzprjcontrlessee.RzPrjcontrLessee;
import com.imfbp.rz.domain.rzprjcontrlessee.query.RzPrjcontrLesseeQuery;
import com.imfbp.rz.dao.rzprjcontrlessee.RzPrjcontrLesseeDao;
import com.imfbp.rz.service.rzprjcontrlessee.RzPrjcontrLesseeService;





@Component("rzPrjcontrLesseeService")
public class RzPrjcontrLesseeServiceImpl implements RzPrjcontrLesseeService{


	private RzPrjcontrLesseeDao rzPrjcontrLesseeDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrLessee
	 * @return
	 */
	@Override
	public void insertRzPrjcontrLessee(RzPrjcontrLessee rzPrjcontrLessee){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrLessee.setPkPrjcontrLessee(pk);
		rzPrjcontrLesseeDao.insertRzPrjcontrLessee(rzPrjcontrLessee);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrLessee>
	 * @return
	 */
	public void insertBatchRzPrjcontrLessee(List<RzPrjcontrLessee> rzPrjcontrLesseeList){
		if(rzPrjcontrLesseeList != null){
			for(int i=0;i<rzPrjcontrLesseeList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrLesseeList.get(i).setPkPrjcontrLessee(pk);
			}
			rzPrjcontrLesseeDao.insertBatchRzPrjcontrLessee(rzPrjcontrLesseeList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLesseeById(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return rzPrjcontrLesseeDao.deleteRzPrjcontrLesseeById(rzPrjcontrLesseeQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrLesseeByCondition(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return rzPrjcontrLesseeDao.deleteRzPrjcontrLesseeByCondition(rzPrjcontrLesseeQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrLesseeByBatchId(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrLesseeQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrLesseeDao.deleteRzPrjcontrLesseeByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrLessee
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrLessee rzPrjcontrLessee) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrLessee!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrLessee.getPkPrjcontrLessee())){
					updateRzPrjcontrLesseeById(rzPrjcontrLessee);
				}else{
					insertRzPrjcontrLessee(rzPrjcontrLessee);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrLessee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrLessee
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrLesseeById(RzPrjcontrLessee rzPrjcontrLessee){
		return rzPrjcontrLesseeDao.updateRzPrjcontrLesseeById(rzPrjcontrLessee);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrLesseeByCondition(RzPrjcontrLesseeQuery record,RzPrjcontrLesseeQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrLesseeDao.updateRzPrjcontrLesseeByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	public Result updateRzPrjcontrLesseeByBatchId(List<RzPrjcontrLessee> rzPrjcontrLesseeList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrLesseeDao.updateRzPrjcontrLesseeByBatchId(rzPrjcontrLesseeList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public RzPrjcontrLessee getRzPrjcontrLesseeById(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return rzPrjcontrLesseeDao.getRzPrjcontrLesseeById(rzPrjcontrLesseeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrLessee> getRzPrjcontrLesseeAll(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return rzPrjcontrLesseeDao.getRzPrjcontrLesseeAll(rzPrjcontrLesseeQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrLessee> getRzPrjcontrLesseeByPage(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrLesseeQuery!=null&&StringUtils.isBlank(rzPrjcontrLesseeQuery.getSort())){
			rzPrjcontrLesseeQuery.setSort("pk_prjcontr_lessee");
			rzPrjcontrLesseeQuery.setOrder("desc");;
		}
		int total = rzPrjcontrLesseeDao.getRzPrjcontrLesseeByPageCount(rzPrjcontrLesseeQuery);
		PaginatedList<RzPrjcontrLessee> rzPrjcontrLesseePageList = new MysqlPaginatedArrayList<RzPrjcontrLessee>(rzPrjcontrLesseeQuery,total);
		List<RzPrjcontrLessee> rzPrjcontrLesseeList = rzPrjcontrLesseeDao.getRzPrjcontrLesseeByPage(rzPrjcontrLesseeQuery);
		rzPrjcontrLesseePageList.addAll(rzPrjcontrLesseeList);
		GridResult<RzPrjcontrLessee> result = new GridResult<RzPrjcontrLessee>(rzPrjcontrLesseePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrLesseeQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrLesseeByPageCount(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery){
		return rzPrjcontrLesseeDao.getRzPrjcontrLesseeByPageCount(rzPrjcontrLesseeQuery);
	}

	public void setRzPrjcontrLesseeDao(RzPrjcontrLesseeDao  rzPrjcontrLesseeDao){
		this.rzPrjcontrLesseeDao = rzPrjcontrLesseeDao;
	}
	
}