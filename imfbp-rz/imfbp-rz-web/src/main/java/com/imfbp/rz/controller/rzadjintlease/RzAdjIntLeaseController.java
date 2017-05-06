package com.imfbp.rz.controller.rzadjintlease;

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

import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzadjintlease.query.RzAdjIntLeaseQuery;
import com.imfbp.rz.service.rzadjintlease.RzAdjIntLeaseService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzAdjIntLeaseController extends PubBaseContrl{

	private RzAdjIntLeaseService rzAdjIntLeaseService;
	
	/**
	 *  跳转到RzAdjIntLease首页
	 * @param rzAdjIntLeaseQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjIntLease/toRzAdjIntLeasePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzAdjIntLeasePage(RzAdjIntLeaseQuery rzAdjIntLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzAdjIntLease/rzAdjIntLease");
		attachLoginInfo(mv);
		req.setAttribute("PkAdjInt", rzAdjIntLeaseQuery.getPkAdjInt());
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
	@RequestMapping(value = "rzAdjIntLease/getRzAdjIntLeaseAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntLeaseAll(RzAdjIntLeaseQuery rzAdjIntLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzAdjIntLeaseQuery == null) {
			rzAdjIntLeaseQuery = new RzAdjIntLeaseQuery();
		}
		List<RzAdjIntLease> rzAdjIntLeaseList = rzAdjIntLeaseService.getRzAdjIntLeaseAll(rzAdjIntLeaseQuery);
		return rzAdjIntLeaseList;
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
	@RequestMapping(value = "rzAdjIntLease/getRzAdjIntLeaseByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntLeaseByPage(RzAdjIntLeaseQuery rzAdjIntLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzAdjIntLeaseQuery == null) {
			rzAdjIntLeaseQuery = new RzAdjIntLeaseQuery();
		}
		GridResult<RzAdjIntLease> gridResult = rzAdjIntLeaseService.getRzAdjIntLeaseByPage(rzAdjIntLeaseQuery);
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
	@RequestMapping(value = "rzAdjIntLease/getRzAdjIntLeaseById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntLeaseById(RzAdjIntLeaseQuery rzAdjIntLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzAdjIntLease rzAdjIntLease = rzAdjIntLeaseService.getRzAdjIntLeaseById(rzAdjIntLeaseQuery);
		return rzAdjIntLease;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzAdjIntLease/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzAdjIntLease rzAdjIntLease,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzAdjIntLeaseService.insertOrUpdate(rzAdjIntLease);
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
	@RequestMapping(value = "rzAdjIntLease/deleteRzAdjIntLeaseByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzAdjIntLeaseByBatchId(RzAdjIntLeaseQuery rzAdjIntLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzAdjIntLeaseService.deleteRzAdjIntLeaseByBatchId(rzAdjIntLeaseQuery);
		return result;
	}
	
	
	public void setRzAdjIntLeaseService(RzAdjIntLeaseService rzAdjIntLeaseService) {
		this.rzAdjIntLeaseService = rzAdjIntLeaseService;
	}

}