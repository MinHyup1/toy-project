<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/board/board.css"/>
</head>
<body>
<div class="content">
	<h2 class="tit">게시판 </h2>
	
	<div class="info">
		<span>번호 : <c:out value ="${datas.board.bdIdx}"/></span>
		<span>제목 : <c:out value ="${datas.board.title}"/></span>
		<span>등록일 : <c:out value ="${datas.board.regDate}"/></span>
		<span>작성자 : <c:out value ="${datas.board.userId}"/></span>
	</div>
	
	<div class ="info file_info">
		<c:if test="${not empty datas.files}">
			<ol>
				<c:forEach items="${datas.files}" var="file">
				<li><a href="${file.downloadLink}">${file.originFileName}</a></li>
				</c:forEach>
			</ol>
		</c:if>
	</div>
	
	<div class="article_content">
		<pre><c:out value="${datas.board.content}"/></pre>
	
	</div>
	




</div>

</body>
</html>