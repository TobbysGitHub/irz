package com.imfbp.rz.controller.rzprjstate;

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

import com.imfbp.rz.domain.rzprjstate.RzPrjState;
import com.imfbp.rz.domain.rzprjstate.query.RzPrjStateQuery;
import com.imfbp.rz.service.rzprjstate.RzPrjStateService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjStateController extends PubBaseContrl{

	private RzPrjStateService rzPrjStateService;
	
	/**
	 *  跳转到RzPrjState首页
	 * @param rzPrjStateQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjState/toRzPrjStatePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjStatePage(RzPrjStateQuery rzPrjStateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjState/rzPrjState");
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
	@RequestMapping(value = "rzPrjState/getRzPrjStateAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjStateAll(RzPrjStateQuery rzPrjStateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjStateQuery == null) {
			rzPrjStateQuery = new RzPrjStateQuery();
		}
		List<RzPrjState> rzPrjStateList = rzPrjStateService.getRzPrjStateAll(rzPrjStateQuery);
		return rzPrjStateList;
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
	@RequestMapping(value = "rzPrjState/getRzPrjStateByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjStateByPage(RzPrjStateQuery rzPrjStateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjStateQuery == null) {
			rzPrjStateQuery = new RzPrjStateQuery();
		}
		GridResult<RzPrjState> gridResult = rzPrjStateService.getRzPrjStateByPage(rzPrjStateQuery);
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
	@RequestMapping(value = "rzPrjState/getRzPrjStateById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjStateById(RzPrjStateQuery rzPrjStateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjState rzPrjState = rzPrjStateService.getRzPrjStateById(rzPrjStateQuery);
		return rzPrjState;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjState/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjState rzPrjState,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjStateService.insertOrUpdate(rzPrjState);
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
	@RequestMapping(value = "rzPrjState/deleteRzPrjStateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjStateByBatchId(RzPrjStateQuery rzPrjStateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjStateService.deleteRzPrjStateByBatchId(rzPrjStateQuery);
		return result;
	}
	
	
	public void setRzPrjStateService(RzPrjStateService rzPrjStateService) {
		this.rzPrjStateService = rzPrjStateService;
	}

}