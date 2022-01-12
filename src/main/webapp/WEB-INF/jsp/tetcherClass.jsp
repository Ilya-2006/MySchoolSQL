<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TetcherClass</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
        <div class="container"> 
        <h4 style="color: blue;">Ученики ${cls}</h4>    
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>ФИО</b></td>
            <td><b>Action</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${thc}" var="cl"> 
        <tr>
            <td>${cl.firstname}</td> 
            <td><a href="/snd?name=${cl.firstname}">Send</a></td>
            <td><a href="/lvl?name=${cl.firstname}">Level</a></td>
        </tr>
        </c:forEach>
        </table>
    </div>
   </body>
</html> 
