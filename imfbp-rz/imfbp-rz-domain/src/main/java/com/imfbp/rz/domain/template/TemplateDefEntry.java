package com.imfbp.rz.domain.template;

import java.io.Serializable;
import java.util.List;

import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;

/**
 * 模板定义实体
 * 
 * @author Xinggh
 * @version 2016-11-25
 *
 */
public class TemplateDefEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4860067375145895537L;

	/**
	 * 模板定义属性
	 */
	private TemplateDef templateDef;
	/**
	 * 模板输入项定义列表
	 */
	private List<TemplateInputDef> templateInputDefLists;

	/**
	 * html内容
	 */
	private String htmlContent;

	public TemplateDef getTemplateDef() {
		return templateDef;
	}

	public void setTemplateDef(TemplateDef templateDef) {
		this.templateDef = templateDef;
	}

	public List<TemplateInputDef> getTemplateInputDefLists() {
		return templateInputDefLists;
	}

	public void setTemplateInputDefLists(
			List<TemplateInputDef> templateInputDefLists) {
		this.templateInputDefLists = templateInputDefLists;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

}
