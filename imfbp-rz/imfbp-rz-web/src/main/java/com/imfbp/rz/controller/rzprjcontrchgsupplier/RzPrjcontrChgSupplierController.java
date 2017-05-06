package com.imfbp.rz.controller.rzprjcontrchgsupplier;

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

import com.imfbp.rz.domain.rzprjcontrchgsupplier.RzPrjcontrChgSupplier;
import com.imfbp.rz.domain.rzprjcontrchgsupplier.query.RzPrjcontrChgSupplierQuery;
import com.imfbp.rz.service.rzprjcontrchgsupplier.RzPrjcontrChgSupplierService;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPrjcontrChgSupplierController extends PubBaseContrl{

	private RzPrjcontrChgSupplierService rzPrjcontrChgSupplierService;
	
	/**
	 *  跳转到RzPrjcontrChgSupplier首页
	 * @param rzPrjcontrChgSupplierQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontrChgSupplier/toRzPrjcontrChgSupplierPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrChgSupplierPage(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		//设置合同变更主键
		req.setAttribute("pkPrjcontrChg", rzPrjcontrChgSupplierQuery.getPkPrjcontrChg());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontrChgSupplier/rzPrjcontrChgSupplier");
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
	@RequestMapping(value = "rzPrjcontrChgSupplier/getRzPrjcontrChgSupplierAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgSupplierAll(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgSupplierQuery == null) {
			rzPrjcontrChgSupplierQuery = new RzPrjcontrChgSupplierQuery();
		}
		List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList = rzPrjcontrChgSupplierService.getRzPrjcontrChgSupplierAll(rzPrjcontrChgSupplierQuery);
		return rzPrjcontrChgSupplierList;
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
	@RequestMapping(value = "rzPrjcontrChgSupplier/getRzPrjcontrChgSupplierByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgSupplierByPage(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrChgSupplierQuery == null) {
			rzPrjcontrChgSupplierQuery = new RzPrjcontrChgSupplierQuery();
		}
		GridResult<RzPrjcontrChgSupplier> gridResult = rzPrjcontrChgSupplierService.getRzPrjcontrChgSupplierByPage(rzPrjcontrChgSupplierQuery);
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
	@RequestMapping(value = "rzPrjcontrChgSupplier/getRzPrjcontrChgSupplierById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrChgSupplierById(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontrChgSupplier rzPrjcontrChgSupplier = rzPrjcontrChgSupplierService.getRzPrjcontrChgSupplierById(rzPrjcontrChgSupplierQuery);
		return rzPrjcontrChgSupplier;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontrChgSupplier/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontrChgSupplier rzPrjcontrChgSupplier,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgSupplierService.insertOrUpdate(rzPrjcontrChgSupplier);
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
	@RequestMapping(value = "rzPrjcontrChgSupplier/deleteRzPrjcontrChgSupplierByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrChgSupplierByBatchId(RzPrjcontrChgSupplierQuery rzPrjcontrChgSupplierQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrChgSupplierService.deleteRzPrjcontrChgSupplierByBatchId(rzPrjcontrChgSupplierQuery);
		return result;
	}
	
	
	public void setRzPrjcontrChgSupplierService(RzPrjcontrChgSupplierService rzPrjcontrChgSupplierService) {
		this.rzPrjcontrChgSupplierService = rzPrjcontrChgSupplierService;
	}

}