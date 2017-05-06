package com.imfbp.rz.service.uploadfile;

import java.util.List;

import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.platform.common.web.result.Result;

public interface FileUploadService {

	/**
	 * 上传文件
	 * 
	 * @param fileinfo
	 *            文件对照信息
	 * @param filebytes
	 *            文件类容
	 * @return
	 */
	public Result uploadFile(PubFileinfo fileinfo, byte[] filebytes);
	
	 public Result getFileURL(String fileFastdfsId) throws Exception;

	/**
	 * 删除文件
	 * 
	 * @param pkfile
	 *            附件主键
	 * @param fileFastdfsId
	 *            服务器上的文件id
	 * @return
	 * @throws Exception
	 */
	public Result deleteFile(String pkfile, String fileFastdfsId)
			throws Exception;

	/**
	 * 获取指定单据附件
	 * 
	 * @param billpk
	 *            业务主键
	 * @return
	 * @throws Exception
	 */
	public List<PubFileinfo> getFileList(String billpk) throws Exception;
}
