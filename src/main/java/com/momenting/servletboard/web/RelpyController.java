package com.momenting.servletboard.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momenting.servletboard.domain.reply.dto.SaveReqDto;
import com.momenting.servletboard.domain.user.User;
import com.momenting.servletboard.service.BoardService;
import com.momenting.servletboard.service.ReplyService;
import com.momenting.servletboard.util.Script;

//http://localhost:8080/servletbord
@WebServlet("/reply")
public class RelpyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelpyController() {
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
		ReplyService replyService = new ReplyService();
		
		if(cmd.equals("save")) {
			Long userId = Long.parseLong(request.getParameter("userId"));
			Long boardId = Long.parseLong(request.getParameter("boardId"));
			String content = request.getParameter("content");
			
			SaveReqDto dto = new SaveReqDto();
			dto.setUserId(userId);
			dto.setBoardId(boardId);
			dto.setContent(content);
			
			int result = replyService.save(dto);
			
			if(result == 1) {
				response.sendRedirect("/servletboard/board?cmd=detail&id="+boardId);
			}else {
				Script.back(response, "댓글쓰기 실패");
			}
		}
	}

}
