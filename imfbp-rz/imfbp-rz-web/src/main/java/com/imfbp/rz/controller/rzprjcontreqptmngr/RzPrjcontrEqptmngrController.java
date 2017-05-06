package com.imfbp.rz.controller.rzprjcontreqptmngr;

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

import com.imfbp.rz.domain.rzprjcontreqptmngr.RzPrjcontrEqptmngr;
import com.imfbp.rz.domain.rzprjcontreqptmngr.query.RzPrjcontrEqptmngrQuery;
import com.imfbp.rz.service.rzprjcontreqptmngr.RzPrjcontrEqptmngrService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrEqptmngrController extends PubBaseContrl{

	private RzPrjcontrEqptmngrService rzPrjcontrEqptmngrService;
	
	/**
	 *  跳转到RzPrjcontrEqptmngr首页
	 * @param rzPrjcontrEqptmngrQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrEqptmngr/toRzPrjcontrEqptmngrPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrEqptmngrPage(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrEqptmngr/rzPrjcontrEqptmngr");
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
	@RequestMapping(value = "rzPrjcontrEqptmngr/getRzPrjcontrEqptmngrAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrEqptmngrAll(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrEqptmngrQuery == null) {
			rzPrjcontrEqptmngrQuery = new RzPrjcontrEqptmngrQuery();
		}
		List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList = rzPrjcontrEqptmngrService.getRzPrjcontrEqptmngrAll(rzPrjcontrEqptmngrQuery);
		return rzPrjcontrEqptmngrList;
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
	@RequestMapping(value = "rzPrjcontrEqptmngr/getRzPrjcontrEqptmngrByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrEqptmngrByPage(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrEqptmngrQuery == null) {
			rzPrjcontrEqptmngrQuery = new RzPrjcontrEqptmngrQuery();
		}
		GridResult<RzPrjcontrEqptmngr> gridResult = rzPrjcontrEqptmngrService.getRzPrjcontrEqptmngrByPage(rzPrjcontrEqptmngrQuery);
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
	@RequestMapping(value = "rzPrjcontrEqptmngr/getRzPrjcontrEqptmngrById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrEqptmngrById(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrEqptmngr rzPrjcontrEqptmngr = rzPrjcontrEqptmngrService.getRzPrjcontrEqptmngrById(rzPrjcontrEqptmngrQuery);
		return rzPrjcontrEqptmngr;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrEqptmngr/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrEqptmngr rzPrjcontrEqptmngr,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrEqptmngrService.insertOrUpdate(rzPrjcontrEqptmngr);
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
	@RequestMapping(value = "rzPrjcontrEqptmngr/deleteRzPrjcontrEqptmngrByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrEqptmngrByBatchId(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrEqptmngrService.deleteRzPrjcontrEqptmngrByBatchId(rzPrjcontrEqptmngrQuery);
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
	@RequestMapping(value = "rzPrjcontrEqptmngr/logicDeleteRzPrjcontrEqptmngrByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPrjcontrEqptmngrByBatchId(RzPrjcontrEqptmngrQuery rzPrjcontrEqptmngrQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrEqptmngrService.logicDeleteRzPrjcontrEqptmngrByBatchId(rzPrjcontrEqptmngrQuery);
		return result;
	}
	
	public void setRzPrjcontrEqptmngrService(RzPrjcontrEqptmngrService rzPrjcontrEqptmngrService) {
		this.rzPrjcontrEqptmngrService = rzPrjcontrEqptmngrService;
	}

}