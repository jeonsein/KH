<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>

<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>write.jsp</title>
</head>

<body>
    <h1>/JSP/write.jsp</h1>
    <hr>
    
    <h2>게시판 글쓰기 화면</h2>
    
	<form action = "write.do" method="POST">
		제목<input type="text" name="title"><br />
		작성자<input type="text" name="author"><br />
		내용<textarea name="content" rows="5"></textarea><br />
		<input type="submit" value="저장">
	</form>
	
	<a href="list.do">목록 보기</a>
    
</body>

</html>