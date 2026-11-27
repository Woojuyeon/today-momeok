<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 즐겨찾기</title>
<style>
    .container {
        width: 400px;
        margin: 30px auto;
    }

    h2 {
        text-align: center;
    }

    .group-list {
        margin-top: 30px;
    }

    .group-item {
        display: flex;
        align-items: center;
        gap: 8px;
        margin-bottom: 15px;
    }

    .group-name {
        flex: 1;
        border: 1px solid #999;
        padding: 15px;
    }

    button {
        padding: 10px 15px;
        cursor: pointer;
    }

    .add-area {
        text-align: center;
        margin-top: 20px;
    }

    .save-area {
        margin-top: 80px;
    }

    .save-btn {
        width: 100%;
    }
</style>

</head>
<body>
<div class="container">

    <button type="button">뒤로가기</button>

    <h2>그룹 즐겨찾기</h2>

    <div class="group-list">

        <div class="group-item">
            <div class="group-name">
                즐찾그룹1
            </div>
            <button type="button">편집</button>
            <button type="button">삭제</button>
        </div>

        <div class="group-item">
            <div class="group-name">
                즐찾그룹2
            </div>
            <button type="button">편집</button>
            <button type="button">삭제</button>
        </div>

        <div class="group-item">
            <div class="group-name">
                즐찾그룹3
            </div>
            <button type="button">편집</button>
            <button type="button">삭제</button>
        </div>

    </div>

    <div class="add-area">
        <button type="button">+ 추가하기</button>
    </div>

    <div class="save-area">
        <button type="button" class="save-btn">
            저장하기
        </button>
    </div>

</div>
</body>
</html>