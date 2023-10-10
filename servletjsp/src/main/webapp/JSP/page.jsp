<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.entity.PageTO" %>

<%
	PageTO to = (PageTO)request.getAttribute("page");

	int curPage = to.getCurPage();
	int perPage = to.getPerPage();
	int totalCount = to.getTotalCount();

	int totalPage = totalCount / perPage; // 보여줄 페이지 번호개수

	if( totalCount % perPage != 0 ) totalPage++;
	
	for( int i = 1 ; i <= totalPage ; i++){
		if( curPage == i ){                                                                                                                                                                              
			out.print( "<font size=5 color='purple'>"+i+"</font>" );
		} else {
			out.print( "<a href='list.do?curPage="+i+"'>"+i+"</a>&nbsp;" ); 
		} // if-else
			
	} // for
%>