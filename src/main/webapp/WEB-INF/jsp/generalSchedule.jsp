<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ShowSchedules</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp"/>
    <center>
        <h4 style="color: blue;">Учебный план на ${period}</h4> 
    </center>
    <div class="col-md-10">
        <table class = "table table-bordered">
        <thead>
        <tr>    
            <td><b>Класс</b></td>
            <td><b>Урок №</b></td>
            <td><b>Комн №</b></td>
            <td><b>Название предмета</b></td>
            <td><b>ФИО перподавателя</b></td>
            <td><b>День недели</b></td>
            <td><b>Примечание</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${sce}" var="csi"> 
        <tr>
            <td>${csi.letter}</td>
            <td>${csi.namberlesson}</td>
            <td>${csi.nambercabinet}</td>           
            <td>${csi.science}</td>
            <td>${csi.nameteacher}</td>
            <td>${csi.weekday}</td>
            <td>${csi.notes}</td>
            <td><a href="/delShd?id=${csi.id}">Delete</a></td>
        </tr>
        </c:forEach>
        </table>
    </div>
    <div class="col-md-2">
        <h4 style="color: green">Смена учебного года</h4>
        <form action="/remDate" method="post"> 
            <input type="int" size="4" name="year"  pattern="[0-9]{4}" placeholder="Год" required>
            <input type="submit" value="RemDate" button type="button" class="btn btn-success" >
        </form> 
        <h4 style="color: red">Удаление всего расписания</h4>
        <form action="/delPlan" method="post"> 
            <input type="submit" value="DelPlan" button type="button" class="btn btn-danger" >
        </form> 
    </div>
   </body>
</html> 