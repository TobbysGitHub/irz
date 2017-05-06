package com.imfbp.rz.service.rzprjcontrchgguarb.impl;

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

import com.imfbp.rz.domain.rzprjcontrchgguarb.RzPrjcontrChgGuarB;
import com.imfbp.rz.domain.rzprjcontrchgguarb.query.RzPrjcontrChgGuarBQuery;
import com.imfbp.rz.dao.rzprjcontrchgguarb.RzPrjcontrChgGuarBDao;
import com.imfbp.rz.service.rzprjcontrchgguarb.RzPrjcontrChgGuarBService;





@Component("rzPrjcontrChgGuarBService")
public class RzPrjcontrChgGuarBServiceImpl implements RzPrjcontrChgGuarBService{


	private RzPrjcontrChgGuarBDao rzPrjcontrChgGuarBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrChgGuarB
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgGuarB(RzPrjcontrChgGuarB rzPrjcontrChgGuarB){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgGuarB.setPkPrjcontrChgGuarB(pk);
		rzPrjcontrChgGuarBDao.insertRzPrjcontrChgGuarB(rzPrjcontrChgGuarB);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgGuarB>
	 * @return
	 */
	public Result insertBatchRzPrjcontrChgGuarB(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList){
		Result result = new Result();
		result.setSuccess(false);
		if(rzPrjcontrChgGuarBList != null){
			for(int i=0;i<rzPrjcontrChgGuarBList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgGuarBList.get(i).setPkPrjcontrChgGuarB(pk);
			}
			try{
				rzPrjcontrChgGuarBDao.insertBatchRzPrjcontrChgGuarB(rzPrjcontrChgGuarBList);
				result.setSuccess(true);
				result.addDefaultModel(rzPrjcontrChgGuarBList);
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
	public boolean deleteRzPrjcontrChgGuarBById(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return rzPrjcontrChgGuarBDao.deleteRzPrjcontrChgGuarBById(rzPrjcontrChgGuarBQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarBByCondition(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return rzPrjcontrChgGuarBDao.deleteRzPrjcontrChgGuarBByCondition(rzPrjcontrChgGuarBQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgGuarBByBatchId(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgGuarBQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgGuarBDao.deleteRzPrjcontrChgGuarBByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgGuarB
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgGuarB rzPrjcontrChgGuarB) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgGuarB!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgGuarB.getPkPrjcontrChgGuarB())){
					updateRzPrjcontrChgGuarBById(rzPrjcontrChgGuarB);
				}else{
					insertRzPrjcontrChgGuarB(rzPrjcontrChgGuarB);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgGuarB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgGuarB
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgGuarBById(RzPrjcontrChgGuarB rzPrjcontrChgGuarB){
		return rzPrjcontrChgGuarBDao.updateRzPrjcontrChgGuarBById(rzPrjcontrChgGuarB);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgGuarBByCondition(RzPrjcontrChgGuarBQuery record,RzPrjcontrChgGuarBQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgGuarBDao.updateRzPrjcontrChgGuarBByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgGuarBByBatchId(List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgGuarBDao.updateRzPrjcontrChgGuarBByBatchId(rzPrjcontrChgGuarBList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgGuarB getRzPrjcontrChgGuarBById(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return rzPrjcontrChgGuarBDao.getRzPrjcontrChgGuarBById(rzPrjcontrChgGuarBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgGuarB> getRzPrjcontrChgGuarBAll(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return rzPrjcontrChgGuarBDao.getRzPrjcontrChgGuarBAll(rzPrjcontrChgGuarBQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgGuarB> getRzPrjcontrChgGuarBByPage(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgGuarBQuery!=null&&StringUtils.isBlank(rzPrjcontrChgGuarBQuery.getSort())){
			rzPrjcontrChgGuarBQuery.setSort("pk_prjcontr_chg_guar_b");
			rzPrjcontrChgGuarBQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgGuarBDao.getRzPrjcontrChgGuarBByPageCount(rzPrjcontrChgGuarBQuery);
		PaginatedList<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBPageList = new MysqlPaginatedArrayList<RzPrjcontrChgGuarB>(rzPrjcontrChgGuarBQuery,total);
		List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList = rzPrjcontrChgGuarBDao.getRzPrjcontrChgGuarBByPage(rzPrjcontrChgGuarBQuery);
		rzPrjcontrChgGuarBPageList.addAll(rzPrjcontrChgGuarBList);
		GridResult<RzPrjcontrChgGuarB> result = new GridResult<RzPrjcontrChgGuarB>(rzPrjcontrChgGuarBPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgGuarBQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgGuarBByPageCount(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery){
		return rzPrjcontrChgGuarBDao.getRzPrjcontrChgGuarBByPageCount(rzPrjcontrChgGuarBQuery);
	}

	public void setRzPrjcontrChgGuarBDao(RzPrjcontrChgGuarBDao  rzPrjcontrChgGuarBDao){
		this.rzPrjcontrChgGuarBDao = rzPrjcontrChgGuarBDao;
	}

	@Override
	public Result updateByBatch(List<RzPrjcontrChgGuarB> editList) {
		boolean isSuccess = rzPrjcontrChgGuarBDao.updateBatch(editList);
		Result result = new Result();
		result.setSuccess(isSuccess);
		return result;
	}
}