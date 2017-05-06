package com.imfbp.rz.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.platform.common.utils.StringUtil;

/**
 * @Title :正则表达式工具类
 * @Description :
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年11月25日 上午10:08:43
 */
public class RegularUtils {

	/**
	 * 获取字符串匹配指定正则表达式的的内容列表
	 * 
	 * @param source
	 *            源匹配字符串
	 * @param regular
	 *            正则表达式
	 * @return
	 */
	public final static List<String> getPatternMatcher(String source,
			String regular) {
		List<String> lists = new ArrayList<String>();
		// String find = "\\$\\{.*?\\#\\}";
		if (!StringUtil.isEmpty(source) && !StringUtil.isEmpty(regular)) {
			Pattern pattern = Pattern.compile(regular);
			Matcher matcher = pattern.matcher(source);
			while (matcher.find()) {
				lists.add(matcher.group());
			}
		}
		return lists;
	}
}
