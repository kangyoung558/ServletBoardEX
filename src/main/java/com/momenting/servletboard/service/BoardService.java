package com.momenting.servletboard.service;

import java.util.List;

import com.momenting.servletboard.domain.board.Board;
import com.momenting.servletboard.domain.board.BoardDao;
import com.momenting.servletboard.domain.board.dto.DetailResDto;
import com.momenting.servletboard.domain.board.dto.SaveReqDto;
import com.momenting.servletboard.domain.board.dto.UpdateReqDto;

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
	
	public DetailResDto detail(int id) {
		int result = boardDao.updateReadCount(id);
		if(result == 1) {
			return boardDao.findById(id);
		}else {
			return null;
		}
	}
	
	public int delete(Long id) {
		return boardDao.deleteById(id);
	}
	
	public int update(UpdateReqDto dto) {
		return boardDao.update(dto);
	}
}
