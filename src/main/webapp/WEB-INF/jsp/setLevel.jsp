<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>setLevel</title>
</head>
<body>
    <jsp:include page="menu.jsp" />
    <center>
        <h3 class="text-center" style="color: blue;">Оценки ученика: ${nme} </h3>
    </center>
    <div class="container">
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
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${lvl}" var="cl"> 
        <tr>
            <td>${cl.cls}</td> 
            <td>${cl.namestudent}</td>
            <td>${cl.science}</td> 
            <td><fmt:formatDate value="${cl.dat}" pattern="dd-MM-yyyy"/></td>
            <td>${cl.level}</td> 
            <td>${cl.nameteacher}</td> 
            <td>${cl.note}</td> 
            <td><a href="/dellvl?id=${cl.id}">Delete</a></td>
        </tr>
        </c:forEach>
        </table>    
	<form action="/levelStudent" method="post" > 
            <input type="date" name="datelevel" required>
            <select lass="mdb-select md-form" name="level" required>
                <option selected value="12">12</option>
                <option value="11">11</option>
                <option value="10">10</option>
                <option value="9">9</option>
                <option value="8">8</option>
                <option value="7">7</option>
                <option value="6">6</option>
                <option value="5">5</option>
                <option value="4">4</option>
                <option value="3">3</option>
                <option value="2">2</option>
                <option value="1">1</option>
            </select>
            <input type="text" name="note" placeholder="Примечание">
            <input type="submit" value="Сохранить" button type="button" class="btn btn-primary" >
        </form> 
    </div>
</body>
</html>
