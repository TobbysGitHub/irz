package com.imfbp.rz.controller.templatenodedef;

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
import com.platform.common.spring.mvc.controller.BaseController;

import com.imfbp.rz.domain.templatenodedef.TemplateNodeDef;
import com.imfbp.rz.domain.templatenodedef.query.TemplateNodeDefQuery;
import com.imfbp.rz.service.templatenodedef.TemplateNodeDefService;


@RestController
public class TemplateNodeDefController extends BaseController{

	private TemplateNodeDefService templateNodeDefService;
	
	/**
	 *  跳转到TemplateNodeDef首页
	 * @param templateNodeDefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateNodeDef/toTemplateNodeDefPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toTemplateNodeDefPage(TemplateNodeDefQuery templateNodeDefQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("templateNodeDef/templateNodeDef");
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
	@RequestMapping(value = "templateNodeDef/getTemplateNodeDefAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateNodeDefAll(TemplateNodeDefQuery templateNodeDefQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (templateNodeDefQuery == null) {
			templateNodeDefQuery = new TemplateNodeDefQuery();
		}
		List<TemplateNodeDef> templateNodeDefList = templateNodeDefService.getTemplateNodeDefAll(templateNodeDefQuery);
		return templateNodeDefList;
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
	@RequestMapping(value = "templateNodeDef/getTemplateNodeDefByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateNodeDefByPage(TemplateNodeDefQuery templateNodeDefQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (templateNodeDefQuery == null) {
			templateNodeDefQuery = new TemplateNodeDefQuery();
		}
		GridResult<TemplateNodeDef> gridResult = templateNodeDefService.getTemplateNodeDefByPage(templateNodeDefQuery);
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
	@RequestMapping(value = "templateNodeDef/getTemplateNodeDefById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getTemplateNodeDefById(TemplateNodeDefQuery templateNodeDefQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		TemplateNodeDef templateNodeDef = templateNodeDefService.getTemplateNodeDefById(templateNodeDefQuery);
		return templateNodeDef;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "templateNodeDef/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(TemplateNodeDef templateNodeDef,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = templateNodeDefService.insertOrUpdate(templateNodeDef);
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
	@RequestMapping(value = "templateNodeDef/deleteTemplateNodeDefByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteTemplateNodeDefByBatchId(TemplateNodeDefQuery templateNodeDefQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = templateNodeDefService.deleteTemplateNodeDefByBatchId(templateNodeDefQuery);
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
	@RequestMapping(value = "templateNodeDef/logicDeleteTemplateNodeDefByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteTemplateNodeDefByBatchId(TemplateNodeDefQuery templateNodeDefQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = templateNodeDefService.logicDeleteTemplateNodeDefByBatchId(templateNodeDefQuery);
		return result;
	}
	
	public void setTemplateNodeDefService(TemplateNodeDefService templateNodeDefService) {
		this.templateNodeDefService = templateNodeDefService;
	}

}