package com.imfbp.rz.controller.rzprjcontrinseqpt;

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

import com.imfbp.rz.domain.rzprjcontrinseqpt.RzPrjcontrInsEqpt;
import com.imfbp.rz.domain.rzprjcontrinseqpt.query.RzPrjcontrInsEqptQuery;
import com.imfbp.rz.service.rzprjcontrinseqpt.RzPrjcontrInsEqptService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrInsEqptController extends PubBaseContrl{

	private RzPrjcontrInsEqptService rzPrjcontrInsEqptService;
	
	/**
	 *  跳转到RzPrjcontrInsEqpt首页
	 * @param rzPrjcontrInsEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrInsEqpt/toRzPrjcontrInsEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrInsEqptPage(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrInsEqpt/rzPrjcontrInsEqpt");
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
	@RequestMapping(value = "rzPrjcontrInsEqpt/getRzPrjcontrInsEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrInsEqptAll(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrInsEqptQuery == null) {
			rzPrjcontrInsEqptQuery = new RzPrjcontrInsEqptQuery();
		}
		List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList = rzPrjcontrInsEqptService.getRzPrjcontrInsEqptAll(rzPrjcontrInsEqptQuery);
		return rzPrjcontrInsEqptList;
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
	@RequestMapping(value = "rzPrjcontrInsEqpt/getRzPrjcontrInsEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrInsEqptByPage(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrInsEqptQuery == null) {
			rzPrjcontrInsEqptQuery = new RzPrjcontrInsEqptQuery();
		}
		GridResult<RzPrjcontrInsEqpt> gridResult = rzPrjcontrInsEqptService.getRzPrjcontrInsEqptByPage(rzPrjcontrInsEqptQuery);
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
	@RequestMapping(value = "rzPrjcontrInsEqpt/getRzPrjcontrInsEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrInsEqptById(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrInsEqpt rzPrjcontrInsEqpt = rzPrjcontrInsEqptService.getRzPrjcontrInsEqptById(rzPrjcontrInsEqptQuery);
		return rzPrjcontrInsEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrInsEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrInsEqpt rzPrjcontrInsEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrInsEqptService.insertOrUpdate(rzPrjcontrInsEqpt);
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
	@RequestMapping(value = "rzPrjcontrInsEqpt/deleteRzPrjcontrInsEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrInsEqptByBatchId(RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrInsEqptService.deleteRzPrjcontrInsEqptByBatchId(rzPrjcontrInsEqptQuery);
		return result;
	}
	
	
	public void setRzPrjcontrInsEqptService(RzPrjcontrInsEqptService rzPrjcontrInsEqptService) {
		this.rzPrjcontrInsEqptService = rzPrjcontrInsEqptService;
	}

}