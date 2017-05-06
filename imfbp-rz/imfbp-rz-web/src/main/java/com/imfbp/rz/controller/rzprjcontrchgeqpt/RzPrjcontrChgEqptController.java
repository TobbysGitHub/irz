package com.imfbp.rz.controller.rzprjcontrchgeqpt;

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

import com.imfbp.rz.domain.rzprjcontrchgeqpt.RzPrjcontrChgEqpt;
import com.imfbp.rz.domain.rzprjcontrchgeqpt.query.RzPrjcontrChgEqptQuery;
import com.imfbp.rz.service.rzprjcontrchgeqpt.RzPrjcontrChgEqptService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgEqptController extends PubBaseContrl{

	private RzPrjcontrChgEqptService rzPrjcontrChgEqptService;
	
	/**
	 *  跳转到RzPrjcontrChgEqpt首页
	 * @param rzPrjcontrChgEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgEqpt/toRzPrjcontrChgEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgEqptPage(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		req.setAttribute("pkPrjcontrChg", rzPrjcontrChgEqptQuery.getPkPrjcontrChg());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgEqpt/rzPrjcontrChgEqpt");
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
	@RequestMapping(value = "rzPrjcontrChgEqpt/getRzPrjcontrChgEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgEqptAll(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgEqptQuery == null) {
			rzPrjcontrChgEqptQuery = new RzPrjcontrChgEqptQuery();
		}
		List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList = rzPrjcontrChgEqptService.getRzPrjcontrChgEqptAll(rzPrjcontrChgEqptQuery);
		return rzPrjcontrChgEqptList;
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
	@RequestMapping(value = "rzPrjcontrChgEqpt/getRzPrjcontrChgEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgEqptByPage(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgEqptQuery == null) {
			rzPrjcontrChgEqptQuery = new RzPrjcontrChgEqptQuery();
		}
		GridResult<RzPrjcontrChgEqpt> gridResult = rzPrjcontrChgEqptService.getRzPrjcontrChgEqptByPage(rzPrjcontrChgEqptQuery);
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
	@RequestMapping(value = "rzPrjcontrChgEqpt/getRzPrjcontrChgEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgEqptById(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgEqpt rzPrjcontrChgEqpt = rzPrjcontrChgEqptService.getRzPrjcontrChgEqptById(rzPrjcontrChgEqptQuery);
		return rzPrjcontrChgEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChgEqpt rzPrjcontrChgEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgEqptService.insertOrUpdate(rzPrjcontrChgEqpt);
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
	@RequestMapping(value = "rzPrjcontrChgEqpt/deleteRzPrjcontrChgEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgEqptByBatchId(RzPrjcontrChgEqptQuery rzPrjcontrChgEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgEqptService.deleteRzPrjcontrChgEqptByBatchId(rzPrjcontrChgEqptQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgEqptService(RzPrjcontrChgEqptService rzPrjcontrChgEqptService) {
		this.rzPrjcontrChgEqptService = rzPrjcontrChgEqptService;
	}

}