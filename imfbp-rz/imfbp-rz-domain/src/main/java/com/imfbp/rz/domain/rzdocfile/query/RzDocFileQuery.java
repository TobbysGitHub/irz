package com.imfbp.rz.domain.rzdocfile.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzDocFileQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String id;
	//节点编码
	private String nodeCode;
	//单据id
	private String billId;
	//租户id
	private String tenantId;
	//创建人
	private String creator;
	//创建时间
	private String createdtime;
	//操作者
	private String operator;
	//操作时间
	private String operationtime;
	//修改人
	private String modifier;
	//修改时间
	private String modifiedtime;
	//时间戳
	private String ts;
	//删除标识
	private String dr;
	//文件名称
	private String filename;
	//文件描述
	private String description;
	//上传人
	private String uploader;
	//上传时间
	private String uploadtime;
	//文件类型
	private String filetype;
	//文件长度
	private Double filelength;
	//文件id
	private String fileFastdfsId;
	

	public void setId(String id){
		this.id =  id;
	}
	
	public String getId(){
		return id;
	}

	public void setNodeCode(String nodeCode){
		this.nodeCode =  nodeCode;
	}
	
	public String getNodeCode(){
		return nodeCode;
	}

	public void setBillId(String billId){
		this.billId =  billId;
	}
	
	public String getBillId(){
		return billId;
	}

	public void setTenantId(String tenantId){
		this.tenantId =  tenantId;
	}
	
	public String getTenantId(){
		return tenantId;
	}

	public void setCreator(String creator){
		this.creator =  creator;
	}
	
	public String getCreator(){
		return creator;
	}

	public void setCreatedtime(String createdtime){
		this.createdtime =  createdtime;
	}
	
	public String getCreatedtime(){
		return createdtime;
	}

	public void setOperator(String operator){
		this.operator =  operator;
	}
	
	public String getOperator(){
		return operator;
	}

	public void setOperationtime(String operationtime){
		this.operationtime =  operationtime;
	}
	
	public String getOperationtime(){
		return operationtime;
	}

	public void setModifier(String modifier){
		this.modifier =  modifier;
	}
	
	public String getModifier(){
		return modifier;
	}

	public void setModifiedtime(String modifiedtime){
		this.modifiedtime =  modifiedtime;
	}
	
	public String getModifiedtime(){
		return modifiedtime;
	}

	public void setTs(String ts){
		this.ts =  ts;
	}
	
	public String getTs(){
		return ts;
	}

	public void setDr(String dr){
		this.dr =  dr;
	}
	
	public String getDr(){
		return dr;
	}

	public void setFilename(String filename){
		this.filename =  filename;
	}
	
	public String getFilename(){
		return filename;
	}

	public void setDescription(String description){
		this.description =  description;
	}
	
	public String getDescription(){
		return description;
	}

	public void setUploader(String uploader){
		this.uploader =  uploader;
	}
	
	public String getUploader(){
		return uploader;
	}

	public void setUploadtime(String uploadtime){
		this.uploadtime =  uploadtime;
	}
	
	public String getUploadtime(){
		return uploadtime;
	}

	public void setFiletype(String filetype){
		this.filetype =  filetype;
	}
	
	public String getFiletype(){
		return filetype;
	}

	public void setFilelength(Double filelength){
		this.filelength =  filelength;
	}
	
	public Double getFilelength(){
		return filelength;
	}

	public void setFileFastdfsId(String fileFastdfsId){
		this.fileFastdfsId =  fileFastdfsId;
	}
	
	public String getFileFastdfsId(){
		return fileFastdfsId;
	}
}




 

