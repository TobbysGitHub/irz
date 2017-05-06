package com.imfbp.rz.domain.templaterulesdef;

import java.io.Serializable;

public class TemplateRulesDef implements Serializable {

	private static final long serialVersionUID = 1L;

	// 主键
	private String id;
	// 节点模板分配id
	private String templateNodeId;
	// 输入项id--指标id
	private String inputItemId;
	// 字段类型
	private String itemType;
	// 取数公式
	private String formula;
	// 取数说明
	private String formulaDetail;
	// 取数表名
	private String tableName;
	// VO限定名
	private String voName;
	// 字段名称
	private String itemName;
	// 时间戳
	private String ts;
	// 删除标识
	private Integer dr;
	// 指标编码
	private String itemBpaCode;
	// 指标名称
	private String itemBpaName;

	public String getItemBpaCode() {
		return itemBpaCode;
	}

	public void setItemBpaCode(String itemBpaCode) {
		this.itemBpaCode = itemBpaCode;
	}

	public String getItemBpaName() {
		return itemBpaName;
	}

	public void setItemBpaName(String itemBpaName) {
		this.itemBpaName = itemBpaName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setTemplateNodeId(String templateNodeId) {
		this.templateNodeId = templateNodeId;
	}

	public String getTemplateNodeId() {
		return templateNodeId;
	}

	public void setInputItemId(String inputItemId) {
		this.inputItemId = inputItemId;
	}

	public String getInputItemId() {
		return inputItemId;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormulaDetail(String formulaDetail) {
		this.formulaDetail = formulaDetail;
	}

	public String getFormulaDetail() {
		return formulaDetail;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setVoName(String voName) {
		this.voName = voName;
	}

	public String getVoName() {
		return voName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getTs() {
		return ts;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public Integer getDr() {
		return dr;
	}
}