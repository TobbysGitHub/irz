package com.imfbp.rz.controller.templateinputdef;

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
import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;
import com.imfbp.rz.domain.templateinputdef.query.TemplateInputDefQuery;
import com.imfbp.rz.service.templateinputdef.TemplateInputDefService;

@RestController
public class TemplateInputDefController extends BaseController {

	private TemplateInputDefService templateInputDefService;

	/**
	 * 跳转到TemplateInputDef首页
	 * 
	 * @param templateInputDefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateInputDef/toTemplateInputDefPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toTemplateInputDefPage(
			TemplateInputDefQuery templateInputDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("templateInputDef/templateInputDef");
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
	@RequestMapping(value = "templateInputDef/getTemplateInputDefAll", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateInputDefAll(
			TemplateInputDefQuery templateInputDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (templateInputDefQuery == null) {
			templateInputDefQuery = new TemplateInputDefQuery();
		}
		List<TemplateInputDef> templateInputDefList = templateInputDefService
				.getTemplateInputDefAll(templateInputDefQuery);
		return templateInputDefList;
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
	@RequestMapping(value = "templateInputDef/getTemplateInputDefByPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateInputDefByPage(
			TemplateInputDefQuery templateInputDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (templateInputDefQuery == null) {
			templateInputDefQuery = new TemplateInputDefQuery();
		}
		GridResult<TemplateInputDef> gridResult = templateInputDefService
				.getTemplateInputDefByPage(templateInputDefQuery);
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
	@RequestMapping(value = "templateInputDef/getTemplateInputDefById", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateInputDefById(
			TemplateInputDefQuery templateInputDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		TemplateInputDef templateInputDef = templateInputDefService
				.getTemplateInputDefById(templateInputDefQuery);
		return templateInputDef;
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
	@RequestMapping(value = "templateInputDef/insertOrUpdate", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(
			TemplateInputDef templateInputDef, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = templateInputDefService
				.insertOrUpdate(templateInputDef);
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
	@RequestMapping(value = "templateInputDef/deleteTemplateInputDefByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteTemplateInputDefByBatchId(
			TemplateInputDefQuery templateInputDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = null;
		try {
			result = templateInputDefService
					.deleteTemplateInputDefByBatchId(templateInputDefQuery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	@RequestMapping(value = "templateInputDef/logicDeleteTemplateInputDefByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteTemplateInputDefByBatchId(
			TemplateInputDefQuery templateInputDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = templateInputDefService
				.logicDeleteTemplateInputDefByBatchId(templateInputDefQuery);
		return result;
	}

	public void setTemplateInputDefService(
			TemplateInputDefService templateInputDefService) {
		this.templateInputDefService = templateInputDefService;
	}

}