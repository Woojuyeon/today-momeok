<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>그룹 만들기</title>
</head>
<body>
    <main>
        <h1>그룹 만들기</h1>
        <a href="<c:url value='/main' />">돌아가기</a>
        <button type="button" disabled>링크로 멤버 추가하기</button>

        <form method="post" action="<c:url value='/group/addmember' />">
            <label for="userId">ID</label>
            <input type="text" id="userId" name="userId" maxlength="10" required>
            <button type="submit" disabled>추가하기</button>
        </form>
        <form method="get" action="<c:url value='/group/favoritegrouplist' />">
            <button type="submit" disabled>그룹 즐겨찾기</button>
        </form>

        <section aria-labelledby="members-heading">
            <h2 id="members-heading">같이 먹을 사람</h2>
            <ul>
                <c:forEach var="member" items="${group.members}">
                    <li>
                        <div>아이디: <c:out value="${member.userId}" /></div>
                        <c:if test="${not empty member.userName}">
                            <div>이름: <c:out value="${member.userName}" /></div>
                        </c:if>
                        <button type="button" disabled>멤버 삭제</button>
                    </li>
                </c:forEach>
            </ul>
        </section>

        <form method="post" action="<c:url value='/group/makegroup' />">
            <label>
                <input type="checkbox" name="filterFoodLog" value="true" <c:if test="${group.filterFoodLog}">checked</c:if>>
                최근 먹은 음식을 제외할까요?
            </label>
            <label for="filterDays">최근 먹은 음식 제외 일수</label>
            <input type="number" id="filterDays" name="filterDays" min="0" max="31" value="${group.filterDays}">일
            <button type="submit" disabled>추천받기!</button>
        </form>
    </main>
</body>
</html>
