package com.imfbp.rz.controller.rzpricecallease;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;





import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.platform.common.spring.mvc.controller.BaseController;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.domain.rzpricecallease.query.RzPricecalLeaseQuery;
import com.imfbp.rz.service.rzpricecallease.RzPricecalLeaseService;


@RestController
public class RzPricecalLeaseController extends BaseController{

	private RzPricecalLeaseService rzPricecalLeaseService;
	
	/**
	 *  跳转到RzPricecalLease首页
	 * @param rzPricecalLeaseQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecalLease/toRzPricecalLeasePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPricecalLeasePage(RzPricecalLeaseQuery rzPricecalLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.addDefaultModel("pkPricecal", rzPricecalLeaseQuery.getPkPricecal());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPricecalLease/rzPricecalLease");
		return mv;
	}
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecalLease/toRzPricecalLeaseTestPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPricecalLeaseTestPage(RzPricecalLeaseQuery rzPricecalLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.addDefaultModel("pkPricecal", rzPricecalLeaseQuery.getPkPricecal());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPricecalLease/rzPricecalLeaseTest");
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
	@RequestMapping(value = "rzPricecalLease/getRzPricecalLeaseAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalLeaseAll(RzPricecalLeaseQuery rzPricecalLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalLeaseQuery == null) {
			rzPricecalLeaseQuery = new RzPricecalLeaseQuery();
		}
		List<RzPricecalLease> rzPricecalLeaseList = rzPricecalLeaseService.getRzPricecalLeaseAll(rzPricecalLeaseQuery);
		return rzPricecalLeaseList;
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
	@RequestMapping(value = "rzPricecalLease/getRzPricecalLeaseByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalLeaseByPage(RzPricecalLeaseQuery rzPricecalLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalLeaseQuery == null) {
			rzPricecalLeaseQuery = new RzPricecalLeaseQuery();
		}
		if(StringUtils.isEmpty(rzPricecalLeaseQuery.getPkPricecal())) return null;
		GridResult<RzPricecalLease> gridResult = rzPricecalLeaseService.getRzPricecalLeaseByPage(rzPricecalLeaseQuery);
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
	@RequestMapping(value = "rzPricecalLease/getRzPricecalLeaseById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPricecalLeaseById(RzPricecalLeaseQuery rzPricecalLeaseQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPricecalLease rzPricecalLease = rzPricecalLeaseService.getRzPricecalLeaseById(rzPricecalLeaseQuery);
		return rzPricecalLease;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPricecalLease/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPricecalLease rzPricecalLease,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPricecalLeaseService.insertOrUpdate(rzPricecalLease);
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
	@RequestMapping(value = "rzPricecalLease/deleteRzPricecalLeaseByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPricecalLeaseByBatchId(RzPricecalLeaseQuery rzPricecalLeaseQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPricecalLeaseService.deleteRzPricecalLeaseByBatchId(rzPricecalLeaseQuery);
		return result;
	}
	//批量更新
	@RequestMapping(value = "rzPricecalLease/updateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateByBatchId(String list,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(StringUtils.isEmpty(list)) return null;
	    List<?> listTemp= JSONArray.fromObject(list.trim());
		if(listTemp == null || listTemp.size() <=0) return null;
		List<RzPricecalLease>list1=new ArrayList<RzPricecalLease>();
		for(Object obj:listTemp) {
			JSONObject json=(JSONObject)obj;
			@SuppressWarnings("static-access")
			RzPricecalLease le=(RzPricecalLease) json.toBean(json,RzPricecalLease.class);
			list1.add(le);
		}
		Result result = rzPricecalLeaseService.updateByBatchId(list1);
		return result;
	}
	public void setRzPricecalLeaseService(RzPricecalLeaseService rzPricecalLeaseService) {
		this.rzPricecalLeaseService = rzPricecalLeaseService;
	}

}