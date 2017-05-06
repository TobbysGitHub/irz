package com.imfbp.rz.controller.rzprjcontrlessee;

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

import com.imfbp.rz.domain.rzprjcontrlessee.RzPrjcontrLessee;
import com.imfbp.rz.domain.rzprjcontrlessee.query.RzPrjcontrLesseeQuery;
import com.imfbp.rz.service.rzprjcontrlessee.RzPrjcontrLesseeService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrLesseeController extends PubBaseContrl{

	private RzPrjcontrLesseeService rzPrjcontrLesseeService;
	
	/**
	 *  跳转到RzPrjcontrLessee首页
	 * @param rzPrjcontrLesseeQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrLessee/toRzPrjcontrLesseePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrLesseePage(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		req.setAttribute("pkPrjcontr", rzPrjcontrLesseeQuery.getPkPrjcontr());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrLessee/rzPrjcontrLessee");
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
	@RequestMapping(value = "rzPrjcontrLessee/getRzPrjcontrLesseeAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrLesseeAll(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrLesseeQuery == null) {
			rzPrjcontrLesseeQuery = new RzPrjcontrLesseeQuery();
		}
		List<RzPrjcontrLessee> rzPrjcontrLesseeList = rzPrjcontrLesseeService.getRzPrjcontrLesseeAll(rzPrjcontrLesseeQuery);
		return rzPrjcontrLesseeList;
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
	@RequestMapping(value = "rzPrjcontrLessee/getRzPrjcontrLesseeByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrLesseeByPage(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrLesseeQuery == null) {
			rzPrjcontrLesseeQuery = new RzPrjcontrLesseeQuery();
		}
		GridResult<RzPrjcontrLessee> gridResult = rzPrjcontrLesseeService.getRzPrjcontrLesseeByPage(rzPrjcontrLesseeQuery);
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
	@RequestMapping(value = "rzPrjcontrLessee/getRzPrjcontrLesseeById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrLesseeById(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrLessee rzPrjcontrLessee = rzPrjcontrLesseeService.getRzPrjcontrLesseeById(rzPrjcontrLesseeQuery);
		return rzPrjcontrLessee;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrLessee/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrLessee rzPrjcontrLessee,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrLesseeService.insertOrUpdate(rzPrjcontrLessee);
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
	@RequestMapping(value = "rzPrjcontrLessee/deleteRzPrjcontrLesseeByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrLesseeByBatchId(RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrLesseeService.deleteRzPrjcontrLesseeByBatchId(rzPrjcontrLesseeQuery);
		return result;
	}
	
	
	public void setRzPrjcontrLesseeService(RzPrjcontrLesseeService rzPrjcontrLesseeService) {
		this.rzPrjcontrLesseeService = rzPrjcontrLesseeService;
	}

}