<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>오늘 뭐 먹지?</h1>
	<div>
	<form name="loginForm" method="POST" action="<c:url value='/user/login' />">
		<table>
	      <tr>
	        <th>아이디: </th>
	        <td><input type="text" id="userId" name="userId"></td>
	      </tr>
	      <tr>
	        <th>비밀번호: </th>
	        <td><input type="password" id="password" name="password"></td>
	      </tr>
	      <tr>
	      	<td colspan="2">
	      		<c:if test="${loginFailed}">아이디나 비밀번호가 틀렸습니다.</c:if>
	      	</td>
	      </tr>
	      <tr>
	      	<td>
	      		<button type="submit">로그인</button>
	      	</td>
	      </tr>
	    </table>
		
	</form>
	</div>
	
	<button onclick="location.href='<c:url value='/user/register' />'">회원가입</button>
</body>
</html>