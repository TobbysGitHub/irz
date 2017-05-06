package com.imfbp.rz.service.uploadfile.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.imfbp.fastdfs.rpc.api.fastdfs.domain.RpcDfsFileInfo;
import com.imfbp.fastdfs.rpc.api.fastdfs.domain.query.RpcDfsFileInfoQuery;
import com.imfbp.fastdfs.rpc.api.fastdfs.service.IImfbpFastDFSRpcService;
import com.imfbp.rz.dao.pubfileinfo.PubFileinfoDao;
import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;
import com.imfbp.rz.pub.IDFSConst;
import com.imfbp.rz.service.imfbpm.PropertyUtil;
import com.imfbp.rz.service.uploadfile.FileUploadService;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.Result;

@Component("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {
	private PubFileinfoDao pubFileinfoDao;
	private PrimaryKeyUtil primaryKeyUtil;

	@Autowired
	private IImfbpFastDFSRpcService iImfbpFastDFSRpcServicel;

	@Transactional
	@Override
	public Result uploadFile(PubFileinfo fileinfo, byte[] filebytes) {

		String pkfile = primaryKeyUtil.getPrimaryKey();

		Result result = new Result();
		try {
			result.setSuccess(false);
			// 组装RPC文件元信息
			RpcDfsFileInfo rpcDfsFileInfo = new RpcDfsFileInfo();
			rpcDfsFileInfo.setTenantId(fileinfo.getTenantId());
			rpcDfsFileInfo.setSystemCode(fileinfo.getSystemCode());
			rpcDfsFileInfo.setNodeCode(fileinfo.getFuncode());
			rpcDfsFileInfo.setUploader(fileinfo.getUploader());
			rpcDfsFileInfo.setUploadTime(fileinfo.getUploadtime());
			rpcDfsFileInfo.setFileName(fileinfo.getFilename());
			rpcDfsFileInfo.setFileSize(fileinfo.getFilelength());
			rpcDfsFileInfo.setFileType(fileinfo.getFiletype());
			rpcDfsFileInfo.setDescription(fileinfo.getDescription());
			// 上传文件
			Result uploadResult = iImfbpFastDFSRpcServicel.uploadFile(
					rpcDfsFileInfo, filebytes);
			if (uploadResult != null && uploadResult.isSuccess()) {
				// 上传成功，设置文件属性
				rpcDfsFileInfo = (RpcDfsFileInfo) uploadResult
						.get("dfsFileInfo");
				if (rpcDfsFileInfo!=null ) {
					fileinfo.setFileFastdfsGroup(rpcDfsFileInfo.getFileFastdfsGroup());
					fileinfo.setFileFastdfsId(rpcDfsFileInfo.getFileFastdfsId());
				}
				fileinfo.setPkFile(pkfile);
				// 存储文件对照关系
				pubFileinfoDao.insertPubFileinfo(fileinfo);
				result.addDefaultModel("file", fileinfo);
				result.addDefaultModel("domain", PropertyUtil
						.getPropertyByKey(IDFSConst.IDFS_DOMAIN_NAME));
				result.setSuccess(true);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage(), e);
			result.setSuccess(false);
			result.setErrorMessage("上传文件失败");
		}
		return result;
	}

	public void setPrimaryKeyUtil(PrimaryKeyUtil primaryKeyUtil) {
		this.primaryKeyUtil = primaryKeyUtil;
	}

	public void setPubFileinfoDao(PubFileinfoDao pubFileinfoDao) {
		this.pubFileinfoDao = pubFileinfoDao;
	}

	@Override
	public Result getFileURL(String fileFastdfsId) throws Exception {
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(fileFastdfsId);
		// 调RPC接口获取文件URL
		Result result = iImfbpFastDFSRpcServicel
				.getFileTokenURL(rpcDfsFileInfoQuery);
		if (result == null) {
			result = new Result();
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public Result deleteFile(String pkfile, String fileFastdfsId)
			throws Exception {
		Result result = new Result();
		try {
			result.setSuccess(false);
			// 删对照信息
			PubFileinfoQuery info = new PubFileinfoQuery();
			info.setPkFile(pkfile);
			pubFileinfoDao.deletePubFileinfoById(info);
			RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
			rpcDfsFileInfoQuery.setFileFastdfsId(fileFastdfsId);
			Result deleteResult = iImfbpFastDFSRpcServicel
					.deleteFile(rpcDfsFileInfoQuery);
			if (deleteResult == null || !deleteResult.isSuccess()) {
				throw new Exception(deleteResult.getErrorMessage());
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			Logger.getLogger(getClass()).error(
					"删除文件出现异常，异常信息：" + e.getMessage(), e);
			result.setErrorMessage("删除文件失败");
		}
		return result;
	}

	@Override
	public List<PubFileinfo> getFileList(String billpk) throws Exception {
		PubFileinfoQuery info = new PubFileinfoQuery();
		info.setPkBill(billpk);
		List<PubFileinfo> fileinfoList = pubFileinfoDao.getPubFileinfoAll(info);
		return fileinfoList;
	}

	public IImfbpFastDFSRpcService getiImfbpFastDFSRpcServicel() {
		return iImfbpFastDFSRpcServicel;
	}

	public void setiImfbpFastDFSRpcServicel(
			IImfbpFastDFSRpcService iImfbpFastDFSRpcServicel) {
		this.iImfbpFastDFSRpcServicel = iImfbpFastDFSRpcServicel;
	}

}
