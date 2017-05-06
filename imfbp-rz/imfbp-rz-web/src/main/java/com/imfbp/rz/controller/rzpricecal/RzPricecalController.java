package com.imfbp.rz.controller.rzpricecal;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.composite.util.ObjectUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ifbp.boss.rpc.currency.domain.query.CurrencyQuery;
import com.ifbp.boss.rpc.currency.service.BossCurrencyRpcService;
import com.ifbp.boss.rpc.dic.domain.RpcDicItem;
import com.ifbp.boss.rpc.dic.service.DicItemRpcService;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;
import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;
import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;
import com.imfbp.rz.service.rzpricecal.RzPricecalService;
import com.imfbp.rz.service.rzpricecaleqpt.RzPricecalEqptService;
import com.imfbp.rz.service.rzpricecallease.RzPricecalLeaseService;
import com.imfbp.rz.service.rzrate.RzRateService;
import com.imfbp.rz.service.rzrateb.RzRateBService;
import com.imfbp.rz.service.rzrateprd.RzRateprdService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ExcelWriterTool;
import com.imfbp.rz.util.ToolUtils;
import com.imfbp.rz.util.WebUtils;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.spring.mvc.controller.BaseController;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzPricecalController extends BaseController{

	private RzPricecalService rzPricecalService;
	@Autowired
	private RzRateService rzRateService;
	@Autowired
	private RzRateBService rzRateBService;
	@Autowired
	private RzRateprdService rzRateprdService;
	@Autowired
	private BossCurrencyRpcService currencyRpcClient;
	@Autowired
	private DicItemRpcService dicItemRpcClient;
	@Autowired
	private RzPricecalLeaseService rzPricecalLeaseService;
	@Autowired
	private RzPricecalEqptService rzPricecalEqptService;
    
	/**
	 *  跳转到RzPricecal首页
	 * @param rzPricecalQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecal/toRzPricecalPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPricecalPage(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPricecal/rzPricecal");
		return mv;
	}
    
	/**
	 *  查询所有
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecal/getRzPricecalAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalAll(RzPricecalQuery rzPricecalQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		List<RzPricecal> rzPricecalList = rzPricecalService.getRzPricecalAll(rzPricecalQuery);
		return rzPricecalList;
	}

	/**
	 * 分页查询
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecal/getRzPricecalByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalByPage(RzPricecalQuery rzPricecalQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		GridResult<RzPricecal> gridResult = rzPricecalService.getRzPricecalByPage(rzPricecalQuery);
		return gridResult;
	}

	/**
	 * 根据id查询
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecal/getRzPricecalById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPricecalById(RzPricecalQuery rzPricecalQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		RzPricecal rzPricecal = rzPricecalService.getRzPricecalById(rzPricecalQuery);
		return rzPricecal;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPricecal/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPricecal rzPricecal,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(rzPricecal == null) rzPricecal=new RzPricecal();
		Result result = rzPricecalService.insertOrUpdate(rzPricecal);
		return result;
	}
    
	/**
	 *  根据Id批量删除 (真正删除数据库数据)
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPricecal/deleteRzPricecalByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPricecalByBatchId(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		Result result = rzPricecalService.deleteRzPricecalByBatchId(rzPricecalQuery);
		return result;
	}
	
	/**
	 * 根据Id批量逻辑删除(修改数据库数据为删除状态)
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPricecal/logicDeleteRzPricecalByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPricecalByBatchId(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		Result result = rzPricecalService.logicDeleteRzPricecalByBatchId(rzPricecalQuery);
		return result;
	}
	//获取组织机构，部门等初始数据
	@RequestMapping(value = "rzPricecal/getInitData", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getInitData(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		Result result = new Result();
		result.setSuccess(true);
		String pkOrg =getOrgId();
		String pkDept=getDeptId();
		String userId=getUserId();
		String time=DateUtil.getTs();
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=format.format(date);
		String today=currentDate.split("-")[2];
		result.addDefaultModel("currentDate",currentDate);
		result.addDefaultModel("pkOrg",pkOrg);
		result.addDefaultModel("pkDept",pkDept);
		result.addDefaultModel("userId",userId);
		result.addDefaultModel("time",time);
		result.addDefaultModel("today",Integer.valueOf(today));
		return result;
	}
	@RequestMapping(value = "rzPricecal/getAllRzEqpt", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllRzEqpt(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(rzPricecalQuery == null) rzPricecalQuery=new RzPricecalQuery();
		List<RzEqpt>rzEqptList= rzPricecalService.getAllRzEqpt(rzPricecalQuery);
		Result result=new Result();
		result.setSuccess(true);
		result.addDefaultModel("rzEqptList", rzEqptList);
		return result;
	}
	//获取当前报价测算的所有设备
	@RequestMapping(value = "rzPricecal/getAllRzEqptBycal", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllRzEqptBycal(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(rzPricecalQuery == null) rzPricecalQuery=new RzPricecalQuery();
		RzPricecalEqptQuery rzPricecalEqptQuery=new RzPricecalEqptQuery();
		rzPricecalEqptQuery.setPkPricecal(rzPricecalQuery.getPkPricecal());
		List<RzPricecalEqpt>rzPricecalEqptList= rzPricecalEqptService.getRzPricecalEqptAll(rzPricecalEqptQuery);
		List<RzEqpt>rzEqptList= rzPricecalService.getAllRzEqpt(rzPricecalQuery);
		Map<String,RzEqpt>map=new HashMap<String,RzEqpt>();
		if(ToolUtils.isNotEmptyCollection(rzEqptList)){
			for(RzEqpt eq:rzEqptList) map.put(eq.getPkEqpt(), eq);
		}
		Map<String,List<Object>>result=new HashMap<String,List<Object>>();
		if(ToolUtils.isNotEmptyCollection(rzPricecalEqptList)){
			List<Object>result0=new ArrayList<Object>();
			List<Object>result1=new ArrayList<Object>();
			List<Object>result2=new ArrayList<Object>();
			List<Object>result3=new ArrayList<Object>();
			for(RzPricecalEqpt eqt:rzPricecalEqptList){
				if(map.containsKey(eqt.getPkEqpt()) && map.get(eqt.getPkEqpt())!= null){ 
					result0.add(map.get(eqt.getPkEqpt()).getPkEqpt());
					result1.add(map.get(eqt.getPkEqpt()).getEqptName());
					result2.add(eqt.getNum());
					result3.add(eqt.getPrice());
				}
			}
			result.put("pk",result0);
			result.put("name", result1);
			result.put("num",result2);
			result.put("price", result3);
			
			
		}
		return result;
	}
	//前端验证约数
	@RequestMapping(value = "rzPricecal/getRzRecMessage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRecMessage(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		 Map<String,Boolean>map=new HashMap<String,Boolean>();
		 map.put("valid",true);
		 int prd= rzPricecalQuery.getLeaseprd();
		 int rept=rzPricecalQuery.getReptcycle();
		 if(prd%rept !=0) map.put("valid",false);
		 return map;
	}
	//根据租赁期限和投放日期获取基准利率
	/**
	 * @param rzPricecalQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPricecal/getRateByLauchdateAndPrd", method = {RequestMethod.POST, RequestMethod.GET })
	public Double getRateByLauchdateAndPrd(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context){
		if(rzPricecalQuery == null) return null;
		Double result=0.0000;
	    List<RzRateprd>rzRateprdList=null;
		List<RzRateB>rzRateBList=null;
		List<RzRate>rzRateList=null;
		RzRateQuery query=new RzRateQuery();
		query.setApprovestatus(3);
    	rzRateprdList=rzRateprdService.getRzRateprdAll(new RzRateprdQuery());
		rzRateBList=rzRateBService.getRzRateBAll(new RzRateBQuery());
    	rzRateList=rzRateService.getRzRateAll(query);
		String lauchdate=rzPricecalQuery.getLaunchdate();
		int prd=rzPricecalQuery.getLeaseprd()==null?0:rzPricecalQuery.getLeaseprd()*30;
		String pkPrd=null,pkRate=null;
		if(ToolUtils.isNotEmptyCollection(rzRateprdList)){
			for(RzRateprd prdtemp:rzRateprdList){
				if(prdtemp != null){
					int start=prdtemp.getStartTerm();
					int end=prdtemp.getEndTerm();
					if(prd >start && prd<=end){
						pkPrd=prdtemp.getPkRateprd();
						break;
					}
				}
			}
		}
		if(!ObjectUtils.isEmpty(rzRateList)){
			for(RzRate rate:rzRateList){
				if(rate != null){
					String start=rate.getStartdate();
					String end=rate.getEnddate();
					if(StringUtils.isNotEmpty(end) && (lauchdate.compareTo(start)>0 || lauchdate.compareTo(start) ==0) && (lauchdate.compareTo(end)<0)){
						pkRate=rate.getPkRate();
						break;
					}else if(StringUtils.isEmpty(end)){
							if(lauchdate.compareTo(start)>0 || lauchdate.compareTo(start) ==0){
								if(rate.getApprovestatus() == 3){
									pkRate=rate.getPkRate();
									break;
							   }
							}
						}
					}
				}
		}
		if(!ObjectUtils.isEmpty(rzRateBList)){
			for(RzRateB rateb:rzRateBList){
				if(rateb != null){
					if(StringUtils.isNotEmpty(rateb.getPkRateB()) && pkPrd != null && pkRate != null 
							&& pkPrd.equals(rateb.getPkRateprd()) && pkRate.equals(rateb.getPkRate())){
						result=rateb.getRate();
						break;
					}
				}
			}
		}
		return result;
	}
	//获取所有的货币档案
	@RequestMapping(value = "rzPricecal/getAllOfCurrency", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllOfCurrency(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			return currencyRpcClient.getCurrencyAll(new CurrencyQuery());
		}
	public void setRzPricecalService(RzPricecalService rzPricecalService) {
		this.rzPricecalService = rzPricecalService;
	}
	//获取所有的租赁业务类型
	@RequestMapping(value = "rzPricecal/getAllBusyType", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllBusyType(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		 Result result=new Result();
		 try {
			List<RpcDicItem> rpcList=dicItemRpcClient.getDicItem("DIC_RZ_BUSI_TYPE");
			result.addDefaultModel("busyTypeList", rpcList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;	
			}
	//启动取消立项
	@RequestMapping(value = "rzPricecal/updateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateByBatchId(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		String tenantId=getTenantId();
		rzPricecalQuery.setDef1(tenantId);
		Result result = rzPricecalService.updateByBatchId(rzPricecalQuery);
		return result;
	}
	//导出
	@SuppressWarnings({ "unchecked", "static-access" })
	@RequestMapping(value = "rzPricecal/outputFile", method = {RequestMethod.POST, RequestMethod.GET })
	public void outputFile(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPricecalQuery == null) {
			rzPricecalQuery = new RzPricecalQuery();
		}
		//def1传递的是查询条件，def2传递的是表头
		OutputStream out;
		Map<String,String> map = new LinkedHashMap<String,String>();//excel表头
		//Map<String,List<String>>transData=new HashMap<String,List<String>>();//需要翻译的数据
		String def1=rzPricecalQuery.getDef1();
		String def2=rzPricecalQuery.getDef2();
		List<RzPricecal>rzPricecalList=new ArrayList<RzPricecal>();
		if(StringUtils.isNotEmpty(rzPricecalQuery.getDef1())){
			JSONObject jsonObj=JSONObject.fromObject(def1.trim());
			if(!jsonObj.isEmpty()) rzPricecalQuery=(RzPricecalQuery)jsonObj.toBean(jsonObj,RzPricecalQuery.class);
		    GridResult<RzPricecal>grids= rzPricecalService.getRzPricecalByPage(rzPricecalQuery);
		    rzPricecalList= grids.getRows();
		    if(ToolUtils.isNotEmptyCollection(rzPricecalList)){
		    	for(RzPricecal cal:rzPricecalList){
		    		if(cal != null){
		    			//def1暂时设置为租赁方式的名称,def2为利率类型,def3审批状态
		    			if(cal.getLeaseway() == null || cal.getLeaseway() == 0) cal.setDef1("直租");
		    			else if(cal.getLeaseway() == 1) cal.setDef1("售后回租");
		    			else cal.setDef1("其他");
		    			if(cal.getRatetype() == null || cal.getRatetype() == 0) cal.setDef2("浮动");
		    			else cal.setDef2("固定");
		    			if(cal.getApprovestatus() != null){
		    			int state=cal.getApprovestatus();
		    			switch(state){
		    			case 0:cal.setDef3("自由态");return;
		    			case 1:cal.setDef3("已提交");return;
		    			case 2:cal.setDef3("审批中");return;
		    			case 3:cal.setDef3("审批通过");return;
		    			case 4:cal.setDef3("审批不通过");return;
		    			default:cal.setDef3("自由态");return;
		    			}
		    			}else cal.setDef3("自由态");
		    		}
		    	}
		    }
			List<JSONObject>list2=JSONArray.fromObject(def2.trim());
			if(ToolUtils.isNotEmptyCollection(list2)){
				for(JSONObject json:list2){
					map.put(json.getString("title"),json.getString("field"));
			}
		}
			RefBaseQuery query=new RefBaseQuery();
			query.setTenantId(getTenantId());
			query.setOrgId(getOrgId());
			rzPricecalList=rzPricecalService.getTranslateData(rzPricecalList, def2, query);
		try {
			out = resp.getOutputStream();
			ExcelWriterTool<RzPricecal> ex = new ExcelWriterTool<RzPricecal>();
			resp.addHeader("Content-Disposition","attachment;filename='"+WebUtils.encodeFileName("报价测算.xls", req));
			resp.setContentType("application/force-download");
			ex.exportExcel("报价测算", map, rzPricecalList, out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	}
	//租金计划表导出
		@RequestMapping(value = "rzPricecal/leaseExport", method = {RequestMethod.POST, RequestMethod.GET })
		public void leaseExport(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			if (rzPricecalQuery == null) {
				rzPricecalQuery = new RzPricecalQuery();
			}
			OutputStream out;
			try {
				out = resp.getOutputStream();
				List<RzPricecalLease>rzPricecalLeaseList;
				RzPricecalLeaseQuery query=new RzPricecalLeaseQuery();
				query.setPkPricecal(rzPricecalQuery.getPkPricecal());
				rzPricecalLeaseList= rzPricecalLeaseService.getRzPricecalLeaseAll(query);
				ExcelWriterTool<RzPricecalLease> ex = new ExcelWriterTool<RzPricecalLease>();
				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("期数", "num");
				map.put("应还款日期","rptDate" );
				map.put("偿还期间", "paydur");
				map.put("期间天数", "durdays");
				map.put("租赁利率(%)", "leaseRate");
				map.put("应还款本金", "rptAmt");
				map.put("应还款利息", "rptInt");
				map.put("应还款租金", "rptRent");
				map.put("应支付手续费", "payFee");
				map.put("应还款合计", "rptTotal");
				map.put("剩余本金", "residualAmt");
				resp.addHeader("Content-Disposition",
						"attachment;filename='"+WebUtils.encodeFileName("租金计划表.xls", req));
				resp.setContentType("application/force-download");
				ex.exportExcel("租金计划表", map, rzPricecalLeaseList, out);
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//编辑页面启动取消立项
		@RequestMapping(value = "rzPricecal/updateRzPricecalByState", method = {RequestMethod.POST, RequestMethod.GET })
		public @ResponseBody Object updateRzPricecalByState(RzPricecal rzPricecal,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			if (rzPricecal == null) {
				rzPricecal = new RzPricecal();
			}
			 rzPricecal.setDef1(getTenantId());
			 return rzPricecalService.updateRzPricecalByState(rzPricecal);
		}
}