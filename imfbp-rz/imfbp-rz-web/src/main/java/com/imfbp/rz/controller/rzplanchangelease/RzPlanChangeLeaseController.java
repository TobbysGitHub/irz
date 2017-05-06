package com.imfbp.rz.controller.rzplanchangelease;

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
import com.imfbp.rz.domain.rzplanchangelease.RzPlanChangeLease;
import com.imfbp.rz.domain.rzplanchangelease.query.RzPlanChangeLeaseQuery;
import com.imfbp.rz.domain.rzprjrevieweqpt.query.RzPrjreviewEqptQuery;
import com.imfbp.rz.service.rzplanchangelease.RzPlanChangeLeaseService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPlanChangeLeaseController extends PubBaseContrl{

	private RzPlanChangeLeaseService rzPlanChangeLeaseService;
	
	/**
	 *  跳转到RzPlanChangeLease首页
	 * @param rzPlanChangeLeaseQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPlanChangeLease/toRzPlanChangeLeasePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPlanChangeLeasePage(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if(rzPlanChangeLeaseQuery==null){
			rzPlanChangeLeaseQuery=new RzPlanChangeLeaseQuery();
		}
		req.setAttribute("pkPlanChange", rzPlanChangeLeaseQuery.getPkPlanChange());
		ModelAndView mv = new ModelAndView("rzPlanChangeLease/rzPlanChangeLease");
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
	@RequestMapping(value = "rzPlanChangeLease/getRzPlanChangeLeaseAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeLeaseAll(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPlanChangeLeaseQuery == null) {
			rzPlanChangeLeaseQuery = new RzPlanChangeLeaseQuery();
		}
		List<RzPlanChangeLease> rzPlanChangeLeaseList = rzPlanChangeLeaseService.getRzPlanChangeLeaseAll(rzPlanChangeLeaseQuery);
		return rzPlanChangeLeaseList;
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
	@RequestMapping(value = "rzPlanChangeLease/getRzPlanChangeLeaseByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeLeaseByPage(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPlanChangeLeaseQuery == null) {
			rzPlanChangeLeaseQuery = new RzPlanChangeLeaseQuery();
		}
		GridResult<RzPlanChangeLease> gridResult = rzPlanChangeLeaseService.getRzPlanChangeLeaseByPage(rzPlanChangeLeaseQuery);
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
	@RequestMapping(value = "rzPlanChangeLease/getRzPlanChangeLeaseById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPlanChangeLeaseById(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPlanChangeLease rzPlanChangeLease = rzPlanChangeLeaseService.getRzPlanChangeLeaseById(rzPlanChangeLeaseQuery);
		return rzPlanChangeLease;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPlanChangeLease/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPlanChangeLease rzPlanChangeLease,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPlanChangeLeaseService.insertOrUpdate(rzPlanChangeLease);
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
	@RequestMapping(value = "rzPlanChangeLease/deleteRzPlanChangeLeaseByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPlanChangeLeaseByBatchId(RzPlanChangeLeaseQuery rzPlanChangeLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPlanChangeLeaseService.deleteRzPlanChangeLeaseByBatchId(rzPlanChangeLeaseQuery);
		return result;
	}
	
	
	public void setRzPlanChangeLeaseService(RzPlanChangeLeaseService rzPlanChangeLeaseService) {
		this.rzPlanChangeLeaseService = rzPlanChangeLeaseService;
	}

}