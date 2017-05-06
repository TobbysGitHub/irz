package com.imfbp.rz.domain.tmpl;

import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;

public class TmplFormula extends TemplateInputDef {

	private static final long serialVersionUID = 1L;

	private String formula;

	private String nodeCode;

	/** 合同模板主键 **/
	private String templateDefId;

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getTemplateDefId() {
		return templateDefId;
	}

	public void setTemplateDefId(String templateDefId) {
		this.templateDefId = templateDefId;
	}

}
