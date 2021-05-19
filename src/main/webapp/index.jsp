<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css'>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--    <script src="script.js" type="text/javascript"></script>--%>
<%--    <link rel="stylesheet" type="text/css" href="style.css">--%>
<%--    <script type="text/javascript">--%>
<%--        <%@include file="/script.js" char%>--%>
<%--    </script>--%>

    <style>
        <%@include file="/style.css"%>
    </style>
    <script src="script.js" charset="utf-8"></script>


    <title>TODO list</title>
</head>

<body>
<c:set var="user" scope="session" value="${user}"/>
<c:if test="${user.name != null}">
        <a class="nav-link" href="<%=request.getContextPath()%>/exit.do">
            Текущий пользователь: <c:out value="${user.name}"/> | Выйти</a>

</c:if>
<c:if test="${user.name == null}">

            <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">
                    Авторизация </a>

                    <%--    <div class="alert alert-primary" role="alert">--%>

<%--                <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>--%>

<%--                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">--%>
<%--                    Авторизация </a>--%>
<%--    </div>--%>
</c:if>


<div id="myDIV" class="header mt-5">
    <h2 style="margin:10px">To-Do List</h2>
    <input type="text" id="myInput" name="desc" placeholder="Task...">
    <button type="submit" id="sendTask" class="addBtn"
            onclick="doSend()">Добавить задачу
    </button>

</div>
    <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="showTasks">
            <label class="form-check-label" for="showTasks">
                Показать все задачи
            </label>
    </div>
    <div class="container">
        <h2 class="custom-colored-h1">Список задач</h2>
        <table class="table" id="table">
            <thead>
            <tr>
                <th>Задача</th>
                <th>Дата</th>
                <th>Статус</th>
                <th>Пользователь</th>
            </tr>
            </thead>
            <tbody>
            <tr id="tableRow">
            </tr>
            </tbody>
        </table>
    </div>
</body>

</html>