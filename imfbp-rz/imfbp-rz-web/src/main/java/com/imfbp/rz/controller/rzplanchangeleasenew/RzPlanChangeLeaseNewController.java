package com.imfbp.rz.controller.rzplanchangeleasenew;

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

import com.imfbp.rz.domain.rzplanchangeleasenew.RzPlanChangeLeaseNew;
import com.imfbp.rz.domain.rzplanchangeleasenew.query.RzPlanChangeLeaseNewQuery;
import com.imfbp.rz.service.rzplanchangeleasenew.RzPlanChangeLeaseNewService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPlanChangeLeaseNewController extends PubBaseContrl{

	private RzPlanChangeLeaseNewService rzPlanChangeLeaseNewService;
	
	/**
	 *  跳转到RzPlanChangeLeaseNew首页
	 * @param rzPlanChangeLeaseNewQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPlanChangeLeaseNew/toRzPlanChangeLeaseNewPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPlanChangeLeaseNewPage(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if(rzPlanChangeLeaseNewQuery==null){
			rzPlanChangeLeaseNewQuery=new RzPlanChangeLeaseNewQuery();
		}
		req.setAttribute("pkPlanChange", rzPlanChangeLeaseNewQuery.getPkPlanChange());
		ModelAndView mv = new ModelAndView("rzPlanChangeLeaseNew/rzPlanChangeLeaseNew");
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
	@RequestMapping(value = "rzPlanChangeLeaseNew/getRzPlanChangeLeaseNewAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeLeaseNewAll(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPlanChangeLeaseNewQuery == null) {
			rzPlanChangeLeaseNewQuery = new RzPlanChangeLeaseNewQuery();
		}
		List<RzPlanChangeLeaseNew> rzPlanChangeLeaseNewList = rzPlanChangeLeaseNewService.getRzPlanChangeLeaseNewAll(rzPlanChangeLeaseNewQuery);
		return rzPlanChangeLeaseNewList;
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
	@RequestMapping(value = "rzPlanChangeLeaseNew/getRzPlanChangeLeaseNewByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeLeaseNewByPage(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPlanChangeLeaseNewQuery == null) {
			rzPlanChangeLeaseNewQuery = new RzPlanChangeLeaseNewQuery();
		}
		GridResult<RzPlanChangeLeaseNew> gridResult = rzPlanChangeLeaseNewService.getRzPlanChangeLeaseNewByPage(rzPlanChangeLeaseNewQuery);
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
	@RequestMapping(value = "rzPlanChangeLeaseNew/getRzPlanChangeLeaseNewById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeLeaseNewById(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPlanChangeLeaseNew rzPlanChangeLeaseNew = rzPlanChangeLeaseNewService.getRzPlanChangeLeaseNewById(rzPlanChangeLeaseNewQuery);
		return rzPlanChangeLeaseNew;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPlanChangeLeaseNew/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPlanChangeLeaseNew rzPlanChangeLeaseNew,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPlanChangeLeaseNewService.insertOrUpdate(rzPlanChangeLeaseNew);
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
	@RequestMapping(value = "rzPlanChangeLeaseNew/deleteRzPlanChangeLeaseNewByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPlanChangeLeaseNewByBatchId(RzPlanChangeLeaseNewQuery rzPlanChangeLeaseNewQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPlanChangeLeaseNewService.deleteRzPlanChangeLeaseNewByBatchId(rzPlanChangeLeaseNewQuery);
		return result;
	}
	
	
	public void setRzPlanChangeLeaseNewService(RzPlanChangeLeaseNewService rzPlanChangeLeaseNewService) {
		this.rzPlanChangeLeaseNewService = rzPlanChangeLeaseNewService;
	}

}