<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SchoolShow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
    <center>
        <h3 style="color: blue;">Список всех учеников и учителей</h3>
    </center>
        <div class="container">
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>ФИО</b></td>
            <td><b>Класс</b></td>
            <td><b>Сообщение</b></td>
        </tr>
        </thead>
        <c:forEach items="${cls}" var="on"> 
        <tr>
            <td>${on.firstname}</td> 
            <td>${on.letter}</td>
            <td><a href="/sen?name=${on.firstname}">Send</a></td>
        </tr>
        </c:forEach>
        </table>
        <h4 style="color: blue;">Всего учеников:${std} </h4>
        <h4 style="color: blue;">Всего учителей:${teth} </h4>
        <h4 style="color: blue;">Вместе:${std+teth} </h4> 
    </div> 
   </body>
</html>        