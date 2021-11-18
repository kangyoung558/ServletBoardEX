package com.momenting.servletboard.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Board> findAll(int page) {
		
		String sql = "select * from board order by id desc limit ?, 4";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Board> boards = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page*4);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = Board.builder()
						.id(rs.getLong("id"))
						.userId(rs.getLong("userid"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.readCount(rs.getLong("read_count"))
						.createdDate(rs.getTimestamp("created_date"))
						.build();
				boards.add(board);		
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	public int count() {
		String sql = "select count(*) from board";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1;
	}
}
