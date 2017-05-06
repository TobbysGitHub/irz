package com.imfbp.rz.controller.rzadjint;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjint.query.RzAdjIntQuery;
import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.domain.rzrate.RzRate;
import com.imfbp.rz.domain.rzrate.query.RzRateQuery;
import com.imfbp.rz.domain.rzrateb.RzRateB;
import com.imfbp.rz.domain.rzrateb.query.RzRateBQuery;
import com.imfbp.rz.domain.rzrateprd.RzRateprd;
import com.imfbp.rz.domain.rzrateprd.query.RzRateprdQuery;
import com.imfbp.rz.service.rzadjint.RzAdjIntService;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.service.rzrate.RzRateService;
import com.imfbp.rz.service.rzrateb.RzRateBService;
import com.imfbp.rz.service.rzrateprd.RzRateprdService;
import com.imfbp.rz.util.ExcelWriterTool;
import com.imfbp.rz.util.ToolUtils;
import com.imfbp.rz.util.WebUtils;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.utils.StringUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

@RestController
public class RzAdjIntController extends PubBaseContrl {

	private RzAdjIntService rzAdjIntService;
	@Autowired
	private RzRateService rzRateService;
	@Autowired
	private RzPmtPlanService rzPmtPlanService;
	@Autowired
	private RzPmtPlanLeaseService rzPmtPlanLeaseService;
	@Autowired
	private RzPrjcontrService rzPrjcontrService;
	@Autowired
	private RzRateprdService rzRateprdService;
	@Autowired
	private RzRateBService rzRateBService;

