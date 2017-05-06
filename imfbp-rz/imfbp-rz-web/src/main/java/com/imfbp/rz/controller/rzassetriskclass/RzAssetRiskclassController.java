package com.imfbp.rz.controller.rzassetriskclass;

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

import com.imfbp.rz.domain.rzassetriskclass.RzAssetRiskclass;
import com.imfbp.rz.domain.rzassetriskclass.query.RzAssetRiskclassQuery;
import com.imfbp.rz.service.rzassetriskclass.RzAssetRiskclassService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzAssetRiskclassController extends PubBaseContrl{

	private RzAssetRiskclassService rzAssetRiskclassService;
	
	/**
	 *  跳转到RzAssetRiskclass首页
	 * @param rzAssetRiskclassQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAssetRiskclass/toRzAssetRiskclassPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzAssetRiskclassPage(RzAssetRiskclassQuery rzAssetRiskclassQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzAssetRiskclass/rzAssetRiskclass");
		attachLoginInfo(mv);
		attachModuleValue(mv,req);
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
	@RequestMapping(value = "rzAssetRiskclass/getRzAssetRiskclassAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAssetRiskclassAll(RzAssetRiskclassQuery rzAssetRiskclassQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzAssetRiskclassQuery == null) {
			rzAssetRiskclassQuery = new RzAssetRiskclassQuery();
		}
		List<RzAssetRiskclass> rzAssetRiskclassList = rzAssetRiskclassService.getRzAssetRiskclassAll(rzAssetRiskclassQuery);
		return rzAssetRiskclassList;
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
	@RequestMapping(value = "rzAssetRiskclass/getRzAssetRiskclassByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAssetRiskclassByPage(RzAssetRiskclassQuery rzAssetRiskclassQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzAssetRiskclassQuery == null) {
			rzAssetRiskclassQuery = new RzAssetRiskclassQuery();
		}
		GridResult<RzAssetRiskclass> gridResult = rzAssetRiskclassService.getRzAssetRiskclassByPage(rzAssetRiskclassQuery);
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
	@RequestMapping(value = "rzAssetRiskclass/getRzAssetRiskclassById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzAssetRiskclass rzAssetRiskclass = rzAssetRiskclassService.getRzAssetRiskclassById(rzAssetRiskclassQuery);
		return rzAssetRiskclass;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzAssetRiskclass/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzAssetRiskclass rzAssetRiskclass,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzAssetRiskclassService.insertOrUpdate(rzAssetRiskclass);
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
	@RequestMapping(value = "rzAssetRiskclass/deleteRzAssetRiskclassByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzAssetRiskclassByBatchId(RzAssetRiskclassQuery rzAssetRiskclassQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzAssetRiskclassService.deleteRzAssetRiskclassByBatchId(rzAssetRiskclassQuery);
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
	@RequestMapping(value = "rzAssetRiskclass/logicDeleteRzAssetRiskclassByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzAssetRiskclassByBatchId(RzAssetRiskclassQuery rzAssetRiskclassQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzAssetRiskclassService.logicDeleteRzAssetRiskclassByBatchId(rzAssetRiskclassQuery);
		return result;
	}
	
	public void setRzAssetRiskclassService(RzAssetRiskclassService rzAssetRiskclassService) {
		this.rzAssetRiskclassService = rzAssetRiskclassService;
	}

}