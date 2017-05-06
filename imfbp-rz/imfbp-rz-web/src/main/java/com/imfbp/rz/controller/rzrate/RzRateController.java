package com.imfbp.rz.controller.rzrate;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.service.rzrateb.RzRateBService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;
import com.imfbp.rz.service.rzrate.RzRateService;


@RestController
public class RzRateController extends PubBaseContrl {

	private RzRateService rzRateService;

	@Autowired
	private RzRateBService rzRateBService;
	
	/**
	 *  跳转到RzRate首页
	 * @param rzRateQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzRate/toRzRatePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzRatePage(RzRateQuery rzRateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzRate/rzRate");
		attachLoginInfo(mv);
		attachModuleValue(mv, req);
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
	@RequestMapping(value = "rzRate/getRzRateAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRateAll(RzRateQuery rzRateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRateQuery == null) {
			rzRateQuery = new RzRateQuery();
		}
		List<RzRate> rzRateList = rzRateService.getRzRateAll(rzRateQuery);
		return rzRateList;
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
	@RequestMapping(value = "rzRate/getRzRateByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRateByPage(RzRateQuery rzRateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRateQuery == null) {
			rzRateQuery = new RzRateQuery();
		}
		GridResult<RzRate> gridResult = rzRateService.getRzRateByPage(rzRateQuery);
		List<RzRate> rzRateList = gridResult.getRows();
		if(rzRateList.size() > 0){
			for(RzRate rzRate : rzRateList){
				RzRateBQuery rzRateBQuery = new RzRateBQuery();
				rzRateBQuery.setPkRate(rzRate.getPkRate());
				List<RzRateB> rzRateBList = rzRateBService.getRzRateBAll(rzRateBQuery);
				if(rzRateBList != null && rzRateBList.size() > 0){
					rzRate.setRzRateBList(rzRateBList);
				}
			}
		}
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
	@RequestMapping(value = "rzRate/getRzRateById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzRateById(RzRateQuery rzRateQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzRate rzRate = rzRateService.getRzRateById(rzRateQuery);
		return rzRate;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
//	@RequestMapping(value = "rzRate/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
//	public @ResponseBody Object insertOrUpdate(RzRate rzRate,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		Result result = rzRateService.insertOrUpdate(rzRate);
//		return result;
//	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzRate/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(@RequestBody Map<String, String> map,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		//修改
		if(StringUtils.isNotEmpty(map.get("pkRate"))){
			result = rzRateService.updateRzRate(map);
			return result;
		}
		//新增
		result = rzRateService.insertRzRate(map);
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
	@RequestMapping(value = "rzRate/deleteRzRateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzRateByBatchId(RzRateQuery rzRateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRateService.deleteRzRateByBatchId(rzRateQuery);
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
	@RequestMapping(value = "rzRate/logicDeleteRzRateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzRateByBatchId(RzRateQuery rzRateQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRateService.logicDeleteRzRateByBatchId(rzRateQuery);
		return result;
	}

	/**
	 * 审批利率时判断时间段
	 * @param rzRate
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzRate/checkPeriod", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object checkPeriod(RzRate rzRate,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRateService.checkPeriod(rzRate);
		return result;
	}
	
	public void setRzRateService(RzRateService rzRateService) {
		this.rzRateService = rzRateService;
	}

}