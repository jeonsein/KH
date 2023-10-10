<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>

<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>retrieve.jsp</title>
</head>

<body>
    <h1>/JSP/retrieve.jsp</h1>
    <hr>
    
    <h2>글 자세히 보기</h2>
    <form action="update.do" method="POST">
    	<!-- 글 수정 시 필요함! -->
    	<input type ="hidden" name="num" value="${retrieve.num}">
    	글 번호: ${retrieve.num} &nbsp;&nbsp;&nbsp;&nbsp;
    	조회수: ${retrieve.readcnt}<br />
    	
    	제목<input type="text" name="title" value="${retrieve.title}"><br>
    	작성자<input type="text" name="author" value="${retrieve.author}"><br>
    	내용<textarea name="content" rows="10">${retrieve.content}</textarea><br>
    	<input type="submit" value="수정">
    </form>
    
    <a href="list.do">목록</a>
    <a href="delete.do?num=${retrieve.num}">삭제</a>
    <a href="replyui.do?num=${retrieve.num}">답변달기</a>
    
</body>

</html>