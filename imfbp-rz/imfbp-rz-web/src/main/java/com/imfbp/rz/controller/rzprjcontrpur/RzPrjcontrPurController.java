package com.imfbp.rz.controller.rzprjcontrpur;

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

import com.imfbp.rz.domain.rzprjcontrpur.RzPrjcontrPur;
import com.imfbp.rz.domain.rzprjcontrpur.query.RzPrjcontrPurQuery;
import com.imfbp.rz.service.rzprjcontrpur.RzPrjcontrPurService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrPurController extends PubBaseContrl{

	private RzPrjcontrPurService rzPrjcontrPurService;
	
	/**
	 *  跳转到RzPrjcontrPur首页
	 * @param rzPrjcontrPurQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrPur/toRzPrjcontrPurPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrPurPage(RzPrjcontrPurQuery rzPrjcontrPurQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrPur/rzPrjcontrPur");
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
	@RequestMapping(value = "rzPrjcontrPur/getRzPrjcontrPurAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrPurAll(RzPrjcontrPurQuery rzPrjcontrPurQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrPurQuery == null) {
			rzPrjcontrPurQuery = new RzPrjcontrPurQuery();
		}
		List<RzPrjcontrPur> rzPrjcontrPurList = rzPrjcontrPurService.getRzPrjcontrPurAll(rzPrjcontrPurQuery);
		return rzPrjcontrPurList;
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
	@RequestMapping(value = "rzPrjcontrPur/getRzPrjcontrPurByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrPurByPage(RzPrjcontrPurQuery rzPrjcontrPurQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrPurQuery == null) {
			rzPrjcontrPurQuery = new RzPrjcontrPurQuery();
		}
		GridResult<RzPrjcontrPur> gridResult = rzPrjcontrPurService.getRzPrjcontrPurByPage(rzPrjcontrPurQuery);
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
	@RequestMapping(value = "rzPrjcontrPur/getRzPrjcontrPurById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrPurById(RzPrjcontrPurQuery rzPrjcontrPurQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrPur rzPrjcontrPur = rzPrjcontrPurService.getRzPrjcontrPurById(rzPrjcontrPurQuery);
		return rzPrjcontrPur;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrPur/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrPur rzPrjcontrPur,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrPurService.insertOrUpdate(rzPrjcontrPur);
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
	@RequestMapping(value = "rzPrjcontrPur/deleteRzPrjcontrPurByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrPurByBatchId(RzPrjcontrPurQuery rzPrjcontrPurQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrPurService.deleteRzPrjcontrPurByBatchId(rzPrjcontrPurQuery);
		return result;
	}
	
	
	public void setRzPrjcontrPurService(RzPrjcontrPurService rzPrjcontrPurService) {
		this.rzPrjcontrPurService = rzPrjcontrPurService;
	}

}