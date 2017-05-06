package com.imfbp.rz.controller.rzprjcontrchgguarb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchgguarb.RzPrjcontrChgGuarB;
import com.imfbp.rz.domain.rzprjcontrchgguarb.query.RzPrjcontrChgGuarBQuery;
import com.imfbp.rz.service.rzprjcontrchgguarb.RzPrjcontrChgGuarBService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgGuarBController extends PubBaseContrl{

	private RzPrjcontrChgGuarBService rzPrjcontrChgGuarBService;
	
	/**
	 *  跳转到RzPrjcontrChgGuarB首页
	 * @param rzPrjcontrChgGuarBQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgGuarB/toRzPrjcontrChgGuarBPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgGuarBPage(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgGuarB/rzPrjcontrChgGuarB");
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
	@RequestMapping(value = "rzPrjcontrChgGuarB/getRzPrjcontrChgGuarBAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgGuarBAll(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgGuarBQuery == null) {
			rzPrjcontrChgGuarBQuery = new RzPrjcontrChgGuarBQuery();
		}
		List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList = rzPrjcontrChgGuarBService.getRzPrjcontrChgGuarBAll(rzPrjcontrChgGuarBQuery);
		return rzPrjcontrChgGuarBList;
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
	@RequestMapping(value = "rzPrjcontrChgGuarB/getRzPrjcontrChgGuarBByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgGuarBByPage(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgGuarBQuery == null) {
			rzPrjcontrChgGuarBQuery = new RzPrjcontrChgGuarBQuery();
		}
		GridResult<RzPrjcontrChgGuarB> gridResult = rzPrjcontrChgGuarBService.getRzPrjcontrChgGuarBByPage(rzPrjcontrChgGuarBQuery);
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
	@RequestMapping(value = "rzPrjcontrChgGuarB/getRzPrjcontrChgGuarBById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgGuarBById(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgGuarB rzPrjcontrChgGuarB = rzPrjcontrChgGuarBService.getRzPrjcontrChgGuarBById(rzPrjcontrChgGuarBQuery);
		return rzPrjcontrChgGuarB;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgGuarB/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChgGuarB rzPrjcontrChgGuarB,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgGuarBService.insertOrUpdate(rzPrjcontrChgGuarB);
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
	@RequestMapping(value = "rzPrjcontrChgGuarB/deleteRzPrjcontrChgGuarBByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgGuarBByBatchId(RzPrjcontrChgGuarBQuery rzPrjcontrChgGuarBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgGuarBService.deleteRzPrjcontrChgGuarBByBatchId(rzPrjcontrChgGuarBQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgGuarBService(RzPrjcontrChgGuarBService rzPrjcontrChgGuarBService) {
		this.rzPrjcontrChgGuarBService = rzPrjcontrChgGuarBService;
	}

	@RequestMapping(value = "rzPrjcontrChgGuarB/addByBatch", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Result addByBatch(@RequestBody List<RzPrjcontrChgGuarB> editList,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgGuarBService.insertBatchRzPrjcontrChgGuarB(editList);
		return result;
	}

	@RequestMapping(value = "rzPrjcontrChgGuarB/updateByBatch", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateByBatch(@RequestBody List<RzPrjcontrChgGuarB> editList,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result= rzPrjcontrChgGuarBService.updateByBatch(editList);
		return result;
	}



































}