package com.imfbp.rz.controller.rzprjcontrchgins;

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

import com.imfbp.rz.domain.rzprjcontrchgins.RzPrjcontrChgIns;
import com.imfbp.rz.domain.rzprjcontrchgins.query.RzPrjcontrChgInsQuery;
import com.imfbp.rz.service.rzprjcontrchgins.RzPrjcontrChgInsService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgInsController extends PubBaseContrl{

	private RzPrjcontrChgInsService rzPrjcontrChgInsService;
	
	/**
	 *  跳转到RzPrjcontrChgIns首页
	 * @param rzPrjcontrChgInsQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgIns/toRzPrjcontrChgInsPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgInsPage(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		req.setAttribute("pkPrjcontrChg", rzPrjcontrChgInsQuery.getPkPrjcontrChg());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgIns/rzPrjcontrChgIns");
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
	@RequestMapping(value = "rzPrjcontrChgIns/getRzPrjcontrChgInsAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgInsAll(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgInsQuery == null) {
			rzPrjcontrChgInsQuery = new RzPrjcontrChgInsQuery();
		}
		List<RzPrjcontrChgIns> rzPrjcontrChgInsList = rzPrjcontrChgInsService.getRzPrjcontrChgInsAll(rzPrjcontrChgInsQuery);
		return rzPrjcontrChgInsList;
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
	@RequestMapping(value = "rzPrjcontrChgIns/getRzPrjcontrChgInsByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgInsByPage(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgInsQuery == null) {
			rzPrjcontrChgInsQuery = new RzPrjcontrChgInsQuery();
		}
		GridResult<RzPrjcontrChgIns> gridResult = rzPrjcontrChgInsService.getRzPrjcontrChgInsByPage(rzPrjcontrChgInsQuery);
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
	@RequestMapping(value = "rzPrjcontrChgIns/getRzPrjcontrChgInsById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgInsById(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgIns rzPrjcontrChgIns = rzPrjcontrChgInsService.getRzPrjcontrChgInsById(rzPrjcontrChgInsQuery);
		return rzPrjcontrChgIns;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgIns/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChgIns rzPrjcontrChgIns,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgInsService.insertOrUpdate(rzPrjcontrChgIns);
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
	@RequestMapping(value = "rzPrjcontrChgIns/deleteRzPrjcontrChgInsByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgInsByBatchId(RzPrjcontrChgInsQuery rzPrjcontrChgInsQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgInsService.deleteRzPrjcontrChgInsByBatchId(rzPrjcontrChgInsQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgInsService(RzPrjcontrChgInsService rzPrjcontrChgInsService) {
		this.rzPrjcontrChgInsService = rzPrjcontrChgInsService;
	}

}