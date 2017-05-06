package com.imfbp.rz.service.rzprjcontrchgsupplier.impl;

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

import com.imfbp.rz.domain.rzprjcontrchgsupplier.RzPrjcontrChgSupplier;
import com.imfbp.rz.domain.rzprjcontrchgsupplier.query.RzPrjcontrChgSupplierQuery;
import com.imfbp.rz.dao.rzprjcontrchgsupplier.RzPrjcontrChgSupplierDao;
import com.imfbp.rz.service.rzprjcontrchgsupplier.RzPrjcontrChgSupplierService;





@Component("rzPrjcontrChgSupplierService")
public class RzPrjcontrChgSupplierServiceImpl implements RzPrjcontrChgSupplierService{


	private RzPrjcontrChgSupplierDao rzPrjcontrChgSupplierDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrChgSupplier
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgSupplier(RzPrjcontrChgSupplier rzPrjcontrChgSupplier){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgSupplier.setPkPrjcontrChgSupplier(pk);
		rzPrjcontrChgSupplierDao.insertRzPrjcontrChgSupplier(rzPrjcontrChgSupplier);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgSupplier>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgSupplier(List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList){
		if(rzPrjcontrChgSupplierList != null){
			for(int i=0;i<rzPrjcontrChgSupplierList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgSupplierList.get(i).setPkPrjcontrChgSupplier(pk);
			}
			rzPrjcontrChgSupplierDao.insertBatchRzPrjcontrChgSupplier(rzPrjcontrChgSupplierList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgSupplierById(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return rzPrjcontrChgSupplierDao.deleteRzPrjcontrChgSupplierById(rzPrjcontrChgSupplierQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgSupplierByCondition(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return rzPrjcontrChgSupplierDao.deleteRzPrjcontrChgSupplierByCondition(rzPrjcontrChgSupplierQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgSupplierByBatchId(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgSupplierQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgSupplierDao.deleteRzPrjcontrChgSupplierByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgSupplier
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgSupplier rzPrjcontrChgSupplier) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgSupplier!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgSupplier.getPkPrjcontrChgSupplier())){
					updateRzPrjcontrChgSupplierById(rzPrjcontrChgSupplier);
				}else{
					insertRzPrjcontrChgSupplier(rzPrjcontrChgSupplier);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgSupplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgSupplier
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgSupplierById(RzPrjcontrChgSupplier rzPrjcontrChgSupplier){
		return rzPrjcontrChgSupplierDao.updateRzPrjcontrChgSupplierById(rzPrjcontrChgSupplier);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgSupplierByCondition(RzPrjcontrChgSupplierQuery record,RzPrjcontrChgSupplierQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgSupplierDao.updateRzPrjcontrChgSupplierByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgSupplierByBatchId(List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgSupplierDao.updateRzPrjcontrChgSupplierByBatchId(rzPrjcontrChgSupplierList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgSupplier getRzPrjcontrChgSupplierById(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return rzPrjcontrChgSupplierDao.getRzPrjcontrChgSupplierById(rzPrjcontrChgSupplierQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgSupplier> getRzPrjcontrChgSupplierAll(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return rzPrjcontrChgSupplierDao.getRzPrjcontrChgSupplierAll(rzPrjcontrChgSupplierQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgSupplier> getRzPrjcontrChgSupplierByPage(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgSupplierQuery!=null&&StringUtils.isBlank(rzPrjcontrChgSupplierQuery.getSort())){
			rzPrjcontrChgSupplierQuery.setSort("pk_prjcontr_chg_supplier");
			rzPrjcontrChgSupplierQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgSupplierDao.getRzPrjcontrChgSupplierByPageCount(rzPrjcontrChgSupplierQuery);
		PaginatedList<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierPageList = new MysqlPaginatedArrayList<RzPrjcontrChgSupplier>(rzPrjcontrChgSupplierQuery,total);
		List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList = rzPrjcontrChgSupplierDao.getRzPrjcontrChgSupplierByPage(rzPrjcontrChgSupplierQuery);
		rzPrjcontrChgSupplierPageList.addAll(rzPrjcontrChgSupplierList);
		GridResult<RzPrjcontrChgSupplier> result = new GridResult<RzPrjcontrChgSupplier>(rzPrjcontrChgSupplierPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgSupplierQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgSupplierByPageCount(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery){
		return rzPrjcontrChgSupplierDao.getRzPrjcontrChgSupplierByPageCount(rzPrjcontrChgSupplierQuery);
	}

	public void setRzPrjcontrChgSupplierDao(RzPrjcontrChgSupplierDao  rzPrjcontrChgSupplierDao){
		this.rzPrjcontrChgSupplierDao = rzPrjcontrChgSupplierDao;
	}
	
}