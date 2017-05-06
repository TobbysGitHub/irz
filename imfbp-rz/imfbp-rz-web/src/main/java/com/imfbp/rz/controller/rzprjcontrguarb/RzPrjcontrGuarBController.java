package com.imfbp.rz.controller.rzprjcontrguarb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;
import com.imfbp.rz.service.rzprjcontrguarb.RzPrjcontrGuarBService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrGuarBController extends PubBaseContrl{

	private RzPrjcontrGuarBService rzPrjcontrGuarBService;
	
	/**
	 *  跳转到RzPrjcontrGuarB首页
	 * @param rzPrjcontrGuarBQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrGuarB/toRzPrjcontrGuarBPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrGuarBPage(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrGuarB/rzPrjcontrGuarB");
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
	@RequestMapping(value = "rzPrjcontrGuarB/getRzPrjcontrGuarBAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrGuarBAll(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrGuarBQuery == null) {
			rzPrjcontrGuarBQuery = new RzPrjcontrGuarBQuery();
		}
		List<RzPrjcontrGuarB> rzPrjcontrGuarBList = rzPrjcontrGuarBService.getRzPrjcontrGuarBAll(rzPrjcontrGuarBQuery);
		return rzPrjcontrGuarBList;
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
	@RequestMapping(value = "rzPrjcontrGuarB/getRzPrjcontrGuarBByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrGuarBByPage(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrGuarBQuery == null) {
			rzPrjcontrGuarBQuery = new RzPrjcontrGuarBQuery();
		}
		GridResult<RzPrjcontrGuarB> gridResult = rzPrjcontrGuarBService.getRzPrjcontrGuarBByPage(rzPrjcontrGuarBQuery);
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
	@RequestMapping(value = "rzPrjcontrGuarB/getRzPrjcontrGuarBById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrGuarBById(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrGuarB rzPrjcontrGuarB = rzPrjcontrGuarBService.getRzPrjcontrGuarBById(rzPrjcontrGuarBQuery);
		return rzPrjcontrGuarB;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrGuarB/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrGuarB rzPrjcontrGuarB,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrGuarBService.insertOrUpdate(rzPrjcontrGuarB);
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
	@RequestMapping(value = "rzPrjcontrGuarB/deleteRzPrjcontrGuarBByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrGuarBByBatchId(RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrGuarBService.deleteRzPrjcontrGuarBByBatchId(rzPrjcontrGuarBQuery);
		return result;
	}
	
	
	public void setRzPrjcontrGuarBService(RzPrjcontrGuarBService rzPrjcontrGuarBService) {
		this.rzPrjcontrGuarBService = rzPrjcontrGuarBService;
	}

	@RequestMapping(value = "rzPrjcontrGuarB/addByBatch", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Result addByBatch(@RequestBody List<RzPrjcontrGuarB> editList,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrGuarBService.insertBatchRzPrjcontrGuarB(editList);
		return result;
	}

	@RequestMapping(value = "rzPrjcontrGuarB/updateByBatch", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateByBatch(@RequestBody List<RzPrjcontrGuarB> editList,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result= rzPrjcontrGuarBService.updateByBatch(editList);
		return result;
	}
}