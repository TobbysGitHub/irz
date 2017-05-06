package com.imfbp.rz.service.rzprjapplyeqpt.impl;

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

import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;
import com.imfbp.rz.dao.rzprjapplyeqpt.RzPrjapplyEqptDao;
import com.imfbp.rz.service.rzprjapplyeqpt.RzPrjapplyEqptService;





@Component("rzPrjapplyEqptService")
public class RzPrjapplyEqptServiceImpl implements RzPrjapplyEqptService{


	private RzPrjapplyEqptDao rzPrjapplyEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjapplyEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjapplyEqpt(RzPrjapplyEqpt rzPrjapplyEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjapplyEqpt.setPkPrjapplyEqpt(pk);
		rzPrjapplyEqptDao.insertRzPrjapplyEqpt(rzPrjapplyEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjapplyEqpt>
	 * @return
	 */
	public void insertBatchRzPrjapplyEqpt(List<RzPrjapplyEqpt> rzPrjapplyEqptList){
		if(rzPrjapplyEqptList != null){
			for(int i=0;i<rzPrjapplyEqptList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjapplyEqptList.get(i).setPkPrjapplyEqpt(pk);
			}
			rzPrjapplyEqptDao.insertBatchRzPrjapplyEqpt(rzPrjapplyEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return rzPrjapplyEqptDao.deleteRzPrjapplyEqptById(rzPrjapplyEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjapplyEqptByCondition(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return rzPrjapplyEqptDao.deleteRzPrjapplyEqptByCondition(rzPrjapplyEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjapplyEqptByBatchId(RzPrjapplyEqptQuery rzPrjapplyEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjapplyEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjapplyEqptDao.deleteRzPrjapplyEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjapplyEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjapplyEqpt rzPrjapplyEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjapplyEqpt!=null){
				if(StringUtil.isNotEmpty(rzPrjapplyEqpt.getPkPrjapplyEqpt())){
					updateRzPrjapplyEqptById(rzPrjapplyEqpt);
				}else{
					insertRzPrjapplyEqpt(rzPrjapplyEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjapplyEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjapplyEqpt
	 * @return
	 */
	@Override
	public boolean updateRzPrjapplyEqptById(RzPrjapplyEqpt rzPrjapplyEqpt){
		return rzPrjapplyEqptDao.updateRzPrjapplyEqptById(rzPrjapplyEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjapplyEqptByCondition(RzPrjapplyEqptQuery record,RzPrjapplyEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjapplyEqptDao.updateRzPrjapplyEqptByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public RzPrjapplyEqpt getRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return rzPrjapplyEqptDao.getRzPrjapplyEqptById(rzPrjapplyEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjapplyEqpt> getRzPrjapplyEqptAll(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return rzPrjapplyEqptDao.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjapplyEqpt> getRzPrjapplyEqptByPage(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjapplyEqptQuery!=null&&StringUtils.isBlank(rzPrjapplyEqptQuery.getSort())){
			rzPrjapplyEqptQuery.setSort("pk_prjapply_eqpt");
			rzPrjapplyEqptQuery.setOrder("desc");;
		}
		int total = rzPrjapplyEqptDao.getRzPrjapplyEqptByPageCount(rzPrjapplyEqptQuery);
		PaginatedList<RzPrjapplyEqpt> rzPrjapplyEqptPageList = new MysqlPaginatedArrayList<RzPrjapplyEqpt>(rzPrjapplyEqptQuery,total);
		List<RzPrjapplyEqpt> rzPrjapplyEqptList = rzPrjapplyEqptDao.getRzPrjapplyEqptByPage(rzPrjapplyEqptQuery);
		rzPrjapplyEqptPageList.addAll(rzPrjapplyEqptList);
		GridResult<RzPrjapplyEqpt> result = new GridResult<RzPrjapplyEqpt>(rzPrjapplyEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjapplyEqptQuery
	 * @return
	 */
	@Override
	public int getRzPrjapplyEqptByPageCount(RzPrjapplyEqptQuery rzPrjapplyEqptQuery){
		return rzPrjapplyEqptDao.getRzPrjapplyEqptByPageCount(rzPrjapplyEqptQuery);
	}

	public void setRzPrjapplyEqptDao(RzPrjapplyEqptDao  rzPrjapplyEqptDao){
		this.rzPrjapplyEqptDao = rzPrjapplyEqptDao;
	}
	
}