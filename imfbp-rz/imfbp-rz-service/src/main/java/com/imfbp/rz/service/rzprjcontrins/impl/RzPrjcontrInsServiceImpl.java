package com.imfbp.rz.service.rzprjcontrins.impl;

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

import com.imfbp.rz.domain.rzprjcontrins.RzPrjcontrIns;
import com.imfbp.rz.domain.rzprjcontrins.query.RzPrjcontrInsQuery;
import com.imfbp.rz.dao.rzprjcontrins.RzPrjcontrInsDao;
import com.imfbp.rz.service.rzprjcontrins.RzPrjcontrInsService;





@Component("rzPrjcontrInsService")
public class RzPrjcontrInsServiceImpl implements RzPrjcontrInsService{


	private RzPrjcontrInsDao rzPrjcontrInsDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrIns
	 * @return
	 */
	@Override
	public void insertRzPrjcontrIns(RzPrjcontrIns rzPrjcontrIns){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrIns.setPkPrjcontrIns(pk);
		rzPrjcontrInsDao.insertRzPrjcontrIns(rzPrjcontrIns);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrIns(List<RzPrjcontrIns> rzPrjcontrInsList){
		if(rzPrjcontrInsList != null){
			for(int i=0;i<rzPrjcontrInsList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrInsList.get(i).setPkPrjcontrIns(pk);
			}
			rzPrjcontrInsDao.insertBatchRzPrjcontrIns(rzPrjcontrInsList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return rzPrjcontrInsDao.deleteRzPrjcontrInsById(rzPrjcontrInsQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsByCondition(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return rzPrjcontrInsDao.deleteRzPrjcontrInsByCondition(rzPrjcontrInsQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrInsByBatchId(RzPrjcontrInsQuery rzPrjcontrInsQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrInsQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrInsDao.deleteRzPrjcontrInsByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrIns
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrIns rzPrjcontrIns) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrIns!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrIns.getPkPrjcontrIns())){
					updateRzPrjcontrInsById(rzPrjcontrIns);
				}else{
					insertRzPrjcontrIns(rzPrjcontrIns);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrIns);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrIns
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrInsById(RzPrjcontrIns rzPrjcontrIns){
		return rzPrjcontrInsDao.updateRzPrjcontrInsById(rzPrjcontrIns);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrInsByCondition(RzPrjcontrInsQuery record,RzPrjcontrInsQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrInsDao.updateRzPrjcontrInsByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	public Result updateRzPrjcontrInsByBatchId(List<RzPrjcontrIns> rzPrjcontrInsList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrInsDao.updateRzPrjcontrInsByBatchId(rzPrjcontrInsList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public RzPrjcontrIns getRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return rzPrjcontrInsDao.getRzPrjcontrInsById(rzPrjcontrInsQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrIns> getRzPrjcontrInsAll(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return rzPrjcontrInsDao.getRzPrjcontrInsAll(rzPrjcontrInsQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrIns> getRzPrjcontrInsByPage(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrInsQuery!=null&&StringUtils.isBlank(rzPrjcontrInsQuery.getSort())){
			rzPrjcontrInsQuery.setSort("pk_prjcontr_ins");
			rzPrjcontrInsQuery.setOrder("desc");;
		}
		int total = rzPrjcontrInsDao.getRzPrjcontrInsByPageCount(rzPrjcontrInsQuery);
		PaginatedList<RzPrjcontrIns> rzPrjcontrInsPageList = new MysqlPaginatedArrayList<RzPrjcontrIns>(rzPrjcontrInsQuery,total);
		List<RzPrjcontrIns> rzPrjcontrInsList = rzPrjcontrInsDao.getRzPrjcontrInsByPage(rzPrjcontrInsQuery);
		rzPrjcontrInsPageList.addAll(rzPrjcontrInsList);
		GridResult<RzPrjcontrIns> result = new GridResult<RzPrjcontrIns>(rzPrjcontrInsPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrInsQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrInsByPageCount(RzPrjcontrInsQuery rzPrjcontrInsQuery){
		return rzPrjcontrInsDao.getRzPrjcontrInsByPageCount(rzPrjcontrInsQuery);
	}

	public void setRzPrjcontrInsDao(RzPrjcontrInsDao  rzPrjcontrInsDao){
		this.rzPrjcontrInsDao = rzPrjcontrInsDao;
	}
	
}