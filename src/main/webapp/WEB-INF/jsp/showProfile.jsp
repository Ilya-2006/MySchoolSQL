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
    <title>AccauntShow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
        <div>
        <h4 class="text-center" style="color: blue;">Анкеты всех учителей и учеников</h4>            
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>Action</b></td>
            <td><b>Фамилия</b></td>
            <td><b>Имя</b></td>
            <td><b>Отчество</b></td>
            <td><b>Пол</b></td>
            <td><b>Год рождения</b></td>
            <td><b>Адрес</b></td>
            <td><b>Образование</b></td>
            <td><b>Должность</b></td>
            <td><b>Телефон 1</b></td>
            <td><b>Телефон 2</b></td>
            <td><b>Эл.почта 1</b></td>
            <td><b>Эл.почта 2</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${acc}" var="bl"> 
        <tr>
            <td><a href="/info?id=${bl.id}">Info</a></td>
            <td>${bl.surname}</td>
            <td>${bl.firstname}</td>
            <td>${bl.lastname}</td>
            <td>${bl.sex}</td>
            <td><calendar:formatDate value="${bl.datebirth}" pattern="dd-MM-yyyy"/></td>
            <td>${bl.adres}</td>
            <td>${bl.education}</td>
            <td>${bl.positions}</td>
            <td>${bl.phone1}</td>
            <td>${bl.phone2}</td>
            <td>${bl.email1}</td>
            <td>${bl.email2}</td>
            <td><a href="/delacc?id=${bl.id}">Delete</a></td>
        </tr>
        </c:forEach>
        </table> 
    </div>
   </body>
</html>          
