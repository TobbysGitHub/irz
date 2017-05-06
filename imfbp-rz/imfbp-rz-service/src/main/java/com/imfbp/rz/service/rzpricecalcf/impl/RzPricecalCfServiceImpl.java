package com.imfbp.rz.service.rzpricecalcf.impl;

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
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.dao.rzpricecalcf.RzPricecalCfDao;
import com.imfbp.rz.service.rzpricecalcf.RzPricecalCfService;





@Component("rzPricecalCfService")
public class RzPricecalCfServiceImpl implements RzPricecalCfService{


	private RzPricecalCfDao rzPricecalCfDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPricecalCf
	 * @return
	 */
	@Override
	public void insertRzPricecalCf(RzPricecalCf rzPricecalCf){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPricecalCf.setPkPricecalCf(pk);
		rzPricecalCfDao.insertRzPricecalCf(rzPricecalCf);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPricecalCf>
	 * @return
	 */
	public void insertBatchRzPricecalCf(List<RzPricecalCf> rzPricecalCfList){
		if(rzPricecalCfList != null){
			for(int i=0;i<rzPricecalCfList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPricecalCfList.get(i).setPkPricecalCf(pk);
			}
			rzPricecalCfDao.insertBatchRzPricecalCf(rzPricecalCfList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalCfById(RzPricecalCfQuery rzPricecalCfQuery){
		return rzPricecalCfDao.deleteRzPricecalCfById(rzPricecalCfQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPricecalCfByCondition(RzPricecalCfQuery rzPricecalCfQuery){
		return rzPricecalCfDao.deleteRzPricecalCfByCondition(rzPricecalCfQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPricecalCfQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPricecalCfByBatchId(RzPricecalCfQuery rzPricecalCfQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPricecalCfQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPricecalCfDao.deleteRzPricecalCfByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPricecalCf
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPricecalCf rzPricecalCf) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPricecalCf!=null){
				if(StringUtil.isNotEmpty(rzPricecalCf.getPkPricecalCf())){
					updateRzPricecalCfById(rzPricecalCf);
				}else{
					insertRzPricecalCf(rzPricecalCf);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPricecalCf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPricecalCf
	 * @return
	 */
	@Override
	public boolean updateRzPricecalCfById(RzPricecalCf rzPricecalCf){
		return rzPricecalCfDao.updateRzPricecalCfById(rzPricecalCf);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPricecalCfByCondition(RzPricecalCfQuery record,RzPricecalCfQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPricecalCfDao.updateRzPricecalCfByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public RzPricecalCf getRzPricecalCfById(RzPricecalCfQuery rzPricecalCfQuery){
		return rzPricecalCfDao.getRzPricecalCfById(rzPricecalCfQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public List<RzPricecalCf> getRzPricecalCfAll(RzPricecalCfQuery rzPricecalCfQuery){
		return rzPricecalCfDao.getRzPricecalCfAll(rzPricecalCfQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public GridResult<RzPricecalCf> getRzPricecalCfByPage(RzPricecalCfQuery rzPricecalCfQuery){
		//如果排序的字段是空或者空字符串
		if(rzPricecalCfQuery!=null&&StringUtils.isBlank(rzPricecalCfQuery.getSort())){
			rzPricecalCfQuery.setSort("seq_no");
			rzPricecalCfQuery.setOrder("asc");;
		}
		int total = rzPricecalCfDao.getRzPricecalCfByPageCount(rzPricecalCfQuery);
		PaginatedList<RzPricecalCf> rzPricecalCfPageList = new MysqlPaginatedArrayList<RzPricecalCf>(rzPricecalCfQuery,total);
		List<RzPricecalCf> rzPricecalCfList = rzPricecalCfDao.getRzPricecalCfByPage(rzPricecalCfQuery);
		rzPricecalCfPageList.addAll(rzPricecalCfList);
		GridResult<RzPricecalCf> result = new GridResult<RzPricecalCf>(rzPricecalCfPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPricecalCfQuery
	 * @return
	 */
	@Override
	public int getRzPricecalCfByPageCount(RzPricecalCfQuery rzPricecalCfQuery){
		return rzPricecalCfDao.getRzPricecalCfByPageCount(rzPricecalCfQuery);
	}

	public void setRzPricecalCfDao(RzPricecalCfDao  rzPricecalCfDao){
		this.rzPricecalCfDao = rzPricecalCfDao;
	}

	@Override
	public Result updateByBatchId(List<RzPricecalCf> list) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			data.put("list",list);
			boolean flag = rzPricecalCfDao.updateByBatchId(data);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}