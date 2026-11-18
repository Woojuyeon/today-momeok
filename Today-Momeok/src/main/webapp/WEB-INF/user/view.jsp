<%@page contentType="text/html; charset=utf-8"%>
<%@page import="model.domain.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  User user = (User) request.getAttribute("user");
--%> 
<html>
<head>
  <title>사용자 관리</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
  <script>
  	function userRemove() {
  		return confirm("정말 삭제하시겠습니까?");
  	}
  </script>
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="title">
    <span>사용자 관리 - 사용자 정보 보기</span>
  </div>
  <table class="uTable">
    <tr>
      <th>사용자 ID</th>
      <td>${user.userId}<%-- <%=user.getUserId()%>--%></td>
    </tr>
    <tr>
      <th>이름</th>
      <td>${user.name}<%-- <%=user.getName()%>--%></td>
    </tr>
    <tr>
      <th>이메일 주소</th>
      <td>${user.email}<%-- <%=user.getEmail()%> --%></td>
    </tr>
    <tr>
      <th>전화번호</th>
      <td>${user.phone}<%-- <%=user.getPhone()%> --%></td>
    </tr>
    <tr>
      <th>소속 커뮤니티</th>
      <td>
        <a href="<c:url value='/community/view'>
            <c:param name='commId' value='${user.commId}'/>
          </c:url>">
          ${user.commName} <%-- <%=user.commName()%> --%>
        </a>
        <c:if test="${user.chair}">&nbsp;(회장)</c:if>
      </td>
    </tr>   
  </table>
  <div class="buttons">
    <a class="btn"
      href="<c:url value='/user/update'>    <!-- '/user/update/form'에서 수정됨 -->
	          <c:param name='userId' value='${user.userId}'/>
			 </c:url>">수정</a>
    <a class="btn"
      href="<c:url value='/user/delete'>
			   <c:param name='userId' value='${user.userId}'/>
			 </c:url>"
      onclick="return userRemove();">삭제</a>
    <a class="btn" href="<c:url value='/user/list'/>">목록</a>
  </div>

  <c:if test="${updateFailed || deleteFailed}">
    <!-- 수정 또는 삭제가 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
    <div class="errmsg">${exception.getMessage()}</div>
  </c:if>
</body>
</html>