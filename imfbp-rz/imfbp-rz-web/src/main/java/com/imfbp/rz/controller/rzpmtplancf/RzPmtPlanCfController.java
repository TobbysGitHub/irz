package com.imfbp.rz.controller.rzpmtplancf;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.service.rzbaseparam.RzBaseParamService;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplancf.RzPmtPlanCfService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzPmtPlanCfController extends PubBaseContrl{

	private RzPmtPlanCfService rzPmtPlanCfService;
	 @Autowired
	private RzContrTallyService rzContrTallyService;
	 @Autowired
    private RzPmtPlanService rzPmtPlanService;
	 @Autowired
	 private RzPrjcontrService rzPrjcontrService;
	 @Autowired
	 private RzBaseParamService rzBaseParamService;
	 /**
	 *  跳转到RzPmtPlanCf首页
	 * @param rzPmtPlanCfQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPmtPlanCf/toRzPmtPlanCfPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPmtPlanCfPage(RzPmtPlanCfQuery rzPmtPlanCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.addDefaultModel("pkPmtPlan",rzPmtPlanCfQuery.getPkPmtPlan());
		result.addDefaultModel("ver", rzPmtPlanCfQuery.getVer());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPmtPlanCf/rzPmtPlanCf");
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
	@RequestMapping(value = "rzPmtPlanCf/getRzPmtPlanCfAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanCfAll(RzPmtPlanCfQuery rzPmtPlanCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPmtPlanCfQuery == null) {
			rzPmtPlanCfQuery = new RzPmtPlanCfQuery();
		}
		List<RzPmtPlanCf> rzPmtPlanCfList = rzPmtPlanCfService.getRzPmtPlanCfAll(rzPmtPlanCfQuery);
		return rzPmtPlanCfList;
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
	@RequestMapping(value = "rzPmtPlanCf/getRzPmtPlanCfByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanCfByPage(RzPmtPlanCfQuery rzPmtPlanCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPmtPlanCfQuery == null) {
			rzPmtPlanCfQuery = new RzPmtPlanCfQuery();
		}
		if(StringUtils.isEmpty(rzPmtPlanCfQuery.getPkPmtPlan())) return null;
		GridResult<RzPmtPlanCf> gridResult = rzPmtPlanCfService.getRzPmtPlanCfByPage(rzPmtPlanCfQuery);
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
	@RequestMapping(value = "rzPmtPlanCf/getRzPmtPlanCfById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanCfById(RzPmtPlanCfQuery rzPmtPlanCfQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPmtPlanCf rzPmtPlanCf = rzPmtPlanCfService.getRzPmtPlanCfById(rzPmtPlanCfQuery);
		return rzPmtPlanCf;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPmtPlanCf/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPmtPlanCf rzPmtPlanCf,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPmtPlanCfService.insertOrUpdate(rzPmtPlanCf);
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
	@RequestMapping(value = "rzPmtPlanCf/deleteRzPmtPlanCfByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPmtPlanCfByBatchId(RzPmtPlanCfQuery rzPmtPlanCfQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPmtPlanCfService.deleteRzPmtPlanCfByBatchId(rzPmtPlanCfQuery);
		return result;
	}
	//根据条件更新
	@RequestMapping(value = "rzPmtPlanCf/updateRzPmtPlanCfByCondition", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateRzPmtPlanCfByCondition(@RequestParam("data") String data, HttpServletResponse resp, ModelMap context) {
		 	List<?> listTemp= JSONArray.fromObject(data.trim());
			if(listTemp == null || listTemp.size() <=0) return null;
			List<RzPmtPlanCfQuery>list=new ArrayList<RzPmtPlanCfQuery>();
			for(Object obj:listTemp) {
				JSONObject json=(JSONObject)obj;
				@SuppressWarnings("static-access")
				RzPmtPlanCfQuery cf=(RzPmtPlanCfQuery) json.toBean(json,RzPmtPlanCfQuery.class);
				list.add(cf);
			}
			if(list.size()>0) return rzPmtPlanCfService.updateRzPmtPlanCfByCondition(list.get(0),list.get(1));
			return null;
	}
	
	//往台账表中插入数据
		@RequestMapping(value = "rzPmtPlanCf/insertToTally", method = {RequestMethod.POST, RequestMethod.GET })
		public @ResponseBody Object insertToTally(RzPmtPlanCf rzPmtPlanCf,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			rzPmtPlanCfService.insertToTally(rzPmtPlanCf);
			return null;
		}
		//修改合同状态
		@RequestMapping(value = "rzPmtPlanCf/updateContr", method = {RequestMethod.POST, RequestMethod.GET })
		public @ResponseBody Object updateContr(RzPmtPlanCfQuery rzPmtPlanCf,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			Result result=new Result();
			String pk=rzPmtPlanCf.getPkPmtPlan();
			RzPmtPlanQuery query=new RzPmtPlanQuery();
			query.setPkPmtPlan(pk);
			List<RzPmtPlan>rzPmtPlanList=rzPmtPlanService.getRzPmtPlanAll(query);
			if(ToolUtils.isNotEmptyCollection(rzPmtPlanList)){
				RzPmtPlan plan=rzPmtPlanList.get(0);
				if(plan != null){
					RzPrjcontr rzPrjcontr=new RzPrjcontr();
					rzPrjcontr.setPkPrjcontr(plan.getPkPrjcontr());
					rzPrjcontr.setContrStatus(Integer.valueOf(rzPmtPlanCf.getState()));
					rzPrjcontrService.updateRzPrjcontrById(rzPrjcontr);
					RzContrTallyQuery rzContrTally=new RzContrTallyQuery();
					rzContrTally.setPkPrjcontr(plan.getPkPrjcontr());
					RzContrTallyQuery rzContrTally1=new RzContrTallyQuery();
					rzContrTally1.setContrStatus(Integer.valueOf(rzPmtPlanCf.getState()));
					rzContrTallyService.updateRzContrTallyByCondition(rzContrTally1, rzContrTally);
					result.setSuccess(true);
				}
			}
			return result;
		}
       //核销功能
		/*@RequestMapping(value = "rzPmtPlanCf/checkOut", method = {RequestMethod.POST, RequestMethod.GET })
		public @ResponseBody Object checkOut(RzPmtPlanCf rzPmtPlanCf,@RequestParam("data") String data,RzPmtPlanCfQuery rzPmtPlanCf1,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			rzPmtPlanCfService.checkOut(rzPmtPlanCf, data, rzPmtPlanCf1);
			return null;
		}*/
	public void setRzPmtPlanCfService(RzPmtPlanCfService rzPmtPlanCfService) {
		this.rzPmtPlanCfService = rzPmtPlanCfService;
	}

}