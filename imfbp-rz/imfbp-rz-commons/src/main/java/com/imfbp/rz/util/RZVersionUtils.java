package com.imfbp.rz.util;

import java.math.BigDecimal;

import com.platform.common.utils.StringUtil;

/**
 * @Title : 版本控制工具类
 * @Description : 版本控制工具类,版本号变更接口
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月5日 下午3:03:56
 */
public final class RZVersionUtils {

	/**
	 * 初始版本号
	 */
	public final static String INIT_VERSION = "V1.0";
	/**
	 * 版本号前缀
	 */
	public final static String VERSION_HEAD = "V";
	/**
	 * 版本增量步长
	 */
	public final static BigDecimal VERSION_INCREMENT = new BigDecimal("0.1");

	/**
	 * 根据当前版本号获取下一版本号，规则为将当前版本号流水加0.1，比如V1.0的下一版本就是V1.1
	 * 
	 * @param version
	 *            当前版本号，版本号规则为：V+版本流水号,比如：V1.0
	 * @return
	 */
	public final static String getNextVersion(String version) {
		if (StringUtil.isEmpty(version)) {
			return INIT_VERSION;
		}
		String versionNum = version.substring(1);
		if (StringUtil.isEmpty(versionNum)) {
			return INIT_VERSION;
		}
		BigDecimal bigDecimal1 = new BigDecimal(versionNum);
		bigDecimal1 = bigDecimal1.add(VERSION_INCREMENT);
		return VERSION_HEAD + bigDecimal1.floatValue();
	}

	// public static void main(String[] args) {
	// // System.out.println(getNextVersion("V1.0"));
	// // System.out.println(getNextVersion("V1.5"));
	// System.out.println(getNextVersion("V5.2"));
	// }

}
