package com.imfbp.rz.domain.templateinputdef.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class TemplateInputDefQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String id;
	//输入项编码
	private String itemCode;
	//输入项名称
	private String itemName;
	//模板id
	private String templateId;
	//时间戳
	private String ts;
	//删除标识
	private Integer dr;
	

	public void setId(String id){
		this.id =  id;
	}
	
	public String getId(){
		return id;
	}

	public void setItemCode(String itemCode){
		this.itemCode =  itemCode;
	}
	
	public String getItemCode(){
		return itemCode;
	}

	public void setItemName(String itemName){
		this.itemName =  itemName;
	}
	
	public String getItemName(){
		return itemName;
	}

	public void setTemplateId(String templateId){
		this.templateId =  templateId;
	}
	
	public String getTemplateId(){
		return templateId;
	}

	public void setTs(String ts){
		this.ts =  ts;
	}
	
	public String getTs(){
		return ts;
	}

	public void setDr(Integer dr){
		this.dr =  dr;
	}
	
	public Integer getDr(){
		return dr;
	}
}




 

