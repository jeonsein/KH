package com.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

public class BoardUpdateCommand implements BoardCommand {
	
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException {
		
		// retrieve에서 넘어온 4개의 파라미터를 얻음
		String num = req.getParameter("num");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		
		// BoardDAO class의 update 메소드로 넘겨주기!
		BoardDAO dao = new BoardDAO();
		dao.update(num, title, author, content);
		
	} // execute()

} // end class
