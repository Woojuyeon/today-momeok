<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%-- <%@page import="model.domain.*"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%
	@SuppressWarnings("unchecked") 
	List<Community> commList = (List<Community>)request.getAttribute("commList");
%> --%>
<html>
<head>
  <title>커뮤니티 관리</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/community.css'/>" type="text/css">
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="title">
    <span>커뮤니티 관리 - 리스트</span>
  </div>
  <table class="cTable">
    <tr>
      <!-- <td>커뮤니티 ID</td> -->
      <th>이름</th>
      <th>설명</th>
      <th>회원수</th>
    </tr>
    <c:forEach var="comm" items="${commList}">
      <tr>
        <td><a href="<c:url value='/community/view'>
				        <c:param name='commId' value='${comm.id}'/>
					 </c:url>">
            ${comm.name}</a></td>
        <td>${comm.description}</td>
        <td>${comm.numOfMembers}</td>
      </tr>
    </c:forEach>
  </table>
  <div class="buttons">
    <a class="btn" href="<c:url value='/community/create'/>">커뮤니티 추가</a>
    <a class="btn" href="<c:url value='/user/list'/>">사용자 목록</a>
  </div>
</body>
</html>