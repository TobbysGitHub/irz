package com.imfbp.rz.controller.rzcontrtally;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
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
import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzcontrtally.RzContrTallyProQueryVo;
import com.imfbp.rz.domain.rzcontrtally.query.RzContrTallyQuery;
import com.imfbp.rz.domain.rzpmtplancf.query.RzPmtPlanCfQuery;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.util.ExcelWriterTool;
import com.imfbp.rz.util.ToolUtils;
import com.imfbp.rz.util.WebUtils;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class RzContrTallyController extends PubBaseContrl{

	private RzContrTallyService rzContrTallyService;
	
	/**
	 *  跳转到RzContrTally首页
	 * @param rzContrTallyQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTally/toRzContrTallyPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzContrTallyPage(RzContrTallyQuery rzContrTallyQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzContrTally/rzContrTally");
		attachLoginInfo(mv);
		return mv;
	}
	/**
	 *  跳转到RzContrTally首页
	 * @param rzContrTallyQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTally/toRzContrTallyBalancePage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzContrTallyBalancePage(RzContrTallyQuery rzContrTallyQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzContrTallyBalance/rzContrTallyBalance");
		attachLoginInfo(mv);
		return mv;
	}
	/**
	 *  查询最新
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTally/getRzContrTallyBalance", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyBalance(RzContrTallyQuery rzContrTallyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzContrTallyQuery == null) {
			rzContrTallyQuery = new RzContrTallyQuery();
		}
		List<RzContrTally> rzContrTallyList = rzContrTallyService.getRzContrTallyBalance(rzContrTallyQuery);
		return rzContrTallyList;
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
	@RequestMapping(value = "rzContrTally/getRzContrTallyAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyAll(RzContrTallyQuery rzContrTallyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzContrTallyQuery == null) {
			rzContrTallyQuery = new RzContrTallyQuery();
		}
		List<RzContrTally> rzContrTallyList = rzContrTallyService.getRzContrTallyAll(rzContrTallyQuery);
		return rzContrTallyList;
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
	@RequestMapping(value = "rzContrTally/getRzContrTallyByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyByPage(RzContrTallyQuery rzContrTallyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzContrTallyQuery == null) {
			rzContrTallyQuery = new RzContrTallyQuery();
		}
		GridResult<RzContrTally> gridResult = rzContrTallyService.getRzContrTallyByPage(rzContrTallyQuery);
		return gridResult;
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
	@RequestMapping(value = "rzContrTally/getRzContrTallyBalanceByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyBalanceByPage(RzContrTallyQuery rzContrTallyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzContrTallyQuery == null) {
			rzContrTallyQuery = new RzContrTallyQuery();
		}
		GridResult<RzContrTally> gridResult = rzContrTallyService.getRzContrTallyByPage(rzContrTallyQuery);
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
	@RequestMapping(value = "rzContrTally/getRzContrTallyById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyById(RzContrTallyQuery rzContrTallyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzContrTally rzContrTally = rzContrTallyService.getRzContrTallyById(rzContrTallyQuery);
		return rzContrTally;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzContrTally/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzContrTally rzContrTally,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzContrTallyService.insertOrUpdate(rzContrTally);
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
	@RequestMapping(value = "rzContrTally/deleteRzContrTallyByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzContrTallyByBatchId(RzContrTallyQuery rzContrTallyQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzContrTallyService.deleteRzContrTallyByBatchId(rzContrTallyQuery);
		return result;
	}
	
	
	public void setRzContrTallyService(RzContrTallyService rzContrTallyService) {
		this.rzContrTallyService = rzContrTallyService;
	}
	/**
	 * 传入code
	 * @param rzContrTallyQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTally/getRzContrTallyLatest", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyLatest(RzContrTallyQuery rzContrTallyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzContrTallyQuery == null) {
			rzContrTallyQuery = new RzContrTallyQuery();
		}
		RzContrTally rzContrTally = rzContrTallyService.getRzContrTallyLatest(rzContrTallyQuery);
		return rzContrTally;
	}
	
	/** 项目进度查询**/
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTallyProQuery/toRzContrTallyProQueryPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzContrTallyProQueryPage(RzContrTallyProQueryVo rzContrTallyProQueryVo,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzContrTallyProQuery/rzContrTallyProQuery");
		attachLoginInfo(mv);
		return mv;
	}


	/**
	 * 合同台账明细表
	 * @param rzContrTallyProQueryVo
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTallyDetail/toRzContrTallyDetailPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzContrTallyDetailQueryPage(RzContrTallyProQueryVo rzContrTallyProQueryVo,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzContrTallyDetail/rzContrTallyDetail");
		attachLoginInfo(mv);
		return mv;
	}
	
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTallyProQuery/getRzContrTallyProQueryByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyProQueryByPage(RzContrTallyProQueryVo rzContrTallyProQueryVo, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzContrTallyProQueryVo == null) {
			rzContrTallyProQueryVo = new RzContrTallyProQueryVo();
		}
		//GridResult<RzContrTally> gridResult = rzContrTallyService.getRzContrTallyByPage(rzContrTallyQuery);
		return null;
	}
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTallyProQuery/getRzContrTallyProQueryAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyProQueryALl(RzContrTallyProQueryVo rzContrTallyProQueryVo, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzContrTallyProQueryVo == null) {
			rzContrTallyProQueryVo = new RzContrTallyProQueryVo();
		}
		rzContrTallyProQueryVo.setTendId(getTenantId());
		List<RzContrTallyProQueryVo> rzContrTallyProQueryVoList = rzContrTallyService.getRzContrTallyProQueryAll(rzContrTallyProQueryVo);
		return rzContrTallyProQueryVoList;
	}
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTallyProQuery/getRzContrTallyProQueryByCondition", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzContrTallyProQueryByCondition(RzContrTallyProQueryVo rzContrTallyProQueryVo, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
//		net.sf.json.JSONObject obj= net.sf.json.JSONObject.fromObject(data.trim());
//		if(obj == null) return null;
//		JSONObject json=(JSONObject)obj;
//		@SuppressWarnings("static-access")
//		RzContrTallyProQueryVo rzContrTallyProQueryVo=(RzContrTallyProQueryVo) json.toBean(json,RzContrTallyProQueryVo.class);
		if(rzContrTallyProQueryVo == null) rzContrTallyProQueryVo=new RzContrTallyProQueryVo();
		List<RzContrTallyProQueryVo> rzContrTallyProQueryVoList = rzContrTallyService.getRzContrTallyProQueryByCondition(rzContrTallyProQueryVo);
		return rzContrTallyProQueryVoList;
	}
	/**导出**/
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzContrTallyProQuery/export", method = {RequestMethod.POST, RequestMethod.GET })
	public void export( RzContrTallyProQueryVo rzContrTallyProQueryVo,HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		List<RzContrTallyProQueryVo> rzContrTallyProQueryVoList=null;
		if(StringUtils.isEmpty(rzContrTallyProQueryVo.getQueryCondition())){
			RzContrTallyProQueryVo	query=new RzContrTallyProQueryVo();
			query.setTendId(getTenantId());
			rzContrTallyProQueryVoList= rzContrTallyService.getRzContrTallyProQueryAll(query);
		}else{
			net.sf.json.JSONObject obj= net.sf.json.JSONObject.fromObject(rzContrTallyProQueryVo.getQueryCondition().trim());
			JSONObject json=(JSONObject)obj;
			json.remove("busitypeList");
			@SuppressWarnings("static-access")
			RzContrTallyProQueryVo query1=(RzContrTallyProQueryVo) json.toBean(json,RzContrTallyProQueryVo.class);
			rzContrTallyProQueryVoList= rzContrTallyService.getRzContrTallyProQueryByCondition(query1);
		}
		
		OutputStream out;
		try {
			out = resp.getOutputStream();
			ExcelWriterTool<RzContrTallyProQueryVo> ex = new ExcelWriterTool<RzContrTallyProQueryVo>();
			Map<String,String> map = new LinkedHashMap<String,String>();
			map.put("项目编号","prjCode" );
			map.put("项目名称", "prjName");
			map.put("合同编号", "contrCode");
			map.put("立项日期", "applydate");
			map.put("客户名称", "customerName");
			map.put("客户类型", "custtype");
			map.put("业务类型","busitypeName" );
			map.put("租赁方式", "leasewayName");
			map.put("合同起租日期", "startLeaseDate");
			map.put("合同结束日期", "endLeaseDate");
			map.put("租赁期限(月)", "leaseprd");
			map.put("项目金额(元)", "itemamt");
			map.put("首付款(元)", "firstpmtamt");
			map.put("融资金额(元)", "financeamt");
			map.put("租赁利率(%)", "pricerate");
			map.put("项目状态", "prjStateName");
			map.put("合同状态", "contrStatusName");
			
			map.put("当前处理人", "operatorName");
			map.put("经营性每期租金", "optamt");
			map.put("客户保证金金额(元)", "depositamt");
			map.put("手续费收入总额(元)", "feeamt");
			
			map.put("服务费支出总额(元)", "scamt");
			map.put("总租金(元)", "rentTotalAmt");
			map.put("总利息(元)", "intTotalAmt");
			map.put("已投放资金(元)", "putAmt");
			
			map.put("本金余额(元)", "curPrinBal");
			map.put("租金余额(元)", "curRentBal");
			map.put("还款周期(月)", "reptcycle");
			map.put("合同计划IRR(%)", "irr");
			
			map.put("合同实际IRR(%)", "curIrr");
			map.put("项目经理", "manageName");
			resp.addHeader("Content-Disposition",
					"attachment;filename='"+WebUtils.encodeFileName("项目进度查询.xls", req));
			resp.setContentType("application/force-download");
			ex.exportExcel("项目进度查询", map, rzContrTallyProQueryVoList, out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}