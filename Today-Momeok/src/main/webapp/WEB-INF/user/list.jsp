<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@page import="model.domain.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--   
  // 아래와 같은  scriptlet은 생략하고 EL로 구현하는 것이 바람직함 
  @SuppressWarnings("unchecked")
  List<User> userList = (List<User>) request.getAttribute("userList");
  String curUserId = (String) request.getAttribute("curUserId");
--%>
<html>
<head>
  <title>사용자 관리</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="logout">
    <a href="<c:url value='/user/logout' />">로그아웃
      (&nbsp;${curUserId}&nbsp;)</a>
  </div>
  <div class="title">
    <span>사용자 관리 - 리스트</span>
  </div>
  <table class="uTable">
    <tr>
      <th>사용자 ID</th>
      <th>이름</th>
      <th>이메일</th>
      <th>소속(커뮤니티)</th>
    </tr>
<%--
    <%
      // 아래와 같은 scriptlet은 생략하고 <c:for-each>와 EL로 구현하는 것이 바람직함 
      if (userList != null) {
      	Iterator<User> userIter = userList.iterator();
  
      	//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
      	while (userIter.hasNext()) {
      		User user = (User) userIter.next();
    %>
    <tr>
      <td><%=user.getUserId()%></td>
      <td><a href="<c:url value='/user/view'>
		    <c:param name='userId' value='<%=user.getUserId()%>'/>
 		  </c:url>">
          <%=user.getName()%></a></td>
      <td><%=user.getEmail()%></td>
    </tr>
    <%
        }
      }
    %>
 --%>
    <c:forEach var="user" items="${userList}">            
      <tr>
        <td>${user.userId}</td>
        <td><a href="<c:url value='/user/view'>
                <c:param name='userId' value='${user.userId}'/>
              </c:url>">
              ${user.name}</a></td>
        <td>${user.email}</td>
        <td><a href="<c:url value='/community/view'>
                <c:param name='commId' value='${user.commId}'/>
              </c:url>">
              ${user.commName}</a></td>
      </tr>
    </c:forEach>
  </table> 
  <div class="buttons">
    <a class="btn" href="<c:url value='/user/register'/>">사용자 추가</a>
    <a class="btn" href="<c:url value='/community/list'/>">커뮤니티 목록</a>
  </div>
</body>
</html>