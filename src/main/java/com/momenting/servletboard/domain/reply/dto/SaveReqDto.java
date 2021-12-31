package com.momenting.servletboard.domain.reply.dto;

import lombok.Data;

@Data
public class SaveReqDto {
	private Long userId;
	private Long boardId;
	private String content;
}
