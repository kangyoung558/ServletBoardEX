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
	private Long userid;
	private String title;
	private String content;
	private Long readCount; //조회수 default = 0
	private Timestamp createdDate;
}
