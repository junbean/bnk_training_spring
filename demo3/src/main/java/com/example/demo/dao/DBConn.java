package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection conn = null;
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:testdb";
	private static final String UID = "green";
	private static final String UPW = "1234";

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (conn == null) {
				conn = DriverManager.getConnection(URL, UID, UPW);
				System.out.println("DB 연결 성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}