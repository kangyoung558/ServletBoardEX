package com.momenting.servletboard.domain.board.dto;

import lombok.Data;

@Data
public class SaveReqDto {
	private Long userid;
	private String title;
	private String content;
}
