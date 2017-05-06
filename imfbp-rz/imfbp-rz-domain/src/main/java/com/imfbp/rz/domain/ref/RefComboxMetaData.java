package com.imfbp.rz.domain.ref;

import java.util.Map;

public class RefComboxMetaData {

	private String comboxName;
	private String comboxShowName;
	private String enumName;
	private Map<Object, Object> keyValueMap;

	public String getComboxName() {
		return comboxName;
	}

	public void setComboxName(String comboxName) {
		this.comboxName = comboxName;
	}

	public Map<Object, Object> getKeyValueMap() {
		return keyValueMap;
	}

	public void setKeyValueMap(Map<Object, Object> keyValueMap) {
		this.keyValueMap = keyValueMap;
	}

	public String getComboxShowName() {
		return comboxShowName;
	}
	
	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	public void setComboxShowName(String comboxShowName) {
		this.comboxShowName = comboxShowName;
	}

}
