package com.momenting.servletboard.domain.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.momenting.servletboard.config.DBConn;
import com.momenting.servletboard.domain.reply.dto.SaveReqDto;

public class ReplyDao {

	public int save(SaveReqDto dto) {
		Connection conn = DBConn.getConnection();
		String sql = "insert into reply(userid, boardid, content) values(?,?,?)";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, dto.getUserId());
			pstmt.setLong(2, dto.getBoardId());
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
