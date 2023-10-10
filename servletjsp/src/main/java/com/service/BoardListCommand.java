package com.service;

import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;

public class BoardListCommand implements BoardCommand {

	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException {
		
		BoardDAO dao = new BoardDAO();
		// 오류 뭔ㄷ ㅔ!!!!!!! -> BoardCommand랑 BoardListCommand 둘다 예외처리 던져줌.
		
		ArrayList<BoardDTO> list = dao.list();
		
		req.setAttribute("list", list);
		
	} // execute
	
} // end class
