package com.imfbp.rz.service.rzprjrevieweqpt.impl;

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

import com.imfbp.rz.domain.rzprjrevieweqpt.RzPrjreviewEqpt;
import com.imfbp.rz.domain.rzprjrevieweqpt.query.RzPrjreviewEqptQuery;
import com.imfbp.rz.dao.rzprjrevieweqpt.RzPrjreviewEqptDao;
import com.imfbp.rz.service.rzprjrevieweqpt.RzPrjreviewEqptService;





@Component("rzPrjreviewEqptService")
public class RzPrjreviewEqptServiceImpl implements RzPrjreviewEqptService{


	private RzPrjreviewEqptDao rzPrjreviewEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjreviewEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjreviewEqpt(RzPrjreviewEqpt rzPrjreviewEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjreviewEqpt.setPkPrjreviewEqpt(pk);
		rzPrjreviewEqptDao.insertRzPrjreviewEqpt(rzPrjreviewEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewEqpt>
	 * @return
	 */
	public void insertBatchRzPrjreviewEqpt(List<RzPrjreviewEqpt> rzPrjreviewEqptList){
		if(rzPrjreviewEqptList != null){
			for(int i=0;i<rzPrjreviewEqptList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjreviewEqptList.get(i).setPkPrjreviewEqpt(pk);
			}
			rzPrjreviewEqptDao.insertBatchRzPrjreviewEqpt(rzPrjreviewEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return rzPrjreviewEqptDao.deleteRzPrjreviewEqptById(rzPrjreviewEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewEqptByCondition(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return rzPrjreviewEqptDao.deleteRzPrjreviewEqptByCondition(rzPrjreviewEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjreviewEqptByBatchId(RzPrjreviewEqptQuery rzPrjreviewEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjreviewEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjreviewEqptDao.deleteRzPrjreviewEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjreviewEqpt rzPrjreviewEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjreviewEqpt!=null){
				if(StringUtil.isNotEmpty(rzPrjreviewEqpt.getPkPrjreviewEqpt())){
					updateRzPrjreviewEqptById(rzPrjreviewEqpt);
				}else{
					insertRzPrjreviewEqpt(rzPrjreviewEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjreviewEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewEqpt
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewEqptById(RzPrjreviewEqpt rzPrjreviewEqpt){
		return rzPrjreviewEqptDao.updateRzPrjreviewEqptById(rzPrjreviewEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewEqptByCondition(RzPrjreviewEqptQuery record,RzPrjreviewEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjreviewEqptDao.updateRzPrjreviewEqptByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public RzPrjreviewEqpt getRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return rzPrjreviewEqptDao.getRzPrjreviewEqptById(rzPrjreviewEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewEqpt> getRzPrjreviewEqptAll(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return rzPrjreviewEqptDao.getRzPrjreviewEqptAll(rzPrjreviewEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjreviewEqpt> getRzPrjreviewEqptByPage(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjreviewEqptQuery!=null&&StringUtils.isBlank(rzPrjreviewEqptQuery.getSort())){
			rzPrjreviewEqptQuery.setSort("pk_prjreview_eqpt");
			rzPrjreviewEqptQuery.setOrder("desc");;
		}
		int total = rzPrjreviewEqptDao.getRzPrjreviewEqptByPageCount(rzPrjreviewEqptQuery);
		PaginatedList<RzPrjreviewEqpt> rzPrjreviewEqptPageList = new MysqlPaginatedArrayList<RzPrjreviewEqpt>(rzPrjreviewEqptQuery,total);
		List<RzPrjreviewEqpt> rzPrjreviewEqptList = rzPrjreviewEqptDao.getRzPrjreviewEqptByPage(rzPrjreviewEqptQuery);
		rzPrjreviewEqptPageList.addAll(rzPrjreviewEqptList);
		GridResult<RzPrjreviewEqpt> result = new GridResult<RzPrjreviewEqpt>(rzPrjreviewEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewEqptQuery
	 * @return
	 */
	@Override
	public int getRzPrjreviewEqptByPageCount(RzPrjreviewEqptQuery rzPrjreviewEqptQuery){
		return rzPrjreviewEqptDao.getRzPrjreviewEqptByPageCount(rzPrjreviewEqptQuery);
	}

	public void setRzPrjreviewEqptDao(RzPrjreviewEqptDao  rzPrjreviewEqptDao){
		this.rzPrjreviewEqptDao = rzPrjreviewEqptDao;
	}
	
}