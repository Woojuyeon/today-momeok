<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>회원가입</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script>
    function userCreate() {
    	let form = document.registerForm;
    	
    	if (form.userId.value == "") {
    		alert("아이디가 비어있습니다.");
    		form.userId.focus();
    		return false;
    	} 
    	if (form.password.value == "") {
    		alert("비밀번호가 비어있습니다.");
    		form.password.focus();
    		return false;
    	}
    	/*
    	if (form.password.value != form.password2.value) {
    		alert("비밀번호가 일치하지 않습니다.");
    		form.password2.focus();
    		return false;
    	}
    	if (form.name.value == "") {
    		alert("이름을 입력하십시오.");
    		form.name.focus();
    		return false;
    	}
    	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    	if(emailExp.test(form.email.value)==false) {
    		alert("이메일 형식이 올바르지 않습니다.");
    		form.email.focus();
    		return false;
    	}
    	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
    	if(phoneExp.test(form.phone.value)==false) {
    		alert("전화번호 형식이 올바르지 않습니다.");
    		form.phone.focus();
    		return false;
    	}
    	*/
    	form.submit();
    }
  </script>
</head>
<body>
	<form name="registerForm" method="POST" action="<c:url value='/user/register' />">
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
		        <th>이름: </th>
		        <td><input type="text" id="name" name="name"></td>
		    </tr>
		    <tr>
		        <th>나이: </th>
		        <td><input type="text" id="age" name="age"></td>
		    </tr>
		    <tr>
		    	<th colspan="2">
		    		<select id="gender" name="gender">
						<option value="M">남자</option>
						<option value="F">여자</option>
					</select>
		    	</th>
		    </tr>
		    <tr>
		    	<td colspan="2">
		    		<input type="file" id="image" name="image">
		    	</td>
		    </tr>
		    <tr>
		    	<td colspan="2">
		    		<c:if test="${registerFailed}">이미 존재하는 사용자입니다.</c:if>
		    	</td>
		    </tr>
		    <tr>
		    	<td colspan="2"><button type="button" onclick="userCreate()">회원가입</button></td>
		    </tr>
		</table>
	</form>
	<button onclick="location.href='<c:url value='/login' />'">돌아가기</button>
</body>
</html>