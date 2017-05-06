package com.imfbp.rz.controller.rzprjrevieweqpt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.domain.rzprjrevieweqpt.RzPrjreviewEqpt;
import com.imfbp.rz.domain.rzprjrevieweqpt.query.RzPrjreviewEqptQuery;
import com.imfbp.rz.service.rzprjreview.RzPrjreviewService;
import com.imfbp.rz.service.rzprjrevieweqpt.RzPrjreviewEqptService;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.spring.mvc.controller.BaseController;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzPrjreviewEqptController extends BaseController{

	private RzPrjreviewEqptService rzPrjreviewEqptService;
	@Autowired
	private RzPrjreviewService rzPrjreviewService;
	
	/**
	 *  跳转到RzPrjreviewEqpt首页
	 * @param rzPrjreviewEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewEqpt/toRzPrjreviewEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjreviewEqptPage(RzPrjreviewEqptQuery rzPrjreviewEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if(rzPrjreviewEqptQuery==null){
			rzPrjreviewEqptQuery=new RzPrjreviewEqptQuery();
		}
		req.setAttribute("pkPrjreview", rzPrjreviewEqptQuery.getPkPrjreview());
		RzPrjreviewQuery rzPrjreviewQuery = new RzPrjreviewQuery();
		rzPrjreviewQuery.setPkPrjreview(rzPrjreviewEqptQuery.getPkPrjreview());
		req.setAttribute("approvestatus",rzPrjreviewService.getRzPrjreviewById(rzPrjreviewQuery));
		ModelAndView mv = new ModelAndView("rzPrjreviewEqpt/rzPrjreviewEqpt");
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
	@RequestMapping(value = "rzPrjreviewEqpt/getRzPrjreviewEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewEqptAll(RzPrjreviewEqptQuery rzPrjreviewEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewEqptQuery == null) {
			rzPrjreviewEqptQuery = new RzPrjreviewEqptQuery();
		}
		List<RzPrjreviewEqpt> rzPrjreviewEqptList = rzPrjreviewEqptService.getRzPrjreviewEqptAll(rzPrjreviewEqptQuery);
		return rzPrjreviewEqptList;
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
	@RequestMapping(value = "rzPrjreviewEqpt/getRzPrjreviewEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewEqptByPage(RzPrjreviewEqptQuery rzPrjreviewEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewEqptQuery == null) {
			rzPrjreviewEqptQuery = new RzPrjreviewEqptQuery();
		}
		GridResult<RzPrjreviewEqpt> gridResult = rzPrjreviewEqptService.getRzPrjreviewEqptByPage(rzPrjreviewEqptQuery);
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
	@RequestMapping(value = "rzPrjreviewEqpt/getRzPrjreviewEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewEqptById(RzPrjreviewEqptQuery rzPrjreviewEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjreviewEqpt rzPrjreviewEqpt = rzPrjreviewEqptService.getRzPrjreviewEqptById(rzPrjreviewEqptQuery);
		return rzPrjreviewEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjreviewEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjreviewEqpt rzPrjreviewEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewEqptService.insertOrUpdate(rzPrjreviewEqpt);
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
	@RequestMapping(value = "rzPrjreviewEqpt/deleteRzPrjreviewEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjreviewEqptByBatchId(RzPrjreviewEqptQuery rzPrjreviewEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewEqptService.deleteRzPrjreviewEqptByBatchId(rzPrjreviewEqptQuery);
		return result;
	}
	
	
	public void setRzPrjreviewEqptService(RzPrjreviewEqptService rzPrjreviewEqptService) {
		this.rzPrjreviewEqptService = rzPrjreviewEqptService;
	}

}