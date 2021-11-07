package com.momenting.servletboard.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax")
public class AjaxTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxTest() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터 응답
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.flush();
		//response.sendRedirect("index.jsp");
	}

}
