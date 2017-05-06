package com.imfbp.rz.domain.pub;

import java.io.Serializable;

public abstract class SuperBean implements Cloneable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8040663010833783935L;

	public void setAttribute(String attribute, Object value) {
		BeanHelper.setProperty(this, attribute, value);
	}

	public Object getAttribute(String attribute) {
		return BeanHelper.getProperty(this, attribute);
	}
	
	public abstract  String getPkFieldName();

	public abstract String getTableName();
	
	public String getPrimaryKey(){
		return (String) getAttribute(getPkFieldName());
	}
	

}
