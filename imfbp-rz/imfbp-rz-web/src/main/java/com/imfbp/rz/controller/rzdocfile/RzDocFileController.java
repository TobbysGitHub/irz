package com.imfbp.rz.controller.rzdocfile;

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

import com.imfbp.rz.domain.rzdocfile.RzDocFile;
import com.imfbp.rz.domain.rzdocfile.query.RzDocFileQuery;
import com.imfbp.rz.service.rzdocfile.RzDocFileService;


@RestController
public class RzDocFileController extends BaseController{

	private RzDocFileService rzDocFileService;
	
	/**
	 *  跳转到RzDocFile首页
	 * @param rzDocFileQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzDocFile/toRzDocFilePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzDocFilePage(RzDocFileQuery rzDocFileQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzDocFile/rzDocFile");
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
	@RequestMapping(value = "rzDocFile/getRzDocFileAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzDocFileAll(RzDocFileQuery rzDocFileQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzDocFileQuery == null) {
			rzDocFileQuery = new RzDocFileQuery();
		}
		List<RzDocFile> rzDocFileList = rzDocFileService.getRzDocFileAll(rzDocFileQuery);
		return rzDocFileList;
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
	@RequestMapping(value = "rzDocFile/getRzDocFileByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzDocFileByPage(RzDocFileQuery rzDocFileQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzDocFileQuery == null) {
			rzDocFileQuery = new RzDocFileQuery();
		}
		GridResult<RzDocFile> gridResult = rzDocFileService.getRzDocFileByPage(rzDocFileQuery);
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
	@RequestMapping(value = "rzDocFile/getRzDocFileById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzDocFileById(RzDocFileQuery rzDocFileQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzDocFile rzDocFile = rzDocFileService.getRzDocFileById(rzDocFileQuery);
		return rzDocFile;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzDocFile/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzDocFile rzDocFile,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzDocFileService.insertOrUpdate(rzDocFile);
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
	@RequestMapping(value = "rzDocFile/deleteRzDocFileByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzDocFileByBatchId(RzDocFileQuery rzDocFileQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzDocFileService.deleteRzDocFileByBatchId(rzDocFileQuery);
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
	@RequestMapping(value = "rzDocFile/logicDeleteRzDocFileByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzDocFileByBatchId(RzDocFileQuery rzDocFileQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzDocFileService.logicDeleteRzDocFileByBatchId(rzDocFileQuery);
		return result;
	}
	
	public void setRzDocFileService(RzDocFileService rzDocFileService) {
		this.rzDocFileService = rzDocFileService;
	}

}