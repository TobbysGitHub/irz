package com.imfbp.rz.controller.rzprjcontrchginseqpt;

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

import com.imfbp.rz.domain.rzprjcontrchginseqpt.RzPrjcontrChgInsEqpt;
import com.imfbp.rz.domain.rzprjcontrchginseqpt.query.RzPrjcontrChgInsEqptQuery;
import com.imfbp.rz.service.rzprjcontrchginseqpt.RzPrjcontrChgInsEqptService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgInsEqptController extends PubBaseContrl{

	private RzPrjcontrChgInsEqptService rzPrjcontrChgInsEqptService;
	
	/**
	 *  跳转到RzPrjcontrChgInsEqpt首页
	 * @param rzPrjcontrChgInsEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgInsEqpt/toRzPrjcontrChgInsEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgInsEqptPage(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgInsEqpt/rzPrjcontrChgInsEqpt");
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
	@RequestMapping(value = "rzPrjcontrChgInsEqpt/getRzPrjcontrChgInsEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgInsEqptAll(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgInsEqptQuery == null) {
			rzPrjcontrChgInsEqptQuery = new RzPrjcontrChgInsEqptQuery();
		}
		List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList = rzPrjcontrChgInsEqptService.getRzPrjcontrChgInsEqptAll(rzPrjcontrChgInsEqptQuery);
		return rzPrjcontrChgInsEqptList;
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
	@RequestMapping(value = "rzPrjcontrChgInsEqpt/getRzPrjcontrChgInsEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgInsEqptByPage(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgInsEqptQuery == null) {
			rzPrjcontrChgInsEqptQuery = new RzPrjcontrChgInsEqptQuery();
		}
		GridResult<RzPrjcontrChgInsEqpt> gridResult = rzPrjcontrChgInsEqptService.getRzPrjcontrChgInsEqptByPage(rzPrjcontrChgInsEqptQuery);
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
	@RequestMapping(value = "rzPrjcontrChgInsEqpt/getRzPrjcontrChgInsEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgInsEqptById(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt = rzPrjcontrChgInsEqptService.getRzPrjcontrChgInsEqptById(rzPrjcontrChgInsEqptQuery);
		return rzPrjcontrChgInsEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgInsEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgInsEqptService.insertOrUpdate(rzPrjcontrChgInsEqpt);
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
	@RequestMapping(value = "rzPrjcontrChgInsEqpt/deleteRzPrjcontrChgInsEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgInsEqptByBatchId(RzPrjcontrChgInsEqptQuery rzPrjcontrChgInsEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgInsEqptService.deleteRzPrjcontrChgInsEqptByBatchId(rzPrjcontrChgInsEqptQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgInsEqptService(RzPrjcontrChgInsEqptService rzPrjcontrChgInsEqptService) {
		this.rzPrjcontrChgInsEqptService = rzPrjcontrChgInsEqptService;
	}

}