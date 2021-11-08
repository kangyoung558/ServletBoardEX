package com.momenting.servletboard.service;

import com.momenting.servletboard.domain.user.User;
import com.momenting.servletboard.domain.user.UserDao;
import com.momenting.servletboard.domain.user.dto.JoinReqDto;
import com.momenting.servletboard.domain.user.dto.LoginReqDto;
import com.momenting.servletboard.domain.user.dto.UpdateReqDto;

public class UserService {
	//회원 가입, 회원수정, 로그인, 로그아웃, 아이디 중복체크 
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public int join(JoinReqDto dto) {
		int result = userDao.save(dto); 
		return result;
	}
	
	public User login(LoginReqDto dto) {
		return userDao.findByUsernameAndPassword(dto);
	}
	 
	public int updateUserInfo(UpdateReqDto dto) {
		return -1;
	}
	
	public int checkUsername(String username) {
		int result = userDao.checkUsername(username);
		return result;
	}
	
}
