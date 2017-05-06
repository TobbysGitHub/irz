package com.imfbp.rz.controller.rzdefinterestplan;

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

import com.imfbp.rz.domain.rzdefinterestplan.RzDefInterestPlan;
import com.imfbp.rz.domain.rzdefinterestplan.query.RzDefInterestPlanQuery;
import com.imfbp.rz.service.rzdefinterestplan.RzDefInterestPlanService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzDefInterestPlanController extends PubBaseContrl{

	private RzDefInterestPlanService rzDefInterestPlanService;
	
	/**
	 *  跳转到RzDefInterestPlan首页
	 * @param rzDefInterestPlanQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzDefInterestPlan/toRzDefInterestPlanPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzDefInterestPlanPage(RzDefInterestPlanQuery rzDefInterestPlanQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzDefInterestPlan/rzDefInterestPlan");
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
	@RequestMapping(value = "rzDefInterestPlan/getRzDefInterestPlanAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzDefInterestPlanAll(RzDefInterestPlanQuery rzDefInterestPlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzDefInterestPlanQuery == null) {
			rzDefInterestPlanQuery = new RzDefInterestPlanQuery();
		}
		List<RzDefInterestPlan> rzDefInterestPlanList = rzDefInterestPlanService.getRzDefInterestPlanAll(rzDefInterestPlanQuery);
		return rzDefInterestPlanList;
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
	@RequestMapping(value = "rzDefInterestPlan/getRzDefInterestPlanByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzDefInterestPlanByPage(RzDefInterestPlanQuery rzDefInterestPlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzDefInterestPlanQuery == null) {
			rzDefInterestPlanQuery = new RzDefInterestPlanQuery();
		}
		GridResult<RzDefInterestPlan> gridResult = rzDefInterestPlanService.getRzDefInterestPlanByPage(rzDefInterestPlanQuery);
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
	@RequestMapping(value = "rzDefInterestPlan/getRzDefInterestPlanById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzDefInterestPlanById(RzDefInterestPlanQuery rzDefInterestPlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzDefInterestPlan rzDefInterestPlan = rzDefInterestPlanService.getRzDefInterestPlanById(rzDefInterestPlanQuery);
		return rzDefInterestPlan;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzDefInterestPlan/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzDefInterestPlan rzDefInterestPlan,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzDefInterestPlanService.insertOrUpdate(rzDefInterestPlan);
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
	@RequestMapping(value = "rzDefInterestPlan/deleteRzDefInterestPlanByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzDefInterestPlanByBatchId(RzDefInterestPlanQuery rzDefInterestPlanQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzDefInterestPlanService.deleteRzDefInterestPlanByBatchId(rzDefInterestPlanQuery);
		return result;
	}
	
	
	public void setRzDefInterestPlanService(RzDefInterestPlanService rzDefInterestPlanService) {
		this.rzDefInterestPlanService = rzDefInterestPlanService;
	}

}