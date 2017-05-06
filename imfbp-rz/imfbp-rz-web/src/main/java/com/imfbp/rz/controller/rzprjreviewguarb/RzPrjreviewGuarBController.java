package com.imfbp.rz.controller.rzprjreviewguarb;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.imfbp.rz.domain.rzprjreviewguarb.RzPrjreviewGuarB;
import com.imfbp.rz.domain.rzprjreviewguarb.query.RzPrjreviewGuarBQuery;
import com.imfbp.rz.service.rzprjreviewguarb.RzPrjreviewGuarBService;


@RestController
public class RzPrjreviewGuarBController extends BaseController{

	private RzPrjreviewGuarBService rzPrjreviewGuarBService;
	
	/**
	 *  跳转到RzPrjreviewGuarB首页
	 * @param rzPrjreviewGuarBQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewGuarB/toRzPrjreviewGuarBPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjreviewGuarBPage(String toEditList,String addOrUpdate,RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if(rzPrjreviewGuarBQuery==null){
			rzPrjreviewGuarBQuery=new RzPrjreviewGuarBQuery();
		}
		if(addOrUpdate!=null&& addOrUpdate.length()>0){
			req.setAttribute("addOrUpdate", addOrUpdate);
		}
		if(toEditList!=null&&toEditList.length()>0){
			req.setAttribute("toEditList", toEditList);
		}
		
		req.setAttribute("pkPrjreviewGuar", rzPrjreviewGuarBQuery.getPkPrjreviewGuar());
		ModelAndView mv = new ModelAndView("rzPrjreviewGuarB/rzPrjreviewGuarB");
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
	@RequestMapping(value = "rzPrjreviewGuarB/getRzPrjreviewGuarBAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewGuarBAll(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewGuarBQuery == null) {
			rzPrjreviewGuarBQuery = new RzPrjreviewGuarBQuery();
		}
		List<RzPrjreviewGuarB> rzPrjreviewGuarBList = rzPrjreviewGuarBService.getRzPrjreviewGuarBAll(rzPrjreviewGuarBQuery);
		return rzPrjreviewGuarBList;
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
	@RequestMapping(value = "rzPrjreviewGuarB/getRzPrjreviewGuarBByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewGuarBByPage(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewGuarBQuery == null) {
			rzPrjreviewGuarBQuery = new RzPrjreviewGuarBQuery();
		}
		GridResult<RzPrjreviewGuarB> gridResult = rzPrjreviewGuarBService.getRzPrjreviewGuarBByPage(rzPrjreviewGuarBQuery);
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
	@RequestMapping(value = "rzPrjreviewGuarB/getRzPrjreviewGuarBById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewGuarBById(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjreviewGuarB rzPrjreviewGuarB = rzPrjreviewGuarBService.getRzPrjreviewGuarBById(rzPrjreviewGuarBQuery);
		return rzPrjreviewGuarB;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjreviewGuarB/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjreviewGuarB rzPrjreviewGuarB,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewGuarBService.insertOrUpdate(rzPrjreviewGuarB);
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
	@RequestMapping(value = "rzPrjreviewGuarB/deleteRzPrjreviewGuarBByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjreviewGuarBByBatchId(RzPrjreviewGuarBQuery rzPrjreviewGuarBQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewGuarBService.deleteRzPrjreviewGuarBByBatchId(rzPrjreviewGuarBQuery);
		return result;
	}
	
	@RequestMapping(value = "rzPrjreviewGuarB/updateByBatch", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateByBatch(@RequestBody List<RzPrjreviewGuarB> editList,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result= rzPrjreviewGuarBService.updateByBatch(editList);
		return result;
	}
	@RequestMapping(value = "rzPrjreviewGuarB/addByBatch", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Result addByBatch(@RequestBody List<RzPrjreviewGuarB> editList,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewGuarBService.insertBatchRzPrjreviewGuarB(editList);
		return result;
	}
	
	
	public void setRzPrjreviewGuarBService(RzPrjreviewGuarBService rzPrjreviewGuarBService) {
		this.rzPrjreviewGuarBService = rzPrjreviewGuarBService;
	}

}