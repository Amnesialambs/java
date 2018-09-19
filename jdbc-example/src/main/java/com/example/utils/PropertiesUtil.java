package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {



	
	static Properties prop = new Properties();
	
	static {
		loadProperty();
	}
	
	public static void loadProperty() {
		
		try {
			InputStream in = PropertiesUtil.class.getResourceAsStream("/db.properties");
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 *   根据属性名获取属性值
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		
		return prop.getProperty(key);
	}
	
	

	
	
}
