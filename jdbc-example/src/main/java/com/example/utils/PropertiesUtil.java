package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	static Properties properties = new Properties();//资源文件类
	
	
	
	/**
	 * 
	* @Title: loadProperties  
	* @Description: 通过类加载器加载配置文件
	* 
	*  
	* @param @param path    参数  
	* @return void    返回类型  
	* @throws
	 */
	public void loadProperties(String path) {
		
		try {
			
			InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(in);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties");
		properties.load(in);
		
		System.out.println(properties.getProperty("username"));
	} 
}
