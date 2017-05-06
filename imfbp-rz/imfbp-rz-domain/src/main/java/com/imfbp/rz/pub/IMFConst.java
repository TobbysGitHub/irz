package com.imfbp.rz.pub;

public interface IMFConst {

	//审批 结果字段
	public static String AUDITRESULST = "audit_result";
	//同意数量
	public static String AGREENUM = "agreeNum";
	//审批时 当前活动 判断 agreeNum是否清空
	public static String CURRENTACTIVITY = "currentactivity";
	//变量的方位
	public static String GLOBALEVARIABLESCOPE = "GLOBAL";

	public static String LOCALEVARIABLESCOPE = "LOCAL";
	//没有定义 流程时 返回的异常信息 包含的字段
	public static String NOTSETFLOWFLAG="NOT_PROCESSDEF_ID";
	
	
	/**
	 * 审批结果
	 */
	// public static String AGREE="同意";
	// public static String DISAGREE="不同意";
	// public static String REJECT="驳回";
	public static String AGREE = "tongyi";
	public static String DISAGREE = "butongyi";
	public static String REJECT = "bohui";
	
	public static String COMMENT_AGREE = "同意:";
	public static String COMMENT_DISAGREE = "不同意:";
	public static String COMMENT_REJECT = "驳回:";
	public static String COMMENT_WITHDRAW = "弃审:";
	public static String COMMENT_FILD = "comments";
	public static String COMMENT_AGREE_FLAG="###IMFAGREE***";
	public static String COMMENT_DISAGREE_FLAG="###IMFDISAGREE***";
	
	
	public   static String AUDITSUCCESSMESSAGE = "审批成功";
	public   static String STARTSUCCESSMESSAGE = "启动成功";
	public   static String WITHDRAWSUCCESSMESSAGE = "弃审成功";
	public static String REJECTSUCCESSMESSAGE = "弃审成功";
	

}
