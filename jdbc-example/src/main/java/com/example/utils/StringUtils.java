package com.example.utils;

import java.util.HashMap;

public class StringUtils {

	
	
	
	
	/**
	 *    获取字符在字符串出现的次数
	 * @param str
	 * @param ch
	 * @return
	 */
	public static Integer countCharOfString(String str,String regex) {
		
		
		//1.把字符串转换成字符数组
		char[] arr = str.toCharArray();
		char ch1 = regex.toCharArray()[0];
		//2.定义一个双列集合，存储字符和字符次数
		HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
		
		//3.遍历数组
		for (char ch : arr) {
			//包含累加，不包含默认1
			hm.put(ch, hm.containsKey(ch1) ? hm.get(ch) + 1 : 1);
		}
		
		
		return hm.containsKey(ch1) ? hm.get(ch1) : -1 ;//如果字符存在返回次数，不存在返回-1
	}
	
	
	/**
	 * 
	* @Title: checkNull  
	* @Description: 校验是否为空  
	* @param @param obj
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public boolean checkNull(Object obj) {
		 
		return (obj == null || obj.equals("") || obj.equals("null")) ? true : false;
	}
	
	
	
}
