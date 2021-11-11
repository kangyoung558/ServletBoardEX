package com.momenting.servletboard.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momenting.servletboard.domain.board.dto.SaveReqDto;
import com.momenting.servletboard.domain.user.User;
import com.momenting.servletboard.service.BoardService;
import com.momenting.servletboard.service.UserService;
import com.momenting.servletboard.util.Script;

import lombok.RequiredArgsConstructor;

//http://localhost:8080/servletbord
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
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
		HttpSession session = request.getSession();
		BoardService boardService = new BoardService();
		
		if(cmd.equals("saveForm")) {
			User principal = (User)session.getAttribute("principal");
			if(principal != null) {
				response.sendRedirect("board/saveForm.jsp");
			}else {
				response.sendRedirect("user/loginForm.jsp");
			}
		}else if(cmd.equals("save")) {
			Long userid = Long.parseLong(request.getParameter("userid"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			SaveReqDto dto = new SaveReqDto();
			
			dto.setUserid(userid);
			dto.setTitle(title);
			dto.setContent(content);
			
			int resilt =  boardService.save(dto);
			if(resilt == 1) {
				response.sendRedirect("index.jsp");
			}else {
				Script.back(response, "글쓰기 실패");
			}
		}
	}
	
}
