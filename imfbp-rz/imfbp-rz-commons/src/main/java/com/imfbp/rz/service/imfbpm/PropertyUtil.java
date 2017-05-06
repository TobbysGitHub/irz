package com.imfbp.rz.service.imfbpm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertyUtil {

	private static Properties prop = null;

	static {
		prop = new Properties();

		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties");
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyByKey(String key) {
		String value = prop.getProperty(key);
		return StringUtils.isBlank(value) ? "" : value;
	}

}
