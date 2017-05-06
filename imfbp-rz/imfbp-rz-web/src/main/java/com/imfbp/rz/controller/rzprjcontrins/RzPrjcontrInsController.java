package com.imfbp.rz.controller.rzprjcontrins;

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

import com.imfbp.rz.domain.rzprjcontrins.RzPrjcontrIns;
import com.imfbp.rz.domain.rzprjcontrins.query.RzPrjcontrInsQuery;
import com.imfbp.rz.service.rzprjcontrins.RzPrjcontrInsService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrInsController extends PubBaseContrl{

	private RzPrjcontrInsService rzPrjcontrInsService;
	
	/**
	 *  跳转到RzPrjcontrIns首页
	 * @param rzPrjcontrInsQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrIns/toRzPrjcontrInsPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrInsPage(RzPrjcontrInsQuery rzPrjcontrInsQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrIns/rzPrjcontrIns");
		req.setAttribute("pkPrjcontr", rzPrjcontrInsQuery.getPkPrjcontr());
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
	@RequestMapping(value = "rzPrjcontrIns/getRzPrjcontrInsAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrInsAll(RzPrjcontrInsQuery rzPrjcontrInsQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrInsQuery == null) {
			rzPrjcontrInsQuery = new RzPrjcontrInsQuery();
		}
		List<RzPrjcontrIns> rzPrjcontrInsList = rzPrjcontrInsService.getRzPrjcontrInsAll(rzPrjcontrInsQuery);
		return rzPrjcontrInsList;
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
	@RequestMapping(value = "rzPrjcontrIns/getRzPrjcontrInsByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrInsByPage(RzPrjcontrInsQuery rzPrjcontrInsQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrInsQuery == null) {
			rzPrjcontrInsQuery = new RzPrjcontrInsQuery();
		}
		GridResult<RzPrjcontrIns> gridResult = rzPrjcontrInsService.getRzPrjcontrInsByPage(rzPrjcontrInsQuery);
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
	@RequestMapping(value = "rzPrjcontrIns/getRzPrjcontrInsById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrInsById(RzPrjcontrInsQuery rzPrjcontrInsQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrIns rzPrjcontrIns = rzPrjcontrInsService.getRzPrjcontrInsById(rzPrjcontrInsQuery);
		return rzPrjcontrIns;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrIns/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrIns rzPrjcontrIns,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrInsService.insertOrUpdate(rzPrjcontrIns);
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
	@RequestMapping(value = "rzPrjcontrIns/deleteRzPrjcontrInsByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrInsByBatchId(RzPrjcontrInsQuery rzPrjcontrInsQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrInsService.deleteRzPrjcontrInsByBatchId(rzPrjcontrInsQuery);
		return result;
	}
	
	
	public void setRzPrjcontrInsService(RzPrjcontrInsService rzPrjcontrInsService) {
		this.rzPrjcontrInsService = rzPrjcontrInsService;
	}

}