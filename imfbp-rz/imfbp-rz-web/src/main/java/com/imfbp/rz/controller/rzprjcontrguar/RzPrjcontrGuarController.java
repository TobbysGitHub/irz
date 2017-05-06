package com.imfbp.rz.controller.rzprjcontrguar;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrguar.RzPrjcontrGuar;
import com.imfbp.rz.domain.rzprjcontrguar.query.RzPrjcontrGuarQuery;
import com.imfbp.rz.service.rzprjcontrguar.RzPrjcontrGuarService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrGuarController extends PubBaseContrl{

	private RzPrjcontrGuarService rzPrjcontrGuarService;
	
	/**
	 *  跳转到RzPrjcontrGuar首页
	 * @param rzPrjcontrGuarQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrGuar/toRzPrjcontrGuarPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrGuarPage(RzPrjcontrGuarQuery rzPrjcontrGuarQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrGuar/rzPrjcontrGuar");
		req.setAttribute("pkPrjcontr", rzPrjcontrGuarQuery.getPkPrjcontr());
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
	@RequestMapping(value = "rzPrjcontrGuar/getRzPrjcontrGuarAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrGuarAll(RzPrjcontrGuarQuery rzPrjcontrGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrGuarQuery == null) {
			rzPrjcontrGuarQuery = new RzPrjcontrGuarQuery();
		}
		List<RzPrjcontrGuar> rzPrjcontrGuarList = rzPrjcontrGuarService.getRzPrjcontrGuarAll(rzPrjcontrGuarQuery);
		return rzPrjcontrGuarList;
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
	@RequestMapping(value = "rzPrjcontrGuar/getRzPrjcontrGuarByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrGuarByPage(RzPrjcontrGuarQuery rzPrjcontrGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrGuarQuery == null) {
			rzPrjcontrGuarQuery = new RzPrjcontrGuarQuery();
		}
		GridResult<RzPrjcontrGuar> gridResult = rzPrjcontrGuarService.getRzPrjcontrGuarByPage(rzPrjcontrGuarQuery);
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
	@RequestMapping(value = "rzPrjcontrGuar/getRzPrjcontrGuarById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrGuarById(RzPrjcontrGuarQuery rzPrjcontrGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrGuar rzPrjcontrGuar = rzPrjcontrGuarService.getRzPrjcontrGuarById(rzPrjcontrGuarQuery);
		return rzPrjcontrGuar;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrGuar/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(@RequestBody Map map,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		JSONObject jsonObject = JSONObject.fromObject(map);
		RzPrjcontrGuar rzPrjcontrGuar = (RzPrjcontrGuar) JSONObject.toBean(jsonObject, RzPrjcontrGuar.class);
		Result result = rzPrjcontrGuarService.insertOrUpdate(rzPrjcontrGuar);
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
	@RequestMapping(value = "rzPrjcontrGuar/deleteRzPrjcontrGuarByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrGuarByBatchId(RzPrjcontrGuarQuery rzPrjcontrGuarQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrGuarService.deleteRzPrjcontrGuarByBatchId(rzPrjcontrGuarQuery);
		return result;
	}
	
	
	public void setRzPrjcontrGuarService(RzPrjcontrGuarService rzPrjcontrGuarService) {
		this.rzPrjcontrGuarService = rzPrjcontrGuarService;
	}

}