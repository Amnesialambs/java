package com.example.utils;

public class JdbcUtil {

	
	
	
	
	/**
	 * 加载驱动
	 */
	public void loadDriver() {
		
		try {
			Class.forName(PropertiesUtil.getProperty("mysql.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
