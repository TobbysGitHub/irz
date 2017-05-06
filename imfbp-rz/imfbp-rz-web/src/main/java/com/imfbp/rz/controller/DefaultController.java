package com.imfbp.rz.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ifbp.boss.rpc.smalluser.domain.query.SmallUserQuery;
import com.ifbp.message.rpc.smallmessage.domain.SmallMessage;
import com.ifbp.message.rpc.smallmessage.domain.query.SmallMessageQuery;
import com.imfbp.rz.controller.pub.BdBaseController;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.service.DefaultService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.service.rzpricecal.RzPricecalService;
import com.imfbp.rz.service.rzprjapply.RzPrjapplyService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.util.DoubleUtils;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.seculity.moduleinfo.Moduleinfo;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.login.enums.LoginEnum;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

@RestController
public class DefaultController extends BdBaseController {

	private HashMap<String, String> currentSystem;

	private DefaultService defaultService;
	
	@Autowired
	private RzPricecalService rzPricecalService;
	@Autowired
	private RzPrjapplyService rzPrjapplyService;
	@Autowired
	private RzPrjcontrService rzPrjcontrService;
	
	@Autowired
	private RzPmtPlanLeaseService rzPmtPlanLeaseService;
	@Autowired
	private RzPmtPlanService rzPmtPlanService;
	/**
	 * 跳转到Mktsetdef首页
	 * 
	 * @param mktsetdefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "toIndex", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toIndex(HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		String menu = getMenuTree();
		String user = getUserCode();
		String rname = getUserRealName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);

		String org = session.getAttribute(LoginEnum.LONGIN_ORG_NAME.getloginInfo()).toString();

		result.addDefaultModel("menu", menu);
		result.addDefaultModel("user", user);
		result.addDefaultModel("today", today);
		result.addDefaultModel("org", org);
		result.addDefaultModel("rname", rname);
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	/**
	 * 得到头像url
	 * 
	 * @param smallUserQuery
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "getImageUrl", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getImageUrl(SmallUserQuery smallUserQuery) {
		smallUserQuery.setId(getUserId());
		Result resultImg = defaultService.getImageUrl(smallUserQuery);
		return resultImg;
	}

	/**
	 * 得到客户个数
	 * 
	 * @param mktsetdefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "getCustNum", method = { RequestMethod.POST, RequestMethod.GET })
	public Result getCustNum(HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		return result;
	}

	/**
	 * 根据日期得到新增客户，新增贷款申请，成交合同，合同金额等
	 * 
	 * @param mktsetdefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "getInfoByDate", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getInfoByDate(String timeBunket, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		return getNumByDate(timeBunket);
	}

	/**
	 * 根据日期得到新增客户，新增投保单，新增保单，总保费等
	 * 
	 * @param mark
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	private Result getNumByDate(String timeBunket) {
		Result result = new Result();
		String tenantId = getTenantId();
		try {
			result.setSuccess(true);
			String startDate = "";
			String endDate = "";
			if (getStartAndEndDate(timeBunket) != null) {
				startDate = getStartAndEndDate(timeBunket).get("startDate");
				endDate = getStartAndEndDate(timeBunket).get("endDate");
				
				//获取报价单数量
				RzPricecalQuery pricecalQuery = new RzPricecalQuery();
				pricecalQuery.setStartdate(startDate);
				pricecalQuery.setEnddate(endDate);
				int pricecalCount = rzPricecalService.getRzPricecalByPageCount(pricecalQuery);
				result.addDefaultModel("pricecalCount",pricecalCount);
				
				//获取立项数量
				RzPrjapplyQuery prjapplyQuery = new RzPrjapplyQuery();
				prjapplyQuery.setEnddate(endDate);
				prjapplyQuery.setStartdate(startDate);
				//审批通过
				prjapplyQuery.setApprovestatus(3);
				int prjapplyCount = rzPrjapplyService.getRzPrjapplyByPageCount(prjapplyQuery);
				result.addDefaultModel("prjapplyCount",prjapplyCount);
				//获取合同
				RzPrjcontrQuery rzPrjcontrQuery = new RzPrjcontrQuery();
				rzPrjcontrQuery.setApprovestatus(3);
				rzPrjcontrQuery.setStartdate(startDate);
				rzPrjcontrQuery.setEnddate(endDate);
				List<RzPrjcontr> contrList = rzPrjcontrService.getRzPrjcontrAll(rzPrjcontrQuery);
				Double total=0.0;
				for(RzPrjcontr contr:contrList){
					total+=contr.getContrAmt();
				}
				result.addDefaultModel("contrAmt",total);
				result.addDefaultModel("contrCount",contrList.size());
				
				Double totalAmt = getIsNotCheckRptAmtTotal(startDate, endDate);
				result.addDefaultModel("totalAmt",totalAmt);
			}
			// 新增客户
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}

	private Double getIsNotCheckRptAmtTotal(String startDate, String endDate) {
		RzPmtPlanQuery rzPmtPlanQuery = new RzPmtPlanQuery();
		rzPmtPlanQuery.setStartdate(startDate);
		rzPmtPlanQuery.setEnddate(endDate);
		//获取当前支付计划 这里存在最大版本的问题 获取最大版本
		List<RzPmtPlan> pmtPlanList = rzPmtPlanService.getRzPmtPlanAll(rzPmtPlanQuery);
		Map<String,RzPmtPlan> pmtMap = new HashMap<String,RzPmtPlan>();
		for (RzPmtPlan plan:pmtPlanList){
			pmtMap.put(plan.getPkPmtPlan(), plan);
		}
		
		//全部未核销
		RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery = new RzPmtPlanLeaseQuery();
		rzPmtPlanLeaseQuery.setIsCheck("N");
		List<RzPmtPlanLease> pmtPlanLeaseList = rzPmtPlanLeaseService.getRzPmtPlanLeaseAll(rzPmtPlanLeaseQuery);
		List<RzPmtPlanLease> maxVerList = rzPmtPlanLeaseService.getAllMaxVerGroupByrzPmtPlan();
		Map<String,RzPmtPlanLease> verMap = new HashMap<String,RzPmtPlanLease>();
		for(RzPmtPlanLease verLease:maxVerList){
			verMap.put(verLease.getPkPmtPlan(), verLease);
		}
		Double totalAmt = 0.0;
		for(RzPmtPlanLease lease:pmtPlanLeaseList){
			//如果是需要统计的收付计划表，且版本最大版本
			if(pmtMap.containsKey(lease.getPkPmtPlan()) && lease.getVer().equals(verMap.get(lease.getPkPmtPlan()).getVer())){
				totalAmt+=DoubleUtils.getDoubleNullAsZero(lease.getRptAmt());
			}
		}
		return totalAmt;
	}

	/**
	 * 跳转到更多消息页面
	 * 
	 * @param mktsetdefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "toMoreinfo", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toMoreinfo(HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		String type = req.getParameter("type");
		if (StringUtil.isEmpty(type)) {
			type = "2";
		}
		ModelAndView mv = new ModelAndView("moreinfo");
		mv.addObject("type", Integer.parseInt(type));
		return mv;
	}

	private String getMenuTree() {
		GenerateMenuTree generateMenuTree = new GenerateMenuTree();
		Map<String, Object> bdSystemMap = new HashMap<String, Object>();
		bdSystemMap.put("id", currentSystem.get("systemId"));
		bdSystemMap.put("system_name", currentSystem.get("systemName"));
		bdSystemMap.put("system_url", currentSystem.get("systemUrl"));
		bdSystemMap.put("system_icon_cls", currentSystem.get("systemIcon"));

		List<Map<String, Object>> systemList = new ArrayList<>();

		systemList.add(bdSystemMap);
		List<Moduleinfo> moduleinfos = (List<Moduleinfo>) session
				.getAttribute(LoginEnum.LONGIN_MODULE_INFOS.getloginInfo());
		Map<String, List<Moduleinfo>> sysModulesMap = new HashMap<>();
		sysModulesMap.put(currentSystem.get("systemName"), moduleinfos);
		String menu = generateMenuTree.generateMenuTree(systemList, sysModulesMap);
		return menu;

	}

	/**
	 * 跳转到Mktsetdef首页
	 * 
	 * @param mktsetdefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "toDemo", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toDemo(HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		ModelAndView mv = new ModelAndView("demo/demo");
		return mv;
	}

	/**
	 * 跳转到Mktsetdef首页
	 * 
	 * @param mktsetdefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "todemoGrid", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView todemoGrid(HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		ModelAndView mv = new ModelAndView("demo/demo");
		return mv;
	}

	/**
	 * 分页取消息
	 * 
	 * @param req
	 * @param page
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "getMessageByPage", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getMessageByPage(SmallMessageQuery smallMessageQuery, HttpServletRequest req,
			Integer page, Integer type, HttpServletResponse resp, ModelMap context) {

		Result result = new Result();
		result.setSuccess(false);
		try {
			if (smallMessageQuery == null) {
				smallMessageQuery = new SmallMessageQuery();
			}
			smallMessageQuery.setUserid(getUserId());
			if (smallMessageQuery.getType() == 2) {
				smallMessageQuery.setApproveflag(0);// 已审批
			}
			GridResult<SmallMessage> easyUIGridResult = defaultService.getMessageByPage(smallMessageQuery);
			result.setSuccess(true);
			result.addDefaultModel("datas", easyUIGridResult);
			return result;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
			result.setErrorMessage("查询更多信息出现异常");
		}

		return result;
	}

	/**
	 * 取未读消息个数
	 * 
	 * @param req
	 * @param page
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "getUnReadMessCount", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getUnReadMessCount(SmallMessageQuery smallMessageQuery, HttpServletRequest req,
			Integer page, Integer type, HttpServletResponse resp, ModelMap context) {

		Result result = new Result();
		result.setSuccess(false);
		try {
			if (smallMessageQuery == null) {
				smallMessageQuery = new SmallMessageQuery();
			}
			smallMessageQuery.setUserid(getUserId());
			smallMessageQuery.setReadMark(0);// 0是未读 1是已读
			Integer messcount = defaultService.getUnReadMessCount(smallMessageQuery);
			result.setSuccess(true);
			result.addDefaultModel("datas", messcount);
			return result;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
			result.setErrorMessage("查询更多信息出现异常");
		}

		return result;
	}

	/**
	 * 修改信息为已读状态
	 * 
	 * @param portalUser
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "setMessageReadMark", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object setMessageReadMark(Long messageId, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		String userId = getUserId();
		boolean flat = defaultService.setMessageReadMark(userId, messageId);
		return flat;
	}

	@SuppressWarnings("unchecked")
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "getMessageUrlByModuleValue", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getMessageUrlByModuleValue(SmallMessage message, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		String moduleValue = message.getModuleValue();
		String moduleUrl = null;
		String moduleName = null;
		String systemUrl = currentSystem.get("systemUrl");
		String systemName = currentSystem.get("systemName");
		Map<String, List<Moduleinfo>> sysModulesMap = (Map<String, List<Moduleinfo>>) session
				.getAttribute(LoginEnum.LONGIN_USER_SYS_MODULES_VALUES.getloginInfo());
		List<Moduleinfo> moduleList = sysModulesMap.get(systemName);
		if (moduleList != null && moduleList.size() > 0) {
			for (int j = 0; j < moduleList.size(); j++) {
				String mValue = moduleList.get(j).getModuleValue();
				if (mValue.equals(moduleValue)) {
					moduleName = moduleList.get(j).getModuleName();
					moduleUrl = moduleList.get(j).getUrl();
					break;
				}
			}
		}

		message.setUrl(systemUrl + moduleUrl);
		message.setModuleName(moduleName);
		return message;
	}
	
	/**
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "getUserSkin", method = { RequestMethod.POST,RequestMethod.GET })
	public Result getUserSkin(HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		SmallUserQuery userQuery = new SmallUserQuery();
		userQuery.setId(getUserId());
		userQuery.setTenantId(getTenantId());
		Result result = defaultService.getUserSkin(userQuery);
		return result;
	}

	public void setCurrentSystem(HashMap<String, String> currentSystem) {
		this.currentSystem = currentSystem;
	}

	public void setDefaultService(DefaultService defaultService) {
		this.defaultService = defaultService;
	}

}