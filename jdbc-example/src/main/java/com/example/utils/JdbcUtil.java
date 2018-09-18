package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcUtil {

	
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
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
	
	
	/**
	 *     获取数据库连接
	 * @param url
	 * @param user
	 * @param password
	 * @return
	 */
	public Connection getConns(String url,String user,String password) {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 获取数据库执行器
	 * @return
	 */
	public Statement getStm(String sql) {
		try {
			stm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stm;
	}
	
	/**
	 *  获取结果集
	 * @param sql
	 * @param queryParam
	 * @return
	 */
	public ResultSet getResult(String sql,List<Object> queryParam) {
		
		try {
			rs = stm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
