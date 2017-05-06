package com.imfbp.rz.service.rzprjcontrcf.impl;

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

import com.imfbp.rz.domain.rzprjcontrcf.RzPrjcontrCf;
import com.imfbp.rz.domain.rzprjcontrcf.query.RzPrjcontrCfQuery;
import com.imfbp.rz.dao.rzprjcontrcf.RzPrjcontrCfDao;
import com.imfbp.rz.service.rzprjcontrcf.RzPrjcontrCfService;





@Component("rzPrjcontrCfService")
public class RzPrjcontrCfServiceImpl implements RzPrjcontrCfService{


	private RzPrjcontrCfDao rzPrjcontrCfDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjcontrCf
	 * @return
	 */
	@Override
	public void insertRzPrjcontrCf(RzPrjcontrCf rzPrjcontrCf){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrCf.setPkPrjcontrCf(pk);
		rzPrjcontrCfDao.insertRzPrjcontrCf(rzPrjcontrCf);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrCf>
	 * @return
	 */
	public void insertBatchRzPrjcontrCf(List<RzPrjcontrCf> rzPrjcontrCfList){
		if(rzPrjcontrCfList != null){
			for(int i=0;i<rzPrjcontrCfList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrCfList.get(i).setPkPrjcontrCf(pk);
			}
			rzPrjcontrCfDao.insertBatchRzPrjcontrCf(rzPrjcontrCfList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrCfById(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return rzPrjcontrCfDao.deleteRzPrjcontrCfById(rzPrjcontrCfQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrCfByCondition(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return rzPrjcontrCfDao.deleteRzPrjcontrCfByCondition(rzPrjcontrCfQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrCfQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrCfByBatchId(RzPrjcontrCfQuery rzPrjcontrCfQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrCfQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrCfDao.deleteRzPrjcontrCfByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrCf
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrCf rzPrjcontrCf) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrCf!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrCf.getPkPrjcontrCf())){
					updateRzPrjcontrCfById(rzPrjcontrCf);
				}else{
					insertRzPrjcontrCf(rzPrjcontrCf);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrCf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrCf
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrCfById(RzPrjcontrCf rzPrjcontrCf){
		return rzPrjcontrCfDao.updateRzPrjcontrCfById(rzPrjcontrCf);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrCfByCondition(RzPrjcontrCfQuery record,RzPrjcontrCfQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrCfDao.updateRzPrjcontrCfByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	public Result updateRzPrjcontrCfByBatchId(List<RzPrjcontrCf> rzPrjcontrCfList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrCfDao.updateRzPrjcontrCfByBatchId(rzPrjcontrCfList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public RzPrjcontrCf getRzPrjcontrCfById(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return rzPrjcontrCfDao.getRzPrjcontrCfById(rzPrjcontrCfQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrCf> getRzPrjcontrCfAll(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return rzPrjcontrCfDao.getRzPrjcontrCfAll(rzPrjcontrCfQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrCf> getRzPrjcontrCfByPage(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrCfQuery!=null&&StringUtils.isBlank(rzPrjcontrCfQuery.getSort())){
			rzPrjcontrCfQuery.setSort("pk_prjcontr_cf");
			rzPrjcontrCfQuery.setOrder("desc");;
		}
		int total = rzPrjcontrCfDao.getRzPrjcontrCfByPageCount(rzPrjcontrCfQuery);
		PaginatedList<RzPrjcontrCf> rzPrjcontrCfPageList = new MysqlPaginatedArrayList<RzPrjcontrCf>(rzPrjcontrCfQuery,total);
		List<RzPrjcontrCf> rzPrjcontrCfList = rzPrjcontrCfDao.getRzPrjcontrCfByPage(rzPrjcontrCfQuery);
		rzPrjcontrCfPageList.addAll(rzPrjcontrCfList);
		GridResult<RzPrjcontrCf> result = new GridResult<RzPrjcontrCf>(rzPrjcontrCfPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrCfQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrCfByPageCount(RzPrjcontrCfQuery rzPrjcontrCfQuery){
		return rzPrjcontrCfDao.getRzPrjcontrCfByPageCount(rzPrjcontrCfQuery);
	}

	public void setRzPrjcontrCfDao(RzPrjcontrCfDao  rzPrjcontrCfDao){
		this.rzPrjcontrCfDao = rzPrjcontrCfDao;
	}
	
}