package com.imfbp.rz.controller.rzprjapplyeqpt;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.platform.common.spring.mvc.controller.BaseController;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;
import com.imfbp.rz.service.rzprjapply.RzPrjapplyService;
import com.imfbp.rz.service.rzprjapplyeqpt.RzPrjapplyEqptService;
import com.imfbp.rz.util.ExcelWriterTool;
import com.imfbp.rz.util.WebUtils;


@RestController
public class RzPrjapplyEqptController extends BaseController{

	private RzPrjapplyEqptService rzPrjapplyEqptService;
	@Autowired
	private RzPrjapplyService rzPrjapplyService;
	
	/**
	 *  跳转到RzPrjapplyEqpt首页
	 * @param rzPrjapplyEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjapplyEqpt/toRzPrjapplyEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjapplyEqptPage(RzPrjapplyEqptQuery rzPrjapplyEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if(rzPrjapplyEqptQuery ==null){
			rzPrjapplyEqptQuery= new RzPrjapplyEqptQuery();
		}
		if(null != rzPrjapplyEqptQuery){
			RzPrjapplyQuery rzPrjapplyQuery = new RzPrjapplyQuery();
			rzPrjapplyQuery.setPkPrjapply(rzPrjapplyEqptQuery.getPkPrjapply());
			RzPrjapply apply = rzPrjapplyService.getRzPrjapplyById(rzPrjapplyQuery);
			if (null != apply){
				req.setAttribute("approvestatus", apply.getApprovestatus());
			}
		}
		req.setAttribute("pkPrjapply",rzPrjapplyEqptQuery.getPkPrjapply());
		ModelAndView mv = new ModelAndView("rzPrjapplyEqpt/rzPrjapplyEqpt");
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
	@RequestMapping(value = "rzPrjapplyEqpt/getRzPrjapplyEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjapplyEqptAll(RzPrjapplyEqptQuery rzPrjapplyEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjapplyEqptQuery == null) {
			rzPrjapplyEqptQuery = new RzPrjapplyEqptQuery();
		}
		List<RzPrjapplyEqpt> rzPrjapplyEqptList = rzPrjapplyEqptService.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
		return rzPrjapplyEqptList;
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
	@RequestMapping(value = "rzPrjapplyEqpt/getRzPrjapplyEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjapplyEqptByPage(RzPrjapplyEqptQuery rzPrjapplyEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjapplyEqptQuery == null) {
			rzPrjapplyEqptQuery = new RzPrjapplyEqptQuery();
		}
		GridResult<RzPrjapplyEqpt> gridResult = rzPrjapplyEqptService.getRzPrjapplyEqptByPage(rzPrjapplyEqptQuery);
		return gridResult;
	}
	
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjapplyEqpt/exportRzPrjapplyEqptAll", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportRzPrjapplyEqptAll(RzPrjapplyEqptQuery rzPrjapplyEqptQuery, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		OutputStream out;
		if (rzPrjapplyEqptQuery == null) {
			rzPrjapplyEqptQuery = new RzPrjapplyEqptQuery();
		}
		try {
			out = resp.getOutputStream();
			List<RzPrjapplyEqpt> rzPrjapplyEqptList = rzPrjapplyEqptService.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
			ExcelWriterTool<RzPrjapplyEqpt> ex = new ExcelWriterTool<RzPrjapplyEqpt>();
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("供应商", "pkCustomer");
			map.put("制造商", "mfg");
			map.put("设备名称", "pkEqpt");
			map.put("客户名称", "pkCustomer");
			map.put("设备分类", "eqptType");
			map.put("设备品牌", "eqptBrand");
			map.put("设备型号", "eqptVer");
			map.put("出厂编码", "mfgNo");
			map.put("出厂日期", "mfgDate");
			map.put("设备价格(元)", "eqptPrice");
			map.put("设备数量", "eqptNum");
			map.put("设备总价(元)", "eqptPriceTotal");
			map.put("设备评估价值（元）", "assessPrice");
			map.put("交货日期", "deliveryDate");
			map.put("设备净值(元)", "netVal");
			resp.addHeader("Content-Disposition", "attachment;filename=" + WebUtils.encodeFileName("项目设备.xls", req));
			resp.setContentType("application/force-download");// 设置为下载application/force-download
			ex.exportExcel("立项项目表格", map, rzPrjapplyEqptList, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	@RequestMapping(value = "rzPrjapplyEqpt/getRzPrjapplyEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjapplyEqptById(RzPrjapplyEqptQuery rzPrjapplyEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjapplyEqpt rzPrjapplyEqpt = rzPrjapplyEqptService.getRzPrjapplyEqptById(rzPrjapplyEqptQuery);
		return rzPrjapplyEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjapplyEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjapplyEqpt rzPrjapplyEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjapplyEqptService.insertOrUpdate(rzPrjapplyEqpt);
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
	@RequestMapping(value = "rzPrjapplyEqpt/deleteRzPrjapplyEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjapplyEqptByBatchId(RzPrjapplyEqptQuery rzPrjapplyEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjapplyEqptService.deleteRzPrjapplyEqptByBatchId(rzPrjapplyEqptQuery);
		return result;
	}
	
	
	public void setRzPrjapplyEqptService(RzPrjapplyEqptService rzPrjapplyEqptService) {
		this.rzPrjapplyEqptService = rzPrjapplyEqptService;
	}

}