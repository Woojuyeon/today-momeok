<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>커뮤니티 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

/* 
 * 커뮤니티 이름 링크를 클릭할 때 새로운 요청(URI: "/community/view")을 비동기적으로 발생시키고(Ajax call)
 * ViewCommunityRestController가 생성한 JSON 형식의 검색 결과를 이용하여 
 * 선택된 커뮤니티의 상세 정보와 회원 리스트를 커뮤니티 리스트 아래에 각각 테이블로 출력시킴 (DOM API 이용)
 */
 
function getCommunityInfo(commId) {
	const requestUri = '<c:url value='/rest/community/view/'/>' + commId;			

	fetch(requestUri)     	// Ajax GET 요청
	.then(response => {		// 응답 객체
		if (!response.ok) throw new Error(reponse.statusText);
		return response.json();   	// 응답 데이터(JSON 문자열)를 parsing
	})
	.then(result => {		// Javascript 객체
		console.log(result);  
		printCommDetailAndMembers(result);
	})   			
	.catch(error => console.error('Error: ', error));
}

function printCommDetailAndMembers(community) { 	 					
	// 커뮤니티 상세 정보 테이블 생성
	$("#commDetail").empty(); 		// commDetail 엘리먼트의 모든 자식들을 삭제
	$("#commDetail").append("<b>" + community.name + "</b><br/>");	// commDetail 엘리먼트에 텍스트 추가
	$("#commDetail").append(document.createElement("table"));	// <table> 엘리먼트 추가
	$("#commDetail table").addClass("cTable");	// table 엘리먼트에 cTable 클래스 속성 추가 
	$("#commDetail table").append("<tr><th>커뮤니티 ID</th><td>"+community.id+"</td></tr>");
	$("#commDetail table").append("<tr><th>이름</th><td>"+community.name+"</td></tr>");
	$("#commDetail table").append("<tr><th>설명</th><td>"+community.description+"</td></tr>");
	$("#commDetail table").append("<tr><th>개설일자</th><td>"+community.startDate+"</td></tr>");
	$("#commDetail table").append("<tr><th>회원 수</th><td>"+community.numOfMembers+"</td></tr>");
	var chairId = "";
	if (community.chairId != null) chairId = community.chairId;
	$("#commDetail table").append("<tr><th>회장</th><td>"+chairId+"</td></tr>");				

	// 현재 커뮤니티 수정/삭제를 위한 URL 파라미터 값 추가
	$("#modifyComm").attr('href', $("#modifyComm").attr('href') + community.id);
	$("#deleteComm").attr('href', $("#deleteComm").attr('href') + community.id);
	$("#commBtns").show();
	$(".errmsg").hide();
	
	// 커뮤니티 회원 리스트 테이블 생성 
	$("#memberList").empty();				
	var members = community.memberList;
	if (members.length > 0) {
		$("#memberList").append("<div><b>회원:</b></div>");
		$("#memberList").append(document.createElement("table"));	
		$("#memberList table").addClass("uTable");
		$("#memberList table").append("<tr><th>사용자 ID</th><th>이름</th><th>이메일</th><th>전화번호</th></tr>");
		for (var i = 0; i < members.length; i++) { 
			var mem = members[i];
			$("#memberList table").append(
				"<tr><td>" + mem.userId + "</td>"
				+ "<td>" + mem.name + "</td>"
				+ "<td>" + mem.email + "</td>"
				+ "<td>" + mem.phone + "</td></tr>");
		}						
	}					
}

function commRemove() {
	return confirm("정말 삭제하시겠습니까?");
}

</script>
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
        <td>
          <%-- <a href="<c:url value='/community/view'>
                  <c:param name='commId' value='${comm.id}'/>
               </c:url>"> --%>            
          <a href="#" onclick="getCommunityInfo(${comm.id})">${comm.name}</a></td>
        <td>${comm.description}</td>
        <td>${comm.numOfMembers}</td>
      </tr>
    </c:forEach>
  </table>

  <div id="commDetail"></div>   <!-- 커뮤니티 상세 정보가 출력될 영역 -->    
  
  <div>
    <div id="commBtns" class="buttons" hidden="hidden">
      <a id="modifyComm" class="btn"
        href="<c:url value='/community/update?commId='/>">수정</a>
      <a id="deleteComm" class="btn"
        href="<c:url value='/community/delete?commId='/>"
        onclick="return commRemove();">삭제</a> 
    </div>
    
    <c:if test="${updateFailed || deleteFailed}">
      <!-- 수정/삭제가 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
      <div class="errmsg">${exception.getMessage()}</div>
    </c:if>  
  </div>  
  
  <div id="memberList"></div>   <!-- 커뮤니티 회원 리스트가 출력될 영역 -->
  
  <div class="buttons">
    <a class="btn" href="<c:url value='/community/create'/>">커뮤니티 추가</a>
    <a class="btn" href="<c:url value='/user/list'/>">사용자 목록</a>
  </div>
</body>
</html>