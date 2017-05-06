package com.imfbp.rz.controller.rzadjintleasenew;

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

import com.imfbp.rz.domain.rzadjintleasenew.RzAdjIntLeaseNew;
import com.imfbp.rz.domain.rzadjintleasenew.query.RzAdjIntLeaseNewQuery;
import com.imfbp.rz.service.rzadjintleasenew.RzAdjIntLeaseNewService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzAdjIntLeaseNewController extends PubBaseContrl{

	private RzAdjIntLeaseNewService rzAdjIntLeaseNewService;
	
	/**
	 *  跳转到RzAdjIntLeaseNew首页
	 * @param rzAdjIntLeaseNewQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjIntLeaseNew/toRzAdjIntLeaseNewPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzAdjIntLeaseNewPage(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzAdjIntLeaseNew/rzAdjIntLeaseNew");
		req.setAttribute("PkAdjInt", rzAdjIntLeaseNewQuery.getPkAdjInt());
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
	@RequestMapping(value = "rzAdjIntLeaseNew/getRzAdjIntLeaseNewAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntLeaseNewAll(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzAdjIntLeaseNewQuery == null) {
			rzAdjIntLeaseNewQuery = new RzAdjIntLeaseNewQuery();
		}
		List<RzAdjIntLeaseNew> rzAdjIntLeaseNewList = rzAdjIntLeaseNewService.getRzAdjIntLeaseNewAll(rzAdjIntLeaseNewQuery);
		return rzAdjIntLeaseNewList;
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
	@RequestMapping(value = "rzAdjIntLeaseNew/getRzAdjIntLeaseNewByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntLeaseNewByPage(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzAdjIntLeaseNewQuery == null) {
			rzAdjIntLeaseNewQuery = new RzAdjIntLeaseNewQuery();
		}
		GridResult<RzAdjIntLeaseNew> gridResult = rzAdjIntLeaseNewService.getRzAdjIntLeaseNewByPage(rzAdjIntLeaseNewQuery);
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
	@RequestMapping(value = "rzAdjIntLeaseNew/getRzAdjIntLeaseNewById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntLeaseNewById(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzAdjIntLeaseNew rzAdjIntLeaseNew = rzAdjIntLeaseNewService.getRzAdjIntLeaseNewById(rzAdjIntLeaseNewQuery);
		return rzAdjIntLeaseNew;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzAdjIntLeaseNew/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzAdjIntLeaseNew rzAdjIntLeaseNew,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzAdjIntLeaseNewService.insertOrUpdate(rzAdjIntLeaseNew);
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
	@RequestMapping(value = "rzAdjIntLeaseNew/deleteRzAdjIntLeaseNewByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzAdjIntLeaseNewByBatchId(RzAdjIntLeaseNewQuery rzAdjIntLeaseNewQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzAdjIntLeaseNewService.deleteRzAdjIntLeaseNewByBatchId(rzAdjIntLeaseNewQuery);
		return result;
	}
	
	
	public void setRzAdjIntLeaseNewService(RzAdjIntLeaseNewService rzAdjIntLeaseNewService) {
		this.rzAdjIntLeaseNewService = rzAdjIntLeaseNewService;
	}

}