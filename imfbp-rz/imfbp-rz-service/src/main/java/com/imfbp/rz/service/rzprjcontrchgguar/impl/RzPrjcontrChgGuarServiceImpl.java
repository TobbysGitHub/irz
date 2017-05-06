package com.imfbp.rz.service.rzprjcontrchgguar.impl;

import java.util.*;

import com.imfbp.rz.dao.rzprjcontrchgguarb.RzPrjcontrChgGuarBDao;
import com.imfbp.rz.domain.rzprjcontrchgguarb.RzPrjcontrChgGuarB;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchgguar.RzPrjcontrChgGuar;
import com.imfbp.rz.domain.rzprjcontrchgguar.query.RzPrjcontrChgGuarQuery;
import com.imfbp.rz.dao.rzprjcontrchgguar.RzPrjcontrChgGuarDao;
import com.imfbp.rz.service.rzprjcontrchgguar.RzPrjcontrChgGuarService;





@Component("rzPrjcontrChgGuarService")
public class RzPrjcontrChgGuarServiceImpl implements RzPrjcontrChgGuarService{


	private RzPrjcontrChgGuarDao rzPrjcontrChgGuarDao;

	private RzPrjcontrChgGuarBDao rzPrjcontrChgGuarBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	//批量主键生成策略
	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;

	/**
	 * 添加
	 * @param rzPrjcontrChgGuar
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChgGuar(RzPrjcontrChgGuar rzPrjcontrChgGuar){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChgGuar.setPkPrjcontrChgGuar(pk);
		RzPrjcontrChgGuarB[] rzPrjcontrChgGuarBs = rzPrjcontrChgGuar.getRzPrjcontrChgGuarBs();
		List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList = new ArrayList<RzPrjcontrChgGuarB>();
		if(rzPrjcontrChgGuarBs != null && rzPrjcontrChgGuarBs.length > 0){
			rzPrjcontrChgGuarBList = Arrays.asList(rzPrjcontrChgGuarBs);
			if(!rzPrjcontrChgGuarBList.isEmpty()){
				for(RzPrjcontrChgGuarB rzPrjcontrChgGuarB : rzPrjcontrChgGuarBList){
					//主表主键
					rzPrjcontrChgGuarB.setPkPrjcontrChgGuar(pk);
					//子表主键
					rzPrjcontrChgGuarB.setPkPrjcontrChgGuarB(primaryKeyIdWorker.getPrimaryKey());
					//合同变更主键
					rzPrjcontrChgGuarB.setPkPrjcontrChg(rzPrjcontrChgGuar.getPkPrjcontrChg());
				}
			}
		}
		rzPrjcontrChgGuarDao.insertRzPrjcontrChgGuar(rzPrjcontrChgGuar);
		if(!rzPrjcontrChgGuarBList.isEmpty()){
			rzPrjcontrChgGuarBDao.insertBatchRzPrjcontrChgGuarB(rzPrjcontrChgGuarBList);
		}
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChgGuar>
	 * @return
	 */
	public void insertBatchRzPrjcontrChgGuar(List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList){
		if(rzPrjcontrChgGuarList != null){
			for(int i=0;i<rzPrjcontrChgGuarList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgGuarList.get(i).setPkPrjcontrChgGuar(pk);
			}
			rzPrjcontrChgGuarDao.insertBatchRzPrjcontrChgGuar(rzPrjcontrChgGuarList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarById(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return rzPrjcontrChgGuarDao.deleteRzPrjcontrChgGuarById(rzPrjcontrChgGuarQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgGuarByCondition(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return rzPrjcontrChgGuarDao.deleteRzPrjcontrChgGuarByCondition(rzPrjcontrChgGuarQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgGuarByBatchId(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgGuarQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgGuarDao.deleteRzPrjcontrChgGuarByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChgGuar
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChgGuar rzPrjcontrChgGuar) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChgGuar!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChgGuar.getPkPrjcontrChgGuar())){
					updateRzPrjcontrChgGuarById(rzPrjcontrChgGuar);
				}else{
					insertRzPrjcontrChgGuar(rzPrjcontrChgGuar);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChgGuar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChgGuar
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgGuarById(RzPrjcontrChgGuar rzPrjcontrChgGuar){
		return rzPrjcontrChgGuarDao.updateRzPrjcontrChgGuarById(rzPrjcontrChgGuar);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgGuarByCondition(RzPrjcontrChgGuarQuery record,RzPrjcontrChgGuarQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgGuarDao.updateRzPrjcontrChgGuarByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgGuarByBatchId(List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgGuarDao.updateRzPrjcontrChgGuarByBatchId(rzPrjcontrChgGuarList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChgGuar getRzPrjcontrChgGuarById(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return rzPrjcontrChgGuarDao.getRzPrjcontrChgGuarById(rzPrjcontrChgGuarQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChgGuar> getRzPrjcontrChgGuarAll(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return rzPrjcontrChgGuarDao.getRzPrjcontrChgGuarAll(rzPrjcontrChgGuarQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChgGuar> getRzPrjcontrChgGuarByPage(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgGuarQuery!=null&&StringUtils.isBlank(rzPrjcontrChgGuarQuery.getSort())){
			rzPrjcontrChgGuarQuery.setSort("pk_prjcontr_chg_guar");
			rzPrjcontrChgGuarQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgGuarDao.getRzPrjcontrChgGuarByPageCount(rzPrjcontrChgGuarQuery);
		PaginatedList<RzPrjcontrChgGuar> rzPrjcontrChgGuarPageList = new MysqlPaginatedArrayList<RzPrjcontrChgGuar>(rzPrjcontrChgGuarQuery,total);
		List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList = rzPrjcontrChgGuarDao.getRzPrjcontrChgGuarByPage(rzPrjcontrChgGuarQuery);
		rzPrjcontrChgGuarPageList.addAll(rzPrjcontrChgGuarList);
		GridResult<RzPrjcontrChgGuar> result = new GridResult<RzPrjcontrChgGuar>(rzPrjcontrChgGuarPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgGuarQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgGuarByPageCount(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery){
		return rzPrjcontrChgGuarDao.getRzPrjcontrChgGuarByPageCount(rzPrjcontrChgGuarQuery);
	}

	public void setRzPrjcontrChgGuarDao(RzPrjcontrChgGuarDao  rzPrjcontrChgGuarDao){
		this.rzPrjcontrChgGuarDao = rzPrjcontrChgGuarDao;
	}

	public void setRzPrjcontrChgGuarBDao(RzPrjcontrChgGuarBDao rzPrjcontrChgGuarBDao) {
		this.rzPrjcontrChgGuarBDao = rzPrjcontrChgGuarBDao;
	}
}