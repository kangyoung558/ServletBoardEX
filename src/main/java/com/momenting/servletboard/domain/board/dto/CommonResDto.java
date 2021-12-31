package com.momenting.servletboard.domain.board.dto;

import lombok.Data;

@Data
public class CommonResDto<T> {
	private int statusCode;
	private T data;
}
