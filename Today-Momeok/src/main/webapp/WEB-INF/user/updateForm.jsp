<%@page contentType="text/html; charset=utf-8"%>
<%@page import="model.domain.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  User user = (User) request.getAttribute("user");
%>
<html>
<head>
  <title>사용자 관리</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
  <script>
    function userModify() {
    	if (form.password.value == "") {
    		alert("비밀번호를 입력하십시오.");
    		form.password.focus();
    		return false;
    	}
    	if (form.password.value != form.password2.value) {
    		alert("비밀번호가 일치하지 않습니다.");
    		form.name.focus();
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
    <span>사용자 관리 - 사용자 수정</span>
  </div>

  <!-- Update Form  -->
  <form name="form" method="POST" action="<c:url value='/user/update' />">
    <input type="hidden" name="userId" value="${user.userId}" />
    
    <table class="uTable">
      <tr>
        <th>사용자 ID</th>
        <td>${user.userId}</td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="password" value="${user.password}" /></td>
      </tr>
      <tr>
        <th>비밀번호 확인</th>
        <td><input type="password" name="password2" value="${user.password}" /></td>
      </tr>
      <tr>
        <th>이름</th>
        <td><input type="text" name="name" value="${user.name}" /></td>
      </tr>
      <tr>
        <th>이메일 주소</th>
        <td><input type="text" name="email" value="${user.email}" /></td>
      </tr>
      <tr>
        <th>전화번호</th>
        <td><input type="text" name="phone" value="${user.phone}" /></td>
      </tr>
      <tr>
        <th>커뮤니티</th>
        <td>
          <select name="commId">
            <option value="0">없음</option>
            <c:forEach var="comm" items="${commList}">
              <option value="${comm.id}"
                <c:if test="${comm.id eq user.commId}">selected="selected"</c:if>>
                ${comm.name}</option>
            </c:forEach>
          </select>     
        </td>
      </tr>    
    </table>
    
    <div class="buttons">
      <button type="button" onClick="userModify()">수정</button>
      <a class="btn" href="<c:url value='/user/list' />">목록</a>
    </div>
  </form>
</body>
</html>