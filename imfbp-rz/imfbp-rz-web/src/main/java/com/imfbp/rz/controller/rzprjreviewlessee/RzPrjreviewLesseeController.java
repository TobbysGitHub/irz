package com.imfbp.rz.controller.rzprjreviewlessee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.brefdata.rpc.reference.domain.RpcDataQuery;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewlessee.query.RzPrjreviewLesseeQuery;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.ref.RefMetaDataService;
import com.imfbp.rz.service.rzprjreview.RzPrjreviewService;
import com.imfbp.rz.service.rzprjreviewlessee.RzPrjreviewLesseeService;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.spring.mvc.controller.BaseController;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import net.sf.json.JSONObject;

@RestController
public class RzPrjreviewLesseeController extends BaseController {

	private RzPrjreviewLesseeService rzPrjreviewLesseeService;
	@Autowired
	private RzPrjreviewService rzPrjreviewService;

	/**
	 * 跳转到RzPrjreviewLessee首页
	 * 
	 * @param rzPrjreviewLesseeQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewLessee/toRzPrjreviewLesseePage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView toRzPrjreviewLesseePage(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if (rzPrjreviewLesseeQuery == null) {
			rzPrjreviewLesseeQuery = new RzPrjreviewLesseeQuery();
		}
		req.setAttribute("pkPrjreview", rzPrjreviewLesseeQuery.getPkPrjreview());

		RzPrjreviewQuery rzPrjreviewQuery = new RzPrjreviewQuery();
		rzPrjreviewQuery.setPkPrjreview(rzPrjreviewLesseeQuery.getPkPrjreview());
		req.setAttribute("approvestatus", rzPrjreviewService.getRzPrjreviewById(rzPrjreviewQuery));
		ModelAndView mv = new ModelAndView("rzPrjreviewLessee/rzPrjreviewLessee");
		return mv;
	}

	/**
	 * 查询所有
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewLessee/getRzPrjreviewLesseeAll", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewLesseeAll(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewLesseeQuery == null) {
			rzPrjreviewLesseeQuery = new RzPrjreviewLesseeQuery();
		}
		List<RzPrjreviewLessee> rzPrjreviewLesseeList = rzPrjreviewLesseeService
				.getRzPrjreviewLesseeAll(rzPrjreviewLesseeQuery);
		return rzPrjreviewLesseeList;
	}

	/**
	 * 分页查询
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewLessee/getRzPrjreviewLesseeByPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewLesseeByPage(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewLesseeQuery == null) {
			rzPrjreviewLesseeQuery = new RzPrjreviewLesseeQuery();
		}
		GridResult<RzPrjreviewLessee> gridResult = rzPrjreviewLesseeService
				.getRzPrjreviewLesseeByPage(getTenantId(),rzPrjreviewLesseeQuery);
		return gridResult;
	}



	/**
	 * 根据id查询
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewLessee/getRzPrjreviewLesseeById", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewLesseeById(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		RzPrjreviewLessee rzPrjreviewLessee = rzPrjreviewLesseeService.getRzPrjreviewLesseeById(rzPrjreviewLesseeQuery);
		return rzPrjreviewLessee;
	}

	/**
	 * 添加或修改
	 * 
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjreviewLessee/insertOrUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjreviewLessee rzPrjreviewLessee, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		rzPrjreviewLessee.setIsMainLessee("N");
		Result result = rzPrjreviewLesseeService.insertOrUpdate(rzPrjreviewLessee);
		return result;
	}

	/**
	 * 根据Id批量删除 (真正删除数据库数据)
	 * 
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjreviewLessee/deleteRzPrjreviewLesseeByBatchId", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjreviewLesseeByBatchId(RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewLesseeService.deleteRzPrjreviewLesseeByBatchId(rzPrjreviewLesseeQuery);
		return result;
	}

	public void setRzPrjreviewLesseeService(RzPrjreviewLesseeService rzPrjreviewLesseeService) {
		this.rzPrjreviewLesseeService = rzPrjreviewLesseeService;
	}

}