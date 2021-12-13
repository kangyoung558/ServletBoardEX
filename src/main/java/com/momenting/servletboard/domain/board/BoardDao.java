package com.momenting.servletboard.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.momenting.servletboard.config.DBConn;
import com.momenting.servletboard.domain.board.dto.DetailResDto;
import com.momenting.servletboard.domain.board.dto.SaveReqDto;
import com.momenting.servletboard.domain.board.dto.UpdateReqDto;

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
	
	public DetailResDto findById(int id) {
		StringBuffer sb = new StringBuffer();
		sb.append("select b.id, b.title, b.content, b.read_count, u.username, b.userid ");
		sb.append("from board b inner join user u ");
		sb.append("on b.userid = u.id ");
		sb.append("where b.id =?");
	
		String sql = sb.toString();
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				DetailResDto dto = new DetailResDto();
				dto.setId(rs.getLong("b.id"));
				dto.setTitle(rs.getString("b.title"));
				dto.setContent(rs.getString("b.content"));
				dto.setReadCount(rs.getLong("b.read_count"));
				dto.setUsername(rs.getString("u.username"));
				dto.setUserId(rs.getLong("b.userid"));
				return dto;
			}
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
	
	public int updateReadCount(int id) {
		Connection conn = DBConn.getConnection();
		String sql = "update board set read_count = read_count+1 where id = ?";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return result;
	}
	
	public int deleteById(Long id) {
		Connection conn = DBConn.getConnection();
		String sql = "delete from board where id =?";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return result;
	}
	
	public int update(UpdateReqDto dto) {
		Connection conn = DBConn.getConnection();
		String sql = "update board set title=?,content=? where id = ?";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setLong(3, dto.getId());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return result;
	}
}
