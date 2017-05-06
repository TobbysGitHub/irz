package com.imfbp.rz.controller.rzprjcontrchg;

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

import com.imfbp.rz.domain.rzprjcontrchg.RzPrjcontrChg;
import com.imfbp.rz.domain.rzprjcontrchg.query.RzPrjcontrChgQuery;
import com.imfbp.rz.service.rzprjcontrchg.RzPrjcontrChgService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgController extends PubBaseContrl{

	private RzPrjcontrChgService rzPrjcontrChgService;
	
	/**
	 *  跳转到RzPrjcontrChg首页
	 * @param rzPrjcontrChgQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChg/toRzPrjcontrChgPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgPage(RzPrjcontrChgQuery rzPrjcontrChgQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChg/rzPrjcontrChg");
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
	@RequestMapping(value = "rzPrjcontrChg/getRzPrjcontrChgAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgAll(RzPrjcontrChgQuery rzPrjcontrChgQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgQuery == null) {
			rzPrjcontrChgQuery = new RzPrjcontrChgQuery();
		}
		List<RzPrjcontrChg> rzPrjcontrChgList = rzPrjcontrChgService.getRzPrjcontrChgAll(rzPrjcontrChgQuery);
		return rzPrjcontrChgList;
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
	@RequestMapping(value = "rzPrjcontrChg/getRzPrjcontrChgByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgByPage(RzPrjcontrChgQuery rzPrjcontrChgQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgQuery == null) {
			rzPrjcontrChgQuery = new RzPrjcontrChgQuery();
		}
		GridResult<RzPrjcontrChg> gridResult = rzPrjcontrChgService.getRzPrjcontrChgByPage(rzPrjcontrChgQuery);
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
	@RequestMapping(value = "rzPrjcontrChg/getRzPrjcontrChgById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChg rzPrjcontrChg = rzPrjcontrChgService.getRzPrjcontrChgById(rzPrjcontrChgQuery);
		return rzPrjcontrChg;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChg/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChg rzPrjcontrChg,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgService.insertOrUpdate(rzPrjcontrChg);
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
	@RequestMapping(value = "rzPrjcontrChg/deleteRzPrjcontrChgByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgByBatchId(RzPrjcontrChgQuery rzPrjcontrChgQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgService.deleteRzPrjcontrChgByBatchId(rzPrjcontrChgQuery);
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
	@RequestMapping(value = "rzPrjcontrChg/logicDeleteRzPrjcontrChgByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPrjcontrChgByBatchId(RzPrjcontrChgQuery rzPrjcontrChgQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgService.logicDeleteRzPrjcontrChgByBatchId(rzPrjcontrChgQuery);
		return result;
	}
	
	public void setRzPrjcontrChgService(RzPrjcontrChgService rzPrjcontrChgService) {
		this.rzPrjcontrChgService = rzPrjcontrChgService;
	}

}