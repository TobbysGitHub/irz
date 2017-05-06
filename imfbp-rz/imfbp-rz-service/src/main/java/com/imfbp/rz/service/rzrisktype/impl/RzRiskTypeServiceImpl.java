package com.imfbp.rz.service.rzrisktype.impl;

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

import com.imfbp.rz.domain.rzrisktype.RzRiskType;
import com.imfbp.rz.domain.rzrisktype.query.RzRiskTypeQuery;
import com.imfbp.rz.dao.rzrisktype.RzRiskTypeDao;
import com.imfbp.rz.service.rzrisktype.RzRiskTypeService;





@Component("rzRiskTypeService")
public class RzRiskTypeServiceImpl implements RzRiskTypeService{


	private RzRiskTypeDao rzRiskTypeDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzRiskType
	 * @return
	 */
	@Override
	public void insertRzRiskType(RzRiskType rzRiskType){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzRiskType.setPkRiskType(pk);
		rzRiskTypeDao.insertRzRiskType(rzRiskType);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzRiskType>
	 * @return
	 */
	public void insertBatchRzRiskType(List<RzRiskType> rzRiskTypeList){
		if(rzRiskTypeList != null){
			for(int i=0;i<rzRiskTypeList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzRiskTypeList.get(i).setPkRiskType(pk);
			}
			rzRiskTypeDao.insertBatchRzRiskType(rzRiskTypeList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzRiskTypeById(RzRiskTypeQuery rzRiskTypeQuery){
		return rzRiskTypeDao.deleteRzRiskTypeById(rzRiskTypeQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRiskTypeByCondition(RzRiskTypeQuery rzRiskTypeQuery){
		return rzRiskTypeDao.deleteRzRiskTypeByCondition(rzRiskTypeQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzRiskTypeQuery
	 * @return
	 */	
	@Override
	public Result deleteRzRiskTypeByBatchId(RzRiskTypeQuery rzRiskTypeQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRiskTypeQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzRiskTypeDao.deleteRzRiskTypeByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzRiskType
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzRiskType rzRiskType) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzRiskType!=null){
				//if(StringUtil.isNotEmpty(rzRiskType.getPkRiskType())){
					updateRzRiskTypeById(rzRiskType);
				//}else{
				//	insertRzRiskType(rzRiskType);
				//}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzRiskType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzRiskType
	 * @return
	 */
	@Override
	public boolean updateRzRiskTypeById(RzRiskType rzRiskType){
		return rzRiskTypeDao.updateRzRiskTypeById(rzRiskType);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRiskTypeByCondition(RzRiskTypeQuery record,RzRiskTypeQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzRiskTypeDao.updateRzRiskTypeByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzRiskTypeQuery
	 * @return
	 */
	public Result updateRzRiskTypeByBatchId(List<RzRiskType> rzRiskTypeList){
		Result result = new Result(false);
		try {
			boolean flag = rzRiskTypeDao.updateRzRiskTypeByBatchId(rzRiskTypeList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public RzRiskType getRzRiskTypeById(RzRiskTypeQuery rzRiskTypeQuery){
		return rzRiskTypeDao.getRzRiskTypeById(rzRiskTypeQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public List<RzRiskType> getRzRiskTypeAll(RzRiskTypeQuery rzRiskTypeQuery){
		return rzRiskTypeDao.getRzRiskTypeAll(rzRiskTypeQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public GridResult<RzRiskType> getRzRiskTypeByPage(RzRiskTypeQuery rzRiskTypeQuery){
		//如果排序的字段是空或者空字符串
		if(rzRiskTypeQuery!=null&&StringUtils.isBlank(rzRiskTypeQuery.getSort())){
			rzRiskTypeQuery.setSort("pk_risk_type");
			rzRiskTypeQuery.setOrder("asc");;
		}
		int total = rzRiskTypeDao.getRzRiskTypeByPageCount(rzRiskTypeQuery);
		PaginatedList<RzRiskType> rzRiskTypePageList = new MysqlPaginatedArrayList<RzRiskType>(rzRiskTypeQuery,total);
		List<RzRiskType> rzRiskTypeList = rzRiskTypeDao.getRzRiskTypeByPage(rzRiskTypeQuery);
		rzRiskTypePageList.addAll(rzRiskTypeList);
		GridResult<RzRiskType> result = new GridResult<RzRiskType>(rzRiskTypePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzRiskTypeQuery
	 * @return
	 */
	@Override
	public int getRzRiskTypeByPageCount(RzRiskTypeQuery rzRiskTypeQuery){
		return rzRiskTypeDao.getRzRiskTypeByPageCount(rzRiskTypeQuery);
	}

	public void setRzRiskTypeDao(RzRiskTypeDao  rzRiskTypeDao){
		this.rzRiskTypeDao = rzRiskTypeDao;
	}
	
}