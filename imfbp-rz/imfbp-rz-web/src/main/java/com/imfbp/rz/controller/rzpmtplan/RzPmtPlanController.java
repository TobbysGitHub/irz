package com.imfbp.rz.controller.rzpmtplan;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.ifbp.boss.rpc.dic.domain.RpcDicItem;
import com.ifbp.boss.rpc.dic.service.DicItemRpcService;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplan.RzpmtplanVo;
import com.imfbp.rz.domain.rzpmtplan.query.RzPmtPlanQuery;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzpmtplanlease.query.RzPmtPlanLeaseQuery;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.service.rzpmtplan.RzPmtPlanService;
import com.imfbp.rz.service.rzpmtplancf.RzPmtPlanCfService;
import com.imfbp.rz.service.rzpmtplanlease.RzPmtPlanLeaseService;
import com.imfbp.rz.util.ExcelWriterTool;
import com.imfbp.rz.util.WebUtils;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzPmtPlanController extends PubBaseContrl{

	private RzPmtPlanService rzPmtPlanService;
	@Autowired
	private DicItemRpcService dicItemRpcClient;
	@Autowired
	private RzPmtPlanCfService rzPmtPlanCfService;
	@Autowired
	private RzPmtPlanLeaseService rzPmtPlanLeaseService;
	/**
	 *  跳转到RzPmtPlan首页
	 * @param rzPmtPlanQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPmtPlan/toRzPmtPlanPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPmtPlanPage(RzPmtPlanQuery rzPmtPlanQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPmtPlan/rzPmtPlan");
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
	@RequestMapping(value = "rzPmtPlan/getRzPmtPlanAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanAll(RzPmtPlanQuery rzPmtPlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPmtPlanQuery == null) {
			rzPmtPlanQuery = new RzPmtPlanQuery();
		}
		List<RzPmtPlan> rzPmtPlanList = rzPmtPlanService.getRzPmtPlanAll(rzPmtPlanQuery);
		return rzPmtPlanList;
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
	@RequestMapping(value = "rzPmtPlan/getRzPmtPlanByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanByPage(RzpmtplanVo rzPmtPlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPmtPlanQuery == null) {
			rzPmtPlanQuery = new RzpmtplanVo();
		}
		if(StringUtils.isNotEmpty(rzPmtPlanQuery.getBusitype()) &&rzPmtPlanQuery.getBusitype().equals("undefined"))rzPmtPlanQuery.setBusitype("");
		GridResult<RzPmtPlan> gridResult = rzPmtPlanService.getRzPmtPlanByPage(rzPmtPlanQuery);
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
	@RequestMapping(value = "rzPmtPlan/getRzPmtPlanById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPmtPlanById(RzPmtPlanQuery rzPmtPlanQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPmtPlan rzPmtPlan = rzPmtPlanService.getRzPmtPlanById(rzPmtPlanQuery);
		return rzPmtPlan;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPmtPlan/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPmtPlan rzPmtPlan,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPmtPlanService.insertOrUpdate(rzPmtPlan);
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
	@RequestMapping(value = "rzPmtPlan/deleteRzPmtPlanByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPmtPlanByBatchId(RzPmtPlanQuery rzPmtPlanQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPmtPlanService.deleteRzPmtPlanByBatchId(rzPmtPlanQuery);
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
	@RequestMapping(value = "rzPmtPlan/logicDeleteRzPmtPlanByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPmtPlanByBatchId(RzPmtPlanQuery rzPmtPlanQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPmtPlanService.logicDeleteRzPmtPlanByBatchId(rzPmtPlanQuery);
		return result;
	}
	//获取合同中最大的版本号
	@RequestMapping(value = "rzPmtPlan/getMaxVersion", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getMaxVersion(RzpmtplanVo rzpmtplanVo,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		String maxVersion=rzPmtPlanService.getMaxVersion(rzpmtplanVo);
		result.addDefaultModel("maxVersion", maxVersion);
		return result;
	}
	//获取所有的租赁业务类型
	@RequestMapping(value = "rzPmtPlan/getAllBusyType", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllBusyType(RzpmtplanVo rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		 Result result=new Result();
		 try {
			List<RpcDicItem> rpcList=dicItemRpcClient.getDicItem("DIC_RZ_BUSI_TYPE");
			result.addDefaultModel("busyTypeList", rpcList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;	
			}
	//租金计划表导出
	@RequestMapping(value = "rzPmtPlan/leaseExport", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object leaseExport(RzpmtplanVo rzpmtplanVo,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
				if (rzpmtplanVo == null) {
					rzpmtplanVo = new RzpmtplanVo();
				}
				Result result=new Result();
				result.setSuccess(true);
				OutputStream out;
				try {
					out = resp.getOutputStream();
					List<RzPmtPlanLease>rzPmtPlanLeaseList;
					rzPmtPlanLeaseList= rzPmtPlanLeaseService.getRzPmtPlanLeaseAll(new RzPmtPlanLeaseQuery());
					ExcelWriterTool<RzPmtPlanLease> ex = new ExcelWriterTool<RzPmtPlanLease>();
					Map<String,String> map = new LinkedHashMap<String,String>();
					map.put("期数", "num");
					map.put("应还款日期","rptDate" );
					map.put("偿还期间", "paydur");
					map.put("期间天数", "durdays");
					map.put("租赁利率(%)", "leaseRate");
					map.put("应还款本金", "rptAmt");
					map.put("应还款利息", "rptInt");
					map.put("应还款租金", "rptRent");
					map.put("应支付手续费", "payFee");
					map.put("应还款合计", "rptTotal");
					map.put("剩余本金", "residualAmt");
					map.put("是否核查", "isCheck");
					resp.addHeader("Content-Disposition","attachment;filename='"+WebUtils.encodeFileName("租金计划表.xls", req));
					resp.setContentType("application/force-download");
					ex.exportExcel("租金计划表", map, rzPmtPlanLeaseList, out);
					out.close();
					result.setSuccess(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.setSuccess(false);
				}
				return result;
			}
	//现金流明细表导出
	@RequestMapping(value = "rzPmtPlan/cfExport", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object cfExport(RzPricecalQuery rzPricecalQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
				if (rzPricecalQuery == null) {
					rzPricecalQuery = new RzPricecalQuery();
				}
				OutputStream out;
				Result result=new Result();
				result.setSuccess(true);
				try {
					out = resp.getOutputStream();
					List<RzPmtPlanCf>rzPmtPlanCfList;
					rzPmtPlanCfList= rzPmtPlanCfService.getRzPmtPlanCfAll(new RzPmtPlanCfQuery());
					ExcelWriterTool<RzPmtPlanCf> ex = new ExcelWriterTool<RzPmtPlanCf>();
					Map<String,String> map = new LinkedHashMap<String,String>();
					map.put("收付计划期次", "prd");
					map.put("计划收支日期","planpmtdate" );
					map.put("计划收付类型","planpmttype" );
					map.put("计划收付类别", "planpmtcategory");
					map.put("收付期间", "pmtdur");
					map.put("期间天数", "durdays");
					map.put("租赁利率(%)", "leaseRate");
					map.put("应收本金", "receivableAmt");
					map.put("应收利息", "receivableInt");
					map.put("应收租金", "receivableRent");
					map.put("应收手续费", "receivableFee");
					map.put("应收款合计", "receivableTotal");
					
					map.put("应付融资金额", "payableFa");
					map.put("应付服务费", "payableSc");
					map.put("应付款项金额", "payableAmt");
					map.put("是否核销", "isCheck");
					resp.addHeader("Content-Disposition","attachment;filename='"+WebUtils.encodeFileName("现金流明细表.xls", req));
					resp.setContentType("application/force-download");
					ex.exportExcel("现金流明细表", map, rzPmtPlanCfList, out);
					out.close();
					result.setSuccess(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.setSuccess(false);
				}
				return result;
			}
	public void setRzPmtPlanService(RzPmtPlanService rzPmtPlanService) {
		this.rzPmtPlanService = rzPmtPlanService;
	}

}