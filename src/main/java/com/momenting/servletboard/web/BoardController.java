package com.momenting.servletboard.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.momenting.servletboard.domain.board.Board;
import com.momenting.servletboard.domain.board.dto.DeleteReqDto;
import com.momenting.servletboard.domain.board.dto.DeleteResDto;
import com.momenting.servletboard.domain.board.dto.DetailResDto;
import com.momenting.servletboard.domain.board.dto.SaveReqDto;
import com.momenting.servletboard.domain.board.dto.UpdateReqDto;
import com.momenting.servletboard.domain.user.User;
import com.momenting.servletboard.service.BoardService;
import com.momenting.servletboard.util.Script;


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
				RequestDispatcher dispatcher = request.getRequestDispatcher("board/saveForm.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("user/loginForm.jsp");
				dispatcher.forward(request, response);
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
		}else if(cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page")); 
			List<Board> boards = boardService.list(page);
			request.setAttribute("boards", boards);
			
			int boardCount = boardService.boardCount();
			int lastPage = (boardCount-1)/4;  
			double currentPosition = (double)page/(lastPage)*100;
			
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);
			
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher("board/list.jsp");
				requestDispatcher.forward(request, response);
		}else if (cmd.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailResDto dto = boardService.detail(id); //board테이블 + user테이블 = 조인된 데이터!!
			if(dto == null) {
				Script.back(response, "상세보기에 실패 하였습니다.");
			}else {
				request.setAttribute("dto", dto);
				//System.out.println("DetailResDto : " + dto);
				RequestDispatcher requestDispatcher =
						request.getRequestDispatcher("board/detail.jsp");
				requestDispatcher.forward(request, response);
			}
			
		}else if(cmd.equals("delete")) {
			BufferedReader br = request.getReader();
			String data = br.readLine();
			
			Gson gson = new Gson();
			DeleteReqDto dto = gson.fromJson(data, DeleteReqDto.class);
			
			int result = boardService.delete(dto.getBoardId());
			DeleteResDto resDto = new DeleteResDto();
			if(result == 1) {
				resDto.setStatus("ok");
			}else {
				resDto.setStatus("fail");
			}
			String resData = gson.toJson(resDto);
			PrintWriter out = response.getWriter();
			out.print(resData);
			out.flush();
			
		}else if (cmd.equals("updateForm")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailResDto dto = boardService.detail(id);
			
			request.setAttribute("dto", dto);
			
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher("board/updateForm.jsp");
			requestDispatcher.forward(request, response);
		}else if (cmd.equals("update")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			UpdateReqDto dto = new UpdateReqDto();
			
			dto.setId(id);
			dto.setTitle(title);
			dto.setContent(content);
			
			int result = boardService.update(dto);
			
			if(result == 1) {
				response.sendRedirect("/servletboard/board?cmd=detail&id="+id);
			}else {
				Script.back(response, "글 수정에 실패하였습니다.");
			}
		}
		
	}
	
}
