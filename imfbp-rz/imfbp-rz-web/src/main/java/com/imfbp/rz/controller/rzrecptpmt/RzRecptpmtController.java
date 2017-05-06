package com.imfbp.rz.controller.rzrecptpmt;

import java.util.HashMap;
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
import com.platform.common.spring.mvc.controller.BaseController;
import com.ifbp.boss.rpc.dic.domain.RpcDicItem;
import com.ifbp.boss.rpc.dic.service.DicItemRpcService;
import com.imfbp.rz.domain.rzrecptpmt.RzRecptpmt;
import com.imfbp.rz.domain.rzrecptpmt.query.RzRecptpmtQuery;
import com.imfbp.rz.service.rzrecptpmt.RzRecptpmtService;
import com.imfbp.rz.util.ToolUtils;


@RestController
public class RzRecptpmtController extends BaseController{

	private RzRecptpmtService rzRecptpmtService;
	@Autowired
	private DicItemRpcService dicItemRpcClient;
	
	/**
	 *  跳转到RzRecptpmt首页
	 * @param rzRecptpmtQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzRecptpmt/toRzRecptpmtPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzRecptpmtPage(RzRecptpmtQuery rzRecptpmtQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzRecptpmt/rzRecptpmt");
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
	@RequestMapping(value = "rzRecptpmt/getRzRecptpmtAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRecptpmtAll(RzRecptpmtQuery rzRecptpmtQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		Result result=new Result();
		List<RzRecptpmt> rzRecptpmtList = rzRecptpmtService.getRzRecptpmtAll(rzRecptpmtQuery);
		result.setSuccess(true);
		result.addDefaultModel("rzRecptpmtList",rzRecptpmtList);
		return result;
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
	@RequestMapping(value = "rzRecptpmt/getRzRecptpmtByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzRecptpmtByPage(RzRecptpmtQuery rzRecptpmtQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		if(StringUtils.isNotEmpty(rzRecptpmtQuery.getBusitype()) && rzRecptpmtQuery.getBusitype().equals("undefined")) rzRecptpmtQuery.setBusitype("");
		if(StringUtils.isNotEmpty(rzRecptpmtQuery.getPmtrival()) &&rzRecptpmtQuery.getPmtrival().equals("undefined"))rzRecptpmtQuery.setPmtrival("");
		GridResult<RzRecptpmt> gridResult = rzRecptpmtService.getRzRecptpmtByPage(rzRecptpmtQuery);
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
	@RequestMapping(value = "rzRecptpmt/getRzRecptpmtById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzRecptpmtById(RzRecptpmtQuery rzRecptpmtQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		RzRecptpmt rzRecptpmt = rzRecptpmtService.getRzRecptpmtById(rzRecptpmtQuery);
		return rzRecptpmt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzRecptpmt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzRecptpmt rzRecptpmt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmt == null) {
			rzRecptpmt = new RzRecptpmt();
		}
		Result result = rzRecptpmtService.insertOrUpdate(rzRecptpmt);
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
	@RequestMapping(value = "rzRecptpmt/deleteRzRecptpmtByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzRecptpmtByBatchId(RzRecptpmtQuery rzRecptpmtQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		Result result = rzRecptpmtService.deleteRzRecptpmtByBatchId(rzRecptpmtQuery);
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
	@RequestMapping(value = "rzRecptpmt/logicDeleteRzRecptpmtByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzRecptpmtByBatchId(RzRecptpmtQuery rzRecptpmtQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		Result result = rzRecptpmtService.logicDeleteRzRecptpmtByBatchId(rzRecptpmtQuery);
		return result;
	}
	//前端校验编码和名称的唯一性
	@RequestMapping(value = "rzRecptpmt/getRzRecMessage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getOfAccbookMessage(RzRecptpmtQuery rzRecptpmtQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		 Map<String,Boolean>map=new HashMap<String,Boolean>();
		//编码不为空就对编码进行唯一性校验
		if(!StringUtils.isEmpty(rzRecptpmtQuery.getCode())){
			 RzRecptpmtQuery query=new RzRecptpmtQuery();
			 query.setCode(rzRecptpmtQuery.getCode());
			List<RzRecptpmt>list=rzRecptpmtService.getRzRecptpmtAll(query);
			map.put("valid",true);
			if(ToolUtils.isNotEmptyCollection(list)){
				String code=rzRecptpmtQuery.getCode();
				RzRecptpmt temp=list.get(0);
				String tempCode=temp.getCode();
				//修改的校验
				if(StringUtils.isNotEmpty(rzRecptpmtQuery.getPkRecptpmt())){
					String tempPk=temp.getPkRecptpmt();
					if(StringUtils.isNotEmpty(tempPk) && !tempPk.equals(rzRecptpmtQuery.getPkRecptpmt())){
						if(StringUtils.isNotEmpty(tempCode) && tempCode.equals(code)){
							map.put("valid", false);
						}
					}
				}else{
					//新增的校验
					if(StringUtils.isNotEmpty(tempCode) && tempCode.equals(code)){
						map.put("valid", false);
					}
				}
			}
			return map;
		}else{
			map.put("valid",true);
			 RzRecptpmtQuery query=new RzRecptpmtQuery();
			 query.setName(rzRecptpmtQuery.getName());
			List<RzRecptpmt>list=rzRecptpmtService.getRzRecptpmtAll(query);
			if(ToolUtils.isNotEmptyCollection(list)){
				String name=rzRecptpmtQuery.getName();
				RzRecptpmt temp=list.get(0);
				String tempName=temp.getName();
				//修改的校验
				if(StringUtils.isNotEmpty(rzRecptpmtQuery.getPkRecptpmt())){
					String tempPk=temp.getPkRecptpmt();
					if(StringUtils.isNotEmpty(tempPk) && !tempPk.equals(rzRecptpmtQuery.getPkRecptpmt())){
						if(StringUtils.isNotEmpty(tempName) && tempName.equals(name)){
							map.put("valid", false);
						}
					}
				}else{
					if(StringUtils.isNotEmpty(tempName) && tempName.equals(name)){
						map.put("valid",false);
					}
				}
			}
			return map;
		}
	}
	@RequestMapping(value = "rzRecptpmt/updateByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateByBatchId(RzRecptpmtQuery rzRecptpmtQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		Result result = rzRecptpmtService.updateByBatchId(rzRecptpmtQuery);
		return result;
	}
	//根据数据字典获取所有的应付对手，和业务类型
	@RequestMapping(value = "rzRecptpmt/getAllBusyTypeAndPmt", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllBusyTypeAndPmt(RzRecptpmtQuery rzRecptpmtQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if (rzRecptpmtQuery == null) {
			rzRecptpmtQuery = new RzRecptpmtQuery();
		}
		 Result result=new Result();
		 try {
			List<RpcDicItem> busyTypeList=dicItemRpcClient.getDicItem("DIC_RZ_BUSI_TYPE_REC");
			List<RpcDicItem> pmtrivalList=dicItemRpcClient.getDicItem("DIC_RZ_PMTRIVAL");
			result.addDefaultModel("busyTypeList", busyTypeList);
			result.addDefaultModel("pmtrivalList", pmtrivalList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;	
	}
	public void setRzRecptpmtService(RzRecptpmtService rzRecptpmtService) {
		this.rzRecptpmtService = rzRecptpmtService;
	}

}