package com.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BoardCommand;
import com.service.BoardDeleteCommand;
import com.service.BoardListCommand;
import com.service.BoardPageCommand;
import com.service.BoardReplyCommand;
import com.service.BoardReplyUICommand;
import com.service.BoardRetrieveCommand;
import com.service.BoardSearchCommand;
import com.service.BoardUpdateCommand;
import com.service.BoardWriteCommand;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// 클라이언트에서 요청한 값을 얻기 위한 작업을 구현하기!
		String reqURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String com = reqURI.substring(contextPath.length());
		
		BoardCommand command = null;
		String nextPage = null;
			
		// 목록 보기
		// list.do 인 경우 목록 보기 요청을 구현한 BoardListCommand 객체를 생성,
		// execute 메소드를 호출!
		// nextPage 변수에는 웹 브라우저에 보여줄 페이지인 list.jsp를 지정!
		if(com.equals("/list.do")) {
			
			command = new BoardListCommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // execute 불러올때마다 NamingException 예외 처리 해줘야함..??
			nextPage = "/JSP/list.jsp";
			
		} // if
		
//		===========================================
		
		// 글쓰기 폼
		if(com.equals("/writeui.do")) {
			
			nextPage = "/JSP/write.jsp";
			
		} // if
		
//		===========================================
		
		// 글쓰기
		if(com.equals("/write.do")) {
			command = (BoardCommand) new BoardWriteCommand(); // type mismatch 떠서.. 인터페이스로 변환해줌...
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "/list.do";
			
		} // if
			
//		===========================================
		
		// 글 자세히 보기
		if(com.equals("/retrieve.do")) {
			command = (BoardCommand) new BoardRetrieveCommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "/JSP/retrieve.jsp";
			
		} // if
		
//		===========================================
		
		// 글 수정하기
		if(com.equals("/update.do")) {
			
			command = new BoardUpdateCommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "list.do";
			
		} // if
		
//		===========================================
		
		// 글 삭제하기
		if(com.equals("/delete.do")) {
			
			command = new BoardDeleteCommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "list.do";
			
		} // if
		
//		===========================================
		
		// 글 검색하기
		if(com.equals("/search.do")) {
			command = new BoardSearchCommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "list.jsp";
			
		} // if
		
//		===========================================
		
		// 답변글 입력폼 보기
		if(com.equals("/replyui.do")) {
			command = new BoardReplyUICommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "/JSP/reply.jsp";
			
		} // if
		
//		===========================================
		
		// 답변 글쓰기
		if(com.equals("/reply.do")) {
			command = new BoardReplyCommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "list.do";
		
		} // if
		
//		===========================================
		
		// 페이징 처리
		if(com.equals("/list.do")) {
			command = new BoardPageCommand();
			
			try {
				command.execute(req, res);
			} catch (NamingException e) {
				e.printStackTrace();
			} // try-catch
			
			nextPage = "/JSP/listPage.jsp";
			
		} // if
		
		
		// Request forward 방식 사용하여 JSP 호출!
		RequestDispatcher dis = req.getRequestDispatcher(nextPage);
		dis.forward(req, res);
		
	} // service()

} // end class
