package com.imfbp.rz.controller.rzoverduederateplan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzoverduederateplan.RzOverdueDeratePlan;
import com.imfbp.rz.domain.rzoverduederateplan.query.RzOverdueDeratePlanQuery;
import com.imfbp.rz.service.rzoverduederateplan.RzOverdueDeratePlanService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzOverdueDeratePlanController extends PubBaseContrl{

	private RzOverdueDeratePlanService rzOverdueDeratePlanService;
	
	/**
	 *  跳转到RzOverdueDeratePlan首页
	 * @param rzOverdueDeratePlanQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzOverdueDeratePlan/toRzOverdueDeratePlanPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzOverdueDeratePlanPage(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzOverdueDeratePlan/rzOverdueDeratePlan");
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
	@RequestMapping(value = "rzOverdueDeratePlan/getRzOverdueDeratePlanAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzOverdueDeratePlanAll(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzOverdueDeratePlanQuery == null) {
			rzOverdueDeratePlanQuery = new RzOverdueDeratePlanQuery();
		}
		List<RzOverdueDeratePlan> rzOverdueDeratePlanList = rzOverdueDeratePlanService.getRzOverdueDeratePlanAll(rzOverdueDeratePlanQuery);
		return rzOverdueDeratePlanList;
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
	@RequestMapping(value = "rzOverdueDeratePlan/getRzOverdueDeratePlanByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzOverdueDeratePlanByPage(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzOverdueDeratePlanQuery == null) {
			rzOverdueDeratePlanQuery = new RzOverdueDeratePlanQuery();
		}
		GridResult<RzOverdueDeratePlan> gridResult = rzOverdueDeratePlanService.getRzOverdueDeratePlanByPage(rzOverdueDeratePlanQuery);
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
	@RequestMapping(value = "rzOverdueDeratePlan/getRzOverdueDeratePlanById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzOverdueDeratePlanById(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzOverdueDeratePlan rzOverdueDeratePlan = rzOverdueDeratePlanService.getRzOverdueDeratePlanById(rzOverdueDeratePlanQuery);
		return rzOverdueDeratePlan;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzOverdueDeratePlan/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzOverdueDeratePlan rzOverdueDeratePlan,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzOverdueDeratePlanService.insertOrUpdate(rzOverdueDeratePlan);
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
	@RequestMapping(value = "rzOverdueDeratePlan/deleteRzOverdueDeratePlanByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzOverdueDeratePlanByBatchId(RzOverdueDeratePlanQuery rzOverdueDeratePlanQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzOverdueDeratePlanService.deleteRzOverdueDeratePlanByBatchId(rzOverdueDeratePlanQuery);
		return result;
	}
	
	
	public void setRzOverdueDeratePlanService(RzOverdueDeratePlanService rzOverdueDeratePlanService) {
		this.rzOverdueDeratePlanService = rzOverdueDeratePlanService;
	}

}