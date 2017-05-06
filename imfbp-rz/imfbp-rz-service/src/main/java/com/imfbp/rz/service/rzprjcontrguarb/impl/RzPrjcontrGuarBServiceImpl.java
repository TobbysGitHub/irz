package com.imfbp.rz.service.rzprjcontrguarb.impl;

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

import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;
import com.imfbp.rz.dao.rzprjcontrguarb.RzPrjcontrGuarBDao;
import com.imfbp.rz.service.rzprjcontrguarb.RzPrjcontrGuarBService;





@Component("rzPrjcontrGuarBService")
public class RzPrjcontrGuarBServiceImpl implements RzPrjcontrGuarBService{


	private RzPrjcontrGuarBDao rzPrjcontrGuarBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrGuarB
	 * @return
	 */
	@Override
	public void insertRzPrjcontrGuarB(RzPrjcontrGuarB rzPrjcontrGuarB){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrGuarB.setPkPrjcontrGuarB(pk);
		rzPrjcontrGuarBDao.insertRzPrjcontrGuarB(rzPrjcontrGuarB);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrGuarB>
	 * @return
	 */
	public Result insertBatchRzPrjcontrGuarB(List<RzPrjcontrGuarB> rzPrjcontrGuarBList){
		Result result = new Result();
		result.setSuccess(false);
		if(rzPrjcontrGuarBList != null){
			for(int i=0;i<rzPrjcontrGuarBList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrGuarBList.get(i).setPkPrjcontrGuarB(pk);
			}
			try{
				rzPrjcontrGuarBDao.insertBatchRzPrjcontrGuarB(rzPrjcontrGuarBList);
				result.setSuccess(true);
				result.addDefaultModel(rzPrjcontrGuarBList);
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarBById(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return rzPrjcontrGuarBDao.deleteRzPrjcontrGuarBById(rzPrjcontrGuarBQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarBByCondition(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return rzPrjcontrGuarBDao.deleteRzPrjcontrGuarBByCondition(rzPrjcontrGuarBQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrGuarBByBatchId(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrGuarBQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrGuarBDao.deleteRzPrjcontrGuarBByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrGuarB
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrGuarB rzPrjcontrGuarB) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrGuarB!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrGuarB.getPkPrjcontrGuarB())){
					updateRzPrjcontrGuarBById(rzPrjcontrGuarB);
				}else{
					insertRzPrjcontrGuarB(rzPrjcontrGuarB);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrGuarB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrGuarB
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrGuarBById(RzPrjcontrGuarB rzPrjcontrGuarB){
		return rzPrjcontrGuarBDao.updateRzPrjcontrGuarBById(rzPrjcontrGuarB);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrGuarBByCondition(RzPrjcontrGuarBQuery record,RzPrjcontrGuarBQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrGuarBDao.updateRzPrjcontrGuarBByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	public Result updateRzPrjcontrGuarBByBatchId(List<RzPrjcontrGuarB> rzPrjcontrGuarBList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrGuarBDao.updateRzPrjcontrGuarBByBatchId(rzPrjcontrGuarBList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public RzPrjcontrGuarB getRzPrjcontrGuarBById(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return rzPrjcontrGuarBDao.getRzPrjcontrGuarBById(rzPrjcontrGuarBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrGuarB> getRzPrjcontrGuarBAll(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return rzPrjcontrGuarBDao.getRzPrjcontrGuarBAll(rzPrjcontrGuarBQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrGuarB> getRzPrjcontrGuarBByPage(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrGuarBQuery!=null&&StringUtils.isBlank(rzPrjcontrGuarBQuery.getSort())){
			rzPrjcontrGuarBQuery.setSort("pk_prjcontr_guar_b");
			rzPrjcontrGuarBQuery.setOrder("desc");;
		}
		int total = rzPrjcontrGuarBDao.getRzPrjcontrGuarBByPageCount(rzPrjcontrGuarBQuery);
		PaginatedList<RzPrjcontrGuarB> rzPrjcontrGuarBPageList = new MysqlPaginatedArrayList<RzPrjcontrGuarB>(rzPrjcontrGuarBQuery,total);
		List<RzPrjcontrGuarB> rzPrjcontrGuarBList = rzPrjcontrGuarBDao.getRzPrjcontrGuarBByPage(rzPrjcontrGuarBQuery);
		rzPrjcontrGuarBPageList.addAll(rzPrjcontrGuarBList);
		GridResult<RzPrjcontrGuarB> result = new GridResult<RzPrjcontrGuarB>(rzPrjcontrGuarBPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrGuarBQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrGuarBByPageCount(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery){
		return rzPrjcontrGuarBDao.getRzPrjcontrGuarBByPageCount(rzPrjcontrGuarBQuery);
	}

	public void setRzPrjcontrGuarBDao(RzPrjcontrGuarBDao  rzPrjcontrGuarBDao){
		this.rzPrjcontrGuarBDao = rzPrjcontrGuarBDao;
	}

	@Override
	public Result updateByBatch(List<RzPrjcontrGuarB> editList) {
		boolean isSuccess = rzPrjcontrGuarBDao.updateBatch(editList);
		Result result = new Result();
		result.setSuccess(isSuccess);
		return result;
	}
}