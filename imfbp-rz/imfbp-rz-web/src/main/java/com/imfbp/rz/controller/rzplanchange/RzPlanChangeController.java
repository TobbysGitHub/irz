package com.imfbp.rz.controller.rzplanchange;

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

import com.imfbp.rz.domain.rzplanchange.RzPlanChange;
import com.imfbp.rz.domain.rzplanchange.query.RzPlanChangeQuery;
import com.imfbp.rz.service.rzplanchange.RzPlanChangeService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPlanChangeController extends PubBaseContrl{

	private RzPlanChangeService rzPlanChangeService;
	
	/**
	 *  跳转到RzPlanChange首页
	 * @param rzPlanChangeQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPlanChange/toRzPlanChangePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPlanChangePage(RzPlanChangeQuery rzPlanChangeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPlanChange/rzPlanChange");
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
	@RequestMapping(value = "rzPlanChange/getRzPlanChangeAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeAll(RzPlanChangeQuery rzPlanChangeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPlanChangeQuery == null) {
			rzPlanChangeQuery = new RzPlanChangeQuery();
		}
		List<RzPlanChange> rzPlanChangeList = rzPlanChangeService.getRzPlanChangeAll(rzPlanChangeQuery);
		return rzPlanChangeList;
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
	@RequestMapping(value = "rzPlanChange/getRzPlanChangeByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeByPage(RzPlanChangeQuery rzPlanChangeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPlanChangeQuery == null) {
			rzPlanChangeQuery = new RzPlanChangeQuery();
		}
		GridResult<RzPlanChange> gridResult = rzPlanChangeService.getRzPlanChangeByPage(rzPlanChangeQuery);
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
	@RequestMapping(value = "rzPlanChange/getRzPlanChangeById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeById(RzPlanChangeQuery rzPlanChangeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPlanChange rzPlanChange = rzPlanChangeService.getRzPlanChangeById(rzPlanChangeQuery);
		return rzPlanChange;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPlanChange/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPlanChange rzPlanChange,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPlanChangeService.insertOrUpdate(rzPlanChange);
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
	@RequestMapping(value = "rzPlanChange/deleteRzPlanChangeByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPlanChangeByBatchId(RzPlanChangeQuery rzPlanChangeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPlanChangeService.deleteRzPlanChangeByBatchId(rzPlanChangeQuery);
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
	@RequestMapping(value = "rzPlanChange/logicDeleteRzPlanChangeByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPlanChangeByBatchId(RzPlanChangeQuery rzPlanChangeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPlanChangeService.logicDeleteRzPlanChangeByBatchId(rzPlanChangeQuery);
		return result;
	}
	
	public void setRzPlanChangeService(RzPlanChangeService rzPlanChangeService) {
		this.rzPlanChangeService = rzPlanChangeService;
	}

}