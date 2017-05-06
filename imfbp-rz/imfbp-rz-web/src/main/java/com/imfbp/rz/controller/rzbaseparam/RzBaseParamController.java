package com.imfbp.rz.controller.rzbaseparam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.domain.rzrisktype.RzRiskType;
import com.imfbp.rz.domain.rzrisktype.query.RzRiskTypeQuery;import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzrisktype.RzRiskTypeService;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzBaseParamController extends PubBaseContrl{

	private RzBaseParamService rzBaseParamService;
	private RzRiskTypeService rzRiskTypeService;
	/**
	 *  跳转到RzBaseParam首页
	 * @param rzBaseParamQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzBaseParam/toRzBaseParamPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzBaseParamPage(RzBaseParamQuery rzBaseParamQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzBaseParam/rzBaseParam");
		attachLoginInfo(mv);
		mv.addObject("tenant_id",getTenantId());
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
	@RequestMapping(value = "rzBaseParam/getRzBaseParamAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzBaseParamAll(RzBaseParamQuery rzBaseParamQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzBaseParamQuery == null) {
			rzBaseParamQuery = new RzBaseParamQuery();
		}
		List<RzBaseParam> rzBaseParamList = rzBaseParamService.getRzBaseParamAll(rzBaseParamQuery);
		return rzBaseParamList;
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
	@RequestMapping(value = "rzBaseParam/getRzBaseParamByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzBaseParamByPage(RzBaseParamQuery rzBaseParamQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzBaseParamQuery == null) {
			rzBaseParamQuery = new RzBaseParamQuery();
		}
		GridResult<RzBaseParam> gridResult = rzBaseParamService.getRzBaseParamByPage(rzBaseParamQuery);
		return gridResult;
	}
	/**
	 * 分页查询风险分类
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzBaseParam/getRzRiskTypeByPage", method = {RequestMethod.POST, RequestMethod.GET })
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
	@RequestMapping(value = "rzBaseParam/getRzBaseParamById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzBaseParamById(RzBaseParamQuery rzBaseParamQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzBaseParam rzBaseParam = rzBaseParamService.getRzBaseParamById(rzBaseParamQuery);
		return rzBaseParam;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzBaseParam/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzBaseParam rzBaseParam,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		
		Result result = rzBaseParamService.insertOrUpdate(rzBaseParam);
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
	@RequestMapping(value = "rzBaseParam/deleteRzBaseParamByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzBaseParamByBatchId(RzBaseParamQuery rzBaseParamQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzBaseParamService.deleteRzBaseParamByBatchId(rzBaseParamQuery);
		return result;
	}
	
	
	public void setRzBaseParamService(RzBaseParamService rzBaseParamService) {
		this.rzBaseParamService = rzBaseParamService;
	}

	public void setRzRiskTypeService(RzRiskTypeService rzRiskTypeService) {
		this.rzRiskTypeService = rzRiskTypeService;
	}

}