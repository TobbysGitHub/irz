package com.imfbp.rz.controller.rzpricecalcf;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.platform.common.utils.json.JsonUtils;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecalcf.query.RzPricecalCfQuery;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;
import com.imfbp.rz.service.rzpricecalcf.RzPricecalCfService;


@RestController
public class RzPricecalCfController extends BaseController{

	private RzPricecalCfService rzPricecalCfService;
	
	/**
	 *  跳转到RzPricecalCf首页
	 * @param rzPricecalCfQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecalCf/toRzPricecalCfPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPricecalCfPage(RzPricecalCfQuery rzPricecalCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.addDefaultModel("pkPricecal", rzPricecalCfQuery.getPkPricecal());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPricecalCf/rzPricecalCf");
		//将从主页面传来的主表主键保存在session中
	    req.getSession().setAttribute("pkPricecal",rzPricecalCfQuery.getPkPricecal());
		return mv;
	}
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPricecalCf/toRzPricecalCfTestPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPricecalCfTestPage(RzPricecalCfQuery rzPricecalCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.addDefaultModel("pkPricecal", rzPricecalCfQuery.getPkPricecal());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPricecalCf/rzPricecalCfTest");
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
	@RequestMapping(value = "rzPricecalCf/getRzPricecalCfAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalCfAll(RzPricecalCfQuery rzPricecalCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalCfQuery == null) {
			rzPricecalCfQuery = new RzPricecalCfQuery();
		}
		List<RzPricecalCf> rzPricecalCfList = rzPricecalCfService.getRzPricecalCfAll(rzPricecalCfQuery);
		return rzPricecalCfList;
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
	@RequestMapping(value = "rzPricecalCf/getRzPricecalCfByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPricecalCfByPage(RzPricecalCfQuery rzPricecalCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPricecalCfQuery == null) {
			rzPricecalCfQuery = new RzPricecalCfQuery();
		}
		if(StringUtils.isEmpty(rzPricecalCfQuery.getPkPricecal())) return null;
		GridResult<RzPricecalCf> gridResult = rzPricecalCfService.getRzPricecalCfByPage(rzPricecalCfQuery);
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
	@RequestMapping(value = "rzPricecalCf/getRzPricecalCfById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPricecalCfById(RzPricecalCfQuery rzPricecalCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPricecalCf rzPricecalCf = rzPricecalCfService.getRzPricecalCfById(rzPricecalCfQuery);
		return rzPricecalCf;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPricecalCf/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPricecalCf rzPricecalCf,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPricecalCfService.insertOrUpdate(rzPricecalCf);
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
	@RequestMapping(value = "rzPricecalCf/deleteRzPricecalCfByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPricecalCfByBatchId(RzPricecalCfQuery rzPricecalCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPricecalCfService.deleteRzPricecalCfByBatchId(rzPricecalCfQuery);
		return result;
	}
	@RequestMapping(value = "rzPricecalCf/updateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateByBatchId(String list,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(StringUtils.isEmpty(list)) return null;
		resp.setContentType("text/json"); 
		resp.setCharacterEncoding("UTF-8"); 
		try {
			list=new String(request.getParameter("list").getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		List<?> listTemp= JSONArray.fromObject(list.trim());
		if(listTemp == null || listTemp.size() <=0) return null;
		List<RzPricecalCf>list1=new ArrayList<RzPricecalCf>();
		for(Object obj:listTemp) {
			JSONObject json=(JSONObject)obj;
			@SuppressWarnings("static-access")
			RzPricecalCf cf=(RzPricecalCf) json.toBean(json,RzPricecalCf.class);
			list1.add(cf);
		}
		Result result = rzPricecalCfService.updateByBatchId(list1);
		return result;
	}
	
	@RequestMapping(value = "rzPricecalCf/getCountAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getCountAll(RzPricecalCfQuery rzPricecalCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context){
		List<RzPricecalCf> rzPricecalCfList = rzPricecalCfService.getRzPricecalCfAll(rzPricecalCfQuery);
		RzPricecalCf rzPricecalCf=new RzPricecalCf();
		if(rzPricecalCfList!=null&&rzPricecalCfList.size()>0){
			Double receivableAmt=0.0;
			Double receivableInt=0.0;
			Double receivableRent=0.0;
			Double receivableFee=0.0;
			Double payableSc=0.0;
			for(int i=0;i<rzPricecalCfList.size();i++){
				if(rzPricecalCfList.get(i).getReceivableAmt()!=null){
					receivableAmt+=rzPricecalCfList.get(i).getReceivableAmt();
				}
				if(rzPricecalCfList.get(i).getReceivableInt()!=null){
					receivableInt+=rzPricecalCfList.get(i).getReceivableInt();
				}
				if(rzPricecalCfList.get(i).getReceivableRent()!=null){
					receivableRent+=rzPricecalCfList.get(i).getReceivableRent();
				}
				if(rzPricecalCfList.get(i).getReceivableFee()!=null){
					receivableFee+=rzPricecalCfList.get(i).getReceivableFee();
				}
				if(rzPricecalCfList.get(i).getPayableSc()!=null){
					payableSc+=rzPricecalCfList.get(i).getPayableSc();
				}
			}
			rzPricecalCf.setReceivableAmt(receivableAmt);
			rzPricecalCf.setReceivableInt(receivableInt);
			rzPricecalCf.setReceivableRent(receivableRent);
			rzPricecalCf.setReceivableFee(receivableFee);
			rzPricecalCf.setPayableSc(payableSc);
		}
		return rzPricecalCf;
	}
	
	public void setRzPricecalCfService(RzPricecalCfService rzPricecalCfService) {
		this.rzPricecalCfService = rzPricecalCfService;
	}

}