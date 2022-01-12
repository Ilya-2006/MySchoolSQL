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
        <div class="col-md-10">
        <h4 class="text-center" style="color: blue;">Полный список учителей и учеников на ${begin} - ${end} учебный год</h4>            
        <table class = "table table-bordered">
        <thead>
        <tr>
            <td><b>Login</b></td>
            <td><b>Firstname</b></td>
            <td><b>Role</b></td>
            <td><b>Class</b></td>
            <td><b>Science</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${user}" var="bl"> 
        <tr>
            <td>${bl.login}</td>
            <td>${bl.firstname}</td>
            <td>${bl.role}</td>
            <td>${bl.letter}</td>
            <td>${bl.science}</td>
            <td><a href="/del?log=${bl.login}">Delete</a></td>
        </tr>
        </c:forEach>
        </table> 
        <form action="/addUsr" method="post">   
            <input type="text" name="login" placeholder="Login" required>
            <input type="text" name="password" placeholder="Password" required>
            <input type="text" name="firstname" placeholder="Firstname" required>
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
            <select lass="mdb-select md-form" name="letter">
       <option selected value="Арх-Класс">Арх-Класс</option>
                <option value="А-Класс">А-Класс</option>
                <option value="Арх-КлассРук">Арх-КлассРук</option>
                <option value="А-КлассРук">А-КлассРук</option>
                <option value="УЧИТЕЛЬ">УЧИТЕЛЬ</option>
                <option value="ЗАВУЧ">ЗАВУЧ</option>
            </select>
            <input type="text" name="science" placeholder="Предмет преподавания">
            <input type="submit" value="AddUser" button type="button" class="btn btn-primary" >
        </form> 
        <form action="/rnmUsr" method="post">   
            <input type="text" name="login" placeholder="Login" required>
            <input type="text" name="newlogin" placeholder="New Login">
            <input type="text" name="password" placeholder="New Password">
            <input type="text" name="firstname" placeholder="New Firstname">
            <select lass="mdb-select md-form" name="role">
                <option selected value="">New Role</option>
                <option value="USER">USER</option>
                <option value="GUEST">GUEST</option>
                <option value="ADMIN">ADMIN</option>
            </select>
            <select lass="mdb-select md-form" name="year">
                <option selected value="">0</option>
                <option value="1">1</option>
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
            <select lass="mdb-select md-form" name="letter">
                <option selected value="">New Class</option>
                <option value="Арх-Класс">Арх-Класс</option>
                <option value="А-Класс">А-Класс</option>
                <option value="Арх-КлассРук">Арх-КлассРук</option>
                <option value="А-КлассРук">А-КлассРук</option>
                <option value="УЧИТЕЛЬ">УЧИТЕЛЬ</option>
                <option value="ЗАВУЧ">ЗАВУЧ</option>
            </select>
            <input type="text" name="science" placeholder="New Science">
            <input type="submit" value="RnmUser" button type="button" class="btn btn-primary" >
        </form> 
        </div>
        <div class="col-md-2">
        <h4 class="text-center" style="color: blue;">Command</h4>
        <form action="/findLog" method="post" >  
            <input type="text" name="login" placeholder="Find Login" required>
            <input type="submit" value="FindLog" button type="button" class="btn btn-primary" >
        </form>   
        <form action="/findFname" method="post" >  
            <input type="text" name="firstname" placeholder="Find Firstname" required>
            <input type="submit" value="FindFnam" button type="button" class="btn btn-primary" >
        </form>  
        <form action="/sortFname" method="post" >  
            <input type="submit" value="SortFirstname" button type="button" class="btn btn-primary" >
        </form>  
         <form action="/sortLogin" method="post" >  
            <input type="submit" value="SortLogin" button type="button" class="btn btn-primary" >
        </form>  
        <form action="/sortClass" method="post" >  
            <input type="submit" value="SortClass" button type="button" class="btn btn-primary" >
        </form>
        </div>
   </body>
</html>

