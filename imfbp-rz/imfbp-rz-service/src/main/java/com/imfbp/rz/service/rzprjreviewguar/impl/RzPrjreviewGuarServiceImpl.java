package com.imfbp.rz.service.rzprjreviewguar.impl;

import java.util.ArrayList;
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
import com.imfbp.rz.domain.rzprjreviewguar.RzPrjreviewGuar;
import com.imfbp.rz.domain.rzprjreviewguar.query.RzPrjreviewGuarQuery;
import com.imfbp.rz.domain.rzprjreviewguarb.RzPrjreviewGuarB;
import com.imfbp.rz.dao.rzprjreviewguar.RzPrjreviewGuarDao;
import com.imfbp.rz.dao.rzprjreviewguarb.RzPrjreviewGuarBDao;
import com.imfbp.rz.service.rzprjreviewguar.RzPrjreviewGuarService;





@Component("rzPrjreviewGuarService")
public class RzPrjreviewGuarServiceImpl implements RzPrjreviewGuarService{


	private RzPrjreviewGuarDao rzPrjreviewGuarDao;
	private RzPrjreviewGuarBDao rzPrjreviewGuarBDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjreviewGuar
	 * @return
	 */
	@Override
	public void insertRzPrjreviewGuar(RzPrjreviewGuar rzPrjreviewGuar){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjreviewGuar.setPkPrjreviewGuar(pk);
		RzPrjreviewGuarB[] rzPrjreviewGuarBArray=rzPrjreviewGuar.getRzPrjreviewGuarBArray();
		List<RzPrjreviewGuarB> rzPrjreviewGuarBList=new ArrayList<RzPrjreviewGuarB>();
		if(rzPrjreviewGuarBArray!=null&&rzPrjreviewGuarBArray.length>0){
			for (int i = 0; i < rzPrjreviewGuarBArray.length; i++) {
				RzPrjreviewGuarB rzPrjreviewGuarB=new RzPrjreviewGuarB();
				rzPrjreviewGuarB.setAddress(rzPrjreviewGuarBArray[i].getAddress());
				rzPrjreviewGuarB.setMortgageName(rzPrjreviewGuarBArray[i].getMortgageName());
				rzPrjreviewGuarB.setMortgageQuality(rzPrjreviewGuarBArray[i].getMortgageQuality());
				rzPrjreviewGuarB.setPkPrjreview(rzPrjreviewGuar.getPkPrjreview());
				rzPrjreviewGuarB.setPkPrjreviewGuar(pk);
				rzPrjreviewGuarB.setProdDate(rzPrjreviewGuarBArray[i].getProdDate());
				rzPrjreviewGuarB.setSituation(rzPrjreviewGuarBArray[i].getSituation());
				rzPrjreviewGuarB.setCurrVal(rzPrjreviewGuarBArray[i].getCurrVal());
				rzPrjreviewGuarB.setMortgageNum(rzPrjreviewGuarBArray[i].getMortgageNum());
				rzPrjreviewGuarB.setMortgageState(rzPrjreviewGuarBArray[i].getMortgageState());
				rzPrjreviewGuarB.setPkPrjreview(rzPrjreviewGuar.getPkPrjreview());
				String pkPrjreviewGuarB = primaryKeyUtil.getPrimaryKey();
				rzPrjreviewGuarB.setPkPrjreviewGuarB(pkPrjreviewGuarB);
				rzPrjreviewGuarBList.add(rzPrjreviewGuarB);
			}
			rzPrjreviewGuarBDao.insertBatchRzPrjreviewGuarB(rzPrjreviewGuarBList);
		}
		rzPrjreviewGuarDao.insertRzPrjreviewGuar(rzPrjreviewGuar);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjreviewGuar>
	 * @return
	 */
	public void insertBatchRzPrjreviewGuar(List<RzPrjreviewGuar> rzPrjreviewGuarList){
		if(rzPrjreviewGuarList != null){
			for(int i=0;i<rzPrjreviewGuarList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjreviewGuarList.get(i).setPkPrjreviewGuar(pk);
			}
			rzPrjreviewGuarDao.insertBatchRzPrjreviewGuar(rzPrjreviewGuarList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarById(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return rzPrjreviewGuarDao.deleteRzPrjreviewGuarById(rzPrjreviewGuarQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjreviewGuarByCondition(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return rzPrjreviewGuarDao.deleteRzPrjreviewGuarByCondition(rzPrjreviewGuarQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjreviewGuarByBatchId(RzPrjreviewGuarQuery rzPrjreviewGuarQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjreviewGuarQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjreviewGuarDao.deleteRzPrjreviewGuarByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjreviewGuar
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjreviewGuar rzPrjreviewGuar) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjreviewGuar!=null){
				if(StringUtil.isNotEmpty(rzPrjreviewGuar.getPkPrjreviewGuar())){
					updateRzPrjreviewGuarById(rzPrjreviewGuar);
				}else{
					insertRzPrjreviewGuar(rzPrjreviewGuar);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjreviewGuar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjreviewGuar
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewGuarById(RzPrjreviewGuar rzPrjreviewGuar){
		return rzPrjreviewGuarDao.updateRzPrjreviewGuarById(rzPrjreviewGuar);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjreviewGuarByCondition(RzPrjreviewGuarQuery record,RzPrjreviewGuarQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjreviewGuarDao.updateRzPrjreviewGuarByCondition(data);
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public RzPrjreviewGuar getRzPrjreviewGuarById(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return rzPrjreviewGuarDao.getRzPrjreviewGuarById(rzPrjreviewGuarQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public List<RzPrjreviewGuar> getRzPrjreviewGuarAll(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return rzPrjreviewGuarDao.getRzPrjreviewGuarAll(rzPrjreviewGuarQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjreviewGuar> getRzPrjreviewGuarByPage(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjreviewGuarQuery!=null&&StringUtils.isBlank(rzPrjreviewGuarQuery.getSort())){
			rzPrjreviewGuarQuery.setSort("pk_prjreview_guar");
			rzPrjreviewGuarQuery.setOrder("desc");;
		}
		int total = rzPrjreviewGuarDao.getRzPrjreviewGuarByPageCount(rzPrjreviewGuarQuery);
		PaginatedList<RzPrjreviewGuar> rzPrjreviewGuarPageList = new MysqlPaginatedArrayList<RzPrjreviewGuar>(rzPrjreviewGuarQuery,total);
		List<RzPrjreviewGuar> rzPrjreviewGuarList = rzPrjreviewGuarDao.getRzPrjreviewGuarByPage(rzPrjreviewGuarQuery);
		rzPrjreviewGuarPageList.addAll(rzPrjreviewGuarList);
		GridResult<RzPrjreviewGuar> result = new GridResult<RzPrjreviewGuar>(rzPrjreviewGuarPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjreviewGuarQuery
	 * @return
	 */
	@Override
	public int getRzPrjreviewGuarByPageCount(RzPrjreviewGuarQuery rzPrjreviewGuarQuery){
		return rzPrjreviewGuarDao.getRzPrjreviewGuarByPageCount(rzPrjreviewGuarQuery);
	}

	public void setRzPrjreviewGuarDao(RzPrjreviewGuarDao  rzPrjreviewGuarDao){
		this.rzPrjreviewGuarDao = rzPrjreviewGuarDao;
	}

	public RzPrjreviewGuarBDao getRzPrjreviewGuarBDao() {
		return rzPrjreviewGuarBDao;
	}

	public void setRzPrjreviewGuarBDao(RzPrjreviewGuarBDao rzPrjreviewGuarBDao) {
		this.rzPrjreviewGuarBDao = rzPrjreviewGuarBDao;
	}
	
}