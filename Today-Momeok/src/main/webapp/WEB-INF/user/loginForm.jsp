<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>사용자 관리(UserManager2)</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
  <script>
    function login() {
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
    	form.submit();
    }
  </script>
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="title">
    <span>사용자 관리 - 로그인</span>
  </div>
  
  <!-- login form  -->
  <form name="form" method="POST" action="<c:url value='/user/login' />">
    <c:if test="${loginFailed}">
      <!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
      <div class="errmsg">${exception.getMessage()}</div>   
    </c:if>
    
    <table class="uTable">
      <tr>
        <th>사용자 ID</th>
        <td><input type="text" name="userId" /></td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="password" /></td>
      </tr>
    </table>
    <div class="buttons">
      <button type="button" onClick="login()">로그인</button> 
      <a class="btn" href="<c:url value='/user/register'/>"> <!-- '/user/register/form' 에서 수정됨 -->
        회원가입</a>
    </div>
  </form>
  <div><br>(관리자 로그인: admin/admin)<br></div>
</body>
</html>