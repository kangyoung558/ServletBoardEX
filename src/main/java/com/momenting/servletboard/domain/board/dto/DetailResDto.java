package com.momenting.servletboard.domain.board.dto;

import lombok.Data;

@Data
public class DetailResDto {
	private Long id;
	private String title;
	private String content;
	private Long readCount;
	private String username; 
	private Long userId;
	
	public String getTitle() {
		return title.replaceAll("<", "&lt;").replaceAll(">", "&gt");
	}
}
