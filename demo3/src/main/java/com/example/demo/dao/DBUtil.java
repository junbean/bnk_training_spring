package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	// 1. 접속 객체(Connection) 제공
	public static Connection getConnection() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:testdb";
		String uid = "green";
		String upw = "1234";
		
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			
			if(conn != null) {
				System.out.println("DB 접속 성공....");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. DAO 에서 사용된 자원 해제(Connection, Statement, ResultSet) 
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			close(conn, pstmt);
			System.out.println("추가로 rs 자원해제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 2-1. ResultSet을 사용하지 않는 경우
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			System.out.println("conn, stmt 자원해제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
