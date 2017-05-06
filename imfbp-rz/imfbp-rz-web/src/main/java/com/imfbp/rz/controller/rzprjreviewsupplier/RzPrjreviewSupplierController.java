package com.imfbp.rz.controller.rzprjreviewsupplier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.domain.rzprjreviewsupplier.RzPrjreviewSupplier;
import com.imfbp.rz.domain.rzprjreviewsupplier.query.RzPrjreviewSupplierQuery;
import com.imfbp.rz.service.rzprjreview.RzPrjreviewService;
import com.imfbp.rz.service.rzprjreviewsupplier.RzPrjreviewSupplierService;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.spring.mvc.controller.BaseController;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzPrjreviewSupplierController extends BaseController{

	private RzPrjreviewSupplierService rzPrjreviewSupplierService;
	@Autowired
	private RzPrjreviewService rzPrjreviewService;
	
	/**
	 *  跳转到RzPrjreviewSupplier首页
	 * @param rzPrjreviewSupplierQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewSupplier/toRzPrjreviewSupplierPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjreviewSupplierPage(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if(rzPrjreviewSupplierQuery==null){
			rzPrjreviewSupplierQuery=new RzPrjreviewSupplierQuery();
		}
		req.setAttribute("pkPrjreview", rzPrjreviewSupplierQuery.getPkPrjreview());
		
		RzPrjreviewQuery rzPrjreviewQuery = new RzPrjreviewQuery();
		rzPrjreviewQuery.setPkPrjreview(rzPrjreviewSupplierQuery.getPkPrjreview());
		req.setAttribute("approvestatus",rzPrjreviewService.getRzPrjreviewById(rzPrjreviewQuery));
		ModelAndView mv = new ModelAndView("rzPrjreviewSupplier/rzPrjreviewSupplier");
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
	@RequestMapping(value = "rzPrjreviewSupplier/getRzPrjreviewSupplierAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewSupplierAll(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewSupplierQuery == null) {
			rzPrjreviewSupplierQuery = new RzPrjreviewSupplierQuery();
		}
		List<RzPrjreviewSupplier> rzPrjreviewSupplierList = rzPrjreviewSupplierService.getRzPrjreviewSupplierAll(rzPrjreviewSupplierQuery);
		return rzPrjreviewSupplierList;
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
	@RequestMapping(value = "rzPrjreviewSupplier/getRzPrjreviewSupplierByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewSupplierByPage(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewSupplierQuery == null) {
			rzPrjreviewSupplierQuery = new RzPrjreviewSupplierQuery();
		}
		GridResult<RzPrjreviewSupplier> gridResult = rzPrjreviewSupplierService.getRzPrjreviewSupplierByPage(rzPrjreviewSupplierQuery);
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
	@RequestMapping(value = "rzPrjreviewSupplier/getRzPrjreviewSupplierById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewSupplierById(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjreviewSupplier rzPrjreviewSupplier = rzPrjreviewSupplierService.getRzPrjreviewSupplierById(rzPrjreviewSupplierQuery);
		return rzPrjreviewSupplier;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjreviewSupplier/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjreviewSupplier rzPrjreviewSupplier,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewSupplierService.insertOrUpdate(rzPrjreviewSupplier);
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
	@RequestMapping(value = "rzPrjreviewSupplier/deleteRzPrjreviewSupplierByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjreviewSupplierByBatchId(RzPrjreviewSupplierQuery rzPrjreviewSupplierQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewSupplierService.deleteRzPrjreviewSupplierByBatchId(rzPrjreviewSupplierQuery);
		return result;
	}
	
	
	public void setRzPrjreviewSupplierService(RzPrjreviewSupplierService rzPrjreviewSupplierService) {
		this.rzPrjreviewSupplierService = rzPrjreviewSupplierService;
	}

}