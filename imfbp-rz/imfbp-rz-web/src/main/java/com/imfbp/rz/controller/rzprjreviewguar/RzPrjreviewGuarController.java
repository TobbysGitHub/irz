package com.imfbp.rz.controller.rzprjreviewguar;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.domain.rzprjreview.query.RzPrjreviewQuery;
import com.imfbp.rz.domain.rzprjreviewguar.RzPrjreviewGuar;
import com.imfbp.rz.domain.rzprjreviewguar.query.RzPrjreviewGuarQuery;
import com.imfbp.rz.service.rzprjreview.RzPrjreviewService;
import com.imfbp.rz.service.rzprjreviewguar.RzPrjreviewGuarService;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.spring.mvc.controller.BaseController;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import net.sf.json.JSONObject;


@RestController
public class RzPrjreviewGuarController extends BaseController{

	private RzPrjreviewGuarService rzPrjreviewGuarService;
	@Autowired
	private RzPrjreviewService rzPrjreviewService;
	/**
	 *  跳转到RzPrjreviewGuar首页
	 * @param rzPrjreviewGuarQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjreviewGuar/toRzPrjreviewGuarPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjreviewGuarPage(RzPrjreviewGuarQuery rzPrjreviewGuarQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		if(rzPrjreviewGuarQuery==null){
			rzPrjreviewGuarQuery=new RzPrjreviewGuarQuery();
		}
		req.setAttribute("pkPrjreview", rzPrjreviewGuarQuery.getPkPrjreview());
		RzPrjreviewQuery rzPrjreviewQuery = new RzPrjreviewQuery();
		rzPrjreviewQuery.setPkPrjreview(rzPrjreviewGuarQuery.getPkPrjreview());
		req.setAttribute("approvestatus",rzPrjreviewService.getRzPrjreviewById(rzPrjreviewQuery));
		ModelAndView mv = new ModelAndView("rzPrjreviewGuar/rzPrjreviewGuar");
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
	@RequestMapping(value = "rzPrjreviewGuar/getRzPrjreviewGuarAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewGuarAll(RzPrjreviewGuarQuery rzPrjreviewGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewGuarQuery == null) {
			rzPrjreviewGuarQuery = new RzPrjreviewGuarQuery();
		}
		List<RzPrjreviewGuar> rzPrjreviewGuarList = rzPrjreviewGuarService.getRzPrjreviewGuarAll(rzPrjreviewGuarQuery);
		return rzPrjreviewGuarList;
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
	@RequestMapping(value = "rzPrjreviewGuar/getRzPrjreviewGuarByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewGuarByPage(RzPrjreviewGuarQuery rzPrjreviewGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjreviewGuarQuery == null) {
			rzPrjreviewGuarQuery = new RzPrjreviewGuarQuery();
		}
		GridResult<RzPrjreviewGuar> gridResult = rzPrjreviewGuarService.getRzPrjreviewGuarByPage(rzPrjreviewGuarQuery);
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
	@RequestMapping(value = "rzPrjreviewGuar/getRzPrjreviewGuarById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjreviewGuarById(RzPrjreviewGuarQuery rzPrjreviewGuarQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjreviewGuar rzPrjreviewGuar = rzPrjreviewGuarService.getRzPrjreviewGuarById(rzPrjreviewGuarQuery);
		return rzPrjreviewGuar;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjreviewGuar/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(@RequestBody Map map,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		JSONObject jsonObject = JSONObject.fromObject(map);
		RzPrjreviewGuar rzPrjreviewGuar = (RzPrjreviewGuar) JSONObject.toBean(jsonObject, RzPrjreviewGuar.class);
		Result result = rzPrjreviewGuarService.insertOrUpdate(rzPrjreviewGuar);
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
	@RequestMapping(value = "rzPrjreviewGuar/deleteRzPrjreviewGuarByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjreviewGuarByBatchId(RzPrjreviewGuarQuery rzPrjreviewGuarQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjreviewGuarService.deleteRzPrjreviewGuarByBatchId(rzPrjreviewGuarQuery);
		return result;
	}
	
	
	public void setRzPrjreviewGuarService(RzPrjreviewGuarService rzPrjreviewGuarService) {
		this.rzPrjreviewGuarService = rzPrjreviewGuarService;
	}

}