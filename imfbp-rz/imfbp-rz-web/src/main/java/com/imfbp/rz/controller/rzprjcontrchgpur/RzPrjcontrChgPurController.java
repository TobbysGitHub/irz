package com.imfbp.rz.controller.rzprjcontrchgpur;

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

import com.imfbp.rz.domain.rzprjcontrchgpur.RzPrjcontrChgPur;
import com.imfbp.rz.domain.rzprjcontrchgpur.query.RzPrjcontrChgPurQuery;
import com.imfbp.rz.service.rzprjcontrchgpur.RzPrjcontrChgPurService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgPurController extends PubBaseContrl{

	private RzPrjcontrChgPurService rzPrjcontrChgPurService;
	
	/**
	 *  跳转到RzPrjcontrChgPur首页
	 * @param rzPrjcontrChgPurQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgPur/toRzPrjcontrChgPurPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgPurPage(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgPur/rzPrjcontrChgPur");
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
	@RequestMapping(value = "rzPrjcontrChgPur/getRzPrjcontrChgPurAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgPurAll(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgPurQuery == null) {
			rzPrjcontrChgPurQuery = new RzPrjcontrChgPurQuery();
		}
		List<RzPrjcontrChgPur> rzPrjcontrChgPurList = rzPrjcontrChgPurService.getRzPrjcontrChgPurAll(rzPrjcontrChgPurQuery);
		return rzPrjcontrChgPurList;
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
	@RequestMapping(value = "rzPrjcontrChgPur/getRzPrjcontrChgPurByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgPurByPage(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgPurQuery == null) {
			rzPrjcontrChgPurQuery = new RzPrjcontrChgPurQuery();
		}
		GridResult<RzPrjcontrChgPur> gridResult = rzPrjcontrChgPurService.getRzPrjcontrChgPurByPage(rzPrjcontrChgPurQuery);
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
	@RequestMapping(value = "rzPrjcontrChgPur/getRzPrjcontrChgPurById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgPurById(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgPur rzPrjcontrChgPur = rzPrjcontrChgPurService.getRzPrjcontrChgPurById(rzPrjcontrChgPurQuery);
		return rzPrjcontrChgPur;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgPur/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChgPur rzPrjcontrChgPur,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgPurService.insertOrUpdate(rzPrjcontrChgPur);
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
	@RequestMapping(value = "rzPrjcontrChgPur/deleteRzPrjcontrChgPurByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgPurByBatchId(RzPrjcontrChgPurQuery rzPrjcontrChgPurQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgPurService.deleteRzPrjcontrChgPurByBatchId(rzPrjcontrChgPurQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgPurService(RzPrjcontrChgPurService rzPrjcontrChgPurService) {
		this.rzPrjcontrChgPurService = rzPrjcontrChgPurService;
	}

}