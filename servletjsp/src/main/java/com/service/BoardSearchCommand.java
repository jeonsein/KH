package com.service;

import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.BoardDTO;

public class BoardSearchCommand implements BoardCommand {

	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException {
		
		// searchName과 searchValue 파라미터 값을 얻음!
		String searchName = req.getParameter("searchName");
		String searchValue = req.getParameter("searchValue");
		
		// BoardDAO search 메소드를 사용하여 일치ㅏ는 데이터를 ArrayList로 리턴 받는다.
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.search(searchName, searchValue);
		
		// 목록보기 구현과 동일하게 request scope "list" 킷값으로 바인딩한다.
		req.setAttribute("list", list);
		
	} // execute()
	
} // end class
