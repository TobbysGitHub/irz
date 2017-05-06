package com.imfbp.rz.util;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/****
 * 
 * 常用工具类
 * 
 * @author Administrator
 *
 */
public class ToolUtils {

	private final static Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
	
	/*****
	 * 检查Double对象是否为空，如果为空返回零，否则返回原值
	 * @param val
	 * 
	 * @return
	 */
	public static Double getDouNullAsZero(Double val) {
		if (val == null) {
			return Double.valueOf(0);
		} else {
			return val;
		}
	}

	/****
	 * 检查Integer对象是否为空，如果为空返回零，否则返回原值
	 * 
	 * @param val
	 * @return
	 */
	public static Integer getIntNullAsZero(Integer val) {
		if (val == null) {
			return Integer.valueOf(0);
		} else {
			return val;
		}
	}
	
	/**
	 * 判断集合是否为空
	 * @param list
	 * @return
	 */
	public static boolean isEmptyCollection(Collection<?> list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/***
	 * 判断集合不为空
	 * @param list
	 * @return
	 */
	public static boolean isNotEmptyCollection(Collection<?> list) {
		return !isEmptyCollection(list);
	}
	
	/****
	 * 将驼峰命名规则转换成下划线
	 * @param str
	 * @return
	 */
	public static String humpToLine(String str) {
		Matcher matcher = HUMP_PATTERN.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	/****
	 * 获取一个对象为空的属性值
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
	/**
	 * 转换为驼峰
	 * 
	 * @param underscoreName
	 * @return
	 */
	public static String camelCaseName(String underscoreName) {
		StringBuilder result = new StringBuilder();
		if (underscoreName != null && underscoreName.length() > 0) {
			boolean flag = false;
			for (int i = 0; i < underscoreName.length(); i++) {
				char ch = underscoreName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}

}
