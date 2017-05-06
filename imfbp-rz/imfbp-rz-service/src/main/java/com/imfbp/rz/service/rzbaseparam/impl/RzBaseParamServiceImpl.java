package com.imfbp.rz.service.rzbaseparam.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzbaseparam.RzBaseParamDao;
import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.domain.rzrisktype.RzRiskType;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzrisktype.RzRiskTypeService;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import net.sf.json.JSONArray;





@Component("rzBaseParamService")
public class RzBaseParamServiceImpl implements RzBaseParamService{

	private RzRiskTypeService rzRiskTypeService;
	private RzBaseParamDao rzBaseParamDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	/**
	 * 添加
	 * @param rzBaseParam
	 * @return
	 */
	@Override
	public void insertRzBaseParam(RzBaseParam rzBaseParam){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzBaseParam.setPkBaseParam(pk);
		rzBaseParamDao.insertRzBaseParam(rzBaseParam);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzBaseParam>
	 * @return
	 */
	public void insertBatchRzBaseParam(List<RzBaseParam> rzBaseParamList){
		if(rzBaseParamList != null){
			for(int i=0;i<rzBaseParamList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				rzBaseParamList.get(i).setPkBaseParam(pk);
			}
			rzBaseParamDao.insertBatchRzBaseParam(rzBaseParamList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzBaseParamById(RzBaseParamQuery rzBaseParamQuery){
		return rzBaseParamDao.deleteRzBaseParamById(rzBaseParamQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public boolean deleteRzBaseParamByCondition(RzBaseParamQuery rzBaseParamQuery){
		return rzBaseParamDao.deleteRzBaseParamByCondition(rzBaseParamQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzBaseParamQuery
	 * @return
	 */	
	@Override
	public Result deleteRzBaseParamByBatchId(RzBaseParamQuery rzBaseParamQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzBaseParamQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzBaseParamDao.deleteRzBaseParamByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzBaseParam
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzBaseParam rzBaseParam) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzBaseParam!=null){
				if(StringUtil.isNotEmpty(rzBaseParam.getPkBaseParam())){
					updateRzBaseParamById(rzBaseParam);
					/*List<?> listTemp =JSONArray.fromObject(rzBaseParam.getDef1().trim());
					List<RzRiskType>rzRiskTypeList=new ArrayList<RzRiskType>();
					for(Object obj:listTemp) {
						JSONObject json=(JSONObject)obj;
						@SuppressWarnings("static-access")
						RzRiskType rt=(RzRiskType) json.toBean(json,RzRiskType.class);
						rzRiskTypeList.add(rt);
					}*/
					String tableStr = rzBaseParam.getDef1();
					String tableStr1 = tableStr.replace('[', '<');
					String tableStr2 = tableStr1.replace(']', '>');
					String tableStr3 = tableStr2.substring(1,tableStr2.length()-1);
					String tableStr4 = "["+tableStr3+"]";
					JSONArray jsonarray = JSONArray.fromObject(tableStr4);  
			        @SuppressWarnings("unchecked")
					List<RzRiskType> rzRiskTypeList = (List<RzRiskType>)JSONArray.toCollection(jsonarray, RzRiskType.class); 
					rzRiskTypeService.updateRzRiskTypeByBatchId(rzRiskTypeList);
				}else{
					insertRzBaseParam(rzBaseParam);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzBaseParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzBaseParam
	 * @return
	 */
	@Override
	public boolean updateRzBaseParamById(RzBaseParam rzBaseParam){
		return rzBaseParamDao.updateRzBaseParamById(rzBaseParam);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzBaseParamByCondition(RzBaseParamQuery record,RzBaseParamQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzBaseParamDao.updateRzBaseParamByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzBaseParamQuery
	 * @return
	 */
	public Result updateRzBaseParamByBatchId(List<RzBaseParam> rzBaseParamList){
		Result result = new Result(false);
		try {
			boolean flag = rzBaseParamDao.updateRzBaseParamByBatchId(rzBaseParamList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public RzBaseParam getRzBaseParamById(RzBaseParamQuery rzBaseParamQuery){
		return rzBaseParamDao.getRzBaseParamById(rzBaseParamQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public List<RzBaseParam> getRzBaseParamAll(RzBaseParamQuery rzBaseParamQuery){
		return rzBaseParamDao.getRzBaseParamAll(rzBaseParamQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public GridResult<RzBaseParam> getRzBaseParamByPage(RzBaseParamQuery rzBaseParamQuery){
		//如果排序的字段是空或者空字符串
		if(rzBaseParamQuery!=null&&StringUtils.isBlank(rzBaseParamQuery.getSort())){
			rzBaseParamQuery.setSort("pk_base_param");
			rzBaseParamQuery.setOrder("asc");;
		}
		int total = rzBaseParamDao.getRzBaseParamByPageCount(rzBaseParamQuery);
		PaginatedList<RzBaseParam> rzBaseParamPageList = new MysqlPaginatedArrayList<RzBaseParam>(rzBaseParamQuery,total);
		List<RzBaseParam> rzBaseParamList = rzBaseParamDao.getRzBaseParamByPage(rzBaseParamQuery);
		rzBaseParamPageList.addAll(rzBaseParamList);
		GridResult<RzBaseParam> result = new GridResult<RzBaseParam>(rzBaseParamPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzBaseParamQuery
	 * @return
	 */
	@Override
	public int getRzBaseParamByPageCount(RzBaseParamQuery rzBaseParamQuery){
		return rzBaseParamDao.getRzBaseParamByPageCount(rzBaseParamQuery);
	}

	public void setRzBaseParamDao(RzBaseParamDao  rzBaseParamDao){
		this.rzBaseParamDao = rzBaseParamDao;
	}

	

	public void setRzRiskTypeService(RzRiskTypeService rzRiskTypeService) {
		this.rzRiskTypeService = rzRiskTypeService;
	}
	
}