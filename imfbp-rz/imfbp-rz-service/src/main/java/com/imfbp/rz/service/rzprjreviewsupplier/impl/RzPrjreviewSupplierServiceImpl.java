package com.imfbp.rz.service.rzprjreviewsupplier.impl;

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

import com.imfbp.rz.domain.rzprjreviewsupplier.RzPrjreviewSupplier;
import com.imfbp.rz.domain.rzprjreviewsupplier.query.RzPrjreviewSupplierQuery;
import com.imfbp.rz.dao.rzprjreviewsupplier.RzPrjreviewSupplierDao;
import com.imfbp.rz.service.rzprjreviewsupplier.RzPrjreviewSupplierService;





@Component("rzPrjreviewSupplierService")
public class RzPrjreviewSupplierServiceImpl implements RzPrjreviewSupplierService{


	private RzPrjreviewSupplierDao rzPrjreviewSupplierDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjreviewSupplier
	 * @return
	 */
	@Override
	public void insertRzPrjreviewSupplier(RzPrjreviewSupplier rzPrjreviewSupplier){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjreviewSupplier.setPkPrjreviewSupplier(pk);
		rzPrjreviewSupplierDao.insertRzPrjreviewSupplier(rzPrjreviewSupplier);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewSupplier>
	 * @return
	 */
	public void insertBatchRzPrjreviewSupplier(List<RzPrjreviewSupplier> rzPrjreviewSupplierList){
		if(rzPrjreviewSupplierList != null){
			for(int i=0;i<rzPrjreviewSupplierList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjreviewSupplierList.get(i).setPkPrjreviewSupplier(pk);
			}
			rzPrjreviewSupplierDao.insertBatchRzPrjreviewSupplier(rzPrjreviewSupplierList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewSupplierById(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return rzPrjreviewSupplierDao.deleteRzPrjreviewSupplierById(rzPrjreviewSupplierQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewSupplierByCondition(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return rzPrjreviewSupplierDao.deleteRzPrjreviewSupplierByCondition(rzPrjreviewSupplierQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjreviewSupplierByBatchId(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjreviewSupplierQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjreviewSupplierDao.deleteRzPrjreviewSupplierByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewSupplier
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjreviewSupplier rzPrjreviewSupplier) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjreviewSupplier!=null){
				if(StringUtil.isNotEmpty(rzPrjreviewSupplier.getPkPrjreviewSupplier())){
					updateRzPrjreviewSupplierById(rzPrjreviewSupplier);
				}else{
					insertRzPrjreviewSupplier(rzPrjreviewSupplier);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjreviewSupplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewSupplier
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewSupplierById(RzPrjreviewSupplier rzPrjreviewSupplier){
		return rzPrjreviewSupplierDao.updateRzPrjreviewSupplierById(rzPrjreviewSupplier);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewSupplierByCondition(RzPrjreviewSupplierQuery record,RzPrjreviewSupplierQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjreviewSupplierDao.updateRzPrjreviewSupplierByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public RzPrjreviewSupplier getRzPrjreviewSupplierById(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return rzPrjreviewSupplierDao.getRzPrjreviewSupplierById(rzPrjreviewSupplierQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewSupplier> getRzPrjreviewSupplierAll(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return rzPrjreviewSupplierDao.getRzPrjreviewSupplierAll(rzPrjreviewSupplierQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjreviewSupplier> getRzPrjreviewSupplierByPage(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjreviewSupplierQuery!=null&&StringUtils.isBlank(rzPrjreviewSupplierQuery.getSort())){
			rzPrjreviewSupplierQuery.setSort("pk_prjreview_supplier");
			rzPrjreviewSupplierQuery.setOrder("desc");;
		}
		int total = rzPrjreviewSupplierDao.getRzPrjreviewSupplierByPageCount(rzPrjreviewSupplierQuery);
		PaginatedList<RzPrjreviewSupplier> rzPrjreviewSupplierPageList = new MysqlPaginatedArrayList<RzPrjreviewSupplier>(rzPrjreviewSupplierQuery,total);
		List<RzPrjreviewSupplier> rzPrjreviewSupplierList = rzPrjreviewSupplierDao.getRzPrjreviewSupplierByPage(rzPrjreviewSupplierQuery);
		rzPrjreviewSupplierPageList.addAll(rzPrjreviewSupplierList);
		GridResult<RzPrjreviewSupplier> result = new GridResult<RzPrjreviewSupplier>(rzPrjreviewSupplierPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewSupplierQuery
	 * @return
	 */
	@Override
	public int getRzPrjreviewSupplierByPageCount(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery){
		return rzPrjreviewSupplierDao.getRzPrjreviewSupplierByPageCount(rzPrjreviewSupplierQuery);
	}

	public void setRzPrjreviewSupplierDao(RzPrjreviewSupplierDao  rzPrjreviewSupplierDao){
		this.rzPrjreviewSupplierDao = rzPrjreviewSupplierDao;
	}
	
}