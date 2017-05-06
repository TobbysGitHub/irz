package com.imfbp.rz.service.rzprjcontrguar.impl;

import java.util.*;

import com.imfbp.rz.dao.rzprjcontrguarb.RzPrjcontrGuarBDao;
import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrguar.RzPrjcontrGuar;
import com.imfbp.rz.domain.rzprjcontrguar.query.RzPrjcontrGuarQuery;
import com.imfbp.rz.dao.rzprjcontrguar.RzPrjcontrGuarDao;
import com.imfbp.rz.service.rzprjcontrguar.RzPrjcontrGuarService;





@Component("rzPrjcontrGuarService")
public class RzPrjcontrGuarServiceImpl implements RzPrjcontrGuarService{


	private RzPrjcontrGuarDao rzPrjcontrGuarDao;

	private RzPrjcontrGuarBDao rzPrjcontrGuarBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	//批量主键生成策略
	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;

	/**
	 * 添加
	 * @param rzPrjcontrGuar
	 * @return
	 */
	@Override
	public void insertRzPrjcontrGuar(RzPrjcontrGuar rzPrjcontrGuar){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrGuar.setPkPrjcontrGuar(pk);
		//获取子表数组
		RzPrjcontrGuarB[] rzPrjcontrGuarBArray = rzPrjcontrGuar.getRzPrjcontrGuarBs();
		//存在子表将子表数组转为list
		List<RzPrjcontrGuarB> rzPrjcontrGuarBList = new ArrayList<RzPrjcontrGuarB>();
		if(rzPrjcontrGuarBArray != null && rzPrjcontrGuarBArray.length > 0){
			rzPrjcontrGuarBList = Arrays.asList(rzPrjcontrGuarBArray);
			if(!rzPrjcontrGuarBList.isEmpty()){
				for(RzPrjcontrGuarB rzPrjcontrGuarB : rzPrjcontrGuarBList){
					//设置主表主键
					rzPrjcontrGuarB.setPkPrjcontrGuar(pk);
					//设置合同主键
					rzPrjcontrGuarB.setPkPrjcontr(rzPrjcontrGuar.getPkPrjcontr());
					//设置子表主键
					rzPrjcontrGuarB.setPkPrjcontrGuarB(primaryKeyIdWorker.getPrimaryKey());
				}
			}
		}
		rzPrjcontrGuarDao.insertRzPrjcontrGuar(rzPrjcontrGuar);
		if(!rzPrjcontrGuarBList.isEmpty()){
			rzPrjcontrGuarBDao.insertBatchRzPrjcontrGuarB(rzPrjcontrGuarBList);
		}
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrGuar>
	 * @return
	 */
	public void insertBatchRzPrjcontrGuar(List<RzPrjcontrGuar> rzPrjcontrGuarList){
		if(rzPrjcontrGuarList != null){
			for(int i=0;i<rzPrjcontrGuarList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrGuarList.get(i).setPkPrjcontrGuar(pk);
			}
			rzPrjcontrGuarDao.insertBatchRzPrjcontrGuar(rzPrjcontrGuarList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarById(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return rzPrjcontrGuarDao.deleteRzPrjcontrGuarById(rzPrjcontrGuarQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrGuarByCondition(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return rzPrjcontrGuarDao.deleteRzPrjcontrGuarByCondition(rzPrjcontrGuarQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrGuarByBatchId(RzPrjcontrGuarQuery rzPrjcontrGuarQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrGuarQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrGuarDao.deleteRzPrjcontrGuarByBatchId(data);
			if(flat){
				if(batchIdArr.length > 0){
					for(String batchId : batchIdArr){
						RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery = new RzPrjcontrGuarBQuery();
						rzPrjcontrGuarBQuery.setPkPrjcontrGuar(batchId);
						List<RzPrjcontrGuarB> rzPrjcontrGuarBList = rzPrjcontrGuarBDao.getRzPrjcontrGuarBAll(rzPrjcontrGuarBQuery);
						if(ToolUtils.isNotEmptyCollection(rzPrjcontrGuarBList)){
							List<String> childIdList = new ArrayList<String>();
							for(RzPrjcontrGuarB rzPrjcontrGuarB : rzPrjcontrGuarBList){
								childIdList.add(rzPrjcontrGuarB.getPkPrjcontrGuarB());
							}
							if(!childIdList.isEmpty()){
								String[] childIds = childIdList.toArray(new String[0]);
								Map<String, Object> data1 = new Hashtable<String, Object>();
								data1.put("batchId1",childIds);
								rzPrjcontrGuarBDao.deleteRzPrjcontrGuarBByBatchId(data1);
							}
						}
					}
				}
			}
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrGuar
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrGuar rzPrjcontrGuar) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrGuar!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrGuar.getPkPrjcontrGuar())){
					updateRzPrjcontrGuarById(rzPrjcontrGuar);
				}else{
					insertRzPrjcontrGuar(rzPrjcontrGuar);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrGuar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrGuar
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrGuarById(RzPrjcontrGuar rzPrjcontrGuar){
		return rzPrjcontrGuarDao.updateRzPrjcontrGuarById(rzPrjcontrGuar);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrGuarByCondition(RzPrjcontrGuarQuery record,RzPrjcontrGuarQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrGuarDao.updateRzPrjcontrGuarByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	public Result updateRzPrjcontrGuarByBatchId(List<RzPrjcontrGuar> rzPrjcontrGuarList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrGuarDao.updateRzPrjcontrGuarByBatchId(rzPrjcontrGuarList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public RzPrjcontrGuar getRzPrjcontrGuarById(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return rzPrjcontrGuarDao.getRzPrjcontrGuarById(rzPrjcontrGuarQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrGuar> getRzPrjcontrGuarAll(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return rzPrjcontrGuarDao.getRzPrjcontrGuarAll(rzPrjcontrGuarQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrGuar> getRzPrjcontrGuarByPage(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrGuarQuery!=null&&StringUtils.isBlank(rzPrjcontrGuarQuery.getSort())){
			rzPrjcontrGuarQuery.setSort("pk_prjcontr_guar");
			rzPrjcontrGuarQuery.setOrder("desc");;
		}
		int total = rzPrjcontrGuarDao.getRzPrjcontrGuarByPageCount(rzPrjcontrGuarQuery);
		PaginatedList<RzPrjcontrGuar> rzPrjcontrGuarPageList = new MysqlPaginatedArrayList<RzPrjcontrGuar>(rzPrjcontrGuarQuery,total);
		List<RzPrjcontrGuar> rzPrjcontrGuarList = rzPrjcontrGuarDao.getRzPrjcontrGuarByPage(rzPrjcontrGuarQuery);
		rzPrjcontrGuarPageList.addAll(rzPrjcontrGuarList);
		GridResult<RzPrjcontrGuar> result = new GridResult<RzPrjcontrGuar>(rzPrjcontrGuarPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrGuarQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrGuarByPageCount(RzPrjcontrGuarQuery rzPrjcontrGuarQuery){
		return rzPrjcontrGuarDao.getRzPrjcontrGuarByPageCount(rzPrjcontrGuarQuery);
	}

	public void setRzPrjcontrGuarDao(RzPrjcontrGuarDao  rzPrjcontrGuarDao){
		this.rzPrjcontrGuarDao = rzPrjcontrGuarDao;
	}

	public RzPrjcontrGuarBDao getRzPrjcontrGuarBDao() {
		return rzPrjcontrGuarBDao;
	}

	public void setRzPrjcontrGuarBDao(RzPrjcontrGuarBDao rzPrjcontrGuarBDao) {
		this.rzPrjcontrGuarBDao = rzPrjcontrGuarBDao;
	}
}