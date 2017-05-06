package com.imfbp.rz.domain.jstreenode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Title : 树形结构数据结构
 * @Description :
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月15日 下午1:56:53
 */
public class JsTreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6676039919639760180L;

	private String id;

	private String parent;

	private String code;

	private String text;

	private String type;

	private boolean children;

	private HashMap<String, Object> state = new HashMap<String, Object>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setState(HashMap<String, Object> state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isChildren() {
		return children;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}

	public HashMap<String, Object> getState() {
		return state;
	}

}
