package com.momenting.servletboard.service;

import java.util.List;

import com.momenting.servletboard.domain.board.Board;
import com.momenting.servletboard.domain.board.BoardDao;
import com.momenting.servletboard.domain.board.dto.SaveReqDto;

public class BoardService {
	
	private BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	public int save(SaveReqDto dto) {
		return boardDao.save(dto);
	}
	
	public List<Board> list(int page) {
		return boardDao.findAll(page);
	}
	
	public int boardCount() {
		return boardDao.count();
	}
}
