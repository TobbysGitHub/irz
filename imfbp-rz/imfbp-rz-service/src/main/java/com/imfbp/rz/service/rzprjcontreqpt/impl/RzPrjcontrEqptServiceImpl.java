package com.imfbp.rz.service.rzprjcontreqpt.impl;

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

import com.imfbp.rz.domain.rzprjcontreqpt.RzPrjcontrEqpt;
import com.imfbp.rz.domain.rzprjcontreqpt.query.RzPrjcontrEqptQuery;
import com.imfbp.rz.dao.rzprjcontreqpt.RzPrjcontrEqptDao;
import com.imfbp.rz.service.rzprjcontreqpt.RzPrjcontrEqptService;





@Component("rzPrjcontrEqptService")
public class RzPrjcontrEqptServiceImpl implements RzPrjcontrEqptService{


	private RzPrjcontrEqptDao rzPrjcontrEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrEqpt(RzPrjcontrEqpt rzPrjcontrEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrEqpt.setPkPrjcontrEqpt(pk);
		rzPrjcontrEqptDao.insertRzPrjcontrEqpt(rzPrjcontrEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrEqpt(List<RzPrjcontrEqpt> rzPrjcontrEqptList){
		if(rzPrjcontrEqptList != null){
			for(int i=0;i<rzPrjcontrEqptList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrEqptList.get(i).setPkPrjcontrEqpt(pk);
			}
			rzPrjcontrEqptDao.insertBatchRzPrjcontrEqpt(rzPrjcontrEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptById(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return rzPrjcontrEqptDao.deleteRzPrjcontrEqptById(rzPrjcontrEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrEqptByCondition(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return rzPrjcontrEqptDao.deleteRzPrjcontrEqptByCondition(rzPrjcontrEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrEqptByBatchId(RzPrjcontrEqptQuery rzPrjcontrEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrEqptDao.deleteRzPrjcontrEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrEqpt rzPrjcontrEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrEqpt!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrEqpt.getPkPrjcontrEqpt())){
					updateRzPrjcontrEqptById(rzPrjcontrEqpt);
				}else{
					insertRzPrjcontrEqpt(rzPrjcontrEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrEqpt
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrEqptById(RzPrjcontrEqpt rzPrjcontrEqpt){
		return rzPrjcontrEqptDao.updateRzPrjcontrEqptById(rzPrjcontrEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrEqptByCondition(RzPrjcontrEqptQuery record,RzPrjcontrEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrEqptDao.updateRzPrjcontrEqptByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	public Result updateRzPrjcontrEqptByBatchId(List<RzPrjcontrEqpt> rzPrjcontrEqptList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrEqptDao.updateRzPrjcontrEqptByBatchId(rzPrjcontrEqptList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrEqpt getRzPrjcontrEqptById(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return rzPrjcontrEqptDao.getRzPrjcontrEqptById(rzPrjcontrEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrEqpt> getRzPrjcontrEqptAll(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return rzPrjcontrEqptDao.getRzPrjcontrEqptAll(rzPrjcontrEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrEqpt> getRzPrjcontrEqptByPage(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrEqptQuery!=null&&StringUtils.isBlank(rzPrjcontrEqptQuery.getSort())){
			rzPrjcontrEqptQuery.setSort("pk_prjcontr_eqpt");
			rzPrjcontrEqptQuery.setOrder("desc");;
		}
		int total = rzPrjcontrEqptDao.getRzPrjcontrEqptByPageCount(rzPrjcontrEqptQuery);
		PaginatedList<RzPrjcontrEqpt> rzPrjcontrEqptPageList = new MysqlPaginatedArrayList<RzPrjcontrEqpt>(rzPrjcontrEqptQuery,total);
		List<RzPrjcontrEqpt> rzPrjcontrEqptList = rzPrjcontrEqptDao.getRzPrjcontrEqptByPage(rzPrjcontrEqptQuery);
		rzPrjcontrEqptPageList.addAll(rzPrjcontrEqptList);
		GridResult<RzPrjcontrEqpt> result = new GridResult<RzPrjcontrEqpt>(rzPrjcontrEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrEqptQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrEqptByPageCount(RzPrjcontrEqptQuery rzPrjcontrEqptQuery){
		return rzPrjcontrEqptDao.getRzPrjcontrEqptByPageCount(rzPrjcontrEqptQuery);
	}

	public void setRzPrjcontrEqptDao(RzPrjcontrEqptDao  rzPrjcontrEqptDao){
		this.rzPrjcontrEqptDao = rzPrjcontrEqptDao;
	}
	
}