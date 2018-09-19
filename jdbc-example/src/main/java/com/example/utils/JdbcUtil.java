package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcUtil {

	static Logger log = LoggerFactory.getLogger(JdbcUtil.class);
	static String url = PropertiesUtil.getProperty("mysql.url");
	static String user = PropertiesUtil.getProperty("mysql.username");
	static String password = PropertiesUtil.getProperty("mysql.password");
	static Connection conn = null;
	static Statement stm = null;
	static ResultSet rs = null;

	static {
		try {
			loadDriver();
			getConns(url, user, password);
			getStm();
		} catch (ClassNotFoundException | SQLException e) {
			rollBackTransaction();
			close();
			e.printStackTrace();
		}
		
	}

	/**
	 * 加载驱动
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void loadDriver() throws ClassNotFoundException {

		Class.forName(PropertiesUtil.getProperty("mysql.driver"));

	}

	/**
	 * 获取数据库连接
	 * 
	 * @param url
	 * @param user
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConns(String url, String user, String password) throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
		beginTransaction();

		return conn;
	}

	/**
	 * 获取数据库执行器
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Statement getStm() throws SQLException {

		stm = conn.createStatement();

		return stm;
	}

	/**
	 * 获取结果集
	 * 
	 * @param sql
	 * @param queryParam
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getResult(String sql, List<Object> queryParam) throws SQLException {

		log.info(parseSql(sql, queryParam));
		rs = stm.executeQuery(parseSql(sql, queryParam));
		commitTransaction();

		return rs;
	}

	/**
	 * 根据占位符" ? " 截断字符串为字符串数组
	 * 
	 * @param sql
	 * @param queryParam
	 */
	public static String parseSql(String sql, List<Object> queryParam) {
		String[] sqlArr = sql.split("[?]");

		Integer sqlParam = com.example.utils.StringUtils.countCharOfString(sql,"?");
		if(queryParam == null) queryParam = new ArrayList<Object>();
		if (sqlParam > queryParam.size()) {
			System.out.println("占位符数 > 传入参数");
		} else if (sqlParam < queryParam.size()) {
			System.out.println("占位符数 < 传入参数 ");
		} else {
			for (int i = 0; i < queryParam.size(); i++) {
				StringBuffer str = new StringBuffer(sqlArr[i]);
				str.append(queryParam.get(i));
				sqlArr[i] = str.toString();
			}
		}

		return StringUtils.join(sqlArr, " ");
	}

	/**
	 * 当我们查询数据库后返回的数据是一个二维结果集，就像excel一样
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public static Map<String, String> parseColumns(ResultSet rs) throws SQLException {
		// 字段名称，类型
		Map<String, String> columns = new HashMap<String, String>();

		ResultSetMetaData rmd = rs.getMetaData();
		int count = rmd.getColumnCount();// 字段总数
		for (int i = 1; i <= count; i++) {
			columns.put(rmd.getColumnName(i), rmd.getColumnTypeName(i));
		}

		return columns;
	}

	/**
	 * 返回List<Map>
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> findBySql(String sql,List<Object> queryParam) throws SQLException {

		getResult(sql,queryParam);
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, String> columns = parseColumns(rs);

		while (rs.next()) {
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			for (String key : columns.keySet()) {
				tmpMap.put(key, rs.getObject(key));
			}
			listMap.add(tmpMap);
		}

		return listMap;
	}

	/**
	 * 关闭资源
	 */
	public static void close() {

		try {
			if (conn != null) {
				conn.close();
			}
			if (stm != null) {
				stm.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设置自动提交为false 就是开启事物
	 */
	public static void beginTransaction() {

		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交事物
	 */
	public static void commitTransaction() {

		try {
			if (!conn.getAutoCommit()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回滚事物
	 */
	public static void rollBackTransaction() {

		try {
			if (!conn.getAutoCommit()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			List<Object> queryParam = new ArrayList<Object>();
			queryParam.add("5064");
			List<Map<String, Object>> list = JdbcUtil.findBySql("select * from tc_code where type = ? ",queryParam );
			System.out.println(list);
		} catch (SQLException e) {
			rollBackTransaction();
			e.printStackTrace();
		}finally {
			close();
		}
	}

}
