package com.imfbp.rz.controller.rzprjcontreqpt;

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

import com.imfbp.rz.domain.rzprjcontreqpt.RzPrjcontrEqpt;
import com.imfbp.rz.domain.rzprjcontreqpt.query.RzPrjcontrEqptQuery;
import com.imfbp.rz.service.rzprjcontreqpt.RzPrjcontrEqptService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrEqptController extends PubBaseContrl{

	private RzPrjcontrEqptService rzPrjcontrEqptService;
	
	/**
	 *  跳转到RzPrjcontrEqpt首页
	 * @param rzPrjcontrEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrEqpt/toRzPrjcontrEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrEqptPage(RzPrjcontrEqptQuery rzPrjcontrEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrEqpt/rzPrjcontrEqpt");
		req.setAttribute("pkPrjcontr", rzPrjcontrEqptQuery.getPkPrjcontr());
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
	@RequestMapping(value = "rzPrjcontrEqpt/getRzPrjcontrEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrEqptAll(RzPrjcontrEqptQuery rzPrjcontrEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrEqptQuery == null) {
			rzPrjcontrEqptQuery = new RzPrjcontrEqptQuery();
		}
		List<RzPrjcontrEqpt> rzPrjcontrEqptList = rzPrjcontrEqptService.getRzPrjcontrEqptAll(rzPrjcontrEqptQuery);
		return rzPrjcontrEqptList;
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
	@RequestMapping(value = "rzPrjcontrEqpt/getRzPrjcontrEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrEqptByPage(RzPrjcontrEqptQuery rzPrjcontrEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrEqptQuery == null) {
			rzPrjcontrEqptQuery = new RzPrjcontrEqptQuery();
		}
		GridResult<RzPrjcontrEqpt> gridResult = rzPrjcontrEqptService.getRzPrjcontrEqptByPage(rzPrjcontrEqptQuery);
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
	@RequestMapping(value = "rzPrjcontrEqpt/getRzPrjcontrEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrEqptById(RzPrjcontrEqptQuery rzPrjcontrEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrEqpt rzPrjcontrEqpt = rzPrjcontrEqptService.getRzPrjcontrEqptById(rzPrjcontrEqptQuery);
		return rzPrjcontrEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrEqpt rzPrjcontrEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrEqptService.insertOrUpdate(rzPrjcontrEqpt);
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
	@RequestMapping(value = "rzPrjcontrEqpt/deleteRzPrjcontrEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrEqptByBatchId(RzPrjcontrEqptQuery rzPrjcontrEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrEqptService.deleteRzPrjcontrEqptByBatchId(rzPrjcontrEqptQuery);
		return result;
	}
	
	
	public void setRzPrjcontrEqptService(RzPrjcontrEqptService rzPrjcontrEqptService) {
		this.rzPrjcontrEqptService = rzPrjcontrEqptService;
	}

}