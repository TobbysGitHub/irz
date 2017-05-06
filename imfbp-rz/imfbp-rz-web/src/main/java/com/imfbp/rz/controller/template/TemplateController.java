package com.imfbp.rz.controller.template;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.fastdfs.rpc.api.fastdfs.domain.query.RpcDfsFileInfoQuery;
import com.imfbp.fastdfs.rpc.api.fastdfs.service.IImfbpFastDFSRpcService;
import com.imfbp.rz.constant.RZConstants;
import com.imfbp.rz.controller.ApproveController;
import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.imfbp.rz.domain.jstreenode.JsTreeNode;
import com.imfbp.rz.domain.template.BaseSystemInfo;
import com.imfbp.rz.domain.template.NodeTemplateEntry;
import com.imfbp.rz.domain.template.TemplateDefEntry;
import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;
import com.imfbp.rz.domain.templatenodedef.query.TemplateNodeDefQuery;
import com.imfbp.rz.domain.templaterulesdef.TemplateRulesDef;
import com.imfbp.rz.enums.FileTypeEnum;
import com.imfbp.rz.service.template.ITemplateService;
import com.imfbp.rz.service.template.nodetable.BasePublicService;
import com.imfbp.rz.service.templatedef.TemplateDefService;
import com.imfbp.rz.service.templaterulesdef.TemplateRulesDefService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.Word2HtmlUtil;
import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.seculity.moduleinfo.Moduleinfo;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.login.enums.LoginEnum;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

/**
 * @Title 模板控制类 :
 * @Description : 提供模板服务统一访问入口，提供模板保存(新增与修改)、获取模板、模板预览、模板复制、导出模板、导出单据文档等服务
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年11月25日 下午2:05:12
 */
@RestController
public class TemplateController extends PubBaseContrl {

	@Autowired
	private IImfbpFastDFSRpcService iImfbpFastDFSRpcService;

	@Autowired
	private ITemplateService iTemplateService;

	@Autowired
	private TemplateDefService templateDefService;

	@Autowired
	private TemplateRulesDefService templateRulesDefService;
	
	@Autowired
	private BasePublicService basePublicService;
	
	private final static Logger logger = Logger
			.getLogger(ApproveController.class);
	
