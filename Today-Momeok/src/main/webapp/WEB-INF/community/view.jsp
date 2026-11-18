<%@page contentType="text/html; charset=utf-8"%>
<%-- <%@page import="model.domain.*"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>커뮤니티 관리</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
  <script>
	function communityRemove() {
		return confirm("정말 삭제하시겠습니까?");
	}
  </script>
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="title">
    <span>커뮤니티 관리 - 상세정보</span>
  </div>
  <table class="cTable">
    <tr>
      <th>커뮤니티 ID</th>
      <td>${community.id}</td>
    </tr>
    <tr>
      <th>이름</th>
      <td>${community.name}</td>
    </tr>
    <tr>
      <th>설명</th>
      <td>${community.description}</td>
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
      <td><a href="<c:url value='/user/view'>
				     <c:param name='userId' value='${community.chairId}'/>
			 	   </c:url>">
          ${community.chairId}</a></td>
    </tr>
    <tr>
      <th>회원</th>
      <td>
        <c:forEach var="member" items="${community.memberList}">
          <a href="<c:url value='/user/view'>
				     <c:param name='userId' value='${member.userId}'/>
			       </c:url>">
          ${member.userId}</a> &nbsp;
	    </c:forEach>
      </td>
    </tr>
  </table>
  <div class="buttons">
    <a class="btn"
      href="<c:url value='/community/update'>
	           <c:param name='commId' value='${community.id}'/>
			</c:url>">수정</a>
    <a class="btn"
      href="<c:url value='/community/delete'>
			   <c:param name='commId' value='${community.id}'/>
			</c:url>"
      onclick="return communityRemove();">삭제</a> 
    <a class="btn" href="<c:url value='/community/list'/>">커뮤니티 목록</a> 
  </div>
  
  <c:if test="${updateFailed || deleteFailed}">
    <!-- 수정/삭제가 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
    <div class="errmsg">${exception.getMessage()}</div>
  </c:if>
</body>
</html>