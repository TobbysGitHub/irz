package com.imfbp.rz.controller.rzprjcontrchglessee;

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

import com.imfbp.rz.domain.rzprjcontrchglessee.RzPrjcontrChgLessee;
import com.imfbp.rz.domain.rzprjcontrchglessee.query.RzPrjcontrChgLesseeQuery;
import com.imfbp.rz.service.rzprjcontrchglessee.RzPrjcontrChgLesseeService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgLesseeController extends PubBaseContrl{

	private RzPrjcontrChgLesseeService rzPrjcontrChgLesseeService;
	
	/**
	 *  跳转到RzPrjcontrChgLessee首页
	 * @param rzPrjcontrChgLesseeQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgLessee/toRzPrjcontrChgLesseePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgLesseePage(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		req.setAttribute("pkPrjcontrChg", rzPrjcontrChgLesseeQuery.getPkPrjcontrChg());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgLessee/rzPrjcontrChgLessee");
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
	@RequestMapping(value = "rzPrjcontrChgLessee/getRzPrjcontrChgLesseeAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgLesseeAll(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgLesseeQuery == null) {
			rzPrjcontrChgLesseeQuery = new RzPrjcontrChgLesseeQuery();
		}
		List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList = rzPrjcontrChgLesseeService.getRzPrjcontrChgLesseeAll(rzPrjcontrChgLesseeQuery);
		return rzPrjcontrChgLesseeList;
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
	@RequestMapping(value = "rzPrjcontrChgLessee/getRzPrjcontrChgLesseeByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgLesseeByPage(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgLesseeQuery == null) {
			rzPrjcontrChgLesseeQuery = new RzPrjcontrChgLesseeQuery();
		}
		GridResult<RzPrjcontrChgLessee> gridResult = rzPrjcontrChgLesseeService.getRzPrjcontrChgLesseeByPage(rzPrjcontrChgLesseeQuery);
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
	@RequestMapping(value = "rzPrjcontrChgLessee/getRzPrjcontrChgLesseeById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgLesseeById(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgLessee rzPrjcontrChgLessee = rzPrjcontrChgLesseeService.getRzPrjcontrChgLesseeById(rzPrjcontrChgLesseeQuery);
		return rzPrjcontrChgLessee;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgLessee/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChgLessee rzPrjcontrChgLessee,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgLesseeService.insertOrUpdate(rzPrjcontrChgLessee);
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
	@RequestMapping(value = "rzPrjcontrChgLessee/deleteRzPrjcontrChgLesseeByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgLesseeByBatchId(RzPrjcontrChgLesseeQuery rzPrjcontrChgLesseeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgLesseeService.deleteRzPrjcontrChgLesseeByBatchId(rzPrjcontrChgLesseeQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgLesseeService(RzPrjcontrChgLesseeService rzPrjcontrChgLesseeService) {
		this.rzPrjcontrChgLesseeService = rzPrjcontrChgLesseeService;
	}

}