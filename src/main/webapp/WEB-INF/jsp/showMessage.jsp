<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MessageShow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
    <center>
        <h3 class="text-center" style="color: blue;">Все сообщения</h3>
    </center>
        <div> 
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>Дата</b></td>
            <td><b>От кого</b></td>
            <td><b>Кому</b></td>
            <td><b>Сообщение</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${msg}" var="cl"> 
        <tr>
            <td><calendar:formatDate value="${cl.dat}" pattern="dd-MM-yyyy"/></td>
            <td>${cl.username}</td>
            <td>${cl.sendname}</td> 
            <td>${cl.send}</td>
            <td><a href="/delsend?id=${cl.id}">Delete</a></td>
        </tr>
        </c:forEach>
        </table>
    </div>
   </body>
</html>          
