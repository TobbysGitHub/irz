package com.imfbp.rz.service.pubfileinfo.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.imfbp.fastdfs.rpc.api.fastdfs.domain.RpcDfsFileInfo;
import com.imfbp.fastdfs.rpc.api.fastdfs.domain.query.RpcDfsFileInfoQuery;
import com.imfbp.fastdfs.rpc.api.fastdfs.service.IImfbpFastDFSRpcService;
import com.imfbp.rz.constant.RZConstants;
import com.imfbp.rz.dao.pubfileinfo.PubFileinfoDao;
import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;
import com.imfbp.rz.service.pubfileinfo.PubFileinfoService;
import com.imfbp.rz.util.Html2PDFUtils;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;





@Component("pubFileinfoService")
public class PubFileinfoServiceImpl implements PubFileinfoService{


	private PubFileinfoDao pubFileinfoDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	@Autowired
	private IImfbpFastDFSRpcService iImfbpFastDFSRpcService;

	/**
	 * 添加
	 * @param pubFileinfo
	 * @return
	 */
	@Override
	public void insertPubFileinfo(PubFileinfo pubFileinfo,PubFileinfo info){
		if(info != null){
			pubFileinfo.setPkFile(info.getPkFile());
			pubFileinfo.setPkBill(info.getPkBill());
			pubFileinfo.setFilename(info.getFilename());
			pubFileinfo.setFileFastdfsId(info.getFileFastdfsId());
			pubFileinfo.setFilelength(info.getFilelength());
			pubFileinfo.setDoctype(info.getDoctype());
			pubFileinfo.setUploader(info.getUploader());
			pubFileinfo.setUploadtime(info.getUploadtime());
		}else{
		String pk = primaryKeyUtil.getPrimaryKey();
		pubFileinfo.setPkFile(pk);
		}
		pubFileinfoDao.insertPubFileinfo(pubFileinfo);	
	}
	
