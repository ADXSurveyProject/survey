package com.survey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDao {

	
	 // private Connection getLocalConnection() throws Exception {
	//	  	Class.forName("com.mysql.jdbc.Driver"); 
	 // 		String url = "jdbc:mysql://localhost:3306/survey?useSSL=false"; 
	  //		Connection conn = DriverManager.getConnection(url, "root", "admin"); 
	 // 		return conn; 
	  //}
	 
	protected Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://us-cdbr-azure-southcentral-f.cloudapp.net/acsm_3b47e87dc84ba62";
			Connection conn = DriverManager.getConnection(url, "b57134f449e9b5", "b66eced6");
			return conn;
			//Connection conn = getLocalConnection();
			//return conn;
		} catch (Exception e) {
			e.printStackTrace();
			//try {
				//Connection conn = getLocalConnection();
				//System.err.println("Returning local connection");

			//	return conn;
			//} catch (Exception ee) {
			//	e.printStackTrace();
				return null;
			//}
		}
	}
	protected void executeUpdate(String sql, String[] args) {
		Connection conn = null;

		try {
			conn = getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			if (args != null) {
				int i=1;
				for (String each : args) {
					ps.setObject(i++, each);
				}
			}
			int cnt = ps.executeUpdate();
			System.out.println("updated : " + cnt + " records");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	protected List executeFetchSql(String sql) {
		return executeFetchSql(sql, null);
	}
	protected List executeFetchSql(String sql, String[] args) {
		Connection conn = null;
		List al = new ArrayList();
		try {
			conn = getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			if (args != null) {
				int i=1;
				for (String each : args) {
					ps.setObject(i++, each);
				}
			}
			ResultSet rs = ps.executeQuery();

			int ccount = rs.getMetaData().getColumnCount();
			String columns[] = new String[ccount];
			for (int i = 1; i <= ccount; i++) {
				columns[i - 1] = rs.getMetaData().getColumnName(i);
			}
			while (rs.next()) {
				Map data = new HashMap();
				for (int i = 1; i <= ccount; i++) {

					Object value = rs.getObject(i);
					data.put(columns[i - 1], value);
				}
				al.add(data);
				//System.out.println(data);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return al;
	}

	
}
