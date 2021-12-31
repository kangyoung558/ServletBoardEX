package com.momenting.servletboard.service;

import com.momenting.servletboard.domain.reply.ReplyDao;
import com.momenting.servletboard.domain.reply.dto.SaveReqDto;

public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}
	
	public int save(SaveReqDto dto) {
		return replyDao.save(dto);
	}
	
}
