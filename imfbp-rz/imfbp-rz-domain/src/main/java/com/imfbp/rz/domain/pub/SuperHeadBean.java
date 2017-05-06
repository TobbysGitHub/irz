package com.imfbp.rz.domain.pub;

public abstract class SuperHeadBean extends SuperBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3291933926396914591L;

	public String instanceIdFieldName() {
		return "flowinstanceid";
	}

	public abstract String billNoFieldName();

	public String billMakerFieldName() {
		return "billmaker";
	}

	public String billDateName() {
		return "billdate";
	}

	public String approverFieldName() {
		return "approveid";
	}

	public String approvedateFieldName() {
		return "approvedate";
	}

	public String approveNoteFieldName() {
		return "approvenote";
	}

	public String approveStatusFieldName() {
		return "approvestatus";
	}

	public String modifierFieldName() {
		return "modifor";
	}

	public String modifitimeFieldName() {
		return "modifydatetime";
	}

}
