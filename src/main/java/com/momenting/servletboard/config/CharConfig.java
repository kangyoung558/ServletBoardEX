package com.momenting.servletboard.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharConfig implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
//		String username = request.getParameter("username");
//		System.out.println("username 필터: " + username);
//		
//		PrintWriter out = response.getWriter();
//		out.println("안녕");
//		out.flush(); dwdwdwd
		
		chain.doFilter(request, response);
	}
	
}
