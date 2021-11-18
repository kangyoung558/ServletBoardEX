<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	RequestDispatcher requestDispatcher =
		request.getRequestDispatcher("board?cmd=list&page=0");
	requestDispatcher.forward(request, response);
	
%>