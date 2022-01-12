<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EditSchedules</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
    <center>
        <h4 style="color: blue;">Учебный план на ${period}</h4> 
    </center>
        <div class="col-md-4"> 
        <form action="/choiceTeacher" method="post"> 
            <h4 style="color: blue;">Преподаватель: ${th} </h4>   
                <table class = "table table-bordered">
                <thead>
                <tr>
                    <td><b>ФИО</b></td>
                    <td><b>Предмет</b></td>
                    <td><b>Выбор</b></td>
                </tr>
                </thead>
                <c:forEach items="${thr}" var="us"> 
                <tr>
                    <td>${us.firstname}</td>
                    <td>${us.science}</td>
                    <td><input type="radio" id="contactChoice" name="contact" checked value="${us.firstname}"></td>
                </tr>
                </c:forEach>
                </table> 
                <input type="submit" value="Add" button type="button" class="btn btn-primary" >
            </form>
    </div>
    <div class="col-md-8">
        <h4 style="color: blue;">Раписание занятий на день недели: ${wday} для ${cl}</h4>
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
            <td><a href="/delIds?id=${csi.id}">Delete</a></td>
        </tr>
        </c:forEach>
        </table>
        <form action="/ext" method="post">
                <select lass="mdb-select md-form" name="year">
                <option selected value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
            </select>
            <select lass="mdb-select md-form" name="let">
                <option selected value="Арх-Класс">Арх-Класс</option>
                <option value="А-Класс">А-Класс</option>
            </select>
            <select lass="mdb-select md-form" name="week">
                <option selected value="понедельник">понедельник</option>
                <option value="вторник">вторник</option>
                <option value="среда">среда</option>
                <option value="четверг">четверг</option>
                <option value="пятница">пятница</option>
                <option value="суббота">суббота</option>
            </select>
                <input type="submit" value="Add" button type="button" class="btn btn-primary" >
            </form>    
        <form action="/addPlan" method="post"> 
            <input type="text" size="4" name="lesson"  pattern="[0-9]{1}" placeholder="#урок" required>
            <input type="text" size="4" name="cabinet"  pattern="[0-9]{1,2}" placeholder="#комн" required>
            <input type="text" name="science" placeholder="Название предмета">
            <input type="text" name="notes" placeholder="Примечание">
            <input type="submit" value="AddPlan" button type="button" class="btn btn-primary" >
        </form> 
    </div>
   </body>
</html>          
