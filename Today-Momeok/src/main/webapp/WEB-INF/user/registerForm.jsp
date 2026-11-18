<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>사용자 관리</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
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
    	form.submit();
    }
  </script>
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="title">
    <span>사용자 관리 - 회원 가입</span>
  </div>

  <!-- registration form  -->
  <form name="form" method="POST" action="<c:url value='/user/register' />">
    <c:if test="${registerFailed}">
      <!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
      <div class="errmsg">${exception.getMessage()}</div>   
    </c:if>
    
    <table class="uTable">
      <tr>
        <th>사용자 ID</th>
        <td><input type="text" name="userId"
          <c:if test="${registerFailed}">value="${user.userId}"</c:if> />
        </td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="password"
          <c:if test="${registerFailed}">value="${user.password}"</c:if> />
        </td>
      </tr>
      <tr>
        <th>비밀번호 확인</th>
        <td><input type="password" name="password2"
          <c:if test="${registerFailed}">value="${user.password}"</c:if> />
        </td>
      </tr>
      <tr>
        <th>이름</th>
        <td><input type="text" name="name"
          <c:if test="${registerFailed}">value="${user.name}"</c:if> />
        </td>
      </tr>
      <tr>
        <th>이메일 주소</th>
        <td><input type="text" name="email"
          <c:if test="${registerFailed}">value="${user.email}"</c:if> />
        </td>
      </tr>
      <tr>
        <th>전화번호</th>
        <td><input type="text" name="phone"
          <c:if test="${registerFailed}">value="${user.phone}"</c:if> />
        </td>
      </tr>
      <tr>
        <th>커뮤니티</th>
        <td>
          <select name="commId">
            <option value="0">없음</option>
            <c:forEach var="comm" items="${commList}">
              <option value="${comm.id}"
                <c:if test="${registerFailed && comm.id eq user.commId}">selected="selected"</c:if>>
                ${comm.name}</option>
            </c:forEach>
          </select>     
        </td>
      </tr>       
    </table>    
    
    <div class="buttons">
      <button type="button" onClick="userCreate()">가입</button>
      <a class="btn" href="<c:url value='/user/list' />">목록</a>
    </div>
  </form>
</body>
</html>