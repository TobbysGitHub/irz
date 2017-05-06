package com.imfbp.rz.controller.rzpmtplanlease;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPmtPlanLeaseController extends PubBaseContrl{

	private RzPmtPlanLeaseService rzPmtPlanLeaseService;
	 @Autowired
	private RzContrTallyService rzContrTallyService;
	 @Autowired
	 private RzPmtPlanService rzPmtPlanService;
	 @Autowired
	private  RzBaseParamService rzBaseParamService;
	 @Autowired
	 private RzPrjcontrService rzPrjcontrService;
	/**
	 *  跳转到RzPmtPlanLease首页
	 * @param rzPmtPlanLeaseQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPmtPlanLease/toRzPmtPlanLeasePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPmtPlanLeasePage(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.addDefaultModel("pkPmtPlan", rzPmtPlanLeaseQuery.getPkPmtPlan());
		result.addDefaultModel("ver", rzPmtPlanLeaseQuery.getVer());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPmtPlanLease/rzPmtPlanLease");
		attachLoginInfo(mv);
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
	@RequestMapping(value = "rzPmtPlanLease/getRzPmtPlanLeaseAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanLeaseAll(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPmtPlanLeaseQuery == null) {
			rzPmtPlanLeaseQuery = new RzPmtPlanLeaseQuery();
		}
		List<RzPmtPlanLease> rzPmtPlanLeaseList = rzPmtPlanLeaseService.getRzPmtPlanLeaseAll(rzPmtPlanLeaseQuery);
		return rzPmtPlanLeaseList;
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
	@RequestMapping(value = "rzPmtPlanLease/getRzPmtPlanLeaseByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanLeaseByPage(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPmtPlanLeaseQuery == null) {
			rzPmtPlanLeaseQuery = new RzPmtPlanLeaseQuery();
		}
		if(StringUtils.isEmpty(rzPmtPlanLeaseQuery.getPkPmtPlan())) return null;
		GridResult<RzPmtPlanLease> gridResult = rzPmtPlanLeaseService.getRzPmtPlanLeaseByPage(rzPmtPlanLeaseQuery);
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
	@RequestMapping(value = "rzPmtPlanLease/getRzPmtPlanLeaseById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanLeaseById(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPmtPlanLease rzPmtPlanLease = rzPmtPlanLeaseService.getRzPmtPlanLeaseById(rzPmtPlanLeaseQuery);
		return rzPmtPlanLease;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPmtPlanLease/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPmtPlanLease rzPmtPlanLease,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPmtPlanLeaseService.insertOrUpdate(rzPmtPlanLease);
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
	@RequestMapping(value = "rzPmtPlanLease/deleteRzPmtPlanLeaseByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPmtPlanLeaseByBatchId(RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPmtPlanLeaseService.deleteRzPmtPlanLeaseByBatchId(rzPmtPlanLeaseQuery);
		return result;
	}
	
	@RequestMapping(value = "rzPmtPlanLease/updateRzPmtPlanLeaseByCondition", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateRzPmtPlanLeaseByCondition(@RequestParam("data") String data,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		List<?> listTemp= JSONArray.fromObject(data.trim());
		if(listTemp == null || listTemp.size() <=0) return null;
		List<RzPmtPlanLeaseQuery>list=new ArrayList<RzPmtPlanLeaseQuery>();
		for(Object obj:listTemp) {
			JSONObject json=(JSONObject)obj;
			@SuppressWarnings("static-access")
			RzPmtPlanLeaseQuery le=(RzPmtPlanLeaseQuery) json.toBean(json,RzPmtPlanLeaseQuery.class);
			list.add(le);
		}
		if(list.size()>0) return rzPmtPlanLeaseService.updateRzPmtPlanLeaseByCondition(list.get(0),list.get(1));
		return null;
	}
	//往台账表中插入数据
	@RequestMapping(value = "rzPmtPlanLease/insertToTally", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object insertToTally(RzPmtPlanLease rzPmtPlanLease,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		rzPmtPlanLeaseService.insertToTally(rzPmtPlanLease);
		return null;
	}
	public void setRzPmtPlanLeaseService(RzPmtPlanLeaseService rzPmtPlanLeaseService) {
		this.rzPmtPlanLeaseService = rzPmtPlanLeaseService;
	}

}