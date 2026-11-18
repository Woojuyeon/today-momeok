<%@page contentType="text/html; charset=utf-8"%>
<%-- <%@page import="model.domain.*"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>커뮤니티 관리</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/community.css'/>" type="text/css">
  <script>
    function commModify() {
    	if (form.name.value == "") {
    		alert("이름을 입력하십시오.");
    		form.name.focus();
    		return false;
    	} 
    	if (form.desc.value == "") {
    		alert("설명을 입력하십시오.");
    		form.desc.focus();
    		return false;
    	}	
    	form.submit();
    }
  </script>
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="title">
    <span>커뮤니티 관리 - 수정</span>
  </div>
  <!-- Update Form  -->
  <form name="form" method="POST" action="<c:url value='/community/update' />">
    <input type="hidden" name="commId" value="${community.id}" />
    <table class="cTable">
      <tr>
        <th>커뮤니티 ID</th>
        <td>${community.id}</td>
      </tr>
      <tr>
        <th>이름</th>
        <td><input type="text" name="name" value="${community.name}"/></td>
      </tr>
      <tr>
        <th>설명</th>
        <td><input type="text" name="desc" value="${community.description}"/></td>
      </tr>
      <tr>
        <th>개설일자</th>
        <td>${community.startDate}</td>
      </tr>
      <tr>
        <th>회원 수</th>
        <td>${community.numOfMembers}</td>
      </tr>
      <tr>
        <th>회장</th>
        <td>
          <select name="chairId">
            <option value="">없음</option>
            <c:forEach var="mem" items="${community.memberList}">
              <option value="${mem.userId}"
                <c:if test="${mem.userId eq community.chairId}">selected="selected"</c:if>>
                ${mem.userId}</option>
            </c:forEach>
          </select>
        </td> 
      </tr>
      <tr>
        <th>회원</th>
        <td>
          <c:forEach var="mem" items="${community.memberList}">
		    ${mem.userId} &nbsp;
		  </c:forEach>
        </td>
      </tr>
    </table>
    <div class="buttons">
      <button type="button" onClick="commModify()">수정</button>
      <a class="btn" href="<c:url value='/community/list' />">커뮤니티 목록</a>
    </div>
  </form>
</body>
</html>