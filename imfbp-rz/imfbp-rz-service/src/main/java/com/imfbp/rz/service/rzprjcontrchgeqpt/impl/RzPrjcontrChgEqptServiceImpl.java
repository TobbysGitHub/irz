package com.imfbp.rz.service.rzprjcontrchgeqpt.impl;

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

import com.imfbp.rz.domain.rzprjcontrchgeqpt.RzPrjcontrChgEqpt;
import com.imfbp.rz.domain.rzprjcontrchgeqpt.query.RzPrjcontrChgEqptQuery;
import com.imfbp.rz.dao.rzprjcontrchgeqpt.RzPrjcontrChgEqptDao;
import com.imfbp.rz.service.rzprjcontrchgeqpt.RzPrjcontrChgEqptService;





@Component("rzPrjcontrChgEqptService")
public class RzPrjcontrChgEqptServiceImpl implements RzPrjcontrChgEqptService{


	private RzPrjcontrChgEqptDao rzPrjcontrChgEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrChgEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgEqpt(RzPrjcontrChgEqpt rzPrjcontrChgEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgEqpt.setPkPrjcontrChgEqpt(pk);
		rzPrjcontrChgEqptDao.insertRzPrjcontrChgEqpt(rzPrjcontrChgEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgEqpt(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList){
		if(rzPrjcontrChgEqptList != null){
			for(int i=0;i<rzPrjcontrChgEqptList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgEqptList.get(i).setPkPrjcontrChgEqpt(pk);
			}
			rzPrjcontrChgEqptDao.insertBatchRzPrjcontrChgEqpt(rzPrjcontrChgEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return rzPrjcontrChgEqptDao.deleteRzPrjcontrChgEqptById(rzPrjcontrChgEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgEqptByCondition(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return rzPrjcontrChgEqptDao.deleteRzPrjcontrChgEqptByCondition(rzPrjcontrChgEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgEqptByBatchId(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgEqptDao.deleteRzPrjcontrChgEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgEqpt rzPrjcontrChgEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgEqpt!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgEqpt.getPkPrjcontrChgEqpt())){
					updateRzPrjcontrChgEqptById(rzPrjcontrChgEqpt);
				}else{
					insertRzPrjcontrChgEqpt(rzPrjcontrChgEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgEqpt
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgEqptById(RzPrjcontrChgEqpt rzPrjcontrChgEqpt){
		return rzPrjcontrChgEqptDao.updateRzPrjcontrChgEqptById(rzPrjcontrChgEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgEqptByCondition(RzPrjcontrChgEqptQuery record,RzPrjcontrChgEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgEqptDao.updateRzPrjcontrChgEqptByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgEqptByBatchId(List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgEqptDao.updateRzPrjcontrChgEqptByBatchId(rzPrjcontrChgEqptList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgEqpt getRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return rzPrjcontrChgEqptDao.getRzPrjcontrChgEqptById(rzPrjcontrChgEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptAll(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return rzPrjcontrChgEqptDao.getRzPrjcontrChgEqptAll(rzPrjcontrChgEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgEqpt> getRzPrjcontrChgEqptByPage(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgEqptQuery!=null&&StringUtils.isBlank(rzPrjcontrChgEqptQuery.getSort())){
			rzPrjcontrChgEqptQuery.setSort("pk_prjcontr_chg_eqpt");
			rzPrjcontrChgEqptQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgEqptDao.getRzPrjcontrChgEqptByPageCount(rzPrjcontrChgEqptQuery);
		PaginatedList<RzPrjcontrChgEqpt> rzPrjcontrChgEqptPageList = new MysqlPaginatedArrayList<RzPrjcontrChgEqpt>(rzPrjcontrChgEqptQuery,total);
		List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList = rzPrjcontrChgEqptDao.getRzPrjcontrChgEqptByPage(rzPrjcontrChgEqptQuery);
		rzPrjcontrChgEqptPageList.addAll(rzPrjcontrChgEqptList);
		GridResult<RzPrjcontrChgEqpt> result = new GridResult<RzPrjcontrChgEqpt>(rzPrjcontrChgEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgEqptQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgEqptByPageCount(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery){
		return rzPrjcontrChgEqptDao.getRzPrjcontrChgEqptByPageCount(rzPrjcontrChgEqptQuery);
	}

	public void setRzPrjcontrChgEqptDao(RzPrjcontrChgEqptDao  rzPrjcontrChgEqptDao){
		this.rzPrjcontrChgEqptDao = rzPrjcontrChgEqptDao;
	}
	
}