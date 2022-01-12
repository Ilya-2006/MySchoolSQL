<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MyLevel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
        <div class="container"> 
        <h4 class="text-center" style="color: blue;">Оценки ${nm}</h4>    
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>Класс</b></td>
            <td><b>Ученик</b></td>
            <td><b>Предмет</b></td>
            <td><b>Дата</b></td>
            <td><b>Оценка</b></td>
            <td><b>Учитель</b></td>
            <td><b>Примечание</b></td>
        </tr>
        </thead>
        <c:forEach items="${myl}" var="cl"> 
        <tr>
            <td>${cl.cls}</td> 
            <td>${cl.namestudent}</td>
            <td>${cl.science}</td> 
            <td><fmt:formatDate value="${cl.dat}" pattern="dd-MM-yyyy"/></td>
            <td>${cl.level}</td> 
            <td>${cl.nameteacher}</td> 
            <td>${cl.note}</td> 
        </tr>
        </c:forEach>
        </table>
    </div>
   </body>
</html>          
