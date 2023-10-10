package com.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

import lombok.Data;

@Data
public class BoardWriteCommand implements BoardCommand {
	
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException {
		
		res.setContentType("text/html;charset=UTF-8");
		
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		dao.write(title, author, content);
		
	}  // execute();
	
	
} // end class
