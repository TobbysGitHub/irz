package com.imfbp.rz.controller.rzrateb;

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
import com.platform.common.spring.mvc.controller.BaseController;

import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.service.rzrateb.RzRateBService;


@RestController
public class RzRateBController extends BaseController{

	private RzRateBService rzRateBService;
	
	/**
	 *  跳转到RzRateB首页
	 * @param rzRateBQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzRateB/toRzRateBPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzRateBPage(RzRateBQuery rzRateBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzRateB/rzRateB");
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
	@RequestMapping(value = "rzRateB/getRzRateBAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRateBAll(RzRateBQuery rzRateBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRateBQuery == null) {
			rzRateBQuery = new RzRateBQuery();
		}
		List<RzRateB> rzRateBList = rzRateBService.getRzRateBAll(rzRateBQuery);
		return rzRateBList;
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
	@RequestMapping(value = "rzRateB/getRzRateBByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRateBByPage(RzRateBQuery rzRateBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRateBQuery == null) {
			rzRateBQuery = new RzRateBQuery();
		}
		GridResult<RzRateB> gridResult = rzRateBService.getRzRateBByPage(rzRateBQuery);
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
	@RequestMapping(value = "rzRateB/getRzRateBById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzRateBById(RzRateBQuery rzRateBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzRateB rzRateB = rzRateBService.getRzRateBById(rzRateBQuery);
		return rzRateB;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzRateB/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzRateB rzRateB,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRateBService.insertOrUpdate(rzRateB);
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
	@RequestMapping(value = "rzRateB/deleteRzRateBByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzRateBByBatchId(RzRateBQuery rzRateBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRateBService.deleteRzRateBByBatchId(rzRateBQuery);
		return result;
	}
	
	
	public void setRzRateBService(RzRateBService rzRateBService) {
		this.rzRateBService = rzRateBService;
	}

}