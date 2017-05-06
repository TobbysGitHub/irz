package com.imfbp.rz.service.rzprjcontrchgins.impl;

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

import com.imfbp.rz.domain.rzprjcontrchgins.RzPrjcontrChgIns;
import com.imfbp.rz.domain.rzprjcontrchgins.query.RzPrjcontrChgInsQuery;
import com.imfbp.rz.dao.rzprjcontrchgins.RzPrjcontrChgInsDao;
import com.imfbp.rz.service.rzprjcontrchgins.RzPrjcontrChgInsService;





@Component("rzPrjcontrChgInsService")
public class RzPrjcontrChgInsServiceImpl implements RzPrjcontrChgInsService{


	private RzPrjcontrChgInsDao rzPrjcontrChgInsDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrChgIns
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgIns(RzPrjcontrChgIns rzPrjcontrChgIns){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgIns.setPkPrjcontrChgIns(pk);
		rzPrjcontrChgInsDao.insertRzPrjcontrChgIns(rzPrjcontrChgIns);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgIns>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgIns(List<RzPrjcontrChgIns> rzPrjcontrChgInsList){
		if(rzPrjcontrChgInsList != null){
			for(int i=0;i<rzPrjcontrChgInsList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgInsList.get(i).setPkPrjcontrChgIns(pk);
			}
			rzPrjcontrChgInsDao.insertBatchRzPrjcontrChgIns(rzPrjcontrChgInsList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return rzPrjcontrChgInsDao.deleteRzPrjcontrChgInsById(rzPrjcontrChgInsQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsByCondition(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return rzPrjcontrChgInsDao.deleteRzPrjcontrChgInsByCondition(rzPrjcontrChgInsQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgInsByBatchId(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgInsQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgInsDao.deleteRzPrjcontrChgInsByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgIns
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgIns rzPrjcontrChgIns) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgIns!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgIns.getPkPrjcontrChgIns())){
					updateRzPrjcontrChgInsById(rzPrjcontrChgIns);
				}else{
					insertRzPrjcontrChgIns(rzPrjcontrChgIns);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgIns);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgIns
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgInsById(RzPrjcontrChgIns rzPrjcontrChgIns){
		return rzPrjcontrChgInsDao.updateRzPrjcontrChgInsById(rzPrjcontrChgIns);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgInsByCondition(RzPrjcontrChgInsQuery record,RzPrjcontrChgInsQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgInsDao.updateRzPrjcontrChgInsByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgInsByBatchId(List<RzPrjcontrChgIns> rzPrjcontrChgInsList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgInsDao.updateRzPrjcontrChgInsByBatchId(rzPrjcontrChgInsList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgIns getRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return rzPrjcontrChgInsDao.getRzPrjcontrChgInsById(rzPrjcontrChgInsQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgIns> getRzPrjcontrChgInsAll(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return rzPrjcontrChgInsDao.getRzPrjcontrChgInsAll(rzPrjcontrChgInsQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgIns> getRzPrjcontrChgInsByPage(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgInsQuery!=null&&StringUtils.isBlank(rzPrjcontrChgInsQuery.getSort())){
			rzPrjcontrChgInsQuery.setSort("pk_prjcontr_chg_ins");
			rzPrjcontrChgInsQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgInsDao.getRzPrjcontrChgInsByPageCount(rzPrjcontrChgInsQuery);
		PaginatedList<RzPrjcontrChgIns> rzPrjcontrChgInsPageList = new MysqlPaginatedArrayList<RzPrjcontrChgIns>(rzPrjcontrChgInsQuery,total);
		List<RzPrjcontrChgIns> rzPrjcontrChgInsList = rzPrjcontrChgInsDao.getRzPrjcontrChgInsByPage(rzPrjcontrChgInsQuery);
		rzPrjcontrChgInsPageList.addAll(rzPrjcontrChgInsList);
		GridResult<RzPrjcontrChgIns> result = new GridResult<RzPrjcontrChgIns>(rzPrjcontrChgInsPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgInsQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgInsByPageCount(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery){
		return rzPrjcontrChgInsDao.getRzPrjcontrChgInsByPageCount(rzPrjcontrChgInsQuery);
	}

	public void setRzPrjcontrChgInsDao(RzPrjcontrChgInsDao  rzPrjcontrChgInsDao){
		this.rzPrjcontrChgInsDao = rzPrjcontrChgInsDao;
	}
	
}