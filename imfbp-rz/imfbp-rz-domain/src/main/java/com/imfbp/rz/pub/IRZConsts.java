package com.imfbp.rz.pub;

/**
 * @author tuxinlin 系统常量类
 */
/**
 * @author tuxinlin
 *
 */
public interface IRZConsts {
	public final static String BILLDATE = "billdate";
	public final static String BILLDATES = "billdates";
	public final static String BILLDATEE = "billdatee";
	/**
	 * 小贷分库前缀
	 */
	public final static String DBPRE = "rz_";
	/**
	 * 大于
	 */
	public final static String GT = ">";
	public final static String GTS = ">";
	/**
	 * 小于
	 */
	public final static String LT = "<";
	public final static String LTS = "<";

	/**
	 * 引用校验删除失败错误码
	 */
	public final static String DELETREFERENCEERRORCODE = "E1001";

	/**
	 * 小计
	 */
	public final static String COUNTSTRING = "小计";


	/**
	 * 注册到浏览器中的枚举数据
	 */
	String WINDOW_ENUM_VALUE = "loanEnumValue";
	public final static String ORG_VALUE = "loginorg";
	public final static String DEPT_VALUE = "logindept";
	public final static String USER_VALUE = "loginuser";
	
	/**
	 * 公共单据号编码规则
	 */
	public final static String PUBBILLNO = "bdpubbillno";

	public final static String DEFAULT_CURRENCY_RMB = "1";

	public final static String STRING_TYPE_CODE = "1";

	public final static String DOUBLE_TYPE_CODE = "2";

	public final static String INTEGER_TYPE_CODE = "3";

	public final static String STRING_TYPE = "string";

	public final static String DOUBLE_TYPE = "double";

	public final static String INTEGER_TYPE = "integer";
	public static final String TOKEN = "token";
	public static final String LOGINERRORCODE = "E1001";
	public static final String SUCCESSPAGEQEURYCode = "S1001";
	public static final String SUCCESSPAGEQEURYMESSAGE = "当前已是最新数据";
	public static final int SESSION_OUT_TIME = 60 * 60 * 4;
	
	/**
	 * 删除状态 - 初始态
	 */
	public static Integer DRSTATE_INIT = 0;

	/**
	 * 删除状态- 删除态
	 */
	public static Integer DRSTATE_DELETED = 0;
	
	/**
	 * 每页显示记录数
	 */
	public final static int PAGESIZE = 10;
	/**
	 * 参照列表界面标识
	 */
	public final static String LIST = "list";
	/**
	 * 参照树型界面标识
	 */
	public final static String TREE = "tree";
	
	/**
	 * 年天数
	 */
	public static Integer YEARDAYS = 360;

	/**
	 * 月天数
	 */
	public static Integer MONTHDAYS = 30;

	/**
	 * 年月数
	 */
	public static Integer YEARMONTHS = 12;

	public static String NULLSTRVALUE = "";
	
	/**
	 * 保存数据成功
	 */
	public final static String SAVESUCCESSMESSAGE = "保存数据成功";
	/**
	 * 保存数据失败
	 */
	public final static String SAVEERRORMESSAGE = "保存数据失败";
	/**
	 * 删除数据成功
	 */
	public final static String DELETESUCCESSMESSAGE = "删除数据成功";
	/**
	 * 删除数据失败
	 */
	public final static String DELETEERRORMESSAGE = "删除数据失败";
	/**
	 * 查询数据成功
	 */
	public final static String QUERYSUCCESSMESSAGE = "查询数据成功";
	/**
	 * 查询数据失败
	 */
	public final static String QUERYERRORMESSAGE = "查询数据失败";
	
	/**
	 * 无任何条件时的mapkey
	 */
	public final static String EMPTYMAPKEY = "emptymapkey";
}