	/**
	 * 跳转到模板定义TemplateDef首页
	 * 
	 * @param templateDefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "template/toTemplateDefPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toTemplateDefPage(TemplateDefQuery templateDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("templateDef/templateDef");
		attachLoginInfo(mv);
		return mv;
	}

	/**
	 *  跳转到模板规则生成TemplateNodeDef首页
	 * @param templateNodeDefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "template/toTemplateNodeDefPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toTemplateNodeDefPage(TemplateNodeDefQuery templateNodeDefQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("templateNodeDef/templateNodeDef");
		attachLoginInfo(mv);
		return mv;
	}
	
	@RequestMapping(value = "template/getFormulaList", method = {
			RequestMethod.POST, RequestMethod.GET })
	public Result getFormulaList(HttpServletRequest req, HttpServletResponse resp){
//		TODO
		Result result = new Result(false);
		try {
			result.addDefaultModel(RZConstants.LIST, basePublicService.getFormulaEntryList());
			result.setSuccessMessage("获取公式数据成功");
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取公式数据出现异常，异常信息："+e.getMessage(), e);
			result.setErrorMessage("获取公式数据出现异常，异常信息："+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取系统相关元数据
	 * @param nodeCode
	 * 			节点编码
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/getMetadata", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getMetadata(@RequestParam("nodeCode") String nodeCode,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			result=iTemplateService.getMetadata(this.getSysId(),nodeCode);
			if (result == null) {
				throw new Exception("获取系统相关元数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("获取系统相关元数据出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("获取系统相关元数据出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result(false);
			}
			result.setErrorMessage("获取系统相关元数据出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}
	/**
	 * 获取公共元数据-比如机构、部门档案
	 * 
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/getPublicMetadata", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getPublicMetadata(HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			result=iTemplateService.getPublicMetadata(getSysId());
			if (result == null) {
				throw new Exception("获取系统相关元数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("获取系统相关元数据出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("获取系统相关元数据出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result(false);
			}
			result.setErrorMessage("获取系统相关元数据出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取功能节点树数据
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "template/getFunMenuTree", method = { RequestMethod.POST,RequestMethod.GET })
	public List<JsTreeNode> getFunMenuTree(HttpServletRequest req,
			HttpServletResponse resp) {
		List<JsTreeNode> jstree = new ArrayList<JsTreeNode>();
		try {
			Object obj = session.getAttribute(LoginEnum.LONGIN_MODULE_INFOS
					.getloginInfo());
			if (obj != null) {
				List<Moduleinfo> moduleinfos = (List<Moduleinfo>) obj;
				//添加根目录
				JsTreeNode rootNode = new JsTreeNode();
				rootNode.setId("0");
				rootNode.setParent("#");
				rootNode.setText("功能列表");
				rootNode.getState().put("opened", true);
				jstree.add(rootNode);
				for (Moduleinfo moduleinfo : moduleinfos) {
					JsTreeNode jsTreeNode = new JsTreeNode();
					jsTreeNode.setId(moduleinfo.getId());
					jsTreeNode.setCode(moduleinfo.getModuleValue());
					jsTreeNode.setText(moduleinfo.getModuleName());
					jsTreeNode.setParent(moduleinfo.getPid());
					jstree.add(jsTreeNode);
				}
			}
		} catch (Exception e) {
			logger.error("获取功能节点树数据出现异常，异常信息："+e.getMessage(), e);
		}
		return jstree;
	}

	/**
	 * 导入模板定义示例文件--上传附件
	 * 
	 * @param file
	 *            模板文件
	 * @param req
	 * @param resp
	 * @return result.map.get(RZConstants.HTML_FILE_DATA)获取文件文本内容
	 */
	@RequestMapping(value = "template/importExampleFile", method = { RequestMethod.POST })
	public Result importExampleFile(HttpServletRequest req, HttpServletResponse resp) {
//		TODO
		Result result = new Result(false);
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("path");
//			byte[] filebytes = file.getBytes();
			result.addDefaultModel(RZConstants.HTML_FILE_DATA, Word2HtmlUtil.doc2Html(file.getInputStream()));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("导入模板定义文件时出现异常，异常信息：" + e.getMessage(), e);
			result.setSuccess(false);
			result.setErrorMessage("导入模板定义文件时出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 改变模板节点分配模板启用停用状态
	 * 
	 * @param nodeTemplateId
	 * 			节点分配模板id
	 * @param isUsed
	 * 			1：启用    0：停用
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/updateNodeTemplateStatus", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateNodeTemplateStatus(
			@RequestParam("nodeTemplateId") String nodeTemplateId,@RequestParam("useStatus") Integer useStatus,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			result=iTemplateService.updateNodeTemplateStatus(nodeTemplateId,useStatus);
			if (result == null) {
				throw new Exception("修改节点分配模板数据启用状态失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("修改节点分配模板数据启用状态出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("修改节点分配模板数据启用状态出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result(false);
			}
			result.setErrorMessage("修改节点分配模板数据启用状态出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 改变模板启用停用状态
	 * 
	 * @param templateId
	 * 			模板id
	 * @param templateStatus
	 * 			1：启用    0：停用
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/updateTemplateDefStatus", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateTemplateDefStatus(
			@RequestParam("templateId") String templateId,@RequestParam("templateStatus") Integer templateStatus,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			result=iTemplateService.updateTemplateDefStatus(templateId,templateStatus);
			if (result == null) {
				throw new Exception("修改模板数据启用状态失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("修改模板数据启用状态出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("修改数据启用状态出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result(false);
			}
			result.setErrorMessage("修改模板数据启用状态出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取指定分配的模板信息
	 * 
	 * @param nodeCode
	 * 				当前选中的节点编码
	 * @param req
	 * @param resp
	 * @param context
	 * @return  
	 * 			result.get(RZConstants.NODE_TEMPLATE_DATA)获取List<NodeTemplateInfo>
	 *          类型结果集数据
	 */
	@RequestMapping(value = "template/getNodeTemplateData", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getNodeTemplateData(
			@RequestParam("nodeCode") String nodeCode,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			result=iTemplateService.getNodeTemplateData(nodeCode,this.getTenantId());
			if (result == null) {
				throw new Exception("获取节点分配模板数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("获取节点分配模板数据出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("获取节点分配模板数据出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result(false);
			}
			result.setErrorMessage("获取节点分配模板数据出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取指定节点未分配的模板信息
	 * 
	 * @param templateDefQuery
	 * 				查询参数，其中nodeCode必须存在
	 * @param req
	 * @param resp
	 * @param context
	 * @return  
	 * 			result.get(RZConstants.LIST)获取GridResult<TemplateDef>类型结果集数据
	 */
	@RequestMapping(value = "template/getNoDisTemplateDefByPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getNoDisTemplateDefByPage(TemplateDefQuery templateDefQuery,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
//		Result result = null;
		GridResult<TemplateDef> list = null;
		try {
			list=iTemplateService.getNoDisTemplateDefByPage(templateDefQuery);
//			if (result == null) {
//				throw new Exception("获取节点未分配模板数据失败");
//			}
//			if (result != null && !result.isSuccess()) {
//				throw new Exception("获取节点未分配模板数据出现异常，异常信息："
//						+ result.getErrorMessage());
//			}
		} catch (Exception e) {
			logger.error("获取节点未分配模板数据出现异常，异常信息：" + e.getMessage(), e);
//			if (result == null) {
//				result = new Result(false);
//			}
//			result.setErrorMessage("获取节点未分配模板数据出现异常，异常信息：" + e.getMessage());
		}
		return list;
	}
	
	/**
	 * 保存节点分配模板数据
	 * 
	 * 
	 * @param nodeTemplateEntry
	 * 					节点模板参数，包含节点编码、分配的模板id集合、取消分配的模板id集合
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/saveNodeTemplateData", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object saveNodeTemplateData(
			@RequestBody() NodeTemplateEntry nodeTemplateEntry,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			BaseSystemInfo baseSystemInfo=new BaseSystemInfo();
			baseSystemInfo.setUserId(getUserId());
			baseSystemInfo.setTenantId(getTenantId());
			result=iTemplateService.saveNodeTemplateData(nodeTemplateEntry, baseSystemInfo);
			if (result == null) {
				throw new Exception("保存节点模板数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("保存节点模板数据出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("保存节点模板数据出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
			}
			result.setErrorMessage("保存节点模板数据出现异常，异常信息：" + e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 取消分配模板
	 * 
	 * @param nodeTemplateIds
	 * 					节点模板id集合
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/delNodeTemplateData", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object delNodeTemplateData(
			@RequestBody() List<String> nodeTemplateIds,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			result=iTemplateService.delNodeTemplateData(nodeTemplateIds);
			if (result == null) {
				throw new Exception("取消分配模板数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("取消分配模板数据出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("取消分配模板数据出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
			}
			result.setErrorMessage("取消分配模板数据出现异常，异常信息：" + e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 批量保存模板规则定义数据
	 * 
	 * 前端JS调用:
	 * var jsonData=[
     *     {
     *   	 'templateNodeId':'00101' ,
     *   	 'inputItemId':'inputItemId',
     *   	 'formula':'formula',
     *  	 'formulaDetail':'formulaDetail'
     *     },{
     *   	  'templateNodeId':'001011' ,
     *       	 'inputItemId':'inputItemId1',
     *       	 'formula':'formula1',
     *       	 'formulaDetail':'formulaDetail1'  
     *     }];
	 *	var url = "http://127.0.0.1:8080/imfbp-rz-web/template/saveTemplateRulesForBatch";
	 *	$.ajax({
	 *	  type: 'POST',
	 *	  url: url,
	 *	  data: JSON.stringify(jsonData),
	 *	  headers : {  
     *           'Content-Type' : 'application/json;charset=utf-8'  
     *      },
	 *	  dataType: "json",
	 *	  success: function(data) {
	 *		  if (data != null && data.success == true) {
	 *				alert("保存模板业务规则数据成功");
	 *				//editor.html('');
	 *				//editor.html(data.map.templateDefEntry.htmlContent);
	 *			} else {
	 *				alert("保存模板业务规则数据失败");
	 *			}
	 *		},
	 *	  error:function(){
	 *		  alert("调用保存模板业务规则数据失败");
	 *	  }
	 *	});
	 * 
	 * @param templateRulesDefList
	 *            模板规则数据集合
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/saveTemplateRulesForBatch", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object saveTemplateRulesForBatch(
			@RequestBody() List<TemplateRulesDef> templateRulesDefList,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
//		TODO
		Result result = null;
		try {
			result = templateRulesDefService
					.insertOrUpdateForBatch(templateRulesDefList);
			if (result == null) {
				throw new Exception("保存模板业务规则数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("保存模板业务规则数据出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("保存模板业务规则数据出现异常，异常信息：" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
			}
			result.setErrorMessage("保存模板业务规则数据出现异常，异常信息：" + e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 查询节点分配的模板对应的指标数据
	 * 
	 * @param templateNodeDefQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "template/getRulesDataByTemplateNodeId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getRulesDataByTemplateNodeId(
			TemplateNodeDefQuery templateNodeDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = new Result();
		result.setSuccess(false);
		try {
			if (templateNodeDefQuery == null) {
				templateNodeDefQuery = new TemplateNodeDefQuery();
			}
			result = iTemplateService.getRulesDataByTemplateNodeId(templateNodeDefQuery);
			if(result==null){
				result = new Result(false);
				result.setErrorMessage("根据节点模板id查询模业务板规则数据为获取到数据");
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据节点模板id查询模业务板规则数据出现异常，异常信息：" + e.getMessage());
			result = new Result();
			result.setSuccess(false);
			result.setErrorMessage("根据节点模板id查询模业务板规则数据出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 查询所有满足条件的模板定义数据
	 * 
	 * @param templateDefQuery
	 *            查询条件
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "template/getTemplateDefAll", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateDefAll(
			TemplateDefQuery templateDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = new Result();
		result.setSuccess(false);
		try {
			if (templateDefQuery == null) {
				templateDefQuery = new TemplateDefQuery();
			}
			List<TemplateDef> templateDefList = templateDefService
					.getTemplateDefAll(templateDefQuery);
			result.addDefaultModel("templateDefList", templateDefList);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("查询所有模板定义数据出现异常，异常信息：" + e.getMessage());
			result = new Result();
			result.setSuccess(false);
			result.setErrorMessage("查询所有模板定义数据出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}

	/**
	 * 分页查询满足条件的模板定义数据
	 * 
	 * @param templateDefQuery
	 *            查询条件
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "template/getTemplateDefByPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getTemplateDefByPage(
			TemplateDefQuery templateDefQuery, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = new Result();
		result.setSuccess(false);
		try {
			if (templateDefQuery == null) {
				templateDefQuery = new TemplateDefQuery();
			}
			GridResult<TemplateDef> gridResult = templateDefService
					.getTemplateDefByPage(templateDefQuery);
			result.addDefaultModel("gridResult", gridResult);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取模板数据出现异常，异常信息：" + e.getMessage());
			result = new Result();
			result.setSuccess(false);
			result.setErrorMessage("分页获取模板数据出现异常，异常信息：" + e.getMessage());
		}
		return result;
	}

	/**
	 * 根据Id批量删除 (真正删除数据库数据)模板数据
	 * 
	 * 前端JS调用:
	 *  var jsonData=[
	 *			'ADMINCXIAO4BKB000051',
	 *			'ADMINCXIAO4BKB000055',
	 *			'ADMINCXIAO4BKB000059'
	 *		];
	 *	var url = "http://127.0.0.1:8080/imfbp-rz-web/template/deleteTemplateDefByBatchId";
	 *	$.ajax({
	 *		  type: 'POST',
	 *		  url: url,
	 *		  data: JSON.stringify(jsonData),
	 *		  headers : {  
	 *               'Content-Type' : 'application/json;charset=utf-8'  
	 *           },
	 *		  dataType: "json",
	 *		  success: function(data) {
	 *			  if (data != null && data.success == true) {
	 *					alert("批量删除模板成功");
	 *				} else {
	 *					alert("批量删除模板失败");
	 *				}
	 *			},
	 *		  error:function(){
	 *			  alert("调用批量删除模板失败");
	 *		  }
	 *		});
	 * 
	 * @param templateDefQuery
	 *            需要删除的模板数据条件
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/deleteTemplateDefByBatchId", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deleteTemplateDefByBatchId(
			@RequestBody() List<String> batchIds, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = null;
		try {
			result = iTemplateService
					.deleteTemplateDefByBatchId(batchIds);
			if (result == null) {
				throw new Exception("删除模板数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("删除模板数据出现异常，异常信息："
						+ result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("保存模板信息出现异常，异常信息:" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
				result.setSuccess(false);
				result.setErrorMessage("保存模板信息出现异常，异常信息:" + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 保存模板数据-模板定义时新增与修改保存模板时调用
	 * 
	 * 前端js调用形式如下:
	 * var jsonData ={
	 *					'templateDef':{
	 *						'templateCode':'templateCode',
	 *						'templateName':'templateName'
	 *					},
	 *					'templateInputDefLists':[{
	 *					'itemCode':'${1}',
	 *						'itemName':'${itemName1}'
	 *					},{
	 *						'itemCode':'${2}',
	 *						'itemName':'${itemName2}'
	 *					}],
	 *					'htmlContent' :htmlData,
	 *				}; 
	 * var url = "http://127.0.0.1:8080/imfbp-rz-web/template/saveTemplatData";
	 * $.ajax({
	 *	  type: 'POST',
	 *	  url: url,
	 *	  data: JSON.stringify(jsonData),
	 *	  headers : {  
     *           'Content-Type' : 'application/json;charset=utf-8'  
     *      },
	 *	  dataType: "json",
	 *	  success: function(data) {
	 *			if (data != null && data.map.success == true) {
	 *					alert("保存成功");
	 *			} else {
	 *				alert("保存失败");
	 *			}
	 *		},
	 *	  error:function(){
	 *	  	alert("保存失败");
	 *	   }
	 *    });
	 *    
	 * @param templateDefEntry
	 *            模板定义实体-包含模板数据、模板输入项数据、模板内容
	 * @param req
	 * @param resp
	 * @param context
	 * @return Result.get(templateDefEntry)获取保存后的模板所有信息
	 */
	@RequestMapping(value = "template/saveTemplatData", method = { RequestMethod.POST })
	public @ResponseBody Result saveTemplatData(@RequestBody()TemplateDefEntry templateDefEntry,
			HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = null;
		try {
			initDefultValue(templateDefEntry);
			result = iTemplateService.saveTemplatData(templateDefEntry);
			if (result == null) {
				throw new Exception("保存模板信息失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("保存模板信息出错：" + result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("保存模板信息出现异常，异常信息:" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
				result.setSuccess(false);
				result.setErrorMessage("保存模板信息出现异常，异常信息:" + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 模板预览-预览时，需要进行数据填充的地方统一使用XXXX替换
	 * 
	 * * 调用形式如下：
	 *  var htmlData = editor.html();
	 *	var jsonData={
	 *			'htmlData':htmlData
	 *	};
	 *	var url = "http://127.0.0.1:8080/imfbp-rz-web/template/previewTemplatData";
	 *	$.post(url,jsonData,function(data) {
	 *		if (data != null && data.map != null && data.map.htmlData != null) {
	 *			//编辑数据
	 *			editor.html('');
	 *			//设置数据
	 *			editor.html(data.map.htmlData);
	 *		} else {
	 *			alert("预览失败");
	 *		}
	 *	});
	 * 
	 * @param htmlData
	 *            模板html数据
	 * @param req
	 * @param resp
	 * @param context
	 * @return result.get(RZConstants.HTML_FILE_DATA)获取htmlData数据
	 */
	@RequestMapping(value = "template/previewTemplatData", method = { RequestMethod.POST })
	public @ResponseBody Result previewTemplatData(
			@RequestParam("htmlData") String htmlData, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = null;
		try {
			// initDefultValue(templateDefEntry);
			result = iTemplateService.previewTemplatData(htmlData);
			if (result == null) {
				throw new Exception("预览模板信息失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("预览模板信息出错：" + result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("预览模板信息出现异常，异常信息:" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
				result.setSuccess(false);
				result.setErrorMessage("预览模板信息出现异常，异常信息:" + e.getMessage());
			}
		}
		return result;
	}
	
	/**
	 * 获取指标数据
	 * 
	 * @param templateId
	 * 				模板id
	 * @param htmlData
	 * 				模板文档html格式内容
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/getInputDataByHtmlData", method = { RequestMethod.POST })
	public @ResponseBody Result getInputDataByHtmlData(@RequestParam("templateId") String templateId,
			@RequestParam("htmlData") String htmlData, HttpServletRequest req,
			HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = null;
		try {
			result = iTemplateService.getTemplatBpaData(templateId,htmlData);
			if (result == null) {
				throw new Exception("获取指标数据失败");
			}
			if (result != null && !result.isSuccess()) {
				throw new Exception("获取指标数据出错：" + result.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("获取指标数据出现异常，异常信息:" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
				result.setSuccess(false);
				result.setErrorMessage("获取指标数据出现异常，异常信息:" + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 复制模板
	 * 
	 * 前端JS调用如下：
	 * var jsonData={
	 *		'templateId':'ADMINCXIAO4BKB00006B',
	 *		'templateName':'CopytemplateName'
	 *	};
	 *	var url = "http://127.0.0.1:8080/imfbp-rz-web/template/copyTemplateData";
	 *	$.post(url,jsonData,function(data) {
	 *		if (data != null && data.success == true) {
	 *			alert("模板复制成功");
	 *		} else {
	 *			alert("模板复制失败");
	 *		}
	 *	});
	 * 
	 * @param templateId
	 *            模板id
	 * @param templateName
	 *            模板复制后的名称
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "template/copyTemplateData", method = { RequestMethod.POST })
	public @ResponseBody Result copyTemplateData(
			@RequestParam("templateId") String templateId,
			@RequestParam("templateName") String templateName,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = null;
		try {
			BaseSystemInfo baseSystemInfo = new BaseSystemInfo();
			baseSystemInfo.setSystemCode(this.getSysCode());
			baseSystemInfo.setTenantId(this.getTenantId());
			baseSystemInfo.setUserId(this.getUserId());
			result = iTemplateService.copyTemplateData(templateId,
					templateName, baseSystemInfo);
			if (result == null || !result.isSuccess()) {
				throw new Exception("复制模板时调用出现异常");
			}
		} catch (Exception e) {
			logger.error("复制模板信息出现异常，异常信息:" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
				result.setSuccess(false);
				result.setErrorMessage("复制模板信息出现异常，异常信息:" + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 根据模板id获取模板数据
	 * 
	 * 前端JS调用：
	 *  var jsonData={
	 *		'templateId':'ADMINCXIAO4BKB00006B'
	 *	};
	 *	var url = "http://127.0.0.1:8080/imfbp-rz-web/template/getTemplateDataById";
	 *	$.post(url,jsonData,function(data) {
	 *		if (data != null && data.success == true) {
	 *			alert("根据id获取模板成功");
	 *			editor.html('');
	 *			editor.html(data.map.templateDefEntry.htmlContent);
	 *		} else {
	 *			alert("根据id获取模板失败");
	 *		}
	 *	});
	 * 
	 * @param templateId
	 *            模板id
	 * @param req
	 * @param resp
	 * @param context
	 * @return result.get(RZConstants.TEMPLATE_DEF_ENTRY)拿到模板文件组合实体
	 */
	@RequestMapping(value = "template/getTemplateDataById", method = { RequestMethod.POST })
	public @ResponseBody Result getTemplateDataById(
			@RequestParam("templateId") String templateId,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		// TODO
		Result result = null;
		try {
			result = iTemplateService.getTemplateDataById(templateId);
			if (result == null || !result.isSuccess()) {
				throw new Exception("获取模板数据时调用出现异常");
			}
		} catch (Exception e) {
			logger.error("获取模板数据出现异常，异常信息:" + e.getMessage(), e);
			if (result == null) {
				result = new Result();
				result.setSuccess(false);
				result.setErrorMessage("获取模板数据出现异常，异常信息:" + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 导出模板文件
	 * 
	 * 前端JS调用：
	 *  var url = "http://127.0.0.1:8080/imfbp-rz-web/template/exportTemplateData?";
	 *	url += "templateId=ADMINCXIAO4BKB00006B";
	 *	url += "&exportType=doc";
	 *	url += "&"+ Math.random();
	 *	window.location.href = url;
	 *
	 * @param templateId
	 *            模板文件id
	 * @param exportType
	 *            导出类型，默认doc,支持pdf
	 * @param req
	 * @param resp
	 * @param context
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "template/exportTemplateData", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void exportTemplateData(
			@RequestParam("templateId") String templateId,
			@RequestParam("exportType") String exportType,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		// TODO
		OutputStream os = null;
		byte[] filebytes = null;
		String fileName = null;
		try {
			Map<String, Object> dataMap = iTemplateService.exportTemplateData(
					templateId, exportType);
			if (dataMap == null || dataMap.size() != 2
					|| dataMap.get(RZConstants.HTML_FILE_DATA) == null
					|| dataMap.get(RZConstants.HTML_FILE_NAME) == null) {
				fileName = "blank.txt";
				filebytes = "未找到对应附件!".getBytes();
			}
			filebytes = (byte[]) dataMap.get(RZConstants.HTML_FILE_DATA);
			fileName = dataMap.get(RZConstants.HTML_FILE_NAME).toString();
//			String htmlData = new String(filebytes);
//			htmlData = RZConstants.HTML_FILE_HEAD + htmlData;
//			htmlData += RZConstants.HTML_FILE_TAIL;
//			filebytes = htmlData.getBytes();
//			filebytes = Html2PDFUtils.html2PDF(htmlData);
//			os = writePdfFileToResp(req, resp, "fileName.pdf", filebytes);
			os = writeFileToResp(req, resp, fileName, filebytes);
		} catch (Exception e) {
			logger.error("导出模板数据出现异常，异常信息:" + e.getMessage(), e);
			fileName = "blank.txt";
			filebytes = "未找到对应附件!".getBytes();
			try {
				os = writeFileToResp(req, resp, fileName, filebytes);
			} catch (Exception e1) {
				logger.error("导出模板数据出现异常，异常信息:" + e1.getMessage(), e1);
			}
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("导出模板数据出现异常，异常信息:" + e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 导出单据文档文件
	 * 
	 * @param billFileId
	 *            文档id
	 * @param exportType
	 *            导出类型-默认pdf，可选doc
	 * @param req
	 * @param resp
	 * @param context
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "template/exportBillDataDoc", method = { RequestMethod.POST })
	public void exportBillDataDoc(
			@RequestParam("billFileId") String billFileId,
			@RequestParam("exportType") String exportType,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		// TODO
		OutputStream os = null;
		byte[] filebytes = null;
		String fileName = null;
		try {
			Result result = iTemplateService.exportBillDataDoc(billFileId,
					exportType);
			if (StringUtil.isEmpty(exportType)) {
				exportType = FileTypeEnum.PDF.getValue();
			}
			if (result == null || !result.isSuccess()
					|| result.get(RZConstants.HTML_FILE_DATA) == null
					|| result.get(RZConstants.HTML_FILE_NAME) == null) {
				fileName = "blank.txt";
				filebytes = "未找到对应附件!".getBytes();
			}
			filebytes = (byte[]) result.get(RZConstants.HTML_FILE_DATA);
			fileName = result.get(RZConstants.HTML_FILE_NAME).toString();
			if (exportType.equals(FileTypeEnum.PDF.getValue())) {
				os = writePdfFileToResp(req, resp, fileName, filebytes);
			} else if (exportType.equals(FileTypeEnum.DOC.getValue())) {
				os = writeFileToResp(req, resp, fileName, filebytes);
			}
		} catch (Exception e) {
			logger.error("导出模板数据出现异常，异常信息:" + e.getMessage(), e);
			fileName = "blank.txt";
			filebytes = "未找到对应附件!".getBytes();
			try {
				os = writeFileToResp(req, resp, fileName, filebytes);
			} catch (Exception e1) {
				logger.error("导出模板数据出现异常，异常信息:" + e1.getMessage(), e1);
			}
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("导出模板数据出现异常，异常信息:" + e.getMessage(), e);
				}
			}
		}
	}

	// /**
	// * 单据文档预览
	// *
	// * @param billId
	// * 单据id(主键)
	// * @param nodeTemplateId
	// * 节点模板分配id
	// * @param req
	// * @param resp
	// * @param context
	// * @return
	// */
	// @RequestMapping(value = "template/previewBillDoc", method = {
	// RequestMethod.POST })
	// private @ResponseBody Result previewBillDoc(
	// @RequestParam("billId") String billId,
	// @RequestParam("nodeTemplateId") String nodeTemplateId,
	// HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
	// Result result = null;
	// try {
	// // 组装数据-在个单据自己的Service中组装即可
	// Map<String, List<SuperBean>> billDataMap = new HashMap<String,
	// List<SuperBean>>();
	// billDataMap.put("tablename1", null);
	// billDataMap.put("tablename2", null);
	// billDataMap.put("tablename3", null);
	// // initDefultValue(templateDefEntry);
	// result = iTemplateService.previewBillDoc(billId, billDataMap,
	// nodeTemplateId);
	// if (result == null) {
	// throw new Exception("预览文档信息失败");
	// }
	// if (result != null && !result.isSuccess()) {
	// throw new Exception("预览文档信息出错：" + result.getErrorMessage());
	// }
	// } catch (Exception e) {
	// logger.error("预览文档信息出现异常，异常信息:" + e.getMessage(), e);
	// if (result == null) {
	// result = new Result();
	// result.setSuccess(false);
	// result.setErrorMessage("预览文档信息出现异常，异常信息:" + e.getMessage());
	// }
	// }
	// return result;
	// }

	// /**
	// * 保存模板--定义模板时保存模板
	// *
	// * @param templateCode
	// * 模板编码
	// * @param templateName
	// * 模板名称
	// * @param htmlData
	// * 模板数据--html格式
	// * @param req
	// * @param resp
	// * @param context
	// * @return
	// */
	// @RequestMapping(value = "template/saveNodeTemplatData", method = {
	// RequestMethod.POST })
	// private @ResponseBody Result saveNodeTemplatData(
	// @RequestParam("templateCode") String templateCode,
	// @RequestParam("templateName") String templateName,
	// @RequestParam("htmlData") String htmlData, HttpServletRequest req,
	// HttpServletResponse resp, ModelMap context) {
	// Result result = new Result();
	// try {
	// result.setSuccess(false);
	// RpcDfsFileInfo rpcDfsFileInfo = new RpcDfsFileInfo();
	// rpcDfsFileInfo.setFileName(templateName);
	// rpcDfsFileInfo.setFileSize((long) htmlData.getBytes().length);
	// rpcDfsFileInfo.setFileType(HTML_FILE_TYPE);
	// byte[] content = htmlData.getBytes();
	// Result uploadResult = iImfbpFastDFSRpcService.uploadFile(
	// rpcDfsFileInfo, content);
	// if (uploadResult != null && uploadResult.isSuccess()) {
	// System.out.println("=========================================");
	// Object obj = uploadResult.get("dfsFileInfo");
	// // 将数据存放自己的数据库中
	// } else {
	// // 存在异常，提示为什么出现异常
	// }
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }
	// return result;
	// }

	@RequestMapping(value = "template/getTemplateDoc", method = {
			RequestMethod.POST, RequestMethod.GET })
	public Result getTemplateDoc(
			@RequestParam("fileFastdfsId") String fileFastdfsId,
			HttpServletRequest req, HttpServletResponse resp) {
		Result result = new Result();
		try {
			result.setSuccess(false);
			RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
			rpcDfsFileInfoQuery.setFileFastdfsId(fileFastdfsId);
			Result downResult = iImfbpFastDFSRpcService
					.downloanFile(rpcDfsFileInfoQuery);
			String fileContent = null;
			if (downResult != null && downResult.isSuccess()) {
				Object obj = downResult.get("fastdfscontent");
				if (obj != null) {
					fileContent = new String((byte[]) obj);
					result.addDefaultModel("fileContent", fileContent);
					result.setSuccess(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// @RequestMapping(value = "template/createDoc", method = {
	// RequestMethod.GET })
	// public void createDoc(@RequestParam("fileFastdfsId") String
	// fileFastdfsId,
	// HttpServletRequest req, HttpServletResponse resp) {
	//
	// String filename = "html转换为doc文档.doc";
	// OutputStream os = null;
	// byte[] filebytes = null;
	// try {
	// RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
	// rpcDfsFileInfoQuery.setFileFastdfsId(fileFastdfsId);
	// Result downResult = iImfbpFastDFSRpcService
	// .downloanFile(rpcDfsFileInfoQuery);
	// boolean flag = false;
	// if (downResult != null && downResult.isSuccess()) {
	// Object obj = downResult.get("fastdfscontent");
	// if (obj != null) {
	// flag = true;
	// filebytes = (byte[]) obj;
	// }
	// }
	// if (!flag) {
	// filename = "blank.txt";
	// filebytes = "未找到对应附件!".getBytes();
	// }
	// os = writeFileToResp(req, resp, filename, filebytes);
	// } catch (Exception e) {
	// filename = "blank.txt";
	// filebytes = "未找到对应附件!".getBytes();
	// try {
	// os = writeFileToResp(req, resp, filename, filebytes);
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	// } finally {
	// if (os != null) {
	// try {
	// os.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }

	// public void htmlToWord2(byte content[]) throws Exception {
	// InputStream bodyIs = new FileInputStream("f:\\1.html");
	// InputStream cssIs = new FileInputStream("f:\\1.css");
	// String body = this.getContent(bodyIs);
	// String css = this.getContent(cssIs);
	// // 拼一个标准的HTML格式文档
	// String content = "<html><head><style>" + css +
	// "</style></head><body>" + body + "</body></html>";
	// InputStream is = new ByteArrayInputStream(content);
	// OutputStream os = new FileOutputStream("f:\\1.doc");
	// this.inputStreamToWord(is, os);
	// }

	/**
	 * 将数据返回 到resp
	 * 
	 * @param req
	 * @param resp
	 * @param filename
	 * @param filebytes
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private OutputStream writeFileToResp(HttpServletRequest req,
			HttpServletResponse resp, String filename, byte[] filebytes)
			throws Exception {

		String userAgent = req.getHeader("User-Agent");
		byte[] bytes = userAgent.contains("MSIE") ? filename.getBytes()
				: filename.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
		filename = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
		OutputStream os = resp.getOutputStream();
		resp.reset();
		resp.setHeader("Content-disposition",
				String.format("attachment; filename=\"%s\"", filename)); // 文件名外的双引号处理firefox的空格截断问题
		resp.setContentType("application/octet-stream; charset=utf-8");
		POIFSFileSystem fs = new POIFSFileSystem();
		InputStream is = new ByteArrayInputStream(filebytes);
		// 对应于org.apache.poi.hdf.extractor.WordDocument
		fs.createDocument(is, "WordDocument");
		fs.writeFilesystem(os);
		os.write(filebytes);
		os.flush();
		return os;
	}

	/**
	 * 将pdf数据返回到resp中
	 * @param req
	 * @param resp
	 * @param filename
	 * @param filebytes
	 * @return
	 * @throws Exception
	 */
	private OutputStream writePdfFileToResp(HttpServletRequest req,
			HttpServletResponse resp, String filename, byte[] filebytes)
			throws Exception {

		String userAgent = req.getHeader("User-Agent");
		byte[] bytes = userAgent.contains("MSIE") ? filename.getBytes()
				: filename.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
		filename = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
		OutputStream os = resp.getOutputStream();
		resp.reset();
		resp.setHeader("Content-disposition",
				String.format("attachment; filename=\"%s\"", filename)); // 文件名外的双引号处理firefox的空格截断问题
		resp.setContentType("application/octet-stream; charset=utf-8");
		os.write(filebytes);
		os.flush();
		return os;
	}
	
	/**
	 * 把is写入到对应的word输出流os中 不考虑异常的捕获，直接抛出
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	// private void inputStreamToWord(InputStream is, OutputStream os)
	// throws IOException {
	// POIFSFileSystem fs = new POIFSFileSystem();
	// // 对应于org.apache.poi.hdf.extractor.WordDocument
	// fs.createDocument(is, "WordDocument");
	// fs.writeFilesystem(os);
	// os.close();
	// is.close();
	// }

	/**
	 * 把输入流里面的内容以UTF-8编码当文本取出。 不考虑异常，直接抛出
	 * 
	 * @param ises
	 * @return
	 * @throws IOException
	 */
	// private String getContent(InputStream... ises) throws IOException {
	// if (ises != null) {
	// StringBuilder result = new StringBuilder();
	// BufferedReader br;
	// String line;
	// for (InputStream is : ises) {
	// br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	// while ((line = br.readLine()) != null) {
	// result.append(line);
	// }
	// }
	// return result.toString();
	// }
	// return null;
	// }

	/**
	 * 设置对象默认属性值
	 * 
	 * @param templateDefEntry
	 * @throws Exception
	 */
	private void initDefultValue(TemplateDefEntry templateDefEntry)
			throws Exception {
		if (templateDefEntry != null) {
			if (templateDefEntry.getTemplateDef() != null) {
				if (StringUtil.isEmpty(templateDefEntry.getTemplateDef()
						.getId())) {
					templateDefEntry.getTemplateDef().setTenantId(
							this.getTenantId());
					templateDefEntry.getTemplateDef().setCreator(
							this.getUserId());
					templateDefEntry.getTemplateDef().setCreatedtime(
							DateUtil.getTs());
					templateDefEntry.getTemplateDef().setOperator(
							this.getUserId());
					templateDefEntry.getTemplateDef().setOperationtime(
							DateUtil.getTs());
					templateDefEntry.getTemplateDef().setTs(DateUtil.getTs());
					templateDefEntry.getTemplateDef().setDr(0);
				} else {
					templateDefEntry.getTemplateDef().setModifier(
							this.getUserId());
					templateDefEntry.getTemplateDef().setModifiedtime(
							DateUtil.getTs());
				}
			}
		}
	}

	public IImfbpFastDFSRpcService getiImfbpFastDFSRpcService() {
		return iImfbpFastDFSRpcService;
	}

	public void setiImfbpFastDFSRpcService(
			IImfbpFastDFSRpcService iImfbpFastDFSRpcService) {
		this.iImfbpFastDFSRpcService = iImfbpFastDFSRpcService;
	}

	public ITemplateService getiTemplateService() {
		return iTemplateService;
	}

	public void setiTemplateService(ITemplateService iTemplateService) {
		this.iTemplateService = iTemplateService;
	}

	public TemplateDefService getTemplateDefService() {
		return templateDefService;
	}

	public void setTemplateDefService(TemplateDefService templateDefService) {
		this.templateDefService = templateDefService;
	}

	public TemplateRulesDefService getTemplateRulesDefService() {
		return templateRulesDefService;
	}

	public void setTemplateRulesDefService(
			TemplateRulesDefService templateRulesDefService) {
		this.templateRulesDefService = templateRulesDefService;
	}

	public BasePublicService getBasePublicService() {
		return basePublicService;
	}

	public void setBasePublicService(BasePublicService basePublicService) {
		this.basePublicService = basePublicService;
	}

}
