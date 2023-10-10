package com.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;


public class BoardReplyUICommand implements BoardCommand {

	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException {
		
		// num 파라미터 값에 해당되는 BoardDTO를 리턴 받아서 request scope에 "replyui" 키 값으로 바인딩함
		String num = req.getParameter("num");
		BoardDAO dao = new BoardDAO();
		
		BoardDTO data = dao.replyui(num);
		req.setAttribute("replyui", data);
		
	} // execute ()
	
} // end class
