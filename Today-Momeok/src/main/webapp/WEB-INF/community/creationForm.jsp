<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>커뮤니티 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
<script>
    function postCommunityInfo() {
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
    
    	const params = { 
    		name : form.name.value, 
    		description : form.desc.value 
    	}
    	const requestUri = '<c:url value="/rest/community/create"/>';
    	
        fetch(requestUri, {					// Ajax POST 요청
        	method: 'POST',  headers: { 'Content-Type': 'application/json' },  		
        	body: JSON.stringify(params)	// 전송할 객체를 JSON 문자열로 변환 
        })
      	.then(response => {					// 응답 객체
      		if (!response.ok) throw new Error(response.statusText);
      		return response.json(); 		// JSON 응답 데이터 parsing
      	})
      	.then(result => console.log(result))  // parsing된 객체 	
      	.catch(error => console.error('Error: ', error));
        
        form.name.value = '';
        form.desc.value = '';
    }
</script>
</head>
<body>
  <div class="appTitle">${pageContext.servletContext.servletContextName}</div>
  <div class="title">
    <span>커뮤니티 관리 - 생성</span>
  </div>
  <!-- creation form  -->
  <form id="form" name="form">
    <table class="cTable">
      <tr>
        <th>이름</th>
        <td><input type="text" name="name"
          <c:if test="${creationFailed}">value="${comm.name}"</c:if> />
        </td>
      </tr>
      <tr>
        <th>설명</th>
        <td><input type="text" name="desc"
          <c:if test="${creationFailed}">value="${comm.description}"</c:if> />
        </td>
      </tr>
    </table>    
    <div class="buttons">
      <button type="button" onclick="postCommunityInfo()">생성</button>
      <a class="btn" href="<c:url value='/community/list' />">커뮤니티 목록</a>
    </div>    
  </form>
</body>
</html>