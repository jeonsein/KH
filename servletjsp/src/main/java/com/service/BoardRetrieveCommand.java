package com.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;

public class BoardRetrieveCommand implements BoardCommand {
	
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException {
		
		String num = req.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.retrieve(num);
		
		req.setAttribute("retrieve", data);
		
	} // execute()
	
} // end class
