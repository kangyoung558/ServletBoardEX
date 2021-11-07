package com.momenting.servletboard.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.momenting.servletboard.config.DBConn;
import com.momenting.servletboard.domain.user.dto.JoinReqDto;

public class UserDao {
	
	public int save(JoinReqDto dto) {
		String sql = "insert into user(username, password, email, address, user_role, created_date) values(?,?,?,?,'USER',now())";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return result;
	}
	
	public void update() {
		
	}
	
	public int checkUsername(String username) {
		String sql = "select username from user where username=?";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1;
	}
	
	public void findById() {
		
	}
}
