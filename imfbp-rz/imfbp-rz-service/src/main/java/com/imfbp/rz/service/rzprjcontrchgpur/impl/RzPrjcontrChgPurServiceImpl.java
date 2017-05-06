package com.imfbp.rz.service.rzprjcontrchgpur.impl;

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

import com.imfbp.rz.domain.rzprjcontrchgpur.RzPrjcontrChgPur;
import com.imfbp.rz.domain.rzprjcontrchgpur.query.RzPrjcontrChgPurQuery;
import com.imfbp.rz.dao.rzprjcontrchgpur.RzPrjcontrChgPurDao;
import com.imfbp.rz.service.rzprjcontrchgpur.RzPrjcontrChgPurService;





@Component("rzPrjcontrChgPurService")
public class RzPrjcontrChgPurServiceImpl implements RzPrjcontrChgPurService{


	private RzPrjcontrChgPurDao rzPrjcontrChgPurDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrChgPur
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgPur(RzPrjcontrChgPur rzPrjcontrChgPur){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgPur.setPkPrjcontrChgPur(pk);
		rzPrjcontrChgPurDao.insertRzPrjcontrChgPur(rzPrjcontrChgPur);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgPur>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgPur(List<RzPrjcontrChgPur> rzPrjcontrChgPurList){
		if(rzPrjcontrChgPurList != null){
			for(int i=0;i<rzPrjcontrChgPurList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgPurList.get(i).setPkPrjcontrChgPur(pk);
			}
			rzPrjcontrChgPurDao.insertBatchRzPrjcontrChgPur(rzPrjcontrChgPurList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgPurById(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return rzPrjcontrChgPurDao.deleteRzPrjcontrChgPurById(rzPrjcontrChgPurQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgPurByCondition(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return rzPrjcontrChgPurDao.deleteRzPrjcontrChgPurByCondition(rzPrjcontrChgPurQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgPurByBatchId(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgPurQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgPurDao.deleteRzPrjcontrChgPurByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgPur
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgPur rzPrjcontrChgPur) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgPur!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgPur.getPkPrjcontrChgPur())){
					updateRzPrjcontrChgPurById(rzPrjcontrChgPur);
				}else{
					insertRzPrjcontrChgPur(rzPrjcontrChgPur);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgPur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgPur
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgPurById(RzPrjcontrChgPur rzPrjcontrChgPur){
		return rzPrjcontrChgPurDao.updateRzPrjcontrChgPurById(rzPrjcontrChgPur);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgPurByCondition(RzPrjcontrChgPurQuery record,RzPrjcontrChgPurQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgPurDao.updateRzPrjcontrChgPurByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgPurByBatchId(List<RzPrjcontrChgPur> rzPrjcontrChgPurList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgPurDao.updateRzPrjcontrChgPurByBatchId(rzPrjcontrChgPurList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgPur getRzPrjcontrChgPurById(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return rzPrjcontrChgPurDao.getRzPrjcontrChgPurById(rzPrjcontrChgPurQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgPur> getRzPrjcontrChgPurAll(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return rzPrjcontrChgPurDao.getRzPrjcontrChgPurAll(rzPrjcontrChgPurQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgPur> getRzPrjcontrChgPurByPage(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgPurQuery!=null&&StringUtils.isBlank(rzPrjcontrChgPurQuery.getSort())){
			rzPrjcontrChgPurQuery.setSort("pk_prjcontr_chg_pur");
			rzPrjcontrChgPurQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgPurDao.getRzPrjcontrChgPurByPageCount(rzPrjcontrChgPurQuery);
		PaginatedList<RzPrjcontrChgPur> rzPrjcontrChgPurPageList = new MysqlPaginatedArrayList<RzPrjcontrChgPur>(rzPrjcontrChgPurQuery,total);
		List<RzPrjcontrChgPur> rzPrjcontrChgPurList = rzPrjcontrChgPurDao.getRzPrjcontrChgPurByPage(rzPrjcontrChgPurQuery);
		rzPrjcontrChgPurPageList.addAll(rzPrjcontrChgPurList);
		GridResult<RzPrjcontrChgPur> result = new GridResult<RzPrjcontrChgPur>(rzPrjcontrChgPurPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgPurQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgPurByPageCount(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery){
		return rzPrjcontrChgPurDao.getRzPrjcontrChgPurByPageCount(rzPrjcontrChgPurQuery);
	}

	public void setRzPrjcontrChgPurDao(RzPrjcontrChgPurDao  rzPrjcontrChgPurDao){
		this.rzPrjcontrChgPurDao = rzPrjcontrChgPurDao;
	}
	
}