package com.momenting.servletboard.domain.board;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private Long readCount; //์กฐํ์ default = 0
	private Timestamp createdDate;
	
	public String getTitle() {
		return title.replaceAll("<", "&lt;").replaceAll(">", "&gt");
	}
}
