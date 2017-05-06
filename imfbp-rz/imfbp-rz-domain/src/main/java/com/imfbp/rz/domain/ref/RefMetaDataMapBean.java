package com.imfbp.rz.domain.ref;

import java.io.Serializable;
import java.util.Map;

public class RefMetaDataMapBean implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993310042915119535L;
	/**
	 * 参照元数据
	 */
	private Map<String, RefMetaDataBean> refMetaDataBeanMaps;

	public Map<String, RefMetaDataBean> getRefMetaDataBeanMaps() {
		return refMetaDataBeanMaps;
	}

	public void setRefMetaDataBeanMaps(
			Map<String, RefMetaDataBean> refMetaDataBeanMaps) {
		this.refMetaDataBeanMaps = refMetaDataBeanMaps;
	}

}
