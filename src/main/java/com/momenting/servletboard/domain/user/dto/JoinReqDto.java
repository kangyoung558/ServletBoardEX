package com.momenting.servletboard.domain.user.dto;

import lombok.Data;

@Data
public class JoinReqDto {
	private String username;
	private String password;
	private String email;
	private String address;
}
