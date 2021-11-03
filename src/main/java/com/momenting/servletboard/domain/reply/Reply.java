package com.momenting.servletboard.domain.reply;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
	private Long id;
	private Long userid;
	private Long boardid;
	private String content;
	private Timestamp createdDate;
}
