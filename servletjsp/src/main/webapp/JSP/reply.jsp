<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>reply.jsp</title>
</head>

<body>
    <h1>/JSP/reply.jsp</h1>
    <hr>
    
    <h2>답변글 쓰기 화면</h2>
    
    <form action ="reply.do" method="POST">
    
    	<!-- 답변글 필요 -->
    	<input type="hidden" name="num" value="${retrieve.num}">
    	<input type="hidden" name="repRoot" value="${replyui.repRoot}">
    	<input type="hidden" name="repStep" value="${replyui.repStep}">
    	<input type="hidden" name="repIndent" value="${replyui.repIndent}">
    	
    	원글 번호:${replyui.num}&nbsp;&nbsp;&nbsp;&nbsp;
    	조회수:${replyui.readcnt}<br>
    	
    	제목<input type="text" name="title" value="${replyui.title}"><br>
		작성자<input type="text" name="author"><br>
		내용<textarea name="content" rows="10">${replyui.content}</textarea><br>
		<input type="submit" value="답변달기">  
    </form>
    
    <a href="list.do">목록 보기</a>

</body>
</html>