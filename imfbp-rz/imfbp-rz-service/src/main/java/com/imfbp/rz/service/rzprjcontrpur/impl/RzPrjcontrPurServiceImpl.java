package com.imfbp.rz.service.rzprjcontrpur.impl;

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

import com.imfbp.rz.domain.rzprjcontrpur.RzPrjcontrPur;
import com.imfbp.rz.domain.rzprjcontrpur.query.RzPrjcontrPurQuery;
import com.imfbp.rz.dao.rzprjcontrpur.RzPrjcontrPurDao;
import com.imfbp.rz.service.rzprjcontrpur.RzPrjcontrPurService;





@Component("rzPrjcontrPurService")
public class RzPrjcontrPurServiceImpl implements RzPrjcontrPurService{


	private RzPrjcontrPurDao rzPrjcontrPurDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrPur
	 * @return
	 */
	@Override
	public void insertRzPrjcontrPur(RzPrjcontrPur rzPrjcontrPur){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrPur.setPkPrjcontrPur(pk);
		rzPrjcontrPurDao.insertRzPrjcontrPur(rzPrjcontrPur);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrPur>
	 * @return
	 */
	public void insertBatchRzPrjcontrPur(List<RzPrjcontrPur> rzPrjcontrPurList){
		if(rzPrjcontrPurList != null){
			for(int i=0;i<rzPrjcontrPurList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrPurList.get(i).setPkPrjcontrPur(pk);
			}
			rzPrjcontrPurDao.insertBatchRzPrjcontrPur(rzPrjcontrPurList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrPurById(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return rzPrjcontrPurDao.deleteRzPrjcontrPurById(rzPrjcontrPurQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrPurByCondition(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return rzPrjcontrPurDao.deleteRzPrjcontrPurByCondition(rzPrjcontrPurQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrPurQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrPurByBatchId(RzPrjcontrPurQuery rzPrjcontrPurQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrPurQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrPurDao.deleteRzPrjcontrPurByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrPur
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrPur rzPrjcontrPur) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrPur!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrPur.getPkPrjcontrPur())){
					updateRzPrjcontrPurById(rzPrjcontrPur);
				}else{
					insertRzPrjcontrPur(rzPrjcontrPur);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrPur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrPur
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrPurById(RzPrjcontrPur rzPrjcontrPur){
		return rzPrjcontrPurDao.updateRzPrjcontrPurById(rzPrjcontrPur);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrPurByCondition(RzPrjcontrPurQuery record,RzPrjcontrPurQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrPurDao.updateRzPrjcontrPurByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	public Result updateRzPrjcontrPurByBatchId(List<RzPrjcontrPur> rzPrjcontrPurList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrPurDao.updateRzPrjcontrPurByBatchId(rzPrjcontrPurList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public RzPrjcontrPur getRzPrjcontrPurById(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return rzPrjcontrPurDao.getRzPrjcontrPurById(rzPrjcontrPurQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrPur> getRzPrjcontrPurAll(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return rzPrjcontrPurDao.getRzPrjcontrPurAll(rzPrjcontrPurQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrPur> getRzPrjcontrPurByPage(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrPurQuery!=null&&StringUtils.isBlank(rzPrjcontrPurQuery.getSort())){
			rzPrjcontrPurQuery.setSort("pk_prjcontr_pur");
			rzPrjcontrPurQuery.setOrder("desc");;
		}
		int total = rzPrjcontrPurDao.getRzPrjcontrPurByPageCount(rzPrjcontrPurQuery);
		PaginatedList<RzPrjcontrPur> rzPrjcontrPurPageList = new MysqlPaginatedArrayList<RzPrjcontrPur>(rzPrjcontrPurQuery,total);
		List<RzPrjcontrPur> rzPrjcontrPurList = rzPrjcontrPurDao.getRzPrjcontrPurByPage(rzPrjcontrPurQuery);
		rzPrjcontrPurPageList.addAll(rzPrjcontrPurList);
		GridResult<RzPrjcontrPur> result = new GridResult<RzPrjcontrPur>(rzPrjcontrPurPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrPurQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrPurByPageCount(RzPrjcontrPurQuery rzPrjcontrPurQuery){
		return rzPrjcontrPurDao.getRzPrjcontrPurByPageCount(rzPrjcontrPurQuery);
	}

	public void setRzPrjcontrPurDao(RzPrjcontrPurDao  rzPrjcontrPurDao){
		this.rzPrjcontrPurDao = rzPrjcontrPurDao;
	}
	
}