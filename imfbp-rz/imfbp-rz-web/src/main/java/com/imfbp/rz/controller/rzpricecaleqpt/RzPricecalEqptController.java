package com.imfbp.rz.controller.rzpricecaleqpt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.platform.common.spring.mvc.controller.BaseController;
import com.imfbp.rz.domain.rzpricecaleqpt.RzPricecalEqpt;
import com.imfbp.rz.domain.rzpricecaleqpt.query.RzPricecalEqptQuery;
import com.imfbp.rz.service.rzpricecaleqpt.RzPricecalEqptService;


@RestController
public class RzPricecalEqptController extends BaseController{

	private RzPricecalEqptService rzPricecalEqptService;
	
	/**
	 *  跳转到RzPricecalEqpt首页
	 * @param rzPricecalEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecalEqpt/toRzPricecalEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPricecalEqptPage(RzPricecalEqptQuery rzPricecalEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPricecalEqpt/rzPricecalEqpt");
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
	@RequestMapping(value = "rzPricecalEqpt/getRzPricecalEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalEqptAll(RzPricecalEqptQuery rzPricecalEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalEqptQuery == null) {
			rzPricecalEqptQuery = new RzPricecalEqptQuery();
		}
		List<RzPricecalEqpt> rzPricecalEqptList = rzPricecalEqptService.getRzPricecalEqptAll(rzPricecalEqptQuery);
		return rzPricecalEqptList;
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
	@RequestMapping(value = "rzPricecalEqpt/getRzPricecalEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalEqptByPage(RzPricecalEqptQuery rzPricecalEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalEqptQuery == null) {
			rzPricecalEqptQuery = new RzPricecalEqptQuery();
		}
		if(StringUtils.isEmpty(rzPricecalEqptQuery.getPkPricecal())) return null;
		GridResult<RzPricecalEqpt> gridResult = rzPricecalEqptService.getRzPricecalEqptByPage(rzPricecalEqptQuery);
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
	@RequestMapping(value = "rzPricecalEqpt/getRzPricecalEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPricecalEqptById(RzPricecalEqptQuery rzPricecalEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPricecalEqpt rzPricecalEqpt = rzPricecalEqptService.getRzPricecalEqptById(rzPricecalEqptQuery);
		return rzPricecalEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPricecalEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPricecalEqpt rzPricecalEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPricecalEqptService.insertOrUpdate(rzPricecalEqpt);
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
	@RequestMapping(value = "rzPricecalEqpt/deleteRzPricecalEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPricecalEqptByBatchId(RzPricecalEqptQuery rzPricecalEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPricecalEqptService.deleteRzPricecalEqptByBatchId(rzPricecalEqptQuery);
		return result;
	}
	
	
	public void setRzPricecalEqptService(RzPricecalEqptService rzPricecalEqptService) {
		this.rzPricecalEqptService = rzPricecalEqptService;
	}

}