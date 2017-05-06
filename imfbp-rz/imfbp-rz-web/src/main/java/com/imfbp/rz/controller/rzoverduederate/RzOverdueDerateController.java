package com.imfbp.rz.controller.rzoverduederate;

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

import com.imfbp.rz.domain.rzoverduederate.RzOverdueDerate;
import com.imfbp.rz.domain.rzoverduederate.query.RzOverdueDerateQuery;
import com.imfbp.rz.service.rzoverduederate.RzOverdueDerateService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzOverdueDerateController extends PubBaseContrl{

	private RzOverdueDerateService rzOverdueDerateService;
	
	/**
	 *  跳转到RzOverdueDerate首页
	 * @param rzOverdueDerateQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzOverdueDerate/toRzOverdueDeratePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzOverdueDeratePage(RzOverdueDerateQuery rzOverdueDerateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzOverdueDerate/rzOverdueDerate");
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
	@RequestMapping(value = "rzOverdueDerate/getRzOverdueDerateAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzOverdueDerateAll(RzOverdueDerateQuery rzOverdueDerateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzOverdueDerateQuery == null) {
			rzOverdueDerateQuery = new RzOverdueDerateQuery();
		}
		List<RzOverdueDerate> rzOverdueDerateList = rzOverdueDerateService.getRzOverdueDerateAll(rzOverdueDerateQuery);
		return rzOverdueDerateList;
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
	@RequestMapping(value = "rzOverdueDerate/getRzOverdueDerateByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzOverdueDerateByPage(RzOverdueDerateQuery rzOverdueDerateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzOverdueDerateQuery == null) {
			rzOverdueDerateQuery = new RzOverdueDerateQuery();
		}
		GridResult<RzOverdueDerate> gridResult = rzOverdueDerateService.getRzOverdueDerateByPage(rzOverdueDerateQuery);
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
	@RequestMapping(value = "rzOverdueDerate/getRzOverdueDerateById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzOverdueDerateById(RzOverdueDerateQuery rzOverdueDerateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzOverdueDerate rzOverdueDerate = rzOverdueDerateService.getRzOverdueDerateById(rzOverdueDerateQuery);
		return rzOverdueDerate;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzOverdueDerate/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzOverdueDerate rzOverdueDerate,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzOverdueDerateService.insertOrUpdate(rzOverdueDerate);
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
	@RequestMapping(value = "rzOverdueDerate/deleteRzOverdueDerateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzOverdueDerateByBatchId(RzOverdueDerateQuery rzOverdueDerateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzOverdueDerateService.deleteRzOverdueDerateByBatchId(rzOverdueDerateQuery);
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
	@RequestMapping(value = "rzOverdueDerate/logicDeleteRzOverdueDerateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzOverdueDerateByBatchId(RzOverdueDerateQuery rzOverdueDerateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzOverdueDerateService.logicDeleteRzOverdueDerateByBatchId(rzOverdueDerateQuery);
		return result;
	}
	
	public void setRzOverdueDerateService(RzOverdueDerateService rzOverdueDerateService) {
		this.rzOverdueDerateService = rzOverdueDerateService;
	}

}