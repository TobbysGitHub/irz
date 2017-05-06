package com.imfbp.rz.controller.templatedef;

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
import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;
import com.imfbp.rz.service.templatedef.TemplateDefService;

@RestController
public class TemplateDefController extends BaseController {

	private TemplateDefService templateDefService;

	/**
	 * 跳转到TemplateDef首页
	 * 
	 * @param templateDefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateDef/toTemplateDefPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toTemplateDefPage(TemplateDefQuery templateDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("templateDef/templateDef");
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
	@RequestMapping(value = "templateDef/getTemplateDefAll", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateDefAll(
			TemplateDefQuery templateDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		if (templateDefQuery == null) {
			templateDefQuery = new TemplateDefQuery();
		}
		List<TemplateDef> templateDefList = templateDefService
				.getTemplateDefAll(templateDefQuery);
		return templateDefList;
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
	@RequestMapping(value = "templateDef/getTemplateDefByPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateDefByPage(
			TemplateDefQuery templateDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		if (templateDefQuery == null) {
			templateDefQuery = new TemplateDefQuery();
		}
		GridResult<TemplateDef> gridResult = templateDefService
				.getTemplateDefByPage(templateDefQuery);
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
	@RequestMapping(value = "templateDef/getTemplateDefById", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateDefById(
			TemplateDefQuery templateDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		TemplateDef templateDef = templateDefService
				.getTemplateDefById(templateDefQuery);
		return templateDef;
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
	@RequestMapping(value = "templateDef/insertOrUpdate", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(TemplateDef templateDef,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = null;
		try {
			result = templateDefService.insertOrUpdate(templateDef);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result();
			result.setSuccess(false);
		}
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
	@RequestMapping(value = "templateDef/deleteTemplateDefByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteTemplateDefByBatchId(
			TemplateDefQuery templateDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = null;
		try {
			result = templateDefService
					.deleteTemplateDefByBatchId(templateDefQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	@RequestMapping(value = "templateDef/logicDeleteTemplateDefByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteTemplateDefByBatchId(
			TemplateDefQuery templateDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = templateDefService
				.logicDeleteTemplateDefByBatchId(templateDefQuery);
		return result;
	}

	public void setTemplateDefService(TemplateDefService templateDefService) {
		this.templateDefService = templateDefService;
	}

}