package com.imfbp.rz.controller.templaterulesdef;

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

import com.imfbp.rz.domain.templaterulesdef.TemplateRulesDef;
import com.imfbp.rz.domain.templaterulesdef.query.TemplateRulesDefQuery;
import com.imfbp.rz.service.templaterulesdef.TemplateRulesDefService;

@RestController
public class TemplateRulesDefController extends BaseController {

	private TemplateRulesDefService templateRulesDefService;

	/**
	 * 跳转到TemplateRulesDef首页
	 * 
	 * @param templateRulesDefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateRulesDef/toTemplateRulesDefPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toTemplateRulesDefPage(
			TemplateRulesDefQuery templateRulesDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("templateRulesDef/templateRulesDef");
		return mv;
	}

	/**
	 * 查询所有
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateRulesDef/getTemplateRulesDefAll", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateRulesDefAll(
			TemplateRulesDefQuery templateRulesDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (templateRulesDefQuery == null) {
			templateRulesDefQuery = new TemplateRulesDefQuery();
		}
		List<TemplateRulesDef> templateRulesDefList = templateRulesDefService
				.getTemplateRulesDefAll(templateRulesDefQuery);
		return templateRulesDefList;
	}

	/**
	 * 分页查询
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateRulesDef/getTemplateRulesDefByPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateRulesDefByPage(
			TemplateRulesDefQuery templateRulesDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (templateRulesDefQuery == null) {
			templateRulesDefQuery = new TemplateRulesDefQuery();
		}
		GridResult<TemplateRulesDef> gridResult = templateRulesDefService
				.getTemplateRulesDefByPage(templateRulesDefQuery);
		return gridResult;
	}

	/**
	 * 根据id查询
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateRulesDef/getTemplateRulesDefById", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateRulesDefById(
			TemplateRulesDefQuery templateRulesDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		TemplateRulesDef templateRulesDef = templateRulesDefService
				.getTemplateRulesDefById(templateRulesDefQuery);
		return templateRulesDef;
	}

	/**
	 * 添加或修改
	 * 
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "templateRulesDef/insertOrUpdate", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(
			TemplateRulesDef templateRulesDef, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = templateRulesDefService
				.insertOrUpdate(templateRulesDef);
		return result;
	}

	/**
	 * 根据Id批量删除 (真正删除数据库数据)
	 * 
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "templateRulesDef/deleteTemplateRulesDefByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteTemplateRulesDefByBatchId(
			TemplateRulesDefQuery templateRulesDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = templateRulesDefService
				.deleteTemplateRulesDefByBatchId(templateRulesDefQuery);
		return result;
	}

	/**
	 * 根据Id批量逻辑删除(修改数据库数据为删除状态)
	 * 
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "templateRulesDef/logicDeleteTemplateRulesDefByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteTemplateRulesDefByBatchId(
			TemplateRulesDefQuery templateRulesDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = templateRulesDefService
				.logicDeleteTemplateRulesDefByBatchId(templateRulesDefQuery);
		return result;
	}

	public void setTemplateRulesDefService(
			TemplateRulesDefService templateRulesDefService) {
		this.templateRulesDefService = templateRulesDefService;
	}

}