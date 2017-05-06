package com.imfbp.rz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.pub.IDFSConst;
import com.imfbp.rz.service.imfbpm.PropertyUtil;
import com.imfbp.rz.service.uploadfile.FileUploadService;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.spring.mvc.controller.BaseController;
import com.platform.common.web.result.Result;

@RestController
public class UploadFileController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(UploadFileController.class);

	private FileUploadService fileUploadService;

	/**
	 * 上传附件
	 * 
	 * @param file
	 *            单个附件 附件内容
	 * @param pk
	 *            业务主键
	 * @param req
	 *            请求对象
	 * @param resp
	 *            返回对象
	 * @return
	 */
	@RequestMapping(value = "file/upload", method = { RequestMethod.POST })
	public Result uploadFile(@RequestParam("file") CommonsMultipartFile file,
			@RequestParam("pk") String pk, HttpServletRequest req,
			HttpServletResponse resp) {

		PubFileinfo fileinfo = new PubFileinfo();

		fileinfo.setPkBill(pk);
		String filename = file.getOriginalFilename();
		fileinfo.setFilename(filename);

		int typeindex = filename.lastIndexOf('.');
		if (typeindex > 0) {
			fileinfo.setDescription(filename.substring(0, typeindex));
			fileinfo.setFiletype(filename.substring(typeindex+1));
		} else {
			fileinfo.setDescription(filename);
		}
		fileinfo.setUploader(this.getUserRealName());
		fileinfo.setUploadtime(DateUtil.getTs());
		fileinfo.setFilelength(file.getSize());
		fileinfo.setTenantId(this.getTenantId());
		fileinfo.setSystemCode(this.getSysCode());

		byte[] filebytes = file.getBytes();

		Result result = fileUploadService.uploadFile(fileinfo, filebytes);
		return result;
	}

	/**
	 * 删除附件
	 * 
	 * @param pk
	 *            附件主键
	 * @param fileFastdfsId
	 *            服务器上的文件id
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "file/delete", method = { RequestMethod.POST })
	public Result deleteFile(@RequestParam("pk") String pk,
			@RequestParam("fileFastdfsId") String fileFastdfsId,
			HttpServletRequest req, HttpServletResponse resp) {
		Result result = new Result();
		try {
			result = fileUploadService.deleteFile(pk, fileFastdfsId);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorMessage("删除附件失败!");
		}

		return result;
	}

	/**
	 * 下载附件
	 * 
	 * @param fileFastdfsId
	 *            FastDFS文件ID
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "file/getFileURL", method = { RequestMethod.POST })
	public Result getFileURL(
			@RequestParam("fileFastdfsId") String fileFastdfsId,
			HttpServletRequest req, HttpServletResponse resp) {

		Result result = new Result();
		try {
			result = fileUploadService.getFileURL(fileFastdfsId);
		} catch (Exception e) {
			logger.error("获取文件URL出现异常,异常信息：" + e.getMessage(), e);
			result.setErrorMessage("获取文件URL出现异常,异常信息：" + e.getMessage());
		}
		return result;
	}

	/**
	 * 查询附件信息
	 * 
	 * @param pk
	 *            业务主键
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "file/query", method = { RequestMethod.POST })
	public Result queryFile(@RequestParam("pk") String pk,
			HttpServletRequest req, HttpServletResponse resp) {

		Result result = new Result();
		try {
			List<PubFileinfo> fileVos = fileUploadService.getFileList(pk);
			result.setSuccess(true);
			result.addDefaultModel("datas", fileVos);
			result.addDefaultModel("domain",
					PropertyUtil.getPropertyByKey(IDFSConst.IDFS_DOMAIN_NAME));
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorMessage("查询附件列表失败");
		}

		return result;
	}

	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

}
