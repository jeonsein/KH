package com.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.entity.PageTO;

public class BoardPageCommand implements BoardCommand {
	
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException {
		
		// 맨 처음 요청 시 보여줄 페이지값 -> 기본 페이지 1로 지정
		int curPage = 1; // 현재 페이지 (기본 페이지 = 1)
		
		// curPage 파라미터를 지정하지 않으면 기본값 1 로 설정하고,
		// 파라미터가 존재하면 파라미터값으로 현재 페이지를 설정하여
		// PageTO에 저장후 리턴 받는다.
		if(req.getParameter("curPage") != null) {
			
			curPage = Integer.parseInt(req.getParameter("curPage"));
			
		} // if
		
		BoardDAO dao = new BoardDAO();
		PageTO list = dao.page(curPage);
		
		// listPage.jsp에서 목록 리스트 데이터 저장
		req.setAttribute("list", list.getList());
		
		// page.jsp에서 페이징 처리 데이터 저장
		req.setAttribute("page", list);
		
	} // execute()
	
} // end class