	/**
	 * 跳转到RzAdjInt首页
	 * 
	 * @param rzAdjIntQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjInt/toRzAdjIntPage", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toRzAdjIntPage(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzAdjInt/rzAdjInt");
		attachLoginInfo(mv);
		attachModuleValue(mv, req);
		return mv;
	}

	/**
	 * 查询所有
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjInt/getRzAdjIntAll", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntAll(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		if (rzAdjIntQuery == null) {
			rzAdjIntQuery = new RzAdjIntQuery();
		}
		List<RzAdjInt> rzAdjIntList = rzAdjIntService.getRzAdjIntAll(rzAdjIntQuery);
		return rzAdjIntList;
	}

	/**
	 * 得到人行调息日期
	 * 
	 * @param
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjInt/getPbcAdjIntDate", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getPbcAdjIntDate(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjInt/getAdjBaserate", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAdjBaserate(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.setSuccess(false);
		// 当前租赁利率
		Double leaseRate = null;
		RzRateQuery rzRatequery = new RzRateQuery();
		rzRatequery.setOrder("desc");
		rzRatequery.setSort("startdate");
		List<RzRate> list = rzRateService.getRzRateAll(rzRatequery);
		// 人行调息日期
		result.addDefaultModel("PbcAdjIntDate", list.get(0));
		// 得到合同
		RzPrjcontrQuery contrQuery = new RzPrjcontrQuery();
		contrQuery.setPkPrjcontr(rzAdjIntQuery.getPkPrjcontr());
		RzPrjcontr contr = rzPrjcontrService.getRzPrjcontrById(contrQuery);

		RzRate rsultRate = null;
		for (RzRate rate : list) {
			rsultRate = rate;
			// 根据调息日期取离调息日最近的“利率生效日期>=调息日期”的对应期限档次的基准利率
			if (rate.getStartdate().compareTo(rzAdjIntQuery.getAdjIntDate()) < 0) {
				break;
			}
		}
		// 查找最近的“最近收租日<利率生效日期<调息日期”的对应期限档次的基准利率；
		if (null == rsultRate) {
			RzPmtPlanLeaseQuery query = new RzPmtPlanLeaseQuery();
			RzPmtPlanQuery rzPmtPlan = new RzPmtPlanQuery();
			rzPmtPlan.setPkPrjcontr(rzAdjIntQuery.getPkPrjcontr());
			List<RzPmtPlan> rzPmtPlanList = rzPmtPlanService.getRzPmtPlanAll(rzPmtPlan);
			if (ToolUtils.isNotEmptyCollection(rzPmtPlanList)) {
				// 某个合同的调息计划表
				RzPmtPlanLeaseQuery planQuery = new RzPmtPlanLeaseQuery();
				planQuery.setIsCheck("Y");
				planQuery.setOrder("desc");
				planQuery.setSort("rptDate");
				query.setPkPmtPlan(rzPmtPlanList.get(0).getPkPmtPlan());
				List<RzPmtPlanLease> rzPmtPlanLeaselist = rzPmtPlanLeaseService
						.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(query);
				if (ToolUtils.isNotEmptyCollection(rzPmtPlanLeaselist)) {
					for (RzRate rate : list) {
						if (rate.getStartdate().compareTo(rzAdjIntQuery.getAdjIntDate()) < 0
								&& rate.getStartdate().compareTo(rzPmtPlanLeaselist.get(0).getRptDate()) > 0) {
							rsultRate = rate;
							break;
						}
					}
				}
			}
		}
		if (null != rsultRate) {
			result.setSuccess(true);
			if (StringUtil.isEmpty(contr.getLeaseprd())){
				result.setSuccess(false);
				result.setErrorMessage("合同赁期限为空");
				return result;
			}
			int prd = contr.getLeaseprd();
			int monthDays = prd * 30;
			RzRateprdQuery rateDate = new RzRateprdQuery();
			rateDate.setStartTerm(monthDays);
			rateDate.setEndTerm(monthDays);
			List<RzRateprd> listRate = rzRateprdService.getRzRateprdByDays(rateDate);
			if (ToolUtils.isNotEmptyCollection(listRate)) {
				RzRateBQuery rzRateBQuery = new RzRateBQuery();
				rzRateBQuery.setPkRateprd(listRate.get(0).getPkRateprd());
				rzRateBQuery.setPkRate(rsultRate.getPkRate());
				List<RzRateB> rateList = rzRateBService.getRzRateBAll(rzRateBQuery);
				if (ToolUtils.isNotEmptyCollection(rateList)) {
					result.setSuccess(true);
					result.addDefaultModel("RzRateB", rateList.get(0));
				}
			}
		}
		//
		RzPmtPlanQuery rzPmtPlan = new RzPmtPlanQuery();
		rzPmtPlan.setPkPrjcontr(rzAdjIntQuery.getPkPrjcontr());
		// rzAdjInt.setAdjLeaseRate(3.0);
		List<RzPmtPlan> rzPmtPlanList = rzPmtPlanService.getRzPmtPlanAll(rzPmtPlan);
		List<RzPmtPlanLease> rzPmtPlanLeaseList = new ArrayList<RzPmtPlanLease>();
		
		if (ToolUtils.isNotEmptyCollection(rzPmtPlanList)) {
			RzPmtPlanLeaseQuery rzPmtPlanLeaseQuery = new RzPmtPlanLeaseQuery();
			rzPmtPlanLeaseQuery.setPkPmtPlan(rzPmtPlanList.get(0).getPkPmtPlan());
			rzPmtPlanLeaseQuery.setIsCheck("N");
			rzPmtPlanLeaseQuery.setSort("seq_no");
			rzPmtPlanLeaseQuery.setOrder("asc");
			rzPmtPlanLeaseList = rzPmtPlanLeaseService.getMaxVerRzPmtPlanLeaseAllByRzPmtPlan(rzPmtPlanLeaseQuery);
			if(ToolUtils.isEmptyCollection(rzPmtPlanLeaseList)){
				result.setSuccess(false);
				result.setErrorMessage("首付计划没有未核销数据，不需要调息");
				return result;
			}
			result.addDefaultModel("leaseprate", rzPmtPlanLeaseList.get(rzPmtPlanLeaseList.size()-1).getLeaseRate());
		}
		if (null != contr.getIntType()) {
			if (1 == contr.getIntType()) {
				result.addDefaultModel("startAdjIntDate", list.get(0).getStartdate());
			} else if (2 == contr.getIntType()) {
				if (ToolUtils.isNotEmptyCollection(rzPmtPlanLeaseList)) {
					// 下一个收租日
					result.addDefaultModel("startAdjIntDate", rzPmtPlanLeaseList.get(0).getRptDate());
				}
			}

		}else{
			result.setErrorMessage("合同调息类型为空");
			result.setSuccess(false);
			return result;
		}
		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjInt/getRzAdjIntByPage", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntByPage(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		if (rzAdjIntQuery == null) {
			rzAdjIntQuery = new RzAdjIntQuery();
		}
		GridResult<RzAdjInt> gridResult = rzAdjIntService.getRzAdjIntByPage(rzAdjIntQuery);
		return gridResult;
	}

	/**
	 * 根据id查询
	 * 
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjInt/getRzAdjIntById", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzAdjIntById(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		RzAdjInt rzAdjInt = rzAdjIntService.getRzAdjIntById(rzAdjIntQuery);
		return rzAdjInt;
	}

	/**
	 * 添加或修改
	 * 
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzAdjInt/insertOrUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzAdjInt rzAdjInt, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		Result result = rzAdjIntService.insertOrUpdate(rzAdjInt);
		return result;
	}

	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzAdjInt/exportRzAdjIntAll", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportRzAdjIntAll(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		OutputStream out;
		if (rzAdjIntQuery == null) {
			rzAdjIntQuery = new RzAdjIntQuery();
		}
		try {
			out = resp.getOutputStream();
			List<RzAdjInt> rzAdjIntList = rzAdjIntService.getRzAdjIntAll(rzAdjIntQuery);
			ExcelWriterTool<RzAdjInt> ex = new ExcelWriterTool<RzAdjInt>();
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("调息单号", "adjIntNo");
			map.put("调息日期", "adjIntDate");
			map.put("合同编号", "contrCode");
			map.put("合同名称", "contrName");
			map.put("客户名称", "pkCustomer");
			map.put("项目经理", "pkUserManager");
			map.put("利率类型", "ratetype");
			map.put("租赁期限(月)", "leaseprd");
			map.put("当前租赁利率", "leaseprate");
			map.put("基准利率(%)", "baserate");
			map.put("上下浮比例(%)", "floatpct");
			map.put("调息类型", "intType");
			map.put("调息后租赁利率(%)", "adjPricerate");
			map.put("调息后基准利率(%)", "adjBaserate");
			map.put("人行调息日期", "pbcAdjIntDate");
			map.put("调息生效日期", "startAdjIntDate");
			map.put("调息幅度(%)", "adjIntPer");
			map.put("业务类型", "busitype");
			map.put("项目金额(元)", "itemamt");
			map.put("首付款金额", "firstpmtamt");
			map.put("融资金额", "financeamt");
			map.put("合同金额(元)", "contrAmt");
			map.put("报价利率", "pricerate");
			map.put("项目余额", "itembal");
			map.put("名义货价", "nomPrice");
			map.put("当前合同IRP(%)", "irr");
			map.put("调息后合同IRP(%)", "adjIrr");
			map.put("当前总利息（元）", "intTotalAmt");
			map.put("调息后总利息（元）", "adjIntTotalAmt");
			resp.addHeader("Content-Disposition", "attachment;filename=" + WebUtils.encodeFileName("立项项目表格.xls", req));
			resp.setContentType("application/force-download");// 设置为下载application/force-download
			ex.exportExcel("立项项目表格", map, rzAdjIntList, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Id批量删除 (真正删除数据库数据)
	 * 
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzAdjInt/deleteRzAdjIntByBatchId", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzAdjIntByBatchId(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = rzAdjIntService.deleteRzAdjIntByBatchId(rzAdjIntQuery);
		return result;
	}

	/**
	 * 根据Id批量逻辑删除(修改数据库数据为删除状态)
	 * 
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzAdjInt/logicDeleteRzAdjIntByBatchId", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzAdjIntByBatchId(RzAdjIntQuery rzAdjIntQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		Result result = rzAdjIntService.logicDeleteRzAdjIntByBatchId(rzAdjIntQuery);
		return result;
	}

	public void setRzAdjIntService(RzAdjIntService rzAdjIntService) {
		this.rzAdjIntService = rzAdjIntService;
	}

}