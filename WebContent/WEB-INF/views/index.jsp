<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

</head>
<body>

<h1 style="text-align: center;">PCLASS TOY PROJECT</h1>

<c:if test="${empty authentication}">

<h2><a href="/member/login-form">login</a></h2>
<h2><a href="/member/join-form">회원가입</a></h2>

</c:if>


<c:if test="${not empty authentication}">
<h1>${authentication.userId} 님 안녕?</h1>
<h2><a href="/member/logout">logout</a></h2>
<h2><a href="/member/mypage">마이페이지</a></h2>
<h2><a href="/board/board-form">게시글 작성</a></h2>
</c:if>




</script>
</body>
</html>