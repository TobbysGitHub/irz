package com.imfbp.rz.service.rzprjstate.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzprjstate.RzPrjStateDao;
import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.domain.rzprjstate.query.RzPrjStateQuery;
import com.imfbp.rz.service.rzprjstate.RzPrjStateService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;





@Component("rzPrjStateService")
public class RzPrjStateServiceImpl implements RzPrjStateService{


	private RzPrjStateDao rzPrjStateDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzPrjState
	 * @return
	 */
	@Override
	public void insertRzPrjState(RzPrjState rzPrjState){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjState.setTs(DateUtil.getTs());
		rzPrjState.setPkPrjState(pk);
		rzPrjStateDao.insertRzPrjState(rzPrjState);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjState>
	 * @return
	 */
	public void insertBatchRzPrjState(List<RzPrjState> rzPrjStateList){
		if(rzPrjStateList != null){
			for(int i=0;i<rzPrjStateList.size();i++){
				rzPrjStateList.get(i).setTs(DateUtil.getTs());
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjStateList.get(i).setPkPrjState(pk);
			}
			rzPrjStateDao.insertBatchRzPrjState(rzPrjStateList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjStateById(RzPrjStateQuery rzPrjStateQuery){
		return rzPrjStateDao.deleteRzPrjStateById(rzPrjStateQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjStateByCondition(RzPrjStateQuery rzPrjStateQuery){
		return rzPrjStateDao.deleteRzPrjStateByCondition(rzPrjStateQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjStateQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjStateByBatchId(RzPrjStateQuery rzPrjStateQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjStateQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjStateDao.deleteRzPrjStateByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjState
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjState rzPrjState) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjState!=null){
				if(StringUtil.isNotEmpty(rzPrjState.getPkPrjState())){
					updateRzPrjStateById(rzPrjState);
				}else{
					insertRzPrjState(rzPrjState);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjState
	 * @return
	 */
	@Override
	public boolean updateRzPrjStateById(RzPrjState rzPrjState){
		rzPrjState.setTs(DateUtil.getTs());
		return rzPrjStateDao.updateRzPrjStateById(rzPrjState);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjStateByCondition(RzPrjStateQuery record,RzPrjStateQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjStateDao.updateRzPrjStateByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjStateQuery
	 * @return
	 */
	public Result updateRzPrjStateByBatchId(List<RzPrjState> rzPrjStateList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjStateDao.updateRzPrjStateByBatchId(rzPrjStateList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public RzPrjState getRzPrjStateById(RzPrjStateQuery rzPrjStateQuery){
		return rzPrjStateDao.getRzPrjStateById(rzPrjStateQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public List<RzPrjState> getRzPrjStateAll(RzPrjStateQuery rzPrjStateQuery){
		return rzPrjStateDao.getRzPrjStateAll(rzPrjStateQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjState> getRzPrjStateByPage(RzPrjStateQuery rzPrjStateQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjStateQuery!=null&&StringUtils.isBlank(rzPrjStateQuery.getSort())){
			rzPrjStateQuery.setSort("pk_prj_state");
			rzPrjStateQuery.setOrder("desc");;
		}
		int total = rzPrjStateDao.getRzPrjStateByPageCount(rzPrjStateQuery);
		PaginatedList<RzPrjState> rzPrjStatePageList = new MysqlPaginatedArrayList<RzPrjState>(rzPrjStateQuery,total);
		List<RzPrjState> rzPrjStateList = rzPrjStateDao.getRzPrjStateByPage(rzPrjStateQuery);
		rzPrjStatePageList.addAll(rzPrjStateList);
		GridResult<RzPrjState> result = new GridResult<RzPrjState>(rzPrjStatePageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjStateQuery
	 * @return
	 */
	@Override
	public int getRzPrjStateByPageCount(RzPrjStateQuery rzPrjStateQuery){
		return rzPrjStateDao.getRzPrjStateByPageCount(rzPrjStateQuery);
	}

	public void setRzPrjStateDao(RzPrjStateDao  rzPrjStateDao){
		this.rzPrjStateDao = rzPrjStateDao;
	}
	
	
	/******
	 * 
	 * 项目状态更新
	 * 
	 * @param rzPrjState
	 */
	public void insertOrUpdatePrjState(RzPrjState rzPrjState) {
		RzPrjStateQuery rzPrjStateQuery = new RzPrjStateQuery();
		rzPrjStateQuery.setPkPricecal(rzPrjState.getPkPricecal());
		rzPrjStateQuery.setPkPrjapply(rzPrjState.getPkPrjapply());

		List<RzPrjState> rzPrjStateList = this.getRzPrjStateAll(rzPrjStateQuery);

		RzPrjState rzPrjStateTmp = null;
		if (ToolUtils.isNotEmptyCollection(rzPrjStateList)) {
			rzPrjStateTmp = rzPrjStateList.get(0);
			String pkPrjapply = rzPrjStateTmp.getPkPrjapply();
			String pkPricecal = rzPrjStateTmp.getPkPricecal();
			if (StringUtil.isNotEmpty(pkPrjapply) && StringUtil.isEmpty(pkPricecal)) {
				rzPrjState.setPrjState(null);
			}
			rzPrjState.setPkPrjState(rzPrjStateTmp.getPkPrjState());
		}
		insertOrUpdate(rzPrjState);
	}

}