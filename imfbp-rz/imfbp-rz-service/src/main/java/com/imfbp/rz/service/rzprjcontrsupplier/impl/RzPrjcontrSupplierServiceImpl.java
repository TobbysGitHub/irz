package com.imfbp.rz.service.rzprjcontrsupplier.impl;

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

import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjcontrsupplier.query.RzPrjcontrSupplierQuery;
import com.imfbp.rz.dao.rzprjcontrsupplier.RzPrjcontrSupplierDao;
import com.imfbp.rz.service.rzprjcontrsupplier.RzPrjcontrSupplierService;





@Component("rzPrjcontrSupplierService")
public class RzPrjcontrSupplierServiceImpl implements RzPrjcontrSupplierService{


	private RzPrjcontrSupplierDao rzPrjcontrSupplierDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrSupplier
	 * @return
	 */
	@Override
	public void insertRzPrjcontrSupplier(RzPrjcontrSupplier rzPrjcontrSupplier){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrSupplier.setPkPrjcontrSupplier(pk);
		rzPrjcontrSupplierDao.insertRzPrjcontrSupplier(rzPrjcontrSupplier);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrSupplier>
	 * @return
	 */
	public void insertBatchRzPrjcontrSupplier(List<RzPrjcontrSupplier> rzPrjcontrSupplierList){
		if(rzPrjcontrSupplierList != null){
			for(int i=0;i<rzPrjcontrSupplierList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrSupplierList.get(i).setPkPrjcontrSupplier(pk);
			}
			rzPrjcontrSupplierDao.insertBatchRzPrjcontrSupplier(rzPrjcontrSupplierList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrSupplierById(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return rzPrjcontrSupplierDao.deleteRzPrjcontrSupplierById(rzPrjcontrSupplierQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrSupplierByCondition(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return rzPrjcontrSupplierDao.deleteRzPrjcontrSupplierByCondition(rzPrjcontrSupplierQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrSupplierByBatchId(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrSupplierQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrSupplierDao.deleteRzPrjcontrSupplierByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrSupplier
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrSupplier rzPrjcontrSupplier) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrSupplier!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrSupplier.getPkPrjcontrSupplier())){
					updateRzPrjcontrSupplierById(rzPrjcontrSupplier);
				}else{
					insertRzPrjcontrSupplier(rzPrjcontrSupplier);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrSupplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrSupplier
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrSupplierById(RzPrjcontrSupplier rzPrjcontrSupplier){
		return rzPrjcontrSupplierDao.updateRzPrjcontrSupplierById(rzPrjcontrSupplier);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrSupplierByCondition(RzPrjcontrSupplierQuery record,RzPrjcontrSupplierQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrSupplierDao.updateRzPrjcontrSupplierByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	public Result updateRzPrjcontrSupplierByBatchId(List<RzPrjcontrSupplier> rzPrjcontrSupplierList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrSupplierDao.updateRzPrjcontrSupplierByBatchId(rzPrjcontrSupplierList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public RzPrjcontrSupplier getRzPrjcontrSupplierById(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return rzPrjcontrSupplierDao.getRzPrjcontrSupplierById(rzPrjcontrSupplierQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrSupplier> getRzPrjcontrSupplierAll(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return rzPrjcontrSupplierDao.getRzPrjcontrSupplierAll(rzPrjcontrSupplierQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrSupplier> getRzPrjcontrSupplierByPage(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrSupplierQuery!=null&&StringUtils.isBlank(rzPrjcontrSupplierQuery.getSort())){
			rzPrjcontrSupplierQuery.setSort("pk_prjcontr_supplier");
			rzPrjcontrSupplierQuery.setOrder("desc");;
		}
		int total = rzPrjcontrSupplierDao.getRzPrjcontrSupplierByPageCount(rzPrjcontrSupplierQuery);
		PaginatedList<RzPrjcontrSupplier> rzPrjcontrSupplierPageList = new MysqlPaginatedArrayList<RzPrjcontrSupplier>(rzPrjcontrSupplierQuery,total);
		List<RzPrjcontrSupplier> rzPrjcontrSupplierList = rzPrjcontrSupplierDao.getRzPrjcontrSupplierByPage(rzPrjcontrSupplierQuery);
		rzPrjcontrSupplierPageList.addAll(rzPrjcontrSupplierList);
		GridResult<RzPrjcontrSupplier> result = new GridResult<RzPrjcontrSupplier>(rzPrjcontrSupplierPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrSupplierQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrSupplierByPageCount(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery){
		return rzPrjcontrSupplierDao.getRzPrjcontrSupplierByPageCount(rzPrjcontrSupplierQuery);
	}

	public void setRzPrjcontrSupplierDao(RzPrjcontrSupplierDao  rzPrjcontrSupplierDao){
		this.rzPrjcontrSupplierDao = rzPrjcontrSupplierDao;
	}
	
}