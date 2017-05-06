package com.imfbp.rz.service.rzassetriskclass.impl;

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
import com.imfbp.rz.domain.rzassetriskclass.RzAssetRiskclass;
import com.imfbp.rz.domain.rzassetriskclass.query.RzAssetRiskclassQuery;
import com.imfbp.rz.dao.rzassetriskclass.RzAssetRiskclassDao;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.rzassetriskclass.RzAssetRiskclassService;
import com.imfbp.rz.util.DateUtil;





@Component("rzAssetRiskclassService")
public class RzAssetRiskclassServiceImpl implements RzAssetRiskclassService{


	private RzAssetRiskclassDao rzAssetRiskclassDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	@Autowired
	private BillnoService billnoService;

	/**
	 * 添加
	 * @param rzAssetRiskclass
	 * @return
	 */
	@Override
	public void insertRzAssetRiskclass(RzAssetRiskclass rzAssetRiskclass){
		String pk = primaryKeyUtil.getPrimaryKey();
		
		try {
			String billNo=billnoService.getBillno(INodeConsts.RZ_ASSETRISKCLASS_NO);
			rzAssetRiskclass.setTs(DateUtil.getTs());
			rzAssetRiskclass.setDr(0);
			rzAssetRiskclass.setPkAssetRiskclass(pk);
			rzAssetRiskclass.setBillNo(billNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rzAssetRiskclassDao.insertRzAssetRiskclass(rzAssetRiskclass);	
	}
	
	/**
	 * 批量添加
	 * @param List<rzAssetRiskclass>
	 * @return
	 */
	public void insertBatchRzAssetRiskclass(List<RzAssetRiskclass> rzAssetRiskclassList){
		if(rzAssetRiskclassList != null){
			for(int i=0;i<rzAssetRiskclassList.size();i++){
				rzAssetRiskclassList.get(i).setTs(DateUtil.getTs());
				rzAssetRiskclassList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzAssetRiskclassList.get(i).setPkAssetRiskclass(pk);
			}
			rzAssetRiskclassDao.insertBatchRzAssetRiskclass(rzAssetRiskclassList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return rzAssetRiskclassDao.deleteRzAssetRiskclassById(rzAssetRiskclassQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAssetRiskclassByCondition(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return rzAssetRiskclassDao.deleteRzAssetRiskclassByCondition(rzAssetRiskclassQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */	
	@Override
	public Result deleteRzAssetRiskclassByBatchId(RzAssetRiskclassQuery rzAssetRiskclassQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzAssetRiskclassQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzAssetRiskclassDao.deleteRzAssetRiskclassByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return rzAssetRiskclassDao.logicDeleteRzAssetRiskclassById(rzAssetRiskclassQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAssetRiskclassByCondition(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return rzAssetRiskclassDao.logicDeleteRzAssetRiskclassByCondition(rzAssetRiskclassQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzAssetRiskclassByBatchId(RzAssetRiskclassQuery rzAssetRiskclassQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzAssetRiskclassQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzAssetRiskclassDao.logicDeleteRzAssetRiskclassByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzAssetRiskclass
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzAssetRiskclass rzAssetRiskclass) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzAssetRiskclass!=null){
				if(StringUtil.isNotEmpty(rzAssetRiskclass.getPkAssetRiskclass())){
					updateRzAssetRiskclassById(rzAssetRiskclass);
				}else{
					insertRzAssetRiskclass(rzAssetRiskclass);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzAssetRiskclass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzAssetRiskclass
	 * @return
	 */
	@Override
	public boolean updateRzAssetRiskclassById(RzAssetRiskclass rzAssetRiskclass){
		Integer changeTimes=rzAssetRiskclass.getChangeTimes();
		if(changeTimes==null){
			changeTimes=1;
		}else{
			changeTimes++;
		}
		rzAssetRiskclass.setChangeTimes(changeTimes);
		return rzAssetRiskclassDao.updateRzAssetRiskclassById(rzAssetRiskclass);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAssetRiskclassByCondition(RzAssetRiskclassQuery record,RzAssetRiskclassQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzAssetRiskclassDao.updateRzAssetRiskclassByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	public Result updateRzAssetRiskclassByBatchId(List<RzAssetRiskclass> rzAssetRiskclassList){
		Result result = new Result(false);
		try {
			boolean flag = rzAssetRiskclassDao.updateRzAssetRiskclassByBatchId(rzAssetRiskclassList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public RzAssetRiskclass getRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return rzAssetRiskclassDao.getRzAssetRiskclassById(rzAssetRiskclassQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public List<RzAssetRiskclass> getRzAssetRiskclassAll(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return rzAssetRiskclassDao.getRzAssetRiskclassAll(rzAssetRiskclassQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public GridResult<RzAssetRiskclass> getRzAssetRiskclassByPage(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		//如果排序的字段是空或者空字符串
		if(rzAssetRiskclassQuery!=null&&StringUtils.isBlank(rzAssetRiskclassQuery.getSort())){
			rzAssetRiskclassQuery.setSort("pk_asset_riskclass");
			rzAssetRiskclassQuery.setOrder("desc");;
		}
		int total = rzAssetRiskclassDao.getRzAssetRiskclassByPageCount(rzAssetRiskclassQuery);
		PaginatedList<RzAssetRiskclass> rzAssetRiskclassPageList = new MysqlPaginatedArrayList<RzAssetRiskclass>(rzAssetRiskclassQuery,total);
		List<RzAssetRiskclass> rzAssetRiskclassList = rzAssetRiskclassDao.getRzAssetRiskclassByPage(rzAssetRiskclassQuery);
		rzAssetRiskclassPageList.addAll(rzAssetRiskclassList);
		GridResult<RzAssetRiskclass> result = new GridResult<RzAssetRiskclass>(rzAssetRiskclassPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public int getRzAssetRiskclassByPageCount(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return rzAssetRiskclassDao.getRzAssetRiskclassByPageCount(rzAssetRiskclassQuery);
	}

	public void setRzAssetRiskclassDao(RzAssetRiskclassDao  rzAssetRiskclassDao){
		this.rzAssetRiskclassDao = rzAssetRiskclassDao;
	}
	
}