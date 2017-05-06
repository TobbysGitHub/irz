package com.imfbp.rz.util;

import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class WebUtils {

	/**
	 * 设置下载文件中文名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String encodeFileName(String fileName, HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(fileName, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
				return MimeUtility.encodeText(fileName, "UTF-8", "B");
			}
			return new String((fileName).getBytes("GBK"), "ISO8859-1");
		} catch (Exception ex) {
			return fileName;
		}
	}

}
