package com.imfbp.rz.service.rzprjcontrchginseqpt.impl;

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

import com.imfbp.rz.domain.rzprjcontrchginseqpt.RzPrjcontrChgInsEqpt;
import com.imfbp.rz.domain.rzprjcontrchginseqpt.query.RzPrjcontrChgInsEqptQuery;
import com.imfbp.rz.dao.rzprjcontrchginseqpt.RzPrjcontrChgInsEqptDao;
import com.imfbp.rz.service.rzprjcontrchginseqpt.RzPrjcontrChgInsEqptService;





@Component("rzPrjcontrChgInsEqptService")
public class RzPrjcontrChgInsEqptServiceImpl implements RzPrjcontrChgInsEqptService{


	private RzPrjcontrChgInsEqptDao rzPrjcontrChgInsEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrChgInsEqpt
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgInsEqpt(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgInsEqpt.setPkPrjcontrChgInsEqpt(pk);
		rzPrjcontrChgInsEqptDao.insertRzPrjcontrChgInsEqpt(rzPrjcontrChgInsEqpt);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgInsEqpt>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgInsEqpt(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList){
		if(rzPrjcontrChgInsEqptList != null){
			for(int i=0;i<rzPrjcontrChgInsEqptList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgInsEqptList.get(i).setPkPrjcontrChgInsEqpt(pk);
			}
			rzPrjcontrChgInsEqptDao.insertBatchRzPrjcontrChgInsEqpt(rzPrjcontrChgInsEqptList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return rzPrjcontrChgInsEqptDao.deleteRzPrjcontrChgInsEqptById(rzPrjcontrChgInsEqptQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgInsEqptByCondition(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return rzPrjcontrChgInsEqptDao.deleteRzPrjcontrChgInsEqptByCondition(rzPrjcontrChgInsEqptQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgInsEqptByBatchId(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgInsEqptQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgInsEqptDao.deleteRzPrjcontrChgInsEqptByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgInsEqpt
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgInsEqpt!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgInsEqpt.getPkPrjcontrChgInsEqpt())){
					updateRzPrjcontrChgInsEqptById(rzPrjcontrChgInsEqpt);
				}else{
					insertRzPrjcontrChgInsEqpt(rzPrjcontrChgInsEqpt);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgInsEqpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgInsEqpt
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt){
		return rzPrjcontrChgInsEqptDao.updateRzPrjcontrChgInsEqptById(rzPrjcontrChgInsEqpt);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgInsEqptByCondition(RzPrjcontrChgInsEqptQuery record,RzPrjcontrChgInsEqptQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgInsEqptDao.updateRzPrjcontrChgInsEqptByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgInsEqptByBatchId(List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgInsEqptDao.updateRzPrjcontrChgInsEqptByBatchId(rzPrjcontrChgInsEqptList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgInsEqpt getRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return rzPrjcontrChgInsEqptDao.getRzPrjcontrChgInsEqptById(rzPrjcontrChgInsEqptQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptAll(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return rzPrjcontrChgInsEqptDao.getRzPrjcontrChgInsEqptAll(rzPrjcontrChgInsEqptQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgInsEqpt> getRzPrjcontrChgInsEqptByPage(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgInsEqptQuery!=null&&StringUtils.isBlank(rzPrjcontrChgInsEqptQuery.getSort())){
			rzPrjcontrChgInsEqptQuery.setSort("pk_prjcontr_chg_ins_eqpt");
			rzPrjcontrChgInsEqptQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgInsEqptDao.getRzPrjcontrChgInsEqptByPageCount(rzPrjcontrChgInsEqptQuery);
		PaginatedList<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptPageList = new MysqlPaginatedArrayList<RzPrjcontrChgInsEqpt>(rzPrjcontrChgInsEqptQuery,total);
		List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList = rzPrjcontrChgInsEqptDao.getRzPrjcontrChgInsEqptByPage(rzPrjcontrChgInsEqptQuery);
		rzPrjcontrChgInsEqptPageList.addAll(rzPrjcontrChgInsEqptList);
		GridResult<RzPrjcontrChgInsEqpt> result = new GridResult<RzPrjcontrChgInsEqpt>(rzPrjcontrChgInsEqptPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgInsEqptQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgInsEqptByPageCount(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery){
		return rzPrjcontrChgInsEqptDao.getRzPrjcontrChgInsEqptByPageCount(rzPrjcontrChgInsEqptQuery);
	}

	public void setRzPrjcontrChgInsEqptDao(RzPrjcontrChgInsEqptDao  rzPrjcontrChgInsEqptDao){
		this.rzPrjcontrChgInsEqptDao = rzPrjcontrChgInsEqptDao;
	}
	
}