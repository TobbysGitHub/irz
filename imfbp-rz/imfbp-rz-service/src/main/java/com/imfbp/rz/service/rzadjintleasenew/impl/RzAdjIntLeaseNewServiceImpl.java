package com.imfbp.rz.service.rzadjintleasenew.impl;

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

import com.imfbp.rz.domain.rzadjintleasenew.RzAdjIntLeaseNew;
import com.imfbp.rz.domain.rzadjintleasenew.query.RzAdjIntLeaseNewQuery;
import com.imfbp.rz.dao.rzadjintleasenew.RzAdjIntLeaseNewDao;
import com.imfbp.rz.service.rzadjintleasenew.RzAdjIntLeaseNewService;





@Component("rzAdjIntLeaseNewService")
public class RzAdjIntLeaseNewServiceImpl implements RzAdjIntLeaseNewService{


	private RzAdjIntLeaseNewDao rzAdjIntLeaseNewDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzAdjIntLeaseNew
	 * @return
	 */
	@Override
	public void insertRzAdjIntLeaseNew(RzAdjIntLeaseNew rzAdjIntLeaseNew){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzAdjIntLeaseNew.setPkAdjIntLeaseNew(pk);
		rzAdjIntLeaseNewDao.insertRzAdjIntLeaseNew(rzAdjIntLeaseNew);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzAdjIntLeaseNew>
	 * @return
	 */
	public void insertBatchRzAdjIntLeaseNew(List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList){
		if(rzAdjIntLeaseNewList != null){
			for(int i=0;i<rzAdjIntLeaseNewList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzAdjIntLeaseNewList.get(i).setPkAdjIntLeaseNew(pk);
			}
			rzAdjIntLeaseNewDao.insertBatchRzAdjIntLeaseNew(rzAdjIntLeaseNewList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseNewById(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return rzAdjIntLeaseNewDao.deleteRzAdjIntLeaseNewById(rzAdjIntLeaseNewQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAdjIntLeaseNewByCondition(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return rzAdjIntLeaseNewDao.deleteRzAdjIntLeaseNewByCondition(rzAdjIntLeaseNewQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */	
	@Override
	public Result deleteRzAdjIntLeaseNewByBatchId(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzAdjIntLeaseNewQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzAdjIntLeaseNewDao.deleteRzAdjIntLeaseNewByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzAdjIntLeaseNew
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzAdjIntLeaseNew rzAdjIntLeaseNew) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzAdjIntLeaseNew!=null){
				if(StringUtil.isNotEmpty(rzAdjIntLeaseNew.getPkAdjIntLeaseNew())){
					updateRzAdjIntLeaseNewById(rzAdjIntLeaseNew);
				}else{
					insertRzAdjIntLeaseNew(rzAdjIntLeaseNew);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzAdjIntLeaseNew);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzAdjIntLeaseNew
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntLeaseNewById(RzAdjIntLeaseNew rzAdjIntLeaseNew){
		return rzAdjIntLeaseNewDao.updateRzAdjIntLeaseNewById(rzAdjIntLeaseNew);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAdjIntLeaseNewByCondition(RzAdjIntLeaseNewQuery record,RzAdjIntLeaseNewQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzAdjIntLeaseNewDao.updateRzAdjIntLeaseNewByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	public Result updateRzAdjIntLeaseNewByBatchId(List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList){
		Result result = new Result(false);
		try {
			boolean flag = rzAdjIntLeaseNewDao.updateRzAdjIntLeaseNewByBatchId(rzAdjIntLeaseNewList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public RzAdjIntLeaseNew getRzAdjIntLeaseNewById(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return rzAdjIntLeaseNewDao.getRzAdjIntLeaseNewById(rzAdjIntLeaseNewQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public List<RzAdjIntLeaseNew> getRzAdjIntLeaseNewAll(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return rzAdjIntLeaseNewDao.getRzAdjIntLeaseNewAll(rzAdjIntLeaseNewQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public GridResult<RzAdjIntLeaseNew> getRzAdjIntLeaseNewByPage(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		//如果排序的字段是空或者空字符串
		if(rzAdjIntLeaseNewQuery!=null&&StringUtils.isBlank(rzAdjIntLeaseNewQuery.getSort())){
			rzAdjIntLeaseNewQuery.setSort("seq_no");
			rzAdjIntLeaseNewQuery.setOrder("asc");;
		}
		int total = rzAdjIntLeaseNewDao.getRzAdjIntLeaseNewByPageCount(rzAdjIntLeaseNewQuery);
		PaginatedList<RzAdjIntLeaseNew> rzAdjIntLeaseNewPageList = new MysqlPaginatedArrayList<RzAdjIntLeaseNew>(rzAdjIntLeaseNewQuery,total);
		List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList = rzAdjIntLeaseNewDao.getRzAdjIntLeaseNewByPage(rzAdjIntLeaseNewQuery);
		rzAdjIntLeaseNewPageList.addAll(rzAdjIntLeaseNewList);
		GridResult<RzAdjIntLeaseNew> result = new GridResult<RzAdjIntLeaseNew>(rzAdjIntLeaseNewPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzAdjIntLeaseNewQuery
	 * @return
	 */
	@Override
	public int getRzAdjIntLeaseNewByPageCount(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery){
		return rzAdjIntLeaseNewDao.getRzAdjIntLeaseNewByPageCount(rzAdjIntLeaseNewQuery);
	}

	public void setRzAdjIntLeaseNewDao(RzAdjIntLeaseNewDao  rzAdjIntLeaseNewDao){
		this.rzAdjIntLeaseNewDao = rzAdjIntLeaseNewDao;
	}
	
}