package com.imfbp.rz.controller.rzprjreview;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.service.rzprjreview.RzPrjreviewService;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzPrjreviewController extends PubBaseContrl{

	private RzPrjreviewService rzPrjreviewService;
	
	/**
	 *  跳转到RzPrjreview首页
	 * @param rzPrjreviewQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreview/toRzPrjreviewPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjreviewPage(RzPrjreviewQuery rzPrjreviewQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjreview/rzPrjreview");
		attachLoginInfo(mv);
		mv.addObject("tenantId", getTenantId());
		attachModuleValue(mv,req);
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
	@RequestMapping(value = "rzPrjreview/getRzPrjreviewAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewAll(RzPrjreviewQuery rzPrjreviewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewQuery == null) {
			rzPrjreviewQuery = new RzPrjreviewQuery();
		}
		List<RzPrjreview> rzPrjreviewList = rzPrjreviewService.getRzPrjreviewAll(rzPrjreviewQuery);
		return rzPrjreviewList;
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
	@RequestMapping(value = "rzPrjreview/getRzPrjreviewByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewByPage(RzPrjreviewQuery rzPrjreviewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewQuery == null) {
			rzPrjreviewQuery = new RzPrjreviewQuery();
		}
		GridResult<RzPrjreview> gridResult = rzPrjreviewService.getRzPrjreviewByPage(rzPrjreviewQuery);
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
	@RequestMapping(value = "rzPrjreview/getRzPrjreviewById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewById(RzPrjreviewQuery rzPrjreviewQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjreview rzPrjreview = rzPrjreviewService.getRzPrjreviewById(rzPrjreviewQuery);
		return rzPrjreview;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjreview/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjreview rzPrjreview,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewService.insertOrUpdate(rzPrjreview);
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
	@RequestMapping(value = "rzPrjreview/deleteRzPrjreviewByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjreviewByBatchId(RzPrjreviewQuery rzPrjreviewQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewService.deleteRzPrjreviewByBatchId(rzPrjreviewQuery);
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
	@RequestMapping(value = "rzPrjreview/logicDeleteRzPrjreviewByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPrjreviewByBatchId(RzPrjreviewQuery rzPrjreviewQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewService.logicDeleteRzPrjreviewByBatchId(rzPrjreviewQuery);
		return result;
	}
	
	public void setRzPrjreviewService(RzPrjreviewService rzPrjreviewService) {
		this.rzPrjreviewService = rzPrjreviewService;
	}

}