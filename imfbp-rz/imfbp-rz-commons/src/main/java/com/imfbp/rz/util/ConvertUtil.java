package com.imfbp.rz.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author quanjianjun
 * 
 */
public class ConvertUtil {

	public static String firstUpperCamelCase(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replace("T_", "");
			str = str.toLowerCase();
			String[] strs = str.split("_");
			if (strs.length == 1) {
				return firstLetterUpper(str);
			} else {
				String convertedStr = "";
				for (int i = 0; i < strs.length; i++) {
					convertedStr += firstLetterUpper(strs[i]);
				}
				return convertedStr;
			}
		}
		return str;
	}

	public static String firstLowerCamelCase(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replace("T_", "");
			str = str.toLowerCase();
			String[] strs = str.split("_");
			if (strs.length == 1) {
				return allLower(str);
			} else {
				String convertedStr = "";
				for (int i = 1; i < strs.length; i++) {
					convertedStr += firstLetterUpper(strs[i]);
				}
				return strs[0] + convertedStr;
			}
		}
		return str;
	}

	public static String firstLetterUpper(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replace("T_", "");
			str = str.toLowerCase();
			return str.substring(0, 1).toUpperCase()
					+ str.substring(1, str.length());
		}
		return str;
	}

	public static String allUpper(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replace("T_", "");
			str = str.toLowerCase();
			String[] strs = str.split("_");
			if (strs.length == 1) {
				return str.toUpperCase();
			} else {
				String convertedStr = "";
				for (int i = 0; i < strs.length; i++) {
					convertedStr += strs[i].toUpperCase();
				}
				return convertedStr;
			}
		}
		return str;
	}

	public static String allLower(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replace("T_", "");
			str = str.toLowerCase();
			String[] strs = str.split("_");
			if (strs.length == 1) {
				return str.toLowerCase();
			} else {
				String convertedStr = "";
				for (int i = 0; i < strs.length; i++) {
					convertedStr += strs[i].toLowerCase();
				}
				return convertedStr;
			}
		}
		return str;
	}
	
	
	public static String getFullSeqno(Integer seqno){
		if(seqno < 10 ){
			return "00000"+ seqno;
		} else if(seqno < 100 ){
			return "0000"+ seqno;
		} else if(seqno < 1000 ){
			return "000"+ seqno;
		} else if(seqno < 10000 ){
			return "00"+ seqno;
		} else if(seqno < 100000 ){
			return "0"+ seqno;
		} else {
			return ""+ seqno;
		}
	}

}
