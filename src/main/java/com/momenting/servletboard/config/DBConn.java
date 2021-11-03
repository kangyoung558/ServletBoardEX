package com.momenting.servletboard.config;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class DBConn {
	
	public static Connection getConnection() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/ServletBoardEX");
			Connection conn = ds.getConnection();
			return conn;
		} catch (Exception e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
		return null;
	}
}
