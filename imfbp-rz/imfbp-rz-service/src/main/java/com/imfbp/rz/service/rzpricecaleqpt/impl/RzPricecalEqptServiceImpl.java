package com.imfbp.rz.service.rzpricecaleqpt.impl;

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
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;
import com.imfbp.rz.dao.rzpricecaleqpt.RzPricecalEqptDao;
import com.imfbp.rz.service.rzpricecaleqpt.RzPricecalEqptService;





@Component("rzPricecalEqptService")
public class RzPricecalEqptServiceImpl implements RzPricecalEqptService{


	private RzPricecalEqptDao rzPricecalEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPricecalEqpt
	 * @return
	 */
	@Override
	public void insertRzPricecalEqpt(RzPricecalEqpt rzPricecalEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPricecalEqpt.setPkPricecalEqpt(pk);
		rzPricecalEqptDao.insertRzPricecalEqpt(rzPricecalEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecalEqpt>
	 * @return
	 */
	public void insertBatchRzPricecalEqpt(List<RzPricecalEqpt> rzPricecalEqptList){
		if(rzPricecalEqptList != null){
			for(int i=0;i<rzPricecalEqptList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPricecalEqptList.get(i).setPkPricecalEqpt(pk);
			}
			rzPricecalEqptDao.insertBatchRzPricecalEqpt(rzPricecalEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery){
		return rzPricecalEqptDao.deleteRzPricecalEqptById(rzPricecalEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalEqptByCondition(RzPricecalEqptQuery rzPricecalEqptQuery){
		return rzPricecalEqptDao.deleteRzPricecalEqptByCondition(rzPricecalEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPricecalEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPricecalEqptByBatchId(RzPricecalEqptQuery rzPricecalEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPricecalEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPricecalEqptDao.deleteRzPricecalEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPricecalEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPricecalEqpt rzPricecalEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPricecalEqpt!=null){
				if(StringUtil.isNotEmpty(rzPricecalEqpt.getPkPricecalEqpt())){
					updateRzPricecalEqptById(rzPricecalEqpt);
				}else{
					insertRzPricecalEqpt(rzPricecalEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPricecalEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPricecalEqpt
	 * @return
	 */
	@Override
	public boolean updateRzPricecalEqptById(RzPricecalEqpt rzPricecalEqpt){
		return rzPricecalEqptDao.updateRzPricecalEqptById(rzPricecalEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalEqptByCondition(RzPricecalEqptQuery record,RzPricecalEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPricecalEqptDao.updateRzPricecalEqptByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public RzPricecalEqpt getRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery){
		return rzPricecalEqptDao.getRzPricecalEqptById(rzPricecalEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public List<RzPricecalEqpt> getRzPricecalEqptAll(RzPricecalEqptQuery rzPricecalEqptQuery){
		return rzPricecalEqptDao.getRzPricecalEqptAll(rzPricecalEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzPricecalEqpt> getRzPricecalEqptByPage(RzPricecalEqptQuery rzPricecalEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzPricecalEqptQuery!=null&&StringUtils.isBlank(rzPricecalEqptQuery.getSort())){
			rzPricecalEqptQuery.setSort("pk_pricecal_eqpt");
			rzPricecalEqptQuery.setOrder("desc");;
		}
		int total = rzPricecalEqptDao.getRzPricecalEqptByPageCount(rzPricecalEqptQuery);
		PaginatedList<RzPricecalEqpt> rzPricecalEqptPageList = new MysqlPaginatedArrayList<RzPricecalEqpt>(rzPricecalEqptQuery,total);
		List<RzPricecalEqpt> rzPricecalEqptList = rzPricecalEqptDao.getRzPricecalEqptByPage(rzPricecalEqptQuery);
		rzPricecalEqptPageList.addAll(rzPricecalEqptList);
		GridResult<RzPricecalEqpt> result = new GridResult<RzPricecalEqpt>(rzPricecalEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalEqptQuery
	 * @return
	 */
	@Override
	public int getRzPricecalEqptByPageCount(RzPricecalEqptQuery rzPricecalEqptQuery){
		return rzPricecalEqptDao.getRzPricecalEqptByPageCount(rzPricecalEqptQuery);
	}

	public void setRzPricecalEqptDao(RzPricecalEqptDao  rzPricecalEqptDao){
		this.rzPricecalEqptDao = rzPricecalEqptDao;
	}

	@Override
	public Result updateByBatchId(List<RzPricecalEqpt> list) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			data.put("list",list);
			boolean flag = rzPricecalEqptDao.updateByBatchId(data);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}