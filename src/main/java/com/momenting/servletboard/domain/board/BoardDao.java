package com.momenting.servletboard.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.momenting.servletboard.config.DBConn;
import com.momenting.servletboard.domain.board.dto.SaveReqDto;

public class BoardDao {
		
	public int save(SaveReqDto dto) {
		Connection conn = DBConn.getConnection();
		String sql = "insert into board(userid, title, content) values(?,?,?)";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, dto.getUserid());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return result;
	}
}
