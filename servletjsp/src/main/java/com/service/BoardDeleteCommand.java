package com.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

public class BoardDeleteCommand implements BoardCommand {

	public void execute(HttpServletRequest req, HttpServletResponse res) 
		throws NamingException {
		
		
		// num에 해당되는 파라미터를 얻어냄
		String num = req.getParameter("num");
		
		// BoardDAO 클래스의 delete 메소드로 넘김
		BoardDAO dao = new BoardDAO();
		dao.delete(num);
	
	} // execute()
		
} // end class
