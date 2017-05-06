package com.imfbp.rz.service.rzprjreviewguarb.impl;

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
import com.imfbp.rz.domain.rzprjreviewguarb.RzPrjreviewGuarB;
import com.imfbp.rz.domain.rzprjreviewguarb.query.RzPrjreviewGuarBQuery;
import com.imfbp.rz.dao.rzprjreviewguarb.RzPrjreviewGuarBDao;
import com.imfbp.rz.service.rzprjreviewguarb.RzPrjreviewGuarBService;





@Component("rzPrjreviewGuarBService")
public class RzPrjreviewGuarBServiceImpl implements RzPrjreviewGuarBService{


	private RzPrjreviewGuarBDao rzPrjreviewGuarBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjreviewGuarB
	 * @return
	 */
	@Override
	public void insertRzPrjreviewGuarB(RzPrjreviewGuarB rzPrjreviewGuarB){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjreviewGuarB.setPkPrjreviewGuarB(pk);
		rzPrjreviewGuarBDao.insertRzPrjreviewGuarB(rzPrjreviewGuarB);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewGuarB>
	 * @return
	 */
	public Result insertBatchRzPrjreviewGuarB(List<RzPrjreviewGuarB> rzPrjreviewGuarBList){
		Result result = new Result(false);
		if(rzPrjreviewGuarBList != null){
			for(int i=0;i<rzPrjreviewGuarBList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjreviewGuarBList.get(i).setPkPrjreviewGuarB(pk);
			}
			try {
				rzPrjreviewGuarBDao.insertBatchRzPrjreviewGuarB(rzPrjreviewGuarBList);
				result.setSuccess(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public boolean deleteRzPrjreviewGuarBById(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return rzPrjreviewGuarBDao.deleteRzPrjreviewGuarBById(rzPrjreviewGuarBQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarBByCondition(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return rzPrjreviewGuarBDao.deleteRzPrjreviewGuarBByCondition(rzPrjreviewGuarBQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjreviewGuarBByBatchId(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjreviewGuarBQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjreviewGuarBDao.deleteRzPrjreviewGuarBByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewGuarB
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjreviewGuarB rzPrjreviewGuarB) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjreviewGuarB!=null){
				if(StringUtil.isNotEmpty(rzPrjreviewGuarB.getPkPrjreviewGuarB())){
					updateRzPrjreviewGuarBById(rzPrjreviewGuarB);
				}else{
					insertRzPrjreviewGuarB(rzPrjreviewGuarB);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjreviewGuarB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewGuarB
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewGuarBById(RzPrjreviewGuarB rzPrjreviewGuarB){
		return rzPrjreviewGuarBDao.updateRzPrjreviewGuarBById(rzPrjreviewGuarB);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewGuarBByCondition(RzPrjreviewGuarBQuery record,RzPrjreviewGuarBQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjreviewGuarBDao.updateRzPrjreviewGuarBByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public RzPrjreviewGuarB getRzPrjreviewGuarBById(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return rzPrjreviewGuarBDao.getRzPrjreviewGuarBById(rzPrjreviewGuarBQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewGuarB> getRzPrjreviewGuarBAll(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return rzPrjreviewGuarBDao.getRzPrjreviewGuarBAll(rzPrjreviewGuarBQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjreviewGuarB> getRzPrjreviewGuarBByPage(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjreviewGuarBQuery!=null&&StringUtils.isBlank(rzPrjreviewGuarBQuery.getSort())){
			rzPrjreviewGuarBQuery.setSort("pk_prjreview_guar_b");
			rzPrjreviewGuarBQuery.setOrder("desc");;
		}
		int total = rzPrjreviewGuarBDao.getRzPrjreviewGuarBByPageCount(rzPrjreviewGuarBQuery);
		PaginatedList<RzPrjreviewGuarB> rzPrjreviewGuarBPageList = new MysqlPaginatedArrayList<RzPrjreviewGuarB>(rzPrjreviewGuarBQuery,total);
		List<RzPrjreviewGuarB> rzPrjreviewGuarBList = rzPrjreviewGuarBDao.getRzPrjreviewGuarBByPage(rzPrjreviewGuarBQuery);
		rzPrjreviewGuarBPageList.addAll(rzPrjreviewGuarBList);
		GridResult<RzPrjreviewGuarB> result = new GridResult<RzPrjreviewGuarB>(rzPrjreviewGuarBPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewGuarBQuery
	 * @return
	 */
	@Override
	public int getRzPrjreviewGuarBByPageCount(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery){
		return rzPrjreviewGuarBDao.getRzPrjreviewGuarBByPageCount(rzPrjreviewGuarBQuery);
	}

	public void setRzPrjreviewGuarBDao(RzPrjreviewGuarBDao  rzPrjreviewGuarBDao){
		this.rzPrjreviewGuarBDao = rzPrjreviewGuarBDao;
	}

	@Override
	public Result updateByBatch(List<RzPrjreviewGuarB> editList) {
		boolean isSuccess= rzPrjreviewGuarBDao.updateBatch(editList);
		Result result=new Result();
		result.setSuccess(isSuccess);
		return result;
	}
	
}