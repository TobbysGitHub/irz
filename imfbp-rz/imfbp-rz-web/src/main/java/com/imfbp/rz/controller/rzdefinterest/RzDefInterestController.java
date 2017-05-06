package com.imfbp.rz.controller.rzdefinterest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;
import com.imfbp.rz.domain.rzdefinterest.RzDefInterest;
import com.imfbp.rz.domain.rzdefinterest.query.RzDefInterestQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzdefinterest.RzDefInterestService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzDefInterestController extends PubBaseContrl{

	private RzDefInterestService rzDefInterestService;
	@Autowired
	private RzBaseParamService rzBaseParamService;
	@Autowired
	private RzContrTallyService rzContrTallyService;
	/**
	 *  跳转到RzDefInterest首页
	 * @param rzDefInterestQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzDefInterest/toRzDefInterestPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzDefInterestPage(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzDefInterest/rzDefInterest");
		attachLoginInfo(mv);
		attachModuleValue(mv,req);
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
	@RequestMapping(value = "rzDefInterest/getRzDefInterestAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzDefInterestAll(RzDefInterestQuery rzDefInterestQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzDefInterestQuery == null) {
			rzDefInterestQuery = new RzDefInterestQuery();
		}
		List<RzDefInterest> rzDefInterestList = rzDefInterestService.getRzDefInterestAll(rzDefInterestQuery);
		return rzDefInterestList;
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
	@RequestMapping(value = "rzDefInterest/getRzDefInterestByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzDefInterestByPage(RzDefInterestQuery rzDefInterestQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzDefInterestQuery == null) {
			rzDefInterestQuery = new RzDefInterestQuery();
		}
		GridResult<RzDefInterest> gridResult = rzDefInterestService.getRzDefInterestByPage(rzDefInterestQuery);
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
	@RequestMapping(value = "rzDefInterest/getRzDefInterestById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzDefInterestById(RzDefInterestQuery rzDefInterestQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzDefInterest rzDefInterest = rzDefInterestService.getRzDefInterestById(rzDefInterestQuery);
		return rzDefInterest;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzDefInterest/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzDefInterest rzDefInterest,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(rzDefInterest == null) rzDefInterest=new RzDefInterest();
		Result result = rzDefInterestService.insertOrUpdate(rzDefInterest);
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
	@RequestMapping(value = "rzDefInterest/deleteRzDefInterestByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzDefInterestByBatchId(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzDefInterestService.deleteRzDefInterestByBatchId(rzDefInterestQuery);
		return result;
	}
	//根据id 删除
	@RequestMapping(value = "rzDefInterest/deleteRzDefInterestById", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzDefInterestById(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		boolean flag = rzDefInterestService.deleteRzDefInterestById(rzDefInterestQuery);
		return flag;
	}
	
	//根据合同编码获取台账表中合同数据
	@RequestMapping(value = "rzDefInterest/getTallyByCode", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTallyByCode(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(rzDefInterestQuery == null)rzDefInterestQuery=new RzDefInterestQuery();
		RzContrTallyQuery  rzContrTallyQuery=new RzContrTallyQuery();
		rzContrTallyQuery.setContrCode(rzDefInterestQuery.getContrCode());
		List<RzContrTally>rzContrTallyList=rzContrTallyService.getRzContrTallyAll(rzContrTallyQuery);
		RzContrTally tally=null;
		 if(ToolUtils.isNotEmptyCollection(rzContrTallyList)){
			 int maxSq=0;
			 for(RzContrTally tal:rzContrTallyList){
				 if(tal != null && tal.getOperSeq()!= null && tal.getOperSeq()>maxSq){
					 maxSq=tal.getOperSeq();
					 tally=tal;
				 }
			 }
		 }
		 return tally;
		}
	/**
	 * 根据Id批量逻辑删除(修改数据库数据为删除状态)
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzDefInterest/logicDeleteRzDefInterestByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzDefInterestByBatchId(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzDefInterestService.logicDeleteRzDefInterestByBatchId(rzDefInterestQuery);
		return result;
	}
	//获取组织机构，部门等初始数据
		@RequestMapping(value = "rzDefInterest/getInitData", method = {RequestMethod.POST, RequestMethod.GET })
		public @ResponseBody Object getInitData(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			if (rzDefInterestQuery == null) {
				rzDefInterestQuery = new RzDefInterestQuery();
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
			List<RzBaseParam>list=rzBaseParamService.getRzBaseParamAll(new RzBaseParamQuery());
			result.addDefaultModel("currentDate",currentDate);
			result.addDefaultModel("pkOrg",pkOrg);
			result.addDefaultModel("pkDept",pkDept);
			result.addDefaultModel("userId",userId);
			result.addDefaultModel("time",time);
			result.addDefaultModel("today",Integer.valueOf(today));
			if(ToolUtils.isNotEmptyCollection(list))result.addDefaultModel("rpmtGrace",list.get(0).getRpmtTerm());
			return result;
		}
		//根据合同的主键获取收付管理主键，从而获取租金计划表主键信息
		@RequestMapping(value = "rzDefInterest/getPlanByContr", method = { RequestMethod.POST,RequestMethod.GET })
		public @ResponseBody Object getPlanByContr(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			return rzDefInterestService.getPlanByContr(rzDefInterestQuery);
		}
		//前端校验合同是否有逾期
		@RequestMapping(value = "rzDefInterest/getRzDefMessage", method = {RequestMethod.POST, RequestMethod.GET })
		public @ResponseBody Object getRzDefMessage(RzDefInterestQuery rzDefInterestQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			if (rzDefInterestQuery == null) {
				rzDefInterestQuery = new RzDefInterestQuery();
			}
			 Map<String,Boolean>map=new HashMap<String,Boolean>();
			 map.put("valid",true);
			 Map<String,Object>dataMap=rzDefInterestService.getLeaseByContr(rzDefInterestQuery);
			if(dataMap != null){
				@SuppressWarnings("unchecked")
				List<RzPmtPlanLease>list=(List<RzPmtPlanLease>) dataMap.get("rzPmtPlanLeaseLists");
				if(ToolUtils.isEmptyCollection(list)){
					map.put("valid",false);
				}
			}
			return map;
		}
	public void setRzDefInterestService(RzDefInterestService rzDefInterestService) {
		this.rzDefInterestService = rzDefInterestService;
	}

}