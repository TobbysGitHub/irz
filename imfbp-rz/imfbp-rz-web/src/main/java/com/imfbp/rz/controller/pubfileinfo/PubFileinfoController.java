package com.imfbp.rz.controller.pubfileinfo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;









import com.platform.common.seculity.annotation.AccessSeculity;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;
import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.service.pubfileinfo.PubFileinfoService;
import com.imfbp.rz.util.ExcelWriterTool;
import com.imfbp.rz.util.ToolUtils;
import com.imfbp.rz.util.WebUtils;
import com.imfbp.rz.controller.pub.PubBaseContrl;


@RestController
public class PubFileinfoController extends PubBaseContrl{

	private PubFileinfoService pubFileinfoService;
	
	/**
	 *  跳转到PubFileinfo首页
	 * @param pubFileinfoQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "pubFileinfo/toPubFileinfoPage", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView toPubFileinfoPage(PubFileinfoQuery pubFileinfoQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = new Result();
		result.addDefaultModel("pkBill", pubFileinfoQuery.getPkBill()==null?"":pubFileinfoQuery.getPkBill());
		toVm(result, context, req);
		ModelAndView mv = new ModelAndView("pubFileinfo/pubFileinfo");
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
	@RequestMapping(value = "pubFileinfo/getPubFileinfoAll", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getPubFileinfoAll(PubFileinfoQuery pubFileinfoQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (pubFileinfoQuery == null) {
			pubFileinfoQuery = new PubFileinfoQuery();
		}
		List<PubFileinfo> pubFileinfoList = pubFileinfoService.getPubFileinfoAll(pubFileinfoQuery);
		return pubFileinfoList;
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
	@RequestMapping(value = "pubFileinfo/getPubFileinfoByPage", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getPubFileinfoByPage(PubFileinfoQuery pubFileinfoQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		if (pubFileinfoQuery == null) {
			pubFileinfoQuery = new PubFileinfoQuery();
		}
		GridResult<PubFileinfo> gridResult = pubFileinfoService.getPubFileinfoByPage(pubFileinfoQuery);
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
	@RequestMapping(value = "pubFileinfo/getPubFileinfoById", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object getPubFileinfoById(PubFileinfoQuery pubFileinfoQuery, HttpServletRequest req,HttpServletResponse resp, ModelMap context) {
		PubFileinfo pubFileinfo = pubFileinfoService.getPubFileinfoById(pubFileinfoQuery);
		return pubFileinfo;
	}

	/**
	 *  添加或修改
	 * @param mktsetlist
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "pubFileinfo/insertOrUpdate", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody Object insertOrUpdate(PubFileinfo pubFileinfo,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		PubFileinfo info=req.getSession().getAttribute("pkFile")==null?null:(PubFileinfo)req.getSession().getAttribute("pkFile");
		Result result = pubFileinfoService.insertOrUpdate(pubFileinfo,info);
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
	@RequestMapping(value = "pubFileinfo/deletePubFileinfoByBatchId", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deletePubFileinfoByBatchId(PubFileinfoQuery pubFileinfoQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		Result result = pubFileinfoService.deletePubFileinfoByBatchId(pubFileinfoQuery);
		return result;
	}
	
	/**
	 *  根据Id删除 (真正删除数据库数据)
	 * @param batchId
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "pubFileinfo/deletePubFileinfoById", method = {RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object deletePubFileinfoById(PubFileinfoQuery pubFileinfoQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		 return pubFileinfoService.deletePubFileinfoById(pubFileinfoQuery);
	}
	
	//文件上传
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "pubFileinfo/upload", method = {RequestMethod.POST})
	public @ResponseBody Object upload(MultipartFile[]  files,HttpServletRequest req,String pkBill,String pkFile, HttpServletResponse resp, ModelMap context) {
		if(files!=null && files.length!=0){
			String userId=getUserId();
			String tendId=getTenantId();
			String systemCode=getSysCode();
			List<String>systemMessage=new ArrayList<String>();
			systemMessage.add(userId);
			systemMessage.add(tendId);
			systemMessage.add(systemCode);
			systemMessage.add(getUserRealName());
			Map<String,Object>map= pubFileinfoService.saveFile(files,pkBill,pkFile,systemMessage);
			PubFileinfo info=(PubFileinfo) map.get("pkFile");
			req.getSession().setAttribute("pkFile", info);
			return map;
		}else{
			return null;
		}
	}
	/**
	 *  文件下载
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "pubFileinfo/downLoadFile", method = {RequestMethod.POST})
	public @ResponseBody void downLoadFile(HttpServletRequest req,String pkBill, HttpServletResponse resp, ModelMap context) {
		String id =req.getParameter("fileId");
		Result result=pubFileinfoService.downLoadFile(id);
		byte[] fileByte=(byte[])result.get("fastdfscontent");
	    // 读到流中
	    // 设置输出的格式
	    response.reset();
	    response.setContentType("bin");
	    // 循环取出流中的数据
	    try {
		    response.addHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(result.get("fileName").toString(), "UTF-8") + "\"");
	        response.getOutputStream().write(fileByte);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 *  批量文件下载
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "pubFileinfo/downLoadBatchFile", method = {RequestMethod.GET})
	public @ResponseBody void downLoadBatchFile(PubFileinfoQuery pubFileinfoQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
		if(pubFileinfoQuery == null) return;
		String ids[]=pubFileinfoQuery.getBatchId().split(",");
		if(ids != null && ids.length >0){
		for(String id:ids){
		Result result=pubFileinfoService.downLoadFile(id);
		byte[] fileByte=(byte[])result.get("fastdfscontent");
	    // 读到流中
	    // 设置输出的格式
	    response.reset();
	    response.setContentType("bin");
	    // 循环取出流中的数据
	    try {
		    response.addHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(result.get("fileName").toString(), "UTF-8") + "\"");
	        response.getOutputStream().write(fileByte);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
			}
		}
	}
	//导出
	@RequestMapping(value = "pubFileinfo/export", method = {RequestMethod.POST, RequestMethod.GET })
	public void export(PubFileinfoQuery pubFileinfoQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			if (pubFileinfoQuery == null) {
				pubFileinfoQuery = new PubFileinfoQuery();
			}
			OutputStream out;
			try {
				out = resp.getOutputStream();
				List<PubFileinfo>pubFileinfoList=new ArrayList<PubFileinfo>();
				List<PubFileinfo>all=pubFileinfoService.getPubFileinfoAll(new PubFileinfoQuery());
				if(StringUtils.isNotEmpty(pubFileinfoQuery.getBatchId())){
					String ids[]=pubFileinfoQuery.getBatchId().split(",");
					List<String>temp=new ArrayList<String>();
					for(String id:ids) temp.add(id);
					if(ToolUtils.isNotEmptyCollection(all)){
						for(PubFileinfo info:all){
							if(info != null && StringUtils.isNotEmpty(info.getPkFile())){
								if(temp.contains(info.getPkFile())) pubFileinfoList.add(info);
							}
						}
					}
				}else pubFileinfoList=all;
				ExcelWriterTool<PubFileinfo> ex = new ExcelWriterTool<PubFileinfo>();
				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("文件分类","filetype" );
				map.put("附件名称", "filename");
				map.put("文档类型", "doctype");
				map.put("上传人", "uploader");
				map.put("上传日期", "uploadtime");
				map.put("附件来源", "filesrc");
				resp.addHeader("Content-Disposition",
						"attachment;filename='"+WebUtils.encodeFileName("附件信息.xls", req));
				resp.setContentType("application/force-download");
				ex.exportExcel("附件信息", map, pubFileinfoList, out);
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	/**
	 *  删除文件
	 * @param mktsetlistQuery
	 * @param req
	 * @param resp
	 * @param context
	 * @return
	 */
	@AccessSeculity(code="PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "pubFileinfo/delete", method = {RequestMethod.POST})
	public @ResponseBody Object deteletAttachById(PubFileinfoQuery pubFileinfoQuery,HttpServletRequest req, HttpServletResponse resp, ModelMap context) {
			Result result= pubFileinfoService.deletePubFileinfoByCondition(pubFileinfoQuery);
			req.getSession().setAttribute("pkFile",result.getMap().get("pkFile"));
			return result;
	}
	public void setPubFileinfoService(PubFileinfoService pubFileinfoService) {
		this.pubFileinfoService = pubFileinfoService;
	}

}