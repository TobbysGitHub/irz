package com.imfbp.rz.controller.rzprjcontrcf;

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

import com.imfbp.rz.domain.rzprjcontrcf.RzPrjcontrCf;
import com.imfbp.rz.domain.rzprjcontrcf.query.RzPrjcontrCfQuery;
import com.imfbp.rz.service.rzprjcontrcf.RzPrjcontrCfService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrCfController extends PubBaseContrl{

	private RzPrjcontrCfService rzPrjcontrCfService;
	
	/**
	 *  跳转到RzPrjcontrCf首页
	 * @param rzPrjcontrCfQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrCf/toRzPrjcontrCfPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrCfPage(RzPrjcontrCfQuery rzPrjcontrCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrCf/rzPrjcontrCf");
		attachLoginInfo(mv);
		req.setAttribute("pkPrjcontr", rzPrjcontrCfQuery.getPkPrjcontr());
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
	@RequestMapping(value = "rzPrjcontrCf/getRzPrjcontrCfAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrCfAll(RzPrjcontrCfQuery rzPrjcontrCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrCfQuery == null) {
			rzPrjcontrCfQuery = new RzPrjcontrCfQuery();
		}
		List<RzPrjcontrCf> rzPrjcontrCfList = rzPrjcontrCfService.getRzPrjcontrCfAll(rzPrjcontrCfQuery);
		return rzPrjcontrCfList;
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
	@RequestMapping(value = "rzPrjcontrCf/getRzPrjcontrCfByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrCfByPage(RzPrjcontrCfQuery rzPrjcontrCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrCfQuery == null) {
			rzPrjcontrCfQuery = new RzPrjcontrCfQuery();
		}
		rzPrjcontrCfQuery.setSort("planpmtdate");
		rzPrjcontrCfQuery.setOrder("asc");
		GridResult<RzPrjcontrCf> gridResult = rzPrjcontrCfService.getRzPrjcontrCfByPage(rzPrjcontrCfQuery);
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
	@RequestMapping(value = "rzPrjcontrCf/getRzPrjcontrCfById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrCfById(RzPrjcontrCfQuery rzPrjcontrCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrCf rzPrjcontrCf = rzPrjcontrCfService.getRzPrjcontrCfById(rzPrjcontrCfQuery);
		return rzPrjcontrCf;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrCf/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrCf rzPrjcontrCf,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrCfService.insertOrUpdate(rzPrjcontrCf);
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
	@RequestMapping(value = "rzPrjcontrCf/deleteRzPrjcontrCfByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrCfByBatchId(RzPrjcontrCfQuery rzPrjcontrCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrCfService.deleteRzPrjcontrCfByBatchId(rzPrjcontrCfQuery);
		return result;
	}
	
	
	public void setRzPrjcontrCfService(RzPrjcontrCfService rzPrjcontrCfService) {
		this.rzPrjcontrCfService = rzPrjcontrCfService;
	}

}