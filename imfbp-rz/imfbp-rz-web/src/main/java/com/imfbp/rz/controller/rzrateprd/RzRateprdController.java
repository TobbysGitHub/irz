package com.imfbp.rz.controller.rzrateprd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;
import com.imfbp.rz.service.rzrateprd.RzRateprdService;


@RestController
public class RzRateprdController extends BaseController{

	private RzRateprdService rzRateprdService;
	
	/**
	 *  跳转到RzRateprd首页
	 * @param rzRateprdQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzRateprd/toRzRateprdPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzRateprdPage(RzRateprdQuery rzRateprdQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzRateprd/rzRateprd");
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
	@RequestMapping(value = "rzRateprd/getRzRateprdAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRateprdAll(RzRateprdQuery rzRateprdQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRateprdQuery == null) {
			rzRateprdQuery = new RzRateprdQuery();
		}
		List<RzRateprd> rzRateprdList = rzRateprdService.getRzRateprdAll(rzRateprdQuery);
		return rzRateprdList;
	}

	/**
	 *  查询所有列(动态显示列名)
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzRateprd/getRzRateprdAllDynamic", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRateprdAllDynamic(RzRateprdQuery rzRateprdQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRateprdQuery == null) {
			rzRateprdQuery = new RzRateprdQuery();
		}
		List<RzRateprd> rzRateprdList = rzRateprdService.getRzRateprdAll(rzRateprdQuery);
		JSONArray jsonArray = new JSONArray();
		//固定显示列：生效日期、失效日期
		JSONObject startdate = new JSONObject();
		startdate.put("field", "startdate");
		startdate.put("title", "生效日期");
		jsonArray.add(startdate);
		JSONObject enddate = new JSONObject();
		enddate.put("field", "enddate");
		enddate.put("title", "失效日期");
		jsonArray.add(enddate);
		JSONObject pkCurrency = new JSONObject();
		pkCurrency.put("field", "pkCurrency");
		pkCurrency.put("title", "币种");
		pkCurrency.put("imfbpRefType", "currencyref");
		pkCurrency.put("renderType", "imfbpRefTransform");
		jsonArray.add(pkCurrency);
		//动态获取显示列
		for(int i = 0; i < rzRateprdList.size(); i++){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("field", "prd_name" + rzRateprdList.get(i).getPkRateprd());
			jsonObject.put("title", rzRateprdList.get(i).getPrdName() + "(%)");
			jsonArray.add(jsonObject);
		}
		//固定显示列：制单人、制单时间、审批人、审批时间、状态
		JSONObject billmaker = new JSONObject();
		billmaker.put("field", "billmaker");
		billmaker.put("title", "制单人");
		billmaker.put("imfbpRefType", "userref");
		billmaker.put("renderType", "imfbpRefTransform");
		jsonArray.add(billmaker);
		JSONObject billdate = new JSONObject();
		billdate.put("field", "billdate");
		billdate.put("title", "制单日期");
		jsonArray.add(billdate);
		JSONObject approveid = new JSONObject();
		approveid.put("field", "approveid");
		approveid.put("title", "审批人");
		approveid.put("imfbpRefType", "userref");
		approveid.put("renderType", "imfbpRefTransform");
		jsonArray.add(approveid);
		JSONObject approvedate = new JSONObject();
		approvedate.put("field", "approvedate");
		approvedate.put("title", "审批时间");
		jsonArray.add(approvedate);
//		JSONObject approvestatus = new JSONObject();
//		approvestatus.put("field", "approvestatus");
//		approvestatus.put("title", "状态");
//		jsonArray.add(approvestatus);
		return jsonArray;
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
	@RequestMapping(value = "rzRateprd/getRzRateprdByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRateprdByPage(RzRateprdQuery rzRateprdQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRateprdQuery == null) {
			rzRateprdQuery = new RzRateprdQuery();
		}
		GridResult<RzRateprd> gridResult = rzRateprdService.getRzRateprdByPage(rzRateprdQuery);
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
	@RequestMapping(value = "rzRateprd/getRzRateprdById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzRateprdById(RzRateprdQuery rzRateprdQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzRateprd rzRateprd = rzRateprdService.getRzRateprdById(rzRateprdQuery);
		return rzRateprd;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzRateprd/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzRateprd rzRateprd,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRateprdService.insertOrUpdate(rzRateprd);
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
	@RequestMapping(value = "rzRateprd/deleteRzRateprdByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzRateprdByBatchId(RzRateprdQuery rzRateprdQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzRateprdService.deleteRzRateprdByBatchId(rzRateprdQuery);
		return result;
	}
	
	
	public void setRzRateprdService(RzRateprdService rzRateprdService) {
		this.rzRateprdService = rzRateprdService;
	}

}