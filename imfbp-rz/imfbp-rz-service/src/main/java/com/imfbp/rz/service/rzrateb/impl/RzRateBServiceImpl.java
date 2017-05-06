package com.imfbp.rz.service.rzrateb.impl;

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

import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.dao.rzrateb.RzRateBDao;
import com.imfbp.rz.service.rzrateb.RzRateBService;





@Component("rzRateBService")
public class RzRateBServiceImpl implements RzRateBService{


	private RzRateBDao rzRateBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzRateB
	 * @return
	 */
	@Override
	public void insertRzRateB(RzRateB rzRateB){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzRateB.setPkRateB(pk);
		rzRateBDao.insertRzRateB(rzRateB);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzRateB>
	 * @return
	 */
	public void insertBatchRzRateB(List<RzRateB> rzRateBList){
		if(rzRateBList != null){
			for(int i=0;i<rzRateBList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzRateBList.get(i).setPkRateB(pk);
			}
			rzRateBDao.insertBatchRzRateB(rzRateBList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzRateBById(RzRateBQuery rzRateBQuery){
		return rzRateBDao.deleteRzRateBById(rzRateBQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzRateBByCondition(RzRateBQuery rzRateBQuery){
		return rzRateBDao.deleteRzRateBByCondition(rzRateBQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzRateBQuery
	 * @return
	 */	
	@Override
	public Result deleteRzRateBByBatchId(RzRateBQuery rzRateBQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzRateBQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzRateBDao.deleteRzRateBByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzRateB
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzRateB rzRateB) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzRateB!=null){
				if(StringUtil.isNotEmpty(rzRateB.getPkRateB())){
					updateRzRateBById(rzRateB);
				}else{
					insertRzRateB(rzRateB);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzRateB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzRateB
	 * @return
	 */
	@Override
	public boolean updateRzRateBById(RzRateB rzRateB){
		return rzRateBDao.updateRzRateBById(rzRateB);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzRateBByCondition(RzRateBQuery record,RzRateBQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzRateBDao.updateRzRateBByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public RzRateB getRzRateBById(RzRateBQuery rzRateBQuery){
		return rzRateBDao.getRzRateBById(rzRateBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public List<RzRateB> getRzRateBAll(RzRateBQuery rzRateBQuery){
		return rzRateBDao.getRzRateBAll(rzRateBQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public GridResult<RzRateB> getRzRateBByPage(RzRateBQuery rzRateBQuery){
		//如果排序的字段是空或者空字符串
		if(rzRateBQuery!=null&&StringUtils.isBlank(rzRateBQuery.getSort())){
			rzRateBQuery.setSort("pk_rate_b");
			rzRateBQuery.setOrder("desc");;
		}
		int total = rzRateBDao.getRzRateBByPageCount(rzRateBQuery);
		PaginatedList<RzRateB> rzRateBPageList = new MysqlPaginatedArrayList<RzRateB>(rzRateBQuery,total);
		List<RzRateB> rzRateBList = rzRateBDao.getRzRateBByPage(rzRateBQuery);
		rzRateBPageList.addAll(rzRateBList);
		GridResult<RzRateB> result = new GridResult<RzRateB>(rzRateBPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzRateBQuery
	 * @return
	 */
	@Override
	public int getRzRateBByPageCount(RzRateBQuery rzRateBQuery){
		return rzRateBDao.getRzRateBByPageCount(rzRateBQuery);
	}

	public void setRzRateBDao(RzRateBDao  rzRateBDao){
		this.rzRateBDao = rzRateBDao;
	}
	
}