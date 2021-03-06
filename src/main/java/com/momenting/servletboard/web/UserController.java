package com.momenting.servletboard.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momenting.servletboard.domain.user.User;
import com.momenting.servletboard.domain.user.dto.JoinReqDto;
import com.momenting.servletboard.domain.user.dto.LoginReqDto;
import com.momenting.servletboard.service.UserService;
import com.momenting.servletboard.util.Script;

//http://localhost:8080/servletbord
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		if(cmd.equals("loginForm")) {
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher("user/loginForm.jsp");
				requestDispatcher.forward(request, response);
		}else if (cmd.equals("login")) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			LoginReqDto dto = new LoginReqDto();
			
			dto.setUsername(username);
			dto.setPassword(password);
			
			User userEntity = userService.login(dto);
			if(userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				response.sendRedirect("index.jsp");
			}else {
				Script.back(response, "????????? ??????");
			}
			
		}else if(cmd.equals("joinForm")) {
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher("user/loginForm.jsp");
				requestDispatcher.forward(request, response);
			
		}else if(cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			JoinReqDto dto = new JoinReqDto();
			
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);
			dto.setAddress(address);
			System.out.println("???????????? : " + dto);
			int result = userService.join(dto);
			if(result==1) {
				RequestDispatcher requestDispatcher =
						request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
			} else {
				Script.back(response, "???????????? ??????");
			}
			
		}else if(cmd.equals("checkUsername")) {
			BufferedReader br = request.getReader();
			String username = br.readLine();
			int result =  userService.checkUsername(username);
			PrintWriter out = response.getWriter();
			if(result == 1) {
				out.print("ok");
			}else {
				out.print("fail");
			}
			out.flush();
		}else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher("user/loginForm.jsp");
				requestDispatcher.forward(request, response);
		}
	}

}
