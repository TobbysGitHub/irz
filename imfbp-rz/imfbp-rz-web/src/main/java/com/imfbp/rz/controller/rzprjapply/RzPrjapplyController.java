package com.imfbp.rz.controller.rzprjapply;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjapplyeqpt.RzPrjapplyEqpt;
import com.imfbp.rz.domain.rzprjapplyeqpt.query.RzPrjapplyEqptQuery;
import com.imfbp.rz.domain.template.BaseSystemInfo;
import com.imfbp.rz.pub.INodeConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.commons.OrgUtil;
import com.imfbp.rz.service.formula.FormulaParseCal;
import com.imfbp.rz.service.rzeqpt.RzEqptService;
import com.imfbp.rz.service.rzprjapply.RzPrjapplyService;
import com.imfbp.rz.service.rzprjapplyeqpt.RzPrjapplyEqptService;
import com.imfbp.rz.util.ExcelWriterTool;
import com.imfbp.rz.util.ToolUtils;
import com.imfbp.rz.util.WebUtils;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


@RestController
public class RzPrjapplyController extends PubBaseContrl{

	private RzPrjapplyService rzPrjapplyService;
	@Autowired
	private OrgUtil orgUtil;
	@Autowired
	private BillnoService billnoService;
	
	@Autowired
	private FormulaParseCal formulaParseCal;
	@Autowired
	private RzPrjapplyEqptService rzPrjapplyEqptService;
	@Autowired
	private RzEqptService rzEqptService;
	/**
	 *  跳转到RzPrjapply首页
	 * @param rzPrjapplyQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjapply/toRzPrjapplyPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzPrjapplyPage(RzPrjapplyQuery rzPrjapplyQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzPrjapply/rzPrjapply");
		attachLoginInfo(mv);
		mv.addObject("pkOrgLessor",orgUtil.getTopSmallOrgById(getTenantId(), getOrgId()) );
		attachModuleValue(mv,req);
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
	@RequestMapping(value = "rzPrjapply/getRzPrjapplyAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjapplyAll(RzPrjapplyQuery rzPrjapplyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjapplyQuery == null) {
			rzPrjapplyQuery = new RzPrjapplyQuery();
		}
		List<RzPrjapply> rzPrjapplyList = rzPrjapplyService.getRzPrjapplyAll(rzPrjapplyQuery);
		return rzPrjapplyList;
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
	@RequestMapping(value = "rzPrjapply/getRzPrjapplyByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzPrjapplyByPage(RzPrjapplyQuery rzPrjapplyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzPrjapplyQuery == null) {
			rzPrjapplyQuery = new RzPrjapplyQuery();
		}
		GridResult<RzPrjapply> gridResult = rzPrjapplyService.getRzPrjapplyByPage(rzPrjapplyQuery);
		return gridResult;
	}
	
	
	/**
	* @Title: createPrjapplyPdf 
	* @Description: 生成立项申请
	* @param @param rzPrjapplyQuery
	* @param @param req
	* @param @param resp
	* @param @param context
	* @param @return    
	* @return Object   
	* @user qinhuimin
	* @date 2016年12月29日上午11:07:37
	* @throws
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjapply/createPrjapplyPdf", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object createPrjapplyPdf(RzPrjapply rzPrjapply, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		BaseSystemInfo baseSystemInfo = new BaseSystemInfo();
		baseSystemInfo.setTenantId(getTenantId());
		baseSystemInfo.setSystemCode(getSysCode());
		baseSystemInfo.setUserId(this.getUserId());
		baseSystemInfo.setFileType("1");
		
		formulaParseCal.formulaParse(rzPrjapply, "rzPrjapply", baseSystemInfo);
		return null;
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
	@RequestMapping(value = "rzPrjapply/getRzPrjapplyById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzPrjapplyById(RzPrjapplyQuery rzPrjapplyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzPrjapply rzPrjapply = rzPrjapplyService.getRzPrjapplyById(rzPrjapplyQuery);
		return rzPrjapply;
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
	@RequestMapping(value = "rzPrjapply/getPrjapplyBillNo", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getPrjapplyBillNo(RzPrjapplyQuery rzPrjapplyQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.setSuccess(true);
		String billno = "";
		try {
			billno = billnoService.getBillno(INodeConsts.RZ_PRJAPPLY_NO);
			result.addDefaultModel("billno", billno);
		} catch (Exception e) {
			result.setSuccess(true);
			result.setErrorMessage("项目编号生成失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjapply/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzPrjapply rzPrjapply,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjapplyService.insertOrUpdate(rzPrjapply,getTenantId());
		return result;
	}
	
	
	@RequestMapping(value = "rzPrjapply/getAllRzEqptBycal", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllRzEqptBycal(RzPrjapplyQuery rzPrjapplyQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(rzPrjapplyQuery == null) rzPrjapplyQuery=new RzPrjapplyQuery();
		RzPrjapplyEqptQuery rzPrjapplyEqptQuery=new RzPrjapplyEqptQuery();
		rzPrjapplyEqptQuery.setPkPrjapply(rzPrjapplyQuery.getPkPrjapply());
		List<RzPrjapplyEqpt> rzPrjapplyEqptList= rzPrjapplyEqptService.getRzPrjapplyEqptAll(rzPrjapplyEqptQuery);
		List<RzEqpt> eqptList= rzEqptService.getRzEqptAll(new RzEqptQuery());
		Map<String,RzEqpt>map=new HashMap<String,RzEqpt>();
		if(ToolUtils.isNotEmptyCollection(eqptList)){
			for(RzEqpt eq:eqptList) map.put(eq.getPkEqpt(), eq);
		}
		Map<String,List<Object>>result=new HashMap<String,List<Object>>();
		if(ToolUtils.isNotEmptyCollection(rzPrjapplyEqptList)){
			List<Object>result0=new ArrayList<Object>();
			List<Object>result1=new ArrayList<Object>();
			List<Object>result2=new ArrayList<Object>();
			//List<Object>result3=new ArrayList<Object>();
			for(RzPrjapplyEqpt eqt:rzPrjapplyEqptList){
				if(map.containsKey(eqt.getPkEqpt()) && map.get(eqt.getPkEqpt())!= null){ 
					result0.add(map.get(eqt.getPkEqpt()).getPkEqpt());
					result1.add(map.get(eqt.getPkEqpt()).getEqptName());
					result2.add(eqt.getEqptNum());
					//result3.add(eqt.getEqptPrice());
				}
			}
			result.put("pk",result0);
			result.put("name", result1);
			result.put("num",result2);
			//result.put("price", result3);
		}
		return result;
	}
	
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzPrjapply/exportRzPrjapplyAll", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportRzPrjapplyAll(RzPrjapplyQuery rzPrjapplyQuery, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		OutputStream out;
		if (rzPrjapplyQuery == null) {
			rzPrjapplyQuery = new RzPrjapplyQuery();
		}
		try {
			out = resp.getOutputStream();
			List<RzPrjapply> rzPrjapplyList = rzPrjapplyService.getRzPrjapplyAll(rzPrjapplyQuery);
			ExcelWriterTool<RzPrjapply> ex = new ExcelWriterTool<RzPrjapply>();
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("项目编码", "prjCode");
			map.put("项目名称", "prjName");
			map.put("报价单名称", "pkPricecal");
			map.put("客户名称", "pkCustomer");
			map.put("租赁物类型", "leasetype");
			map.put("项目来源", "prjSrc");
			map.put("项目经理", "pkUserManager");
			map.put("申请部门", "pkDeptApply");
			map.put("租赁期限", "leaseprd");
			map.put("项目金额", "itemamt");
			map.put("设备总额", "eqptPriceTotal");
			map.put("融资金额", "financeamt");
			map.put("单据状态", "approvestatus");
			map.put("制单人", "billmaker");
			map.put("制单日期", "billdate");
			map.put("审核人", "approveid");
			map.put("审核日期", "approvedate");
			resp.addHeader("Content-Disposition", "attachment;filename=" + WebUtils.encodeFileName("立项项目表格.xls", req));
			resp.setContentType("application/force-download");// 设置为下载application/force-download
			ex.exportExcel("立项项目表格", map, rzPrjapplyList, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 *  根据Id批量删除 (真正删除数据库数据)
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzPrjapply/deleteRzPrjapplyByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzPrjapplyByBatchId(RzPrjapplyQuery rzPrjapplyQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjapplyService.deleteRzPrjapplyByBatchId(rzPrjapplyQuery);
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
	@RequestMapping(value = "rzPrjapply/logicDeleteRzPrjapplyByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzPrjapplyByBatchId(RzPrjapplyQuery rzPrjapplyQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzPrjapplyService.logicDeleteRzPrjapplyByBatchId(rzPrjapplyQuery);
		return result;
	}
	
	public void setRzPrjapplyService(RzPrjapplyService rzPrjapplyService) {
		this.rzPrjapplyService = rzPrjapplyService;
	}

}