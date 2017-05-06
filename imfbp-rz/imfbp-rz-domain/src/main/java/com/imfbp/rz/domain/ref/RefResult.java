package com.imfbp.rz.domain.ref;

import java.io.Serializable;
import java.util.List;

import com.platform.common.web.result.Result;

public class RefResult extends Result implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4073361972903372521L;
	/**
	 * 当前参照对应的元数据
	 */
	private RefMetaDataViewBean refMetaDataBean;
	/**
	 * Map类型的数据List，Map.key:字段属性，与元数据中显示字段的英文名一致，Map.value:字段对应的实际值
	 */
	private List<Object> datas;
	/**
	 * 参照分页信息
	 */
	private RefBasePage refBasePage;

	public RefBasePage getRefBasePage() {
		return refBasePage;
	}

	public void setRefBasePage(RefBasePage refBasePage) {
		this.refBasePage = refBasePage;
	}

	public RefMetaDataViewBean getRefMetaDataBean() {
		return refMetaDataBean;
	}

	public void setRefMetaDataBean(RefMetaDataViewBean refMetaDataBean) {
		this.refMetaDataBean = refMetaDataBean;
	}

	public List<Object> getDatas() {
		return datas;
	}

	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}

}
