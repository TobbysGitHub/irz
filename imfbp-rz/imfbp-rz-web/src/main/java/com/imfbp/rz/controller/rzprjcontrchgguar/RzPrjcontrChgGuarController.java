package com.imfbp.rz.controller.rzprjcontrchgguar;

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

import com.imfbp.rz.domain.rzprjcontrchgguar.RzPrjcontrChgGuar;
import com.imfbp.rz.domain.rzprjcontrchgguar.query.RzPrjcontrChgGuarQuery;
import com.imfbp.rz.service.rzprjcontrchgguar.RzPrjcontrChgGuarService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgGuarController extends PubBaseContrl{

	private RzPrjcontrChgGuarService rzPrjcontrChgGuarService;
	
	/**
	 *  跳转到RzPrjcontrChgGuar首页
	 * @param rzPrjcontrChgGuarQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgGuar/toRzPrjcontrChgGuarPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgGuarPage(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		req.setAttribute("pkPrjcontrChg", rzPrjcontrChgGuarQuery.getPkPrjcontrChg());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgGuar/rzPrjcontrChgGuar");
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
	@RequestMapping(value = "rzPrjcontrChgGuar/getRzPrjcontrChgGuarAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgGuarAll(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgGuarQuery == null) {
			rzPrjcontrChgGuarQuery = new RzPrjcontrChgGuarQuery();
		}
		List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList = rzPrjcontrChgGuarService.getRzPrjcontrChgGuarAll(rzPrjcontrChgGuarQuery);
		return rzPrjcontrChgGuarList;
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
	@RequestMapping(value = "rzPrjcontrChgGuar/getRzPrjcontrChgGuarByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgGuarByPage(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgGuarQuery == null) {
			rzPrjcontrChgGuarQuery = new RzPrjcontrChgGuarQuery();
		}
		GridResult<RzPrjcontrChgGuar> gridResult = rzPrjcontrChgGuarService.getRzPrjcontrChgGuarByPage(rzPrjcontrChgGuarQuery);
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
	@RequestMapping(value = "rzPrjcontrChgGuar/getRzPrjcontrChgGuarById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgGuarById(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgGuar rzPrjcontrChgGuar = rzPrjcontrChgGuarService.getRzPrjcontrChgGuarById(rzPrjcontrChgGuarQuery);
		return rzPrjcontrChgGuar;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgGuar/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(@RequestBody Map map,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		JSONObject jsonObject = JSONObject.fromObject(map);
		RzPrjcontrChgGuar rzPrjcontrChgGuar = (RzPrjcontrChgGuar)JSONObject.toBean(jsonObject, RzPrjcontrChgGuar.class);
		Result result = rzPrjcontrChgGuarService.insertOrUpdate(rzPrjcontrChgGuar);
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
	@RequestMapping(value = "rzPrjcontrChgGuar/deleteRzPrjcontrChgGuarByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgGuarByBatchId(RzPrjcontrChgGuarQuery rzPrjcontrChgGuarQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgGuarService.deleteRzPrjcontrChgGuarByBatchId(rzPrjcontrChgGuarQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgGuarService(RzPrjcontrChgGuarService rzPrjcontrChgGuarService) {
		this.rzPrjcontrChgGuarService = rzPrjcontrChgGuarService;
	}

}