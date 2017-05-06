package com.imfbp.rz.controller.rzprjcontr;

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

import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.domain.template.BaseSystemInfo;
import com.imfbp.rz.service.formula.FormulaParseCal;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzPrjcontrController extends PubBaseContrl{

	private RzPrjcontrService rzPrjcontrService;
	
	@Autowired
	private FormulaParseCal formulaParseCal;
	
	/**
	 *  跳转到RzPrjcontr首页
	 * @param rzPrjcontrQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjcontr/toRzPrjcontrPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjcontrPage(RzPrjcontrQuery rzPrjcontrQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjcontr/rzPrjcontr");
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
	@RequestMapping(value = "rzPrjcontr/getRzPrjcontrAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrAll(RzPrjcontrQuery rzPrjcontrQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrQuery == null) {
			rzPrjcontrQuery = new RzPrjcontrQuery();
		}
		List<RzPrjcontr> rzPrjcontrList = rzPrjcontrService.getRzPrjcontrAll(rzPrjcontrQuery);
		return rzPrjcontrList;
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
	@RequestMapping(value = "rzPrjcontr/getRzPrjcontrByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrByPage(RzPrjcontrQuery rzPrjcontrQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjcontrQuery == null) {
			rzPrjcontrQuery = new RzPrjcontrQuery();
		}
		GridResult<RzPrjcontr> gridResult = rzPrjcontrService.getRzPrjcontrByPage(rzPrjcontrQuery);
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
	@RequestMapping(value = "rzPrjcontr/getRzPrjcontrById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjcontrById(RzPrjcontrQuery rzPrjcontrQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjcontr rzPrjcontr = rzPrjcontrService.getRzPrjcontrById(rzPrjcontrQuery);
		return rzPrjcontr;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjcontr/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjcontr rzPrjcontr,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrService.insertOrUpdate(rzPrjcontr, getTenantId());
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
	@RequestMapping(value = "rzPrjcontr/deleteRzPrjcontrByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjcontrByBatchId(RzPrjcontrQuery rzPrjcontrQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrService.deleteRzPrjcontrByBatchId(rzPrjcontrQuery);
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
	@RequestMapping(value = "rzPrjcontr/logicDeleteRzPrjcontrByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPrjcontrByBatchId(RzPrjcontrQuery rzPrjcontrQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjcontrService.logicDeleteRzPrjcontrByBatchId(rzPrjcontrQuery);
		return result;
	}

	@RequestMapping(value = "rzPrjcontr/createRzPrjcontr", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object createRzPrjcontr(RzPrjcontr rzPrjcontr,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		BaseSystemInfo baseSystemInfo = new BaseSystemInfo();
		baseSystemInfo.setTenantId(getTenantId());
		baseSystemInfo.setSystemCode(getSysCode());
		baseSystemInfo.setUserId(this.getUserId());
		baseSystemInfo.setFileType("1");
		
		formulaParseCal.formulaParse(rzPrjcontr, "rzPrjcontr", baseSystemInfo);
		return result;
	}
	
	public void setRzPrjcontrService(RzPrjcontrService rzPrjcontrService) {
		this.rzPrjcontrService = rzPrjcontrService;
	}

}