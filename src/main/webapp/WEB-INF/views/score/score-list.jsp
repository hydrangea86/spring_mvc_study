<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>성적관리 프로그램</title>

    <style>
        label {
            display: block;
        }

        .score-list>li {
            margin-bottom: 10px;
        }

        .score-list>li:first-child {
            font-size: 1.2em;
            color: blue;
            font-weight: 700;
            border-bottom: 1px solid skyblue;
        }

        .del-btn {
            width: 10px;
            height: 10px;
            background: red;
            color: #fff;
            border-radius: 5px;
            margin-left: 5px;
            text-decoration: none;
            font-size: 0.7em;
            padding: 6px;
        }

        .del-btn:hover {
            background: orangered;
        }
    </style>
</head>

<body>

    <h1>시험 점수 등록</h1>
    <form action="/score/register" method="POST">
        <label>
            # 이름: <input type="text" name="name">
        </label>
        <label>
            # 국어: <input type="text" name="kor">
        </label>
        <label>
            # 영어: <input type="text" name="eng">
        </label>
        <label>
            # 수학: <input type="text" name="math">
        </label>
        <label>
            <button type="submit">확인</button>
            <button id="go-home" type="button">홈화면으로</button>
        </label>
    </form>

    <hr>
    <ul class="score-list">
        <li>총 학생 수: ${scores.size()}명</li>
        <c:forEach var="s" items="${scores}">
            <li># 학번: ${s.stuNum}, 이름: ${s.name}, 국어: ${s.kor}점,
                영어: ${s.eng}점, 수학: ${s.math}점, 총점: ${s.total}점,
                평균: ${s.average}점
            </li>
        </c:forEach>
    </ul>



</body>

</html>