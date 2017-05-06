package com.imfbp.rz.domain.template;

import java.io.Serializable;

/**
 * @Title : 公式实体
 * @Description : 模板取数公式实体
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月20日 下午2:57:45
 */
public class FormulaEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2413031725917475335L;

	// 公式编码-唯一标识
	private String formulaCode;
	// 公式内容
	private String formulaText;
	// 公式释义
	private String formulaNote;
	// 公式名称-对应的翻译
	private String formulaName;
	// 参数个数
	private Integer parameterNumber;

	public String getFormulaCode() {
		return formulaCode;
	}

	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}

	public String getFormulaText() {
		return formulaText;
	}

	public void setFormulaText(String formulaText) {
		this.formulaText = formulaText;
	}

	public String getFormulaNote() {
		return formulaNote;
	}

	public void setFormulaNote(String formulaNote) {
		this.formulaNote = formulaNote;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public Integer getParameterNumber() {
		return parameterNumber;
	}

	public void setParameterNumber(Integer parameterNumber) {
		this.parameterNumber = parameterNumber;
	}

}
