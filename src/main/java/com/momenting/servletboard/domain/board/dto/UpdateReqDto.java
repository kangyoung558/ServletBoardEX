package com.momenting.servletboard.domain.board.dto;

import lombok.Data;

@Data
public class UpdateReqDto {
	private Long id;
	private String title;
	private String content;
}
