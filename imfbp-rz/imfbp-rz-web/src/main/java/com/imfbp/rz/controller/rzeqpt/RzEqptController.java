package com.imfbp.rz.controller.rzeqpt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.imfbp.rz.domain.rzeqpt.RzEqpt;
import com.imfbp.rz.domain.rzeqpt.query.RzEqptQuery;
import com.imfbp.rz.service.rzeqpt.RzEqptService;


@RestController
public class RzEqptController extends BaseController{

	private RzEqptService rzEqptService;
	@Autowired
	private DicItemRpcService dicItemRpcClient;
	
	/**
	 *  跳转到RzEqpt首页
	 * @param rzEqptQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "rzEqpt/toRzEqptPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toRzEqptPage(RzEqptQuery rzEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("rzEqpt/rzEqpt");
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
	@RequestMapping(value = "rzEqpt/getRzEqptAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzEqptAll(RzEqptQuery rzEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzEqptQuery == null) {
			rzEqptQuery = new RzEqptQuery();
		}
		List<RzEqpt> rzEqptList = rzEqptService.getRzEqptAll(rzEqptQuery);
		return rzEqptList;
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
	@RequestMapping(value = "rzEqpt/getRzEqptByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRzEqptByPage(RzEqptQuery rzEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (rzEqptQuery == null) {
			rzEqptQuery = new RzEqptQuery();
		}
		GridResult<RzEqpt> gridResult = rzEqptService.getRzEqptByPage(rzEqptQuery);
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
	@RequestMapping(value = "rzEqpt/getRzEqptById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getRzEqptById(RzEqptQuery rzEqptQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		RzEqpt rzEqpt = rzEqptService.getRzEqptById(rzEqptQuery);
		return rzEqpt;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "rzEqpt/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(RzEqpt rzEqpt,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzEqptService.insertOrUpdate(rzEqpt);
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
	@RequestMapping(value = "rzEqpt/deleteRzEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteRzEqptByBatchId(RzEqptQuery rzEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzEqptService.deleteRzEqptByBatchId(rzEqptQuery);
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
	@RequestMapping(value = "rzEqpt/logicDeleteRzEqptByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object logicDeleteRzEqptByBatchId(RzEqptQuery rzEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = rzEqptService.logicDeleteRzEqptByBatchId(rzEqptQuery);
		return result;
	}
	
	@RequestMapping(value = "rzEqpt/getAllEqptType", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getAllEqptType(RzEqptQuery rzEqptQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context){
		Result result=new Result();
		 try {
			List<RpcDicItem> rpcList=dicItemRpcClient.getDicItem("DIC_RZ_EQPT_TYPE");
			result.addDefaultModel("eqptTypeList", rpcList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public void setRzEqptService(RzEqptService rzEqptService) {
		this.rzEqptService = rzEqptService;
	}

}