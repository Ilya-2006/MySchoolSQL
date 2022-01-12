<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MyClass</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
    <center>
        <h3 class="text-center" style="color: blue;">${yr}</h3>
    </center>
        <div class="container"> 
        <h4 class="text-center" style="color: blue;">Ученики</h4>    
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>ФИО</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${myc}" var="cl"> 
        <tr>
            <td>${cl.firstname}</td> 
            <td><a href="/sn?name=${cl.firstname}">Send</a></td>
        </tr>
        </c:forEach>
        </table>
        <h4 style="color: blue;">Всего: ${cnt} </h4> 
        <h4 style="color: blue;">Классный руководитель</h4>    
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>ФИО</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${tyc}" var="tl"> 
        <tr>
            <td>${tl.firstname}</td> 
            <td><a href="/sn?name=${tl.firstname}">Send</a></td>
            
        </tr>
        </c:forEach>
        </table>
    </div>
   </body>
</html>          
