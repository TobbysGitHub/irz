package com.imfbp.rz.service.rzprjcontrinseqpt.impl;

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

import com.imfbp.rz.domain.rzprjcontrinseqpt.RzPrjcontrInsEqpt;
import com.imfbp.rz.domain.rzprjcontrinseqpt.query.RzPrjcontrInsEqptQuery;
import com.imfbp.rz.dao.rzprjcontrinseqpt.RzPrjcontrInsEqptDao;
import com.imfbp.rz.service.rzprjcontrinseqpt.RzPrjcontrInsEqptService;





@Component("rzPrjcontrInsEqptService")
public class RzPrjcontrInsEqptServiceImpl implements RzPrjcontrInsEqptService{


	private RzPrjcontrInsEqptDao rzPrjcontrInsEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrInsEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrInsEqpt(RzPrjcontrInsEqpt rzPrjcontrInsEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrInsEqpt.setPkPrjcontrInsEqpt(pk);
		rzPrjcontrInsEqptDao.insertRzPrjcontrInsEqpt(rzPrjcontrInsEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrInsEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrInsEqpt(List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList){
		if(rzPrjcontrInsEqptList != null){
			for(int i=0;i<rzPrjcontrInsEqptList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrInsEqptList.get(i).setPkPrjcontrInsEqpt(pk);
			}
			rzPrjcontrInsEqptDao.insertBatchRzPrjcontrInsEqpt(rzPrjcontrInsEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsEqptById(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return rzPrjcontrInsEqptDao.deleteRzPrjcontrInsEqptById(rzPrjcontrInsEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrInsEqptByCondition(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return rzPrjcontrInsEqptDao.deleteRzPrjcontrInsEqptByCondition(rzPrjcontrInsEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrInsEqptByBatchId(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrInsEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrInsEqptDao.deleteRzPrjcontrInsEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrInsEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrInsEqpt rzPrjcontrInsEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrInsEqpt!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrInsEqpt.getPkPrjcontrInsEqpt())){
					updateRzPrjcontrInsEqptById(rzPrjcontrInsEqpt);
				}else{
					insertRzPrjcontrInsEqpt(rzPrjcontrInsEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrInsEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrInsEqpt
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrInsEqptById(RzPrjcontrInsEqpt rzPrjcontrInsEqpt){
		return rzPrjcontrInsEqptDao.updateRzPrjcontrInsEqptById(rzPrjcontrInsEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrInsEqptByCondition(RzPrjcontrInsEqptQuery record,RzPrjcontrInsEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrInsEqptDao.updateRzPrjcontrInsEqptByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	public Result updateRzPrjcontrInsEqptByBatchId(List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrInsEqptDao.updateRzPrjcontrInsEqptByBatchId(rzPrjcontrInsEqptList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrInsEqpt getRzPrjcontrInsEqptById(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return rzPrjcontrInsEqptDao.getRzPrjcontrInsEqptById(rzPrjcontrInsEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrInsEqpt> getRzPrjcontrInsEqptAll(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return rzPrjcontrInsEqptDao.getRzPrjcontrInsEqptAll(rzPrjcontrInsEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrInsEqpt> getRzPrjcontrInsEqptByPage(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrInsEqptQuery!=null&&StringUtils.isBlank(rzPrjcontrInsEqptQuery.getSort())){
			rzPrjcontrInsEqptQuery.setSort("pk_prjcontr_ins_eqpt");
			rzPrjcontrInsEqptQuery.setOrder("desc");;
		}
		int total = rzPrjcontrInsEqptDao.getRzPrjcontrInsEqptByPageCount(rzPrjcontrInsEqptQuery);
		PaginatedList<RzPrjcontrInsEqpt> rzPrjcontrInsEqptPageList = new MysqlPaginatedArrayList<RzPrjcontrInsEqpt>(rzPrjcontrInsEqptQuery,total);
		List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList = rzPrjcontrInsEqptDao.getRzPrjcontrInsEqptByPage(rzPrjcontrInsEqptQuery);
		rzPrjcontrInsEqptPageList.addAll(rzPrjcontrInsEqptList);
		GridResult<RzPrjcontrInsEqpt> result = new GridResult<RzPrjcontrInsEqpt>(rzPrjcontrInsEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrInsEqptQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrInsEqptByPageCount(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery){
		return rzPrjcontrInsEqptDao.getRzPrjcontrInsEqptByPageCount(rzPrjcontrInsEqptQuery);
	}

	public void setRzPrjcontrInsEqptDao(RzPrjcontrInsEqptDao  rzPrjcontrInsEqptDao){
		this.rzPrjcontrInsEqptDao = rzPrjcontrInsEqptDao;
	}
	
}