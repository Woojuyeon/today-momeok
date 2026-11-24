<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script>
function userCreate() {
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.password2.focus();
		return false;
	}
	
	form.submit();
}
</script>
<body>
	<form name="registerForm" method="POST" action="<c:url value='/user/registerForm' />">
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
		        <th>나이: </th>
		        <td><input type="text" id="age" name="age"></td>
		    </tr>
		    <tr>
		    	<th colspan="2">
		    		<select id="gender" name="gender">
						<option value="man">남자</option>
						<option value="woman">여자</option>
					</select>
		    	</th>
		    </tr>
		    <tr>
		    	<td colspan="2">
		    		<input type="file">
		    	</td>
		    </tr>
		    <tr>
		    	<td colspan="2"><button type="submit" onClick="userCreate()">회원가입</button></td>
		    </tr>
		</table>
	</form>
	<button onclick="location.href='<c:url value='/login' />'">돌아가기</button>
</body>
</html>