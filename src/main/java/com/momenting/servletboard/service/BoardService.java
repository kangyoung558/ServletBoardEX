package com.momenting.servletboard.service;

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
}
