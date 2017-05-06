package com.imfbp.rz.controller.templateversion;

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
import com.imfbp.rz.domain.templateversion.TemplateVersion;
import com.imfbp.rz.domain.templateversion.query.TemplateVersionQuery;
import com.imfbp.rz.service.templateversion.TemplateVersionService;
import com.imfbp.rz.controller.pub.PubBaseContrl;

@RestController
public class TemplateVersionController extends PubBaseContrl {

	private TemplateVersionService templateVersionService;

	/**
	 * 跳转到TemplateVersion首页
	 * 
	 * @param templateVersionQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "templateVersion/toTemplateVersionPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toTemplateVersionPage(
			TemplateVersionQuery templateVersionQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("templateVersion/templateVersion");
		attachLoginInfo(mv);
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
	@RequestMapping(value = "templateVersion/getTemplateVersionAll", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateVersionAll(
			TemplateVersionQuery templateVersionQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		if (templateVersionQuery == null) {
			templateVersionQuery = new TemplateVersionQuery();
		}
		List<TemplateVersion> templateVersionList = templateVersionService
				.getTemplateVersionAll(templateVersionQuery);
		return templateVersionList;
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
	@RequestMapping(value = "templateVersion/getTemplateVersionByPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateVersionByPage(
			TemplateVersionQuery templateVersionQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		if (templateVersionQuery == null) {
			templateVersionQuery = new TemplateVersionQuery();
		}
		GridResult<TemplateVersion> gridResult = templateVersionService
				.getTemplateVersionByPage(templateVersionQuery);
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
	@RequestMapping(value = "templateVersion/getTemplateVersionById", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateVersionById(
			TemplateVersionQuery templateVersionQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		TemplateVersion templateVersion = templateVersionService
				.getTemplateVersionById(templateVersionQuery);
		return templateVersion;
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
	@RequestMapping(value = "templateVersion/insertOrUpdate", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(TemplateVersion templateVersion,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = null;
		try {
			result = templateVersionService.insertOrUpdate(templateVersion);
		} catch (Exception e) {
			e.printStackTrace();
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
	@RequestMapping(value = "templateVersion/deleteTemplateVersionByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteTemplateVersionByBatchId(
			TemplateVersionQuery templateVersionQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = templateVersionService
				.deleteTemplateVersionByBatchId(templateVersionQuery);
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
	@RequestMapping(value = "templateVersion/logicDeleteTemplateVersionByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteTemplateVersionByBatchId(
			TemplateVersionQuery templateVersionQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = templateVersionService
				.logicDeleteTemplateVersionByBatchId(templateVersionQuery);
		return result;
	}

	public void setTemplateVersionService(
			TemplateVersionService templateVersionService) {
		this.templateVersionService = templateVersionService;
	}

}