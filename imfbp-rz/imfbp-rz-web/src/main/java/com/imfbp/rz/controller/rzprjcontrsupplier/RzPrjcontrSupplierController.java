package com.imfbp.rz.controller.rzprjcontrsupplier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjcontrsupplier.query.RzPrjcontrSupplierQuery;
import com.imfbp.rz.service.rzprjcontrsupplier.RzPrjcontrSupplierService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrSupplierController extends PubBaseContrl{

	private RzPrjcontrSupplierService rzPrjcontrSupplierService;
	
	/**
	 *  跳转到RzPrjcontrSupplier首页
	 * @param rzPrjcontrSupplierQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrSupplier/toRzPrjcontrSupplierPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrSupplierPage(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery,String busitype, HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		//合同主键
		req.setAttribute("pkPrjcontr", rzPrjcontrSupplierQuery.getPkPrjcontr());
		//业务类型
		req.setAttribute("busitype", busitype);
		ModelAndView mv = new ModelAndView("rzPrjcontrSupplier/rzPrjcontrSupplier");
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
	@RequestMapping(value = "rzPrjcontrSupplier/getRzPrjcontrSupplierAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrSupplierAll(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrSupplierQuery == null) {
			rzPrjcontrSupplierQuery = new RzPrjcontrSupplierQuery();
		}
		List<RzPrjcontrSupplier> rzPrjcontrSupplierList = rzPrjcontrSupplierService.getRzPrjcontrSupplierAll(rzPrjcontrSupplierQuery);
		return rzPrjcontrSupplierList;
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
	@RequestMapping(value = "rzPrjcontrSupplier/getRzPrjcontrSupplierByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrSupplierByPage(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrSupplierQuery == null) {
			rzPrjcontrSupplierQuery = new RzPrjcontrSupplierQuery();
		}
		GridResult<RzPrjcontrSupplier> gridResult = rzPrjcontrSupplierService.getRzPrjcontrSupplierByPage(rzPrjcontrSupplierQuery);
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
	@RequestMapping(value = "rzPrjcontrSupplier/getRzPrjcontrSupplierById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrSupplierById(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrSupplier rzPrjcontrSupplier = rzPrjcontrSupplierService.getRzPrjcontrSupplierById(rzPrjcontrSupplierQuery);
		return rzPrjcontrSupplier;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrSupplier/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrSupplier rzPrjcontrSupplier,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrSupplierService.insertOrUpdate(rzPrjcontrSupplier);
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
	@RequestMapping(value = "rzPrjcontrSupplier/deleteRzPrjcontrSupplierByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrSupplierByBatchId(RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrSupplierService.deleteRzPrjcontrSupplierByBatchId(rzPrjcontrSupplierQuery);
		return result;
	}
	
	
	public void setRzPrjcontrSupplierService(RzPrjcontrSupplierService rzPrjcontrSupplierService) {
		this.rzPrjcontrSupplierService = rzPrjcontrSupplierService;
	}

}