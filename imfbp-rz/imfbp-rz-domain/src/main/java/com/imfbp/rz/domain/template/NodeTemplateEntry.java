package com.imfbp.rz.domain.template;

import java.io.Serializable;
import java.util.List;

/**
 * @Title : 节点模板实体
 * @Description : 节点分配模板时调用
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月9日 下午3:39:01
 */
public class NodeTemplateEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2113748261894179534L;

	// 节点编码
	private String nodeCode;
	// 分配模板id集合
	private List<String> addTemplateIds;

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public List<String> getAddTemplateIds() {
		return addTemplateIds;
	}

	public void setAddTemplateIds(List<String> addTemplateIds) {
		this.addTemplateIds = addTemplateIds;
	}

}
