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
    <title>Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
    <body>
        <jsp:include page="menu.jsp" />
        <h3 class="text-center" style="color: blue;">Анкета ${surname} ${firstname} ${lastname}</h3>
        <div class="col-md-4"> 
            <h4 class="text-success">Пол:${sex}</h4>
            <h4 class="text-success">Дата рождения:<calendar:formatDate value="${datebirth}" pattern="dd-MM-yyyy"/></h4>
            <h4 class="text-success">Адрес проживания:${adres}</h4>
            <h4 class="text-success">Образование:${education}</h4>
            <h4 class="text-success">Должность:${positions}</h4>
            <h4 class="text-success">Телефон-1:${phone1}</h4>
            <h4 class="text-success">Телефон-2:${phone2}</h4>
            <h4 class="text-success">E-mail-1:${email1}</h4>
            <h4 class="text-success">E-mail-2:${email2}</h4>  
    </div>
    <div class="col-md-4">
        <img src="data:image/jpg;base64,${photo}" width="220" height="220" alt="No image">
    </div>
    <div class="col-md-4"> 
     <h4 style="color: blue;">Изменить:</h4>   
        <form action="/renameProf" method="post" >   
                <h4>Должность:</h4><p><textarea rows="1" cols="30" name=positions>${positions}</textarea></p>
                <h4>Образование:</h4><p><textarea rows="1" cols="30" name=education>${education}</textarea></p>
                <h4>Харакитеристика:</h4>            
                <p><textarea rows="8" cols="60" name="text">${note}</textarea></p>
                <p><input type="submit" value="Сохранить" button type="button" class="btn btn-primary" /></p>
        </form>
   </body>
</html>          
