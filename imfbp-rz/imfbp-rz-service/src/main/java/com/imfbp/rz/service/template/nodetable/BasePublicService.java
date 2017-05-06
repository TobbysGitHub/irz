package com.imfbp.rz.service.template.nodetable;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.imfbp.rz.domain.template.FormulaEntry;

/**
 * @Title : 基础公共服务
 * @Description : 提供节点与其对应数据表、公式数据
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月20日 下午3:16:49
 */
@Service
@Component("basePublicService")
public class BasePublicService {

	/**
	 * 功能节点对应表名映射关系
	 */
	private Map<String, List<String>> funNodeTableCodeMap;
	/**
	 * 公共基础服务数据元数据表
	 */
	private List<String> publicMetadataList;

	/**
	 * 公式集合
	 */
	private List<FormulaEntry> formulaEntryList;

	public List<FormulaEntry> getFormulaEntryList() {
		return formulaEntryList;
	}

	public void setFormulaEntryList(List<FormulaEntry> formulaEntryList) {
		this.formulaEntryList = formulaEntryList;
	}

	public Map<String, List<String>> getFunNodeTableCodeMap() {
		return funNodeTableCodeMap;
	}

	public void setFunNodeTableCodeMap(
			Map<String, List<String>> funNodeTableCodeMap) {
		this.funNodeTableCodeMap = funNodeTableCodeMap;
	}

	/**
	 * 根据功能节点编码获取该节点对应的数据表集合
	 * 
	 * @param nodeCode
	 * @return 数据表表名编码
	 */
	public String[] getTableCodesByNodeCode(String nodeCode) {
		if (funNodeTableCodeMap != null && funNodeTableCodeMap.size() > 0) {
			List<String> list = funNodeTableCodeMap.get(nodeCode);
			if (list != null && list.size() > 0) {
				return list.toArray(new String[0]);
			}
		}
		return null;
	}

	public List<String> getPublicMetadataList() {
		return publicMetadataList;
	}

	public void setPublicMetadataList(List<String> publicMetadataList) {
		this.publicMetadataList = publicMetadataList;
	}

}
