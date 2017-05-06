package com.imfbp.rz.controller.rzrisktype;

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

import com.imfbp.rz.domain.rzrisktype.RzRiskType;
import com.imfbp.rz.domain.rzrisktype.query.RzRiskTypeQuery;
import com.imfbp.rz.service.rzrisktype.RzRiskTypeService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzRiskTypeController extends PubBaseContrl{

	private RzRiskTypeService rzRiskTypeService;
	
	/**
	 *  跳转到RzRiskType首页
	 * @param rzRiskTypeQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzRiskType/toRzRiskTypePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzRiskTypePage(RzRiskTypeQuery rzRiskTypeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzRiskType/rzRiskType");
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
	@RequestMapping(value = "rzRiskType/getRzRiskTypeAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRiskTypeAll(RzRiskTypeQuery rzRiskTypeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRiskTypeQuery == null) {
			rzRiskTypeQuery = new RzRiskTypeQuery();
		}
		List<RzRiskType> rzRiskTypeList = rzRiskTypeService.getRzRiskTypeAll(rzRiskTypeQuery);
		return rzRiskTypeList;
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
	@RequestMapping(value = "rzRiskType/getRzRiskTypeByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRiskTypeByPage(RzRiskTypeQuery rzRiskTypeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRiskTypeQuery == null) {
			rzRiskTypeQuery = new RzRiskTypeQuery();
		}
		GridResult<RzRiskType> gridResult = rzRiskTypeService.getRzRiskTypeByPage(rzRiskTypeQuery);
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
	@RequestMapping(value = "rzRiskType/getRzRiskTypeById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzRiskTypeById(RzRiskTypeQuery rzRiskTypeQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzRiskType rzRiskType = rzRiskTypeService.getRzRiskTypeById(rzRiskTypeQuery);
		return rzRiskType;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzRiskType/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzRiskType rzRiskType,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRiskTypeService.insertOrUpdate(rzRiskType);
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
	@RequestMapping(value = "rzRiskType/deleteRzRiskTypeByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzRiskTypeByBatchId(RzRiskTypeQuery rzRiskTypeQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRiskTypeService.deleteRzRiskTypeByBatchId(rzRiskTypeQuery);
		return result;
	}
	
	
	public void setRzRiskTypeService(RzRiskTypeService rzRiskTypeService) {
		this.rzRiskTypeService = rzRiskTypeService;
	}

}