	/**
	 * 批量添加
	 * @param List<pubFileinfo>
	 * @return
	 */
	public void insertBatchPubFileinfo(List<PubFileinfo> pubFileinfoList){
		if(pubFileinfoList != null){
			for(int i=0;i<pubFileinfoList.size();i++){
				String pk = primaryKeyUtil.getPrimaryKey();
				pubFileinfoList.get(i).setPkFile(pk);
			}
			pubFileinfoDao.insertBatchPubFileinfo(pubFileinfoList);
		}
	}
	
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deletePubFileinfoById(PubFileinfoQuery pubFileinfoQuery){
		try {
			PubFileinfo pubFileinfo =pubFileinfoDao.getPubFileinfoById(pubFileinfoQuery);
			String fileStoreId=pubFileinfo.getFileFastdfsId();
			if(StringUtils.isNotEmpty(fileStoreId)){
			RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
			rpcDfsFileInfoQuery.setBatchId(fileStoreId);
			iImfbpFastDFSRpcService.deleteFile(rpcDfsFileInfoQuery);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pubFileinfoDao.deletePubFileinfoById(pubFileinfoQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public Result deletePubFileinfoByCondition(PubFileinfoQuery pubFileinfoQuery){
		Result result = new Result();
		result.setSuccess(false);
		try {
			PubFileinfo pubFileinfo =pubFileinfoDao.getPubFileinfoById(pubFileinfoQuery);
			String fileStoreId=pubFileinfo.getFileFastdfsId();
			RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
			rpcDfsFileInfoQuery.setBatchId(fileStoreId);
			iImfbpFastDFSRpcService.deleteFile(rpcDfsFileInfoQuery);
			pubFileinfoDao.clearFile(pubFileinfoQuery);
			//将session中的临时对象置空
			result.getMap().put("pkFile", null);
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param pubFileinfoQuery
	 * @return
	 */	
	@Override
	public Result deletePubFileinfoByBatchId(PubFileinfoQuery pubFileinfoQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = pubFileinfoQuery.getBatchId().split(",");
			for(String pkFile:batchIdArr){
				PubFileinfoQuery query =new PubFileinfoQuery();
				query.setPkFile(pkFile);
				PubFileinfo info=pubFileinfoDao.getPubFileinfoById(query);
				if(info != null && StringUtils.isNotEmpty(info.getFilename())){
					String fileStoreId=info.getFileFastdfsId();
					RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
					rpcDfsFileInfoQuery.setBatchId(fileStoreId);
					try {
						iImfbpFastDFSRpcService.deleteFile(rpcDfsFileInfoQuery);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			data.put("batchId1",batchIdArr);
			boolean flat = pubFileinfoDao.deletePubFileinfoByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param pubFileinfo
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(PubFileinfo pubFileinfo,PubFileinfo info) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(pubFileinfo!=null){
				if(StringUtil.isNotEmpty(pubFileinfo.getPkFile())){
					updatePubFileinfoById(pubFileinfo,info);
				}else{
					insertPubFileinfo(pubFileinfo,info);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(pubFileinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param pubFileinfo
	 * @return
	 */
	@Override
	public boolean updatePubFileinfoById(PubFileinfo pubFileinfo,PubFileinfo info){
		String pkFile=pubFileinfo.getPkFile();
		if(info != null){
			if(StringUtils.isNotEmpty(info.getPkFile()) && pkFile.equals(info.getPkFile())){
				pubFileinfo.setPkBill(info.getPkBill());
				pubFileinfo.setFilename(info.getFilename());
				pubFileinfo.setFileFastdfsId(info.getFileFastdfsId());
				pubFileinfo.setFilelength(info.getFilelength());
				pubFileinfo.setDoctype(info.getDoctype());
				pubFileinfo.setUploader(info.getUploader());
				pubFileinfo.setUploadtime(info.getUploadtime());
			}
		}else{
			pubFileinfo.setPkBill(null);
			pubFileinfo.setFilename(null);
			pubFileinfo.setFileFastdfsId(null);
			pubFileinfo.setFilelength(null);
			pubFileinfo.setDoctype(null);
			pubFileinfo.setUploader(null);
			pubFileinfo.setUploadtime(null);
		}
		return pubFileinfoDao.updatePubFileinfoById(pubFileinfo);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updatePubFileinfoByCondition(PubFileinfoQuery record,PubFileinfoQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return pubFileinfoDao.updatePubFileinfoByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param pubFileinfoQuery
	 * @return
	 */
	public Result updatePubFileinfoByBatchId(List<PubFileinfo> pubFileinfoList){
		Result result = new Result(false);
		try {
			boolean flag = pubFileinfoDao.updatePubFileinfoByBatchId(pubFileinfoList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public PubFileinfo getPubFileinfoById(PubFileinfoQuery pubFileinfoQuery){
		return pubFileinfoDao.getPubFileinfoById(pubFileinfoQuery);
	}
	
	/**
	 * 查询所有
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public List<PubFileinfo> getPubFileinfoAll(PubFileinfoQuery pubFileinfoQuery){
		return pubFileinfoDao.getPubFileinfoAll(pubFileinfoQuery);
	}
	
	/**
	 * 分页查询
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public GridResult<PubFileinfo> getPubFileinfoByPage(PubFileinfoQuery pubFileinfoQuery){
		//如果排序的字段是空或者空字符串
		if(pubFileinfoQuery!=null&&StringUtils.isBlank(pubFileinfoQuery.getSort())){
			pubFileinfoQuery.setSort("pk_file");
			pubFileinfoQuery.setOrder("desc");;
		}
		int total = pubFileinfoDao.getPubFileinfoByPageCount(pubFileinfoQuery);
		PaginatedList<PubFileinfo> pubFileinfoPageList = new MysqlPaginatedArrayList<PubFileinfo>(pubFileinfoQuery,total);
		List<PubFileinfo> pubFileinfoList = pubFileinfoDao.getPubFileinfoByPage(pubFileinfoQuery);
		pubFileinfoPageList.addAll(pubFileinfoList);
		GridResult<PubFileinfo> result = new GridResult<PubFileinfo>(pubFileinfoPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param pubFileinfoQuery
	 * @return
	 */
	@Override
	public int getPubFileinfoByPageCount(PubFileinfoQuery pubFileinfoQuery){
		return pubFileinfoDao.getPubFileinfoByPageCount(pubFileinfoQuery);
	}

	public void setPubFileinfoDao(PubFileinfoDao  pubFileinfoDao){
		this.pubFileinfoDao = pubFileinfoDao;
	}
    //下载文件
	@Override
	public Result downLoadFile(String id) {
		// TODO Auto-generated method stub
		PubFileinfoQuery pubFileinfoQuery=new PubFileinfoQuery();
		pubFileinfoQuery.setPkFile(id);
		PubFileinfo pubFileinfo=pubFileinfoDao.getPubFileinfoById(pubFileinfoQuery);
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery =new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(pubFileinfo.getFileFastdfsId());
		Result result=null;
		try {
			result=iImfbpFastDFSRpcService.downloanFile(rpcDfsFileInfoQuery);
//			如果当前是自动生成的单据文档，内容为html格式，需要转换
			if (result != null && result.isSuccess()) {
				if(pubFileinfo != null 
					&& RZConstants.HTML_FILE_TYPE.equals(pubFileinfo.getDescription()) 
					&& "2".equals(pubFileinfo.getFilesrc()) 
					&& RZConstants.FILE_TYPE_PDF.equals(pubFileinfo.getDoctype())){
					Object obj = result.get("fastdfscontent");
					if (obj != null) {
						String htmlData = new String((byte[])obj,RZConstants.CHARSET_UTF_8);
						byte[] filebytes = Html2PDFUtils.html2PDF(htmlData);
						result.addDefaultModel("fastdfscontent", filebytes);
					}
				}
				result.addDefaultModel("fileName",pubFileinfo.getFilename());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.imfbp.rz.service.pubfileinfo.PubFileinfoService#saveFile(org.springframework.web.multipart.MultipartFile[], java.lang.String)
	 */
	//上传文件
	@Override
	public Map<String, Object> saveFile(MultipartFile[] files, String pkBill,String pkFile,List<String>systemMessage) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		PubFileinfo pubFileinfo=new PubFileinfo();
		MultipartFile file=files[0];
		String fileName=file.getOriginalFilename();
		String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
		map.put("fileName", fileName);
		if(file.getSize()>5242880){
			map.put("message",fileName+"文件过大无法上传");
			map.put("uploadResult", false);
			return map;
		}
		//判断文件是否重复,重复文件
		PubFileinfoQuery query=new PubFileinfoQuery();
		query.setPkBill(pkBill);
		query.setFilename(fileName);
		List<PubFileinfo> existlist=pubFileinfoDao.getPubFileinfoAll(query);
		if(existlist!=null && existlist.size()!=0){
			
			
		}
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			RpcDfsFileInfo rpcDfsFileInfo = new RpcDfsFileInfo();
			rpcDfsFileInfo.setFileName(fileName);
			rpcDfsFileInfo.setFileType(fileType);
			rpcDfsFileInfo.setFileSize(file.getSize());
			Result uploadResult;
			uploadResult = iImfbpFastDFSRpcService.uploadFile(rpcDfsFileInfo,file.getBytes());
			RpcDfsFileInfo dfsFileInfo = null;
			if (uploadResult != null) {
				map.put("uploadResult", uploadResult.isSuccess());
				dfsFileInfo = (RpcDfsFileInfo) uploadResult.get("dfsFileInfo");
				RpcDfsFileInfoQuery rpcDfsFileInfoQuery =new RpcDfsFileInfoQuery();
				rpcDfsFileInfoQuery.setFileFastdfsId(dfsFileInfo.getFileFastdfsId());
				Result result=iImfbpFastDFSRpcService.getFileTokenURL(rpcDfsFileInfoQuery);
				map.put("fileUrl",result.get("fastdfsfileurl"));
				//pubFileinfo.setAttachContentType(getContentTypeByFileType(fileType));
				pubFileinfo.setPkBill(pkBill);
				if(pkFile != null && !pkFile.equals("") && !pkFile.equals("null")) pubFileinfo.setPkFile(pkFile);
				else pubFileinfo.setPkFile(primaryKeyUtil.getPrimaryKey());
				pubFileinfo.setFilename(fileName);
				pubFileinfo.setFileFastdfsId(dfsFileInfo.getFileFastdfsId());
				if(ToolUtils.isNotEmptyCollection(systemMessage)){
					pubFileinfo.setUploader(systemMessage.get(0));
					//pubFileinfo.setTenantId(systemMessage.get(1));
					//pubFileinfo.setSystemCode(systemMessage.get(2));
				}
				pubFileinfo.setDoctype(fileType);
				pubFileinfo.setFilelength(file.getSize());
				pubFileinfo.setUploadtime(format.format(date));
				//pubFileinfo.setAttachTempName(dfsFileInfo.getFileName());
				//pubFileinfo.setAttachUrl(map.get("fileUrl").toString());
				map.put("fileId", pubFileinfo.getPkFile());
				//暂时不保存
				map.put("pkFile", pubFileinfo);
				map.put("message","文件上传成功");
				map.put("uploader",systemMessage.get(3));
				map.put("doctype",pubFileinfo.getDoctype());
				map.put("uploadtime", pubFileinfo.getUploadtime());
			}
		} catch (Exception e) {
			map.put("uploadResult", false);
			map.put("message",fileName+"上传失败");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> saveFile(MultipartFile[] files) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		MultipartFile file=files[0];
		String fileName=file.getOriginalFilename();
		String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
		map.put("fileName", fileName);
		if(file.getSize()>5242880){
			map.put("message",fileName+"文件过大无法上传");
			map.put("uploadResult", false);
			return map;
		}
		try {
			RpcDfsFileInfo rpcDfsFileInfo = new RpcDfsFileInfo();
			rpcDfsFileInfo.setFileName(fileName);
			rpcDfsFileInfo.setFileType(fileType);
			rpcDfsFileInfo.setFileSize(file.getSize());
			Result uploadResult;
			uploadResult = iImfbpFastDFSRpcService.uploadFile(rpcDfsFileInfo,file.getBytes());
			RpcDfsFileInfo dfsFileInfo = null;
			if (uploadResult != null) {
				map.put("uploadResult", uploadResult.isSuccess());
				dfsFileInfo = (RpcDfsFileInfo) uploadResult.get("dfsFileInfo");
				RpcDfsFileInfoQuery rpcDfsFileInfoQuery =new RpcDfsFileInfoQuery();
				rpcDfsFileInfoQuery.setFileFastdfsId(dfsFileInfo.getFileFastdfsId());
				Result result=iImfbpFastDFSRpcService.getFileTokenURL(rpcDfsFileInfoQuery);
				map.put("fileUrl",result.get("fastdfsfileurl"));
				map.put("message","文件上传成功");
				map.put("fileFastdfsId", rpcDfsFileInfoQuery.getFileFastdfsId());
			}
		} catch (Exception e) {
			map.put("uploadResult", false);
			map.put("message",fileName+"上传失败");
			e.printStackTrace();
		}
		return map;
	}
	
}