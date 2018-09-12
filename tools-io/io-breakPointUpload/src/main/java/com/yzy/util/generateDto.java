package com.yzy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class generateDto {

	private static Connection conn = null;
	private static PreparedStatement st = null;
	private static ResultSet rs = null;
	private static String url = "jdbc:mysql://172.20.32.3:3306/factory_base?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true";
	private static String user = "dbadmin";
	private static String password = "Root-1234";
	private static String classForName = "com.mysql.jdbc.Driver";
	
	
	public enum paramEnum {
		String,Integer,Int,Float,Double,Long
	}
	
	/**
	 * 
	* @Title: loadDriver  
	* @Description: ��������
	* @param     ����  
	* @return void    ��������  
	* @throws
	 */
	public static void loadDriver() {
		
		try {
			Class.forName(classForName);
		} catch (ClassNotFoundException e) {

			System.out.println("��������ʧ��");
		}
	}
	
	/**
	 * 
	* @Title: getConnection  
	* @Description: ��ȡ���ݿ�����  
	* @param @return    ����  
	* @return Connection    ��������  
	* @throws
	 */
	public static Connection getConnection() {
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("�������ݿ�ʧ��");
		}
		return con;
		
	}
	
	public ResultSet getResult(String sql,List<Object> queryParam) {
		
		try {
			
			st = conn.prepareStatement(sql);
			for(Object param :queryParam) {
			    
				if(param instanceof String) {
					
				}
			}
			
			rs = st.executeQuery();
		} catch (SQLException e) {
			
			System.out.println("ִ��sqlʧ�ܣ�");
		}
		
		return rs;
	}
	
	public void closeResource() {
		
		try {
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		/*try {
			String sql = "select * from information_schema.COLUMNS "
					+ "  where TABLE_SCHEMA = ? "
					+ "    and TABLE_NAME = ? ";
			st.setString(1, "market_support");
			st.setString(2, "tm_activityPlan");
			while(rs.next()) {
				Object resultObject = Class.forName("activityPlanDto").newInstance() ;//��������ʵ����һ������
			}
			
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			
			
		}*/
		
		for(paramEnum p : paramEnum.values()) {
			System.out.println("key: "+p+" value: "+p.ordinal());
		}
	}
	
	
}
